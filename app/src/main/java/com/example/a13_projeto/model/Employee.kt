package com.example.a13_projeto.model

class Employee {
    var id:Int=0
    var name:String?=null
    var rg:Int=0
    var department:Department = Department()

    constructor(){}

    constructor(id:Int, name:String, rg:Int, department: Department) {
        this.id = id
        this.name = name
        this.rg = rg
        this.department = department
    }
}