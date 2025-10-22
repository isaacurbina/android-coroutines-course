package com.techyourchance.coroutines.exercises.exercise1

import com.techyourchance.coroutines.common.ThreadInfoLogger
import kotlin.random.Random

class GetReputationEndpoint {
	suspend fun getReputation(userId: String): Int {
		ThreadInfoLogger.logThreadInfo("GetReputationEndpoint#getReputation($userId): called")
		Thread.sleep(3000)
		ThreadInfoLogger.logThreadInfo("GetReputationEndpoint#getReputation($userId): return data")
		return Random.nextInt(0, 100)
	}
}
