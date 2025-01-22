package aungbophyoe.github.io.basemvijc.core.navigation.navTypes

import aungbophyoe.github.io.basemvijc.data.local.SampleData

object SampleDataNavArg {
    val type = object : JsonNavType<SampleData>(SampleData.serializer()) {}
}