package kh.jdbc.portfolio.order.model.service;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.order.model.dao.OrderDAO;
import kh.jdbc.portfolio.order.model.vo.Order;

public class OrderService {

	public OrderDAO odao = new OrderDAO();

	/*
	 * 주문 리스트 생성
	 */
	public List<Order> orderList(int userNo) throws Exception {
		Connection conn = getConnection();

		List<Order> orderList = odao.orderList(conn, userNo);

		close(conn);

		return orderList;
	}

	/**
	 * 주문금액 합계
	 * 
	 * @return
	 */
	public Object priceSum() throws Exception {
		Connection conn = getConnection();

		int priceSum = odao.priceSum(conn);

		close(conn);

		return priceSum;
	}

	/**
	 * 주문 내역 삭제
	 * 
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public int deleteOrder(int orderNo) throws Exception {
		
		Connection conn = getConnection();

		int result = odao.updateComment(conn, orderNo);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

}
