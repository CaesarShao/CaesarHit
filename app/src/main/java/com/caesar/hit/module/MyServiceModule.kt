package com.caesar.hit.module

import com.caesar.hit.beans.ServiceGlobalData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */

@InstallIn(ServiceComponent::class)
@Module
class MyServiceModule {

    @Provides
    fun providerSerFrag(): ServiceGlobalData {
        return ServiceGlobalData()
    }

}