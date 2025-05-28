package com.coutinho.estereofinance.data.utils

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.Date

class Converters {

    // from

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    // to

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

}