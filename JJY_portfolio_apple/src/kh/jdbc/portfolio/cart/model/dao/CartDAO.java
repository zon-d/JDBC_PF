package kh.jdbc.portfolio.cart.model.dao;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

			if (rs.next()) {

				int cartInNo = rs.getInt("CARTIN_NO");
				String productModel = rs.getString("PRODUCT_MODEL");
				String productMemory = rs.getString("PRODUCT_MEMORY");
				String productCorlor = rs.getString("PRODUCT_CORLOR");
				int productPrice = rs.getInt("PRODUCT_PRICE");

				Cart cart = new Cart();

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

	public int deleteCart(Connection conn, int cartInNo) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("deleteCart");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cartInNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	public int cart(Connection conn) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("cart");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());

			rs = pstmt.executeQuery();

		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int deleteAllCart(Connection conn) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("deleteAllCart");

			pstmt = conn.prepareStatement(sql);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	public Cart myCart(Connection conn) throws Exception {

		Cart cart = null;

		try {

			String sql = prop.getProperty("cart");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserView.insertUser.getUserNo());

			rs = pstmt.executeQuery();

		} finally {
			close(rs);
			close(pstmt);
		}
		return cart;
	}

}
