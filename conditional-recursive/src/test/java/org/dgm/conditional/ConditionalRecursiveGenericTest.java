package org.dgm.conditional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionOperator.FIRST_CONDITION;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.FirstLevelObject;
import org.dgm.conditional.model.SecondLevelObject;
import org.dgm.conditional.model.ThirdLevelObject;
import org.dgm.conditional.utils.ConditionExtractorHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConditionalRecursiveGenericTest {


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
	void should_return_false_when_recursive_object_to_inspect_is_null_object() {

		final FirstLevelObject firstLevelObject = null;

		final ConditionalRecursive<FirstLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(firstLevelObject)
			.isNotNull();

		assertThat(conditionalRecursive).isNotNull();

		final Condition conditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(conditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(conditionCreated.is()).isFalse();
		assertThat(conditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_first_level_recursive_object_to_inspect_is_inside() {

		final FirstLevelObject firstLevelObject = FirstLevelObject.builder().build();

		final ConditionalRecursive<FirstLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(firstLevelObject)
			.isNotNull();

		assertThat(conditionalRecursive).isNotNull();

		final Condition conditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(conditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(conditionCreated.is()).isTrue();
		assertThat(conditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_second_level_recursive_object_to_inspect_is_null_object() {

		final SecondLevelObject secondLevelObject = null;
		final FirstLevelObject firstLevelObject = FirstLevelObject.builder()
			.secondLevelObjectInside(secondLevelObject)
			.build();

		final ConditionalRecursive<FirstLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(firstLevelObject)
			.isNotNull(FirstLevelObject::getSecondLevelObjectInside, FIRST_CONDITION);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_second_level_recursive_object_to_inspect_is_inside() {

		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.build();
		final FirstLevelObject firstLevelObject = FirstLevelObject.builder()
			.secondLevelObjectInside(secondLevelObject)
			.build();

		final ConditionalRecursive<FirstLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(firstLevelObject)
			.isNotNull(FirstLevelObject::getSecondLevelObjectInside, FIRST_CONDITION);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_second_level_recursive_object_to_inspect_is_not_inside() {

		final SecondLevelObject secondLevelObject = null;
		final FirstLevelObject firstLevelObject = FirstLevelObject.builder()
			.secondLevelObjectInside(secondLevelObject)
			.build();

		final ConditionalRecursive<FirstLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(firstLevelObject)
			.isNotNull(FirstLevelObject::getSecondLevelObjectInside, FIRST_CONDITION)
			.isNotNull(principalObject -> principalObject.getSecondLevelObjectInside().getThirdLevelObjectInside(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_third_level_recursive_object_to_inspect_is_inside() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder().build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();
		final FirstLevelObject firstLevelObject = FirstLevelObject.builder()
			.secondLevelObjectInside(secondLevelObject)
			.build();

		final ConditionalRecursive<FirstLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(firstLevelObject)
			.isNotNull(FirstLevelObject::getSecondLevelObjectInside, FIRST_CONDITION)
			.isNotNull(principalObject -> principalObject.getSecondLevelObjectInside().getThirdLevelObjectInside(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_string_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(NOT_EMTPY_STRING)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyString(principalObject -> principalObject.getThirdLevelObjectInside().getStringValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_string_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(EMPTY_STRING)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyString(principalObject -> principalObject.getThirdLevelObjectInside().getStringValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_string_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyString(principalObject -> principalObject.getThirdLevelObjectInside().getStringValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_object_with_string_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = null;
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyString(principalObject -> principalObject.getThirdLevelObjectInside().getStringValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_string_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(NOT_EMTPY_STRING)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isEmptyString(principalObject -> principalObject.getThirdLevelObjectInside().getStringValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_string_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(EMPTY_STRING)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isEmptyString(principalObject -> principalObject.getThirdLevelObjectInside().getStringValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_string_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.stringValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyString(principalObject -> principalObject.getThirdLevelObjectInside().getStringValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}


	@Test
	void should_return_true_when_collection_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(NOT_EMTPY_LIST)
			.setValue(NOT_EMTPY_SET)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getListValue(), AND)
			.isNotEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getSetValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_collection_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(List.of())
			.setValue(Set.of())
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getListValue(), AND)
			.isNotEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getSetValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		// Final result
		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_collection_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(null)
			.setValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getListValue(), AND)
			.isNotEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getSetValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}


	@Test
	void should_return_false_when_collection_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(NOT_EMTPY_LIST)
			.setValue(NOT_EMTPY_SET)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getListValue(), AND)
			.isEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getSetValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
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
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getListValue(), AND)
			.isEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getSetValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_collection_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.listValue(null)
			.setValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getListValue(), AND)
			.isEmptyCollection(principalObject -> principalObject.getThirdLevelObjectInside().getSetValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_map_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(NOT_EMTPY_MAP)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyMap(principalObject -> principalObject.getThirdLevelObjectInside().getMapValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_false_when_map_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(Map.of())
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyMap(principalObject -> principalObject.getThirdLevelObjectInside().getMapValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		// Final result
		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_false_when_map_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isNotEmptyMap(principalObject -> principalObject.getThirdLevelObjectInside().getMapValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}


	@Test
	void should_return_false_when_map_attribute_is_not_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(NOT_EMTPY_MAP)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isEmptyMap(principalObject -> principalObject.getThirdLevelObjectInside().getMapValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

	@Test
	void should_return_true_when_map_attribute_is_empty() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(Map.of())
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isEmptyMap(principalObject -> principalObject.getThirdLevelObjectInside().getMapValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isTrue();
	}

	@Test
	void should_return_true_when_map_attribute_is_null() {

		final ThirdLevelObject thirdLevelObject = ThirdLevelObject.builder()
			.mapValue(null)
			.build();
		final SecondLevelObject secondLevelObject = SecondLevelObject.builder()
			.thirdLevelObjectInside(thirdLevelObject)
			.build();

		final ConditionalRecursive<SecondLevelObject> conditionalRecursive = ConditionalRecursive
			.conditionalRecursiveOver(secondLevelObject)
			.isNotNull()
			.isNotNull(SecondLevelObject::getThirdLevelObjectInside, AND)
			.isEmptyMap(principalObject -> principalObject.getThirdLevelObjectInside().getMapValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(FIRST_CONDITION)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

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
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue(), AND)
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue(), AND)
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
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
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue(), AND)
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue(), AND)
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
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
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue(), AND)
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue(), AND)
			.isNonNullAndNotZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
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
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue(), AND)
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue(), AND)
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
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
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue(), AND)
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue(), AND)
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
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
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getIntegerValue(), AND)
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getLongValue(), AND)
			.isNonNullAndIsZero(principalObject -> principalObject.getThirdLevelObjectInside().getBigDecimalValue(), AND);

		assertThat(conditionalRecursive).isNotNull();

		final Condition firstConditionCreated = ConditionExtractorHelper.extractFirstConditionCreated(conditionalRecursive);

		assertThat(firstConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(firstConditionCreated.is()).isFalse();
		assertThat(firstConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition secondConditionCreated = ConditionExtractorHelper.extractSecondConditionCreated(conditionalRecursive);

		assertThat(secondConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(secondConditionCreated.is()).isFalse();
		assertThat(secondConditionCreated.isAlreadyLoaded()).isTrue();

		final Condition thirdConditionCreated = ConditionExtractorHelper.extractThirdConditionCreated(conditionalRecursive);
		assertThat(thirdConditionCreated.isSameOperator(AND)).isTrue();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isFalse();
		assertThat(thirdConditionCreated.is()).isFalse();
		assertThat(thirdConditionCreated.isAlreadyLoaded()).isTrue();

		assertThat(conditionalRecursive.is()).isFalse();
	}

}