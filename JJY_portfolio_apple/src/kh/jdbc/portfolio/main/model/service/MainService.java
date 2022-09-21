package kh.jdbc.portfolio.main.model.service;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.sql.Connection;

import kh.jdbc.portfolio.main.model.dao.MainDAO;
import kh.jdbc.portfolio.product.vo.Product;

public class MainService {
	
	private MainDAO dao = new MainDAO();


	public int cartIn(Product product) throws Exception {
		Connection conn = getConnection();
		
		int cart = dao.cartIn(conn, product);
		
		close(conn);
		
		return cart;
	}


	

	


	

	 

}
