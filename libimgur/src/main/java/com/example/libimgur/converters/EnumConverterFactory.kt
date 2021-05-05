package com.example.libimgur.converters

import com.squareup.moshi.Json
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EnumConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): Converter<Enum<*>, String>? {
//        return super.stringConverter(type, annotations, retrofit)
        return if (type is Class<*> && type.isEnum) {
            Converter<Enum<*>, String> {
                try {
                    it.javaClass.getField(it.name).getAnnotation(Json::class.java).name
                    // arnav said this `type` variable is denoting Section enum class
                    // means parent class
                } catch (e: Exception) {
                    null
                }
            }
        } else {
            null
        }
        // we will return null if receiving type is not enum class
        // and if it couldn't be converted into string
    }

    // First we will check the type like it should be the class & we also check that
    // it should be enum. If we found both conditions true then we will get JSON annotations
    // then will get the name of those annotations
    // & then this Converter from retrofit will now convert the enum type to String type
}