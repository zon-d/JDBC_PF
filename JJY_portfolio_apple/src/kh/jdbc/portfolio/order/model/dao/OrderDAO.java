package kh.jdbc.portfolio.order.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.member.view.UserView;
import kh.jdbc.portfolio.member.vo.User;
import kh.jdbc.portfolio.order.model.vo.Order;

public class OrderDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Properties prop = null;

	public OrderDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("order-query.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 주문 리스트 생성
	 * 
	 * @param conn
	 * @param userNo
	 * @return
	 * @throws Exception
	 */
	public List<Order> orderList(Connection conn, int userNo) throws Exception {
		List<Order> orderList = new ArrayList<>();

		try {
			String sql = prop.getProperty("orderList");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());

			rs = pstmt.executeQuery();

			while (rs.next()) {

//				int userNo = rs.getInt("USER_NO");
//				int cartInNo = rs.getInt("CARTIN_NO");
				String userName = rs.getString("USER_NM");
				String userPhone = rs.getString("USER_PH");
				int orderNo = rs.getInt("ORDER_NO");
				String productModel = rs.getString("PRODUCT_MODEL");
				String orderDate = rs.getString("ORDER_DT");
				String productMemory = rs.getString("PRODUCT_MEMORY");
				String productCorlor = rs.getString("PRODUCT_CORLOR");
				int productPrice = rs.getInt("PRODUCT_PRICE");

				Order order = new Order();

				order.setUserNo(userNo);

				order.setUserNo(userNo);
				order.setUserPhone(userPhone);
				order.setOrderNo(orderNo);
				order.setProductModel(productModel);
				order.setOrderDate(orderDate);
				order.setProductMemory(productMemory);
				order.setProductCorlor(productCorlor);
				order.setProductPrice(productPrice);

				orderList.add(order);
			}

		} finally {
			close(rs);
			close(pstmt);

		}

		return orderList;
	}

	/**
	 * 주문금액 합계
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int priceSum(Connection conn) throws Exception {
		int priceSum = 0;

		try {
			String sql = prop.getProperty("priceSum");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				priceSum = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);

		}

		return priceSum;
	}

	/**
	 * 주문 내역 삭제
	 * @param conn
	 * @param orderNo
	 * @return
	 * @throws Exception 
	 */
	public int updateComment(Connection conn, int orderNo) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("deleteOrder");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

}
