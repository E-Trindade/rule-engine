package engine;

import java.util.Arrays;
import java.util.List;

import business.BusinessClasses;
import business.BusinessClasses.Coupon;

public abstract class LogicExpression<T> implements EvaluableExpression<T> {
	List<EvaluableExpression<T>> arguments;

	public LogicExpression(List<EvaluableExpression<T>> arguments) {
		this.arguments = arguments;
	}
	
	public static class AndExpr<T> extends LogicExpression<T> {
		public AndExpr(List<EvaluableExpression<T>> arguments) {
			super(arguments);
		}

		@Override
		public boolean evaluate(T context) {
			for (var arg : this.arguments)
				if (arg.evaluate(context) == false)
					return false;
			return true;
		}
	}

	public static class OrExpr<T> extends LogicExpression<T> {
		public OrExpr(List<EvaluableExpression<T>> arguments) {
			super(arguments);
		}

		@Override
		public boolean evaluate(T context) {
			for (var arg : this.arguments)
				if (arg.evaluate(context))
					return true;
			return false;
		}
	}
	
	@SafeVarargs
	public static AndExpr<BusinessClasses.Coupon> and(EvaluableExpression<Coupon>... exprs){
		return new AndExpr<>(Arrays.asList(exprs));
	}

	@SafeVarargs
	public static OrExpr<BusinessClasses.Coupon> or(EvaluableExpression<Coupon>... exprs){
		return new OrExpr<>(Arrays.asList(exprs));
	}
}
