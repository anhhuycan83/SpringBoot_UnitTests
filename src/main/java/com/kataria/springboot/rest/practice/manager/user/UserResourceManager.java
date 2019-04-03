package com.kataria.springboot.rest.practice.manager.user;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kataria.springboot.rest.practice.core.beans.User;
import com.kataria.springboot.rest.practice.core.beans.UserList;
import com.kataria.springboot.rest.practice.core.validate.Assert;
import com.kataria.springboot.rest.practice.dao.user.UserResourceRepository;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;

@Component
public class UserResourceManager {

	public static final String NO_USERS_EXIST = "NO_USERS_EXIST";
	public static final String NO_USER_EXIST = "NO_USER_EXIST";
	public static final String INVALID_INPUT_PARAMS = "INVALID_INPUT_PARAMS";

	@Autowired
	private UserResourceRepository userResourceRepository;

	public UserList getAllUsers() throws UserResourceException {
		try {
			Map<Integer, User> usersMap = userResourceRepository.getAllUsers();
			Assert.isTrue(Objects.nonNull(usersMap) && !usersMap.isEmpty(), NO_USERS_EXIST);
			return new UserList(Collections.list(Collections.enumeration(usersMap.values())));
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public User getUser(Integer userId) throws UserResourceException {
		try {
			Map<Integer, User> usersMap = userResourceRepository.getAllUsers();
			Assert.isTrue(Objects.nonNull(usersMap) && usersMap.containsKey(userId), NO_USER_EXIST);
			return usersMap.get(userId);
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public User addUser(User user) throws UserResourceException {
		try {
			return userResourceRepository.addUser(user);
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public void deleteUser(Integer userId) throws UserResourceException {
		try {
			Assert.isTrue(Objects.nonNull(userResourceRepository.removeUser(userId)), NO_USER_EXIST);
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public List<User.Address> getAllCorresspondingAddress(Integer userId) throws UserResourceException {
		try {
			Map<Integer, User> usersMap = userResourceRepository.getAllUsers();
			Assert.isTrue(Objects.nonNull(usersMap) && usersMap.containsKey(userId), NO_USER_EXIST);
			return usersMap.get(userId).getCorrespondingAddresses();
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public List<String> getAllJuniors(Integer userId) throws UserResourceException {
		try {
			Map<Integer, User> usersMap = userResourceRepository.getAllUsers();
			Assert.isTrue(Objects.nonNull(usersMap) && usersMap.containsKey(userId), NO_USER_EXIST);
			return usersMap.get(userId).getJuniors();
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

}
