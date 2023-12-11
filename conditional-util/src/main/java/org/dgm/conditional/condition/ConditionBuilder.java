package org.dgm.conditional.condition;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.function.BooleanSupplier;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionOperator;
import org.dgm.conditional.model.ConditionResult;
import org.dgm.conditional.utils.NumberUtils.BigDecimalUtils;
import org.dgm.conditional.utils.NumberUtils.IntegerUtils;
import org.dgm.conditional.utils.NumberUtils.LongUtils;

public class ConditionBuilder {

	private ConditionBuilder() {

	}

	public static Condition isNull(final Object object,
								   final ConditionOperator operator,
								   final ConditionResult conditionResult) {

		return Condition.condition(() -> object == null, operator, conditionResult);

	}

	public static Condition isEmpty(final String string,
									final ConditionOperator operator,
									final ConditionResult conditionResult) {

		return Condition.condition(() -> StringUtils.isEmpty(string), operator, conditionResult);

	}

	public static Condition isEmpty(final Collection<?> collection,
									final ConditionOperator operator,
									final ConditionResult conditionResult) {

		return Condition.condition(() -> CollectionUtils.isEmpty(collection), operator, conditionResult);

	}

	public static Condition isEmpty(final Map<?, ?> map,
									final ConditionOperator operator,
									final ConditionResult conditionResult) {

		return Condition.condition(() -> MapUtils.isEmpty(map), operator, conditionResult);

	}

	public static Condition isNonNullAndNotZero(final Integer integer,
												final ConditionOperator operator,
												final ConditionResult conditionResult) {

		return isNonNullAndNotZero(() -> IntegerUtils.isNonNullAndNotZero(integer), operator, conditionResult);
	}

	public static Condition isNonNullAndNotZero(final Long longNumber,
												final ConditionOperator operator,
												final ConditionResult conditionResult) {

		return isNonNullAndNotZero(() -> LongUtils.isNonNullAndNotZero(longNumber), operator, conditionResult);
	}

	public static Condition isNonNullAndNotZero(final BigDecimal bigDecimal,
												final ConditionOperator operator,
												final ConditionResult conditionResult) {

		return isNonNullAndNotZero(() -> BigDecimalUtils.isNonNullAndNotZero(bigDecimal), operator, conditionResult);
	}

	public static Condition isNonNullAndIsZero(final Integer integer,
											   final ConditionOperator operator,
											   final ConditionResult conditionResult) {

		return isNonNullAndIsZero(() -> IntegerUtils.isNonNullAndIsZero(integer), operator, conditionResult);
	}

	public static Condition isNonNullAndIsZero(final Long longNumber,
											   final ConditionOperator operator,
											   final ConditionResult conditionResult) {

		return isNonNullAndIsZero(() -> LongUtils.isNonNullAndIsZero(longNumber), operator, conditionResult);
	}

	public static Condition isNonNullAndIsZero(final BigDecimal bigDecimal,
											   final ConditionOperator operator,
											   final ConditionResult conditionResult) {

		return isNonNullAndIsZero(() -> BigDecimalUtils.isNonNullAndIsZero(bigDecimal), operator, conditionResult);
	}

	private static Condition isNonNullAndNotZero(final BooleanSupplier booleanSupplier,
												 final ConditionOperator operator,
												 final ConditionResult conditionResult) {

		return Condition.condition(booleanSupplier, operator, conditionResult);
	}

	private static Condition isNonNullAndIsZero(final BooleanSupplier booleanSupplier,
												final ConditionOperator operator,
												final ConditionResult conditionResult) {

		return Condition.condition(booleanSupplier, operator, conditionResult);
	}
}
