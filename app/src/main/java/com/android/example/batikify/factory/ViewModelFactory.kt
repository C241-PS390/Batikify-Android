package com.android.example.batikify.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.batikify.data.injection.BatikInjection
import com.android.example.batikify.data.repository.BatikRepository
import com.android.example.batikify.screen.artikel.ArtikelViewModel
import com.android.example.batikify.screen.classification.ClassificationViewModel
import com.android.example.batikify.screen.detail.DetailViewModel
import com.android.example.batikify.screen.ensiklopedia.EnsiklopediaViewModel
import com.android.example.batikify.screen.history.HistoryViewModel
import com.android.example.batikify.screen.home.HomeViewModel
import com.android.example.batikify.screen.profile.ProfileViewModel

class ViewModelFactory(
    private val batikRepository: BatikRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(batikRepository) as T
            }
            modelClass.isAssignableFrom(EnsiklopediaViewModel::class.java) -> {
                EnsiklopediaViewModel(batikRepository) as T
            }
            modelClass.isAssignableFrom(ArtikelViewModel::class.java) -> {
                ArtikelViewModel(batikRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(batikRepository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(batikRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(batikRepository) as T
            }
            modelClass.isAssignableFrom(ClassificationViewModel::class.java) -> {
                ClassificationViewModel(batikRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        fun getInstance(context: Context) = ViewModelFactory(BatikInjection.provideStoryRepository(context))
    }
}