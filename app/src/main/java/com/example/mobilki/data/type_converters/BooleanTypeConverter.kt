package com.example.mobilki.data.type_converters

import androidx.room.TypeConverter

class BooleanTypeConverter {

    @TypeConverter
    fun toInt(bool: Boolean) = if (bool) 1 else 0

    @TypeConverter
    fun fromInt(int: Int) = int == 1

}
