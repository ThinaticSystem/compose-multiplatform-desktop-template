package service.store.credential

import com.microsoft.credentialstorage.SecretStore
import com.microsoft.credentialstorage.StorageProvider
import com.microsoft.credentialstorage.StorageProvider.SecureOption
import com.microsoft.credentialstorage.model.StoredCredential
import service.store.credential.CredentialStore.IProp

/**
 * 他ユーザーから秘匿する値の読み書き
 *
 * 注意: Windowsの資格情報マネージャーでは同一ログインアカウントで動作する他のアプリケーションからの平文読み取りが可能
 *
 * インスタンス作成時に型引数でキー定義 ([IProp]を実装したenumクラス)を指定する
 *
 * - Windows: CredentialManager
 * - Linux: GNOME Keyring
 * - Mac OSX: Keychain
 */
class CredentialStore<P : CredentialStore.IProp> {
    /**
     * プロパティのキー名を列挙するenumのベースインターフェース
     */
    interface IProp {
        /**
         * 実際にキーとして保存される文字列
         */
        val key: String
    }

    private val credentialStorage: SecretStore<StoredCredential> =
        StorageProvider.getCredentialStorage(true, SecureOption.REQUIRED)
            ?: throw UnsupportedOperationException("No secure credential storage available")

    operator fun get(prop: P): Credential? =
        try {
            val storedCredential = this.credentialStorage[prop.key]!!
            // 未格納の値だった場合はget時にNullPointerExceptionがthrowされる
            Credential.create(storedCredential.username, storedCredential.password)
        } catch (nullPo: NullPointerException) {
            null
        }

    operator fun set(prop: P, credential: StoredCredential) {
        this.credentialStorage.add(prop.key, credential)
    }

    fun remove(prop: P) {
        this.credentialStorage.delete(prop.key)
    }
}
