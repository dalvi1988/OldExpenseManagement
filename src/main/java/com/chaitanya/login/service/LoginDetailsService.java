package com.chaitanya.login.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaitanya.login.convertor.LoginConvertor;
import com.chaitanya.login.dao.ILoginDAO;
import com.chaitanya.login.model.Login;
import com.chaitanya.login.model.LoginDTO;
import com.chaitanya.login.model.LoginUserDetails;
import com.chaitanya.login.model.UserRole;

@Service("loginDetailsService")
public class LoginDetailsService implements UserDetailsService {

	@Autowired
	private ILoginDAO loginDAO;

	
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	
		Login loginDetails = loginDAO.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(loginDetails.getUserRole());
		LoginDTO loginDTO=LoginConvertor.setLoginToLoginDTO(loginDetails);
		return buildUserForAuthentication(loginDetails, authorities,loginDTO);
		
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(Login loginDetails, List<GrantedAuthority> authorities,LoginDTO loginDTO) {
		return new LoginUserDetails(loginDetails.getUserName(), loginDetails.getPassword(), true, true, true, true, authorities,loginDTO);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}