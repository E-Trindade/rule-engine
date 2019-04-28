package engine;

import business.BusinessClasses.Coupon;
import business.BusinessClasses.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

public abstract class CouponPropositions implements EvaluableExpression<Coupon> {

	private static final long serialVersionUID = 8665064495054974163L;

	@AllArgsConstructor
	@ToString
	public static class ProductHasQuantity extends CouponPropositions {

		private static final long serialVersionUID = -1117880049185838509L;

		private long productId;
		private Integer minQuantity;
		private Integer MaxQuantity;

		@Override
		public boolean evaluate(Coupon t) {
			int accSum = 0;
			for (Product p : t.getProducts())
				if (p.getId() == this.productId)
					accSum += p.getQuantity();
		
			System.out.println(this.toString() + " accSum: " + accSum);

			if (this.minQuantity != null && accSum < this.minQuantity) {
				System.out.println(this.toString() + " did not hitted minQuantity. Evaluating to false");
				return false;
			}
			if (this.MaxQuantity != null && accSum > this.MaxQuantity) {
				System.out.println(this.toString() + " hitted maxQuantity. Evaluating to false");
				return false;
			}

			System.out.println(this.toString() + " evaluated to true");
			return true;
		}

	}

	@Data
	@EqualsAndHashCode(callSuper=false)
	@AllArgsConstructor
	public static class SaleHasValue extends CouponPropositions {

		private static final long serialVersionUID = 5348410170222649352L;

		private Double minValue;
		private Double maxValue;

		@Override
		public boolean evaluate(Coupon t) {
			
			System.out.println(this.toString() + " sale totalValue:" + t.getTotalValue());
			
			if (this.minValue != null && t.getTotalValue() < this.minValue) {
				System.out.println(this.toString() + " did not hit minValue. evaluated to false");
				return false;
			}
			if (this.maxValue != null && t.getTotalValue() > this.maxValue) {
				System.out.println(this.toString() + " hit max value. Evaluated to false");
				return false;
			}

			System.out.println(this.toString() + " evaluated to true");
			return true;
		}
	}
}
