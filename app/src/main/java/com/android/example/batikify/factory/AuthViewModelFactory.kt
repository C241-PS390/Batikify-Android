package com.android.example.batikify.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.batikify.data.injection.AuthInjection
import com.android.example.batikify.data.repository.AuthRepository
import com.android.example.batikify.screen.login.LoginViewModel
import com.android.example.batikify.screen.signup.SignUpViewModel

class AuthViewModelFactory(
    private val authRepository: AuthRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(authRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthViewModelFactory? = null
        @JvmStatic
        fun getAuthInstance(context: Context): AuthViewModelFactory {
            if (INSTANCE == null) {
                synchronized(AuthViewModelFactory::class.java) {
                    val authRepo = AuthInjection.provideAuthRepository(context)
                    INSTANCE = AuthViewModelFactory(authRepo)
                }
            }
            return INSTANCE as AuthViewModelFactory
        }
    }
}