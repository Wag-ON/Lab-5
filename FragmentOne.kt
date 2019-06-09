package com.example.pogoda

import android.content.Context
import android.content.res.Configuration
import android.media.VolumeShaper
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_one.*
import java.io.Serializable
import android.view.Gravity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class FragmentOne: MvpAppCompatFragment(),FragmentOneView {


    @InjectPresenter
    lateinit var mPresenter: FragmentOnePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView =  inflater.inflate(R.layout.fragment_one, container, false)

        mPresenter.getData(getArguments()!!, activity!!, rootView)

        return rootView
    }

    override fun ShowThisShit(rootView:View, base_weather_info:Array<String>){

        val listview:ListView = rootView.findViewById(R.id.myList)

        val myListAdapter = MyListAdapter(activity!!,base_weather_info)
        listview.adapter = myListAdapter

        listview.setOnItemClickListener(){adapterView, view, position, id ->

            val itemId = adapterView.getItemIdAtPosition(position)
            //Toast.makeText(activity, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()

            mPresenter.sayKek(getArguments()!!, itemId)

        }
    }
}