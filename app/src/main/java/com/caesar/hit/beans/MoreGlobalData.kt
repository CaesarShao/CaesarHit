package com.caesar.hit.beans

import android.content.Context
import com.caesar.hit.utils.CaesarHitLog

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class MoreGlobalData(val singleData: SingleData, var context: Context){
    init {
        CaesarHitLog.I("MoreGlobalData类的构造函数被调用了,context是否为空:"+(context==null))
    }
}