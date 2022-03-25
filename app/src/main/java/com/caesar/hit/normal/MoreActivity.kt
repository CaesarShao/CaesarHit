package com.caesar.hit.normal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.caesar.hit.R
import com.caesar.hit.beans.FragmentData
import com.caesar.hit.beans.FragmentGlobalData
import com.caesar.hit.beans.MoreDate
import com.caesar.hit.beans.MoreGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoreActivity : AppCompatActivity() {

    @Inject
    lateinit var fragData: MoreDate

    @Inject
    lateinit var fragData2: MoreGlobalData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        CaesarHitLog.I("显示数据:"+fragData)
        CaesarHitLog.I("显示数据2:"+fragData.simpleData)
        CaesarHitLog.I("显示数据3:"+fragData2)
        CaesarHitLog.I("显示数据4:"+fragData2.singleData)
    }
}