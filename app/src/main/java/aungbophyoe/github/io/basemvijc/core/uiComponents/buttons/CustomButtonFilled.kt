package aungbophyoe.github.io.basemvijc.core.uiComponents.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by aungb on 1/22/2025.
 */

const val debounceTime = 800L
@Composable
fun CustomButtonFilled(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.secondary,
    radius: Dp = 10.dp,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    var isClickable by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    Button(
        shape = RoundedCornerShape(radius),
        enabled = isEnabled,
        modifier = modifier.height(40.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = {
            if (isClickable) {
                onClick()
                isClickable = false

                coroutineScope.launch {
                    delay(debounceTime)
                    isClickable = true
                }
            }
        },
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}