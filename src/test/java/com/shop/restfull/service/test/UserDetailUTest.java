package com.shop.restfull.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import com.shop.restfull.model.ERole;
import com.shop.restfull.model.Role;
import com.shop.restfull.model.User;
import com.shop.restfull.serviceImpl.UserDetailsImpl;

public class UserDetailUTest {

	private UserDetailsImpl userDetails;
	
	@Test
	public void givenUserShouldReturnUserDetails() {
		User user = this.createUser();
		Set<Role> roles = this.createRoles();
		user.setRoles(roles);
		UserDetailsImpl userDetailsImpl = userDetails.build(user);
		assertThat(userDetailsImpl.getUsername()).isEqualTo(user.getUsername());
	}
	@Test
	public void givenOneRoleShouldReturnCorrectSize() {
		User user = this.createUser();
		Set<Role> roles = this.createRoles();
		user.setRoles(roles);
		UserDetailsImpl userDetailsImpl = userDetails.build(user);
		assertThat(userDetailsImpl.getAuthorities().size()).isEqualTo(1);
	}
	@Test
	public void givenNullAuthoritiesShouldReturnAuthoritiesNull() {
		User user = this.createUser();
		user.setRoles(null);
		UserDetailsImpl userDetailsImpl = userDetails.build(user);
		assertThat(userDetailsImpl.getAuthorities()).isNull();
	}
	
	private User createUser() {
		User user = new User();
		user.setId(1L);
		user.setEnabled(true);
		user.setPassword("1234");
		user.setUsername("Chechu");
		return user;
	}
	private Set<Role> createRoles(){
		Set<Role> roles = new HashSet<>();
		Role rol = new Role();
		rol.setId(1);
		rol.setName(ERole.ROLE_USER);
		roles.add(rol);
		return roles;
	}
}
