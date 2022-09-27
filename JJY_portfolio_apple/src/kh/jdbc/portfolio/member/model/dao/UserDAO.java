package kh.jdbc.portfolio.member.model.dao;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import kh.jdbc.portfolio.member.vo.User;

public class UserDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Properties prop = null;

	public UserDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("user-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 사용자 중복 확인
	 * 
	 * @param conn
	 * @param userName
	 * @param userPhone
	 * @return
	 * @throws Exception
	 */
	public int userDupCheck(Connection conn, String userName, String userPhone) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("userDupCheck");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userName);
			pstmt.setString(2, userPhone);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				result = rs.getInt(1);
			}

		} finally {

			close(rs);
			close(pstmt);
		}

		return result;
	}

	/**
	 * 사용자 등록
	 * 
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int signUp(Connection conn, User user) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("insertUser");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPhone());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}

	public User login(Connection conn, String userName, String userPhone) throws Exception {
		User insertUser = null;

		try {

			String sql = prop.getProperty("login");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userName);
			pstmt.setString(2, userPhone);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				insertUser = new User(rs.getInt("USER_NO"), userName, userPhone, rs.getInt("CART"), rs.getInt("ORDER"));

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			close(rs);
			close(pstmt);

		}

		return insertUser;
	}

}
