package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import cafe.adriel.voyager.navigator.Navigator
import ui.screens.hello.HelloScreen
import ui.theme.MainTheme

@Composable
fun ApplicationScope.MainWindow() {
    Window(onCloseRequest = ::exitApplication) {
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
