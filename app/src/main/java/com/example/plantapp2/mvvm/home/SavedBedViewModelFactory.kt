package com.example.plantapp2.mvvm.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantapp2.ui.home.SavedBedsViewModel

class SavedBedsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedBedsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedBedsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
