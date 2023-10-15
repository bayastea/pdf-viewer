package jp.bayastea.myapplication.ui.theme.screen

import androidx.annotation.StringRes
import jp.bayastea.myapplication.R

/**
 * BottomNavigationに設定する画面名
 */
sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object RecentUsed : Screen("recentUsed", R.string.screen_recent_used)
    object Favorite : Screen("favorite", R.string.screen_favorite)
    object All : Screen("all", R.string.screen_all)
}