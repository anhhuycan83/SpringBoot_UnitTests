package com.kataria.springboot.rest.practice.core.tags;

public interface TagsConstants {

	// Tags based on layer type .
	String DAO = "dao";
	String MANAGER = "manager";
	String SERVICE = "service";
	String CONTROLLER = "controller";

	// Tags based on environment types
	String DEV = "dev";
	String PROD = "prod";
	String QA = "qa";
	String RFS = "rfs";

	String CI_SERVER = "ciserver";
	String SLOW = "slow";

}
