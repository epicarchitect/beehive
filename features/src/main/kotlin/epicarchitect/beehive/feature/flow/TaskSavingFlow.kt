package epicarchitect.beehive.feature.flow

import kotlinx.coroutines.flow.flow

fun taskSavingFlow(
    save: suspend () -> Unit
) = flow {
    emit(save())
}