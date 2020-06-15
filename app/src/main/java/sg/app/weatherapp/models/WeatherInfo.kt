package sg.app.weatherapp.models
import com.google.gson.annotations.SerializedName

data class WeatherInfo(

    @SerializedName("cod")
    var cod: String,

    @SerializedName("cnt")
    var cnt: Int,

    @SerializedName("city")
    var city: City,

    @SerializedName("list")
    var list: List<WeatherCatList>? = null


)