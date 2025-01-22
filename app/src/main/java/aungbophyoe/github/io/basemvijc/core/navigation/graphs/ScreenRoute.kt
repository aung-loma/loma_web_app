package aungbophyoe.github.io.basemvijc.core.navigation.graphs

import androidx.annotation.Keep
import aungbophyoe.github.io.basemvijc.data.local.SampleData
import kotlinx.serialization.Serializable

/**
 * Created by aungb on 1/22/2025.
 */

@Keep
@Serializable
sealed class ScreenRoute {
    @Serializable
    data object GoBack : ScreenRoute()

    @Serializable
    data object HomeScreen : ScreenRoute()

    @Serializable
    data class DetailScreen(val data: SampleData) : ScreenRoute()
}