package engine;

import java.util.Arrays;
import java.util.List;

import business.BusinessClasses;
import business.BusinessClasses.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;

enum ExpressionType {
	AND, OR, NOT
}

@Data
@AllArgsConstructor
public class LogicExpression<T> implements EvaluableExpression<T> {

	private static final long serialVersionUID = 6135483257028281353L;

	ExpressionType expressionType;

	List<EvaluableExpression<T>> arguments;

	@Override
	public boolean evaluate(T context) {
		switch (this.expressionType) {
		case AND:
			for (var a : this.arguments)
				if (!a.evaluate(context))
					return false;
			return true;
		case OR:
			for (var a : this.arguments)
				if (a.evaluate(context))
					return true;
			return false;
		case NOT:
			return !this.arguments.get(0).evaluate(context);
		}
		return false;
	}

	@SafeVarargs
	public static LogicExpression<BusinessClasses.Coupon> and(EvaluableExpression<Coupon>... exprs) {
		return new LogicExpression<>(ExpressionType.AND, Arrays.asList(exprs));
	}

	@SafeVarargs
	public static LogicExpression<BusinessClasses.Coupon> or(EvaluableExpression<Coupon>... exprs) {
		return new LogicExpression<>(ExpressionType.OR, Arrays.asList(exprs));
	}

	public static LogicExpression<BusinessClasses.Coupon> not(EvaluableExpression<Coupon> expr) {
		return new LogicExpression<>(ExpressionType.NOT, Arrays.asList(expr));
		
	}
}
