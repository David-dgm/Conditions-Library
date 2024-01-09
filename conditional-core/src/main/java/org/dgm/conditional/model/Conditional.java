package org.dgm.conditional.model;

public interface Conditional {

	boolean is();


	default boolean negate() {

		return !this.is();
	}

	//	Conditional addCondition(final Condition newCondition);

	//	boolean scapeNextCondition(final boolean previousConditionEvaluated, final Condition previousCondition);
}
