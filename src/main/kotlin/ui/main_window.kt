package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import ui.theme.MainTheme

@Composable
fun ApplicationScope.MainWindow() {
    Window(onCloseRequest = ::exitApplication) {
        MainWindowContent()
    }
}

@Preview
@Composable
fun MainWindowContent() {
    var text by remember { mutableStateOf("Hello, Desktop!") }

    MainTheme {
        Button(
            onClick = { text = "こんにちわーるど！" },
            modifier = Modifier.testTag("button")
        ) {
            Text(text)
        }
    }
}
