package sg.app.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

class StringUtil {

    fun convertMilliToTimeFormat(sunset: Long): String {

        val timeString = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))

        return timeString
    }
}