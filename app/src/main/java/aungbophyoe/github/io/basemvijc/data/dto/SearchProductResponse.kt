package aungbophyoe.github.io.basemvijc.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchProductResponse(
    @SerializedName("data")
    val productData: SearchProductData?
) : BaseResponse()

@Keep
@Serializable
data class SearchProductData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("platform_name")
    val platformName: String?,
    @SerializedName("merchant_code")
    val productCode: String?,
    @SerializedName("alternative_code")
    val alternativeCode: Int?,
    @SerializedName("url_link")
    val urlLink: String?,
    @SerializedName("h5_url")
    val h5_url: String?,
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("created_by")
    val createdBy: Int?,
    @SerializedName("is_h5")
    val isH5: Boolean?,
)



