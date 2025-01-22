package aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.components

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider

/**
 * Created by aungb on 1/22/2025.
 */

@Composable
fun AppProgressDialog(
    showDialog: Boolean,
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            val curView = LocalView.current
            LaunchedEffect(curView) {
                tailrec fun Context.findWindow(): Window? = when (this) {
                    is Activity -> window
                    is ContextWrapper -> baseContext.findWindow()
                    else -> null
                }

                fun View.findWindow(): Window? =
                    (parent as? DialogWindowProvider)?.window ?: context.findWindow()

                try {
                    val window = curView.findWindow() ?: return@LaunchedEffect
                    val lp = window.attributes
                    lp.dimAmount = 0.3F
                    window.attributes = lp
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(25.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round,
                    trackColor = Color.Gray.copy(alpha = 0.3f),
                )
            }
        }
    }
}