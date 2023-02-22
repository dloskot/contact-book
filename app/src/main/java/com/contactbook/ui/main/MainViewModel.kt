package com.contactbook.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contactbook.App
import com.contactbook.data.ClientRepository
import com.contactbook.data.model.ClientModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: ClientRepository
    private var internalList = emptyList<ClientModel>()
    private var _clientList = MutableSharedFlow<List<ClientModel>>()
    var clientList = _clientList.asSharedFlow()
    var currentClient = ClientModel()
    var editMode: Boolean = false
    val editClient: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    init {
        App.appComponent.inject(this)
    }

    fun fetchClientsList() {
        viewModelScope.launch {
            internalList = repository.getClientsList()
            _clientList.emit(internalList)
        }
    }

    fun addClient() {
        repository.saveClient(currentClient)
    }

    fun newClient() {
        editMode = false
        currentClient = ClientModel()
    }

    fun editClient(position: Int) {
        editMode = true
        viewModelScope.launch {
            currentClient = internalList[position]
            editClient.postValue(true)
        }
    }
}