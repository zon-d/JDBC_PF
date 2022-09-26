package kh.jdbc.portfolio.cart.model.vo;

import java.util.List;

public class Cart {
	public Cart() {
	}

	private int cartInNo;
	private String productModel;
	private String productMemory;
	private String productCorlor;
	private int productPrice;
	private int priceSum;

	private List<Cart> cartList;

	public int getPriceSum() {
		return priceSum;
	}

	public Cart(int cartInNo, String productModel, String productMemory, String productCorlor, int productPrice,
			int priceSum, List<Cart> cartList) {
		super();
		this.cartInNo = cartInNo;
		this.productModel = productModel;
		this.productMemory = productMemory;
		this.productCorlor = productCorlor;
		this.productPrice = productPrice;
		this.priceSum = priceSum;
		this.cartList = cartList;
	}

	public void setPriceSum(int priceSum) {
		this.priceSum = priceSum;
	}

	public Cart(int priceSum) {
		super();
		this.priceSum = priceSum;
	}

	public Cart(int cartInNo, String productModel, String productMemory, String productCorlor, int productPrice,
			List<Cart> cartList) {
		super();
		this.cartInNo = cartInNo;
		this.productModel = productModel;
		this.productMemory = productMemory;
		this.productCorlor = productCorlor;
		this.productPrice = productPrice;
		this.cartList = cartList;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
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
