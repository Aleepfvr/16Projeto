package com.example.a13_projeto.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.a13_projeto.R
import com.example.a13_projeto.model.Department
import kotlinx.android.synthetic.main.row_layout.view.*

class ListDepartmentAdapter(internal var activity:Activity,
                            internal var lstDepartment: List<Department>,
                            internal var edit_id:EditText,
                            internal var edit_name:EditText,
                            internal var edit_initials:EditText):BaseAdapter() {

    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout,null)

        rowView.txt_row_id.text = lstDepartment[position].id.toString()
        rowView.txt_name.text = lstDepartment[position].name.toString()
        rowView.txt_initials.text = lstDepartment[position].initials.toString()

        return rowView
    }

    override fun getItem(i: Int): Any {
        return lstDepartment[i]
    }

    override fun getItemId(i: Int): Long {
        return lstDepartment[i].id.toLong()
    }

    override fun getCount(): Int {
        return lstDepartment.size
    }
}