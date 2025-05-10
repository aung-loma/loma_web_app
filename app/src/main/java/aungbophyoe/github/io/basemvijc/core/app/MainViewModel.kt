package aungbophyoe.github.io.basemvijc.core.app

import androidx.lifecycle.viewModelScope
import aungbophyoe.github.io.basemvijc.core.base.BaseViewModel
import aungbophyoe.github.io.basemvijc.core.providers.DataManager
import aungbophyoe.github.io.basemvijc.data.local.SearchRequest
import aungbophyoe.github.io.basemvijc.data.remote.ApiResult
import aungbophyoe.github.io.basemvijc.domain.usecase.SearchProductUseCase
import com.lomawebview.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by aungb on 5/10/2025.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase
) : BaseViewModel<MainUiState,MainAction>() {
    private val _keepOnSplashScreen = MutableStateFlow(true)
    val keepOnSplashScreen = _keepOnSplashScreen.asStateFlow()

    override fun setInitialState() = MainUiState()

    override fun onAction(eventType: MainAction) {
        when(eventType) {
            is MainAction.FetchSearchAPI -> fetchSearchAPI()
        }
    }

    private fun fetchSearchAPI() {
        searchProductUseCase.invoke(
            SearchRequest(
                inputKey = BuildConfig.MERCHANT_CODE,
                searchStatus = true
            )
        ).flowOn(Dispatchers.IO).onEach { response ->
            when (response) {
                is ApiResult.Error -> {
                    _keepOnSplashScreen.value = false
                }

                is ApiResult.Loading -> Unit

                is ApiResult.Success -> {
                    response.data?.productData?.let {
                        DataManager.setSearchProductData(data = response.data.productData)
                    }
                    _keepOnSplashScreen.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}