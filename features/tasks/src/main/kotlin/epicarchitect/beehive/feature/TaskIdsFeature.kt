package epicarchitect.beehive.feature

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TaskIdsFeature(
    tasksRepository: TasksRepository,
    coroutineScope: CoroutineScope
) {

    val state = tasksRepository.tasksFlow().map {
        it.map { it.id }
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), emptyList())

}