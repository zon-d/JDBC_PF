package kh.jdbc.portfolio.cart.model.dao;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.member.view.UserView;
import kh.jdbc.portfolio.member.vo.User;

public class CartDAO {

//	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	Cart cart = new Cart();
	User user = new User();

	private Properties prop = null;

	public CartDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("cart-query.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 장바구니 목록 조회 DAO
	 * 
	 * @param conn
	 * @return cartList
	 * @throws Exception
	 */
	public List<Cart> cartList(Connection conn) throws Exception {

		List<Cart> cartList = new ArrayList<>();

		try {
			String sql = prop.getProperty("viewCartList");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int userNo = rs.getInt("USER_NO");
				int cartInNo = rs.getInt("CARTIN_NO");
				String productModel = rs.getString("PRODUCT_MODEL");
				String productMemory = rs.getString("PRODUCT_MEMORY");
				String productCorlor = rs.getString("PRODUCT_CORLOR");
				int productPrice = rs.getInt("PRODUCT_PRICE");

				Cart cart = new Cart();

				cart.setUserNo(userNo);
				cart.setCartInNo(cartInNo);
				cart.setProductModel(productModel);
				cart.setProductMemory(productMemory);
				cart.setProductCorlor(productCorlor);
				cart.setProductPrice(productPrice);

				cartList.add(cart);
			}

		} finally {
			close(rs);
			close(pstmt);

		}

		return cartList;
	}

	/**
	 * 장바구니 상품 금액 합계
	 * 
	 * @param conn
	 * @param cartList
	 * @return priceSum
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

//	/**
//	 * 장바구니 상품 삭제 DAO
//	 * 
//	 * @param conn
//	 * @param cart
//	 * @return
//	 * @throws Exception
//	 */
//	public int deleteCart(Connection conn, Cart cart) throws Exception {
//
//		int cartInNo = 0;
//
//		try {
//			String sql = prop.getProperty("deleteCart");
//
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setInt(1, cartInNo);
//
//			cartInNo = pstmt.executeUpdate();
//
//		} finally {
//			close(rs);
//			close(pstmt);
//
//		}
//		return cartInNo;
//	}

//	public int deleteCart(Connection conn) throws Exception {
//
//		int result = 0;
//
//		try {
//			String sql = prop.getProperty("deleteCart");
//
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setInt(1, cart.getCartInNo());
//
//			rs = pstmt.executeUpdate();
//
//		} finally {
//			close(pstmt);
//			close(rs);
//		}
//
//		return result;
//
//	}

	/**
	 * 장바구니 선택 삭제
	 * 
	 * @param conn
	 * @param cartInNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCart(Connection conn, int cartInNo) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("deleteCart");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cartInNo);
			pstmt.setInt(2, UserView.insertUser.getUserNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	/**
	 * 장바구니에 담긴 상품 갯수
	 * 
	 * @param conn
	 * @param userNo 
	 * @return
	 * @throws Exception
	 */
	public int cart(Connection conn, int userNo) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("cart");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());

			rs = pstmt.executeQuery();

		} finally {

			close(pstmt);
		}
		return result;
	}

	/**
	 * 장바구니 전체 삭제
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int deleteAllCart(Connection conn) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("deleteAllCart");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	public int order(Connection conn, int cartInNo) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("order");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());
			pstmt.setString(2, UserView.insertUser.getUserName());
			pstmt.setString(3, UserView.insertUser.getUserPhone());
			pstmt.setString(4, cart.getProductModel());
			pstmt.setString(5, cart.getProductMemory());
			pstmt.setString(6, cart.getProductCorlor());
			pstmt.setInt(7, cart.getProductPrice());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

}
