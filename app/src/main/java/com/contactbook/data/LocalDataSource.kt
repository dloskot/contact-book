package com.contactbook.data

import com.contactbook.data.model.ClientModel

class LocalDataSource : DataSource<ClientModel> {

    private val clients = mutableListOf<ClientModel>()

    override fun getDataStream(): List<ClientModel> {
        return clients
    }

    override fun saveData(data: ClientModel) {
        clients.add(data)
    }

    override fun getErrorStream(): String {
        TODO("Not yet implemented")
    }
}