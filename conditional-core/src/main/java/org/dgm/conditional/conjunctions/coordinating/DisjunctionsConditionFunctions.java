package org.dgm.conditional.conjunctions.coordinating;

import static org.dgm.conditional.model.ConditionOperator.OR;

import java.util.Collection;
import java.util.Map;
import org.dgm.conditional.condition.ConditionalSpeciality;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionSpecialityFunctions;

class DisjunctionsConditionFunctions implements ConditionSpecialityFunctions {


	private DisjunctionsConditionFunctions() {

	}

	public static DisjunctionsConditionFunctions disjunctiveConditionFunctions() {

		return new DisjunctionsConditionFunctions();

	}

	@Override
	public Condition isNull(final Object object) {

		return ConditionalSpeciality.isNull(object, OR);

	}

	@Override
	public Condition isNotNull(final Object object) {

		return ConditionalSpeciality.isNotNull(object, OR);
	}

	@Override
	public Condition isEmpty(final String string) {

		return ConditionalSpeciality.isEmpty(string, OR);
	}

	@Override
	public Condition isEmpty(final Collection<?> collection) {

		return ConditionalSpeciality.isEmpty(collection, OR);
	}

	@Override
	public Condition isEmpty(final Map<?, ?> collection) {

		return ConditionalSpeciality.isEmpty(collection, OR);
	}


	@Override
	public Condition isNotEmpty(final String string) {

		return ConditionalSpeciality.isNotEmpty(string, OR);
	}

	@Override
	public Condition isNotEmpty(final Collection<?> collection) {

		return ConditionalSpeciality.isNotEmpty(collection, OR);
	}

	@Override
	public Condition isNotEmpty(final Map<?, ?> collection) {

		return ConditionalSpeciality.isNotEmpty(collection, OR);
	}

	@Override
	public Condition isNonNullAndNotZero(final Number number) {

		return ConditionalSpeciality.isNonNullAndNotZero(number, OR);
	}

	@Override
	public Condition isNonNullAndIsZero(final Number number) {

		return ConditionalSpeciality.isNonNullAndIsZero(number, OR);
	}


}
