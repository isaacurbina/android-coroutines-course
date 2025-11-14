package com.techyourchance.coroutines.exercises.exercise8

import com.techyourchance.coroutines.common.ThreadInfoLogger.logThreadInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UsersDao {

	suspend fun upsertUserInfo(user: User) = withContext(Dispatchers.IO) {
		logThreadInfo("upsertUserInfo($user)")
		delay(1500)
		logThreadInfo("upsertUserInfo -> done!)")
	}
}
