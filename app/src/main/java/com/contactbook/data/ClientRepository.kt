package com.contactbook.data

import com.contactbook.data.model.ClientModel

interface ClientRepository {
    fun getClientsList(): List<ClientModel>
    fun saveClient(client: ClientModel)
    fun getError(): String
    fun fetchData()
}