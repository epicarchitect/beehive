package epicarchitect.beehive.feature

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

object TikTik {

    private val currentTime = MutableStateFlow(0)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                currentTime.update { it + 1 }
                delay(1000)
            }
        }
    }

    fun everySecond(action: (Int) -> Unit = {}): Flow<Int> {
        return currentTime.onEach { action(it) }
    }
}