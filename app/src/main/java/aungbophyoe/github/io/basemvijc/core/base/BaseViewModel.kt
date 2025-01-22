package aungbophyoe.github.io.basemvijc.core.base

/**
 * Created by aungb on 1/22/2025.
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aungbophyoe.github.io.basemvijc.core.events.NotifyEvents
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState, UiAction>(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    abstract fun setInitialState(): UiState

    abstract fun onAction(eventType: UiAction)


    private val initialState: UiState by lazy { setInitialState() }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(initialState)

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val notifyEvents = Channel<NotifyEvents>(capacity = 10)

    val notifyEventsChannel = notifyEvents.receiveAsFlow()

    protected fun setState(reducer: UiState.() -> UiState) {
        viewModelScope.launch(mainDispatcher) {
            val newState = uiState.value.reducer()
            _uiState.value = newState
        }
    }

    fun sendEvent(event: NotifyEvents) {
        viewModelScope.launch(mainDispatcher) {
            notifyEvents.trySend(event)
        }
    }

    fun toggleLoading(canShowLoading: Boolean) {
        viewModelScope.launch {
            notifyEvents.trySend(NotifyEvents.ToggleLoading(canShowLoading))
        }
    }


    fun handleApiError(
        error: Throwable?,
    ) {
        sendEvent(NotifyEvents.ToggleLoading(false))
        /*if (isValidString(error?.message)) {
            val messageData = getResponsiveMessage(Gson().fromJson(error?.message, ErrorMessageData::class.java))
            sendEvent(
                NotifyEvents.ShowError(
                    dialogData = DialogData(
                        title = "${messageData.title}",
                        description = messageData.message
                    ),
                    errorCode = messageData.code,
                    errorStr = messageData.message,
                    needToShowPopUp = needToShowPopUp
                )
            )
        }*/
    }
}

