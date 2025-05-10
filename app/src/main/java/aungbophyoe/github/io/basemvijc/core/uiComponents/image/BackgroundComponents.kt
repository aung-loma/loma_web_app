package aungbophyoe.github.io.basemvijc.core.uiComponents.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by aungb on 5/10/2025.
 */

@Composable
fun BackgroundImageComponent(
    modifier: Modifier = Modifier,
    //@DrawableRes backgroundImg: Int = R.drawable.main_background,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {
        /*Image(
            painterResource(backgroundImg),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = modifier.fillMaxSize(),
        )*/
        content()
    }
}

@Composable
fun BackgroundComponent(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.onBackground).fillMaxSize()) {
        content()
    }
}