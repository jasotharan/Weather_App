package sg.app.weatherapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sg.app.weatherapp.models.WeatherCatList
import sg.app.weatherapp.models.WeatherInfo
import sg.app.weatherapp.network.WeatherApi
import sg.app.weatherapp.util.StringUtil


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = getApplication<Application>().applicationContext

    val appid: String = "e201f96da1403d6b12ea825bcc60233c" // own API key

    private var _weatherData = MutableLiveData<WeatherInfo>()
    private var _temp = MutableLiveData<String>()
    private var _pressure = MutableLiveData<String>()
    private var _seaLevelpressure = MutableLiveData<String>()
    private var _sunrise = MutableLiveData<String>()
    private var _sunset = MutableLiveData<String>()
    private var _wind = MutableLiveData<String>()
    private var _humidity = MutableLiveData<String>()
    private var _temp_min = MutableLiveData<String>()
    private var _temp_max = MutableLiveData<String>()
    private var _temperature_date = MutableLiveData<String>()
    private var _fiveDaysList: MutableLiveData<List<WeatherCatList>>? = null

    fun getFiveDaysList(): MutableLiveData<List<WeatherCatList>> {
        if (_fiveDaysList == null) {
            _fiveDaysList = MutableLiveData()
        }
        return _fiveDaysList as MutableLiveData<List<WeatherCatList>>
    }

    val temp: LiveData<String>
        get() = _temp
    val pressure: LiveData<String>
        get() = _pressure
    val seaLevelpressure: LiveData<String>
        get() = _seaLevelpressure
    val sunrise: LiveData<String>
        get() = _sunrise
    val sunset: LiveData<String>
        get() = _sunset
    val wind: LiveData<String>
        get() = _wind
    val humidity: LiveData<String>
        get() = _humidity
    val temp_min: LiveData<String>
        get() = _temp_min
    val temp_max: LiveData<String>
        get() = _temp_max
    val temperature_date: LiveData<String>
        get() = _temperature_date

    val weatherData: LiveData<WeatherInfo>
        get() = _weatherData

    init {
        _sunset.value = null
        _sunrise.value = null
        _pressure.value = null
        _seaLevelpressure.value = null
        _temp.value = null
        _wind.value = null
        _humidity.value = null
        _temp_min.value = null
        _temp_max.value = null
        _temperature_date.value = null
        _weatherData.value = null
    }

    fun getWeatherData(lat: String, lon: String) {
        val api = WeatherApi.retrofitService.getWeatherData(lat, lon, appid)

        api.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)

                _weatherData.value = SimpleSessionStore().getStoresList(mContext)
                updateValues()
                _fiveDaysList?.value = weatherData.value?.list
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                _weatherData.value = response.body()
                // save list in local session
                SimpleSessionStore().saveStoresList(mContext, response.body())
                updateValues()

                _fiveDaysList?.value = weatherData.value?.list
            }
        })
    }

    fun getWeatherDataBycity(city: String) {
        val api = WeatherApi.retrofitService.getWeatherDataBycity(city, appid)

        api.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)

                _weatherData.value = SimpleSessionStore().getStoresList(mContext)
                updateValues()
                _fiveDaysList?.value = weatherData.value?.list
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                _weatherData.value = response.body()
                // save list in local session
                SimpleSessionStore().saveStoresList(mContext, response.body())
                updateValues()
                _fiveDaysList?.value = weatherData.value?.list
            }
        })
    }

    private fun updateValues() {

        val sunriseLong = weatherData.value?.city?.getSunrise()
        val sunsetLong = weatherData.value?.city?.getSunset()

        _sunrise.value = sunriseLong?.let { StringUtil().convertMilliToTimeFormat(it) }
        _sunset.value = sunsetLong?.let { StringUtil().convertMilliToTimeFormat(it) }

        updateDayTemperature(weatherData.value?.list?.get(0))
    }

    fun updateDayTemperature(weather: WeatherCatList?) {

        val tempFloat = weather?.getMain()?.temp?.toFloat()
        val tempMinFloat = weather?.getMain()?.tempMin?.toFloat()
        val tempMaxFloat = weather?.getMain()?.tempMax?.toFloat()

        _temp.value = tempFloat?.let { StringUtil().convertKelvinToCelsius(tempFloat) }
        _temp_min.value =
            "Min Temp : " + tempMinFloat?.let { StringUtil().convertKelvinToCelsius(tempMinFloat) }
        _temp_max.value =
            "Max Temp : " + tempMaxFloat?.let { StringUtil().convertKelvinToCelsius(tempMaxFloat) }
        _temperature_date.value = "Temperature at : " + weather?.dtTxt
        _pressure.value = weather?.getMain()?.pressure.toString() + " hPa"
        _seaLevelpressure.value = weather?.getMain()?.seaLevel.toString() + " hPa"
        _humidity.value = weather?.getMain()?.humidity.toString() + " %"
        _wind.value = weather?.wind?.speed.toString()

    }

}