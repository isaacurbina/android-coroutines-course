package com.techyourchance.coroutines.demonstrations.structuredconcurrency.java;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

public class FibonacciUseCaseAsyncUiTest {

	FibonacciUseCaseAsyncUi.Callback mCallback;
	FibonacciUseCaseAsyncUi SUT;

	BigInteger lastResult = null;

	@Before
	public void setup() {
		mCallback = result -> lastResult = result;
		SUT = new FibonacciUseCaseAsyncUi();
	}

	@Test
	public void computeFibonacci_0_returns0() throws Exception {
		// Arrange
		// Act
		SUT.computeFibonacci(0, mCallback);
		Thread.sleep(10);
		// Assert
		assertThat(lastResult, is(new BigInteger("0")));
	}

	@Test
	public void computeFibonacci_1_returns1() throws Exception {
		// Arrange
		// Act
		SUT.computeFibonacci(1, mCallback);
		Thread.sleep(10);
		// Assert
		assertThat(lastResult, is(new BigInteger("1")));
	}

	@Test
	public void computeFibonacci_10_returnsCorrectAnswer() throws Exception {
		// Arrange
		// Act
		SUT.computeFibonacci(10, mCallback);
		Thread.sleep(200);
		// Assert
		assertThat(lastResult, is(new BigInteger("55")));
	}

	@Test
	public void computeFibonacci_30_returnsCorrectAnswer() throws Exception {
		// Arrange
		// Act
		SUT.computeFibonacci(30, mCallback);
		Thread.sleep(200);
		// Assert
		assertThat(lastResult, is(new BigInteger("832040")));
	}
}
