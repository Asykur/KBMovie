package com.asykurkhamid.kitabisamovie.database

import androidx.room.TypeConverter
import kotlin.collections.ArrayList


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Int?): List<Int>? {
        return when (value) {
            null -> null
            else -> ArrayList<Int>(value)
        }
    }

    @TypeConverter
    fun toTimestamp(date: List<Int>?): Int? {
        var dataInt: Int = 0

        if (date == null) {
            return dataInt
        }

        for (i in date.indices) {
            dataInt += date[i]
        }
        return dataInt
    }
}
