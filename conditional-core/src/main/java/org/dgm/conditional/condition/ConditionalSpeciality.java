package org.dgm.conditional.condition;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionOperator;
import org.dgm.conditional.model.ConditionResult;


public class ConditionalSpeciality {


	private ConditionalSpeciality() {

	}


	public static Condition isNull(final Object object, final ConditionOperator operator) {

		return ConditionBuilder.isNull(object, operator, ConditionResult.NATURAL);

	}

	public static Condition isNotNull(final Object object, final ConditionOperator operator) {

		return ConditionBuilder.isNull(object, operator, ConditionResult.DENIED);

	}

	public static Condition isEmpty(final String string, final ConditionOperator operator) {

		return ConditionBuilder.isEmpty(string, operator, ConditionResult.NATURAL);

	}

	public static Condition isNotEmpty(final String string, final ConditionOperator operator) {

		return ConditionBuilder.isEmpty(string, operator, ConditionResult.DENIED);

	}

	public static Condition isEmpty(final Collection<?> collection, final ConditionOperator operator) {

		return ConditionBuilder.isEmpty(collection, operator, ConditionResult.NATURAL);

	}

	public static Condition isNotEmpty(final Collection<?> collection, final ConditionOperator operator) {

		return ConditionBuilder.isEmpty(collection, operator, ConditionResult.DENIED);

	}

	public static Condition isEmpty(final Map<?, ?> map, final ConditionOperator operator) {

		return ConditionBuilder.isEmpty(map, operator, ConditionResult.NATURAL);

	}

	public static Condition isNotEmpty(final Map<?, ?> map, final ConditionOperator operator) {

		return ConditionBuilder.isEmpty(map, operator, ConditionResult.DENIED);

	}

	public static <T extends Number> Condition isNonNullAndNotZero(final T number, final ConditionOperator operator) {

		if (number instanceof Integer integerNumber) {

			return isNonNullAndNotZero(integerNumber, operator);
		}
		if (number instanceof Long longNumber) {

			return isNonNullAndNotZero(longNumber, operator);
		}
		if (number instanceof BigDecimal bigDecimal) {

			return isNonNullAndNotZero(bigDecimal, operator);
		}
		return isNotNull(number, operator);
	}

	private static Condition isNonNullAndNotZero(final Integer integer, final ConditionOperator operator) {

		return ConditionBuilder.isNonNullAndNotZero(integer, operator, ConditionResult.NATURAL);
	}

	private static Condition isNonNullAndNotZero(final Long longNumber, final ConditionOperator operator) {

		return ConditionBuilder.isNonNullAndNotZero(longNumber, operator, ConditionResult.NATURAL);
	}

	private static Condition isNonNullAndNotZero(final BigDecimal bigDecimal, final ConditionOperator operator) {

		return ConditionBuilder.isNonNullAndNotZero(bigDecimal, operator, ConditionResult.NATURAL);
	}


	public static Condition isNonNullAndIsZero(final Number number, final ConditionOperator operator) {

		if (number instanceof Integer integer) {

			return isNonNullAndIsZero(integer, operator);
		}
		if (number instanceof Long longNumber) {

			return isNonNullAndIsZero(longNumber, operator);
		}
		if (number instanceof BigDecimal bigDecimal) {

			return isNonNullAndIsZero(bigDecimal, operator);
		}
		return isNotNull(number, operator);
	}

	private static Condition isNonNullAndIsZero(final Integer integer, final ConditionOperator operator) {

		return ConditionBuilder.isNonNullAndIsZero(integer, operator, ConditionResult.NATURAL);
	}

	private static Condition isNonNullAndIsZero(final Long longNumber, final ConditionOperator operator) {

		return ConditionBuilder.isNonNullAndIsZero(longNumber, operator, ConditionResult.NATURAL);
	}

	private static Condition isNonNullAndIsZero(final BigDecimal bigDecimal, final ConditionOperator operator) {

		return ConditionBuilder.isNonNullAndIsZero(bigDecimal, operator, ConditionResult.NATURAL);
	}
}
