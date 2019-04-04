package com.kataria.springboot.rest.practice.dao.user;

import org.junit.jupiter.api.function.Executable;

public abstract class AbstractUserResourceRepositoryImplTest {

	protected UserResourceRepositoryImpl userResourceRepositoryImpl = new UserResourceRepositoryImpl();

	protected void initialiseData(Executable executable) throws Throwable {
		executable.execute();
	}

	public abstract void getAllUsers();

	public abstract void addUser();

	public abstract void removeUser();

}
