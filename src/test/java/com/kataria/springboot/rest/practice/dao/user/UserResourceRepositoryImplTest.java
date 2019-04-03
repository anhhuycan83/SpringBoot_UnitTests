package com.kataria.springboot.rest.practice.dao.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kataria.springboot.rest.practice.core.beans.User;

@ExtendWith(MockitoExtension.class)
public class UserResourceRepositoryImplTest {

	private UserResourceRepositoryImpl userResourceRepositoryImpl = new UserResourceRepositoryImpl();

	@BeforeEach
	public void setup() {
		userResourceRepositoryImpl.init();
	}

	@Test
	@DisplayName("Method:getAllUsers , TestCase:getAllUsersForPredefinedData")
	public void getAllUsers_getAllUsersForPredefinedData() {
		Map<Integer, User> actualUsersMap = userResourceRepositoryImpl.getAllUsers();

		assertAll(() -> assertTrue(Objects.nonNull(actualUsersMap) && !actualUsersMap.isEmpty()),
				() -> assertArrayEquals(
						UserResourceRepository.sampleUsersMap().entrySet().stream().toArray(Entry[]::new),
						actualUsersMap.entrySet().stream().toArray(Entry[]::new)));
	}

	@Test
	public void addUser() {

	}

	@Test
	public void removeUser() {

	}

}
