package org.dgm.conditional.model;

import static org.dgm.conditional.model.Condition.condition;

import java.util.function.BooleanSupplier;

public class ParameterTestProvider {

	private ParameterTestProvider() {

	}

	public static final BooleanSupplier BOOLEAN_SUPPLIER_TRUE = () -> {
		System.out.println("Executing supplier true.");
		return true;
	};
	public static final BooleanSupplier BOOLEAN_SUPPLIER_TRUE_EXCEPTION = () -> {
		System.out.println("This supplier should not be running.");
		throw new UnsupportedOperationException();
	};
	public static final BooleanSupplier BOOLEAN_SUPPLIER_FALSE = () -> {
		System.out.println("Executing supplier false.");
		return false;
	};
	public static final BooleanSupplier BOOLEAN_SUPPLIER_FALSE_EXCEPTION = () -> {
		System.out.println("This supplier should not be running.");
		throw new UnsupportedOperationException();
	};

	public static final Condition CONDITION_FALSE = condition(BOOLEAN_SUPPLIER_FALSE);
	public static final Condition CONDITION_TRUE = condition(BOOLEAN_SUPPLIER_TRUE);

	public static final Condition CONDITION_FALSE_EXCEPTION = condition(BOOLEAN_SUPPLIER_FALSE_EXCEPTION);
	public static final Condition CONDITION_TRUE_EXCEPTION = condition(BOOLEAN_SUPPLIER_TRUE_EXCEPTION);
}
