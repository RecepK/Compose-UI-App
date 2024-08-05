package com.kurban.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurban.app.repository.remote.api.Api
import com.kurban.app.repository.local.proto.ProtoUseCase
import com.kurban.app.util.DELAY_TIME
import com.kurban.app.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: Api,
    private val protoUseCase: ProtoUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    init {
        protoUseCase.protoData.observeForever { user: User ->
            if (user == null) {
                viewModelScope.launch {
                    _uiState.postValue(UiState.FAILURE)
                }
            } else {
                _uiState.postValue(UiState.SUCCESS)
            }
        }
    }

    fun fetchUser() {
        viewModelScope.launch {
            protoUseCase.getUser()
        }
    }


    private fun fetchState() {
        viewModelScope.launch {
            val response = api.isOpen()
            _uiState.postValue(UiState.LOAD)
            delay(DELAY_TIME)

            if (response.isSuccessful) {
                response.body()?.let {

                    //protoUseCase.createUser("asd@asd.com", "1234", "RK", "KK")

                    it.isOpen?.let { status ->
                        _uiState.postValue(if (status) UiState.SUCCESS else UiState.FAILURE)
                    } ?: run {
                        _uiState.postValue(UiState.FAILURE)
                    }
                } ?: run {
                    _uiState.postValue(UiState.FAILURE)
                }
            } else {
                _uiState.postValue(UiState.FAILURE)
            }
        }
    }
}