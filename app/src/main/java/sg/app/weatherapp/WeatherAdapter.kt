package sg.app.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_list_item.view.*
import sg.app.weatherapp.models.WeatherCatList
import sg.app.weatherapp.util.StringUtil

class WeatherAdapter(
    val items: List<WeatherCatList>,
    val context: Context,
    val clickListener: (WeatherCatList) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.weather_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tempFloat = items.get(position)?.getMain()?.temp?.toFloat()
        val tempMinFloat = items.get(position)?.getMain()?.tempMin?.toFloat()
        val tempMaxFloat = items.get(position)?.getMain()?.tempMax?.toFloat()
        val weather = items.get(position)?.weather?.get(0)?.main
        val date = items.get(position)?.dtTxt

        holder.tempTv?.text = tempFloat?.let { StringUtil().convertKelvinToCelsius(tempFloat) }
        holder.tempMinMaxTv?.text = tempMinFloat?.let {
            StringUtil().convertKelvinToCelsiusInt(tempMinFloat)
        } + "/" +
                tempMaxFloat?.let { StringUtil().convertKelvinToCelsiusInt(tempMaxFloat) }
        holder.weatherTv?.text = weather
        holder.dayAndDateTv?.text = date?.let { StringUtil().convertMilliToDay(it) }
        holder?.itemView?.setOnClickListener {
            clickListener(items.get(position))
        }

    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tempTv = view.temp_tv
    val tempMinMaxTv = view.temp_min_n_max_tv
    val weatherTv = view.weather_tv
    val dayAndDateTv = view.day_and_date_tv

}