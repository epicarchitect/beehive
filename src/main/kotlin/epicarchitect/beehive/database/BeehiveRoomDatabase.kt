package epicarchitect.beehive.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomTask::class],
    version = 1
)
abstract class BeehiveRoomDatabase : RoomDatabase() {
    abstract val tasksDao: RoomTasksDao
}