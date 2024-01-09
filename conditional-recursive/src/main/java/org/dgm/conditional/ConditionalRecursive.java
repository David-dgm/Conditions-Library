package org.dgm.conditional;

import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionOperator.FIRST_CONDITION;
import static org.dgm.conditional.model.ConditionOperator.OR;

import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import org.dgm.conditional.condition.Conditional.Conditional123;
import org.dgm.conditional.condition.ConditionalSpeciality;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionOperator;

public class ConditionalRecursive<T> extends Conditional123 {

	private T recursiveObject;
	private RecursiveConditionFunctions conditionFunctionsProvider;

	private ConditionalRecursive() {

		super();
	}

	private ConditionalRecursive(final T objectToInspect) {

		super();
		this.recursiveObject = objectToInspect;
		this.conditionFunctionsProvider = RecursiveConditionFunctions.recursiveConditionFunctions();
	}

	public static <T> ConditionalRecursive<T> conditionalRecursiveOver(final T objectToInspect) {

		return new ConditionalRecursive<>(objectToInspect);
	}


	public ConditionalRecursive<T> isNotNull() {

		final Condition notNullCondition = ConditionalSpeciality.isNotNull(this.recursiveObject, FIRST_CONDITION);

		return this.addRecursive(notNullCondition);
	}

	public <R> ConditionalRecursive<T> isNotNull(@NotNull final Function<T, R> extractAttributeFunction,
												 final ConditionOperator operator) {

		final Condition notNullCondition = this.conditionFunctionsProvider.isNotNull(this.recursiveObject,
																					 extractAttributeFunction,
																					 operator);

		return this.addRecursive(notNullCondition);
	}

	public ConditionalRecursive<T> isNotEmptyString(@NotNull final Function<T, String> extractStringFunction,
													final ConditionOperator operator) {

		final Condition notEmptyStringCondition = this.conditionFunctionsProvider.isNotEmptyString(this.recursiveObject,
																								   extractStringFunction,
																								   operator);

		return this.addRecursive(notEmptyStringCondition);
	}

	public ConditionalRecursive<T> isEmptyString(@NotNull final Function<T, String> extractStringFunction,
												 final ConditionOperator operator) {

		final Condition emptyStringCondition = this.conditionFunctionsProvider.isEmptyString(this.recursiveObject,
																							 extractStringFunction,
																							 operator);

		return this.addRecursive(emptyStringCondition);
	}

	public ConditionalRecursive<T> isNotEmptyCollection(@NotNull final Function<T, Collection<?>> extractStringFunction,
														final ConditionOperator operator) {

		final Condition notEmptyCollectionCondition = this.conditionFunctionsProvider.isNotEmptyCollection(this.recursiveObject,
																										   extractStringFunction,
																										   operator);

		return this.addRecursive(notEmptyCollectionCondition);
	}

	public ConditionalRecursive<T> isEmptyCollection(@NotNull final Function<T, Collection<?>> extractStringFunction,
													 final ConditionOperator operator) {

		final Condition emptyCollectionCondition = this.conditionFunctionsProvider.isEmptyCollection(this.recursiveObject,
																									 extractStringFunction,
																									 operator);

		return this.addRecursive(emptyCollectionCondition);
	}

	public ConditionalRecursive<T> isNotEmptyMap(@NotNull final Function<T, Map<?, ?>> extractMapFunction,
												 final ConditionOperator operator) {

		final Condition notEmptyMapCondition = this.conditionFunctionsProvider.isNotEmptyMap(this.recursiveObject,
																							 extractMapFunction,
																							 operator);

		return this.addRecursive(notEmptyMapCondition);
	}

	/**
	 * Null-safe check if the specified map is empty.
	 * <p>
	 * Null returns true.
	 *
	 * @param extractMapFunction the map to check, may be null
	 *
	 * @return true if empty or null
	 */
	public ConditionalRecursive<T> isEmptyMap(@NotNull final Function<T, Map<?, ?>> extractMapFunction,
											  final ConditionOperator operator) {

		final Condition emptyMapCondition = this.conditionFunctionsProvider.isEmptyMap(this.recursiveObject,
																					   extractMapFunction,
																					   operator);

		return this.addRecursive(emptyMapCondition);
	}

	public <R extends Number> ConditionalRecursive<T> isNonNullAndNotZero(@NotNull final Function<T, R> extractNumberFunction,
																		  final ConditionOperator operator) {

		final Condition condition = this.conditionFunctionsProvider.isNonNullAndNotZero(this.recursiveObject,
																						extractNumberFunction,
																						operator);

		return this.addRecursive(condition);
	}

	public <R extends Number> ConditionalRecursive<T> isNonNullAndIsZero(@NotNull final Function<T, R> extractNumberFunction,
																		 final ConditionOperator operator) {

		final Condition notNullAndZeroNumberCondition = this.conditionFunctionsProvider.isNonNullAndIsZero(this.recursiveObject,
																										   extractNumberFunction,
																										   operator);

		return this.addRecursive(notNullAndZeroNumberCondition);
	}

	public <R> ConditionalRecursive<T> andIsNotNull(@NotNull final Function<T, R> extractAttributeFunction) {

		return this.isNotNull(extractAttributeFunction, AND);
	}

	public ConditionalRecursive<T> andIsNotEmptyString(@NotNull final Function<T, String> extractStringFunction) {

		return this.isNotEmptyString(extractStringFunction, AND);
	}

	public ConditionalRecursive<T> andIsEmptyString(@NotNull final Function<T, String> extractStringFunction) {

		return this.isEmptyString(extractStringFunction, AND);
	}

	public ConditionalRecursive<T> andIsNotEmptyCollection(@NotNull final Function<T, Collection<?>> extractStringFunction) {

		return this.isNotEmptyCollection(extractStringFunction, AND);
	}

	public ConditionalRecursive<T> andIsEmptyCollection(@NotNull final Function<T, Collection<?>> extractStringFunction) {

		return this.isEmptyCollection(extractStringFunction, AND);
	}

	public ConditionalRecursive<T> andIsNotEmptyMap(@NotNull final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isNotEmptyMap(extractMapFunction, AND);
	}

	public ConditionalRecursive<T> andIsEmptyMap(@NotNull final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isEmptyMap(extractMapFunction, AND);
	}

	public <R extends Number> ConditionalRecursive<T> andIsNonNullAndNotZero(@NotNull final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndNotZero(extractNumberFunction, AND);
	}

	public <R extends Number> ConditionalRecursive<T> andIsNonNullAndIsZero(@NotNull final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndIsZero(extractNumberFunction, AND);
	}


	public <R> ConditionalRecursive<T> orIsNotNull(@NotNull final Function<T, R> extractAttributeFunction) {

		return this.isNotNull(extractAttributeFunction, OR);
	}

	public ConditionalRecursive<T> orIsNotEmptyString(@NotNull final Function<T, String> extractStringFunction) {

		return this.isNotEmptyString(extractStringFunction, OR);
	}

	public ConditionalRecursive<T> orIsEmptyString(@NotNull final Function<T, String> extractStringFunction) {

		return this.isEmptyString(extractStringFunction, OR);
	}

	public ConditionalRecursive<T> orIsNotEmptyCollection(@NotNull final Function<T, Collection<?>> extractStringFunction) {

		return this.isNotEmptyCollection(extractStringFunction, OR);
	}

	public ConditionalRecursive<T> orIsEmptyCollection(@NotNull final Function<T, Collection<?>> extractStringFunction) {

		return this.isEmptyCollection(extractStringFunction, OR);
	}

	public ConditionalRecursive<T> orIsNotEmptyMap(@NotNull final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isNotEmptyMap(extractMapFunction, OR);
	}

	public ConditionalRecursive<T> orIsEmptyMap(@NotNull final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isEmptyMap(extractMapFunction, OR);
	}

	public <R extends Number> ConditionalRecursive<T> orIsNonNullAndNotZero(@NotNull final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndNotZero(extractNumberFunction, OR);
	}

	public <R extends Number> ConditionalRecursive<T> orIsNonNullAndIsZero(@NotNull final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndIsZero(extractNumberFunction, OR);
	}


	// Este add sobreescribe el normal, añadiendo la validación
	private ConditionalRecursive<T> addRecursive(final Condition condition) {

		return (ConditionalRecursive<T>) super.addCondition(condition);
	}
}
