package com.api.portal.service;

import java.util.List;

import com.api.portal.dto.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {

	
	UserDetails loadUserByUsername(String userId);

	List findAll();

	User fidUserById(String item);

    User fidUserByUsername(String username);

	void save(User user);

	void update(User user);

	void delete(String item);


}
