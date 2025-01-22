package aungbophyoe.github.io.basemvijc.presentation.home

import aungbophyoe.github.io.basemvijc.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by aungb on 1/22/2025.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModel<HomeUiState, HomeAction>() {

    override fun setInitialState() = HomeUiState()

    override fun onAction(eventType: HomeAction) {
        when(eventType) {
            is HomeAction.OnRefresh -> Unit
        }
    }
}