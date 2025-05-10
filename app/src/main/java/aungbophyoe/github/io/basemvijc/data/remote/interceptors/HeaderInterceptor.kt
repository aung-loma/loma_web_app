package aungbophyoe.github.io.basemvijc.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by aungb on 1/20/2025.
 */

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder().apply {
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
        }
        return chain.proceed(newRequest.build())
    }
}