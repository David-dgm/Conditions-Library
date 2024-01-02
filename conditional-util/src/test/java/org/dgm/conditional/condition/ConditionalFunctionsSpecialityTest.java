package org.dgm.conditional.condition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.model.ConditionOperator.AND;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.recursive.model.FirstLevelObject;
import org.dgm.conditional.recursive.model.SecondLevelObject;
import org.dgm.conditional.recursive.model.ThirdLevelObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConditionalFunctionsSpecialityTest {


	private static final String EMPTY_STRING = "";
	private static final String NOT_EMTPY_STRING = "Not Emtpy";
	private static final List<String> NOT_EMTPY_LIST = List.of(NOT_EMTPY_STRING);
	private static final Set<String> NOT_EMTPY_SET = Set.of(NOT_EMTPY_STRING);
	private static final Map<String, String> NOT_EMTPY_MAP = Map.of(NOT_EMTPY_STRING, NOT_EMTPY_STRING);


	@Test
	void should_return_false_when_object_to_inspect_is_null_object() {

		final FirstLevelObject firstLevelObject = FirstLevelObject.builder().build();

		final Condition notNullCondition = ConditionalFunctionsSpeciality
			.isNotNull(firstLevelObject, FirstLevelObject::getSecondLevelObjectInside, AND);

		assertThat(notNullCondition).isNotNull();

		assertThat(notNullCondition.isSameOperator(AND)).isTrue();
		assertThat(notNullCondition.isAlreadyLoaded()).isFalse();
		assertThat(notNullCondition.is()).isFalse();
		assertThat(notNullCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_object_to_inspect_is_inside() {

		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.build();
		final FirstLevelObject firstLevelObject = FirstLevelObject.builder()
			.secondLevelObjectInside(secondLevelObject)
			.build();

		final Condition notNullCondition = ConditionalFunctionsSpeciality
			.isNotNull(firstLevelObject, FirstLevelObject::getSecondLevelObjectInside, AND);

		assertThat(notNullCondition).isNotNull();

		assertThat(notNullCondition.isSameOperator(AND)).isTrue();
		assertThat(notNullCondition.isAlreadyLoaded()).isFalse();
		assertThat(notNullCondition.is()).isTrue();
		assertThat(notNullCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_object_to_inspect_is_null_object() {

		final FirstLevelObject firstLevelObject = FirstLevelObject.builder().build();

		final Condition notNullCondition = ConditionalFunctionsSpeciality
			.isNull(firstLevelObject, FirstLevelObject::getSecondLevelObjectInside, AND);

		assertThat(notNullCondition).isNotNull();

		assertThat(notNullCondition.isSameOperator(AND)).isTrue();
		assertThat(notNullCondition.isAlreadyLoaded()).isFalse();
		assertThat(notNullCondition.is()).isTrue();
		assertThat(notNullCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_object_to_inspect_is_inside() {

		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.build();
		final FirstLevelObject firstLevelObject = FirstLevelObject.builder()
			.secondLevelObjectInside(secondLevelObject)
			.build();

		final Condition notNullCondition = ConditionalFunctionsSpeciality
			.isNull(firstLevelObject, FirstLevelObject::getSecondLevelObjectInside, AND);

		assertThat(notNullCondition).isNotNull();

		assertThat(notNullCondition.isSameOperator(AND)).isTrue();
		assertThat(notNullCondition.isAlreadyLoaded()).isFalse();
		assertThat(notNullCondition.is()).isFalse();
		assertThat(notNullCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_string_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(NOT_EMTPY_STRING)
			.build();

		final Condition notEmptyString = ConditionalFunctionsSpeciality
			.isNotEmptyString(thirdLevelObject, ThirdLevelObject::getStringValue, AND);

		assertThat(notEmptyString).isNotNull();

		assertThat(notEmptyString.isSameOperator(AND)).isTrue();
		assertThat(notEmptyString.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyString.is()).isTrue();
		assertThat(notEmptyString.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_string_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(EMPTY_STRING)
			.build();

		final Condition notEmptyStringCondition = ConditionalFunctionsSpeciality
			.isNotEmptyString(thirdLevelObject, ThirdLevelObject::getStringValue, AND);

		assertThat(notEmptyStringCondition).isNotNull();

		assertThat(notEmptyStringCondition.isSameOperator(AND)).isTrue();
		assertThat(notEmptyStringCondition.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyStringCondition.is()).isFalse();
		assertThat(notEmptyStringCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_string_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(null)
			.build();

		final Condition notEmptyStringCondition = ConditionalFunctionsSpeciality
			.isNotEmptyString(thirdLevelObject, ThirdLevelObject::getStringValue, AND);

		assertThat(notEmptyStringCondition).isNotNull();

		assertThat(notEmptyStringCondition.isSameOperator(AND)).isTrue();
		assertThat(notEmptyStringCondition.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyStringCondition.is()).isFalse();
		assertThat(notEmptyStringCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_string_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(NOT_EMTPY_STRING)
			.build();

		final Condition emptyStringCondition = ConditionalFunctionsSpeciality
			.isEmptyString(thirdLevelObject, ThirdLevelObject::getStringValue, AND);

		assertThat(emptyStringCondition).isNotNull();

		assertThat(emptyStringCondition.isSameOperator(AND)).isTrue();
		assertThat(emptyStringCondition.isAlreadyLoaded()).isFalse();
		assertThat(emptyStringCondition.is()).isFalse();
		assertThat(emptyStringCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_string_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(EMPTY_STRING)
			.build();

		final Condition emptyStringCondition = ConditionalFunctionsSpeciality
			.isEmptyString(thirdLevelObject, ThirdLevelObject::getStringValue, AND);

		assertThat(emptyStringCondition).isNotNull();

		assertThat(emptyStringCondition.isSameOperator(AND)).isTrue();
		assertThat(emptyStringCondition.isAlreadyLoaded()).isFalse();
		assertThat(emptyStringCondition.is()).isTrue();
		assertThat(emptyStringCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_string_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(null)
			.build();

		final Condition emptyStringCondition = ConditionalFunctionsSpeciality
			.isEmptyString(thirdLevelObject, ThirdLevelObject::getStringValue, AND);

		assertThat(emptyStringCondition).isNotNull();

		assertThat(emptyStringCondition.isSameOperator(AND)).isTrue();
		assertThat(emptyStringCondition.isAlreadyLoaded()).isFalse();
		assertThat(emptyStringCondition.is()).isTrue();
		assertThat(emptyStringCondition.isAlreadyLoaded()).isTrue();
	}


	@Test
	void should_return_true_when_collection_attribute_is_not_empty_list() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(NOT_EMTPY_LIST)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isNotEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_collection_attribute_is_not_empty_set() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.setValue(NOT_EMTPY_SET)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isNotEmptyCollection(thirdLevelObject, ThirdLevelObject::getSetValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_collection_attribute_is_empty_list() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(List.of())
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isNotEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isFalse();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_false_when_collection_attribute_is_empty_set() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(List.of())
			.setValue(Set.of())
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isNotEmptyCollection(thirdLevelObject, ThirdLevelObject::getSetValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isFalse();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_collection_attribute_is_null_list() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(null)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isNotEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isFalse();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_false_when_collection_attribute_is_null_set() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.setValue(null)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isNotEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isFalse();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();

	}


	@Test
	void should_return_false_when_collection_attribute_is_not_empty_list() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(NOT_EMTPY_LIST)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isFalse();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_collection_attribute_is_not_empty_set() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.setValue(NOT_EMTPY_SET)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isEmptyCollection(thirdLevelObject, ThirdLevelObject::getSetValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isFalse();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_collection_attribute_is_empty_list() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(List.of())
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_when_collection_attribute_is_empty_set() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.setValue(Set.of())
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isEmptyCollection(thirdLevelObject, ThirdLevelObject::getSetValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_collection_attribute_is_null_list() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(null)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_when_collection_attribute_is_null_set() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.setValue(null)
			.build();

		final Condition notEmptyCollection = ConditionalFunctionsSpeciality
			.isEmptyCollection(thirdLevelObject, ThirdLevelObject::getListValue, AND);

		assertThat(notEmptyCollection).isNotNull();

		assertThat(notEmptyCollection.isSameOperator(AND)).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyCollection.is()).isTrue();
		assertThat(notEmptyCollection.isAlreadyLoaded()).isTrue();

	}


	@Test
	void should_return_true_when_map_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(NOT_EMTPY_MAP)
			.build();

		final Condition notEmptyMap = ConditionalFunctionsSpeciality
			.isNotEmptyMap(thirdLevelObject, ThirdLevelObject::getMapValue, AND);

		assertThat(notEmptyMap).isNotNull();

		assertThat(notEmptyMap.isSameOperator(AND)).isTrue();
		assertThat(notEmptyMap.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyMap.is()).isTrue();
		assertThat(notEmptyMap.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_map_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(Map.of())
			.build();

		final Condition notEmptyMap = ConditionalFunctionsSpeciality
			.isNotEmptyMap(thirdLevelObject, ThirdLevelObject::getMapValue, AND);

		assertThat(notEmptyMap).isNotNull();

		assertThat(notEmptyMap.isSameOperator(AND)).isTrue();
		assertThat(notEmptyMap.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyMap.is()).isFalse();
		assertThat(notEmptyMap.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_false_when_map_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(null)
			.build();

		final Condition notEmptyMap = ConditionalFunctionsSpeciality
			.isNotEmptyMap(thirdLevelObject, ThirdLevelObject::getMapValue, AND);

		assertThat(notEmptyMap).isNotNull();

		assertThat(notEmptyMap.isSameOperator(AND)).isTrue();
		assertThat(notEmptyMap.isAlreadyLoaded()).isFalse();
		assertThat(notEmptyMap.is()).isFalse();
		assertThat(notEmptyMap.isAlreadyLoaded()).isTrue();

	}


	@Test
	void should_return_false_when_map_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(NOT_EMTPY_MAP)
			.build();

		final Condition emptyMapCondition = ConditionalFunctionsSpeciality
			.isEmptyMap(thirdLevelObject, ThirdLevelObject::getMapValue, AND);

		assertThat(emptyMapCondition).isNotNull();

		assertThat(emptyMapCondition.isSameOperator(AND)).isTrue();
		assertThat(emptyMapCondition.isAlreadyLoaded()).isFalse();
		assertThat(emptyMapCondition.is()).isFalse();
		assertThat(emptyMapCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(Map.of())
			.build();

		final Condition emptyMapCondition = ConditionalFunctionsSpeciality
			.isEmptyMap(thirdLevelObject, ThirdLevelObject::getMapValue, AND);

		assertThat(emptyMapCondition).isNotNull();

		assertThat(emptyMapCondition.isSameOperator(AND)).isTrue();
		assertThat(emptyMapCondition.isAlreadyLoaded()).isFalse();
		assertThat(emptyMapCondition.is()).isTrue();
		assertThat(emptyMapCondition.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_when_map_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(null)
			.build();

		final Condition emptyMapCondition = ConditionalFunctionsSpeciality
			.isEmptyMap(thirdLevelObject, ThirdLevelObject::getMapValue, AND);

		assertThat(emptyMapCondition).isNotNull();

		assertThat(emptyMapCondition.isSameOperator(AND)).isTrue();
		assertThat(emptyMapCondition.isAlreadyLoaded()).isFalse();
		assertThat(emptyMapCondition.is()).isTrue();
		assertThat(emptyMapCondition.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_and_not_zero_integer() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(1)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getIntegerValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_and_not_zero_long() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.longValue(1L)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getLongValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_and_not_zero_big_decimal() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.bigDecimalValue(BigDecimal.ONE)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getBigDecimalValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_but_is_zero_integer() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(0)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getLongValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_but_is_zero_long() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.longValue(0L)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getLongValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_but_is_zero_big_decimal() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.bigDecimalValue(BigDecimal.ZERO)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getBigDecimalValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_and_is_integer_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(null)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getIntegerValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_and_is_long_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.longValue(null)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getLongValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_and_is_big_decimal_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.bigDecimalValue(null)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getBigDecimalValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_and_not_zero_integer() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(1)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getIntegerValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_and_not_zero_long() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.longValue(1L)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getLongValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_and_not_zero_big_decimal() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.bigDecimalValue(BigDecimal.ONE)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getBigDecimalValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_but_is_zero_integer() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(0)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getIntegerValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_but_is_zero_long() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.longValue(0L)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getLongValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_but_is_zero_big_decimal() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.bigDecimalValue(BigDecimal.ZERO)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getBigDecimalValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_integer_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(null)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getIntegerValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_long_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.longValue(null)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndIsZero(thirdLevelObject, ThirdLevelObject::getLongValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_big_decimal_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.bigDecimalValue(null)
			.build();

		final Condition nonNullAndNotZeroCondition = ConditionalFunctionsSpeciality
			.isNonNullAndNotZero(thirdLevelObject, ThirdLevelObject::getBigDecimalValue, AND);

		assertThat(nonNullAndNotZeroCondition).isNotNull();

		assertThat(nonNullAndNotZeroCondition.isSameOperator(AND)).isTrue();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isFalse();
		assertThat(nonNullAndNotZeroCondition.is()).isFalse();
		assertThat(nonNullAndNotZeroCondition.isAlreadyLoaded()).isTrue();
	}

}