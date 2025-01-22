package aungbophyoe.github.io.basemvijc.core.uiComponents.image

/**
 * Created by aungb on 1/22/2025.
 */

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import aungbophyoe.github.io.basemvijc.core.resource.icons.AppIcons
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers

fun loadImage(
    context: Context,
    imageUrl: String,
    placeHolderAndErrorImage: Int = AppIcons.icBrokenImage,
    height: Int = 500,
    width: Int = 500
): ImageRequest {
    return ImageRequest.Builder(context = context)
        .data(imageUrl)
        .crossfade(false)
        .decoderFactory(
            if (SDK_INT >= 28) {
                ImageDecoderDecoder.Factory()
            } else {
                GifDecoder.Factory()
            }
        )
        .size(height = height, width = width)
        .dispatcher(Dispatchers.IO)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .error(placeHolderAndErrorImage)
        .build()
}
