package sg.app.weatherapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sg.app.weatherapp.models.WeatherInfo
import java.util.*

class SimpleSessionStore {
    val SHARED_PREFERENCE = "shared_preference"
    val DATS_LIST = "data_list"


    fun saveStoresList(context: Context, articleList: WeatherInfo?) {
        val myPrefs = context.getSharedPreferences(SHARED_PREFERENCE, 0)
        val prefEditor = myPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(articleList)
        prefEditor.putString(DATS_LIST, json)
        prefEditor.apply()
    }

    fun getStoresList(context: Context): WeatherInfo? {
        val myPrefs = context.getSharedPreferences(SHARED_PREFERENCE, 0)
        val articleList = myPrefs.getString(DATS_LIST, null) ?: return null
        val gson = Gson()
        val type = object : TypeToken<WeatherInfo?>() {}.type
        return gson.fromJson<WeatherInfo?>(articleList, type)
    }

    fun removeStoresList(context: Context) {
        val myPrefs = context.getSharedPreferences(SHARED_PREFERENCE, 0)
        val myPrefsEditor = myPrefs.edit()
        myPrefsEditor.remove(DATS_LIST)
        myPrefsEditor.apply()
    }

}