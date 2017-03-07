package com.icodejava.research.ready;

import java.util.Date;

public class ExchangeRate {
		private String currency;
		private Date date;
		private int quantity;
		private double buyRate;
		private double sellRate;
		
		public ExchangeRate (String currency, Date date, int quantity, double buyRate, double sellRate) {
			this.currency = currency;
			this.date = date;
			this.quantity = quantity;
			this.buyRate = buyRate;
			this.sellRate = sellRate;
		}

		public ExchangeRate() {
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}
		
		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}

		public double getBuyRate() {
			return buyRate;
		}

		public void setBuyRate(double buyRate) {
			this.buyRate = buyRate;
		}

		public double getSellRate() {
			return sellRate;
		}

		public void setSellRate(double sellRate) {
			this.sellRate = sellRate;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		@Override
		public String toString () {
			
			return "Currency: " + currency + "Date: " + date +" Quantity: " + quantity + " Buy Rate: " + buyRate + " Sell Rate: " + sellRate;
			
		}
}