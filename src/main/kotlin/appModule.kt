import org.koin.dsl.module
import ui.screens.hello.HelloScreenModel

fun appModule() = module {
    factory { HelloScreenModel() }
}
