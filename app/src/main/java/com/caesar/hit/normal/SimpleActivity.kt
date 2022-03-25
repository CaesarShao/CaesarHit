package com.caesar.hit.normal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caesar.hit.R
import com.caesar.hit.beans.SimpleData
import com.caesar.hit.beans.SimpleGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//基础用法
@AndroidEntryPoint
class SimpleActivity : AppCompatActivity() {
    @Inject
    lateinit var simpleData1: SimpleData

    @Inject
    lateinit var simpleData2: SimpleData

    @Inject
    lateinit var simpleGlobalData1: SimpleGlobalData

    @Inject
    lateinit var simpleGlobalData2: SimpleGlobalData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        simpleData1.deal()
        CaesarHitLog.I("simpleData1的地址:" + simpleData1.toString())
        CaesarHitLog.I("simpleData2的地址:" + simpleData2.toString())
        simpleGlobalData1.deal()
        CaesarHitLog.I("simpleGlobalData1的地址:" + simpleGlobalData1.toString())
        CaesarHitLog.I("simpleGlobalData2的地址:" + simpleGlobalData2.toString())

    }
}