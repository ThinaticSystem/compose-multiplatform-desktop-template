package ui.screens.hello

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import ui.components.Center

data class HelloScreen(private val modifier: Modifier) : Screen {
    @Composable
    override fun Content() {
        val model = this.getScreenModel<HelloScreenModel>()
        val state by model.state.collectAsState()

        Center(modifier = this.modifier.fillMaxSize()) {
            Button(
                onClick = { model.toggleState() },
                modifier = Modifier.testTag("button"),
            ) {
                Text(
                    text = when (state) {
                        is HelloScreenModel.State.Init -> "Hello, Desktop!"
                        is HelloScreenModel.State.Second -> "こんにちわーるど！"
                    }
                )
            }
        }
    }
}
