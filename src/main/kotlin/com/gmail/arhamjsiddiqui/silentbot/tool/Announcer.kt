package main.kotlin.com.gmail.arhamjsiddiqui.silentbot.tool

import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.getDefaultTextChannel
import com.gmail.arhamjsiddiqui.silentbot.DiscordFunctions.queueMessage
import com.gmail.arhamjsiddiqui.silentbot.SilentBot
import java.util.*

fun main(args: Array<String>) {
    SilentBot
    val scanner = Scanner(System.`in`)
    println("What's the message you want to send?")
    println()
    val message = scanner.nextLine()
    SilentBot.BOT.guilds.forEach { guild ->
        SilentBot.BOT.getDefaultTextChannel(guild.idLong)?.queueMessage(message)
    }
}