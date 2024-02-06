package service.store.credential

import com.microsoft.credentialstorage.model.StoredCredential
import java.util.*

class Credential private constructor(
    private val storedCredential: StoredCredential,
) {
    companion object {
        /**
         * ファクトリーメソッド
         *
         * @param password このメソッドの呼び出しにより値は0で埋められる
         */
        fun create(userName: String, password: CharArray): Credential {
            val credential = StoredCredential(userName, password)
            Arrays.fill(password, 0x00.toChar())
            return Credential(credential)
        }
    }

    sealed class State {
        /** [consume]未実行 (実行可能) */
        data object NotConsumed : State()

        /** [consume]実行済み (実行不能) */
        data object Consumed : State()
    }

    var state: State = State.NotConsumed
        private set

    /**
     * 値を利用するブロックを提供する
     *
     * 注意: 1度のみ実行可能
     *
     * @param block 値を利用するブロック
     *
     * @throws IllegalStateException 2回以上の[consume]の実行でthrowされる
     */
    fun <R> consume(block: (StoredCredential) -> R): R {
        when (this.state) {
            is State.NotConsumed ->
                this.state = State.Consumed

            is State.Consumed ->
                throw IllegalStateException("consume() has already been called")
        }

        return try {
            block(this.storedCredential)
        } finally {
            this.storedCredential.clear()
        }
    }
}
