package com.caesar.hit.beans

import com.caesar.hit.utils.CaesarHitLog

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class SimpleGlobalData {
    init {
        CaesarHitLog.I("SimpleGlobalData类的构造函数被调用了")
    }
    fun deal(){
        CaesarHitLog.I("SimpleGlobalData调用了方法")
    }
}