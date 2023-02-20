package com.contactbook.di.modules

import com.contactbook.data.*
import com.contactbook.data.model.ClientModel
import com.contactbook.di.modules.RemoteModule.REMOTE
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object PersistenceModule {

    const val LOCAL = "local"

    @Singleton
    @Provides
    @Named(LOCAL)
    fun provideDataSource(): DataSource<ClientModel> {
        return LocalDataSource()
    }

    @Singleton
    @Provides
    fun provideClientRepository(@Named(LOCAL) localDataSource: DataSource<ClientModel>,
                                @Named(REMOTE) remoteDataSource: DataSource<ClientModel>): ClientRepository {
        return ClientRepositoryImpl(localDataSource, remoteDataSource)
    }
}