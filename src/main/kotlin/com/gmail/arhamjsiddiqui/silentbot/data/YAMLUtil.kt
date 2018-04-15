package com.gmail.arhamjsiddiqui.silentbot.data

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files
import kotlin.reflect.KClass

private val mapper: ObjectMapper
    get() {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
        return mapper
    }

/**
 * Called YAMLParse to not conflict with jackson's YAMLParser.
 *
 * @author Arham 4
 */
object YAMLParse {
    /**
     * Takes in a data class (with ::class) and parses it by the fileName provided, returning the appropriate class
     * originally provided with parsed data.
     */
    fun <T : Any> parseDto(fileName: String, dto: KClass<T>): T {
        return Files.newBufferedReader(FileSystems.getDefault().getPath(fileName)).use { mapper.readValue(it, dto.java) }
    }
}

object YAMLWrite {
    fun writeDto(fileName: String, content: Any) {
        mapper.writerWithDefaultPrettyPrinter().writeValue(File(fileName), content)
    }
}