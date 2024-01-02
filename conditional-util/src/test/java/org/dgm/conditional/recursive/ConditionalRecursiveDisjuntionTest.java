package org.dgm.conditional.recursive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.conjunctions.coordinating.ConditionExtractorHelper.extractFirstConditionCreated;
import static org.dgm.conditional.conjunctions.coordinating.ConditionExtractorHelper.extractSecondConditionCreated;
import static org.dgm.conditional.conjunctions.coordinating.ConditionExtractorHelper.extractThirdConditionCreated;
import static org.dgm.conditional.model.ConditionOperator.OR;

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
class ConditionalRecursiveDisjuntionTest {


	private static final String EMPTY_STRING = "";
	private static final String NOT_EMTPY_STRING = "Not Emtpy";
	private static final List<String> NOT_EMTPY_LIST = List.of(NOT_EMTPY_STRING);
	private static final Set<String> NOT_EMTPY_SET = Set.of(NOT_EMTPY_STRING);
	private static final Map<String, String> NOT_EMTPY_MAP = Map.of(NOT_EMTPY_STRING, NOT_EMTPY_STRING);

	@Test
	void should_return_new_recursive_conditional() {

		final FirstLevelObject firstLevelObject = FirstLevelObject.builder().build();

		final ConditionalRecursive<FirstLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(firstLevelObject);

		assertThat(conditionalRecursive).isNotNull();
	}

	@Test
	void should_return_true_when_is_not_null_condition_evaluate_an_object() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(1)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotNull(ThirdLevelObject::getIntegerValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_or_condition_to_inspect_object_are_false_response() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotNull(ThirdLevelObject::getIntegerValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_string_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(NOT_EMTPY_STRING)
			.integerValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyString(ThirdLevelObject::getStringValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_string_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(EMPTY_STRING)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyString(ThirdLevelObject::getStringValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_string_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyString(ThirdLevelObject::getStringValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}


	@Test
	void should_return_false_when_string_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(NOT_EMTPY_STRING)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyString(ThirdLevelObject::getStringValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_string_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(EMPTY_STRING)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyString(ThirdLevelObject::getStringValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_string_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.andIsNotNull(ThirdLevelObject::getStringValue)
			.orIsEmptyString(ThirdLevelObject::getStringValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}


	@Test
	void should_return_true_when_collection_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(NOT_EMTPY_LIST)
			.setValue(NOT_EMTPY_SET)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyCollection(ThirdLevelObject::getListValue)
			.orIsNotEmptyCollection(ThirdLevelObject::getSetValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

	}

	@Test
	void should_return_false_when_collection_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(List.of())
			.setValue(Set.of())
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyCollection(ThirdLevelObject::getListValue)
			.orIsNotEmptyCollection(ThirdLevelObject::getSetValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		// Final result
		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_collection_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(null)
			.setValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyCollection(ThirdLevelObject::getListValue)
			.orIsNotEmptyCollection(ThirdLevelObject::getSetValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}


	@Test
	void should_return_false_when_collection_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(NOT_EMTPY_LIST)
			.setValue(NOT_EMTPY_SET)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyCollection(ThirdLevelObject::getListValue)
			.orIsEmptyCollection(ThirdLevelObject::getSetValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_collection_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(List.of())
			.setValue(Set.of())
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyCollection(ThirdLevelObject::getListValue)
			.orIsEmptyCollection(ThirdLevelObject::getSetValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_collection_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(null)
			.setValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyCollection(ThirdLevelObject::getListValue)
			.orIsEmptyCollection(ThirdLevelObject::getSetValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_map_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(NOT_EMTPY_MAP)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyMap(ThirdLevelObject::getMapValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_map_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(Map.of())
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyMap(ThirdLevelObject::getMapValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		// Final result
		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_map_attribute_and_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsNotEmptyMap(ThirdLevelObject::getMapValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}


	@Test
	void should_return_false_when_map_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(NOT_EMTPY_MAP)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyMap(ThirdLevelObject::getMapValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_map_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(Map.of())
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyMap(ThirdLevelObject::getMapValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_map_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(null)
			.build();

		final ConditionalRecursive<ThirdLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(thirdLevelObject)
			.orIsEmptyMap(ThirdLevelObject::getMapValue);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_and_not_zero() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(1)
			.longValue(1L)
			.bigDecimalValue(BigDecimal.ONE)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue())
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue())
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue());

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_but_is_zero() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(0)
			.longValue(0L)
			.bigDecimalValue(BigDecimal.ZERO)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue())
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue())
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue());

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_number_attribute_and_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(null)
			.longValue(null)
			.bigDecimalValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue())
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue())
			.orIsNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue());

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_number_attribute_is_not_null_and_not_zero() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(1)
			.longValue(1L)
			.bigDecimalValue(BigDecimal.ONE)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue())
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue())
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue());

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_number_attribute_is_not_null_but_is_zero() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(0)
			.longValue(0L)
			.bigDecimalValue(BigDecimal.ZERO)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue())
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue())
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue());

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_number_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.integerValue(null)
			.longValue(null)
			.bigDecimalValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue())
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue())
			.orIsNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue());

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(OR)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

}