package com.techyourchance.coroutines.demonstrations.supervisorjob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SupervisorJobDemoTest {

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
	fun uncaughtExceptionInConcurrentCoroutinesWithSupervisorJob() {
		runBlocking {
			val scopeJob = SupervisorJob()
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
	fun uncaughtExceptionInConcurrentCoroutinesWithSupervisorJobAndExceptionHandler() {
		runBlocking {
			val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
				println("Caught exception")
			}
			val scopeJob = SupervisorJob()
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
