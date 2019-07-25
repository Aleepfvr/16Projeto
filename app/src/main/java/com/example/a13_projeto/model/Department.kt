package com.example.a13_projeto.model

class Department {
    var id:Int = 0
    var name:String?=null
    var initials:String?=null

    constructor(){}

    constructor(id:Int, name:String, initials:String){
        this.id = id
        this.name = name
        this.initials = initials
    }
}
