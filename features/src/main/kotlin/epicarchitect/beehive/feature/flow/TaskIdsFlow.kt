package epicarchitect.beehive.feature.flow

import epicarchitect.beehive.data.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun taskIdsFlow(
    tasksFlow: Flow<List<Task>>
) = tasksFlow.map {
    it.map { it.id }
}