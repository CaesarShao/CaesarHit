package com.caesar.hit.module

import com.caesar.hit.beans.OtherBean
import com.caesar.hit.beans.SimpleGlobalData
import com.caesar.hit.beans.UserNameBean
import com.caesar.hit.utils.CaesarHitLog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */

@InstallIn(ActivityComponent::class)
@Module
class MyActModule {

    @Provides
    fun providerSimpleGlobal(): SimpleGlobalData {
        return SimpleGlobalData()
    }


    @Named("name1")
    @Provides
    fun providerUserName1(): UserNameBean {
        return  UserNameBean("111")
    }

    @Named("name2")
    @Provides
    fun providerUserName2(): UserNameBean {
        return  UserNameBean("222")
    }

    @Provides
    fun proviewOther(): OtherBean {
        CaesarHitLog.I("OtherBean创建了")
        return OtherBean()
    }


}