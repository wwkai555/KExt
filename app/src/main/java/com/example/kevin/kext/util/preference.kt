package com.example.kevin.kext.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by kevin on 23/11/2017.
 */

class Preference<T>(context: Context, private val key: String, private val defaultValue: T) : ReadWriteProperty<Any?, T> {
    companion object {
        val preferenceName = "KKPreference"
    }

    private val sharePreference: SharedPreferences by lazy { context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE) }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = with(sharePreference) {
        return defaultValue.run {
            val v: Any = when (this) {
                is Int -> getInt(key, this)
                is Long -> getLong(key, this)
                is Float -> getFloat(key, this)
                is Boolean -> getBoolean(key, this)
                is String -> getString(key, this)
                else -> illegal()
            }
            v as T
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = with(sharePreference.edit()) {
        when (value) {
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> illegal()
        }
        apply()
    }
}

fun <T> Context.preference(key: String, defaultValue: T): Preference<T> = Preference(this, key, defaultValue)