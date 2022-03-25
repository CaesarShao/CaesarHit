package com.caesar.hit.normal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caesar.hit.R
import com.caesar.hit.beans.FragmentData
import com.caesar.hit.beans.FragmentGlobalData
import com.caesar.hit.beans.SingleData
import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
//fragment示例
@AndroidEntryPoint
class BlankFragment : Fragment() {

    @Inject
    lateinit var fragData: FragmentData

    @Inject
    lateinit var fragData2: FragmentGlobalData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CaesarHitLog.I("FragmentData地址:"+fragData)
        CaesarHitLog.I("FragmentGlobalData地址:"+fragData2)
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

}