package com.caesar.hit.beans

import android.content.Context
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class MoreDate  @Inject constructor(val simpleData: SimpleData,@ActivityContext val context: Context){

    init {
        CaesarHitLog.I("MoreDate类的构造函数被调用了,context是否为空:"+(context==null))
    }
}