package com.example.pogoda

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class FragmentTwo: Fragment(){

    var surnames:Array<String> = arrayOf("0")
    var itemNum:Long = -1
    private lateinit var text1:TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        surnames = getArguments()!!.getStringArray("additional_weather_info")
        itemNum = getArguments()!!.getLong("index")
        val rootView =  inflater.inflate(R.layout.fragment_two, container, false)
        text1 = rootView.findViewById(R.id.textView1)
        text1.setText(surnames[itemNum.toInt()])

        return rootView
    }


}