package com.caesar.hit.beans

import com.caesar.hit.utils.CaesarHitLog
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class Person @Inject constructor() {

    fun  eat(){
        CaesarHitLog.I("说话了")
    }

}