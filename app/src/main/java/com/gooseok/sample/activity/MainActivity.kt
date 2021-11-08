package com.gooseok.sample.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gooseok.sample.R
import com.gooseok.sample.adapter.GitUserListAdapter
import com.gooseok.sample.contract.GitUserInfoContract
import com.gooseok.sample.network.GitUserInfo
import com.gooseok.sample.presenter.GitUserInfoPresenter
import com.gooseok.sample.util.JLog
import com.gooseok.sample.viewmodel.GitUserInfoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
                    , GitUserInfoContract.View{

    /**
     * @MARK MVVM, ViewModel
     */
    private lateinit var viewModel : GitUserInfoViewModel

    /**
     * @MARK MVP
     */
    private var presenter : GitUserInfoPresenter = GitUserInfoPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

//        viewModel = ViewModelProvider(this).get(GitUserInfoViewModel::class.java)

        /*
        이곳이 바로 메인 맥티비티 입니다!!!!
         */

        /**
         * @MARK MVVM 세팅
         */
        val factory = GitUserInfoViewModel.Companion.Factory(application)
        viewModel = ViewModelProvider(viewModelStore,factory).get(GitUserInfoViewModel::class.java)

        this.initViews()

        /**
         * @MARK MVP 세팅
         */
        this.initViewMVP()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.releaseView()
    }

    private fun initViews(){
        this.initOnClickListener()
        this.initViewModel()
    }

    /**
     * @MARK: Set On Click Listener
     */
    private fun initOnClickListener(){
        viewInitList.setOnClickListener {
            this.viewModel.initList()
            viewInput.text = null
            //            viewInput.clearFocus()
        }

        viewGetUserInfo.setOnClickListener {
            viewInput.text?.let { text ->
                val userId = text.toString()
                if (userId.isNotEmpty()) {
                    this.viewModel.getUserInfo(userId = userId)
//                    this.getUserInfo(userId = userId)
                }else{
                    Toast.makeText(applicationContext,"GitHub ID를 입력해주세여",Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewSearch.setOnClickListener {
            viewInput.text?.let{ text ->
                val userId = text.toString()
                if(userId.isNotEmpty()){
                    this.viewModel.search(userId = userId)
                    //                    this.searchDatabase(userId)
                }else{
                    Toast.makeText(applicationContext,"GitHub ID를 입력해주세여",Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewSearchLike.setOnClickListener {
            if(!viewInput.text.isNullOrEmpty()){
                val userId = viewInput.text.toString()
                if(userId.isNotEmpty()){
                    this.viewModel.like(userId = userId)
                    //                    this.searchLikeDatabase(userId)
                }else{
                    Toast.makeText(applicationContext,"GitHub ID를 입력해주세여",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"GitHub ID를 입력해주세여",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * @MARK Init View Model, ViewModel Observe
     */
    private fun initViewModel(){
        viewModel.userLiveData.observe(this,{
            val userInfoAdapter = GitUserListAdapter{ userInfo ->
                //                delete(userInfo)
            }

            userInfoAdapter.submitList(it)
            viewContent.adapter = userInfoAdapter
        })
    }

    private fun initViewMVP(){
        this.presenter.setView(this)
    }

    /**
     * @MARK MVP, View Override
     */

    override fun showLoading() {
        JLog.f("showLoading")
    }

    override fun hideLoading() {
        JLog.f("hideLoading")
    }

    override fun setItems(items: List<GitUserInfo>) {
        JLog.f("setItems")
    }

    override fun updateView(userInfo: GitUserInfo) {
        JLog.f("updateView")
    }


}