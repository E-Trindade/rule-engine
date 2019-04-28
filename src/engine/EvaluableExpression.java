package engine;

import java.io.Serializable;

public interface EvaluableExpression <T> extends Serializable {
	public boolean evaluate(T t);
}