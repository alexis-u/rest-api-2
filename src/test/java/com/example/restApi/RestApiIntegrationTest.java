package com.example.restApi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestApiIntegrationTest {

	@Test
	void applicationTest() {
		RestApiApplication classUnderTest = new RestApiApplication();
		RestApiApplication.main(new String[] {}); // a simplest integration test usually for dev env
		assertNotNull(classUnderTest);
	}
}
