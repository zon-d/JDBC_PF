package kh.jdbc.portfolio.product.vo;

public class Product {
	
	private String productType;
	private String productModel;
	private String productMemory;
	private String productCorlor;
	private String productPrice;
	private String appleCare;
	
	private void Product() {}

	

	public Product(String productModel, String productMemory, String productCorlor) {
		super();
		this.productModel = productModel;
		this.productMemory = productMemory;
		this.productCorlor = productCorlor;
	}



	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
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

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getAppleCare() {
		return appleCare;
	}

	public void setAppleCare(String appleCare) {
		this.appleCare = appleCare;
	}
	
	

}
