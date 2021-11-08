package com.gooseok.sample.contract

import com.gooseok.sample.db.GitUserDao
import com.gooseok.sample.network.GitUserInfo

interface GitUserInfoContract {
    interface View : BaseContract.View{
        fun showLoading()
        fun hideLoading()
        fun setItems(items : List<GitUserInfo>)
        fun updateView(userInfo : GitUserInfo)
    }
    interface Presenter : BaseContract.Presenter<View>{
        fun getUserList(userDao: GitUserDao) : List<GitUserInfo>
    }
}