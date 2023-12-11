package org.dgm.conditional.model;

import java.util.function.BiPredicate;

public enum ConditionOperator implements BiPredicate<Boolean, Boolean> {

	AND((c0, c1) -> c0 && c1),
	OR((c0, c1) -> c0 || c1),
	XOR((c0, c1) -> c0 ^ c1),
	FIRST_CONDITION((c0, c1) -> false),
	;

	private final BiPredicate<Boolean, Boolean> biPredicate;

	ConditionOperator(final BiPredicate<Boolean, Boolean> biPredicate) {

		this.biPredicate = biPredicate;
	}

	@Override
	public boolean test(final Boolean s0, final Boolean s1) {

		return this.biPredicate.test(s0, s1);
	}
}
