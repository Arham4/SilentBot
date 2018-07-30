package com.gmail.arhamjsiddiqui.silentbot.listeners

import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.MarkdownText.bold
import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.getDefaultTextChannel
import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.queueMessage
import com.gmail.arhamjsiddiqui.silentbot.SilentBot
import com.gmail.arhamjsiddiqui.silentbot.data.Guild
import com.gmail.arhamjsiddiqui.silentbot.data.Guilds
import com.gmail.arhamjsiddiqui.silentbot.data.isBlacklistedServer
import com.gmail.arhamjsiddiqui.silentbot.toTimeString
import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.EventListener
import java.util.*

/**
 * A listener that listens for when a user has joined the Discord.
 *
 * @author Arham J. Siddiqui
 */
class MessageReceivedListener : EventListener {
    override fun onEvent(event: Event) {
        if (event is MessageReceivedEvent && !isBlacklistedServer(event.guild.id)) {
            Guilds.useGuild(event.guild.name, event.guild.idLong) { guild ->
                if (guild.lastMessage.time beats guild.dailyRecord) {
                    guild.dailyRecord = System.currentTimeMillis() - guild.lastMessage.time
                }
                if (guild.lastMessage.time beats guild.record) {
                    guild.record = System.currentTimeMillis() - guild.lastMessage.time
                    sendNewRecordMessage(guild)
                }

                guild.guildName = event.guild.name
                guild.lastMessage = Date(System.currentTimeMillis())
                Guilds.updateGuild(event.guild.idLong, guild)
            }
        }
    }

    private fun sendNewRecordMessage(guild: Guild) {
        SilentBot.BOT.getDefaultTextChannel(guild.guildId)?.queueMessage("A new server-wide record for silence has been made! The new record is now ${guild.record.toTimeString().bold()}!")
    }

    private infix fun Long.beats(other: Long): Boolean {
        return System.currentTimeMillis() - this > other
    }
}