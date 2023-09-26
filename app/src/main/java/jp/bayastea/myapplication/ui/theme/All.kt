package jp.bayastea.myapplication.ui.theme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AllContent(name: String) {
    Text(name)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewALl() {
    MyApplicationTheme {
        AllContent("Android")
    }
}
