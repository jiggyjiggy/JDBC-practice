package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public void create(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.executeUpdate(user, sql, new PreparedStatementSetter() {
			@Override
			public void setter(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		});
	}
	
	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
		
		return jdbcTemplate.executeUpdate(userId, sql, new PreparedStatementSetter() {
			@Override
			public void setter(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		}, new RowMapper() {
			@Override
			public Object map(ResultSet resultSet) throws SQLException {
				return new User(
					resultSet.getString("userId"),
					resultSet.getString("password"),
					resultSet.getString("name"),
					resultSet.getString("email")
				);
			}
		});
	}
}