package aungbophyoe.github.io.basemvijc.data.local

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchRequest(
    @SerializedName("input_key")
    val inputKey: String,
    @SerializedName("search_status")
    val searchStatus: Boolean,
)
