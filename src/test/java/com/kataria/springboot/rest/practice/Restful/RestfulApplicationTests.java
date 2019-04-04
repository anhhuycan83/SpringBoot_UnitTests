package com.kataria.springboot.rest.practice.Restful;

import org.junit.jupiter.api.Test;

import com.kataria.springboot.rest.practice.core.tags.interfaces.CIServerTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DaoTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.DevEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.ProdEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.QAEnvironementTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.RFSEnvironmentTest;
import com.kataria.springboot.rest.practice.core.tags.interfaces.SlowTest;

public class RestfulApplicationTests implements DaoTest, DevEnvironementTest, QAEnvironementTest, RFSEnvironmentTest,
		ProdEnvironementTest, CIServerTest, SlowTest {

	@Test
	public void contextLoads() {
	}

}
