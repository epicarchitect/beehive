package epicarchitect.beehive.data

typealias TaskId = Int
typealias TaskContent = String

data class Task(
    val id: TaskId,
    val content: TaskContent
)