package com.kataria.springboot.rest.practice.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kataria.springboot.rest.practice.core.beans.User;
import com.kataria.springboot.rest.practice.core.beans.User.Address;

public interface UserResourceRepository {

	public static Map<Integer, User> sampleUsersMap() {

		User user = User.Id(1).setFirstName("Vaneet")
				.setJuniors(Stream.of("Deepak", "Dheeraj", "Franka").collect(Collectors.toList()))
				.setCorrespondingAddresses(
						Stream.of(Address.of("k612"), Address.of("ucp39")).collect(Collectors.toList()))
				.build();

		return Stream.of(user, User.of(2, "Mansi"), User.of(3, "Neealm"), User.of(4, "Naresh"), User.of(5, "Rashmi"))
				.collect(Collectors.toMap(User::getId, Function.identity(), (u1, u2) -> u1, LinkedHashMap::new));
	}

	public Map<Integer, User> getAllUsers();

	public User addUser(User user);

	public User removeUser(Integer userId);

}