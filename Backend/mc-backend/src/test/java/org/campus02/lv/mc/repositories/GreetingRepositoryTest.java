package org.campus02.lv.mc.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.campus02.lv.mc.entities.Greeting;
import org.campus02.lv.mc.testing.TestingConfigurationClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestingConfigurationClass.class }, webEnvironment = WebEnvironment.MOCK)
@WebAppConfiguration
public class GreetingRepositoryTest {

	@Autowired
	private GreetingRepository greetingRepo;

	@Test
	public void simple_database_test() {
		List<Greeting> allGreetings = greetingRepo.findAll();
		assertEquals(0, allGreetings.size());
	}
}
