@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package ui.screens.hello

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import appModule
import cafe.adriel.voyager.navigator.Navigator
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

class HelloScreenTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule())
    }

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun ボタンを押すとテキストが変わる() {
        this.rule.setContent {
            Navigator(HelloScreen(modifier = Modifier))
        }

        this.rule.apply {
            onNodeWithTag("button").apply {
                assertTextEquals("Hello, Desktop!")
                performClick()
                assertTextEquals("こんにちわーるど！")
            }
        }
    }
}
