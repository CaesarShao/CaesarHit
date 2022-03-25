package com.caesar.hit

import android.content.Context
import android.widget.Toast
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class CallBackImpl @Inject constructor(@ActivityContext var context: Context):ICallBack{
    override fun onData() {
        Toast.makeText(context,"onData调用了",Toast.LENGTH_SHORT).show()
        CaesarHitLog.I("数据1调用额")
    }

    override fun onDes() {
        CaesarHitLog.I("数据2调用额")
    }

}