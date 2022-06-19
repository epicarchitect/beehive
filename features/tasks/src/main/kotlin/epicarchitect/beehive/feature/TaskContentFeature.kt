package epicarchitect.beehive.feature

import android.util.Log
import epicarchitect.beehive.data.TaskId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TaskContentFeature(
    tasksRepository: TasksRepository,
    coroutineScope: CoroutineScope,
    private val taskId: TaskId
) {

    val state = combine(TikTik.everySecond(), tasksRepository.tasksFlow()) { tik, tasks ->
        val content = tasks.find { it.id == taskId }?.content
        Log.d("test123", "tik $tik for $content")
        "$content, $tik"
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

}

