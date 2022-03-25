package com.caesar.hit.normal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caesar.hit.R
import com.caesar.hit.beans.UserNameBean
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named
//重名示例
@AndroidEntryPoint
class NamesActivity : AppCompatActivity() {

    @Inject
    @Named("name1")
    lateinit var nameOne: UserNameBean

    @Inject
    @Named("name2")
    lateinit var nameTwo: UserNameBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_names)
        CaesarHitLog.I("名字1为:"+nameOne.name)
        CaesarHitLog.I("名字2为:"+nameTwo.name)
    }
}