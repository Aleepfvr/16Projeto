package com.example.a13_projeto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a13_projeto.Adapter.ListEmployeesAdapter
import com.example.a13_projeto.DBHelper.DBHelper
import com.example.a13_projeto.model.Department
import com.example.a13_projeto.model.Employee
import kotlinx.android.synthetic.main.activity_list_employees.*

class ListEmployees : AppCompatActivity() {

    internal var lstEmployees: List<Employee> = ArrayList<Employee>()
    internal lateinit var db:DBHelper
    internal var department:Department = Department()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_employees)

        db = DBHelper(this)

        refreshData()

        //event
        btn_emp_add.setOnClickListener {
            val employee = Employee(
                Integer.parseInt(emp_id.text.toString()),
                emp_name.text.toString(),
                Integer.parseInt(emp_rg.text.toString()),
                department
            )
            db.addEmployee(employee)
            refreshData()
        }
        btn_emp_update.setOnClickListener {
            val employee = Employee(
                Integer.parseInt(emp_id.text.toString()),
                emp_name.text.toString(),
                Integer.parseInt(emp_rg.text.toString()),
                department
            )
            db.updateEmployee(employee)
            refreshData()
        }
        btn_emp_delete.setOnClickListener {
            val employee = Employee(
                Integer.parseInt(emp_id.text.toString()),
                emp_name.text.toString(),
                Integer.parseInt(emp_rg.text.toString()),
                department
            )
            db.deleteEmployee(employee)
            refreshData()
        }


    }

    private fun refreshData() {
        lstEmployees = db.allEmployee
        val adapter = ListEmployeesAdapter(this@ListEmployees,lstEmployees,emp_id,emp_name,emp_rg,dp_id)
        list_employees.adapter = adapter
    }

}
