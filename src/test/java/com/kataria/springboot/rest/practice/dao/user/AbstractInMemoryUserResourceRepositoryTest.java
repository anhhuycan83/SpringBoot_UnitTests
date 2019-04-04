package com.kataria.springboot.rest.practice.dao.user;

import java.util.Map;
import java.util.function.Supplier;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.kataria.springboot.rest.practice.core.beans.User;
import com.kataria.springboot.rest.practice.core.tags.interfaces.CIServerTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DaoTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DevEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.ProdEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.QAEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.RFSEnvironmentTest;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractInMemoryUserResourceRepositoryTest implements DaoTest, DevEnvironementTest,
		QAEnvironementTest, RFSEnvironmentTest, ProdEnvironementTest, CIServerTest {

	@BeforeAll
	public void processBeforeAll(TestInfo testInfo) {
		System.out.println("Started execution of Test Class :" + testInfo.getTestClass().get()
				+ "With Associated Tags :" + testInfo.getTags());
	}

	@BeforeEach
	public void processBeforeEach(TestInfo testInfo) {
		System.out.println("Started execution of Test Method  :" + testInfo.getTestMethod().get()
				+ "With Display Name :" + testInfo.getDisplayName());
	}

	@AfterEach
	public void processAfterEach(TestInfo testInfo) {
		System.out.println("Ended execution of Test Method  :" + testInfo.getTestMethod().get() + "With Display Name :"
				+ testInfo.getDisplayName());
	}

	@AfterAll
	public void processAfterAll(TestInfo testInfo) {
		System.out.println("Ended execution of Test Class :" + testInfo.getTestClass().get() + "With Associated Tags :"
				+ testInfo.getTags());
	}

	protected InMemoryUserResourceRepository inMemoryUserResourceRepository = new InMemoryUserResourceRepository();

	protected void initialiseData(Supplier<Map<Integer, User>> usersMapSupplier) {
		inMemoryUserResourceRepository.initData(usersMapSupplier.get());
	}

	public abstract void getAllUsers();

	public abstract void addUser();

	public abstract void removeUser();

}
