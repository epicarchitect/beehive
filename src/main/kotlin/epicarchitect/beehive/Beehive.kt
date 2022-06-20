package epicarchitect.beehive

import android.app.Application
import androidx.room.Room
import epicarchitect.beehive.data.Task
import epicarchitect.beehive.data.TaskContent
import epicarchitect.beehive.data.TaskId
import epicarchitect.beehive.database.BeehiveRoomDatabase
import epicarchitect.beehive.feature.TikTik
import epicarchitect.beehive.feature.flow.TasksRepository
import epicarchitect.beehive.impl.TasksRepositoryImpl
import kotlinx.coroutines.flow.map

class Beehive : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: Beehive

        private val database by lazy {
            Room.databaseBuilder(instance, BeehiveRoomDatabase::class.java, "epicarchitect.beehive")
                .fallbackToDestructiveMigration()
                .build()
        }

        private val tasksRepository: TasksRepository by lazy {
            TasksRepositoryImpl(database.tasksDao)
        }

        fun taskContentFlow(taskId: TaskId) = epicarchitect.beehive.feature.flow.taskContentFlow(
            tasksRepository.tasksFlow().map { it.find { it.id == taskId } },
            TikTik.everySecond()
        )

        fun taskIdsFlow() = epicarchitect.beehive.feature.flow.taskIdsFlow(
            tasksRepository.tasksFlow()
        )

        fun taskSavingFlow(content: TaskContent) = epicarchitect.beehive.feature.flow.taskSavingFlow(
            save = {
                tasksRepository.saveTask(Task(0, content))
            }
        )

        fun taskDeletionFlow(taskId: TaskId) = epicarchitect.beehive.feature.flow.taskDeletionFlow(
            delete = {
                tasksRepository.deleteById(taskId)
            }
        )
    }
}