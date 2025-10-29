package com.techyourchance.coroutines.demonstrations.structuredconcurrency.java;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

public class FibonacciUseCaseTest {

	FibonacciUseCase SUT;

	@Before
	public void setup() {
		SUT = new FibonacciUseCase();
	}

	@Test
	public void computeFibonacci_0_returns0() {
		// Arrange
		// Act
		BigInteger result = SUT.computeFibonacci(0);
		// Assert
		assertThat(result, is(new BigInteger("0")));
	}

	@Test
	public void computeFibonacci_1_returns1() {
		// Arrange
		// Act
		BigInteger result = SUT.computeFibonacci(1);
		// Assert
		assertThat(result, is(new BigInteger("1")));
	}

	@Test
	public void computeFibonacci_10_returnsCorrectAnswer() {
		// Arrange
		// Act
		BigInteger result = SUT.computeFibonacci(10);
		// Assert
		assertThat(result, is(new BigInteger("55")));
	}

	@Test
	public void computeFibonacci_30_returnsCorrectAnswer() {
		// Arrange
		// Act
		BigInteger result = SUT.computeFibonacci(30);
		// Assert
		assertThat(result, is(new BigInteger("832040")));
	}

	@Test
	public void computeFibonacci_31_returnsCorrectAnswer() {
		// Arrange
		// Act
		BigInteger result = SUT.computeFibonacci(31);
		// Assert
		assertThat(result, is(new BigInteger("1346269")));
	}
}
