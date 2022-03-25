package com.caesar.hit.normal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caesar.hit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag)
        val frag = BlankFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fm,frag).commit()
    }
}