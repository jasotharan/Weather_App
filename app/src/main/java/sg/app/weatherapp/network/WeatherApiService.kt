package sg.app.weatherapp.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import sg.app.weatherapp.models.WeatherInfo
//http://api.openweathermap.org/data/2.5/forecast?lat=1.43&lon=103.83&appid=e201f96da1403d6b12ea825bcc60233c

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

interface WeatherApiService {
    @GET("/users/{profile}")
    fun getUserData(@Path("profile") profile: String):
            Call<WeatherInfo>
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}