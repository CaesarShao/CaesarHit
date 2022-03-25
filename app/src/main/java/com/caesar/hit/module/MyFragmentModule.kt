package com.caesar.hit.module

import com.caesar.hit.beans.FragmentGlobalData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */

@InstallIn(FragmentComponent::class)
@Module
class MyFragmentModule {

    @Provides
    fun providerFrag(): FragmentGlobalData {
        return FragmentGlobalData()
    }

}