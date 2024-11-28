package com.example.plantapp2.mvvm.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantapp2.ui.home.SavedBedsViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SavedBedsViewModelFactory(private val context: Context, private var bedId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedBedsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedBedsViewModel(context, bedId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
