package epicarchitect.beehive

import android.app.Application
import androidx.room.Room
import epicarchitect.beehive.data.TaskId
import epicarchitect.beehive.database.BeehiveRoomDatabase
import epicarchitect.beehive.feature.TaskContentFeature
import epicarchitect.beehive.feature.TaskIdsFeature
import epicarchitect.beehive.feature.TasksRepository
import epicarchitect.beehive.impl.TasksRepositoryImpl
import kotlinx.coroutines.CoroutineScope

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

        val tasksRepository: TasksRepository by lazy {
            TasksRepositoryImpl(database.tasksDao)
        }

        fun createTaskIdsFeature(
            coroutineScope: CoroutineScope
        ) = TaskIdsFeature(
            tasksRepository,
            coroutineScope
        )

        fun createTaskContentFeature(
            coroutineScope: CoroutineScope,
            taskId: TaskId
        ) = TaskContentFeature(
            tasksRepository,
            coroutineScope,
            taskId
        )
    }
}