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
	 * @param userNo 
	 * 
	 * @return
	 */
	public List<Cart> cartList(int userNo) throws Exception {
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

	/**
	 * 상품 선택 삭제
	 * 
	 * @param cartInNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCart(int cartInNo) throws Exception {

		Connection conn = getConnection();

		int result = cdao.deleteCart(conn, cartInNo);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

	/**
	 * 장바구니에 담긴 상품 개수
	 * @param userNo 
	 * 
	 * @return
	 * @throws Exception
	 */
	public int cart(int userNo) throws Exception {

		Connection conn = getConnection();

		int result = cdao.cart(conn, userNo);

		close(conn);

		return result;
	}

	/**
	 * 장바구니 전체 삭제
	 * 
	 * @return
	 * @throws Exception
	 */
	public int deleteAllCart() throws Exception {

		Connection conn = getConnection();

		int result = cdao.deleteAllCart(conn);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

	/**
	 * 상품 선택 주문
	 * 
	 * @param cartInNo
	 * @return
	 */
	public int order(int cartInNo) throws Exception {
		Connection conn = getConnection();

		int result = cdao.order(conn, cartInNo);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}



}
