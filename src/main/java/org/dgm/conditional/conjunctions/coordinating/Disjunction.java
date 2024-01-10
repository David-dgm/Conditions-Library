package org.dgm.conditional.conjunctions.coordinating;


import static org.dgm.conditional.conjunctions.coordinating.DisjunctionsConditionFunctions.disjunctiveConditionFunctions;
import static org.dgm.conditional.model.Condition.conditionAsDisjunction;
import static org.dgm.conditional.model.ConditionOperator.OR;
import static org.dgm.conditional.model.ConditionResult.NATURAL;

import java.util.Collection;
import java.util.Map;
import java.util.function.BooleanSupplier;
import org.dgm.conditional.condition.Conditional.Conditional123;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionResult;
import org.dgm.conditional.model.ConditionSpecialityFunctions;
import org.dgm.conditional.model.ConditionalFunctions;

public class Disjunction {

	private Disjunction() {

	}


	public static final class DisjunctionSpeciality123 extends Conditional123 implements ConditionalFunctions {

		private final ConditionSpecialityFunctions disjunctiveFunctions;

		private DisjunctionSpeciality123() {

			super();
			this.disjunctiveFunctions = disjunctiveConditionFunctions();
		}

		private DisjunctionSpeciality123(final BooleanSupplier booleanSupplier, final ConditionResult conditionResult) {

			super(booleanSupplier, OR, conditionResult);
			this.disjunctiveFunctions = disjunctiveConditionFunctions();
		}

		private DisjunctionSpeciality123(final Condition condition) {

			super(condition);
			this.disjunctiveFunctions = disjunctiveConditionFunctions();
		}

		public static DisjunctionSpeciality123 disjunctionConditioning() {

			return new DisjunctionSpeciality123();
		}

		public static DisjunctionSpeciality123 disjunctionConditioning(final BooleanSupplier booleanSupplier) {

			return disjunctionConditioning(booleanSupplier, NATURAL);
		}

		public static DisjunctionSpeciality123 disjunctionConditioning(final BooleanSupplier booleanSupplier,
																	   final ConditionResult conditionResult) {

			return new DisjunctionSpeciality123(booleanSupplier, conditionResult);
		}


		public static DisjunctionSpeciality123 disjunctionConditioning(final Condition condition) {

			if (condition.isSameOperator(OR)) {

				return new DisjunctionSpeciality123(condition);

			}
			return new DisjunctionSpeciality123(condition.asDisjunction());
		}


		public DisjunctionSpeciality123 or(final BooleanSupplier s1) {

			final Condition disjunctionCondition = conditionAsDisjunction(s1);

			return this.addOr(disjunctionCondition);
		}

		public DisjunctionSpeciality123 or(final BooleanSupplier s1, final ConditionResult conditionResult) {

			final Condition disjunctionCondition = conditionAsDisjunction(s1, conditionResult);

			return this.addOr(disjunctionCondition);
		}

		public DisjunctionSpeciality123 or(final Condition condition) {

			if (condition.isSameOperator(OR)) {

				return this.addOr(condition);
			}

			return this.addOr(condition.asDisjunction());
		}

		private DisjunctionSpeciality123 addOr(final Condition condition) {

			return (DisjunctionSpeciality123) super.addCondition(condition);
		}

		@Override
		public boolean is() {

			if (this.c1 == null) {

				return this.c0.is();
			}

			final boolean firstCondition = this.c0.operateCondition(this.c1);

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
		public DisjunctionSpeciality123 isNull(final Object object) {

			return this.addOr(this.disjunctiveFunctions.isNull(object));
		}

		@Override
		public DisjunctionSpeciality123 isNotNull(final Object object) {

			return this.addOr(this.disjunctiveFunctions.isNotNull(object));
		}

		@Override
		public DisjunctionSpeciality123 isEmpty(final String string) {

			return this.addOr(this.disjunctiveFunctions.isEmpty(string));
		}

		@Override
		public DisjunctionSpeciality123 isEmpty(final Collection<?> collection) {

			return this.addOr(this.disjunctiveFunctions.isEmpty(collection));

		}

		@Override
		public DisjunctionSpeciality123 isEmpty(final Map<?, ?> map) {

			return this.addOr(this.disjunctiveFunctions.isEmpty(map));

		}


		@Override
		public DisjunctionSpeciality123 isNotEmpty(final String string) {

			return this.addOr(this.disjunctiveFunctions.isNotEmpty(string));
		}

		@Override
		public DisjunctionSpeciality123 isNotEmpty(final Collection<?> collection) {

			return this.addOr(this.disjunctiveFunctions.isNotEmpty(collection));

		}

		@Override
		public DisjunctionSpeciality123 isNotEmpty(final Map<?, ?> map) {

			return this.addOr(this.disjunctiveFunctions.isNotEmpty(map));

		}


		@Override
		public DisjunctionSpeciality123 isNonNullAndNonZero(final Number number) {

			return this.addOr(this.disjunctiveFunctions.isNonNullAndNotZero(number));
		}

		@Override
		public DisjunctionSpeciality123 isNonNullAndIsZero(final Number number) {

			return this.addOr(this.disjunctiveFunctions.isNonNullAndIsZero(number));
		}
	}
}
