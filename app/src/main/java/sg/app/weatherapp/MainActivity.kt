package sg.app.weatherapp

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import sg.app.weatherapp.databinding.ActivityMainBinding
import sg.app.weatherapp.models.WeatherCatList


class MainActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null


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


        viewModel.getWeatherData("1.29", "103.85")
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog?.setMessage("Loading....")
        progressDialog?.show()

        viewModel.getFiveDaysList().observe(this, Observer<List<WeatherCatList>>
        { fruitList ->
            progressDialog?.dismiss()
            // Access the RecyclerView Adapter and load the data into it
            more_days_rv.adapter = WeatherAdapter(fruitList, this,
                { partItem: WeatherCatList ->
                    viewModel.updateDayTemperature(partItem)
                })
        })

    }


}
