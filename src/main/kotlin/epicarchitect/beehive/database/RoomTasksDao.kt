package epicarchitect.beehive.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomTasksDao {

    @Insert
    fun insertTask(task: RoomTask)

    @Query("DELETE FROM tasks WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM tasks")
    fun tasks(): Flow<List<RoomTask>>

}