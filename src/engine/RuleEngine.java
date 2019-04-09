package engine;

public class RuleEngine<T> {
	
	public boolean run(T context, EvaluableExpression<T> expr) {
		return expr.evaluate(context);
	}

}
