package epicarchitect.beehive.impl

import epicarchitect.beehive.data.Task
import epicarchitect.beehive.data.TaskId
import epicarchitect.beehive.database.RoomTask
import epicarchitect.beehive.database.RoomTasksDao
import epicarchitect.beehive.feature.TasksRepository
import kotlinx.coroutines.flow.map

class TasksRepositoryImpl(
    private val tasksDao: RoomTasksDao
) : TasksRepository {

    override suspend fun insertTask(task: Task) = tasksDao.insertTask(task.toRoom())

    override suspend fun deleteById(id: TaskId) = tasksDao.deleteById(id)

    override fun tasksFlow() = tasksDao.tasks().map { it.map { it.toDomain() } }

    private fun Task.toRoom() = RoomTask(id, content)

    private fun RoomTask.toDomain() = Task(id, content)
}