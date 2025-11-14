package com.techyourchance.coroutines.solutions.exercise7

import com.techyourchance.coroutines.common.TestUtils.printJobsHierarchy
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

class Exercise7SolutionTest {

	/*
	Write nested withContext blocks, explore the resulting Job's hierarchy, test cancellation
	of the outer scope
	 */
	@Test
	fun nestedWithContext() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.IO)
			val job = scope.launch(CoroutineName("coroutine")) {
				delay(1000)
				try {
					withContext(CoroutineName("withContext") + Dispatchers.Default) {
						try {
							delay(1000)
							withContext(CoroutineName("nested withContext")) {
								try {
									delay(1000)
									printJobsHierarchy(scopeJob)
									println("nested withContext completed")
								} catch (e: CancellationException) {
									println("nested withContext cancelled: ${e.localizedMessage}")
								}

							}
							println("withContext completed")
						} catch (e: CancellationException) {
							println("withContext cancelled: ${e.localizedMessage}")
						}
					}
					println("coroutine completed")
				} catch (e: CancellationException) {
					println("coroutine cancelled: ${e.localizedMessage}")
				}
			}

			launch {
				delay(2500)
				scope.cancel()
			}
			job.join()
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
			val job = scope.launch(CoroutineName("coroutine")) {
				delay(1000)
				try {
					withContext(CoroutineName("withContext") + Dispatchers.Default) {
						try {
							delay(1000)
							launch(CoroutineName("nested coroutine")) {
								try {
									delay(1000)
									printJobsHierarchy(scopeJob)
									println("nested coroutine completed")
								} catch (e: CancellationException) {
									println("nested coroutine cancelled: ${e.localizedMessage}")
								}
							}
							println("withContext completed")
						} catch (e: CancellationException) {
							println("withContext cancelled: ${e.localizedMessage}")
						}
					}
					println("coroutine completed")
				} catch (e: CancellationException) {
					println("coroutine cancelled: ${e.localizedMessage}")
				}
			}

			launch {
				delay(2500)
				scope.cancel()
			}
			job.join()
			println("test done")
		}
	}

	/*
	Launch new coroutine on "outer scope" inside another coroutine, explore the resulting Job's hierarchy,
	test cancellation of the outer scope, explore structured concurrency
	 */
	@Test
	fun nestedLaunchBuildersInOuterScope() {
		runBlocking {
			val scopeJob = Job()
			val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.IO)
			val job = scope.launch(CoroutineName("coroutine")) {
				delay(1000)
				try {
					withContext(CoroutineName("withContext") + Dispatchers.Default) {
						try {
							delay(1000)
							scope.launch(CoroutineName("nested coroutine")) {
								try {
									delay(1000)
									printJobsHierarchy(scopeJob)
									println("nested coroutine completed")
								} catch (e: CancellationException) {
									println("nested coroutine cancelled: ${e.localizedMessage}")
								}
							}
							println("withContext completed")
						} catch (e: CancellationException) {
							println("withContext cancelled: ${e.localizedMessage}")
						}
					}
					println("coroutine completed")
					delay(2000)
				} catch (e: CancellationException) {
					println("coroutine cancelled: ${e.localizedMessage}")
				}
			}

			launch {
				delay(2500)
				scope.cancel()
			}
			job.join()
			println("test done")
		}
	}
}
