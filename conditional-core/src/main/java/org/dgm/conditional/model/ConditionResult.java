package org.dgm.conditional.model;

import java.util.function.Predicate;

public enum ConditionResult implements Predicate<Boolean> {

	NATURAL(c0 -> c0),
	DENIED(c0 -> !c0);

	private final Predicate<Boolean> predicate;

	ConditionResult(final Predicate<Boolean> predicate) {

		this.predicate = predicate;
	}

	@Override
	public boolean test(final Boolean s0) {

		return this.predicate.test(s0);
	}
}
