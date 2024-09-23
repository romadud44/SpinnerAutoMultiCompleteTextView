package com.example.spinnerautomulticompletetextview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter(context: Context, personList: MutableList<Person>) :
    ArrayAdapter<Person>(context, R.layout.list_item, personList) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val person = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }


        val personName = view?.findViewById<TextView>(R.id.listNameTV)
        val personLastName = view?.findViewById<TextView>(R.id.listLastNameTV)
        val personAge = view?.findViewById<TextView>(R.id.listAgeTV)
        val personPosition = view?.findViewById<TextView>(R.id.listPositionTV)


        personLastName?.text = person?.lastName
        personAge?.text = "Возраст: ${person?.age}"
        personName?.text = person?.name
        personPosition?.text = person?.position

        return view!!
    }
}