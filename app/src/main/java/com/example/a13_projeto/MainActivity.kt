package com.example.a13_projeto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.a13_projeto.Adapter.ListDepartmentAdapter
import com.example.a13_projeto.DBHelper.DBHelper
import com.example.a13_projeto.model.Department
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var lstDepartment:List<Department> = ArrayList<Department>()
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        //event
        btn_add.setOnClickListener {
            val department = Department(
                Integer.parseInt(ed_id.text.toString()),
                ed_name.text.toString(),
                ed_initials.text.toString()
            )
            db.addDepartment(department)
            refreshData()
        }
        btn_update.setOnClickListener {
            val department = Department(
                Integer.parseInt(ed_id.text.toString()),
                ed_name.text.toString(),
                ed_initials.text.toString()
            )
            db.updateDepartment(department)
            refreshData()
        }
        btn_delete.setOnClickListener {
            val department = Department(
                Integer.parseInt(ed_id.text.toString()),
                ed_name.text.toString(),
                ed_initials.text.toString()
            )
            db.deleteDepartment(department)
            refreshData()
        }
        listView = findViewById(R.id.list_department)

        listView.adapter = ListDepartmentAdapter(this,lstDepartment,ed_id,ed_name,ed_initials)


        listView.setOnItemClickListener { adapterView: AdapterView<*>, view: View, i: Int, l: Long ->
            for (i: Int in i..lstDepartment.size) {
                if (i < lstDepartment.size) {
                    val lstEmployees = Intent(this@MainActivity, ListEmployees::class.java)
                    lstEmployees.putExtra("nameDepartment", lstDepartment[i].name)
                    startActivity(lstEmployees)

                }
            }
        }

    }

    private fun refreshData() {
        lstDepartment = db.allDepartment
        val adapter = ListDepartmentAdapter(this@MainActivity,lstDepartment,ed_id,ed_name,ed_initials)
        list_department.adapter = adapter

    }

}
