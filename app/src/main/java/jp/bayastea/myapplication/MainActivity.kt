package jp.bayastea.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jp.bayastea.myapplication.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Top(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable

fun Top(modifier: Modifier) {
    val navController: NavHostController = rememberNavController()
    Column(modifier = modifier, verticalArrangement = Arrangement.Bottom) {
        TopAppBar(
            title = { Text("TopAppBar") },
            actions = {
                IconButton(onClick = { /* do something */ }) {
                    Icon(Icons.Filled.AddCircle, contentDescription = "Edit text")
                }
            }
        )
        createNavHost(navController = navController, modifier = Modifier.weight(1.0F))
        createBottomNavigation(navController = navController)
    }
}

@Composable
fun createNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "recentUsed",
        modifier = modifier
    ) {
        composable("recentUsed") { RecentUsedContent() }
        composable("favorite") { FavoriteContent("favorite") }
        composable("all") { AllContent(name = "all") }
    }
}

@Composable
fun createBottomNavigation(navController: NavHostController) {
    val items = listOf(
        Screen.RecentUsed,
        Screen.Favorite,
        Screen.All
    )

    BottomNavigation() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

fun callSelectFile() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Top(Modifier.fillMaxSize())
    }
}