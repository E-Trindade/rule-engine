package business;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BusinessClasses {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Coupon {
		List<Product> products;
		
		public double getTotalValue() {
			var sum = 0.0;
			for(var p : products)
				sum += p.getPrice();
			return sum;
		}
		
 	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Product {
		long id;
		double price;
		long quantity;
	}
}
