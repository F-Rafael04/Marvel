package edu.iesam.marvel.core.cache

import android.content.Context
import edu.iesam.json.R
import edu.iesam.marvel.core.appTime.AppTime

interface AppCache {
    fun updateTime(key: String)
    fun isExpired(key: String, ttl: Long): Boolean
}

class AppCacheTTL(
    private val context: Context,
    private val appTime: AppTime
) : AppCache {


    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.AppCache),
        Context.MODE_PRIVATE
    )

    override fun updateTime(key: String) {
        sharedPref.edit().apply {
            putLong(key, appTime.currentTimeToLong())
            apply()
        }
    }

    override fun isExpired(key: String, ttl: Long): Boolean {
        val timeSaved = sharedPref.getLong(key, -1)
        return (appTime.currentTimeToLong() - timeSaved) >= ttl
    }
}