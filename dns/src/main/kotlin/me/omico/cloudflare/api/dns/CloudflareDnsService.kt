package me.omico.cloudflare.api.dns

import me.omico.cloudflare.api.dns.model.CloudflareDnsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CloudflareDnsService {

    @Headers("accept: application/dns-json")
    @GET("/dns-query")
    suspend fun dnsQuery(
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("do") `do`: Boolean,
        @Query("cd") cd: Boolean,
    ): CloudflareDnsResponse
}
