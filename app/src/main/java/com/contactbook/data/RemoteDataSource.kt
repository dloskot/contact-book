package com.contactbook.data

import com.contactbook.data.model.ClientModel

class RemoteDataSource : DataSource<ClientModel> {
    override fun getDataStream(): List<ClientModel> {
        TODO("Not yet implemented")
    }

    override fun saveData(data: ClientModel) {
        TODO("Not yet implemented")
    }

    override fun getErrorStream(): String {
        TODO("Not yet implemented")
    }

    fun fetch() {

    }
}