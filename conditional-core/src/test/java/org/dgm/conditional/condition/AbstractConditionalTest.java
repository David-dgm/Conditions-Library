package org.dgm.conditional.condition;

import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionOperator.OR;
import static org.dgm.conditional.model.ConditionResult.NATURAL;

import java.util.function.BooleanSupplier;
import org.assertj.core.api.Assertions;
import org.dgm.conditional.model.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbstractConditionalTest {

	private static final BooleanSupplier TRUE_BOOLEAN_SUPPLIER = () -> true;
	private static final BooleanSupplier FALSE_BOOLEAN_SUPPLIER = () -> false;

	@Test
	void should_return_true_when_previous_result_is_false_and_is_and_condition() {

		final Condition previousCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final boolean previousResult = false;

		final AbstractConditionalImpl conditionalImpl = new AbstractConditionalImpl();

		final boolean scapeNextCondition = conditionalImpl.scapeNextCondition(previousResult, previousCondition);

		Assertions.assertThat(scapeNextCondition).isTrue();
	}

	@Test
	void should_return_false_when_previous_result_is_true_and_is_and_condition() {

		final Condition previousCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, AND, NATURAL);
		final boolean previousResult = true;

		final AbstractConditionalImpl conditionalImpl = new AbstractConditionalImpl();

		final boolean scapeNextCondition = conditionalImpl.scapeNextCondition(previousResult, previousCondition);

		Assertions.assertThat(scapeNextCondition).isFalse();
	}

	@Test
	void should_return_false_when_previous_result_is_false_and_is_or_condition() {

		final Condition previousCondition = Condition.condition(FALSE_BOOLEAN_SUPPLIER, OR, NATURAL);
		final boolean previousResult = false;

		final AbstractConditionalImpl conditionalImpl = new AbstractConditionalImpl();

		final boolean scapeNextCondition = conditionalImpl.scapeNextCondition(previousResult, previousCondition);

		Assertions.assertThat(scapeNextCondition).isFalse();
	}

	@Test
	void should_return_false_when_previous_result_is_true_and_is_or_condition() {

		final Condition previousCondition = Condition.condition(TRUE_BOOLEAN_SUPPLIER, OR, NATURAL);
		final boolean previousResult = true;

		final AbstractConditionalImpl conditionalImpl = new AbstractConditionalImpl();

		final boolean scapeNextCondition = conditionalImpl.scapeNextCondition(previousResult, previousCondition);

		Assertions.assertThat(scapeNextCondition).isFalse();
	}

	private static class AbstractConditionalImpl extends AbstractConditional {

		AbstractConditionalImpl() {

		}

		@Override
		public boolean is() {

			return true;
		}

		@Override
		protected AbstractConditional addCondition(final Condition newCondition) {

			return new AbstractConditionalImpl();
		}
	}
}