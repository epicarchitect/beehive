package epicarchitect.beehive.database

import androidx.room.Entity
import androidx.room.PrimaryKey

typealias TaskId = Int
typealias TaskContent = String

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: TaskId,
    val content: TaskContent
)