package aungbophyoe.github.io.basemvijc.core.uiComponents.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Created by aungb on 1/22/2025.
 */

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    lineHeight: TextUnit = TextUnit.Unspecified,
    color: Color = MaterialTheme.colorScheme.onSecondary,
    brush: Brush? = null,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLines : Int = Int.MAX_VALUE,
    minLines : Int = 1,
    overflow: TextOverflow = TextOverflow.Visible,
) {
    brush?.let {
        Text(
            text = text,
            style = style.copy(
                fontWeight = fontWeight,
                fontSize = fontSize,
                textAlign = textAlign,
                brush = brush,
                lineHeight = lineHeight,
            ),
            overflow = overflow,
            modifier = modifier,
            maxLines = maxLines,
            minLines = minLines
        )
    } ?: run {
        Text(
            text = text,
            style = style.copy(
                fontWeight = fontWeight,
                fontSize = fontSize,
                textAlign = textAlign,
                color = color,
                lineHeight = lineHeight,
            ),
            overflow = overflow,
            modifier = modifier,
            maxLines = maxLines,
            minLines = minLines
        )
    }
}