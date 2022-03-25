package com.caesar.hit.normal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.caesar.hit.beans.SingleData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class MyBroadReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if ("com.caesar.hit.normal.broad" == intent?.action){
           val singletom =  EntryPointAccessors.fromApplication<MySingleTom>(context!!)
            CaesarHitLog.I("收到了和广播:"+singletom.getSingleData())
        }
    }
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MySingleTom {
        fun getSingleData(): SingleData
    }
}