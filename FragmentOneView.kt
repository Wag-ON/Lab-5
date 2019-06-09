package com.example.pogoda

import android.support.v4.app.Fragment
import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

interface FragmentOneView : MvpView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun ShowThisShit(rootView:View, base_weather_info:Array<String>)

}

