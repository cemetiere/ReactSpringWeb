package com.cemetiere.weblab;

import com.cemetiere.weblab.auth.User;
import com.cemetiere.weblab.auth.UserRepository;
import com.cemetiere.weblab.point.Attempt;
import com.cemetiere.weblab.point.PointAttemptRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WebLab4ApplicationTests {
	@Autowired
	UserRepository repository;
	@Autowired
	PointAttemptRepository pointAttemptRepository;

	@BeforeAll
	public void addUser() throws Exception {
		User user = new User();
		user.setUsername("testNewUser");
		user.setPassword("testPassword");
		repository.save(user);

		Attempt attempt = new Attempt();
		attempt.setX(1);
		attempt.setY(2);
		attempt.setR(3);
		attempt.setUser(user);
		attempt.setAttemptTime(System.currentTimeMillis());
		attempt.setSuccess(true);
		attempt.setProcessTime(0);
		if (repository.attemptsCountByUsername("testNewUser") == 0){
			pointAttemptRepository.save(attempt);
		}
	}
	@Test
	public void checkUser() throws Exception {
		assertThat(repository.findByUsername("testNewUser").getPassword()).isEqualTo("testPassword");
	}
	@Test
	public void checkNoUserFound() throws Exception{
		assertThat(repository.findByUsername("testNoUser")).isNull();
	}

	@Test
	public void checkPoint() throws Exception{
		User user = new User();
		user.setUsername("testNewUser");
		user.setPassword("testPassword");
		assertThat(pointAttemptRepository.findAllByUser(user)).isNotNull();
	}

	@Test
	public void checkAttemptCount() throws Exception{
		assertThat(repository.attemptsCountByUsername("testNewUser")).isEqualTo(1);
	}
}
