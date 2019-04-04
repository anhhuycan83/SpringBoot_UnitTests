package com.kataria.springboot.rest.practice.dao.user;

import org.junit.jupiter.api.function.Executable;

import com.kataria.springboot.rest.practice.core.tags.interfaces.CIServerTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DaoTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DevEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.ProdEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.QAEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.RFSEnvironmentTest;

public abstract class AbstractInMemoryUserResourceRepositoryTest implements DaoTest, DevEnvironementTest,
		QAEnvironementTest, RFSEnvironmentTest, ProdEnvironementTest, CIServerTest {

	protected InMemoryUserResourceRepository inMemoryUserResourceRepository = new InMemoryUserResourceRepository();

	protected void initialiseData(Executable executable) throws Throwable {
		executable.execute();
	}

	public abstract void getAllUsers();

	public abstract void addUser();

	public abstract void removeUser();

}
