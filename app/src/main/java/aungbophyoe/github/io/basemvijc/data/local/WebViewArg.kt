package aungbophyoe.github.io.basemvijc.data.local

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class WebViewArg(
    @SerializedName("url")
    val url : String
)
