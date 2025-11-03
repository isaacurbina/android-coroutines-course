package com.techyourchance.coroutines.demonstrations.structuredconcurrency.kotlin

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.math.BigInteger

@ExperimentalCoroutinesApi
class FibonacciUseCaseUiCoroutinesTest {

	private lateinit var SUT: FibonacciUseCaseUiCoroutines

	@Before
	fun setup() {
		SUT = FibonacciUseCaseUiCoroutines()
	}

	@Test
	fun computeFibonacci_0_returns0() {
		runBlocking {
			// Arrange
			// Act
			val result = SUT.computeFibonacci(0)
			// Assert
			assertThat(result, `is`(BigInteger("0")))
		}
	}

	@Test
	fun computeFibonacci_1_returns1() {
		runBlocking {
			// Arrange
			// Act
			val result = SUT.computeFibonacci(1)
			// Assert
			assertThat(result, `is`(BigInteger("1")))
		}
	}


	@Test
	fun computeFibonacci_10_returnsCorrectResult() {
		runBlocking {
			// Arrange
			// Act
			val result = SUT.computeFibonacci(10)
			// Assert
			assertThat(result, `is`(BigInteger("55")))
		}
	}

	@Test
	fun computeFibonacci_30_returnsCorrectResult() {
		runBlocking {
			// Arrange
			// Act
			val result = SUT.computeFibonacci(30)
			// Assert
			assertThat(result, `is`(BigInteger("832040")))
		}
	}

}
