package org.dgm.conditional.model;

import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionOperator.FIRST_CONDITION;
import static org.dgm.conditional.model.ConditionOperator.OR;

import java.util.Objects;
import java.util.function.BooleanSupplier;

public class Condition {

	BooleanSupplier booleanSupplier;
	ConditionOperator operator;
	ConditionResult conditionResult = ConditionResult.NATURAL;
	private boolean value;
	private boolean loaded;

	private Condition(final BooleanSupplier booleanSupplier) {

		this.booleanSupplier = booleanSupplier;
		this.operator = FIRST_CONDITION;
	}

	private Condition(final BooleanSupplier booleanSupplier, final ConditionResult conditionResult) {

		this.booleanSupplier = booleanSupplier;
		this.conditionResult = conditionResult;
		this.operator = FIRST_CONDITION;
	}

	private Condition(final BooleanSupplier booleanSupplier, final ConditionOperator operator) {

		this.booleanSupplier = booleanSupplier;
		this.operator = operator;
	}

	private Condition(final BooleanSupplier booleanSupplier,
					  final ConditionOperator operator,
					  final ConditionResult conditionResult) {

		this.booleanSupplier = booleanSupplier;
		this.operator = operator;
		this.conditionResult = conditionResult;
	}

	public static Condition condition(final BooleanSupplier booleanSupplier) {

		return new Condition(booleanSupplier);
	}

	public static Condition condition(final BooleanSupplier booleanSupplier, final ConditionResult conditionResult) {

		return new Condition(booleanSupplier, conditionResult);
	}

	public static Condition condition(final BooleanSupplier booleanSupplier, final ConditionOperator operator) {

		return new Condition(booleanSupplier, operator);
	}

	public static Condition condition(final BooleanSupplier booleanSupplier,
									  final ConditionOperator operator,
									  final ConditionResult conditionResult) {

		return new Condition(booleanSupplier, operator, conditionResult);
	}

	public static Condition conditionAsCopulative(final BooleanSupplier booleanSupplier) {

		return condition(booleanSupplier, AND);
	}

	public static Condition conditionAsCopulative(final BooleanSupplier booleanSupplier, final ConditionResult conditionResult) {

		return condition(booleanSupplier, AND, conditionResult);
	}

	public static Condition conditionAsDisjunction(final BooleanSupplier booleanSupplier) {

		return condition(booleanSupplier, OR);
	}

	public static Condition conditionAsDisjunction(final BooleanSupplier booleanSupplier, final ConditionResult conditionResult) {

		return condition(booleanSupplier, OR, conditionResult);
	}


	public Condition load() {

		final boolean supplierAsBoolean = this.booleanSupplier.getAsBoolean();
		this.value = this.conditionResult.test(supplierAsBoolean);
		this.loaded = true;
		return this;
	}

	public boolean is() {

		this.checkLoad(this);
		return this.value;
	}

	public boolean isAlreadyLoaded() {

		return this.loaded;
	}

	public boolean operateCondition(final boolean condition) {

		if (this.isSameOperator(AND) && !condition) {

			return false;
		}

		if (this.isSameOperator(OR) && condition) {

			return true;
		}

		this.checkLoad(this);

		return this.operator.test(condition, this.value);
	}

	public boolean operateCondition(final Condition condition) {

		this.checkLoad(this);

		if (condition.isSameOperator(AND) && !this.value) {

			return this.is();
		}

		if (condition.isSameOperator(OR) && this.value) {

			return this.is();
		}

		this.checkLoad(condition);

		return condition.operateCondition(this.value);
		//		return this.operateCondition(condition.is());
	}

	public boolean isSameOperator(final ConditionOperator otherConditionOperator) {

		return Objects.equals(this.operator, otherConditionOperator);
	}

	private void checkLoad(final Condition condition) {

		if (!condition.isAlreadyLoaded()) {
			condition.load();
		}
	}

	public Condition asCopulative() {

		return conditionAsCopulative(this.booleanSupplier, this.conditionResult);
	}

	public Condition asDisjunction() {

		return conditionAsDisjunction(this.booleanSupplier, this.conditionResult);
	}
}
