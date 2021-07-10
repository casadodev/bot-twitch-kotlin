package com.casadodev.application

import com.github.twitch4j.TwitchClientBuilder
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import javax.annotation.PostConstruct


@Context
class ChamaChat() {

    @Value("\${app.twitch.clientId}")
    lateinit var clientId: String

    @Value("\${app.twitch.secret}")
    lateinit var secret: String

    @PostConstruct
    fun chat() {
        val twitchClient = TwitchClientBuilder.builder()
            .withClientId(clientId)
            .withClientSecret(secret)
//            .withCredentialManager(credentialManager)
//            .withChatAccount(oAuth2CredentialHere)
            .withEnableChat(true)
            .withEnableHelix(true)
            .build()

        twitchClient.chat.joinChannel("casadodev")

        twitchClient.eventManager.onEvent(ChannelMessageEvent::class.java) { event ->
            println("[${event.channel.name}] ${event.user.name}: ${event.message}")
        }
        println("teste")

        println(clientId)
        twitchClient.chat.connect()

        println("teste")
    }
}
