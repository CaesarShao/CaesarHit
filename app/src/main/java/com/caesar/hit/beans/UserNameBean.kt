package com.caesar.hit.beans

import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class UserNameBean constructor(val name: String) {

    init {
        CaesarHitLog.I("UserNameBean构造了")
    }

}