package com.leunesmedia.tappbe.data.repositories

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.leunesmedia.tappbe.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception

/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [RESULT] represents the type for database.
 * [REQUEST] represents the type for network.
 */
@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT, REQUEST> {
    fun asFlow() = flow<State<RESULT>> {
        //Emit Loading state
        emit(State.loading())

        try {
            //Emit Room content first
            emit(State.success(fetchFromLocal().first()))

            // Fetch latest from remote
            val response = fetchFromRemote()

            // Parse Body
            val responseContent = response.body()

            // Check for response validation
            if (response.isSuccessful && responseContent != null) {
                //save new content into room
                saveRemoteData(responseContent)
            } else {
                // Uh oh.
                emit(State.error(response.message()))
            }
        } catch (e: Exception) {
            // Emit Error
            emit(State.error("Network error! Can't get latest content"))
            e.printStackTrace()
        }

        //  Retrieve all content from ROOM and emit
        emitAll(fetchFromLocal().map {
            State.success<RESULT>(it)
        })
    }

    //Saves retrieved from remote into ROOM
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    //Retrieves all data from persistence storage
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    //Fetches Response from from the remote end point
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}