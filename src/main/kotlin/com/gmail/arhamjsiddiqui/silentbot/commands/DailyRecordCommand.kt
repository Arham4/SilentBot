package com.gmail.arhamjsiddiqui.silentbot.commands

import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.MarkdownText.bold
import com.gmail.arhamjsiddiqui.silentbot.data.Guilds
import com.gmail.arhamjsiddiqui.silentbot.toTimeString
import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.TextChannel

/**
 * !!!record command to get the current record for silence in the channel.
 *
 * @author Arham 4
 */
class DailyRecordCommand : CommandExecutor {

    @Command(aliases = ["!!!dailyrecord"], async = true, description = "Shows the current record for silence in the current Discord channel.")
    fun onRecordCommand(textChannel: TextChannel): String {
        Guilds.useGuild(textChannel.guild.name, textChannel.guild.idLong) { guild ->
            if (guild.dailyRecord > 0) {
                return "The current record for silence ${"today".bold()} is only ${guild.dailyRecord.toTimeString(true).bold()}."
            }
        }
        return "Unable to retrieve current silence record. If you believe this to be a problem, report the issue at: https://github.com/Arham4/SilentBot/issues/new"
    }
}