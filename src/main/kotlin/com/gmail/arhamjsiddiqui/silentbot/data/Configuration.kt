package com.gmail.arhamjsiddiqui.silentbot.data

data class DiscordDto(val token: String)
data class ConfigDto(val discord: DiscordDto, val gitRepoUrl: String, val blacklistedServers: Array<String>)

val CONFIG: ConfigDto = YAMLParse.parseDto("config.yaml", ConfigDto::class)!!
fun isBlacklistedServer(guildId: String): Boolean = CONFIG.blacklistedServers.contains(guildId)