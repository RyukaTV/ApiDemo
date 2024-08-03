package com.ryuk.ApiDemo.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryuk.ApiDemo.model.User;
import com.ryuk.ApiDemo.tools.DatabaseConfig;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {

	@Autowired
	private UserController userController = new UserController();
	private User user1 = new User(1, "username1", "user1@usermail.com"),
			user2 = new User(2, "username2", "user2@usermail.com"),
			user3 = new User(3, "username3", "user3@usermail.com");

	@BeforeAll
	public static void setUpClass() {
		System.out.println("*** Start of controller testing ***");

	}

	@Test
	@Order(1)
	public void testcreateUser() {
		userController.createUser(user1);
		userController.createUser(user2);
		userController.createUser(user3);
	}

	@Test
	@Order(2)
	public void testgetUserById() {
		assertTrue(areUsersEqual(user1, userController.getUserById(1)));
		assertTrue(areUsersEqual(user2, userController.getUserById(2)));
		assertFalse(areUsersEqual(user3, userController.getUserById(4)));
	}

	@Test
	@Order(3)
	public void testgetAllUsers() {
		List<User> expectedUsers = new ArrayList<User>();
		expectedUsers.add(user1);
		expectedUsers.add(user2);
		expectedUsers.add(user3);
		assertTrue(areUserListsEqual(expectedUsers, userController.getAllUsers()));
		expectedUsers.add(new User());
		assertFalse(areUserListsEqual(expectedUsers, userController.getAllUsers()));
	}

	@Test
	@Order(4)
	public void testdeleteUser() {
		userController.deleteUser(1);
		userController.deleteUser(2);
		userController.deleteUser(3);
	}

	@AfterAll
	public static void End() {
		try {
			System.out.println("*** End of controller testing ***");
			DatabaseConfig.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean areUsersEqual(User u1, User u2) {
		if (u1 == u2)
			return true;
		if (u1 == null || u2 == null)
			return false;
		return u1.getId() == u2.getId() && Objects.equals(u1.getName(), u2.getName())
				&& Objects.equals(u1.getEmail(), u2.getEmail());
	}

	private boolean areUserListsEqual(List<User> list1, List<User> list2) {
		if (list1.size() != list2.size())
			return false;
		for (int i = 0; i < list1.size(); i++) {
			if (!areUsersEqual(list1.get(i), list2.get(i))) {
				return false;
			}
		}
		return true;
	}

}
