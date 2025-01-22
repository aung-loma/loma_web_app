package aungbophyoe.github.io.basemvijc.core.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast

/**
 * Created by aungb on 1/22/2025.
 */

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

fun Context.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, it, duration).show()
    }
}