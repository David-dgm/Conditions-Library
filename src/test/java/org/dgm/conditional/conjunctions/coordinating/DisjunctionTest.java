package org.dgm.conditional.conjunctions.coordinating;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.conjunctions.coordinating.ConditionExtractorHelper.extractFirstConditionCreated;
import static org.dgm.conditional.conjunctions.coordinating.ConditionExtractorHelper.extractSecondConditionCreated;
import static org.dgm.conditional.conjunctions.coordinating.ConditionExtractorHelper.extractThirdConditionCreated;
import static org.dgm.conditional.conjunctions.coordinating.Disjunction.DisjunctionSpeciality123.disjunctionConditioning;
import static org.dgm.conditional.model.Condition.condition;
import static org.dgm.conditional.model.Condition.conditionAsCopulative;
import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionOperator.OR;
import static org.dgm.conditional.model.ConditionResult.DENIED;
import static org.dgm.conditional.model.ParameterTestProvider.BOOLEAN_SUPPLIER_FALSE;
import static org.dgm.conditional.model.ParameterTestProvider.BOOLEAN_SUPPLIER_FALSE_EXCEPTION;
import static org.dgm.conditional.model.ParameterTestProvider.BOOLEAN_SUPPLIER_TRUE;
import static org.dgm.conditional.model.ParameterTestProvider.BOOLEAN_SUPPLIER_TRUE_EXCEPTION;
import static org.dgm.conditional.model.ParameterTestProvider.CONDITION_FALSE;
import static org.dgm.conditional.model.ParameterTestProvider.CONDITION_FALSE_EXCEPTION;
import static org.dgm.conditional.model.ParameterTestProvider.CONDITION_TRUE;
import static org.dgm.conditional.model.ParameterTestProvider.CONDITION_TRUE_EXCEPTION;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.dgm.conditional.conjunctions.coordinating.Disjunction.DisjunctionSpeciality123;
import org.dgm.conditional.model.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DisjunctionTest {

	@Test
	void should_return_new_copulative_condition_with_natural_result() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE);

		assertThat(disjunctive123).isNotNull();

		final Condition conditionCreated = extractFirstConditionCreated(disjunctive123);
		assertThat(conditionCreated.isSameOperator(OR)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(disjunctive123.is()).isTrue();
	}

	@Test
	void should_return_new_copulative_condition_with_denied_result() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE, DENIED);

		assertThat(disjunctive123).isNotNull();

		final Condition conditionCreated = extractFirstConditionCreated(disjunctive123);
		assertThat(conditionCreated.isSameOperator(OR)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(disjunctive123.is()).isFalse();
	}

	@Test
	void should_return_true_when_negate_response_from_two_boolean_suppliers_as_true_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_TRUE_EXCEPTION);

		assertThat(disjunctive123.negate()).isFalse();

	}

	@Test
	void should_return_false_when_negate_response_from_two_boolean_suppliers_as_true_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_FALSE_EXCEPTION);

		assertThat(disjunctive123.negate()).isFalse();

	}

	@Test
	void should_return_new_copulative_123_when_condition_is_operator_or() {

		final Condition condition = conditionAsCopulative(BOOLEAN_SUPPLIER_TRUE);

		final DisjunctionSpeciality123 disjunctionConditioning = disjunctionConditioning(condition);

		final Condition conditionCreated = extractFirstConditionCreated(disjunctionConditioning);

		assertThat(conditionCreated.isSameOperator(OR)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(conditionCreated.is()).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_new_disjunction_conditional_123_when_condition_is_not_operator_or() {

		final Condition condition = condition(BOOLEAN_SUPPLIER_TRUE, AND);

		final DisjunctionSpeciality123 disjunctionConditioning = disjunctionConditioning(condition);

		final Condition conditionCreated = extractFirstConditionCreated(disjunctionConditioning);

		assertThat(conditionCreated.isSameOperator(OR)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(conditionCreated.is()).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_when_check_two_boolean_suppliers_as_true_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_TRUE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

	}

	@Test
	void should_return_true_when_check_two_boolean_suppliers_as_true_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_FALSE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_true_when_check_two_boolean_suppliers_as_false_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_FALSE)
			.or(BOOLEAN_SUPPLIER_TRUE);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_check_two_boolean_suppliers_as_false_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_FALSE)
			.or(BOOLEAN_SUPPLIER_FALSE);

		assertThat(disjunctive123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_check_three_boolean_suppliers_as_true_or_true_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_TRUE_EXCEPTION)
			.or(BOOLEAN_SUPPLIER_TRUE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

		final Condition thirdConditionCreated = extractThirdConditionCreated(disjunctive123);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();

	}

	@Test
	void should_return_true_when_check_three_boolean_suppliers_as_true_or_true_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_TRUE_EXCEPTION)
			.or(BOOLEAN_SUPPLIER_FALSE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

		final Condition thirdConditionCreated = extractThirdConditionCreated(disjunctive123);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_true_when_check_three_boolean_suppliers_as_true_or_false_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_FALSE_EXCEPTION)
			.or(BOOLEAN_SUPPLIER_TRUE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

		final Condition thirdConditionCreated = extractThirdConditionCreated(disjunctive123);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_true_when_check_three_boolean_suppliers_as_true_or_false_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE)
			.or(BOOLEAN_SUPPLIER_FALSE_EXCEPTION)
			.or(BOOLEAN_SUPPLIER_FALSE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

		final Condition thirdConditionCreated = extractThirdConditionCreated(disjunctive123);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();

	}


	@Test
	void should_return_true_when_check_two_boolean_suppliers_with_result_denied_as_false_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_FALSE, DENIED)
			.or(BOOLEAN_SUPPLIER_FALSE_EXCEPTION, DENIED);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_true_when_check_two_boolean_suppliers_with_result_denied_as_false_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_FALSE, DENIED)
			.or(BOOLEAN_SUPPLIER_TRUE_EXCEPTION, DENIED);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

	}

	@Test
	void should_return_true_when_check_two_boolean_suppliers_with_result_denied_as_true_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE, DENIED)
			.or(BOOLEAN_SUPPLIER_FALSE, DENIED);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_check_two_boolean_suppliers_with_result_denied_as_true_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(BOOLEAN_SUPPLIER_TRUE, DENIED)
			.or(BOOLEAN_SUPPLIER_TRUE, DENIED);

		assertThat(disjunctive123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();
	}


	@Test
	void should_return_true_when_check_two_conditions_as_true_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(CONDITION_TRUE)
			.or(CONDITION_TRUE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

	}

	@Test
	void should_return_false_when_check_two_conditions_with_result_denied_as_true_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(CONDITION_TRUE)
			.or(CONDITION_FALSE_EXCEPTION);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

	}

	@Test
	void should_return_false_when_check_two_conditions_with_result_denied_as_false_or_true() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(CONDITION_FALSE)
			.or(CONDITION_TRUE);

		assertThat(disjunctive123.is()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_check_two_conditions_with_result_denied_as_false_or_false() {

		final DisjunctionSpeciality123 disjunctive123 = disjunctionConditioning(CONDITION_FALSE)
			.or(CONDITION_FALSE);

		assertThat(disjunctive123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(disjunctive123);
		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();
	}

	// Speciality functions

	@Test
	void should_return_true_to_check_if_an_object_is_null_when_object_is_null() {

		final DisjunctionSpeciality123 conditionIsNullObject = disjunctionConditioning()
			.isNull(null);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNullObject);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNullObject.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_an_object_is_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final DisjunctionSpeciality123 conditionIsNullObject = disjunctionConditioning()
			.isNull(objectNotNull);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNullObject);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNullObject.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_an_object_is_not_null_when_object_is_null() {

		final DisjunctionSpeciality123 conditionIsNotNullObject = disjunctionConditioning()
			.isNotNull(null);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotNullObject);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_an_object_is_not_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final DisjunctionSpeciality123 conditionIsNotNullObject = disjunctionConditioning()
			.isNotNull(objectNotNull);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotNullObject);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_string_is_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final DisjunctionSpeciality123 conditionIsEmptyString = disjunctionConditioning()
			.isEmpty(stringEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyString);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final DisjunctionSpeciality123 conditionIsEmptyString = disjunctionConditioning()
			.isEmpty(stringNonEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyString);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isFalse();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_not_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final DisjunctionSpeciality123 conditionIsNotEmptyString = disjunctionConditioning()
			.isNotEmpty(stringEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyString);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_string_is_not_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final DisjunctionSpeciality123 conditionIsNotEmptyString = disjunctionConditioning()
			.isNotEmpty(stringNonEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyString);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final DisjunctionSpeciality123 conditionIsEmptyCollection = disjunctionConditioning()
			.isEmpty(emptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_collection_is_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final DisjunctionSpeciality123 conditionIsEmptyCollection = disjunctionConditioning()
			.isEmpty(nonEmptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_a_collection_is_not_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final DisjunctionSpeciality123 conditionIsNotEmptyCollection = disjunctionConditioning()
			.isNotEmpty(emptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_not_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final DisjunctionSpeciality123 conditionIsNotEmptyCollection = disjunctionConditioning()
			.isNotEmpty(nonEmptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_map_is_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final DisjunctionSpeciality123 conditionIsEmptyMap = disjunctionConditioning()
			.isEmpty(emptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_map_is_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final DisjunctionSpeciality123 conditionIsEmptyMap = disjunctionConditioning()
			.isEmpty(nonEmptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_a_map_is_not_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final DisjunctionSpeciality123 conditionIsNotEmptyMap = disjunctionConditioning()
			.isNotEmpty(emptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_map_is_not_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final DisjunctionSpeciality123 conditionIsNotEmptyMap = disjunctionConditioning()
			.isNotEmpty(nonEmptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isTrue();

	}

	@Test
	void should_return_true_when_integer_is_not_null_and_not_zero() {

		final Integer integer = 1;

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_integer_is_not_null_but_is_zero() {

		final Integer integer = 0;

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_integer_is_null() {

		final Integer integer = null;

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_long_is_not_null_and_not_zero() {

		final Long longNumber = 1L;

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_long_is_not_null_but_is_zero() {

		final Long longNumber = 0L;

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_long_is_null() {

		final Long longNumber = null;

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_not_zero() {

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(ONE);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_is_zero() {

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(ZERO);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_big_decimal_is_null() {

		final BigDecimal bigDecimal = null;

		final DisjunctionSpeciality123 nonNullAndNotZero = disjunctionConditioning()
			.isNonNullAndNonZero(bigDecimal);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_integer_is_not_null_and_is_zero() {

		final Integer integer = 0;

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_integer_is_not_null_but_not_zero() {

		final Integer integer = 1;

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_integer_is_null_by_is_non_null_and_is_zero_method() {

		final Integer integer = null;

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_long_is_not_null_and_is_zero() {

		final Long longNumber = 0L;

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_long_is_not_null_but_non_zero() {

		final Long longNumber = 1L;

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_long_is_null_by_is_non_null_and_is_zero_method() {

		final Long longNumber = null;

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_is_zero() {

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(ZERO);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_non_zero() {

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(ONE);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_big_decimal_is_null_by_is_non_null_and_is_zero_method() {

		final BigDecimal bigDecimal = null;

		final DisjunctionSpeciality123 nonNullAndIsZero = disjunctionConditioning()
			.isNonNullAndIsZero(bigDecimal);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

}