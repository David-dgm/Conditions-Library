package org.dgm.conditional.condition;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionOperator;
import org.dgm.conditional.model.ConditionResult;
import org.dgm.conditional.utils.NumberUtils;

public class ConditionFunctionsBuilder {

	private ConditionFunctionsBuilder() {

	}

	public static <T, R> Condition isNull(final T recursiveObject,
										  final Function<T, R> extractFunction,
										  final ConditionOperator operator,
										  final ConditionResult conditionResult) {

		return Condition.condition(() -> extractFunction.andThen(Objects::isNull).apply(recursiveObject),
								   operator,
								   conditionResult);

	}

	public static <T> Condition isEmptyString(final T recursiveObject,
											  final Function<T, String> extractFunction,
											  final ConditionOperator operator,
											  final ConditionResult conditionResult) {

		return Condition.condition(() -> extractFunction.andThen(StringUtils::isEmpty).apply(recursiveObject),
								   operator,
								   conditionResult);
	}

	public static <T> Condition isEmptyCollection(final T recursiveObject,
												  final Function<T, Collection<?>> extractFunction,
												  final ConditionOperator operator,
												  final ConditionResult conditionResult) {

		return Condition.condition(() -> extractFunction.andThen(CollectionUtils::isEmpty).apply(recursiveObject),
								   operator,
								   conditionResult);
	}

	public static <T> Condition isEmptyMap(final T recursiveObject,
										   final Function<T, Map<?, ?>> extractMapFunction,
										   final ConditionOperator operator,
										   final ConditionResult conditionResult) {

		return Condition.condition(() -> extractMapFunction.andThen(MapUtils::isEmpty).apply(recursiveObject),
								   operator,
								   conditionResult);

	}

	public static <T, R extends Number> Condition isNonNullAndNotZero(final T recursiveObject,
																	  final Function<T, R> extractNumberFunction,
																	  final ConditionOperator operator,
																	  final ConditionResult conditionResult) {

		return Condition.condition(() -> NumberUtils.isNonNullAndNotZero(extractNumberFunction.apply(recursiveObject)),
								   operator,
								   conditionResult);
	}

	public static <T, R extends Number> Condition isNonNullAndIsZero(final T recursiveObject,
																	 final Function<T, R> extractNumberFunction,
																	 final ConditionOperator operator,
																	 final ConditionResult conditionResult) {

		return Condition.condition(() -> NumberUtils.isNonNullAndIsZero(extractNumberFunction.apply(recursiveObject)),
								   operator,
								   conditionResult);
	}
}
