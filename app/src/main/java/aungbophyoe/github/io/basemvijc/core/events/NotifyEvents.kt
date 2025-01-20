package aungbophyoe.github.io.basemvijc.core.events

/**
 * Created by aungb on 1/20/2025.
 */

interface NotifyEvents {
    data object PopBackRoute : NotifyEvents

    /*data class Navigate(val route: ScreenRoute, val currentRoute: ScreenRoute? = null) :
        NotifyEvents*/

    data class ToggleLoading(val isLoading: Boolean) : NotifyEvents

    data object LoginEvent : NotifyEvents


    /*data class ShowDialog(
        val dialogData: DialogData,
    ) : NotifyEvents*/

    data object ShowSuccessDialog : NotifyEvents

    data class NoInternet(
        val title: String? = null,
        val message: String? = null,
        val needToShowPopUp: Boolean = true
    ) : NotifyEvents

    data class UnAuthorized(val msg: String, val fromHome: Boolean = false) : NotifyEvents

    data class ShowToastMessage(val msg: String) : NotifyEvents

   /* data class ShowError(
        val dialogData: DialogData,
        val errorCode: Int? = null,
        val errorStr: String? = null,
        val needToShowPopUp: Boolean = true
    ) : NotifyEvents*/
}