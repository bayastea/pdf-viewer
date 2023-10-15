package jp.bayastea.myapplication

import android.app.Fragment
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jp.bayastea.myapplication.R

class TopFragment() {


    @Composable
    fun Top(modifier: Modifier) {
        Column() {
            Text(text = "一覧", modifier = Modifier.weight(1.0F, true))
            BottomNavigation(modifier = modifier, contentColor = Color(R.color.purple_200)) {
            }
        }
    }
}