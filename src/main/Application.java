package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import business.BusinessClasses;
import business.BusinessClasses.Coupon;
import engine.CouponPropositions.ProductHasQuantity;
import engine.CouponPropositions.SaleHasValue;
import engine.EvaluableExpression;
import engine.LogicExpression;
import engine.RuleEngine;

public class Application {
	public static BusinessClasses.Coupon getCoupon() {
		var products = Arrays.asList(
				new BusinessClasses.Product(1, 1.5, 5),
				new BusinessClasses.Product(2, 5, 3),
				new BusinessClasses.Product(3, 1, 2),
				new BusinessClasses.Product(4, 0.4, 4)
		);
		return new BusinessClasses.Coupon(products);
	}
	
	public static EvaluableExpression<Coupon> getExpr() {
		return LogicExpression.and(
				LogicExpression.or(
						new ProductHasQuantity(1, 6, null),
						new ProductHasQuantity(2, 2, null),
						new ProductHasQuantity(3, 3, 1)
				),
				LogicExpression.or(
						new ProductHasQuantity(4, 2, null)
				),

				new SaleHasValue(6.0, null)
		);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		var coupon = getCoupon();
		var expr = getExpr();
		
		RuleEngine<Coupon> engine = new RuleEngine<>();
		
		boolean run = engine.run(coupon, expr);
		
		System.out.println("Evaluated to " + run);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(expr);
		objectOutputStream.close();
		System.out.println(outputStream.toByteArray());
		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
		ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
		System.out.println(inputStream.readObject());
	}
}
