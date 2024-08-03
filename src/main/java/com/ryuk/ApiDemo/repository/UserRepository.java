package com.ryuk.ApiDemo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuk.ApiDemo.model.User;
import com.ryuk.ApiDemo.tools.DatabaseConfig;

@Repository
public class UserRepository {

	private Connection conn = DatabaseConfig.getConnection();

	public void save(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (name, email) VALUES (?, ?)");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public User findById(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public User findByUsername(String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE name = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAlluser() {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
			ResultSet rs = stmt.executeQuery();
			List<User> ls = new ArrayList<User>();
			while (rs.next()) {
				ls.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
			}
			return ls;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void deleteById(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE id = ?");
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
