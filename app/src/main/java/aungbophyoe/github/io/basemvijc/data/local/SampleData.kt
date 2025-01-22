package aungbophyoe.github.io.basemvijc.data.local

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SampleData(
    val id: String? = null,
    val name: String? = null
)
