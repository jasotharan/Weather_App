package sg.app.weatherapp

import android.app.ProgressDialog
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import sg.app.weatherapp.databinding.ActivityMainBinding
import sg.app.weatherapp.models.WeatherCatList
import sg.app.weatherapp.util.StringUtil


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

        // TODO to get user's latitude and longitude
        viewModel.getWeatherData("1.29", "103.85")  // sg location
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog?.setMessage("Loading....")
        progressDialog?.show()

        // search location
        binding.btnSearch.setOnClickListener {
            if (binding.etCityOrCountryName.text.isNotBlank()) {
                viewModel.getWeatherDataBycity(binding.etCityOrCountryName.text.toString())
                progressDialog?.show()
                //   hide key board after click
                val view = this.currentFocus
                view?.let { v ->
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        binding.ivSearchImage.setOnClickListener {
            btn_search.visibility = View.VISIBLE
            et_city_or_country_name.visibility = View.VISIBLE
        }

        // this for bottom recycle view
        viewModel.getFiveDaysList().observe(this, Observer<List<WeatherCatList>>
        { dataList ->
            progressDialog?.dismiss()
            if (dataList != null) { // if location not found
                btn_search.visibility = View.GONE
                et_city_or_country_name.visibility = View.GONE
                // Access the RecyclerView Adapter and load the data into it
                // TODO improve show list view for customer attraction and more details
                more_days_rv.adapter = WeatherAdapter(dataList, this,
                    { partItem: WeatherCatList ->
                        viewModel.updateDayTemperature(partItem)
                        setBackgroundImg(partItem.dtTxt)
                    })
            } else {
                Toast.makeText(this, "No result found", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //TODO  It can be modified further specifically in future by considering both time and weather condition
    private fun setBackgroundImg(dtTxt: String?) {
        var isNight = dtTxt?.let { StringUtil().isNight(it) }!!
        if (isNight) {
            scroll_view.setBackgroundResource(R.drawable.bag_img_nt)
        } else {
            scroll_view.setBackgroundResource(R.drawable.bag_img)
        }
    }

}
