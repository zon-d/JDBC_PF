package kh.jdbc.portfolio.cart.model.dao;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kh.jdbc.portfolio.cart.model.vo.Cart;

public class CartDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

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

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

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

				try {

					String price = prop.getProperty("allCartInPrice");

					stmt = conn.createStatement();

					rs = stmt.executeQuery(price);

				} finally {
					close(rs);
					close(stmt);
				}

			}

		} finally {
			close(rs);
			close(stmt);

		}

		return cartList;
	}

	/**
	 * 장바구니 상품 합계 DAO
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int cartInSum(Connection conn) throws Exception {

		int cartInSum = 0;

		try {
			String sql = prop.getProperty("cartInSum");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

		} finally {
			close(rs);
			close(stmt);

		}

		return cartInSum;
	}

	/**
	 * 장바구니 목록 조회 서비스
	 * 
	 * @return cartList
	 * @throws Exception
	 */
//	public List<Cart> viewCartList() throws Exception{
//
//
//		List<Cart> cartList = new ArrayList<>();
//		
//		try {
//			String sql = prop.getProperty("cartList");
//		}
//		
//		return null;
//	}
//	

}
