package com.example.restapiandroid.network.api

import com.google.gson.*
import java.lang.reflect.Type


class BooleanSerializerDeserializer : JsonSerializer<Boolean>,
    JsonDeserializer<Boolean> {
    override fun serialize(
        src: Boolean,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(if (src) 1 else 0)
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Boolean {
        val parse = json.asString
        return parse.equals("true", ignoreCase = true) || parse.equals("1", ignoreCase = true)
    }
}