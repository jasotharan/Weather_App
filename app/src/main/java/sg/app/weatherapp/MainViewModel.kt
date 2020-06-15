package sg.app.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sg.app.weatherapp.models.WeatherInfo
import sg.app.weatherapp.network.WeatherApi

class MainViewModel : ViewModel() {
    val appid: String = "e201f96da1403d6b12ea825bcc60233c" // own API key

    private var _weatherData = MutableLiveData<WeatherInfo>()
    val weatherData: LiveData<WeatherInfo>
        get() = _weatherData

    init {
        _weatherData.value = null
    }

    fun getWeatherData(lat: String,lon: String) {
        val api = WeatherApi.retrofitService.getWeatherData(lat,lon,appid)

        api.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                _weatherData.value = response.body()
            }
        })
    }
}