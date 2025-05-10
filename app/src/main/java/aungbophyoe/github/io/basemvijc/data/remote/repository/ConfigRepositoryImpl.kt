package aungbophyoe.github.io.basemvijc.data.remote.repository

import aungbophyoe.github.io.basemvijc.data.dto.SearchProductResponse
import aungbophyoe.github.io.basemvijc.data.local.SearchRequest
import aungbophyoe.github.io.basemvijc.data.remote.ApiService
import aungbophyoe.github.io.basemvijc.domain.repository.ConfigRepository
import javax.inject.Inject

/**
 * Created by aungb on 5/10/2025.
 */

class ConfigRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ConfigRepository {
    override suspend fun searchProduct(request: SearchRequest): SearchProductResponse {
       return apiService.searchProduct(
            inputKey = request.inputKey,
            searchStatus = request.searchStatus,
        )
    }
}