package me.omico.cloudflare.api.dns.internal

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import me.omico.cloudflare.api.dns.CloudflareDnsService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

internal const val baseUrl: String = "https://cloudflare-dns.com"

private val client: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .followRedirects(true)
        .retryOnConnectionFailure(true)
        .build()
}

@OptIn(ExperimentalSerializationApi::class)
private val jsonConverterFactory by lazy { Json.asConverterFactory("application/json".toMediaType()) }

internal val dnsService: CloudflareDnsService by lazy {
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(jsonConverterFactory)
        .build()
        .create()
}
