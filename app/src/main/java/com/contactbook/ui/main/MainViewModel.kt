package com.contactbook.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contactbook.App
import com.contactbook.data.ClientRepository
import com.contactbook.data.DataSource
import com.contactbook.data.model.ClientModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: ClientRepository
    private var _clientList = MutableSharedFlow<List<ClientModel>>()
    var clientList = _clientList.asSharedFlow()
    var currentClient = ClientModel()

    init {
        App.appComponent.inject(this)
        fetchClientsList()
    }

    fun fetchClientsList() {
        viewModelScope.launch {
            val clients = repository.getClientsList()
            _clientList.emit(clients)
        }
    }

    fun saveClient() {
        repository.saveClient(currentClient)
    }

    fun newClient() {
        currentClient = ClientModel()
    }
}