package aungbophyoe.github.io.basemvijc.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Created by aungb on 1/22/2025.
 */
@Keep
@Serializable
open class BaseResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("message")
    val message: ResponseMessage? = null,
    @SerializedName("status")
    val status: Boolean? = null
)

@Keep
@Serializable
data class ResponseMessage(
    @SerializedName("title")
    val title : String? = null,
    @SerializedName("description")
    val description : String? = null,
)