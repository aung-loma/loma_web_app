package aungbophyoe.github.io.basemvijc.data.remote

/**
 * Created by aungb on 5/10/2025.
 */
sealed class ApiResult<T>(
    val data: T? = null,
    val error: Throwable? = null,
) {
    class Loading<T>(
        data: T? = null,
    ) : ApiResult<T>(data)

    class Success<T>(
        data: T,
    ) : ApiResult<T>(data)

    class Error<T>(error: Throwable? = null) : ApiResult<T>(null, error)
}