package edu.iesam.marvel.core.appTime

interface AppTime {
    fun currentTimeToLong(): Long
}

class LocalAppTime : AppTime {
    override fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }
}