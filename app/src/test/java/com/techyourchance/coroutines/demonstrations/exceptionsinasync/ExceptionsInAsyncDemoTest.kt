package com.techyourchance.coroutines.demonstrations.exceptionsinasync

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExceptionsInAsyncDemoTest {

	@Test
	fun uncaughtExceptionInConcurrentCoroutinesWithLaunch() {
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
	fun uncaughtExceptionInConcurrentCoroutinesWithAsync() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + Dispatchers.Default)
			val job1 = scope.launch {
				delay(100)
				println("inside coroutine")
			}
			val job2 = scope.async {
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
	fun uncaughtExceptionInConcurrentCoroutinesWithAsyncAndDeferred() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + Dispatchers.Default)
			val job1 = scope.launch {
				delay(100)
				println("inside coroutine")
			}
			val deferred = scope.async<Int> {
				delay(50)
				throw RuntimeException("My EXCEPTION")
			}
			joinAll(job1, deferred)
			println("scopeJob: $scopeJob")
			println("job1: $job1")
			println("job2: $deferred")
			try {
				val result = deferred.await()
			} catch (e: Throwable) {
				println("Caught exception: $e")
			}
		}
		Thread.sleep(100)
		println("test completed")
	}

	@Test
	fun uncaughtExceptionInConcurrentCoroutinesWithAsyncAndDeferredAndSupervision() {
		runBlocking {
			val scopeJob = SupervisorJob()
			val scope = CoroutineScope(scopeJob + Dispatchers.Default)
			val job1 = scope.launch {
				delay(100)
				println("inside coroutine")
			}
			val deferred = scope.async<Int> {
				delay(50)
				throw RuntimeException("My EXCEPTION")
			}
			joinAll(job1, deferred)
			println("scopeJob: $scopeJob")
			println("job1: $job1")
			println("job2: $deferred")
			try {
				val result = deferred.await()
			} catch (e: Throwable) {
				println("Caught exception: $e")
			}
		}
		Thread.sleep(100)
		println("test completed")
	}
}
