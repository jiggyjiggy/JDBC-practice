package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
	
	@BeforeEach
	void setUp() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("db_schema.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}
	
	@Test
	void createTest() throws SQLException {
		UserDao userDao = new UserDao();
		User actualUser = new User("jiggyjiggy", "password", "기성", "jiggyjiggy0323@gmail.com");
		userDao.create(actualUser);
		
		User expectedUser = userDao.findByUserId("jiggyjiggy");
		
		// 객체와 객체를 비교하기 때문에 equals() hashcode() override 필요
		assertThat(actualUser).isEqualTo(expectedUser);
	}
	
	@Test
	void createTest2() throws SQLException {
		UserDao userDao = new UserDao();
		User actualUser = new User("jiggyjiggy", "password", "기성", "jiggyjiggy0323@gmail.com");
		userDao.create(actualUser);
		
		User expectedUser = userDao.findByUserId("failUserId");
		
		assertThat(actualUser).isNotEqualTo(expectedUser);
	}
}
