package com.caesar.hit.normal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.caesar.hit.R
import com.caesar.hit.beans.SingleData
import com.caesar.hit.beans.SingleGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyViewModelActivity : AppCompatActivity() {


    val viewmodel: MyViewModel by  lazy {
        ViewModelProvider(this).get(MyViewModel::class.java)
    }

    @Inject
    lateinit var singleData: SingleData

    @Inject
    lateinit var singleGlobalData: SingleGlobalData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_view)
        CaesarHitLog.I("singleData地址:"+singleData)
        CaesarHitLog.I("singleGlobalData地址:"+singleGlobalData)
        viewmodel.check()
    }
}