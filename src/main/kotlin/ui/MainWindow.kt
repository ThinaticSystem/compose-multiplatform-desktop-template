package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import club.eridani.compose.jwm.ApplicationWindow
import ui.screens.hello.HelloScreen
import ui.theme.MainTheme
import kotlin.system.exitProcess

fun mainWindow() {
    ApplicationWindow(
        onClose = { exitProcess(0) },
    ) {
        MainWindowContainer()
    }
}

@Preview
@Composable
fun MainWindowContainer() {
    MainTheme {
        Surface {
            Content()
        }
    }
}

@Composable
private fun Content(modifier: Modifier = Modifier) {
    Navigator(HelloScreen(modifier))
}
