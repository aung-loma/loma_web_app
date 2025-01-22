package aungbophyoe.github.io.basemvijc.core.navigation.navTypes

/**
 * Created by aungb on 1/22/2025.
 */

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

abstract class JsonNavType<T>(
    private val serializer: KSerializer<T>
) : NavType<T>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): T? {
        return Json.decodeFromString(serializer, bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, Uri.decode(value))
    }

    override fun serializeAsValue(value: T): String {
        return Uri.encode(Json.encodeToString(serializer, value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(serializer, value))
    }
}