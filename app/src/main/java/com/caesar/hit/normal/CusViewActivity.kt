package com.caesar.hit.normal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.caesar.hit.R
import dagger.hilt.android.AndroidEntryPoint
//自定义view示例
@AndroidEntryPoint
class CusViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cus_view)
        findViewById<CusView>(R.id.custom).showDo()
    }
}