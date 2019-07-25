@file:Suppress("DEPRECATION")

package com.example.a13_projeto.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.a13_projeto.model.Department
import com.example.a13_projeto.model.Employee

@Suppress("DEPRECATION")
class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER) {

    companion object{
        private val DATABASE_VER = 3
        private val DATABASE_NAME = "EDMTDB.db"

        private val TABLE_NAME="Department"
        private val COL_ID="Id"
        private val COL_NAME="Name"
        private val COL_INITIALS="Initials"

        private val TABLE_NAME2="Employee"
        private val COL_ID2="Id"
        private val COL_NAME2="Name"
        private val COL_RG="Rg"
        private val COL_ID_DEP="IdDep"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME TEXT, $COL_INITIALS TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)

        val CREATE_TABLE_QUERY2 = ("CREATE TABLE IF NOT EXISTS $TABLE_NAME2 ($COL_ID2 INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME2 TEXT, $COL_RG INTEGER, $COL_ID_DEP INTEGER, FOREIGN KEY ($COL_ID_DEP) REFERENCES $COL_NAME ($COL_ID))")
        db.execSQL(CREATE_TABLE_QUERY2)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME2")
        onCreate(db)
    }


    //Crud
    val allDepartment:List<Department>
        get(){
            val lstDepartments = ArrayList<Department>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery,null)

            if (cursor.moveToFirst()){
                do{
                    val department = Department()
                    department.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    department.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    department.initials = cursor.getString(cursor.getColumnIndex(COL_INITIALS))

                    lstDepartments.add(department)
                }while (cursor.moveToNext())
            }
            db.close()
            return lstDepartments
        }

    //Crud
    val allEmployee:List<Employee>
        get(){
            val lstEmployee = ArrayList<Employee>()
            val selectQuery = "SELECT * FROM $TABLE_NAME2"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery,null)

            if (cursor.moveToFirst()){
                do{
                    val employee = Employee()
                    employee.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    employee.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    employee.rg = cursor.getInt(cursor.getColumnIndex(COL_RG))
                    employee.department.id = cursor.getInt(cursor.getColumnIndex(COL_ID_DEP))

                    lstEmployee.add(employee)
                }while (cursor.moveToNext())
            }
            db.close()
            return lstEmployee
        }

    fun addDepartment(department: Department){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,department.id)
        values.put(COL_NAME,department.name)
        values.put(COL_INITIALS,department.initials)

        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun addEmployee(employee: Employee){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID2,employee.id)
        values.put(COL_NAME2,employee.name)
        values.put(COL_RG,employee.rg)
        values.put(COL_ID,employee.department.id)

        db.insert(TABLE_NAME2,null,values)
        db.close()
    }

    fun updateDepartment(department: Department):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,department.id)
        values.put(COL_NAME,department.name)
        values.put(COL_INITIALS,department.initials)

        return db.update(TABLE_NAME,values, "$COL_ID=?", arrayOf(department.id.toString()))
    }

    fun updateEmployee(employee: Employee):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID2,employee.id)
        values.put(COL_NAME2,employee.name)
        values.put(COL_RG,employee.department.id)

        return db.update(TABLE_NAME2,values, "$COL_ID2=?", arrayOf(employee.id.toString()))
    }

    fun deleteDepartment(department: Department){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(department.id.toString()))
        db.close()
    }

    fun deleteEmployee(employee: Employee){
        val db = this.writableDatabase

        db.delete(TABLE_NAME2, "$COL_ID2=?", arrayOf(employee.id.toString()))
        db.close()
    }
}