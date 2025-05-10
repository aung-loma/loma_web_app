package aungbophyoe.github.io.basemvijc.presentation.webView

import aungbophyoe.github.io.basemvijc.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by aungb on 5/10/2025.
 */

@HiltViewModel
class WebViewViewModel @Inject constructor(
) : BaseViewModel<WebViewState, WebViewAction>() {
    override fun setInitialState() = WebViewState()

    override fun onAction(eventType: WebViewAction) {
    }
}