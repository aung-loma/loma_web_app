package aungbophyoe.github.io.basemvijc.domain.usecase

import aungbophyoe.github.io.basemvijc.core.base.BaseUseCase
import aungbophyoe.github.io.basemvijc.data.dto.SearchProductResponse
import aungbophyoe.github.io.basemvijc.data.local.SearchRequest
import aungbophyoe.github.io.basemvijc.data.remote.ApiResult
import aungbophyoe.github.io.basemvijc.data.remote.NetworkMonitor
import aungbophyoe.github.io.basemvijc.data.remote.interceptors.CustomErrorException
import aungbophyoe.github.io.basemvijc.data.remote.interceptors.getTimeOutError
import aungbophyoe.github.io.basemvijc.domain.repository.ConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by aungb on 5/10/2025.
 */

class SearchProductUseCase @Inject constructor(
    private val repository: ConfigRepository,
    private val networkMonitor: NetworkMonitor
) : BaseUseCase<SearchRequest,SearchProductResponse>(networkMonitor) {
    override fun execute(requestData: SearchRequest): Flow<ApiResult<SearchProductResponse>> = flow {
        try {
            emit(ApiResult.Loading())
            val response = repository.searchProduct(requestData)
            emit(
                if (response.status == true) ApiResult.Success(response) else ApiResult.Error(
                    error = null
                )
            )
        } catch (e: CustomErrorException) {
            emit(ApiResult.Error(error = e))
        } catch (ex: Exception) {
            emit(ApiResult.Error(error = getTimeOutError(message = ex.message)))
        }
    }
}