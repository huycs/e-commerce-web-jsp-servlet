package model;

public class Order extends Product{

	private String buyerMail;
	private int orderId;
	private int orderStatus;
	private String date;
	private String discountCode;
	private String address;
	
	public Order() {}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getBuyerMail() {
		return buyerMail;
	}

	public void setBuyerMail(String buyerMail) {
		this.buyerMail = buyerMail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [buyerMail=" + buyerMail + ", orderId=" + orderId + ", orderStatus=" + orderStatus + ", date="
				+ date + ", discountCode=" + discountCode + ", address=" + address + "]";
	}
		
	

}
