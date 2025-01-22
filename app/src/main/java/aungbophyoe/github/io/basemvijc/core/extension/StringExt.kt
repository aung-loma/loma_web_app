package aungbophyoe.github.io.basemvijc.core.extension

import java.util.Locale

/**
 * Created by aungb on 1/22/2025.
 */

fun isValidString(value: String?): Boolean {
    return !value.isNullOrBlank() && value != "null"
}

fun String.toCapital() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }