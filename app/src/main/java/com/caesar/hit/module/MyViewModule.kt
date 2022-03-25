package com.caesar.hit.module

import com.caesar.hit.beans.CusViewGlobalData
import com.caesar.hit.beans.FragmentGlobalData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewComponent

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */

@InstallIn(ViewComponent::class)
@Module
class MyViewModule {

    @Provides
    fun providerCus(): CusViewGlobalData {
        return CusViewGlobalData()
    }

}