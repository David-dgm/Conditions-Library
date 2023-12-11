package org.dgm.conditional.condition;

import static java.util.Objects.requireNonNull;
import static org.dgm.conditional.model.ConditionOperator.AND;

import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.Conditional;

public abstract class AbstractConditional implements Conditional {

	protected AbstractConditional() {

	}

	static UnsupportedOperationException uoe() {

		return new UnsupportedOperationException();
	}


	@Override
	public boolean is() {

		throw uoe();
	}


	protected abstract AbstractConditional addCondition(final Condition newCondition);


	protected boolean scapeNextCondition(final boolean previousConditionEvaluated, final Condition previousCondition) {

		requireNonNull(previousCondition);

		return !previousConditionEvaluated && previousCondition.isSameOperator(AND);
	}
}
