package com.gooseok.sample.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gooseok.sample.db.AppDatabase
import com.gooseok.sample.db.GitUserDao
import com.gooseok.sample.network.GitUserInfo
import com.gooseok.sample.network.RetrofitBuilder
import com.gooseok.sample.repository.GitUserInfoRepository
import com.gooseok.sample.util.JLog
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

open class GitUserInfoViewModel(application : Application) : AndroidViewModel(application),CoroutineScope {
    companion object{
        class Factory(private val app:Application) : ViewModelProvider.NewInstanceFactory(){
            @Suppress("UncheckedCase")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(GitUserInfoViewModel::class.java)){
                    return GitUserInfoViewModel(app) as T
                }
                throw IllegalArgumentException("Unknown ViewModel Class")
            }
        }
    }

    /**
     *  코루틴 관련 객체
     */
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
    private val scope = CoroutineScope(coroutineContext)
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    /**
     * DAO
     */
    private val userDao : GitUserDao = AppDatabase.getInstance(application).gitUserDao()

    /**
     * Repository
     */
    private val repository : GitUserInfoRepository = GitUserInfoRepository(userDao = userDao).getInstance()

    /**
     * Live Data
     */
    private val _userLiveData : MutableLiveData<List<GitUserInfo>> = MutableLiveData(emptyList())
    val userLiveData : LiveData<List<GitUserInfo>> get() = _userLiveData

    /**
     * 초기화 시 LiveData 설정
     */
    init {
        this.initList()
//        scope.launch {
//            withContext(coroutineContext){
//                this@GitUserInfoViewModel._userLiveData.postValue(repository.getList())
//            }
//        }
    }

    /**
     * 목록 가져와서 세팅하기
     */
    fun initList(){
        scope.launch {
            withContext(coroutineContext){
                this@GitUserInfoViewModel._userLiveData.postValue(repository.getList())
            }
        }
    }

    /**
     * User Info Insert
     */
    fun insert(userInfo: GitUserInfo){
        scope.launch {
            withContext(coroutineContext){
                val isExistUserId = userDao.isExistUserId(userInfo.userId) == 1
                if(!isExistUserId) {
                    repository.insert(userInfo = userInfo)
                    Log.d("JTest","Insert Success")
                    this@GitUserInfoViewModel.initList()
                }
            }
        }
    }

    /**
     * Search At UserID In Room
     */
    fun search(userId : String){
        scope.launch {
            withContext(coroutineContext){
                this@GitUserInfoViewModel._userLiveData.postValue(repository.search(userId = userId))
            }
        }
    }

    /**
     * Search At Like UserID In Room
     */
    fun like(userId : String){
        scope.launch {
            withContext(coroutineContext){
                this@GitUserInfoViewModel._userLiveData.postValue(repository.like(userId = userId))
            }
        }
    }

    /**
     * Get User Info At GitHub API
     */
    fun getUserInfo(userId : String){
        RetrofitBuilder.api.getUserInfo(userId = userId).enqueue(object : Callback<GitUserInfo> {
            override fun onResponse(call: Call<GitUserInfo>, response: Response<GitUserInfo>) {
                val userInfo = response.body()
                userInfo?.let { userInfo ->
                    insert(userInfo)

                }
//                Log.d("JTest", userInfo.toString())
                JLog.f(userInfo.toString())
            }

            override fun onFailure(call: Call<GitUserInfo>, t: Throwable) {
//                Log.d("JTest", t.message.toString())
                JLog.e(t.message.toString())
            }

        })
    }

}