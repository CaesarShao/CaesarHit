package com.caesar.hit.normal

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.caesar.hit.beans.FragmentData
import com.caesar.hit.beans.FragmentGlobalData
import com.caesar.hit.beans.ServiceData
import com.caesar.hit.beans.ServiceGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
@AndroidEntryPoint
class MyService:Service() {

    @Inject
    lateinit var data: ServiceData

    @Inject
    lateinit var data2: ServiceGlobalData

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        CaesarHitLog.I("data地址:"+data)
        CaesarHitLog.I("data2地址:"+data2)
    }
}