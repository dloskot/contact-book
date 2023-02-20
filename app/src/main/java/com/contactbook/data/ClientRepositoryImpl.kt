package com.contactbook.data

import com.contactbook.data.model.ClientModel
import com.contactbook.di.modules.PersistenceModule.LOCAL
import com.contactbook.di.modules.RemoteModule.REMOTE
import javax.inject.Inject
import javax.inject.Named

class ClientRepositoryImpl @Inject constructor(@Named(LOCAL) private val localDataSource: DataSource<ClientModel>,
                                               @Named(REMOTE) private val remoteDataSource: DataSource<ClientModel>) : ClientRepository {

    override fun getClientsList(): List<ClientModel> {
        return localDataSource.getDataStream()
    }

    override fun saveClient(client: ClientModel) {
        localDataSource.saveData(client)
    }

    override fun getError(): String {
        TODO("Not yet implemented")
    }

    override fun fetchData() {
        TODO("Not yet implemented")
    }
}