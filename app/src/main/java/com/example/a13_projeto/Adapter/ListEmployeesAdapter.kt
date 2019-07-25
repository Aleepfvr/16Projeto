package com.example.a13_projeto.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.a13_projeto.R
import com.example.a13_projeto.model.Employee
import kotlinx.android.synthetic.main.row_layout_employees.view.*

class ListEmployeesAdapter(internal var activity: Activity,
                           internal var lstEmployee: List<Employee>,
                           internal var emp_id: EditText,
                           internal var emp_name: EditText,
                           internal var emp_rg: EditText,
                           internal var dp_id: EditText): BaseAdapter() {
    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout_employees,null)

        rowView.txt_emp_id.text = lstEmployee[position].id.toString()
        rowView.txt_emp_name.text = lstEmployee[position].name.toString()
        rowView.txt_emp_rg.text = lstEmployee[position].rg.toString()
        rowView.txt_emp_dp.text = lstEmployee[position].department.id.toString()

        return rowView
    }

    override fun getItem(i: Int): Any {
        return lstEmployee[i]
    }

    override fun getItemId(i: Int): Long {
        return lstEmployee[i].id.toLong()
    }

    override fun getCount(): Int {
        return lstEmployee.size
    }
}