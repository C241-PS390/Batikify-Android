package com.android.example.batikify.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.batikify.data.injection.AuthInjection
import com.android.example.batikify.data.repository.AuthRepository
import com.android.example.batikify.screen.main.MainViewModel
import com.android.example.batikify.screen.main.SignUpViewModel
import com.android.example.batikify.screen.splash.SplashViewModel

class AuthViewModelFactory(
    private val authRepository: AuthRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(authRepository) as T
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