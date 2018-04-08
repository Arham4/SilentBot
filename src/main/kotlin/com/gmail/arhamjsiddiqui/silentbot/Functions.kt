package com.gmail.arhamjsiddiqui.silentbot

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.TextChannel
import java.awt.Color
import java.util.concurrent.TimeUnit


/**
 * A random assortment of functions (include extension functions here too)
 *
 * @author Arham 4
 */
object DiscordFunctions {
    /**
     * Extension functions for common functions in JDA.
     *
     * @author Arham 4
     */
    fun TextChannel.queueMessage(message: String) = sendMessage(message).queue()

    fun TextChannel.queueSimpleEmbedMessage(title: String, color: Color, message: String, image: String? = null) {
        queueEmbedMessage { eb ->
            eb.setTitle(title, null)
            eb.setColor(color)
            eb.setDescription(message)
            eb.setThumbnail(image)
        }
    }

    fun TextChannel.queueEmbedMessage(builder: (embedBuilder: EmbedBuilder) -> Unit) {
        val eb = EmbedBuilder()
        builder(eb)
        sendMessage(eb.build()).queue()
    }

    object MarkdownText {
        fun String.italics() = "*$this*"
        fun String.bold() = "**$this**"
        fun String.boldItalics() = "***$this***"
        fun String.underline() = "__${this}__"
        fun String.underlineItalics() = "__*$this*__"
        fun String.underlineBold() = "__**$this**__"
        fun String.underlineBoldItalics() = "__***$this***__"
        fun String.strikethrough() = "~~$this~~"
        fun String.singleCodeBlock() = "`$this`"
        fun String.multiLineCodeBlock() = "```$this```"
        fun String.multiLineCodeBlock(language: String) = "```$language\n$this```"
    }

    val JDA.defaultTextChannel: TextChannel?
        get() = textChannels.firstOrNull { it.canTalk() && !it.isNSFW }
}

fun String.asProperSubjectType(number: Int, plural: String = "${this}s") = if (number == 1) this else plural
val String.mentionToId: String
    get() = replace("<@", "").replace(">", "").replace("!", "")

fun String.isUser(): Boolean {
    return this.startsWith("<@") && this.endsWith(">")
}

fun Long.toTimeString(): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
    val hours = minutes / 60
    val days = hours / 24
    val output = StringBuilder()
    output.append("${minutes - (hours * 60)} minutes")
    if (hours > 0) {
        var insertion = "${hours - (days * 24)} hours"
        if (minutes != 60L) insertion += ", "
        output.insert(0, insertion)
    }
    if (days > 0) {
        var insertion = "$days days"
        if (hours > 0 || minutes > 0) insertion += ", "
        output.insert(0, insertion)
    }
    return output.toString()
}