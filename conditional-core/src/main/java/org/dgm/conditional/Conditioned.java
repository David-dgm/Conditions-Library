package org.dgm.conditional;

@FunctionalInterface
public interface Conditioned {


	boolean is();

	default boolean negate() {

		return !this.is();
	}

	//	AbstractConditional addCondition(final Condition newCondition);
}
