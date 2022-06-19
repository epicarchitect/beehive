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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import epicarchitect.beehive.database.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val tasksDao by lazy {
        Beehive.database.tasksDao
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeehiveTheme {
                Box(Modifier.fillMaxSize()) {
                    val tasks by tasksDao.tasks().collectAsState(initial = emptyList())
                    val coroutineScope = rememberCoroutineScope()

                    LazyColumn(
                        contentPadding = PaddingValues(
                            top = 50.dp,
                            bottom = 120.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                    ) {
                        items(tasks, key = { it.id }) { item ->
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
                                        text = item.content
                                    )

                                    Text(
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .clickable {
                                                coroutineScope.launch(Dispatchers.IO) {
                                                    tasksDao.deleteById(item.id)
                                                }
                                            },
                                        text = "Delete",
                                        color = Color.Red
                                    )
                                }
                            }
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
                                tasksDao.insertTask(Task(0, "test ${(0..99999).random()}"))
                            }
                        },
                        content = {
                            Text(text = "Add task")
                        }
                    )
                }
            }
        }
    }
}