package epicarchitect.beehive.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class RoomTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val content: String
)