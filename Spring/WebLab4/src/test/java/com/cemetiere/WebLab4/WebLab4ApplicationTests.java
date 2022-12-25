package com.cemetiere.WebLab4;

import com.cemetiere.weblab.auth.User;
import com.cemetiere.weblab.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WebLab4ApplicationTests {
	@Autowired
	UserRepository repository;

	@BeforeAll
	public void addUser() throws Exception {
		User user = new User();
		user.setUsername("testUser");
		user.setPassword("testPassword");
		repository.save(user);
	}
	@Test
	public void checkUser() throws Exception {
		assertThat(repository.findByUsername("testUser").getPassword()).isEqualTo("testPassword");
	}
}
