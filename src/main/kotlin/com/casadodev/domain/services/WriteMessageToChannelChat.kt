package com.casadodev.domain.services

import com.github.philippheuer.events4j.core.EventManager
import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.chat.events.CommandEvent
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.concurrent.timer


class WriteMessageToChannelChat(eventManager: EventManager) {
    private val logger: Logger = LoggerFactory.getLogger(WriteMessageToChannelChat::class.java)
    /**
     * Subscribe to send Message to the chat
     */
    private fun inChannelMessage(event:ChannelMessageEvent) {
        logger.info("#### start inChannelMessage ####")
        if(event.message.contains("boa noite")) {
            event.twitchChat.sendMessage(event.channel.name, "Boa! Como você está?")
        }

        if(event.message.contains("salve")) {
            event.twitchChat.sendMessage(event.channel.name, "Ta salvado! Agora com o bot feito em Kotlin.")
        }
    }

//    fun onCommandEvent(event: CommandEvent?) {
//        event?.twitchChat?.sendMessage("casadodev", "testando comando")
//    }
    //   JChatPane status;
    /**
     * Register events of this class with the EventManager
     *
     * @param eventManager EventManager
     */

    init {
        eventManager.getEventHandler(SimpleEventHandler::class.java).onEvent(
            ChannelMessageEvent::class.java
        ) { event: ChannelMessageEvent -> inChannelMessage(event) }
    }
}