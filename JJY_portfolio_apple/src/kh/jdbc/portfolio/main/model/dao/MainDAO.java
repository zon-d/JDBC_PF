package kh.jdbc.portfolio.main.model.dao;

import static kh.jdbc.portfolio.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import kh.jdbc.portfolio.product.vo.Product;

public class MainDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Properties prop = null;

	public MainDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int cartIn(Connection conn, Product product) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("cartIn");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, product.getProductModel());
			pstmt.setString(2, product.getProductMemory());
			pstmt.setString(3, product.getProductCorlor());

			result = pstmt.executeUpdate();
			try {

				String price = prop.getProperty("cartPrice");

				stmt = conn.createStatement();

				rs = stmt.executeQuery(price);
			} finally {
				close(rs);
				close(stmt);

			}

		} finally {
			close(pstmt);
		}

		return result;
	}

}
