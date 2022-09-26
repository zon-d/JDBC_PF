package kh.jdbc.portfolio.cart.model.service;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import kh.jdbc.portfolio.cart.model.dao.CartDAO;
import kh.jdbc.portfolio.cart.model.vo.Cart;
import oracle.net.aso.c;

public class CartService {

	private CartDAO cdao = new CartDAO();

	/**
	 * 장바구니 조회 서비스
	 * 
	 * @return
	 */
	public List<Cart> cartList() throws Exception {
		Connection conn = getConnection();

		List<Cart> cartList = cdao.cartList(conn);

		close(conn);

		return cartList;

	}

	/**
	 * 장바구니 금액 합계 서비스
	 * 
	 * @return priceSum
	 * @throws Exception
	 */
	public int priceSum() throws Exception {
		Connection conn = getConnection();

		int priceSum = cdao.priceSum(conn);

		close(conn);

		return priceSum;
	}

//
//	public int deleteCart(int input) throws Exception {
//		Connection conn = getConnection();
//		
//		int result = cdao.deleteCart(conn);
//		
//		if(result > 0) commit(conn);
//		else  	       rollback(conn);
//		
//		close(conn);
//
//		return result;
//	}

	public int deleteCart(int cartInNo) throws Exception {

		Connection conn = getConnection();

		int result = cdao.deleteCart(conn, cartInNo);

		close(conn);

		return result;
	}

	/** 
	 * 장바구니에 담긴 상품 개수
	 * @return
	 * @throws Exception
	 */
	public Object cart() throws Exception{

		Connection conn = getConnection();
		
		int result = cdao.cart(conn);
		
		close(conn);
		
		return result;
	}

	/** 장바구니 전체 삭제
	 * @return
	 * @throws Exception
	 */
	public int deleteAllCart() throws Exception{

		Connection conn = getConnection();
		
		int result = cdao.deleteAllCart(conn);
		
		if(result > 0) commit(conn);
		else  	       rollback(conn);
		
		close(conn);
		
		return result;
	}

}
