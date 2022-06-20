package epicarchitect.beehive.feature.flow

import android.util.Log
import epicarchitect.beehive.data.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

fun taskContentFlow(
    taskFlow: Flow<Task?>,
    tikTikFlow: Flow<Int>
) = combine(tikTikFlow, taskFlow) { tik, task ->
    val content = task?.content
    Log.d("test123", "tik $tik for $content")
    "$content, $tik"
}