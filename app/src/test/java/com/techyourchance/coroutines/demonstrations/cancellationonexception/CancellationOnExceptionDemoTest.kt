package com.techyourchance.coroutines.demonstrations.cancellationonexception

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CancellationOnExceptionDemoTest {

	@Test
	fun concurrentCoroutines() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + Dispatchers.Default)
			val job1 = scope.launch {
				delay(100)
				println("inside coroutine")
			}
			val job2 = scope.launch {
				delay(50)
			}
			joinAll(job1, job2)
			println("scopeJob: $scopeJob")
			println("job1: $job1")
			println("job2: $job2")
		}
		Thread.sleep(100)
		println("test completed")
	}

	@Test
	fun uncaughtExceptionInConcurrentCoroutines() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + Dispatchers.Default)
			val job1 = scope.launch {
				delay(100)
				println("inside coroutine")
			}
			val job2 = scope.launch {
				delay(50)
				throw RuntimeException()
			}
			joinAll(job1, job2)
			println("scopeJob: $scopeJob")
			println("job1: $job1")
			println("job2: $job2")
		}
		Thread.sleep(100)
		println("test completed")
	}


	@Test
	fun uncaughtExceptionInConcurrentCoroutinesWithExceptionHandler() {
		runBlocking {
			val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
				println("Caught exception: $throwable")
			}

			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + Dispatchers.Default + coroutineExceptionHandler)
			val job1 = scope.launch {
				delay(100)
				println("inside coroutine")
			}
			val job2 = scope.launch {
				delay(50)
				throw RuntimeException()
			}
			joinAll(job1, job2)
			println("scopeJob: $scopeJob")
			println("job1: $job1")
			println("job2: $job2")
		}
		Thread.sleep(100)
		println("test completed")
	}
}
