package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import cafe.adriel.voyager.core.screen.Screen
import ui.component.Center

data class HelloScreen(private val modifier: Modifier) : Screen {
    @Composable
    override fun Content() {
        var text by remember { mutableStateOf("Hello, Desktop!") }

        Center(modifier = this.modifier.fillMaxSize()) {
            Button(
                onClick = { text = "こんにちわーるど！" },
                modifier = Modifier.testTag("button"),
            ) {
                Text(text = text)
            }
        }
    }
}
