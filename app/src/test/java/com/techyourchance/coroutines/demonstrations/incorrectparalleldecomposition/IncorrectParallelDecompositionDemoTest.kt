package com.techyourchance.coroutines.demonstrations.incorrectparalleldecomposition

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

class IncorrectParallelDecompositionDemoTest {

	@Test
	fun wrongUseOfCoroutines() = runBlocking {
		var totalIterations = 0
		withContext(Dispatchers.Default) {
			for (duration in 1..5) {
				launch {
					val startTimeNano = System.nanoTime()
					var iterations = 0
					while (System.nanoTime() < startTimeNano + (duration * 10f.pow(9))) {
						iterations++
					}
					totalIterations += iterations
				}
			}
		}
		println("total iterations: $totalIterations")
	}

	@Test
	fun atomicityProblemDemo() = runBlocking {
		val totalIterations = AtomicInteger(0)
		withContext(Dispatchers.Default) {
			repeat(100) {
				launch {
					delay(100)
					totalIterations.incrementAndGet()
				}
			}
		}
		println("total iterations: $totalIterations")
	}
}
