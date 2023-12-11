package org.dgm.conditional.conjunctions.coordinating;

import java.lang.reflect.Field;
import org.dgm.conditional.condition.Conditional.Conditional123;
import org.dgm.conditional.model.Condition;

public class ConditionExtractorHelper {

	private ConditionExtractorHelper() {

	}

	private static final String FIRST_CONDITION = "c0";
	private static final String SECOND_CONDITION = "c1";
	private static final String THIRD_CONDITION = "c2";

	public static <T extends Conditional123> Condition extractFirstConditionCreated(final T conditional123) {

		return extractNewConditionCreated(conditional123, FIRST_CONDITION);
	}

	public static <T extends Conditional123> Condition extractSecondConditionCreated(final T conditional123) {

		return extractNewConditionCreated(conditional123, SECOND_CONDITION);
	}

	public static <T extends Conditional123> Condition extractThirdConditionCreated(final T conditional123) {

		return extractNewConditionCreated(conditional123, THIRD_CONDITION);
	}

	private static <T extends Conditional123> Condition extractNewConditionCreated(final T conditional123,
																				   final String conditionNumber) {

		try {
			final Field conditionField = conditional123.getClass().getSuperclass().getDeclaredField(conditionNumber);
			conditionField.setAccessible(true);

			return (Condition) conditionField.get(conditional123);

		} catch (final NoSuchFieldException | IllegalAccessException exception) {

			System.out.println("Unexpected Exception: " + exception);

			return null;
		}

	}
}
