package org.dgm.conditional.conjunctions.coordinating;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.conjunctions.coordinating.Copulative.CopulativeSpeciality123.copulativeConditioning;
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
import static org.dgm.conditional.utils.ConditionExtractorHelper.extractFirstConditionCreated;
import static org.dgm.conditional.utils.ConditionExtractorHelper.extractSecondConditionCreated;
import static org.dgm.conditional.utils.ConditionExtractorHelper.extractThirdConditionCreated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.dgm.conditional.conjunctions.coordinating.Copulative.CopulativeSpeciality123;
import org.dgm.conditional.model.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CopulativeTest {


	@Test
	void should_return_new_copulative_condition_with_natural_result() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE);

		assertThat(copulative123).isNotNull();
		assertThat(copulative123.is()).isTrue();
	}

	@Test
	void should_return_new_copulative_condition_with_denied_result() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE, DENIED);

		assertThat(copulative123).isNotNull();
		assertThat(copulative123.is()).isFalse();
	}

	@Test
	void should_return_true_when_negate_response_from_two_boolean_suppliers_as_true_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_TRUE);

		assertThat(copulative123.negate()).isFalse();

	}

	@Test
	void should_return_true_when_negate_response_from_two_boolean_suppliers_as_true_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_FALSE);

		assertThat(copulative123.negate()).isTrue();

	}

	@Test
	void should_return_new_copulative_123_when_condition_is_operator_and() {

		final Condition condition = conditionAsCopulative(BOOLEAN_SUPPLIER_TRUE);

		final CopulativeSpeciality123 copulativeConditioning = copulativeConditioning(condition);

		final Condition conditionCreated = extractFirstConditionCreated(copulativeConditioning);

		assertThat(conditionCreated.isSameOperator(AND)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(conditionCreated.is()).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_new_conditional_123_when_condition_is_not_operator_and() {

		final Condition condition = condition(BOOLEAN_SUPPLIER_TRUE, OR);

		final CopulativeSpeciality123 copulativeConditioning = copulativeConditioning(condition);

		final Condition conditionCreated = extractFirstConditionCreated(copulativeConditioning);

		assertThat(conditionCreated.isSameOperator(AND)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(conditionCreated.is()).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_when_check_two_boolean_suppliers_as_true_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_TRUE);

		assertThat(copulative123.is()).isTrue();

	}

	@Test
	void should_return_false_when_check_two_boolean_suppliers_as_true_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_FALSE);

		assertThat(copulative123.is()).isFalse();

	}

	@Test
	void should_return_false_when_check_two_boolean_suppliers_as_false_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_FALSE)
			.and(BOOLEAN_SUPPLIER_TRUE_EXCEPTION);

		assertThat(copulative123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(copulative123);
		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_true_when_check_three_boolean_suppliers_as_true_and_true_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_TRUE);

		assertThat(copulative123.is()).isTrue();

	}

	@Test
	void should_return_false_when_check_three_boolean_suppliers_as_true_and_true_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_FALSE);

		assertThat(copulative123.is()).isFalse();

	}

	@Test
	void should_return_false_when_check_three_boolean_suppliers_as_true_and_false_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_FALSE)
			.and(BOOLEAN_SUPPLIER_TRUE_EXCEPTION);

		assertThat(copulative123.is()).isFalse();

		final Condition secondConditionCreated = extractThirdConditionCreated(copulative123);
		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_false_when_check_three_boolean_suppliers_as_true_and_false_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE)
			.and(BOOLEAN_SUPPLIER_FALSE)
			.and(BOOLEAN_SUPPLIER_FALSE_EXCEPTION);

		assertThat(copulative123.is()).isFalse();

		final Condition secondConditionCreated = extractThirdConditionCreated(copulative123);
		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

	}


	@Test
	void should_return_true_when_check_two_boolean_suppliers_with_result_denied_as_false_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_FALSE, DENIED)
			.and(BOOLEAN_SUPPLIER_FALSE, DENIED);

		assertThat(copulative123.is()).isTrue();

	}

	@Test
	void should_return_false_when_check_two_boolean_suppliers_with_result_denied_as_false_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_FALSE, DENIED)
			.and(BOOLEAN_SUPPLIER_TRUE, DENIED);

		assertThat(copulative123.is()).isFalse();

	}

	@Test
	void should_return_false_when_check_two_boolean_suppliers_with_result_denied_as_true_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE, DENIED)
			.and(BOOLEAN_SUPPLIER_FALSE_EXCEPTION, DENIED);

		assertThat(copulative123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(copulative123);
		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_false_when_check_two_boolean_suppliers_with_result_denied_as_true_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(BOOLEAN_SUPPLIER_TRUE, DENIED)
			.and(BOOLEAN_SUPPLIER_TRUE_EXCEPTION, DENIED);

		assertThat(copulative123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(copulative123);
		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}


	@Test
	void should_return_true_when_check_two_conditions_as_true_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(CONDITION_TRUE)
			.and(CONDITION_TRUE);

		assertThat(copulative123.is()).isTrue();

	}

	@Test
	void should_return_false_when_check_two_conditions_with_result_denied_as_true_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(CONDITION_TRUE)
			.and(CONDITION_FALSE);

		assertThat(copulative123.is()).isFalse();

	}

	@Test
	void should_return_false_when_check_two_conditions_with_result_denied_as_false_and_true() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(CONDITION_FALSE)
			.and(CONDITION_TRUE_EXCEPTION);

		assertThat(copulative123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(copulative123);
		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}

	@Test
	void should_return_false_when_check_two_conditions_with_result_denied_as_false_and_false() {

		final CopulativeSpeciality123 copulative123 = copulativeConditioning(CONDITION_FALSE)
			.and(CONDITION_FALSE_EXCEPTION);

		assertThat(copulative123.is()).isFalse();

		final Condition secondConditionCreated = extractSecondConditionCreated(copulative123);
		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
	}

	// Speciality functions

	@Test
	void should_return_true_to_check_if_an_object_is_null_when_object_is_null() {

		final CopulativeSpeciality123 conditionIsNullObject = copulativeConditioning()
			.isNull(null);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNullObject);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNullObject.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_an_object_is_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final CopulativeSpeciality123 conditionIsNullObject = copulativeConditioning()
			.isNull(objectNotNull);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNullObject);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNullObject.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_an_object_is_not_null_when_object_is_null() {

		final CopulativeSpeciality123 conditionIsNotNullObject = copulativeConditioning()
			.isNotNull(null);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotNullObject);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_an_object_is_not_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final CopulativeSpeciality123 conditionIsNotNullObject = copulativeConditioning()
			.isNotNull(objectNotNull);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotNullObject);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_string_is_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final CopulativeSpeciality123 conditionIsEmptyString = copulativeConditioning()
			.isEmpty(stringEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyString);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final CopulativeSpeciality123 conditionIsEmptyString = copulativeConditioning()
			.isEmpty(stringNonEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyString);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isFalse();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_not_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final CopulativeSpeciality123 conditionIsNotEmptyString = copulativeConditioning()
			.isNotEmpty(stringEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyString);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_string_is_not_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final CopulativeSpeciality123 conditionIsNotEmptyString = copulativeConditioning()
			.isNotEmpty(stringNonEmpty);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyString);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final CopulativeSpeciality123 conditionIsEmptyCollection = copulativeConditioning()
			.isEmpty(emptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_collection_is_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final CopulativeSpeciality123 conditionIsEmptyCollection = copulativeConditioning()
			.isEmpty(nonEmptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_a_collection_is_not_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final CopulativeSpeciality123 conditionIsNotEmptyCollection = copulativeConditioning()
			.isNotEmpty(emptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_not_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final CopulativeSpeciality123 conditionIsNotEmptyCollection = copulativeConditioning()
			.isNotEmpty(nonEmptyCollection);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyCollection);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_map_is_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final CopulativeSpeciality123 conditionIsEmptyMap = copulativeConditioning()
			.isEmpty(emptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_map_is_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final CopulativeSpeciality123 conditionIsEmptyMap = copulativeConditioning()
			.isEmpty(nonEmptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_a_map_is_not_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final CopulativeSpeciality123 conditionIsNotEmptyMap = copulativeConditioning()
			.isNotEmpty(emptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_map_is_not_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final CopulativeSpeciality123 conditionIsNotEmptyMap = copulativeConditioning()
			.isNotEmpty(nonEmptyMap);

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionIsNotEmptyMap);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isTrue();

	}

	@Test
	void should_return_true_when_integer_is_not_null_and_not_zero() {

		final Integer integer = 1;

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_integer_is_not_null_but_is_zero() {

		final Integer integer = 0;

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_integer_is_null() {

		final Integer integer = null;

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_long_is_not_null_and_not_zero() {

		final Long longNumber = 1L;

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_long_is_not_null_but_is_zero() {

		final Long longNumber = 0L;

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_long_is_null() {

		final Long longNumber = null;

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_not_zero() {

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(ONE);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_is_zero() {

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(ZERO);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_big_decimal_is_null() {

		final BigDecimal bigDecimal = null;

		final CopulativeSpeciality123 nonNullAndNotZero = copulativeConditioning()
			.isNonNullAndNonZero(bigDecimal);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndNotZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_integer_is_not_null_and_is_zero() {

		final Integer integer = 0;

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_integer_is_not_null_but_not_zero() {

		final Integer integer = 1;

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_integer_is_null_by_is_non_null_and_is_zero_method() {

		final Integer integer = null;

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(integer);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_long_is_not_null_and_is_zero() {

		final Long longNumber = 0L;

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_long_is_not_null_but_non_zero() {

		final Long longNumber = 1L;

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_long_is_null_by_is_non_null_and_is_zero_method() {

		final Long longNumber = null;

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(longNumber);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_is_zero() {

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(ZERO);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_non_zero() {

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(ONE);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_big_decimal_is_null_by_is_non_null_and_is_zero_method() {

		final BigDecimal bigDecimal = null;

		final CopulativeSpeciality123 nonNullAndIsZero = copulativeConditioning()
			.isNonNullAndIsZero(bigDecimal);

		final Condition firstConditionCreated = extractFirstConditionCreated(nonNullAndIsZero);
		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

}