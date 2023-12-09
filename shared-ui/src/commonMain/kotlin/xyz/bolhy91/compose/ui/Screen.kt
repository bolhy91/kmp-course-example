package xyz.bolhy91.compose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String) {
    object TimeZonesScreen : Screen("Timezones")
    object FindTimeScreen : Screen("Find Time")
}

data class BottomItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomItem(
        route = Screen.TimeZonesScreen.title,
        icon = Icons.Filled.Language,
        iconContentDescription = "Timezones"
    ),
    BottomItem(
        route = Screen.FindTimeScreen.title,
        icon = Icons.Filled.Place,
        iconContentDescription = "Find Time"
    )
)
