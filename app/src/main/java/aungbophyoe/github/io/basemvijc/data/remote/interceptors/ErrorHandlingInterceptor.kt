package aungbophyoe.github.io.basemvijc.data.remote.interceptors

import android.text.Html
import androidx.annotation.Keep
import aungbophyoe.github.io.basemvijc.core.resource.language.CommonStringRes
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * Created by aungb on 1/20/2025.
 */

class ErrorHandlingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        try {
            if (response.isSuccessful) return response
            val errorMessage = getMessageFromErrorResponse(response.body?.string())
            val apiErrorCode = when (response.code) {
                in 301..308 -> ApiExceptionConstants.SERVER_MOVED_ERROR
                400 -> ApiExceptionConstants.BAD_REQUEST_ERROR
                401 -> ApiExceptionConstants.UNAUTHORIZED_ERROR
                403 -> ApiExceptionConstants.REQUEST_FORBIDDEN_ERROR
                404 -> ApiExceptionConstants.WRONG_URL_ERROR
                408 -> ApiExceptionConstants.REQUEST_TIMEOUT_ERROR
                413 -> ApiExceptionConstants.LARGE_FILE_SIZE_ERROR
                422 -> ApiExceptionConstants.UNABLE_TO_RESPONSE_ERROR
                429 -> ApiExceptionConstants.TOO_MANY_REQUEST_ERROR
                500 -> ApiExceptionConstants.INTERNAL_SERVER_ERROR
                502 -> ApiExceptionConstants.BAD_GATEWAY_ERROR
                503 -> ApiExceptionConstants.SERVICE_UNAVAILABLE_ERROR
                504 -> ApiExceptionConstants.GATEWAY_TIMEOUT_ERROR
                else -> ApiExceptionConstants.UNKNOWN_ERROR
            }
            throw CustomErrorException(getErrorMessage(response.code, CommonStringRes.ERROR, errorMessage, apiErrorCode))
        } catch (e: Exception) {
            throw CustomErrorException(
                e.message ?: getErrorMessage(
                    response.code,
                    CommonStringRes.ERROR,
                    e.message,
                    ApiExceptionConstants.REQUEST_TIMEOUT_ERROR
                )
            )
        }
    }


    private fun getMessageFromErrorResponse(requestData: String?): String? {
        var message = requestData
        if (message?.startsWith("<") == true) {
            val regex = Regex("<h1>(.*?)</h1>", RegexOption.DOT_MATCHES_ALL)
            val matchResult = regex.find(requestData ?: "")
            val bodyContent = matchResult?.groupValues?.getOrNull(1)?.trim()
            message = Html.fromHtml(
                bodyContent ?: "Something Went Wrong", Html.FROM_HTML_MODE_COMPACT
            ).toString().trim()
        } else if (!requestData.isNullOrBlank() && requestData != "null") {
            val jsonObject = requestData?.let { JSONObject(it) }
            if (jsonObject?.has("message") == true) {
                message = jsonObject.getString("message")
            }
        }
        return message
    }
}

fun getErrorMessage(code: Int?, title: String?, message: String?, type: Int?): String {
    val errorMessage =
        "{\"code\":$code,\"title\":\"$title\",\"message\":\"${message?.replaceWrongSting()}\",\"type\":$type}"
    return errorMessage
}

private fun String.replaceWrongSting(): String {
    return this.replace("\"", "")
}

@Keep
class CustomErrorException(override val message: String = "Server Error") : IOException(message)

object ApiExceptionConstants {
    const val UNAUTHORIZED_ERROR = 1
    const val SERVER_MOVED_ERROR = 2
    const val BAD_REQUEST_ERROR = 3
    const val REQUEST_FORBIDDEN_ERROR = 4
    const val WRONG_URL_ERROR = 5
    const val REQUEST_TIMEOUT_ERROR = 6
    const val LARGE_FILE_SIZE_ERROR = 7
    const val UNABLE_TO_RESPONSE_ERROR = 8
    const val TOO_MANY_REQUEST_ERROR = 9
    const val INTERNAL_SERVER_ERROR = 10
    const val BAD_GATEWAY_ERROR = 11
    const val SERVICE_UNAVAILABLE_ERROR = 12
    const val GATEWAY_TIMEOUT_ERROR = 13
    const val UNKNOWN_ERROR = 14
    const val INTERNET_ERROR = 1001
    const val SUCCESS_ERROR = 16
}
