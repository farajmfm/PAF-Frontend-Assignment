package com.xadmin.customermanagement.model;

public class Customer {
		protected int id;
		protected String paymentMethod;
		protected String amount;
		protected String billNo;
		protected String bankName;
		
		public Customer() {
		}
		
		public Customer(String paymentMethod, String amount, String billNo, String bankName) {
			super();
			this.paymentMethod = paymentMethod;
			this.amount = amount;
			this.billNo = billNo;
			this.bankName = bankName;
		}

		public Customer(int id, String paymentMethod, String amount, String billNo, String bankName) {
			super();
			this.id = id;
			this.paymentMethod = paymentMethod;
			this.amount = amount;
			this.billNo = billNo;
			this.bankName = bankName;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

}

