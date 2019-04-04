package com.kataria.springboot.rest.practice.dao.user;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kataria.springboot.rest.practice.core.beans.User;;

@Repository
public class InMemoryUserResourceRepository implements UserResourceRepository {

	private Map<Integer, User> usersMap;

	@Autowired
	public void initData(Map<Integer, User> usersMap) {
		this.usersMap = usersMap;
	}

	public Map<Integer, User> getAllUsers() {
		return usersMap;
	}

	public User addUser(User user) {
		Optional<Integer> optionalMax = usersMap.keySet().stream().max(Comparator.naturalOrder());
		user.setId(optionalMax.isPresent() ? optionalMax.get() + 1 : 1);
		usersMap.put(user.getId(), user);
		return user;
	}

	public User removeUser(Integer userId) {
		return usersMap.remove(userId);
	}

}