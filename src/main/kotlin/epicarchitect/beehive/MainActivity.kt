package epicarchitect.beehive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import epicarchitect.beehive.data.Task
import epicarchitect.beehive.data.TaskId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeehiveTheme {
                Tasks()
            }
        }
    }
}

@Composable
fun Tasks() {
    Box(Modifier.fillMaxSize()) {
        val coroutineScope = rememberCoroutineScope()
        val taskIds by Beehive.taskIdsFlow().collectAsState(initial = emptyList())

        LazyColumn(
            contentPadding = PaddingValues(
                top = 50.dp,
                bottom = 120.dp,
                start = 8.dp,
                end = 8.dp
            )
        ) {
            items(taskIds, key = { it }) { taskId ->
                TaskItem(taskId)
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    horizontal = 24.dp,
                    vertical = 64.dp
                ),
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    Beehive.taskSavingFlow("test ${(0..99999).random()}").collect()
                }
            },
            content = {
                Text(text = "Add task")
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TaskItem(taskId: TaskId) {
    val taskContent by Beehive.taskContentFlow(taskId).collectAsState(initial = null)
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
        ) {

            Text(
                modifier = Modifier.padding(4.dp),
                text = taskContent ?: ""
            )

            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        coroutineScope.launch(Dispatchers.IO) {
                            Beehive
                                .taskDeletionFlow(taskId)
                                .collect()
                        }
                    },
                text = "Delete",
                color = Color.Red
            )
        }
    }
}