package aungbophyoe.github.io.basemvijc.data.remote

import aungbophyoe.github.io.basemvijc.data.dto.SearchProductResponse
import aungbophyoe.github.io.basemvijc.data.remote.ApiConstants.SEARCH_PRODUCT
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aungb on 1/20/2025.
 */

fun interface ApiService {
    @GET(SEARCH_PRODUCT)
    suspend fun searchProduct(
        @Query("input_key") inputKey: String,
        @Query("search_status") searchStatus: Boolean,
    ): SearchProductResponse
}