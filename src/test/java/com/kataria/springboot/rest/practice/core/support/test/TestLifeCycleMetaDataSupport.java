package com.kataria.springboot.rest.practice.core.support.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public interface TestLifeCycleMetaDataSupport {

	@BeforeAll
	default void processBeforeAll(TestInfo testInfo) {
		System.out.println("\n Started execution of Test Class :" + testInfo.getTestClass().get()
				+ " With Associated Tags :" + testInfo.getTags());
	}

	@BeforeEach
	default void processBeforeEach(TestInfo testInfo) {
		System.out.println("\n  Started execution of Test Method  :" + testInfo.getTestMethod().get()
				+ " With Display Name :" + testInfo.getDisplayName());
	}

	@AfterEach
	default void processAfterEach(TestInfo testInfo) {
		System.out.println("\n Ended execution of Test Method  :" + testInfo.getTestMethod().get()
				+ " With Display Name :" + testInfo.getDisplayName());
	}

	@AfterAll
	default void processAfterAll(TestInfo testInfo) {
		System.out.println("\n Ended execution of Test Class :" + testInfo.getTestClass().get()
				+ " With Associated Tags :" + testInfo.getTags());
	}

}
