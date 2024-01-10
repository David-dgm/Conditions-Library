package org.dgm.conditional.condition;


import static java.util.Objects.requireNonNull;
import static org.dgm.conditional.model.Condition.condition;
import static org.dgm.conditional.model.ConditionResult.NATURAL;

import java.util.function.BooleanSupplier;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionOperator;
import org.dgm.conditional.model.ConditionResult;

public class Conditional {

	Conditional() {

	}

	public static class Conditional123 extends AbstractConditional {


		protected Condition c0;
		protected Condition c1;
		protected Condition c2;

		protected Conditional123() {

		}

		protected Conditional123(final BooleanSupplier booleanSupplier, final ConditionResult conditionResult) {

			this.c0 = condition(booleanSupplier, conditionResult);

		}

		protected Conditional123(final BooleanSupplier booleanSupplier,
								 final ConditionOperator conditionOperator,
								 final ConditionResult conditionResult) {

			this.c0 = condition(booleanSupplier, conditionOperator, conditionResult);
		}

		protected Conditional123(final Condition condition) {

			this.c0 = condition;
		}

		private Conditional123(final BooleanSupplier booleanSupplier) {

			this.c0 = condition(booleanSupplier);

		}


		public static Conditional.Conditional123 conditioning(final BooleanSupplier booleanSupplier) {

			return new Conditional123(booleanSupplier);
		}

		public static Conditional.Conditional123 conditioning(final BooleanSupplier booleanSupplier,
															  final ConditionResult conditionResult) {

			return new Conditional123(booleanSupplier, conditionResult);
		}

		public static Conditional.Conditional123 conditioning(final Condition condition) {

			return new Conditional123(condition);
		}

		public Conditional123 add(final BooleanSupplier s1, final ConditionOperator operator) {

			return this.add(s1, operator, NATURAL);

		}

		public Conditional123 add(final BooleanSupplier s1,
								  final ConditionOperator operator,
								  final ConditionResult conditionResult) {

			requireNonNull(s1);
			requireNonNull(operator);

			final Condition newCondition = condition(s1, operator, conditionResult);

			return this.addCondition(newCondition);
		}

		@Override
		public boolean is() {

			if (this.c1 == null) {

				return this.c0.is();
			}

			final boolean firstCondition = this.c0.operateCondition(this.c1);

			//			if (this.scapeNextCondition(firstCondition, this.c1) || this.c2 == null) {
			if (this.c2 == null) {

				return firstCondition;
			}

			return this.c2.operateCondition(firstCondition);
		}

		@Override
		public boolean negate() {

			return !this.is();
		}

		@Override
		protected Conditional123 addCondition(final Condition newCondition) {

			if (newCondition == null) {
				return this;
			}

			if (this.c0 == null) {

				this.c0 = newCondition;
				return this;
			}
			if (this.c1 == null) {

				this.c1 = newCondition;
				return this;
			}
			if (this.c2 == null) {

				this.c2 = newCondition;
				return this;
			}

			// return Conditional.ConditionalLst(this, newCondition);
			return this;
		}
	}
}
