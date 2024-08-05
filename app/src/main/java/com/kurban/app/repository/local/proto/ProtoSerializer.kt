package com.kurban.app.repository.local.proto

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.kurban.app.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object ProtoSerializer : Serializer<User> {
    override val defaultValue: User
        get() = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User {
        return withContext(Dispatchers.IO) {
            try {
                User.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("CorruptionException", exception)
            }
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        return withContext(Dispatchers.IO) { t.writeTo(output) }
    }
}

val Context.protoSerializer: DataStore<User> by dataStore(
    "dataStore.db",
    ProtoSerializer
)