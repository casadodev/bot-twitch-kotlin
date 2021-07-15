package com.casadodev.domain.services

import com.casadodev.application.Bot
import com.github.philippheuer.events4j.core.EventManager
import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.chat.events.CommandEvent
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class WriteChannelChatToConsole(eventManager: EventManager) {
    private val logger: Logger = LoggerFactory.getLogger(WriteChannelChatToConsole::class.java)
    /**
     * Subscribe to the ChannelMessage Event and write the output to the console
     */
    private fun onChannelMessage(event: ChannelMessageEvent) {
        logger.info("#### start onChannelMessage ####")
        println("[${event.channel.name}] ${event.permissions} ${event.user.name}: ${event.message}")
    }

//    fun onCommandEvent(event: CommandEvent?) {}
    //   JChatPane status;
    /**
     * Register events of this class with the EventManager
     *
     * @param eventManager EventManager
     */
    init {
        eventManager.getEventHandler(SimpleEventHandler::class.java).onEvent(
            ChannelMessageEvent::class.java
        ) { event: ChannelMessageEvent -> onChannelMessage(event) }
    }
}