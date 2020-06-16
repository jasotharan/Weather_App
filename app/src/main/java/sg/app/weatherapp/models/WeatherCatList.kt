package sg.app.weatherapp.models
import com.google.gson.annotations.SerializedName

class WeatherCatList {

    @SerializedName("dt")
    private var dt: Int? = null

    @SerializedName("main")
    private var main: DetailsMain? = null

    @SerializedName("weather")
    var weather: List<Weather>? = null
//    @SerializedName("clouds")
//    var clouds: Clouds? = null
    @SerializedName("wind")
    var wind: Wind? = null
//    @SerializedName("rain")
//    var rain: Rain? = null
//    @SerializedName("sys")
//    var sys: Sys? = null

    @SerializedName("dt_txt")
    var dtTxt: String? = null


    fun getMain(): DetailsMain? {
        return main
    }

    fun setMain(coord: DetailsMain?) {
        this.main = coord
    }
}
