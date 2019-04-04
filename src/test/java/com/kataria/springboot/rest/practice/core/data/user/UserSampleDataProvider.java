package com.kataria.springboot.rest.practice.core.data.user;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kataria.springboot.rest.practice.core.beans.User;
import com.kataria.springboot.rest.practice.core.beans.User.Address;

public class UserSampleDataProvider {

	public static Map<Integer, User> sampleUsersMap() {
		User user = User.id(1).firstName("Vaneet")
				.juniors(Stream.of("Deepak", "Dheeraj", "Franka").collect(Collectors.toList()))
				.correspondingAddresses(Stream.of(Address.of("k612"), Address.of("ucp39")).collect(Collectors.toList()))
				.build();

		return Stream.of(user, User.of(2, "Mansi"), User.of(3, "Neealm"), User.of(4, "Naresh"), User.of(5, "Rashmi"))
				.collect(Collectors.toMap(User::getId, Function.identity(), (u1, u2) -> u1, LinkedHashMap::new));
	}

}
