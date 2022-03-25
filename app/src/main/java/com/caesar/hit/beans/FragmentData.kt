package com.caesar.hit.beans

import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
@FragmentScoped
class FragmentData @Inject constructor(){
    init {
        CaesarHitLog.I("FragmentData类的构造函数被调用了")
    }
}