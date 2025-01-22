package aungbophyoe.github.io.basemvijc.core.uiComponents.dialog

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object DialogManager {
    private val _dialogEvents = MutableSharedFlow<DialogEvent>(extraBufferCapacity = 10)
    val dialogEvents: SharedFlow<DialogEvent> = _dialogEvents.asSharedFlow()

    fun showSingleActionDialog(dialogData: DialogData, onConfirm: () -> Unit) {
        _dialogEvents.tryEmit(DialogEvent.SingleAction(dialogData, onConfirm))
    }

    fun toggleProgress(canShowLoading: Boolean) {
        _dialogEvents.tryEmit(DialogEvent.ToggleProgress(canShowLoading))
    }

    fun showMultiActionDialog(dialogData: DialogData, onConfirm: () -> Unit, onDismiss: () -> Unit) {
        _dialogEvents.tryEmit(DialogEvent.MultiAction(dialogData, onConfirm, onDismiss))
    }

    fun showInfoDialog(dialogData: DialogData) {
        _dialogEvents.tryEmit(DialogEvent.Info(dialogData))
    }

    fun showNoInternet(title: String, message: String, onRetry: () -> Unit) {
        _dialogEvents.tryEmit(
            DialogEvent.NoInternetDialog(
                title = title,
                message = message,
                canShowProgress = true,
                onRetry = onRetry
            )
        )
    }

    fun dismissDialog() {
        _dialogEvents.tryEmit(DialogEvent.Dismiss)
    }
}