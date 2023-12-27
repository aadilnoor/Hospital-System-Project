package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDao {

	private Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean register(User u) {
		boolean f = false;

		try {
			String query = "insert into user_details(fullname,email,password) value(?,?,?)";
      
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, u.getFullname());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

		}
		return f;

	}

	public User login(String email, String password) {
		User u = null;

		try {
			String query = "select * from user_details where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setFullname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));

			}

		} catch (Exception e) {
          e.printStackTrace();
		}

		return u;

	}

	public boolean checkOldPassword(int userid, String oldPassword) {
		boolean f = false;
		try {
			String query = "select * from user_details where id = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setString(2, oldPassword);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public boolean changePassword(int userid, String newPassword) {
		boolean f = false;
		try {
			String query = "update user_details set password = '"+newPassword+"' WHERE id = "+userid+"";
			PreparedStatement ps = conn.prepareStatement(query);
			
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

}
