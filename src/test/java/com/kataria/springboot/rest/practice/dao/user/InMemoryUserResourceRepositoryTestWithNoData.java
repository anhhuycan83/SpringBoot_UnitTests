package com.kataria.springboot.rest.practice.dao.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kataria.springboot.rest.practice.core.beans.User;

@ExtendWith(MockitoExtension.class)
public class InMemoryUserResourceRepositoryTestWithNoData extends AbstractInMemoryUserResourceRepositoryTest {

	@BeforeEach
	public void setup() throws Throwable {
		initialiseData(() -> inMemoryUserResourceRepository.initData(new LinkedHashMap<>()));
	}

	@Test
	@DisplayName("Method:getAllUsers , TestCase:GetAllUsersForNoPredefinedData")
	public void getAllUsers() {
		Map<Integer, User> actualUsersMap = inMemoryUserResourceRepository.getAllUsers();
		assertTrue(() -> actualUsersMap.isEmpty());
	}

	@Test
	@DisplayName("Method:addUser , TestCase:AddUserForNoPredefinedData")
	public void addUser() {
		User userActuallyAdded = inMemoryUserResourceRepository.addUser(User.of(0, "Sahil"));
		assertAll(() -> assertNotNull(userActuallyAdded), () -> assertEquals(User.of(1, "Sahil"), userActuallyAdded),
				() -> assertTrue(
						() -> inMemoryUserResourceRepository.getAllUsers().get(1).equals(User.of(1, "Sahil"))));
	}

	@Test
	@DisplayName("Method:removeUser , TestCase:RemoveUserFromNoPredefinedData")
	public void removeUser() {
		User actuallyRemoveduser = inMemoryUserResourceRepository.removeUser(5);
		assertAll(() -> assertTrue(inMemoryUserResourceRepository.getAllUsers().isEmpty()),
				() -> assertNull(actuallyRemoveduser));
	}

}
