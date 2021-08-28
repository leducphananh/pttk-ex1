package vn.ptit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.model.User;

public class UserDAO extends DAO {
	public UserDAO() {
		super();
	}

	public List<User> getAllUser() {
		User user = null;
		String sql = "SELECT * FROM tbl_user";
		List<User> listUser = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("country"),
						rs.getString("gender"));
				listUser.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUser;
	}

	public User selectUser(int id) {
		User user = null;
		String sql = "SELECT * FROM tbl_user WHERE id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("country"),
						rs.getString("gender"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void insertUser(User user) throws SQLException {
		String sql = "INSERT INTO tbl_user (name, email, country, gender) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.setString(4, user.getGender());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated = false;
		String sql = "UPDATE tbl_user SET name = ?, email = ?, country = ?, gender = ? WHERE id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.setString(4, user.getGender());
			ps.setInt(5, user.getId());
			rowUpdated = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	public boolean deleteUser(int id) throws SQLException {
		String sql = "DELETE FROM tbl_user WHERE id = ?";
		boolean rowDeleted = false;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

}
