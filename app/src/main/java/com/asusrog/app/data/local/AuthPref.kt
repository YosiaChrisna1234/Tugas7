package com.asusrog.app.data.local

import android.content.Context
import android.content.SharedPreferences
import com.asusrog.app.data.model.ActionState
import com.asusrog.app.data.model.AuthUser
import com.asusrog.app.util.getObject
import com.asusrog.app.util.putObject

class AuthPref (val context: Context) {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(AuthPref::class.java.name, Context.MODE_PRIVATE)
    }

    private companion object {
        const val AUTH_USER = "auth"
        const val IS_LOGIN = "is login"

    }

    var authUser: AuthUser?
    get() = sp.getObject(AUTH_USER)
    private set(value) = sp.edit().putObject(AUTH_USER, value).apply()

    var isLogin: Boolean
    get() = sp.getBoolean(IS_LOGIN, false )
    private set(value) = sp.edit().putBoolean(IS_LOGIN, value).apply()

    suspend fun login(email: String, password: String): ActionState<AuthUser> {
        val user = authUser
        return if (user == null) {
            ActionState(message = "Anda Belum Terdaftar", isSucces = false)
        } else if (email.isBlank() || password.isBlank()) {
            ActionState(message = "Email dan Password tidak boleh kosong", isSucces = false)
        } else if (user.email == email && user.password == password) {
            isLogin = true
            ActionState(authUser, message = "Anda berhasil Login")
        } else {
            ActionState(message = "Email atau Password salah", isSucces = false)

        }
    }

    suspend fun register(user: AuthUser): ActionState<AuthUser> {
        return if (user.email.isBlank() || user.password.isBlank()) {
            ActionState(message = "Email dan Password tidak boleh kosong", isSucces = false)
        } else {
            authUser = user
            ActionState(user, message = "Anda Berhasil Daftar")
        }
    }
    suspend fun logout(): ActionState<Boolean> {
        isLogin = false
        return ActionState(true, message = "Anda berhasil Logout")
    }

}