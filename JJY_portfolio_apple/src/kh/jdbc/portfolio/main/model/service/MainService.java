package kh.jdbc.portfolio.main.model.service;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;

import kh.jdbc.portfolio.main.model.dao.MainDAO;
import kh.jdbc.portfolio.product.vo.Product;

public class MainService {

	private MainDAO dao = new MainDAO();

	public int cartIn(Product product, int userNo) throws Exception{
		Connection conn = getConnection();

		int cart = dao.cartIn(conn, product, userNo);

		close(conn);

		return cart;
	}

	
	public int cartIn2(Product product) throws Exception {
		Connection conn = getConnection();

		int cart = dao.cartIn2(conn, product);

		close(conn);

		return cart;
	}


	public int cartIn3(Product product) throws SQLException {
		Connection conn = getConnection();

		int cart = dao.cartIn3(conn, product);

		close(conn);

		return cart;
	}

}
