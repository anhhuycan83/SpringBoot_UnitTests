package com.kataria.springboot.rest.practice.manager.user;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.BDDMockito.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kataria.springboot.rest.practice.core.beans.User;
import com.kataria.springboot.rest.practice.core.beans.User.Address;
import com.kataria.springboot.rest.practice.core.data.user.UserSampleDataProvider;
import com.kataria.springboot.rest.practice.core.tags.TagsConstants;
import com.kataria.springboot.rest.practice.dao.user.UserResourceRepository;
import com.kataria.springboot.rest.practice.core.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;

@EnabledIfSystemProperty(named = "junit.version", matches = "5")
@EnabledIfEnvironmentVariable(named = "environment", matches = "dev")
@EnabledOnOs({ OS.LINUX, OS.WINDOWS, OS.MAC, OS.SOLARIS, OS.AIX })
@EnabledOnJre({ JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11, JRE.JAVA_12, JRE.JAVA_13 })
@ExtendWith(MockitoExtension.class)
@Tags({ @Tag(TagsConstants.DEV), @Tag(TagsConstants.PROD), @Tag(TagsConstants.QA), @Tag(TagsConstants.RFS),
		@Tag(TagsConstants.MANAGER), @Tag(TagsConstants.CI_SERVER) })
public class UserResourceManagerTest {

	private static final String DATASOURCE_NOT_AVAILABLE = "DATASOURCE_NOT_AVAILABLE";

	@Mock
	public UserResourceRepository userResourceRepository;

	@InjectMocks
	public UserResourceManager userResourceManager;

	@Test
	@DisplayName("Method:getAllUsers , TestCase:TestForExceptionThrownFromRepository")
	public void getAllUsers_testForExceptionThrownFromRepository() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willThrow(new RuntimeException(DATASOURCE_NOT_AVAILABLE));
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () ->
		// when
		userResourceManager.getAllUsers(), "Users cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertAll(() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getMessage()),
				() -> assertTrue(actualException.getCause().getClass() == RuntimeException.class),
				() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getCause().getMessage()));

		then(userResourceRepository).should().getAllUsers();
	}

	@Test
	@DisplayName("Method:getAllUsers , TestCase:TestForNullUsers")
	public void getAllUsers_testForNullUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(null);
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getAllUsers();
		}, "Users cannot be null.");

		assertEquals(UserResourceManager.NO_USERS_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getAllUsers , TestCase:TestForEmptyListUsers")
	public void getAllUsers_testForEmptyListUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(new LinkedHashMap<>());
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getAllUsers();
		}, "Users cannot be null.");

		assertEquals(UserResourceManager.NO_USERS_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getAllUsers , TestCase:TestForAtLeastOneUser")
	public void getAllUsers_testForAtLeastOneUser() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(UserSampleDataProvider.sampleUsersMap());
		// whens
		UserList userList = userResourceManager.getAllUsers();

		// then
		assertAll(() -> {
			assertDoesNotThrow(() -> userResourceManager.getAllUsers());
		}, () -> {
			assertNotNull(userList, "UserList Object cannot be null.");
		}, () -> {
			assertTrue((Objects.nonNull(userList.getUsers()) && !userList.getUsers().isEmpty()),
					"Users in UserList Object cannot be null.");
		}, () -> {
			assertArrayEquals(UserSampleDataProvider.sampleUsersMap().values().stream().toArray(User[]::new),
					userList.getUsers().stream().toArray(User[]::new), () -> "Both Users List must be same .");
		});

		// verify getAllUsers() was called once
		then(userResourceRepository).should(times(2)).getAllUsers();

	}

	@Test
	@DisplayName("Method:getUser , TestCase:TestForExceptionThrownFromRepository")
	public void getUser_testForExceptionThrownFromRepository() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willThrow(new RuntimeException(DATASOURCE_NOT_AVAILABLE));
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class,
				() -> userResourceManager.getUser(1), "Users list cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		// then
		assertAll(() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getMessage()),
				() -> assertTrue(actualException.getCause().getClass() == RuntimeException.class),
				() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getCause().getMessage()));

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getUser , TestCase:TestForNullUsers")
	public void getUser_testForNullUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(null);
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getUser(1);
		}, "Users list cannot be null.");

		assertEquals(UserResourceManager.NO_USER_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getUser , TestCase:TestForEmptyListUsers")
	public void getUser_testForEmptyListUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(new LinkedHashMap<>());
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getUser(1);
		}, "Users list cannot be null.");

		assertEquals(UserResourceManager.NO_USER_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getUser , TestCase:TestForAtLeastOneUser")
	public void getUser_testForAtLeastOneUser() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(UserSampleDataProvider.sampleUsersMap());
		// whens
		User user = userResourceManager.getUser(1);

		// then
		assertAll(() -> {
			assertDoesNotThrow(() -> userResourceManager.getUser(1));
		}, () -> {
			assertNotNull(user, "UserList Object cannot be null.");
		}, () -> {
			assertEquals(User.of(1, "Vaneet"), user);
		});

		// verify getAllUsers() was called once
		then(userResourceRepository).should(times(2)).getAllUsers();

	}

	@Test
	@DisplayName("Method:addUser , TestCase:UnSuccessfullyAddedCase")
	public void addUser_unSuccessfullyAddedCase() throws UserResourceException {
		// given
		given(userResourceRepository.addUser(any(User.class)))
				.willThrow(new RuntimeException(DATASOURCE_NOT_AVAILABLE));

		UserResourceException actualException = assertThrows(UserResourceException.class,
				() -> userResourceManager.addUser(User.of(0, "Mansi")));

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		// then
		assertAll(() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getMessage()),
				() -> assertTrue(actualException.getCause().getClass() == RuntimeException.class),
				() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getCause().getMessage()));

		then(userResourceRepository).should().addUser(any(User.class));

	}

	@Test
	@DisplayName("Method:addUser , TestCase:SuccessfullyAddedCase")
	public void addUser_successfullyAddedCase() throws UserResourceException {
		// given
		given(userResourceRepository.addUser(User.of(0, "Mansi"))).willReturn(User.of(1, "Mansi"));

		// when
		User actualUserReturned = userResourceManager.addUser(User.of(0, "Mansi"));

		// then
		assertEquals(User.of(1, "Mansi"), actualUserReturned);
		assertDoesNotThrow(() -> userResourceManager.addUser(User.of(0, "Mansi")));

		then(userResourceRepository).should(times(2)).addUser(User.of(0, "Mansi"));

	}

	@Test
	@DisplayName("Method:deleteUser , TestCase:ExceptionThrownFromRepositoryCase")
	public void deleteUser_exceptionThrownFromRepositoryCase() {

		given(userResourceRepository.removeUser(anyInt())).willThrow(new RuntimeException(DATASOURCE_NOT_AVAILABLE));

		UserResourceException actualException = assertThrows(UserResourceException.class,
				() -> userResourceManager.deleteUser(1));

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertAll(() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getMessage()),
				() -> assertTrue(actualException.getCause().getClass() == RuntimeException.class),
				() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getCause().getMessage()));

		then(userResourceRepository).should().removeUser(anyInt());

	}

	@Test
	@DisplayName("Method:deleteUser , TestCase:DeleteUnsuccessfulCase")
	public void deleteUser_deleteUnsuccessfulCase() {

		given(userResourceRepository.removeUser(anyInt())).willReturn(null);

		UserResourceException actualException = assertThrows(UserResourceException.class,
				() -> userResourceManager.deleteUser(1));

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertEquals(UserResourceManager.NO_USER_EXIST, actualException.getMessage());

		then(userResourceRepository).should().removeUser(anyInt());

	}

	@Test
	@DisplayName("Method:deleteUser , TestCase:SuccessfullyDeletedCase")
	public void deleteUser_successfullyDeletedCase() throws UserResourceException {
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);

		// given
		given(userResourceRepository.removeUser(argumentCaptor.capture())).willReturn(User.of(1, "Vaneet"));

		// when
		userResourceManager.deleteUser(1);

		// then
		assertDoesNotThrow(() -> userResourceManager.deleteUser(1));
		assertEquals(1, argumentCaptor.getValue());
		then(userResourceRepository).should(times(2)).removeUser(1);

	}

	@Test
	@DisplayName("Method:getAllCorresspondingAddress , TestCase:TestForExceptionThrownFromRepository")
	public void getAllCorresspondingAddress_testForExceptionThrownFromRepository() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willThrow(new RuntimeException(DATASOURCE_NOT_AVAILABLE));
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () ->
		// when
		userResourceManager.getAllCorresspondingAddress(1), "Users cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertAll(() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getMessage()),
				() -> assertTrue(actualException.getCause().getClass() == RuntimeException.class),
				() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getCause().getMessage()));

		then(userResourceRepository).should().getAllUsers();
	}

	@Test
	@DisplayName("Method:getAllCorresspondingAddress , TestCase:TestForNullUsers")
	public void getAllCorresspondingAddress_testForNullUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(null);
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getAllCorresspondingAddress(1);
		}, "Users cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertEquals(UserResourceManager.NO_USER_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getAllCorresspondingAddress , TestCase:TestForEmptyListUsers")
	public void getAllCorresspondingAddress_testForEmptyListUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(new LinkedHashMap<>());
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getAllCorresspondingAddress(1);
		}, "Users cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertEquals(UserResourceManager.NO_USER_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getAllCorresspondingAddress , TestCase:SucessCase")
	public void getAllCorresspondingAddress_sucessCase() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(UserSampleDataProvider.sampleUsersMap());

		// when
		List<User.Address> actualusersAddresses = userResourceManager.getAllCorresspondingAddress(1);

		// then
		assertDoesNotThrow(() -> userResourceManager.getAllCorresspondingAddress(1),
				"No exception is thrown is data if non null and non empty data is found. ");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		// assert null as have not been set before.
		assertAll(() -> assertNotNull(actualusersAddresses),
				() -> assertArrayEquals(new Address[] { Address.of("k612"), Address.of("ucp39") },
						actualusersAddresses.stream().toArray(Address[]::new)));

		then(userResourceRepository).should(times(2)).getAllUsers();

	}

	@Test
	@DisplayName("Method:getAllJuniors , TestCase:TestForExceptionThrownFromRepository")
	public void getAllJuniors_testForExceptionThrownFromRepository() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willThrow(new RuntimeException(DATASOURCE_NOT_AVAILABLE));
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class,
				() -> userResourceManager.getAllCorresspondingAddress(1), "Users cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertAll(() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getMessage()),
				() -> assertTrue(actualException.getCause().getClass() == RuntimeException.class),
				() -> assertEquals(DATASOURCE_NOT_AVAILABLE, actualException.getCause().getMessage()));

		then(userResourceRepository).should().getAllUsers();
	}

	@Test
	@DisplayName("Method:getAllJuniors , TestCase:TestForNullUsers")
	public void getAllJuniors_testForNullUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(null);
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getAllCorresspondingAddress(1);
		}, "Users cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertEquals(UserResourceManager.NO_USER_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getAllJuniors , TestCase:TestForEmptyListUsers")
	public void getAllJuniors_testForEmptyListUsers() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(new LinkedHashMap<>());
		// when
		UserResourceException actualException = assertThrows(UserResourceException.class, () -> {
			// when
			userResourceManager.getAllCorresspondingAddress(1);
		}, "Users cannot be null.");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		assertEquals(UserResourceManager.NO_USER_EXIST, actualException.getMessage());

		then(userResourceRepository).should().getAllUsers();

	}

	@Test
	@DisplayName("Method:getAllJuniors , TestCase:SuucessCase()")
	public void getAllJuniors_suucessCase() throws UserResourceException {
		// given
		given(userResourceRepository.getAllUsers()).willReturn(UserSampleDataProvider.sampleUsersMap());

		// when
		List<String> juniors = userResourceManager.getAllJuniors(1);

		// then
		assertDoesNotThrow(() -> userResourceManager.getAllJuniors(1),
				"No exception is thrown is data if non null and non empty data is found. ");

		then(userResourceRepository).shouldHaveNoMoreInteractions();

		// assert null as have not been set before.
		assertAll(() -> assertNotNull(juniors), () -> assertArrayEquals(new String[] { "Deepak", "Dheeraj", "Franka" },
				juniors.stream().toArray(String[]::new)));

		then(userResourceRepository).should(times(2)).getAllUsers();

	}

}
