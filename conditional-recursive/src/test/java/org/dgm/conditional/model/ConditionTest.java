package org.dgm.conditional.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionResult.DENIED;
import static org.dgm.conditional.model.ConditionResult.NATURAL;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConditionTest {

	private static final BooleanSupplier TRUE_BOOLEAN_SUPPLIER = () -> true;
	private static final BooleanSupplier FALSE_BOOLEAN_SUPPLIER = () -> false;

	@Test
	void should_create_condition_object_with_a_boolean_supplier() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER);

		assertThat(condition)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("operator", "value");

		assertThat(condition.booleanSupplier).isSameAs(TRUE_BOOLEAN_SUPPLIER);

		assertThat((Predicate<Boolean>) condition.conditionResult).isEqualTo(NATURAL);

	}

	@Test
	void should_create_condition_object_with_a_boolean_supplier_and_condition_result() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, DENIED);

		assertThat(condition)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("operator", "value");

		assertThat(condition.booleanSupplier).isSameAs(TRUE_BOOLEAN_SUPPLIER);

		assertThat((Predicate<Boolean>) condition.conditionResult).isEqualTo(DENIED);

	}

	@Test
	void should_create_condition_object_with_a_boolean_supplier_an_operator_and_a_condition_result() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, DENIED);

		assertThat(condition)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("value");

		assertThat(condition.booleanSupplier).isSameAs(TRUE_BOOLEAN_SUPPLIER);
		assertThat(condition.operator).isEqualTo(AND);
		assertThat((Predicate<Boolean>) condition.conditionResult).isEqualTo(DENIED);

	}

	@Test
	void should_execute_the_supplier_only_after_to_load_the_condition() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);

		assertThat(condition)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("value");

		assertThat(condition.booleanSupplier).isSameAs(TRUE_BOOLEAN_SUPPLIER);
		assertThat(condition.operator).isEqualTo(AND);
		assertThat((Predicate<Boolean>) condition.conditionResult).isEqualTo(NATURAL);

		// Given
		condition.load();

		// Then
		assertThat(condition)
			.isNotNull()
			.hasNoNullFieldsOrProperties();

		assertThat(condition.is()).isTrue();

	}

	@Test
	void should_execute_the_supplier_only_after_to_load_the_condition_when_denied_result() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, DENIED);

		assertThat(condition)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("value");

		assertThat(condition.booleanSupplier).isSameAs(TRUE_BOOLEAN_SUPPLIER);
		assertThat(condition.operator).isEqualTo(AND);
		assertThat((Predicate<Boolean>) condition.conditionResult).isEqualTo(DENIED);

		// Given
		condition.load();

		// Then
		assertThat(condition)
			.isNotNull()
			.hasNoNullFieldsOrProperties();

		assertThat(condition.is()).isFalse();

	}

	@Test
	void should_operate_the_condition_using_a_boolean() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final boolean result = condition.load().operateCondition(false);

		assertThat(condition.is()).isTrue();

		assertThat(result).isFalse();

	}

	@Test
	void should_operate_the_condition_using_a_true_condition_without_load() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final boolean result = condition.load().operateCondition(secondCondition);

		assertThat(condition.is()).isTrue();
		assertThat(secondCondition.is()).isTrue();

		assertThat(result).isTrue();

	}

	@Test
	void should_operate_the_condition_using_a_false_condition_without_load() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final boolean result = condition.load().operateCondition(secondCondition);

		assertThat(condition.is()).isTrue();
		assertThat(secondCondition.is()).isFalse();

		assertThat(result).isFalse();

	}

	@Test
	void should_operate_the_condition_using_a_true_condition_loaded() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final boolean result = condition.load().operateCondition(secondCondition.load());

		assertThat(condition.is()).isTrue();
		assertThat(secondCondition.is()).isTrue();

		assertThat(result).isTrue();

	}

	@Test
	void should_operate_the_condition_using_a_false_condition_loaded() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final boolean result = condition.load().operateCondition(secondCondition.load());

		assertThat(condition.is()).isTrue();
		assertThat(secondCondition.is()).isFalse();

		assertThat(result).isFalse();

	}

}