package com.caesar.hit.normal

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatSeekBar
import com.caesar.hit.beans.CusViewData
import com.caesar.hit.beans.CusViewGlobalData
import com.caesar.hit.beans.FragmentData
import com.caesar.hit.beans.FragmentGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
@AndroidEntryPoint
class CusView :View {

    @Inject
    lateinit var data: CusViewData

    @Inject
    lateinit var data2: CusViewGlobalData

    fun showDo(){
        CaesarHitLog.I("数据1显示:"+data)
        CaesarHitLog.I("数据2显示:"+data2)
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }


}