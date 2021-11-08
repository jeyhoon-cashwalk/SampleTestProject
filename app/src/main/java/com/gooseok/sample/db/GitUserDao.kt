package com.gooseok.sample.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gooseok.sample.network.GitUserInfo

@Dao
interface GitUserDao {
    @Query("SELECT * FROM GitUserInfo")
    fun getAllUserListObserved(): LiveData<List<GitUserInfo>>

    @Query("SELECT * FROM GitUserInfo")
    fun getAllUserList(): List<GitUserInfo>

    @Query("SELECT * FROM GitUserInfo WHERE userId = :userId")
    fun getWithUserId(userId : String):List<GitUserInfo>

    @Query("SELECT * FROM GitUserInfo WHERE userId LIKE '%' || :userId || '%'")
    fun getLikeUserId(userId : String):List<GitUserInfo>

    @Query("SELECT COUNT(*) FROM GitUserInfo WHERE userId = :userId")
    fun isExistUserId(userId : String):Int

    @Insert
    fun insert(userInfo : GitUserInfo)

    @Insert
    fun insert(userInfo : List<GitUserInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg userInfo : GitUserInfo)

    @Update
    fun update(userInfo : GitUserInfo)

    @Update
    fun update(userInfo : List<GitUserInfo>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg userInfo : GitUserInfo)

    @Delete
    fun delete(userInfo : GitUserInfo)

    @Delete
    fun delete(vararg userInfo : GitUserInfo)

    @Delete
    fun delete(userInfo : List<GitUserInfo>)

}