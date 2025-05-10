package aungbophyoe.github.io.basemvijc.core.base

import aungbophyoe.github.io.basemvijc.data.remote.ApiResult
import aungbophyoe.github.io.basemvijc.data.remote.NetworkMonitor
import aungbophyoe.github.io.basemvijc.data.remote.interceptors.getInternetError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by aungb on 5/10/2025.
 */

abstract class BaseUseCase<in Params, T>(
    private val networkMonitor: NetworkMonitor
) {
    operator fun invoke(params: Params): Flow<ApiResult<T>> {
        return if (networkMonitor.isInternetAvailable()) {
            execute(params)
        } else {
            flow {
                emit(
                    ApiResult.Error(
                        error = getInternetError()
                    )
                )
            }
        }
    }

    protected abstract fun execute(requestData: Params): Flow<ApiResult<T>>
}

abstract class SimpleUseCase<T>(private val networkMonitor: NetworkMonitor) {
    operator fun invoke(): Flow<ApiResult<T>> {
        return if (networkMonitor.isInternetAvailable()) {
            execute()
        } else {
            flow {
                emit(
                    ApiResult.Error(
                        error = getInternetError()
                    )
                )
            }
        }
    }


    protected abstract fun execute(): Flow<ApiResult<T>>
}
