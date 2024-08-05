package com.kurban.app.repository.local.proto

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kurban.app.User
import com.kurban.app.copy
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ProtoUseCase @Inject constructor(context: Context) {

    private val protoSerializer = context.protoSerializer

    private val _protoData = MutableLiveData<User>()
    val protoData: LiveData<User> = _protoData

    suspend fun createUser(
        email: String,
        password: String,
        name: String,
        surname: String,
    ) {
        val user = User.newBuilder()
            .setEmail(email)
            .setPassword(password)
            .setName(name)
            .setSurname(surname)
            .build()
        updateUser(user)
    }

    suspend fun updateUser(user: User) {
        protoSerializer.data.collectLatest { params: User ->
            params.copy {
                user
            }
        }
    }

    suspend fun getUser() {
        protoSerializer.data.collectLatest { params: User ->
            _protoData.postValue(params)
        }
    }

    suspend fun resetUser() {
        protoSerializer.updateData {
            User.getDefaultInstance()
        }
    }
}