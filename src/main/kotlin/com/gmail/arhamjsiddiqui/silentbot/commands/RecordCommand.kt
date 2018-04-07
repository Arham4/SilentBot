package com.gmail.arhamjsiddiqui.silentbot.commands

import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.MarkdownText.bold
import com.gmail.arhamjsiddiqui.silentbot.data.Guilds
import com.gmail.arhamjsiddiqui.silentbot.toMinutes
import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.TextChannel

/**
 * !!!record command to get the current record for silence in the channel.
 *
 * @author Arham 4
 */
class RecordCommand : CommandExecutor {

    @Command(aliases = ["!!!record"], async = true, description = "Shows the current record for silence in the current Discord channel.")
    fun onRecordCommand(textChannel: TextChannel): String {
        Guilds.useGuild(textChannel.guild.idLong) { guild ->
            if (guild.record > 60000) { // so it doesn't print out the default record cap. Just for professionals sake tbh.
                return "The current record for silence is ${"${guild.record.toMinutes()} minutes".bold()}."
                // TODO make it say minutes, hours, days, etc.
            }
        }
        return "Unable to retrieve current silence record."
    }
}