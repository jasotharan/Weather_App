package sg.app.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_list_item.view.*
import sg.app.weatherapp.models.WeatherCatList
import sg.app.weatherapp.util.StringUtil

class WeatherAdapter(val items : List<WeatherCatList>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tempFloat = items.get(position)?.getMain()?.temp?.toFloat()

        holder?.tempTv?.text = tempFloat?.let{ StringUtil().convertKelvinToCelsius(tempFloat)}
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tempTv = view.temp_tv

}