import io.github.humbleui.jwm.App
import org.koin.core.context.startKoin
import ui.mainWindow

fun main() {
    // DIの構成
    startKoin {
        printLogger()
        // TODO logger()
        modules(appModule())
    }

    App.start {
        mainWindow()
    }
}
