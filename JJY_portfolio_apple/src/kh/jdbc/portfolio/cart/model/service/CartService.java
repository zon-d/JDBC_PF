package kh.jdbc.portfolio.cart.model.service;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import kh.jdbc.portfolio.cart.model.dao.CartDAO;
import kh.jdbc.portfolio.cart.model.vo.Cart;

public class CartService {

	private CartDAO cdao = new CartDAO();

	/**
	 * 장바구니 조회 서비스
	 * @return
	 */
	public List<Cart> cartList() throws Exception{
		Connection conn = getConnection();

		List<Cart> cartList = cdao.cartList(conn);

		close(conn);

		return cartList;
	}

	/**
	 * 장바구니 상품 총액
	 * @return
	 */
	public int cartInSum() throws Exception{
		Connection conn = getConnection();
		
		int cartInSum = cdao.cartInSum(conn);
		
		close(conn);
		
		return cartInSum;
	}

}
