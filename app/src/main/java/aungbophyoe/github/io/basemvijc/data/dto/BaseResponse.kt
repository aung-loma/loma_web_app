package aungbophyoe.github.io.basemvijc.data.dto

import androidx.annotation.Keep
import aungbophyoe.github.io.basemvijc.data.remote.interceptors.ApiExceptionConstants
import kotlinx.serialization.Serializable

/**
 * Created by aungb on 1/22/2025.
 */

@Serializable
open class BaseResponse(
    val code: Int? = null,
    val message: String? = null,
)


@Keep
data class ApiMessageData(
    val errorCode: String? = null,
    val title: String? = "",
    val description: String? = "",
)

@Keep
data class ErrorMessageData(
    val code: Int = ApiExceptionConstants.UNKNOWN_ERROR,
    val title: String? = null,
    val message: String? = null,
    val type: Int? = null
)
