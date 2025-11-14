package com.techyourchance.coroutines.exercises.exercise7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Exercise7Test {

	/*
	Write nested withContext blocks, explore the resulting Job's hierarchy, test cancellation
	of the outer scope
	 */
	@Test
	fun nestedWithContext() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.IO)


			scopeJob.join()
			println("test done")
		}
	}

	/*
	Launch new coroutine inside another coroutine, explore the resulting Job's hierarchy, test cancellation
	of the outer scope, explore structured concurrency
	 */
	@Test
	fun nestedLaunchBuilders() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.IO)


			scopeJob.join()
			println("test done")
		}
	}

	/*
	Launch new coroutine on "outer scope" inside another coroutine, explore the resulting Job's hierarchy,
	test cancellation of the outer scope, explore structured concurrency
	 */
	@Test
	fun nestedCoroutineInOuterScope() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.IO)


			scopeJob.join()
			println("test done")
		}
	}


}
