package org.dgm.conditional.model;

import java.util.Collection;
import java.util.Map;
import org.dgm.conditional.condition.AbstractConditional;

public interface ConditionalFunctions {

	AbstractConditional isNull(Object object);

	AbstractConditional isNotNull(Object object);

	AbstractConditional isEmpty(String string);

	AbstractConditional isEmpty(Collection<?> collection);

	AbstractConditional isEmpty(Map<?, ?> collection);

	AbstractConditional isNotEmpty(String string);

	AbstractConditional isNotEmpty(Collection<?> collection);

	AbstractConditional isNotEmpty(Map<?, ?> collection);

	AbstractConditional isNonNullAndNonZero(Number number);

	AbstractConditional isNonNullAndIsZero(Number number);

}
