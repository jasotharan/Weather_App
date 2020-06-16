package sg.app.weatherapp.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StringUtil {

    fun convertMilliToTimeFormat(sunset: Long): String {

        val timeString = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))

        return timeString
    }
    fun convertMilliToDay(date: String): String {
        val input_date = "2017-01-30 18:00:00"
        val format1 = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        val dt1 = format1.parse(date)
        val format2: DateFormat = SimpleDateFormat("dd EEE hh a")
        val finalDay: String = format2.format(dt1)

        return finalDay
    }


    fun convertKelvinToCelsius(tempFloat: Float): String {
        val celsius: Float? = tempFloat?.minus(273.15f)
        val celsiusString = String.format("%.1f", celsius) + "°C"

        return celsiusString
    }

    fun convertKelvinToCelsiusInt(tempFloat: Float): String {
        val celsius: Float? = tempFloat?.minus(273.15f)
        val celsiusString = celsius?.toInt().toString() + "°"

        return celsiusString
    }
}