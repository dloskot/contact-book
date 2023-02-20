package com.contactbook.di.modules

import com.contactbook.data.DataSource
import com.contactbook.data.RemoteDataSource
import com.contactbook.data.model.ClientModel
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object RemoteModule {

    const val REMOTE = "remote"

    @Singleton
    @Provides
    @Named(REMOTE)
    fun provideDataSource(): DataSource<ClientModel> {
        return RemoteDataSource()
    }
}