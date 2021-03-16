package com.example.tatvasoft

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tatvasoft.userScreen.UserScreenRepository
import com.example.tatvasoft.userScreen.UserScreenViewModel

/**
 * used this because in future if we want to pass multiple args in viewmodel
 */
class ViewModelFactory:
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(UserScreenViewModel::class.java) ->
                    UserScreenViewModel(UserScreenRepository())
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}