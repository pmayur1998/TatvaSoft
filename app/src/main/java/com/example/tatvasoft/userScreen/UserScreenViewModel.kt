package com.example.tatvasoft.userScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tatvasoft.models.Result
import com.example.tatvasoft.utils.Response
import kotlinx.coroutines.launch

class UserScreenViewModel(
    private val repository: UserScreenRepository,
) : ViewModel()  {

    /**
     * usage call repo fun to get user data from API
     * @param
     * @return MutableLiveData<Response<Result?>>
     */
    fun getUsersFromAPI() : MutableLiveData<Response<Result?>> {

        val data = MutableLiveData<Response<Result?>>()

        viewModelScope.launch {
            repository.getUsers().let {
                data.value = it
            }
        }
        return data
    }
}