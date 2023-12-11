package org.dgm.conditional.conjunctions.coordinating;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.conjunctions.coordinating.CopulativeConditionFunctions.copulativeConditionFunctions;
import static org.dgm.conditional.model.ConditionOperator.AND;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.dgm.conditional.model.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CopulativeConditionFunctionsTest {


	private final CopulativeConditionFunctions copulativeFunctions = copulativeConditionFunctions();

	@Test
	void should_return_true_to_check_if_an_object_is_null_when_object_is_null() {

		final Condition conditionIsNullObject = this.copulativeFunctions.isNull(null);

		assertThat(conditionIsNullObject.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNullObject.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNullObject.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_an_object_is_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final Condition conditionIsNullObject = this.copulativeFunctions
			.isNull(objectNotNull);

		assertThat(conditionIsNullObject.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNullObject.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNullObject.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_an_object_is_not_null_when_object_is_null() {

		final Condition conditionIsNotNullObject = this.copulativeFunctions
			.isNotNull(null);

		assertThat(conditionIsNotNullObject.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotNullObject.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_an_object_is_not_null_when_object_is_not_null() {

		final Object objectNotNull = new Object();

		final Condition conditionIsNotNullObject = this.copulativeFunctions
			.isNotNull(objectNotNull);

		assertThat(conditionIsNotNullObject.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotNullObject.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotNullObject.is()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_string_is_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final Condition conditionIsEmptyString = this.copulativeFunctions
			.isEmpty(stringEmpty);

		assertThat(conditionIsEmptyString.isSameOperator(AND)).isTrue();
		assertThat(conditionIsEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final Condition conditionIsEmptyString = this.copulativeFunctions
			.isEmpty(stringNonEmpty);

		assertThat(conditionIsEmptyString.isSameOperator(AND)).isTrue();
		assertThat(conditionIsEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyString.is()).isFalse();

	}

	@Test
	void should_return_false_to_check_if_a_string_is_not_empty_when_string_is_empty() {

		final String stringEmpty = "";

		final Condition conditionIsNotEmptyString = this.copulativeFunctions
			.isNotEmpty(stringEmpty);

		assertThat(conditionIsNotEmptyString.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_string_is_not_empty_when_string_is_not_empty() {

		final String stringNonEmpty = "NotEmpty";

		final Condition conditionIsNotEmptyString = this.copulativeFunctions
			.isNotEmpty(stringNonEmpty);

		assertThat(conditionIsNotEmptyString.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotEmptyString.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyString.is()).isTrue();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final Condition conditionIsEmptyCollection = this.copulativeFunctions
			.isEmpty(emptyCollection);

		assertThat(conditionIsEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(conditionIsEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_collection_is_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final Condition conditionIsEmptyCollection = this.copulativeFunctions
			.isEmpty(nonEmptyCollection);

		assertThat(conditionIsEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(conditionIsEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyCollection.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_a_collection_is_not_empty_when_collection_is_empty() {

		final List<String> emptyCollection = List.of();

		final Condition conditionIsNotEmptyCollection = this.copulativeFunctions
			.isNotEmpty(emptyCollection);

		assertThat(conditionIsNotEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_collection_is_not_empty_when_collection_is_not_empty() {

		final List<String> nonEmptyCollection = List.of("NotEmpty");

		final Condition conditionIsNotEmptyCollection = this.copulativeFunctions
			.isNotEmpty(nonEmptyCollection);

		assertThat(conditionIsNotEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotEmptyCollection.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyCollection.is()).isTrue();

	}

	@Test
	void should_return_true_to_check_if_a_map_is_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final Condition conditionIsEmptyMap = this.copulativeFunctions
			.isEmpty(emptyMap);

		assertThat(conditionIsEmptyMap.isSameOperator(AND)).isTrue();
		assertThat(conditionIsEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isTrue();

	}

	@Test
	void should_return_false_to_check_if_a_map_is_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final Condition conditionIsEmptyMap = this.copulativeFunctions
			.isEmpty(nonEmptyMap);

		assertThat(conditionIsEmptyMap.isSameOperator(AND)).isTrue();
		assertThat(conditionIsEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsEmptyMap.is()).isFalse();
	}

	@Test
	void should_return_false_to_check_if_a_map_is_not_empty_when_map_is_empty() {

		final Map<String, String> emptyMap = Map.of();

		final Condition conditionIsNotEmptyMap = this.copulativeFunctions
			.isNotEmpty(emptyMap);

		assertThat(conditionIsNotEmptyMap.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isFalse();
	}

	@Test
	void should_return_true_to_check_if_a_map_is_not_empty_when_map_is_not_empty() {

		final Map<String, String> nonEmptyMap = Map.of("NotEmptyKey", "NotEmptyValue");

		final Condition conditionIsNotEmptyMap = this.copulativeFunctions
			.isNotEmpty(nonEmptyMap);

		assertThat(conditionIsNotEmptyMap.isSameOperator(AND)).isTrue();
		assertThat(conditionIsNotEmptyMap.isAlreadyLoaded()).isFalse();

		assertThat(conditionIsNotEmptyMap.is()).isTrue();

	}

	@Test
	void should_return_true_when_integer_is_not_null_and_not_zero() {

		final Integer integer = 1;

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(integer);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_integer_is_not_null_but_is_zero() {

		final Integer integer = 0;

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(integer);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_integer_is_null() {

		final Integer integer = null;

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(integer);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_long_is_not_null_and_not_zero() {

		final Long longNumber = 1L;

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(longNumber);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_long_is_not_null_but_is_zero() {

		final Long longNumber = 0L;

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(longNumber);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_long_is_null() {

		final Long longNumber = null;

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(longNumber);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_not_zero() {

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(ONE);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_is_zero() {

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(ZERO);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_big_decimal_is_null() {

		final BigDecimal bigDecimal = null;

		final Condition nonNullAndNotZero = this.copulativeFunctions
			.isNonNullAndNotZero(bigDecimal);

		assertThat(nonNullAndNotZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndNotZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_integer_is_not_null_and_is_zero() {

		final Integer integer = 0;

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(integer);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_integer_is_not_null_but_not_zero() {

		final Integer integer = 1;

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(integer);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_integer_is_null_by_is_non_null_and_is_zero_method() {

		final Integer integer = null;

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(integer);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_long_is_not_null_and_is_zero() {

		final Long longNumber = 0L;

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(longNumber);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_long_is_not_null_but_non_zero() {

		final Long longNumber = 1L;

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(longNumber);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_long_is_null_by_is_non_null_and_is_zero_method() {

		final Long longNumber = null;

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(longNumber);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

	@Test
	void should_return_true_when_big_decimal_is_not_null_and_is_zero() {

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(ZERO);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isTrue();

	}

	@Test
	void should_return_false_when_big_decimal_is_not_null_but_non_zero() {

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(ONE);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();
	}

	@Test
	void should_return_false_when_big_decimal_is_null_by_is_non_null_and_is_zero_method() {

		final BigDecimal bigDecimal = null;

		final Condition nonNullAndIsZero = this.copulativeFunctions
			.isNonNullAndIsZero(bigDecimal);

		assertThat(nonNullAndIsZero.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndIsZero.isAlreadyLoaded()).isFalse();

		assertThat(nonNullAndIsZero.is()).isFalse();

	}

}