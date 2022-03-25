package com.caesar.hit.module

import com.caesar.hit.CallBackImpl
import com.caesar.hit.ICallBack
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class MyAbsModule {

    @Binds
    abstract fun provideCallback(callback: CallBackImpl):ICallBack

}