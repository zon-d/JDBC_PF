package kh.jdbc.portfolio.member.vo;

public class User {

	public User() {
	}

	public int userNo;
	public String userName;
	public String userPhone;

	public int cart;
	public int order;

	public int getCart() {
		return cart;
	}

	public void setCart(int cart) {
		this.cart = cart;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public User(int userNo, String userName, String userPhone, int cart, int order) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.userPhone = userPhone;
		this.cart = cart;
		this.order = order;
	}

	public User(String userName, String userPhone) {
		super();
		this.userName = userName;
		this.userPhone = userPhone;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}