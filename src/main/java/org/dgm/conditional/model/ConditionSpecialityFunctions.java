package org.dgm.conditional.model;

import java.util.Collection;
import java.util.Map;

public interface ConditionSpecialityFunctions {

	<T> Condition isNull(T object);

	<T> Condition isNotNull(T object);

	Condition isEmpty(String string);

	Condition isEmpty(Collection<?> collection);

	Condition isEmpty(Map<?, ?> collection);

	Condition isNotEmpty(String string);

	Condition isNotEmpty(Collection<?> collection);

	Condition isNotEmpty(Map<?, ?> collection);

	Condition isNonNullAndNotZero(Number number);

	Condition isNonNullAndIsZero(Number number);

}
