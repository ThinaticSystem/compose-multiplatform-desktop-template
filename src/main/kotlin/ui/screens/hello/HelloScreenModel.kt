package ui.screens.hello

import cafe.adriel.voyager.core.model.StateScreenModel

class HelloScreenModel : StateScreenModel<HelloScreenModel.State>(State.Init) {
    sealed class State {
        data object Init : State()
        data object Second : State()
    }

    /** [state] に [State.Init] <-> [State.Second] をかわりばんこでセットする */
    fun toggleState() {
        this.mutableState.apply {
            value = when (value) {
                is State.Init -> State.Second
                is State.Second -> State.Init
            }
        }
    }
}
