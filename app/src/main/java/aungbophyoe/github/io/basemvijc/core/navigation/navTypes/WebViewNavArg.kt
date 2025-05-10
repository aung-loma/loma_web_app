package aungbophyoe.github.io.basemvijc.core.navigation.navTypes

import aungbophyoe.github.io.basemvijc.data.local.WebViewArg

object WebViewNavArg {
    val type = object  : JsonNavType<WebViewArg>(WebViewArg.serializer()) {}
}