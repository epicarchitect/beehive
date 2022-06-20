package epicarchitect.beehive.feature.flow

import epicarchitect.beehive.data.TaskId
import kotlinx.coroutines.flow.flow

fun taskDeletionFlow(
    delete: suspend () -> Unit
) = flow {
    emit(delete())
}