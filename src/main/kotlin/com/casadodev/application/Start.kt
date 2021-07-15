package com.casadodev.application

import com.casadodev.domain.services.SchedulerMessages
import com.casadodev.domain.services.WriteChannelChatToConsole
import com.casadodev.domain.services.WriteMessageToChannelChat
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct

@Context
class Bot() {

    private val logger: Logger = LoggerFactory.getLogger(Bot::class.java)

    @Value("\${app.twitch.secret}")
    lateinit var secret: String

    @Value("\${app.twitch.channel}")
    lateinit var canal: String

    /*
     * Constructor
     */
    fun configuration(): TwitchClient {
        logger.info("#### configuration ####")
        val credential = OAuth2Credential("twitch", secret)

        return TwitchClientBuilder.builder()
            .withDefaultAuthToken(credential)
            .withChatAccount(credential)
            .withEnableChat(true)
            .withEnableHelix(true)
            .withEnableKraken(true)
            .withEnableTMI(true)
            .withEnablePubSub(false)
            .withCommandTrigger("!")
            .build()
    }

    /*
     * Loader
     */
    @PostConstruct
    fun start() {
        logger.info("#### start ####")

        val twitchClient = configuration()
        twitchClient.chat.joinChannel(canal)

        WriteChannelChatToConsole(twitchClient.eventManager)
        WriteMessageToChannelChat(twitchClient.eventManager)
        SchedulerMessages(twitchClient.eventManager)

        twitchClient.chat.sendMessage(canal, "Hey! salve! Estou online e sou feito em Kotlin!")

        twitchClient.clientHelper.enableStreamEventListener(canal)
        twitchClient.clientHelper.enableFollowEventListener(canal)
    }

}
