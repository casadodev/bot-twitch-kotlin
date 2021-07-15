package com.casadodev.domain.services

import com.casadodev.application.Bot
import com.casadodev.application.util.Scheduler
import com.github.philippheuer.events4j.core.EventManager
import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import org.apache.commons.lang.RandomStringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class SchedulerMessages(eventManager: EventManager) {
    private val logger: Logger = LoggerFactory.getLogger(SchedulerMessages::class.java)
    private var messageScheduler: Scheduler = Scheduler()

    private fun sendScheduledMessage(event: ChannelMessageEvent) {
        logger.info("#### start sendScheduledMessage ####")
        val numero = RandomStringUtils.randomNumeric(10)
        val randomMessageChat = "Testando mensagem em scheduler $numero"
        event.twitchChat.sendMessage("casadodev", randomMessageChat)
    }

    init {
        eventManager.getEventHandler(SimpleEventHandler::class.java).onEvent(
            ChannelMessageEvent::class.java
        ) {
            event: ChannelMessageEvent -> sendScheduledMessage(event)
            val sendScheduledMessage = Runnable { sendScheduledMessage(event) }
            messageScheduler.schedule(sendScheduledMessage, 0, 3, TimeUnit.SECONDS)
        }
    }
}