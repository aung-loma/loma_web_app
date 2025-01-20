package aungbophyoe.github.io.basemvijc.core.events

/**
 * Created by aungb on 1/20/2025.
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> CollectEventsFromVM(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onCollect: suspend (T) -> Unit,
) = LaunchedEffect(flow, lifecycleOwner.lifecycle) {
    lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        withContext(Dispatchers.Main.immediate) {
            flow.collect(onCollect)
        }
    }
}