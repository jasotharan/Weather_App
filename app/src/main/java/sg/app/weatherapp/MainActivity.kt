package sg.app.weatherapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import sg.app.weatherapp.databinding.ActivityMainBinding
import sg.app.weatherapp.models.WeatherCatList


class MainActivity : AppCompatActivity() {
    var _data = MutableLiveData<List<WeatherCatList>>()
    lateinit var da: List<WeatherCatList>
    var mListRecyclerView: RecyclerView? = null
    var mAdapter: WeatherAdapter? = null

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        more_days_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getWeatherData("1.43", "103.83")

        viewModel.getFiveDaysList().observe(this, Observer<List<WeatherCatList>>
        { fruitList ->
            // Access the RecyclerView Adapter and load the data into it
            more_days_rv.adapter = WeatherAdapter(fruitList, this,
                { partItem: WeatherCatList ->
                    viewModel.updateDayTemperature(partItem)
                })
        })

    }


}
