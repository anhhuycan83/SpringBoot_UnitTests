package com.kataria.springboot.rest.practice.dao.user;

import java.util.Map;
import com.kataria.springboot.rest.practice.core.beans.User;

public interface UserResourceRepository {

	public Map<Integer, User> getAllUsers();

	public User addUser(User user);

	public User removeUser(Integer userId);

}