package com.caesar.hit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.caesar.hit.beans.OneData
import com.caesar.hit.beans.Person
import com.caesar.hit.normal.*
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }
    fun initView(){
        findViewById<Button>(R.id.btn1).setOnClickListener {
            val intent = Intent(this,SimpleActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            val intent = Intent(this, MyViewModelActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn3).setOnClickListener {
            val intent = Intent(this, FragActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn4).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        findViewById<Button>(R.id.btn5).setOnClickListener {
            val intent = Intent(this, MoreActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn6).setOnClickListener {
            val intent = Intent(this, InterActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn7).setOnClickListener {
            val intent = Intent(this, NamesActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn8).setOnClickListener {
            val intent = Intent(this, CusViewActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn9).setOnClickListener {
            val intent = Intent(this, OtherActivity::class.java)
            startActivity(intent)
        }

    }
}