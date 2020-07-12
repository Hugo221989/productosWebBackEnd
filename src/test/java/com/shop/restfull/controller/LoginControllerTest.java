package com.shop.restfull.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.shop.restfull.model.User;
import com.shop.restfull.payload.request.SignupRequest;
import com.shop.restfull.repository.UserLoginRepository;
import com.shop.restfull.serviceImpl.UserDetailsImpl;

public class LoginControllerTest {
	
	@Mock
	AuthenticationManager authenticationManager;
	
	@Mock
	UserLoginRepository userRepository;
	
	@Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }   
	
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
         Authentication authentication = mock(Authentication.class);
         SecurityContextHolder.getContext().setAuthentication(authentication);
 		 UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		 when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
    }
    
    @Test
    public void givenSignUpRequestShouldExist() {
    	SignupRequest signUpRequest = new SignupRequest();
    	signUpRequest.setUsername("Manolo");
    	signUpRequest.setPassword("1234");
    	signUpRequest.setEnabled(true);
    	User user = this.fillUserFromSignUp(signUpRequest);
    	Optional<User> userOptional = Optional.of(user);
    	when(userRepository.findByUsername("Manolo")).thenReturn(userOptional);
    	assertEquals(userRepository.findByUsername("Manolo"), userOptional);
    }
    
    private User fillUserFromSignUp(SignupRequest signUpRequest) {
    	User user = new User(signUpRequest.getUsername(), 
				 signUpRequest.getEmail(),
				 signUpRequest.getPassword(),
				 signUpRequest.getRememberLogin(),
				 signUpRequest.getEnabled());
    	return user;
    }
}
