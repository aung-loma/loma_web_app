package aungbophyoe.github.io.basemvijc.domain.repository

import aungbophyoe.github.io.basemvijc.data.dto.SearchProductResponse
import aungbophyoe.github.io.basemvijc.data.local.SearchRequest

/**
 * Created by aungb on 5/10/2025.
 */

fun interface ConfigRepository {
    suspend fun searchProduct (request: SearchRequest) : SearchProductResponse
}