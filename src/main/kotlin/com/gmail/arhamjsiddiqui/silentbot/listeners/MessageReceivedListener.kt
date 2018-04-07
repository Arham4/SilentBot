package com.gmail.arhamjsiddiqui.silentbot.listeners

import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.MarkdownText.bold
import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.defaultTextChannel
import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.queueMessage
import com.gmail.arhamjsiddiqui.silentbot.SilentBot
import com.gmail.arhamjsiddiqui.silentbot.data.Guild
import com.gmail.arhamjsiddiqui.silentbot.data.Guilds
import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.EventListener
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * A listener that listens for when a user has joined the Discord.
 *
 * @author Arham J. Siddiqui
 */
class MessageReceivedListener : EventListener {
    override fun onEvent(event: Event) {
        if (event is MessageReceivedEvent) {
            var guild = Guilds.retrieveGuild(event.guild.idLong)
            if (Guilds.retrieveGuild(event.guild.idLong) == null) {
                guild = Guilds.createGuild(event.guild.idLong)
            }
            guild?.let {
                if (guild.lastMessage.time beats guild.record) {
                    guild.record = System.currentTimeMillis() - guild.lastMessage.time
                    sendNewRecordMessage(guild)
                }

                guild.lastMessage = Date(System.currentTimeMillis())
                Guilds.updateGuild(event.guild.idLong, guild)
            }
        }
    }

    private fun sendNewRecordMessage(guild: Guild) {
        // make it say minutes, hours, days, etc.
        SilentBot.BOT.defaultTextChannel?.queueMessage("A new server-wide record for silence has been made! The new record is now ${"${guild.record.toMinutes()} minutes".bold()}!")
    }

    private infix fun Long.beats(other: Long): Boolean {
        println("${System.currentTimeMillis() - this} > $other")
        return System.currentTimeMillis() - this > other
    }

    private fun Long.toMinutes(): Long {
        return TimeUnit.MILLISECONDS.toMinutes(this)
    }
}