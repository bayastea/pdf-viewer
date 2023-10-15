package jp.bayastea.myapplication.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.bayastea.myapplication.R

class RecentUsed() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RecentUsedContent()
                }
            }
        }
    }
}

@Composable
fun RecentUsedContent() {

    Column() {

        MessageList(
            messages = listOf(
                PdfItem(
                    "太陽の塔", "2023.09.28", "10KB"
                ),
                PdfItem(
                    "夜は短し歩けよ乙女", "2023.09.28", "10KB"
                ),
                PdfItem(
                    "聖なる怠け者の冒険", "2023.09.28", "10KB"
                ),
                PdfItem(
                    "四畳半神話大系", "2023.09.28", "10KB"
                ),
                PdfItem(
                    "有頂天家族", "2023.09.28", "10KB"
                ),
            )
        )
    }
}

@Composable
fun MessageList(messages: List<PdfItem>) {
    LazyColumn(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // itemごとにspace追加
    ) {
        items(messages) { message ->
            RecentUSedItem(message = message)
        }
    }
}

data class PdfItem(
    val title: String,
    val date: String,
    val fileSize: String
)

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecentUSedItem(message: PdfItem) {

    val showModal = remember { mutableStateOf(false) }
    Column(
        Modifier
            .border(
                width = 1.dp,
                color = Color(R.color.app_main_blue),
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .padding(16.dp, 16.dp),
    ) {
        Text(text = message.title)
        Row(Modifier.align(alignment = Alignment.End)) {
            Text(text = message.date)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = message.fileSize)
            Spacer(modifier = Modifier.padding(8.dp))
            Image(
                painter = painterResource(R.drawable.more_horiz_24px),
                contentDescription = "Show Menu",
                Modifier.clickable {

                    showModal.value = true
                }
            )
        }
    }

    if (showModal.value) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            onDismissRequest = { showModal.value = false },
            dragHandle = null
        ) {
            // Sheet content
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.width(32.dp))
                Row(Modifier.clickable {
                    showModal.value = false
                }) {
                    Image(painter = rememberVectorPainter(image = Icons.Filled.Favorite), contentDescription = "Add favorite")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "お気に入りに追加")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row() {
                    Image(painter = rememberVectorPainter(image = Icons.Filled.Delete), contentDescription = "Add favorite")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "削除")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row() {
                    Image(painter = rememberVectorPainter(image = Icons.Filled.Edit), contentDescription = "Add favorite")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "名前を変更")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        RecentUsedContent()
    }
}