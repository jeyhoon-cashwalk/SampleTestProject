package com.gooseok.sample.presenter

import com.gooseok.sample.contract.GitUserInfoContract
import com.gooseok.sample.db.GitUserDao
import com.gooseok.sample.network.GitUserInfo
import com.gooseok.sample.util.JLog

class GitUserInfoPresenter : GitUserInfoContract.Presenter {

    private lateinit var view : GitUserInfoContract.View

    override fun getUserList(userDao: GitUserDao) : List<GitUserInfo> {
        JLog.f("takeView")
        return userDao.getAllUserList()
    }

    override fun setView(view: GitUserInfoContract.View) {
        JLog.f("setView")
        this.view = view
    }

    override fun releaseView() {
        JLog.f("releaseView")
    }

}