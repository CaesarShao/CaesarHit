package com.caesar.hit.normal

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.caesar.hit.R
import com.caesar.hit.beans.SimpleData
import com.caesar.hit.beans.SingleData
import com.caesar.hit.beans.SingleGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyViewModelActivity : AppCompatActivity() {

//    @Inject
//    lateinit var viewmodel: MyViewModel  错误的获取方式

    //    val viewmodel: MyViewModel by  lazy {
//        ViewModelProvider(this).get(MyViewModel::class.java)
//    }//老的获取方式
    val viewmodel by viewModels<MyViewModel>()//便捷获取方式

    @Inject
    lateinit var singleData: SingleData

    @Inject
    lateinit var singleGlobalData: SingleGlobalData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_view)
        CaesarHitLog.I("singleData地址:" + singleData)
        CaesarHitLog.I("singleGlobalData地址:" + singleGlobalData)
        viewmodel.check()
    }
}