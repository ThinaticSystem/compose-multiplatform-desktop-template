import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import cafe.adriel.voyager.navigator.Navigator
import org.junit.Rule
import org.junit.Test
import ui.HelloScreen

class HelloScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun textChangesWhenButtonIsClicked() {
        rule.setContent {
            Navigator(HelloScreen(modifier = Modifier))
        }

        rule.onNodeWithTag("button").assertTextEquals("Hello, Desktop!")
        rule.onNodeWithTag("button").performClick()
        rule.onNodeWithTag("button").assertTextEquals("こんにちわーるど！")
    }
}
