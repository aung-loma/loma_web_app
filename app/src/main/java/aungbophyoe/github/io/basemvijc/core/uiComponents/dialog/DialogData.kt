package aungbophyoe.github.io.basemvijc.core.uiComponents.dialog

import androidx.annotation.Keep
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import aungbophyoe.github.io.basemvijc.core.resource.icons.AppIcons
import aungbophyoe.github.io.basemvijc.core.resource.language.CommonStringRes

@Keep
data class DialogData(
    val title: String,
    val description: String? = null,
    val positiveBtnText: String = CommonStringRes.OK,
    val negativeBtnText: String = CommonStringRes.CANCEL,
    val positiveBtnColor: Color = Color.Blue,
    val negativeBtnColor: Color = Color.Red,
    val autoDismissTime: Long = 2L,
    val dialogType: DialogType = DialogType.NONE,
    val infoIcon: Int? = AppIcons.icBrokenImage,
    val isLandscape : Boolean = false,
    val modifier: Modifier = Modifier,
)

enum class DialogType {
    SINGLE, MULTI, INFO, NONE
}
