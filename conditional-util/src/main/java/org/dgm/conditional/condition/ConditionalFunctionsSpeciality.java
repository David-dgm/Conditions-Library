package org.dgm.conditional.condition;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionOperator;
import org.dgm.conditional.model.ConditionResult;


public class ConditionalFunctionsSpeciality {


	private ConditionalFunctionsSpeciality() {

	}


	public static <T, R> Condition isNull(final T recursiveObject,
										  final Function<T, R> extractFunction,
										  final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isNull(recursiveObject, extractFunction, operator, ConditionResult.NATURAL);

	}

	public static <T, R> Condition isNotNull(final T recursiveObject,
											 final Function<T, R> extractFunction,
											 final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isNull(recursiveObject, extractFunction, operator, ConditionResult.DENIED);

	}

	public static <T> Condition isEmptyString(final T recursiveObject,
											  final Function<T, String> extractStringFunction,
											  final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isEmptyString(recursiveObject, extractStringFunction, operator, ConditionResult.NATURAL);

	}

	public static <T> Condition isNotEmptyString(final T recursiveObject,
												 final Function<T, String> extractStringFunction,
												 final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isEmptyString(recursiveObject, extractStringFunction, operator, ConditionResult.DENIED);

	}

	public static <T> Condition isEmptyCollection(final T recursiveObject,
												  final Function<T, Collection<?>> extractCollectionFunction,
												  final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isEmptyCollection(recursiveObject,
														   extractCollectionFunction,
														   operator,
														   ConditionResult.NATURAL);

	}

	public static <T> Condition isNotEmptyCollection(final T recursiveObject,
													 final Function<T, Collection<?>> extractCollectionFunction,
													 final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isEmptyCollection(recursiveObject,
														   extractCollectionFunction,
														   operator,
														   ConditionResult.DENIED);

	}

	public static <T> Condition isEmptyMap(final T recursiveObject,
										   final Function<T, Map<?, ?>> extractMapFunction,
										   final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isEmptyMap(recursiveObject, extractMapFunction, operator, ConditionResult.NATURAL);

	}

	public static <T> Condition isNotEmptyMap(final T recursiveObject,
											  final Function<T, Map<?, ?>> extractMapFunction,
											  final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isEmptyMap(recursiveObject, extractMapFunction, operator, ConditionResult.DENIED);

	}

	public static <T, R extends Number> Condition isNonNullAndNotZero(final T recursiveObject,
																	  final Function<T, R> extractNumberFunction,
																	  final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isNonNullAndNotZero(recursiveObject,
															 extractNumberFunction,
															 operator,
															 ConditionResult.NATURAL);
	}

	public static <T, R extends Number> Condition isNonNullAndIsZero(final T recursiveObject,
																	 final Function<T, R> extractNumberFunction,
																	 final ConditionOperator operator) {

		return ConditionFunctionsBuilder.isNonNullAndIsZero(recursiveObject,
															extractNumberFunction,
															operator,
															ConditionResult.NATURAL);
	}

}
