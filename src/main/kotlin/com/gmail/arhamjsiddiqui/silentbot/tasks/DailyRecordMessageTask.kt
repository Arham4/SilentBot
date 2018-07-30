package com.gmail.arhamjsiddiqui.silentbot.tasks

import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.MarkdownText.bold
import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.getDefaultTextChannel
import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.queueMessage
import com.gmail.arhamjsiddiqui.silentbot.SilentBot
import com.gmail.arhamjsiddiqui.silentbot.data.Guilds
import com.gmail.arhamjsiddiqui.silentbot.data.isBlacklistedServer
import com.gmail.arhamjsiddiqui.silentbot.toTimeString
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DailyRecordMessageTask : Runnable {
    override fun run() {
        SilentBot.BOT.guilds.forEach { guild ->
            if (!isBlacklistedServer(guild.id)) {
                Guilds.useGuild(guild.name, guild.idLong) { guild ->
                    if (guild.dailyRecord != 0.toLong()) {
                        SilentBot.BOT.getDefaultTextChannel(guild.guildId)?.queueMessage(
                                "The day's up! The record for today was only ${guild.record.toTimeString().bold()}!")
                    }
                }
            }
        }
        reschedule()
    }

    companion object {
        private val scheduler by lazy { Executors.newScheduledThreadPool(1) }

        fun schedule() {
            val midnight = LocalDateTime.now().until(LocalDate.now().plusDays(1).atStartOfDay(), ChronoUnit.MINUTES)
            scheduler.scheduleAtFixedRate(DailyRecordMessageTask(), midnight, TimeUnit.DAYS.toMinutes(1), TimeUnit.MINUTES)
        }

        fun reschedule() {
            schedule()
        }
    }
}