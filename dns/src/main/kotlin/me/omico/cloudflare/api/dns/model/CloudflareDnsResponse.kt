package me.omico.cloudflare.api.dns.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CloudflareDnsResponse(
    @SerialName("AD") val ad: Boolean,
    @SerialName("Answer") val answer: List<Answer>,
    @SerialName("CD") val cd: Boolean,
    @SerialName("Question") val question: List<Question>,
    @SerialName("RA") val ra: Boolean,
    @SerialName("RD") val rd: Boolean,
    @SerialName("Status") val status: Int,
    @SerialName("TC") val tc: Boolean,
) {

    @Serializable
    data class Answer(
        @SerialName("data") val `data`: String,
        @SerialName("name") val name: String,
        @SerialName("TTL") val ttl: Int,
        @SerialName("type") val type: Int,
    )

    @Serializable
    data class Question(
        @SerialName("name") val name: String,
        @SerialName("type") val type: Int,
    )
}
