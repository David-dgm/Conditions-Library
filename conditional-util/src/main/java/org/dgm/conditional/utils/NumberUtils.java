package org.dgm.conditional.utils;

import java.math.BigDecimal;

public class NumberUtils {

	private NumberUtils() {

	}

	public static <T extends Number> boolean isNull(final T number) {

		return number == null;
	}

	public static <T extends Number> boolean isNonNull(final T number) {

		return !isNull(number);
	}

	public static <T extends Number> boolean isNonNullAndNotZero(final T number) {

		return isNonNull(number) && isNotZero(number);
	}


	public static <T extends Number> boolean isNonNullAndIsZero(final T number) {

		return isNonNull(number) && isZero(number);
	}

	public static <T extends Number> boolean isNullOrZero(final T number) {

		return isNull(number) || isZero(number);
	}

	private static <T extends Number> boolean isNotZero(final T value) {

		return !isZero(value);
	}

	private static <T extends Number> boolean isZero(final T number) {

		if (number instanceof Integer integerNumber) {

			return IntegerUtils.isZero(integerNumber);
		}
		if (number instanceof Long longNumber) {

			return LongUtils.isZero(longNumber);
		}
		if (number instanceof BigDecimal bigDecimal) {

			return BigDecimalUtils.isZero(bigDecimal);
		}
		System.out.println("Type not contemplate, Class: " + number.getClass());
		// TODO Todo revisar casos concretos
		return false;
	}

	public static class IntegerUtils {

		private IntegerUtils() {

		}

		//		public static boolean isNonNullAndNotZero(final Integer value) {
		//
		//			return value != null && isNotZero(value);
		//		}
		//
		//		public static boolean isNonNullAndIsZero(final Integer value) {
		//
		//			return value != null && isZero(value);
		//		}
		//
		//		public static boolean isNullOrZero(final Integer value) {
		//
		//			return value == null || isZero(value);
		//		}

		private static boolean isZero(final Integer value) {

			return value.equals(0);
		}

		//		private static boolean isNotZero(final Integer value) {
		//
		//			return !isZero(value);
		//		}
	}

	public static class LongUtils {

		private LongUtils() {

		}

		//		public static boolean isNonNullAndNotZero(final Long value) {
		//
		//			return value != null && isNotZero(value);
		//		}
		//
		//		public static boolean isNonNullAndIsZero(final Long value) {
		//
		//			return value != null && isZero(value);
		//		}
		//
		//		public static boolean isNullOrZero(final Long value) {
		//
		//			return value == null || isZero(value);
		//		}

		private static boolean isZero(final Long value) {

			return value.equals(0L);
		}

		//		private static boolean isNotZero(final Long value) {
		//
		//			return !isZero(value);
		//		}
	}

	public static class BigDecimalUtils {

		private BigDecimalUtils() {

		}

		//		public static boolean isNonNullAndNotZero(final BigDecimal value) {
		//
		//			return Objects.nonNull(value) && isNotZero(value);
		//		}
		//
		//		public static boolean isNonNullAndIsZero(final BigDecimal value) {
		//
		//			return Objects.nonNull(value) && isZero(value);
		//		}
		//
		//		public static boolean isNullOrZero(final BigDecimal value) {
		//
		//			return Objects.isNull(value) || BigDecimal.ZERO.compareTo(value) == 0;
		//		}

		private static boolean isZero(final BigDecimal value) {

			return BigDecimal.ZERO.compareTo(value) == 0;
		}

		//		private static boolean isNotZero(final BigDecimal value) {
		//
		//			return BigDecimal.ZERO.compareTo(value) != 0;
		//		}
	}
}