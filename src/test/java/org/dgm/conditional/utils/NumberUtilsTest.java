package org.dgm.conditional.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NumberUtilsTest {

	@Test
	void should_return_true_when_number_is_null() {

		assertThat(NumberUtils.<Integer>isNull(null)).isTrue();
	}

	@Test
	void should_return_false_when_number_is_not_null() {

		assertThat(NumberUtils.<Integer>isNull(1)).isFalse();
	}

	@Test
	void should_return_false_when_number_is_null() {

		assertThat(NumberUtils.<Integer>isNonNull(null)).isFalse();
	}

	@Test
	void should_return_true_when_number_is_not_null() {

		assertThat(NumberUtils.<Integer>isNonNull(1)).isTrue();
	}

	@Test
	void should_return_false_when_number_is_null_and_checking_is_non_null_and_not_zero() {

		assertThat(NumberUtils.<Integer>isNonNullAndNotZero(null)).isFalse();
	}

	@Test
	void should_return_false_when_number_is_zero_and_checking_is_non_null_and_not_zero() {

		assertThat(NumberUtils.<Integer>isNonNullAndNotZero(0)).isFalse();
	}

	@Test
	void should_return_true_when_number_is_greater_than_zero_and_checking_is_non_null_and_not_zero() {

		assertThat(NumberUtils.<Integer>isNonNullAndNotZero(1)).isTrue();
	}

	@Test
	void should_return_false_when_number_is_null_and_checking_is_non_null_and_is_zero() {

		assertThat(NumberUtils.<Integer>isNonNullAndIsZero(null)).isFalse();
	}

	@Test
	void should_return_true_when_number_is_zero_and_checking_is_non_null_and_is_zero() {

		assertThat(NumberUtils.<Integer>isNonNullAndIsZero(0)).isTrue();
	}

	@Test
	void should_return_false_when_number_is_greater_than_zero_and_checking_is_non_null_and_is_zero() {

		assertThat(NumberUtils.<Integer>isNonNullAndIsZero(1)).isFalse();
	}

	@Test
	void should_return_true_when_number_is_null_and_checking_is_null_or_is_zero() {

		assertThat(NumberUtils.<Integer>isNullOrZero(null)).isTrue();
	}

	@Test
	void should_return_true_when_number_is_zero_and_checking_is_null_or_is_zero() {

		assertThat(NumberUtils.<Integer>isNullOrZero(0)).isTrue();
	}

	@Test
	void should_return_false_when_number_is_greater_than_zero_and_checking_is_null_or_is_zero() {

		assertThat(NumberUtils.<Integer>isNullOrZero(1)).isFalse();
	}


}