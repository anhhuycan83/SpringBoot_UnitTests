package com.kataria.springboot.rest.practice.dao.user;

import java.util.Map;
import java.util.function.Supplier;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.kataria.springboot.rest.practice.core.beans.User;
import com.kataria.springboot.rest.practice.core.support.test.TestLifeCycleMetaDataSupport;
import com.kataria.springboot.rest.practice.core.tags.interfaces.CIServerTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DaoTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DevEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.ProdEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.QAEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.RFSEnvironmentTest;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractInMemoryUserResourceRepositoryTest implements DaoTest, DevEnvironementTest,
		QAEnvironementTest, RFSEnvironmentTest, ProdEnvironementTest, CIServerTest, TestLifeCycleMetaDataSupport {

	protected InMemoryUserResourceRepository inMemoryUserResourceRepository = new InMemoryUserResourceRepository();

	protected void initialiseData(Supplier<Map<Integer, User>> usersMapSupplier) {
		inMemoryUserResourceRepository.initData(usersMapSupplier.get());
	}

	public abstract void getAllUsers();

	public abstract void addUser();

	public abstract void removeUser();

}
