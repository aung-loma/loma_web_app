package aungbophyoe.github.io.basemvijc.core.uiComponents.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import aungbophyoe.github.io.basemvijc.core.extension.toCapital
import aungbophyoe.github.io.basemvijc.core.uiComponents.buttons.CustomButtonFilled
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.components.AppProgressDialog
import aungbophyoe.github.io.basemvijc.core.uiComponents.texts.CustomText

/**
 * Created by aungb on 1/22/2025.
 */

@Composable
fun DialogHost() {
    val dialogEvent by DialogManager.dialogEvents.collectAsState(initial = null)
    dialogEvent?.let { event ->
        when (event) {
            is DialogEvent.ToggleProgress -> event.canShowProgress?.let { AppProgressDialog(it) }

            is DialogEvent.SingleAction -> SingleActionDialog(
                dialogData = event.dialogData,
                onConfirm = {
                    event.onConfirm()
                    DialogManager.dismissDialog()
                }
            )

            is DialogEvent.MultiAction -> Unit

            is DialogEvent.Info -> Unit

            is DialogEvent.NoInternetDialog -> Unit

            is DialogEvent.Dismiss -> Unit
        }
    }
}

@Composable
fun SingleActionDialog(dialogData: DialogData, onConfirm: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onConfirm()
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxSize()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            CustomText(
                text = dialogData.title.toCapital(),
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleLarge
            )

            CustomText(
                modifier = Modifier.padding(top = 16.dp),
                text = dialogData.description ?: "",
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Spacer(Modifier.weight(1f))

                CustomButtonFilled(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .widthIn(min = 80.dp)
                        .height(42.dp),
                    radius = 8.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    onClick = {
                        onConfirm()
                    }) {
                    CustomText(
                        dialogData.positiveBtnText,
                        maxLines = 1,
                        color = Color.White,
                        fontWeight = FontWeight.W800,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}