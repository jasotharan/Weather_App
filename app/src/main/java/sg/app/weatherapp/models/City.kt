package sg.app.weatherapp.models
import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("id")
    private var id: Int? = null
    @SerializedName("name")
    private var name: String? = null
    @SerializedName("coord")
    private var coord: Coord? = null
    @SerializedName("country")
    private var country: String? = null
    @SerializedName("timezone")
    private var timezone: Int? = null
    @SerializedName("sunrise")
    private var sunrise: Long? = null
    @SerializedName("sunset")
    private var sunset: Long? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCoord(): Coord? {
        return coord
    }

    fun setCoord(coord: Coord?) {
        this.coord = coord
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getTimezone(): Int? {
        return timezone
    }

    fun setTimezone(timezone: Int?) {
        this.timezone = timezone
    }

    fun getSunrise(): Long? {
        return sunrise
    }

    fun setSunrise(sunrise: Long?) {
        this.sunrise = sunrise
    }

    fun getSunset(): Long? {
        return sunset
    }

    fun setSunset(sunset: Long?) {
        this.sunset = sunset
    }
}

class Coord {
    @SerializedName("lat")
    var lat: Double? = null
    @SerializedName("lon")
    var lon: Double? = null


}
