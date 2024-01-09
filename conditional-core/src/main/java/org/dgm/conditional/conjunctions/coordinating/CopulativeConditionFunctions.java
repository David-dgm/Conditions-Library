package org.dgm.conditional.conjunctions.coordinating;

import static org.dgm.conditional.model.ConditionOperator.AND;

import java.util.Collection;
import java.util.Map;
import org.dgm.conditional.condition.ConditionalSpeciality;
import org.dgm.conditional.model.Condition;
import org.dgm.conditional.model.ConditionSpecialityFunctions;

class CopulativeConditionFunctions implements ConditionSpecialityFunctions {


	private CopulativeConditionFunctions() {

	}

	public static CopulativeConditionFunctions copulativeConditionFunctions() {

		return new CopulativeConditionFunctions();

	}

	@Override
	public Condition isNull(final Object object) {

		return ConditionalSpeciality.isNull(object, AND);

	}

	@Override
	public Condition isNotNull(final Object object) {

		return ConditionalSpeciality.isNotNull(object, AND);
	}

	@Override
	public Condition isEmpty(final String string) {

		return ConditionalSpeciality.isEmpty(string, AND);
	}

	@Override
	public Condition isEmpty(final Collection<?> collection) {

		return ConditionalSpeciality.isEmpty(collection, AND);
	}

	@Override
	public Condition isEmpty(final Map<?, ?> map) {

		return ConditionalSpeciality.isEmpty(map, AND);
	}


	@Override
	public Condition isNotEmpty(final String string) {

		return ConditionalSpeciality.isNotEmpty(string, AND);
	}

	@Override
	public Condition isNotEmpty(final Collection<?> collection) {

		return ConditionalSpeciality.isNotEmpty(collection, AND);
	}

	@Override
	public Condition isNotEmpty(final Map<?, ?> collection) {

		return ConditionalSpeciality.isNotEmpty(collection, AND);
	}

	@Override
	public Condition isNonNullAndNotZero(final Number number) {

		return ConditionalSpeciality.isNonNullAndNotZero(number, AND);
	}

	@Override
	public Condition isNonNullAndIsZero(final Number number) {

		return ConditionalSpeciality.isNonNullAndIsZero(number, AND);
	}


}
