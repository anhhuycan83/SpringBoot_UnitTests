package com.kataria.springboot.rest.practice.dao.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.kataria.springboot.rest.practice.core.beans.User;
import com.kataria.springboot.rest.practice.core.data.user.UserSampleDataProvider;

public class InMemoryUserResourceRepositoryTest extends AbstractInMemoryUserResourceRepositoryTest {

	@BeforeEach
	public void setup() throws Throwable {
		initialiseData(() -> inMemoryUserResourceRepository.initData(UserSampleDataProvider.sampleUsersMap()));
	}

	@Test
	@DisplayName("Method:getAllUsers , TestCase:GetAllUsersForPredefinedData")
	public void getAllUsers() {
		Map<Integer, User> actualUsersMap = inMemoryUserResourceRepository.getAllUsers();
		assertAll(() -> assertTrue(Objects.nonNull(actualUsersMap) && !actualUsersMap.isEmpty()),
				() -> assertArrayEquals(
						UserSampleDataProvider.sampleUsersMap().entrySet().stream().toArray(Entry[]::new),
						actualUsersMap.entrySet().stream().toArray(Entry[]::new)));
	}

	@Test
	@DisplayName("Method:addUser , TestCase:AddUserForPredefinedData")
	public void addUser() {
		User user = inMemoryUserResourceRepository.addUser(User.of(0, "Sahil"));
		assertAll(() -> Objects.nonNull(user), () -> assertEquals(User.of(6, "Sahil"), user),
				() -> assertTrue(inMemoryUserResourceRepository.getAllUsers().get(6).equals(User.of(6, "Sahil"))));
	}

	@Test
	@DisplayName("Method:removeUser , TestCase:RemoveUserFromPredefinedData")
	public void removeUser() {
		User actuallyRemoveduser = inMemoryUserResourceRepository.removeUser(5);
		assertAll(() -> assertEquals(User.of(5, "Rashmi"), actuallyRemoveduser),
				() -> assertTrue(() -> !inMemoryUserResourceRepository.getAllUsers().containsKey(5)));
	}

}
