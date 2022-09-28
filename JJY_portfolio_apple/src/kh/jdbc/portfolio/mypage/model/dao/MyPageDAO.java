package kh.jdbc.portfolio.mypage.model.dao;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import kh.jdbc.portfolio.member.view.UserView;
import kh.jdbc.portfolio.member.vo.User;

public class MyPageDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	public MyPageDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("user-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 사용자 정보 수정
	 * 
	 * @param conn
	 * @param user
	 * @return
	 */
	public int updateUser(Connection conn, User user) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("updateUser");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPhone());
			pstmt.setInt(3, user.getUserNo());

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);
		}

		return result;
	}

	public int deleteUser(Connection conn, String userName, String userPhone, int userNo) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("deleteUser");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());
			pstmt.setString(2, userName);
			pstmt.setString(3, userPhone);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

}
