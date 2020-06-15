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
import sg.app.weatherapp.util.StringUtil
import java.text.SimpleDateFormat
import java.util.*


class MainViewModel : ViewModel() {
    val appid: String = "e201f96da1403d6b12ea825bcc60233c" // own API key

    private var _weatherData = MutableLiveData<WeatherInfo>()
    private var _temp = MutableLiveData<String>()
    private var _pressure = MutableLiveData<String>()
    private var _sunrise = MutableLiveData<String>()
    private var _sunset = MutableLiveData<String>()
    val temp: LiveData<String>
        get() = _temp
    val pressure: LiveData<String>
        get() = _pressure
    val sunrise: LiveData<String>
        get() = _sunrise
    val sunset: LiveData<String>
        get() = _sunset

    val temp_min: String = ""
    val temp_max: String = ""
    val weatherData: LiveData<WeatherInfo>
        get() = _weatherData

    init {
        _sunset.value = null
        _sunrise.value = null
        _pressure.value = null
        _temp.value = null
        _weatherData.value = null
    }

    fun getWeatherData(lat: String, lon: String) {
        val api = WeatherApi.retrofitService.getWeatherData(lat, lon, appid)

        api.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                _weatherData.value = response.body()
                updateValues()
            }
        })
    }

    private fun updateValues() {
        val tempFloat = weatherData.value?.list?.get(0)?.getMain()?.temp?.toFloat()
        val celsius: Float? = tempFloat?.minus(273.15f)
        _temp.value = String.format("%.1f", celsius) + "°C"

        val sunriseLong = weatherData.value?.city?.getSunrise()
        val sunsetLong = weatherData.value?.city?.getSunset()
        _sunrise.value = sunriseLong?.let { StringUtil().convertMilliToTimeFormat(it) }
        _sunset.value = sunsetLong?.let { StringUtil().convertMilliToTimeFormat(it) }

        _pressure.value = weatherData.value?.list?.get(0)?.getMain()?.pressure.toString()

    }


}