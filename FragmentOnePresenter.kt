package com.example.pogoda

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.android.synthetic.main.fragment_one.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InjectViewState
class FragmentOnePresenter : MvpPresenter<FragmentOneView>(){


    lateinit var _date: String
    lateinit var _temp: String
    lateinit var _pressure: String
    lateinit var _feel: String
    lateinit var _humidity: String

    lateinit var act:Activity

    val weathers = arrayListOf<Description>()
    val additional_weather_info = arrayOfNulls<String>(5)


    fun getData(this_bundle:Bundle, act:Activity, rootView: View){

        this.act = act

        val retrofit = Retrofit.Builder()
            .baseUrl("http://icomms.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchAllWeathers().enqueue(object : Callback<List<Description>> {
            override fun onResponse(call: Call<List<Description>>, response: Response<List<Description>>) {

                var base_weather_info:Array<String> = Array(5, {"0"})

                for (i in 0..19) {

                    _date = response.body()!![i].date
                    _temp = response.body()!![i].temp
                    _pressure = response.body()!![i].pressure
                    _feel = response.body()!![i].feel
                    _humidity = response.body()!![i].humidity

                    weathers.add(
                        Description(_date, _temp, _pressure, _feel, _humidity)

                    )
                }

                var ii: Int = 0
                for (j in 0..16 step 4) {

                    var base_data = weathers[j+2].date + ": " + weathers[j+2].temp;


                    //val tost1 = Toast.makeText(act.applicationContext, ii.toString() + "\n" + this_bundle.getString("elem"+ii.toString()), Toast.LENGTH_SHORT)
                    //tost1.setGravity(Gravity.CENTER, 0, -10*ii)
                    //tost1.show()

                    base_weather_info[ii] = base_data


                    var data:String = "Ночь:\n"
                    data += "температура: " + weathers[j].temp + " ("
                    data += "ощущается как: " + weathers[j].feel + ")\n"
                    data += "атм. давление: " + weathers[j].pressure + "\n\n"

                    data += "Утро:\n"
                    data += "температура: " + weathers[j+1].temp + " ("
                    data += "ощущается как: " + weathers[j+1].feel + ")\n"
                    data += "атм. давление: " + weathers[j+1].pressure + "\n\n"

                    data += "День:\n"
                    data += "температура: " + weathers[j+2].temp + " ("
                    data += "ощущается как: " + weathers[j+2].feel + ")\n"
                    data += "атм. давление: " + weathers[j+2].pressure + "\n\n"

                    data += "Вечер:\n"
                    data += "температура: " + weathers[j+3].temp + " ("
                    data += "ощущается как: " + weathers[j+3].feel + ")\n"
                    data += "атм. давление: " + weathers[j+3].pressure + "\n\n"

                    additional_weather_info.set(ii, data)
                    ii++

                }

                this_bundle.putStringArray("additional_weather_info", additional_weather_info)

                viewState.ShowThisShit(rootView, base_weather_info)
            }

            override fun onFailure(call: Call<List<Description>>, t: Throwable) {

            }
        })
    }

    fun sayKek(this_bundle:Bundle, itemIdAtPos:Long){
        //Toast.makeText(act, "kek", Toast.LENGTH_LONG).show()

        this_bundle.putLong("index", itemIdAtPos)

        val fragmentTwo = FragmentTwo()

        if(act.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentTwo.setArguments(this_bundle)
            (act as AppCompatActivity).supportFragmentManager!!.beginTransaction()
                .replace(R.id.fragment_holder, fragmentTwo)
                .addToBackStack(null)
                .commit()
        }
        if(act.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTwo.setArguments(this_bundle)
            (act as AppCompatActivity).supportFragmentManager!!.beginTransaction()
                .replace(R.id.list_holder, fragmentTwo)
                .addToBackStack(null)
                .commit()
        }
    }

}