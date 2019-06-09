package com.example.pogoda

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
class MyListAdapter(private val context: Activity, private val title: Array<String>) : ArrayAdapter<String>(context, R.layout.one_list_item, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.one_list_item, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView

        titleText.text = title[position]

        return rowView
    }
}