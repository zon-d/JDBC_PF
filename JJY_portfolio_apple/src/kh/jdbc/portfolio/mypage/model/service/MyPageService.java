package kh.jdbc.portfolio.mypage.model.service;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.sql.Connection;

import kh.jdbc.portfolio.member.vo.User;
import kh.jdbc.portfolio.mypage.model.dao.MyPageDAO;

public class MyPageService {

	MyPageDAO mpdao = new MyPageDAO();

	public int updateUser(User user) throws Exception{
		Connection conn = getConnection();

		int result = mpdao.updateUser(conn, user);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

	/**
	 * 사용자 정보 삭제
	 * @param userName
	 * @param userPhone
	 * @param userNo
	 * @return
	 * @throws Exception
	 */
	public int deleteUser(String userName, String userPhone, int userNo) throws Exception {
		Connection conn = getConnection();
		
		int result = mpdao.deleteUser(conn, userName, userPhone, userNo);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}

}
