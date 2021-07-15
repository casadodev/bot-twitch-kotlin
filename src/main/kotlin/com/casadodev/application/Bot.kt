//package com.casadodev.application
//
//import com.github.philippheuer.credentialmanager.domain.Credential
//import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
//import com.github.philippheuer.events4j.core.EventManager
//import com.github.philippheuer.events4j.simple.SimpleEventHandler
//import com.github.twitch4j.TwitchClient
//import com.github.twitch4j.TwitchClientBuilder
//import java.util.*
//import kotlin.collections.ArrayList
//
//
class Bot {
//    fun loadConfiguration() {
//        // Events
//        eventManager.getEventHandler(SimpleEventHandler::class.java).registerListener(DefaultModCommandEvent())
//        eventManager.getEventHandler(SimpleEventHandler::class.java).registerListener(DefaultUserCommandEvent())
//        eventManager.getEventHandler(SimpleEventHandler::class.java).registerListener(CustomCommandEvent())
//        eventManager.getEventHandler(SimpleEventHandler::class.java).registerListener(ModerationEvent())
//        val command: org.h2.command.Command = org.h2.command.Command()
//        command.getCustomCommands().forEach { customCommand -> customCommandsList.add(customCommand) }
//    }
//
//    fun start() {
//        loadConfiguration()
//
//        // Start the timer for command cooldowns
//        CommandTimer.startCooldown()
//        val channels = Channel()
//        val joinedChannels: Map<String, Int> = channels.getAddedChannels()
//
//        // Loop through all registered channels and join
//        val addChannel = AddChannel()
//        joinedChannels.forEach { (channel: String?, newStatus: Int?) ->
//            addChannel.joinChannel(
//                channel
//            )
//        }
//
//        // Make sure we keep updating the channel OAuth tokens
//        val timer = Timer()
//        // Timer thread for each hour
//        timer.scheduleAtFixedRate(Hour(), 60 * 60 * 1000, 60 * 60 * 1000)
//        // Timer thread for each minute
//        timer.scheduleAtFixedRate(Minute(), 60 * 1000, 60 * 1000)
//
//        // Update the local commands list
//        timer.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                val command: org.h2.command.Command = org.h2.command.Command()
//                customCommandsList = command.getCustomCommands()
//            }
//        }, 15 * 1000, 15 * 1000)
//    }
//
//    companion object {
//        private val eventManager = EventManager()
//        lateinit var twitchClient: TwitchClient
//        var chatOauth = OAuth2Credential("twitch", Credential.BOT_OAUTH.getValue())
//        var customCommandsList: MutableList<CustomCommand> = ArrayList<CustomCommand>()
//        var watchTimeList: List<WatchTime> = ArrayList<WatchTime>()
//    }
//
//    /*
//	 * Constructor
//	 */
//    init {
//        eventManager.registerEventHandler(SimpleEventHandler())
//        twitchClient = TwitchClientBuilder.builder()
//            .withEventManager(eventManager)
//            .withEnableHelix(true)
//            .withEnableKraken(true)
//            .withEnableTMI(true)
//            .withEnableChat(true)
//            .withChatAccount(chatOauth)
//            .withEnablePubSub(true)
//            .build()
//    }
}