package org.dgm.conditional.recursive;

import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionOperator.OR;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import org.dgm.conditional.condition.ConditionalFunctionsSpeciality;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionOperator;

class RecursiveConditionFunctions {


	private RecursiveConditionFunctions() {

	}

	public static RecursiveConditionFunctions recursiveConditionFunctions() {

		return new RecursiveConditionFunctions();

	}

	// General
	public <T, R> Condition isNotNull(final T recusiveObject,
									  final Function<T, R> functionExtract,
									  final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isNotNull(recusiveObject, functionExtract, operator);
	}


	public <T> Condition isEmptyString(final T recursiveObject,
									   final Function<T, String> extractStringFunction,
									   final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isEmptyString(recursiveObject, extractStringFunction, operator);
	}


	public <T> Condition isEmptyCollection(final T recursiveObject,
										   final Function<T, Collection<?>> extractCollectionFunction,
										   final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isEmptyCollection(recursiveObject, extractCollectionFunction, operator);
	}


	public <T> Condition isEmptyMap(final T recursiveObject,
									final Function<T, Map<?, ?>> extractMapFunction,
									final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isEmptyMap(recursiveObject, extractMapFunction, operator);
	}


	public <T> Condition isNotEmptyString(final T recursiveObject,
										  final Function<T, String> extractStringFunction,
										  final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isNotEmptyString(recursiveObject, extractStringFunction, operator);
	}


	public <T> Condition isNotEmptyCollection(final T recursiveObject,
											  final Function<T, Collection<?>> extractCollectionFunction,
											  final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isNotEmptyCollection(recursiveObject, extractCollectionFunction, operator);
	}


	public <T> Condition isNotEmptyMap(final T recursiveObject,
									   final Function<T, Map<?, ?>> extractMapFunction,
									   final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isNotEmptyMap(recursiveObject, extractMapFunction, operator);
	}


	public <T, R extends Number> Condition isNonNullAndNotZero(final T recursiveObject,
															   final Function<T, R> extractNumberFunction,
															   final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isNonNullAndNotZero(recursiveObject, extractNumberFunction, operator);
	}


	public <T, R extends Number> Condition isNonNullAndIsZero(final T recursiveObject,
															  final Function<T, R> extractNumberFunction,
															  final ConditionOperator operator) {

		return ConditionalFunctionsSpeciality.isNonNullAndIsZero(recursiveObject, extractNumberFunction, operator);
	}

	// TODO valorar si es necesario tener  aquí los operadores ,
	//  o un nivel más arriba, en tal caso sobran las disyuntivas y copulativas

	// Disyuntivas

	public <T, R> Condition andIsNotNull(final T recusiveObject, final Function<T, R> functionExtract) {

		return this.isNotNull(recusiveObject, functionExtract, AND);
	}


	public <T> Condition andIsEmptyString(final T recursiveObject,
										  final Function<T, String> extractStringFunction) {

		return this.isEmptyString(recursiveObject, extractStringFunction, AND);
	}


	public <T> Condition andIsEmptyCollection(final T recursiveObject,
											  final Function<T, Collection<?>> extractCollectionFunction) {

		return this.isEmptyCollection(recursiveObject, extractCollectionFunction, AND);
	}


	public <T> Condition andIsEmptyMap(final T recursiveObject,
									   final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isEmptyMap(recursiveObject, extractMapFunction, AND);
	}


	public <T> Condition andIsNotEmptyString(final T recursiveObject,
											 final Function<T, String> extractStringFunction) {

		return this.isNotEmptyString(recursiveObject, extractStringFunction, AND);
	}


	public <T> Condition andIsNotEmptyCollection(final T recursiveObject,
												 final Function<T, Collection<?>> extractCollectionFunction) {

		return this.isNotEmptyCollection(recursiveObject, extractCollectionFunction, AND);
	}


	public <T> Condition andIsNotEmptyMap(final T recursiveObject,
										  final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isNotEmptyMap(recursiveObject, extractMapFunction, AND);
	}


	public <T, R extends Number> Condition andIsNonNullAndNotZero(final T recursiveObject,
																  final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndNotZero(recursiveObject, extractNumberFunction, AND);
	}


	public <T, R extends Number> Condition andIsNonNullAndIsZero(final T recursiveObject,
																 final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndIsZero(recursiveObject, extractNumberFunction, AND);
	}
	// Copulativas

	public <T, R> Condition orIsNotNull(final T recusiveObject, final Function<T, R> functionExtract) {

		return this.isNotNull(recusiveObject, functionExtract, OR);
	}


	public <T> Condition orIsEmptyString(final T recursiveObject,
										 final Function<T, String> extractStringFunction) {

		return this.isEmptyString(recursiveObject, extractStringFunction, OR);
	}


	public <T> Condition orIsEmptyCollection(final T recursiveObject,
											 final Function<T, Collection<?>> extractCollectionFunction) {

		return this.isEmptyCollection(recursiveObject, extractCollectionFunction, OR);
	}


	public <T> Condition orIsEmptyMap(final T recursiveObject,
									  final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isEmptyMap(recursiveObject, extractMapFunction, OR);
	}


	public <T> Condition orIsNotEmptyString(final T recursiveObject,
											final Function<T, String> extractStringFunction) {

		return this.isNotEmptyString(recursiveObject, extractStringFunction, OR);
	}


	public <T> Condition orIsNotEmptyCollection(final T recursiveObject,
												final Function<T, Collection<?>> extractCollectionFunction) {

		return this.isNotEmptyCollection(recursiveObject, extractCollectionFunction, OR);
	}


	public <T> Condition orIsNotEmptyMap(final T recursiveObject,
										 final Function<T, Map<?, ?>> extractMapFunction) {

		return this.isNotEmptyMap(recursiveObject, extractMapFunction, OR);
	}


	public <T, R extends Number> Condition orIsNonNullAndNotZero(final T recursiveObject,
																 final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndNotZero(recursiveObject, extractNumberFunction, OR);
	}


	public <T, R extends Number> Condition orIsNonNullAndIsZero(final T recursiveObject,
																final Function<T, R> extractNumberFunction) {

		return this.isNonNullAndIsZero(recursiveObject, extractNumberFunction, OR);
	}


}
