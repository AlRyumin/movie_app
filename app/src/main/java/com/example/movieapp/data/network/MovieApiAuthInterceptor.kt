package com.example.movieapp.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MovieApiAuthInterceptor @Inject constructor(
    private val movieTokenProvider: MovieTokenProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = movieTokenProvider.getToken()

        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }

}