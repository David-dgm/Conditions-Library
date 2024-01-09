package org.dgm.conditional.condition;

import static org.dgm.conditional.condition.Conditional.Conditional123.conditioning;
import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionOperator.OR;
import static org.dgm.conditional.model.ConditionResult.DENIED;
import static org.dgm.conditional.model.ConditionResult.NATURAL;

import java.util.function.BooleanSupplier;
import org.assertj.core.api.Assertions;
import org.dgm.conditional.condition.Conditional.Conditional123;
import org.dgm.conditional.model.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConditionalTest {

	private static final BooleanSupplier TRUE_BOOLEAN_SUPPLIER = () -> true;
	private static final BooleanSupplier FALSE_BOOLEAN_SUPPLIER = () -> false;
	private static final BooleanSupplier SCAPE_SUPPLIER = () -> {
		throw new UnsupportedOperationException();
	};

	@Test
	void should_return_conditional_object_with_first_attribute_non_null_when_parameter_is_a_supplier() {

		final Conditional123 conditional = conditioning(TRUE_BOOLEAN_SUPPLIER);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasAllNullFieldsOrPropertiesExcept("c0")
			.extracting(conditional123 -> conditional123.c0)
			.hasFieldOrPropertyWithValue("booleanSupplier", TRUE_BOOLEAN_SUPPLIER);
	}

	@Test
	void should_return_conditional_object_with_first_attribute_non_null_when_parameter_is_a_supplier_and_condition_result() {

		final Conditional123 conditional = conditioning(TRUE_BOOLEAN_SUPPLIER, DENIED);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasAllNullFieldsOrPropertiesExcept("c0")
			.extracting(conditional123 -> conditional123.c0)
			.hasFieldOrPropertyWithValue("booleanSupplier", TRUE_BOOLEAN_SUPPLIER)
			.hasFieldOrPropertyWithValue("conditionResult", DENIED);
	}

	@Test
	void should_return_conditional_object_with_first_attribute_non_null_when_parameter_is_a_condition() {

		final Condition condition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final Conditional123 conditional = conditioning(condition);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasAllNullFieldsOrPropertiesExcept("c0")
			.hasFieldOrPropertyWithValue("c0", condition);
	}

	@Test
	void should_add_a_supplier_and_operator_as_new_condition() {

		final Conditional123 conditional = conditioning(TRUE_BOOLEAN_SUPPLIER)
			.add(FALSE_BOOLEAN_SUPPLIER, AND);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("c2")
			.extracting(conditional123 -> conditional123.c0)
			.hasFieldOrPropertyWithValue("booleanSupplier", TRUE_BOOLEAN_SUPPLIER);

		Assertions.assertThat(conditional.c1)
			.hasFieldOrPropertyWithValue("booleanSupplier", FALSE_BOOLEAN_SUPPLIER)
			.hasFieldOrPropertyWithValue("operator", AND);

	}

	@Test
	void should_add_a_supplier_and_operator_and_condition_result_as_new_condition() {

		final Conditional123 conditional = conditioning(TRUE_BOOLEAN_SUPPLIER)
			.add(FALSE_BOOLEAN_SUPPLIER, AND, DENIED);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("c2")
			.extracting(conditional123 -> conditional123.c0)
			.hasFieldOrPropertyWithValue("booleanSupplier", TRUE_BOOLEAN_SUPPLIER);

		Assertions.assertThat(conditional.c1)
			.hasFieldOrPropertyWithValue("booleanSupplier", FALSE_BOOLEAN_SUPPLIER)
			.hasFieldOrPropertyWithValue("operator", AND)
			.hasFieldOrPropertyWithValue("conditionResult", DENIED);

	}

	@Test
	void should_not_add_a_condition_when_is_null() {

		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final Conditional123 conditional = conditioning(firstCondition)
			.addCondition(null);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasAllNullFieldsOrPropertiesExcept("c0");

	}

	@Test
	void should_add_a_condition_as_new_condition() {

		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition newCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final Conditional123 conditional = conditioning(firstCondition)
			.addCondition(newCondition);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasNoNullFieldsOrPropertiesExcept("c2")
			.hasFieldOrPropertyWithValue("c0", firstCondition)
			.hasFieldOrPropertyWithValue("c1", newCondition);

	}

	@Test
	void should_create_a_conditional_with_three_conditions() {

		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition thirdCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final Conditional123 conditional = conditioning(firstCondition)
			.addCondition(secondCondition)
			.addCondition(thirdCondition);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasNoNullFieldsOrProperties()
			.hasFieldOrPropertyWithValue("c0", firstCondition)
			.hasFieldOrPropertyWithValue("c1", secondCondition)
			.hasFieldOrPropertyWithValue("c2", thirdCondition);
	}

	@Test
	void should_create_a_conditional_with_more_than_three_conditions() {
		// TODO dgm este test tiene que comprobar que cuando sobre pasa 3 condiciones devuelve conditionalList.
		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition thirdCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition fourthCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final Conditional123 conditional = conditioning(firstCondition)
			.addCondition(secondCondition)
			.addCondition(thirdCondition)
			.addCondition(fourthCondition);

		Assertions.assertThat(conditional)
			.isNotNull()
			.hasNoNullFieldsOrProperties()
			.hasFieldOrPropertyWithValue("c0", firstCondition)
			.hasFieldOrPropertyWithValue("c1", secondCondition)
			.hasFieldOrPropertyWithValue("c2", thirdCondition);

		// TODO dgm cambiar test:
		//		Assertions.assertThat(conditional)
		//			.doesNotHaveSameClassAs(Conditional123.class)
		//			.hasSameClassAs(ConditionalList.class);
	}

	@Test
	void should_evaluate_condition_to_known_the_result_when_have_one_condition() {

		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, NATURAL);

		final boolean result = conditioning(firstCondition)
			.is();

		Assertions.assertThat(result).isTrue();
	}

	@Test
	void should_evaluate_all_conditions_to_known_the_result_when_and_conditions() {

		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition thirdCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final boolean result = conditioning(firstCondition)
			.addCondition(secondCondition)
			.addCondition(thirdCondition)
			.is();

		Assertions.assertThat(result).isFalse();
	}

	@Test
	void should_negate_the_result() {

		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition thirdCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);

		final boolean result = conditioning(firstCondition)
			.addCondition(secondCondition)
			.addCondition(thirdCondition)
			.negate();

		Assertions.assertThat(result).isTrue();
	}

	@Test
	void should_scape_conditions_as_soon_as_posible_when_and_Conditions() {

		final Condition firstCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition secondCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final Condition thirdCondition = Condition.condition(SCAPE_SUPPLIER, AND, NATURAL);

		final boolean result = conditioning(firstCondition)
			.addCondition(secondCondition)
			.addCondition(thirdCondition)
			.is();

		Assertions.assertThat(result).isFalse();
	}

	@Test
	void should_evaluate_all_conditions_to_known_the_result_when_or_conditions() {

		final Condition firstCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, NATURAL);
		final Condition secondCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, OR, NATURAL);
		final Condition thirdCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, OR, NATURAL);

		final boolean result = conditioning(firstCondition)
			.addCondition(secondCondition)
			.addCondition(thirdCondition)
			.is();

		Assertions.assertThat(result).isTrue();
	}
}