package epicarchitect.beehive.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert
    fun insertTask(task: Task)

    @Query("DELETE FROM tasks WHERE id = :id")
    fun deleteById(id: TaskId)

    @Query("SELECT * FROM tasks")
    fun tasks(): Flow<List<Task>>

}