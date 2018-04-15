package com.gmail.arhamjsiddiqui.silentbot

import com.gmail.arhamjsiddiqui.silentbot.commands.HelpCommand
import com.gmail.arhamjsiddiqui.silentbot.commands.RecordCommand
import com.gmail.arhamjsiddiqui.silentbot.data.CONFIG
import com.gmail.arhamjsiddiqui.silentbot.listeners.MessageReceivedListener
import de.btobastian.sdcf4j.handler.JDA3Handler
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.Game

/**
 * @author Arham 4
 */
object SilentBot {

    @JvmStatic
    fun main(args: Array<String>) {

    }

    val BOT: JDA = let {
        fun registerListeners(registrants: () -> Unit) {
            registrants.invoke()
        }

        fun registerCommands(registrants: () -> Unit) {
            registrants.invoke()
        }

        val jda = JDABuilder(AccountType.BOT).setToken(CONFIG.discord.token).buildAsync()
        val cmd = JDA3Handler(jda)

        registerListeners {
            jda.addEventListener(MessageReceivedListener())
        }
        registerCommands {
            cmd.registerCommand(HelpCommand(cmd))
            cmd.registerCommand(RecordCommand())
        }

        jda.presence.game = Game.playing("the silent game || !!!help")

        jda
    }
}