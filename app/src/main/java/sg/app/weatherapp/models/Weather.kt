package sg.app.weatherapp.models
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("main")
    var main: String? = null

}
