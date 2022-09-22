package kh.jdbc.portfolio.cart.model.vo;

import java.util.List;

public class Cart {
	public Cart() {}
	
	private int cartInNo;
	private String productModel;
	private String productMemory;
	private String productCorlor;
	
	private List<Cart> cartList;

	public Cart(int cartInNo, String productModel, String productMemory, String productCorlor, List<Cart> cartList) {
		super();
		this.cartInNo = cartInNo;
		this.productModel = productModel;
		this.productMemory = productMemory;
		this.productCorlor = productCorlor;
		this.cartList = cartList;
	}

	public int getCartInNo() {
		return cartInNo;
	}

	public void setCartInNo(int cartInNo) {
		this.cartInNo = cartInNo;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductMemory() {
		return productMemory;
	}

	public void setProductMemory(String productMemory) {
		this.productMemory = productMemory;
	}

	public String getProductCorlor() {
		return productCorlor;
	}

	public void setProductCorlor(String productCorlor) {
		this.productCorlor = productCorlor;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	
	
	

}
