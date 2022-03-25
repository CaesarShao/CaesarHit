package com.caesar.hit.beans

import com.caesar.hit.utils.CaesarHitLog
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
@ViewModelScoped
class UserInfo @Inject constructor() {

    var name:String? = "12"
}