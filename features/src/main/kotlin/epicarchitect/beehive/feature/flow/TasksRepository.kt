package epicarchitect.beehive.feature.flow

import epicarchitect.beehive.data.Task
import epicarchitect.beehive.data.TaskId
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun saveTask(task: Task)

    suspend fun deleteById(id: TaskId)

    fun tasksFlow(): Flow<List<Task>>

}