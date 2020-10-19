package com.shah.mvvmdemo.repository

import com.shah.mvvmdemo.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApoiCall(api: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(api.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> Resource.Failure(
                        false,
                        throwable.code(),
                        throwable.response()?.errorBody()
                    )
                    else -> Resource.Failure(true, null, null)
                }
            }
        }
    }
}