package com.gooseok.sample.contract

class BaseContract {
    interface Presenter<T>{
        fun setView(view : T)
        fun releaseView()
    }

    interface View{

    }
}