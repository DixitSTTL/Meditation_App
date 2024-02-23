package com.app.meditation.ui.util

import android.os.Bundle
import androidx.navigation.NavType
import com.app.meditation.ui.screen.tuneList.DataTunes
import com.google.gson.Gson


abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}


class DataTunesArgType : NavType<DataTunes>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): DataTunes? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): DataTunes {
        return Gson().fromJson(value, DataTunes::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: DataTunes) {
        bundle.putParcelable(key, value)
    }
}
