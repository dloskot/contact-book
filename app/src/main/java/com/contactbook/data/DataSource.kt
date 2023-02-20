package com.contactbook.data

interface DataSource<T> {
    fun getDataStream() : List<T>
    fun saveData(data: T)
    fun getErrorStream() : String
}