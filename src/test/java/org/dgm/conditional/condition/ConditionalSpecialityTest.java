package org.dgm.conditional.condition;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.model.ConditionOperator.AND;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.dgm.conditional.model.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConditionalSpecialityTest {

	@Test
	void should_return_true_to_check_if_an_object_is_null_when_object_is_null() {

		final Condition conditionIsNullObject = ConditionalSpeciality.isNull(null, AND);

		assertThat(conditionIsNullObject.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNullObject.is()).isTrue();
		assertThat(conditionIsNullObject.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_to_check_if_an_object_is_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final Condition conditionIsNullObject = ConditionalSpeciality.isNull(objectNotNull, AND);

		assertThat(conditionIsNullObject.isAlreadyLoaded()).isFalse();
		
		assertThat(conditionIsNullObject.is()).isFalse();
		assertThat(conditionIsNullObject.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_to_check_if_an_object_is_not_null_when_object_is_null() {

		final Condition conditionIsNotNullObject = ConditionalSpeciality.isNotNull(null, AND);

		assertThat(conditionIsNotNullObject.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isFalse();
		assertThat(conditionIsNotNullObject.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_an_object_is_not_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final Condition conditionIsNotNullObject = ConditionalSpeciality.isNotNull(objectNotNull, AND);

		assertThat(conditionIsNotNullObject.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isTrue();
		assertThat(conditionIsNotNullObject.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_string_is_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final Condition conditionIsEmptyString = ConditionalSpeciality.isEmpty(stringEmpty, AND);

		assertThat(conditionIsEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isTrue();
		assertThat(conditionIsEmptyString.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final Condition conditionIsEmptyString = ConditionalSpeciality.isEmpty(stringNonEmpty, AND);

		assertThat(conditionIsEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isFalse();
		assertThat(conditionIsEmptyString.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_not_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final Condition conditionIsNotEmptyString = ConditionalSpeciality.isNotEmpty(stringEmpty, AND);

		assertThat(conditionIsNotEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isFalse();
		assertThat(conditionIsNotEmptyString.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_a_string_is_not_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final Condition conditionIsNotEmptyString = ConditionalSpeciality.isNotEmpty(stringNonEmpty, AND);

		assertThat(conditionIsNotEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isTrue();
		assertThat(conditionIsNotEmptyString.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final Condition conditionIsEmptyCollection = ConditionalSpeciality.isEmpty(emptyCollection, AND);

		assertThat(conditionIsEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isTrue();
		assertThat(conditionIsEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_to_check_if_a_collection_is_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final Condition conditionIsEmptyCollection = ConditionalSpeciality.isEmpty(nonEmptyCollection, AND);

		assertThat(conditionIsEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isFalse();
		assertThat(conditionIsEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_to_check_if_a_collection_is_not_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final Condition conditionIsNotEmptyCollection = ConditionalSpeciality.isNotEmpty(emptyCollection, AND);

		assertThat(conditionIsNotEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isFalse();
		assertThat(conditionIsNotEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_not_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final Condition conditionIsNotEmptyCollection = ConditionalSpeciality.isNotEmpty(nonEmptyCollection, AND);

		assertThat(conditionIsNotEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isTrue();
		assertThat(conditionIsNotEmptyCollection.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_map_is_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final Condition conditionIsEmptyMap = ConditionalSpeciality.isEmpty(emptyMap, AND);

		assertThat(conditionIsEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isTrue();
		assertThat(conditionIsEmptyMap.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_to_check_if_a_map_is_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final Condition conditionIsEmptyMap = ConditionalSpeciality.isEmpty(nonEmptyMap, AND);

		assertThat(conditionIsEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isFalse();
		assertThat(conditionIsEmptyMap.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_to_check_if_a_map_is_not_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final Condition conditionIsNotEmptyMap = ConditionalSpeciality.isNotEmpty(emptyMap, AND);

		assertThat(conditionIsNotEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isFalse();
		assertThat(conditionIsNotEmptyMap.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_a_map_is_not_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final Condition conditionIsNotEmptyMap = ConditionalSpeciality.isNotEmpty(nonEmptyMap, AND);

		assertThat(conditionIsNotEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isTrue();
		assertThat(conditionIsNotEmptyMap.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_integer_is_not_null_and_not_zero() {

		final Integer integer = 1;

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(integer, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_integer_is_not_null_but_is_zero() {

		final Integer integer = 0;

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(integer, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_integer_is_null() {

		final Integer integer = null;

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(integer, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_long_is_not_null_and_not_zero() {

		final Long longNumber = 1L;

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(longNumber, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_long_is_not_null_but_is_zero() {

		final Long longNumber = 0L;

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(longNumber, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_long_is_null() {

		final Long longNumber = null;

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(longNumber, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_not_zero() {

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(ONE, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_is_zero() {

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(ZERO, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_big_decimal_is_null() {

		final BigDecimal bigDecimal = null;

		final Condition nonNullAndNotZero = ConditionalSpeciality.isNonNullAndNotZero(bigDecimal, AND);

		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_integer_is_not_null_and_is_zero() {

		final Integer integer = 0;

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(integer, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_integer_is_not_null_but_not_zero() {

		final Integer integer = 1;

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(integer, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_integer_is_null_by_is_non_null_and_is_zero_method() {

		final Integer integer = null;

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(integer, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_long_is_not_null_and_is_zero() {

		final Long longNumber = 0L;

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(longNumber, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_long_is_not_null_but_non_zero() {

		final Long longNumber = 1L;

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(longNumber, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_long_is_null_by_is_non_null_and_is_zero_method() {

		final Long longNumber = null;

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(longNumber, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_is_zero() {

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(ZERO, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_non_zero() {

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(ONE, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_big_decimal_is_null_by_is_non_null_and_is_zero_method() {

		final BigDecimal bigDecimal = null;

		final Condition nonNullAndIsZero = ConditionalSpeciality.isNonNullAndIsZero(bigDecimal, AND);

		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndIsZero.is()).isFalse();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isTrue();

	}
}