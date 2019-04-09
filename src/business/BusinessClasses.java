package business;

import java.util.List;

public class BusinessClasses {
	public static class Coupon {
		List<Product> products;

		public Coupon() {
		}
		
		public Coupon(List<Product> products) {
			super();
			this.products = products;
		}
		
		
 	}
	
	public static class Product {
		long id;
		double price;
		long quantity;
		
		public Product() {
		}
		
		public Product(long id, double price, long quantity) {
			super();
			this.id = id;
			this.price = price;
			this.quantity = quantity;
		}

	}
}
