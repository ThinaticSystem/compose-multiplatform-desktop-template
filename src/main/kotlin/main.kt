import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import ui.MainWindow

fun main() {
    // DIの構成
    startKoin {
        printLogger()
        // TODO logger()
        modules(appModule())
    }

    application {
        MainWindow()
    }
}
