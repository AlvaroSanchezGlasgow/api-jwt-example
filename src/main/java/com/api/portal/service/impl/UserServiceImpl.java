package com.api.portal.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import com.api.portal.dao.UserDao;
import com.api.portal.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.portal.service.UserService;


@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;
	
	private final String NOT_ACTIVE= "N";
	private final String ACTIVE= "Y";
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername -->"+username);
		User user = userDao.findByUsernameLogin(username);
		log.info("user -->"+user.toString());
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public List findAll() {
		List list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public User fidUserById(String id) {

		return Optional.ofNullable(userDao.fidUserById(Integer.parseInt(id))).orElse(new User());

	}


	@Override
    public void save(User newUser) throws NullPointerException, IllegalArgumentException {

        if (userDao.findUserByUsername(newUser.getUsername().trim().toUpperCase()) != null)
            throw new IllegalArgumentException("User with the username : " + newUser.getUsername() + "  already exists");

        if ((newUser.getUsername() == null) || (newUser.getPassword() == null)
                || (newUser.getFirstName() == null) || (newUser.getLastName() == null)
                || (newUser.getEmail() == null)  || (newUser.getComment() == null) ) {
            throw new NullPointerException("Mandatory fields on the User Object contains null elements " + newUser.toString());
        }

		 userDao.save(newUser);
    }

	@Override
	public User fidUserByUsername(String username) {

		return Optional.ofNullable(userDao.findUserByUsername(username)).orElse(new User());

	}

	@Override
	public void update(User user) {

		userDao.updateUser(user);

	}

	@Override
	public void delete(String item) {

		userDao.deleteUser(Integer.parseInt(item));

	}


}