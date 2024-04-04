package com.opentourism.tour
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import com.opentourism.tour.ui.AccountScreen
import com.opentourism.tour.ui.HomeScreen
import com.opentourism.tour.ui.ProfileScreen
import com.russhwolf.settings.Settings
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import travel_buddy.shared.generated.resources.Res


@Composable
fun App() {
    val screens = listOf("Home", "Post", "Jobs")
    var selectedScreen by remember { mutableStateOf(screens.first()) }
    val settings = Settings()
    val isFirstTime = settings.getBoolean("isFirstTime",  defaultValue = true)
    val onboardingCompleted = remember { mutableStateOf(!isFirstTime) }

    MaterialTheme {
        if (onboardingCompleted.value) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        screens.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        getIconForScreen(screen), contentDescription = screen,
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                selected = screen == selectedScreen,
                                onClick = { selectedScreen = screen },
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                },
                content = {
                    when (selectedScreen) {
                        "Home" -> Navigator(HomeScreen)
                        "Post" -> Navigator(AccountScreen)
                        "Jobs" -> Navigator(ProfileScreen)
                    }
                }
            )
        } else {
            OnboardingScreen  (
                screens = screens,
                imageResources = listOf("drawable/takeoff.png", "drawable/backpack.png", "drawable/traveler.png"),
                onboardingCompleted = {
                    onboardingCompleted.value = true
                    settings.putBoolean("isFirstTime", false)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(screens: List<String>, imageResources: List<String>, onboardingCompleted: () -> Unit) {
    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HorizontalPager(
            state = rememberPagerState(pageCount = { screens.size }),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            currentPage = page
            OnboardingContent(screen = screens[page], path = imageResources[page])
        }

        DotsIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            currentPage = currentPage,
            pageCount = screens.size
        )

        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onboardingCompleted
        ) {
            Text("Skip")
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingContent(screen: String, path : String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(DrawableResource(path)),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome to $screen",
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Composable
fun DotsIndicator(modifier: Modifier = Modifier, currentPage: Int, pageCount: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            Dot(isSelected = index == currentPage)
        }
    }
}

@Composable
fun Dot(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(
                color = if (isSelected) Color.Black else Color.Gray,
                shape = CircleShape
            )
            .padding(4.dp)
    )
}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "Home" -> Icons.Default.Home
        "Post" -> Icons.Default.Notifications
        "Jobs" -> Icons.Default.AccountBox
        else -> Icons.Default.Home
    }
}






