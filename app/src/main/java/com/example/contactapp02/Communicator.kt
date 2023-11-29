package com.example.contactapp02

interface Communicator {
    fun passDetailData(idText : String, emailText : String, image : Int, name : String, number : String, id : String, email : String)
    fun passContactData()
    fun turnOnContact(name : String)
}