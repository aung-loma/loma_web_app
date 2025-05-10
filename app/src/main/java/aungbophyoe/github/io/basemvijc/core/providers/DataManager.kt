package aungbophyoe.github.io.basemvijc.core.providers

import aungbophyoe.github.io.basemvijc.data.dto.SearchProductData

object DataManager {
    private var searchProductData : SearchProductData? = null

    fun setSearchProductData(data: SearchProductData) {
        searchProductData = data
    }

    fun getSearchProductData(): SearchProductData? {
        return searchProductData
    }
}