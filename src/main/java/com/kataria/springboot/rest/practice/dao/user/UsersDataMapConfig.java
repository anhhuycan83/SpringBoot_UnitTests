package com.kataria.springboot.rest.practice.dao.user;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kataria.springboot.rest.practice.core.beans.User;

@Configuration
public class UsersDataMapConfig {

	@Bean
	public Map<Integer, User> usersMap() {
		return Stream.of(User.of(1, "vaneet"), User.of(2, "Prakash"), User.of(3, "Deepak"))
				.collect(Collectors.toMap(User::getId, Function.identity(), (u1, u2) -> u1, LinkedHashMap::new));
	}

}
