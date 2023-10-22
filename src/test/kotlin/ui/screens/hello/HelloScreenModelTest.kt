@file:Suppress("NonAsciiCharacters")

package ui.screens.hello

import org.junit.Test
import kotlin.test.assertSame

class HelloScreenModelTest {
    @Test
    fun `init stateがInitにセットされている`() {
        HelloScreenModel().also { instance ->
            assertSame(HelloScreenModel.State.Init, instance.state.value)
        }
    }

    @Test
    fun `toggleState stateがInitとSecondをトグルする`() {
        HelloScreenModel().also { instance ->
            repeat(2) {
                instance.toggleState()
                assertSame(HelloScreenModel.State.Second, instance.state.value)

                instance.toggleState()
                assertSame(HelloScreenModel.State.Init, instance.state.value)
            }
        }
    }
}
