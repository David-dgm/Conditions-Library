package org.dgm.conditional.conjunctions.coordinating;


import static org.dgm.conditional.conjunctions.coordinating.CopulativeConditionFunctions.copulativeConditionFunctions;
import static org.dgm.conditional.model.Condition.conditionAsCopulative;
import static org.dgm.conditional.model.ConditionOperator.AND;
import static org.dgm.conditional.model.ConditionResult.NATURAL;

import java.util.Collection;
import java.util.Map;
import java.util.function.BooleanSupplier;
import org.dgm.conditional.condition.Conditional.Conditional123;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionResult;
import org.dgm.conditional.model.ConditionSpecialityFunctions;
import org.dgm.conditional.model.ConditionalFunctions;

public class Copulative {

	private Copulative() {

	}

	public static final class CopulativeSpeciality123 extends Conditional123 implements ConditionalFunctions {

		private final ConditionSpecialityFunctions copulativeFunctions;

		private CopulativeSpeciality123() {

			super();
			this.copulativeFunctions = copulativeConditionFunctions();
		}

		private CopulativeSpeciality123(final BooleanSupplier booleanSupplier, final ConditionResult conditionResult) {

			super(booleanSupplier, AND, conditionResult);
			this.copulativeFunctions = copulativeConditionFunctions();
		}

		private CopulativeSpeciality123(final Condition condition) {

			super(condition);
			this.copulativeFunctions = copulativeConditionFunctions();
		}

		public static CopulativeSpeciality123 copulativeConditioning() {

			return new CopulativeSpeciality123();
		}

		public static CopulativeSpeciality123 copulativeConditioning(final BooleanSupplier booleanSupplier) {

			return copulativeConditioning(booleanSupplier, NATURAL);
		}

		public static CopulativeSpeciality123 copulativeConditioning(final BooleanSupplier booleanSupplier,
																	 final ConditionResult conditionResult) {

			return new CopulativeSpeciality123(booleanSupplier, conditionResult);
		}


		public static CopulativeSpeciality123 copulativeConditioning(final Condition condition) {

			if (condition.isSameOperator(AND)) {

				return new CopulativeSpeciality123(condition);

			}

			return new CopulativeSpeciality123(condition.asCopulative());
		}


		public CopulativeSpeciality123 and(final BooleanSupplier s1) {

			final Condition copulativeCondition = conditionAsCopulative(s1);

			return this.addAnd(copulativeCondition);
		}

		public CopulativeSpeciality123 and(final BooleanSupplier s1, final ConditionResult conditionResult) {

			final Condition copulativeCondition = conditionAsCopulative(s1, conditionResult);

			return this.addAnd(copulativeCondition);
		}

		public CopulativeSpeciality123 and(final Condition condition) {

			if (condition.isSameOperator(AND)) {

				return this.addAnd(condition);
			}

			return this.addAnd(condition.asCopulative());
		}

		// Este add sobreescribe el normal, añadiendo la validación
		private CopulativeSpeciality123 addAnd(final Condition condition) {

			return (CopulativeSpeciality123) super.addCondition(condition);
		}

		@Override
		public boolean is() {

			if (this.c1 == null) {

				return this.c0.is();
			}

			final boolean firstCondition = this.c0.operateCondition(this.c1);

			if (super.scapeNextCondition(firstCondition, this.c1) || this.c2 == null) {

				return firstCondition;
			}

			return this.c2.operateCondition(firstCondition);
		}

		@Override
		public CopulativeSpeciality123 isNull(final Object object) {

			return this.addAnd(this.copulativeFunctions.isNull(object));
		}

		@Override
		public CopulativeSpeciality123 isNotNull(final Object object) {

			return this.addAnd(this.copulativeFunctions.isNotNull(object));
		}

		@Override
		public CopulativeSpeciality123 isEmpty(final String string) {

			return this.addAnd(this.copulativeFunctions.isEmpty(string));
		}

		@Override
		public CopulativeSpeciality123 isEmpty(final Collection<?> collection) {

			return this.addAnd(this.copulativeFunctions.isEmpty(collection));
		}

		@Override
		public CopulativeSpeciality123 isEmpty(final Map<?, ?> map) {

			return this.addAnd(this.copulativeFunctions.isEmpty(map));
		}

		@Override
		public CopulativeSpeciality123 isNotEmpty(final String string) {

			return this.addAnd(this.copulativeFunctions.isNotEmpty(string));
		}

		@Override
		public CopulativeSpeciality123 isNotEmpty(final Collection<?> collection) {

			return this.addAnd(this.copulativeFunctions.isNotEmpty(collection));
		}

		@Override
		public CopulativeSpeciality123 isNotEmpty(final Map<?, ?> map) {

			return this.addAnd(this.copulativeFunctions.isNotEmpty(map));
		}


		@Override
		public CopulativeSpeciality123 isNonNullAndNonZero(final Number number) {

			return this.addAnd(this.copulativeFunctions.isNonNullAndNotZero(number));
		}

		@Override
		public CopulativeSpeciality123 isNonNullAndIsZero(final Number number) {

			return this.addAnd(this.copulativeFunctions.isNonNullAndIsZero(number));
		}
	}
}
