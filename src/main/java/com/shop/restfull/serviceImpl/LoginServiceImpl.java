package com.shop.restfull.serviceImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.dto.Mail;
import com.shop.restfull.model.ERole;
import com.shop.restfull.model.RegistrationToken;
import com.shop.restfull.model.Role;
import com.shop.restfull.model.User;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.payload.request.LoginRequest;
import com.shop.restfull.payload.request.SignupRequest;
import com.shop.restfull.payload.response.JwtResponse;
import com.shop.restfull.payload.response.MessageResponse;
import com.shop.restfull.repository.RegistrationTokenRepository;
import com.shop.restfull.repository.RoleLoginRepository;
import com.shop.restfull.repository.UserLoginRepository;
import com.shop.restfull.service.IEmailService;
import com.shop.restfull.service.ILoginService;
import com.shop.restfull.service.IUsuarioService;
import com.shop.restfull.utils.JwtUtils;
@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserLoginRepository userRepository;

	@Autowired
	RoleLoginRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private IUsuarioService usuarioService;	
	
	@Autowired 
    private IEmailService emailService;
	
	@Autowired
	private RegistrationTokenRepository registrationTokenRepository;
	
	private final static String ACTIVATE = "activate";
	private final static String EMAIL_REMITER = "vintagetiendaonlinecontact@gmail.com";
	private final static String EMAIL_SUBJECT= "Bienvenido a MCyclo";
	private final static String EMAIL_SUBJECT_ENGLISH = "Welcome to MCyclo";
	private final static String EMAIL_SIGNATURE = "MCyclo Services";
	
	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = this.getAuthenticationUserRoles(userDetails);
		
		this.saveUserRememberLogin(loginRequest);

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 roles,
												 loginRequest.isRememberLogin()));
	}
	
	private void saveUserRememberLogin(LoginRequest loginRequest) {
		if(loginRequest.isRememberLogin()) {
			User user = this.userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
			if(user != null && user.isRememberLogin() != null && user.isRememberLogin() != loginRequest.isRememberLogin()) {
				user.setRememberLogin(loginRequest.isRememberLogin());
				this.userRepository.save(user);
			}
			
		}
	}
	
	private List<String> getAuthenticationUserRoles(UserDetailsImpl userDetails){
		return userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest, HttpServletRequest request) throws SQLException{
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User userLogin = this.createAndSaveNewUserLogin(signUpRequest);
		Usuario userProfile = this.createAndSaveUserProfile(userLogin);
		String respuestaEmailSender = this.sendRegistrationEmail(signUpRequest.getLanguage(), userProfile, request);
		if(respuestaEmailSender.equals("error")) {
			throw new SQLException();
		}
		
		return ResponseEntity.ok(userProfile);
	}
	
	private User createAndSaveNewUserLogin(SignupRequest signUpRequest) {
		User user = new User(signUpRequest.getUsername(), 
				 signUpRequest.getEmail(),
				 encoder.encode(signUpRequest.getPassword()),
				 signUpRequest.getRememberLogin(),
				 signUpRequest.getEnabled());
		user.setRoles(this.setUserRoles(signUpRequest));
		userRepository.save(user);
		return user; 
	}
	
	private Usuario createAndSaveUserProfile(User userLogin) {
		Usuario userProfile = new Usuario();
		userProfile.setAdmin(false);
		userProfile.setUsuario(userLogin.getUsername());
		userProfile.setEmail(userLogin.getEmail());
		userProfile.setEmailConfirmado(false);
		//return userProfile;
		return usuarioService.crearUsuario(userProfile);
	}
	
	private Set<Role> setUserRoles(SignupRequest signUpRequest){
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = this.addRole(roles, ERole.ROLE_USER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = this.addRole(roles, ERole.ROLE_ADMIN);
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = this.addRole(roles, ERole.ROLE_MODERATOR);
					roles.add(modRole);
					break;
				default:
					Role userRole = this.addRole(roles, ERole.ROLE_USER);
					roles.add(userRole);
				}
			});
		}
		return roles;
	}
	
	private Role addRole(Set<Role> roles, ERole eRole) {
		return roleRepository.findByName(eRole)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	}
	
	private String sendRegistrationEmail(String language, Usuario userProfile, HttpServletRequest request) {
		
		RegistrationToken registrationToken = this.createActivationUserToken(userProfile);
		Map<String, Object> model = this.createTemplateModel(userProfile, request, registrationToken);
        Mail mail = this.createMail(userProfile, language, model);
        try {
        	emailService.sendEmail(mail, language, ACTIVATE);
        }catch(Exception e) {
        	e.printStackTrace();
        	return "error";
        }

        return "ok";
	}
	
	private RegistrationToken createActivationUserToken(Usuario userProfile) {
		RegistrationToken registrationToken = new RegistrationToken();
		registrationToken.setToken(UUID.randomUUID().toString());
		registrationToken.setUser(userProfile);
		registrationToken.setExpiryDate(10080);// 7 dias
		this.registrationTokenRepository.save(registrationToken);
        return registrationToken;
	}
	private Mail createMail(Usuario userProfile, String language, Map<String, Object> model) {
		Mail mail = new Mail();
        mail.setFrom(EMAIL_REMITER);
        mail.setTo(userProfile.getEmail());
        String asunto = EMAIL_SUBJECT;
        if(language.equals("en"))asunto = EMAIL_SUBJECT_ENGLISH;
        mail.setSubject(asunto);
        mail.setModel(model);
        return mail;
	}
	private Map<String, Object> createTemplateModel(Usuario userProfile, HttpServletRequest request, RegistrationToken registrationToken){
		Map<String, Object> model = new HashMap<>();
        model.put("user", userProfile);
        model.put("signature", EMAIL_SIGNATURE);
        //String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        int port = 4200;
        String url = request.getScheme() + "://" + request.getServerName() + ":" + port;
        model.put("activateAccountUrl", url + "/home?token=" + registrationToken.getToken());
        return model;
	}
	
	@Override
	@Transactional
	public ResponseEntity<Usuario> activateUserByToken(String token){
		if(token == null) {
			return new ResponseEntity<>(null);
		}
		RegistrationToken registrationToken = this.registrationTokenRepository.findByToken(token);
		Usuario userProfile = registrationToken.getUser();
		this.registrationTokenRepository.delete(registrationToken);
		if(userProfile == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(userProfile);
	}

}
