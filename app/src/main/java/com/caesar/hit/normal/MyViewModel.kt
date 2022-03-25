package com.caesar.hit.normal

import androidx.lifecycle.ViewModel
import com.caesar.hit.beans.SingleData
import com.caesar.hit.beans.SingleGlobalData
import com.caesar.hit.beans.VMData
import com.caesar.hit.beans.VMGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
@HiltViewModel
class MyViewModel @Inject constructor() :ViewModel() {

    @Inject
    lateinit var singleData2: SingleData

    @Inject
    lateinit var singleGlobalData2: SingleGlobalData

    @Inject
    lateinit var vm: VMData

    @Inject
    lateinit var vmg: VMGlobalData

    fun check(){
        CaesarHitLog.I("singleData2地址:"+singleData2)
        CaesarHitLog.I("singleGlobalData2地址:"+singleGlobalData2)
        CaesarHitLog.I("VMData地址:"+vm)
        CaesarHitLog.I("VMGlobalData:"+vmg)
    }

}