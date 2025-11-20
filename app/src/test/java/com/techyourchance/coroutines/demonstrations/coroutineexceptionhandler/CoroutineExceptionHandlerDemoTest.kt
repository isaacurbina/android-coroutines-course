package com.techyourchance.coroutines.demonstrations.coroutineexceptionhandler

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineExceptionHandlerDemoTest {

	@Test
	fun uncaughtException() {
		runBlocking {
			val scope = CoroutineScope(Dispatchers.Default)
			val job = scope.launch {
				delay(50)
				throw RuntimeException()
			}
			job.join()
		}
		Thread.sleep(100)
		println("test completed")
	}

	@Test
	fun caughtException() {
		runBlocking {
			val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
				println("Caught exception: $throwable")
			}

			val scope = CoroutineScope(Dispatchers.Default + coroutineExceptionHandler)
			val job = scope.launch {
				delay(50)
				throw RuntimeException()
			}
			job.join()
		}
		Thread.sleep(100)
		println("test completed")
	}

	@Test
	fun caughtExceptionAtCoroutineLevel() {
		runBlocking {
			val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
				println("Caught exception: $throwable")
			}

			val scope = CoroutineScope(Dispatchers.Default)
			val job = scope.launch(coroutineExceptionHandler) {
				delay(50)
				throw RuntimeException()
			}
			job.join()
		}
		Thread.sleep(100)
		println("test completed")
	}


}
