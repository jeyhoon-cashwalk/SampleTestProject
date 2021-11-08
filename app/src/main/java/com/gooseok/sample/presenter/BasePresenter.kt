package com.gooseok.sample.presenter

interface BasePresenter<T> {
    fun takeView(view : T)
    fun dropView()
}