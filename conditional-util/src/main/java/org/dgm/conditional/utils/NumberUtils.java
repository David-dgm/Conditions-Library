package org.dgm.conditional.utils;

import java.math.BigDecimal;
import java.util.Objects;

public class NumberUtils {

	private NumberUtils() {

	}

	public static class IntegerUtils {

		private IntegerUtils() {

		}

		public static boolean isNonNullAndNotZero(final Integer value) {

			return value != null && isNotZero(value);
		}

		public static boolean isNonNullAndIsZero(final Integer value) {

			return value != null && isZero(value);
		}

		public static boolean isNullOrZero(final Integer value) {

			return value == null || isZero(value);
		}

		private static boolean isZero(final Integer value) {

			return value.equals(0);
		}

		private static boolean isNotZero(final Integer value) {

			return !isZero(value);
		}
	}

	public static class LongUtils {

		private LongUtils() {

		}


		public static boolean isNonNullAndNotZero(final Long value) {

			return value != null && isNotZero(value);
		}

		public static boolean isNonNullAndIsZero(final Long value) {

			return value != null && isZero(value);
		}

		public static boolean isNullOrZero(final Long value) {

			return value == null || isZero(value);
		}

		private static boolean isZero(final Long value) {

			return value.equals(0L);
		}

		private static boolean isNotZero(final Long value) {

			return !isZero(value);
		}
	}

	public static class BigDecimalUtils {

		private BigDecimalUtils() {

		}

		public static boolean isNonNullAndNotZero(final BigDecimal value) {

			return Objects.nonNull(value) && isNotZero(value);
		}

		public static boolean isNonNullAndIsZero(final BigDecimal value) {

			return Objects.nonNull(value) && isZero(value);
		}

		public static boolean isNullOrZero(final BigDecimal value) {

			return Objects.isNull(value) || BigDecimal.ZERO.compareTo(value) == 0;
		}

		private static boolean isZero(final BigDecimal value) {

			return BigDecimal.ZERO.compareTo(value) == 0;
		}

		private static boolean isNotZero(final BigDecimal value) {

			return BigDecimal.ZERO.compareTo(value) != 0;
		}
	}
}