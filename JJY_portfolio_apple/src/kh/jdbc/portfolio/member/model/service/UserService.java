package kh.jdbc.portfolio.member.model.service;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.sql.Connection;

import kh.jdbc.portfolio.member.model.dao.UserDAO;
import kh.jdbc.portfolio.member.vo.User;

public class UserService {

	private UserDAO udao = new UserDAO();

	/**
	 * 사용자 이름, 번호 중복 체크
	 * 
	 * @param memberName
	 * @param memberPhone
	 * @return
	 * @throws Exception
	 */
	public int userDupCheck(String userName, String userPhone) throws Exception {

		Connection conn = getConnection();

		int result = udao.userDupCheck(conn, userName, userPhone);

		close(conn);

		return result;
	}

	public int signUp(User user) throws Exception {
		Connection conn = getConnection();

		int result = udao.signUp(conn, user);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

	public User login(String userName, String userPhone) throws Exception {
		Connection conn = getConnection();

		User insertUser = udao.login(conn, userName, userPhone);

		close(conn);

		return insertUser;
	}

}
