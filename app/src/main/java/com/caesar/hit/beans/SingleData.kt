package com.caesar.hit.beans

import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
@Singleton
class SingleData @Inject constructor(){
    init {
        CaesarHitLog.I("SingleData类的构造函数被调用了")
    }
}