package com.gmail.arhamjsiddiqui.silentbot.data

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

/**
 * Makes files for servers logging their records.
 *
 * @author Arham 4
 */
object Guilds {

    fun createGuild(guildName: String, guildId: Long): Guild {
        val guild = Guild(guildName, guildId, 600000, Date(System.currentTimeMillis()))
        // 600000 to make the record a default of 10 minutes. Otherwise, it will be annoying going on and on at the beginning.
        YAMLWrite.writeDto("data/$guildId.yaml", guild)
        return guild
    }

    fun updateGuild(guildId: Long, guild: Guild) {
        YAMLWrite.writeDto("data/$guildId.yaml", guild)
    }

    fun retrieveGuild(guildId: Long): Guild? {
        return YAMLParse.parseDto("data/$guildId.yaml", Guild::class)
    }

    inline fun useGuild(guildName: String, guildId: Long, action: (guild: Guild) -> Unit) {
        var guild = Guilds.retrieveGuild(guildId)
        if (Guilds.retrieveGuild(guildId) == null) {
            guild = Guilds.createGuild(guildName, guildId)
        }
        guild?.let {
            action(guild)
        }
    }
}

data class Guild internal constructor(internal var guildName: String, // legit out of curiosity, never used.
                                      val guildId: Long,
                                      var record: Long, // the biggest difference in the lastMessage and latest message in milliseconds
                                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a z") var lastMessage: Date)