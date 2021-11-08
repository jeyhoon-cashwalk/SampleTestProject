package com.gooseok.sample.repository

import androidx.annotation.WorkerThread
import com.gooseok.sample.db.GitUserDao
import com.gooseok.sample.network.GitUserInfo

class GitUserInfoRepository(private val userDao : GitUserDao) {
    @Volatile
    private var instance : GitUserInfoRepository? = null

    /**
     * SingleTon
     */
    fun getInstance():GitUserInfoRepository{
        return instance ?: synchronized(this){
            instance ?: GitUserInfoRepository(userDao).also { instance = it }
        }
    }

//    @Suppress("GitUserInfo Get List")
    @WorkerThread
    suspend fun getList(): List<GitUserInfo>{
        return userDao.getAllUserList()
    }

//    @Suppress("GitUserInfo Insert")
    @WorkerThread
    suspend fun insert(userInfo : GitUserInfo){
        userDao.insert(userInfo = userInfo)
    }

//    @Suppress()
    @WorkerThread
    suspend fun search(userId : String):List<GitUserInfo>{
        return userDao.getWithUserId(userId = userId)
    }

//    @Suppress()
    @WorkerThread
    suspend fun like(userId : String):List<GitUserInfo>{
        return userDao.getLikeUserId(userId = userId)
    }
}