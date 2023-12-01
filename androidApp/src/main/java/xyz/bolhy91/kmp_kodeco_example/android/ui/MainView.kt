package xyz.bolhy91.kmp_kodeco_example.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import xyz.bolhy91.kmp_kodeco_example.android.MyApplicationTheme
import xyz.bolhy91.kmp_kodeco_example.android.bottomNavigationItems

@Composable
fun MainView(
    actionBarFun: topBarFun = { EmptyComposable() }
) {
    val showAddDialog = remember {
        mutableStateOf(false)
    }
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    MyApplicationTheme {
        Scaffold(
            topBar = {
                actionBarFun(selectedIndex.intValue)
            },
            floatingActionButton = {
                if (selectedIndex.intValue == 0) {
                    FloatingActionButton(
                        modifier = Modifier.padding(16.dp),
                        shape = FloatingActionButtonDefaults.largeShape,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        onClick = { showAddDialog.value = true }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Timezone")
                    }
                }
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    bottomNavigationItems.forEachIndexed { i, bottomItem ->
                        NavigationBarItem(
                            selected = selectedIndex.intValue == i ,
                            onClick = { selectedIndex.intValue = i },
                            icon = {
                                Icon(
                                    bottomItem.icon,
                                    contentDescription = bottomItem.iconContentDescription
                                )
                            },
                            label = {
                                Text(text = bottomItem.route, style = MaterialTheme.typography.bodyMedium)
                            },
                            colors =  NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedIconColor = Color.Black,
                                unselectedTextColor = Color.Black,
                                indicatorColor = MaterialTheme.colorScheme.primary,
                            )
                        )
                    }
                }
            }
        ) { paddingValues ->  
            Box(modifier = Modifier.padding(paddingValues))
        }
    }
}