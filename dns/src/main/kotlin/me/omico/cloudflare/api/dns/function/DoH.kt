@file:Suppress("unused")

package me.omico.cloudflare.api.dns.function

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.omico.cloudflare.api.dns.CloudflareDnsClient
import me.omico.cloudflare.api.dns.internal.dnsService
import me.omico.cloudflare.api.dns.model.CloudflareDnsResponse

suspend fun CloudflareDnsClient.dnsQuery(
    name: String,
    type: String,
    `do`: Boolean = true,
    cd: Boolean = false,
): CloudflareDnsResponse = withContext(Dispatchers.IO) {
    dnsService.dnsQuery(name, type, `do`, cd)
}
