package main;

import java.util.Arrays;

import business.BusinessClasses;
import business.BusinessClasses.Coupon;
import engine.EvaluableExpression;
import engine.LogicExpression;
import engine.RuleEngine;

public class Application {
	public static BusinessClasses.Coupon getCoupon() {
		var products = Arrays.asList(
				new BusinessClasses.Product(1, 1.5, 5),
				new BusinessClasses.Product(2, 5, 1),
				new BusinessClasses.Product(3, 9, 2),
				new BusinessClasses.Product(4, 0.1, 1)
		);
		return new BusinessClasses.Coupon(products);
	}
	
	public static EvaluableExpression<Coupon> getExpr() {
		return LogicExpression.and(
				LogicExpression.or(
						(t) -> true,
						(t) -> false
				),
				(t) -> false
		);
	}
	
	public static void main(String[] args) {
		var coupon = getCoupon();
		var expr = getExpr();
		
		RuleEngine<Coupon> engine = new RuleEngine<>();
		
		boolean run = engine.run(coupon, expr);
		
		System.out.println(run);
	}
}
