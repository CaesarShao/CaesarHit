package com.caesar.hit.normal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.caesar.hit.ICallBack
import com.caesar.hit.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class InterActivity : AppCompatActivity() {

    @Inject
    lateinit var calBack: ICallBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inter)
        calBack.onData()
        calBack.onDes()
    }
}