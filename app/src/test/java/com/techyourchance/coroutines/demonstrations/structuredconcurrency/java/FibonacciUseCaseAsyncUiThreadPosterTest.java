package com.techyourchance.coroutines.demonstrations.structuredconcurrency.java;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.techyourchance.threadposter.testdoubles.ThreadPostersTestDouble;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

public class FibonacciUseCaseAsyncUiThreadPosterTest {

	FibonacciUseCaseAsyncUiThreadPoster.Callback mCallback;
	FibonacciUseCaseAsyncUiThreadPoster SUT;

	BigInteger lastResult = null;

	ThreadPostersTestDouble mThreadPostersTestDouble;

	@Before
	public void setup() {
		mThreadPostersTestDouble = new ThreadPostersTestDouble();
		mCallback = result -> lastResult = result;
		SUT = new FibonacciUseCaseAsyncUiThreadPoster(
			mThreadPostersTestDouble.getBackgroundTestDouble(),
			mThreadPostersTestDouble.getUiTestDouble()
		);
	}

	@Test
	public void computeFibonacci_0_returns0() {
		// Arrange
		// Act
		SUT.computeFibonacci(0, mCallback);
		mThreadPostersTestDouble.join();
		// Assert
		assertThat(lastResult, is(new BigInteger("0")));
	}

	@Test
	public void computeFibonacci_1_returns1() {
		// Arrange
		// Act
		SUT.computeFibonacci(1, mCallback);
		mThreadPostersTestDouble.join();
		// Assert
		assertThat(lastResult, is(new BigInteger("1")));
	}

	@Test
	public void computeFibonacci_10_returnsCorrectAnswer() {
		// Arrange
		// Act
		SUT.computeFibonacci(10, mCallback);
		mThreadPostersTestDouble.join();
		// Assert
		assertThat(lastResult, is(new BigInteger("55")));
	}

	@Test
	public void computeFibonacci_30_returnsCorrectAnswer() {
		// Arrange
		// Act
		SUT.computeFibonacci(30, mCallback);
		mThreadPostersTestDouble.join();
		// Assert
		assertThat(lastResult, is(new BigInteger("832040")));
	}

}
