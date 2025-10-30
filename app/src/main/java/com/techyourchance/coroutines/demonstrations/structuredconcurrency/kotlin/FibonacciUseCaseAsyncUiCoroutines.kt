package com.techyourchance.coroutines.demonstrations.structuredconcurrency.kotlin

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

internal class FibonacciUseCaseAsyncUiCoroutines(private val bgDispatcher: CoroutineDispatcher) {

	interface Callback {
		fun onFibonacciComputed(result: BigInteger?)
	}

	private val coroutineScope = CoroutineScope(Dispatchers.Main)

	fun computeFibonacci(index: Int, callback: Callback) {
		coroutineScope.launch {
			val result = computeFibonacciBg(index)
			callback.onFibonacciComputed(result)
		}
	}

	private suspend fun computeFibonacciBg(index: Int): BigInteger = withContext(bgDispatcher) {
		when (index) {
			0 -> BigInteger("0")
			1 -> BigInteger("1")
			else -> computeFibonacciBg(index - 1).add(computeFibonacciBg(index - 2))
		}
	}
}
