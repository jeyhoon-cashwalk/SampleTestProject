package com.gooseok.sample.util

import android.util.Log

object JLog {
    val VERBOSE = 2
    val DEBUG = 3
    val INFO = 4
    val WARN = 5
    val ERROR = 6
    val ASSERT = 7
    val TAG = "JTest"
    val ETAG = "JErrorTag"
    val HERE = "**************************************************************************************"

    fun v(msg : String) : Int{
        return Log.v(TAG,buildLogMsg(msg))
    }

    fun d(msg : String) : Int{
        return Log.d(TAG, buildLogMsg(msg))
    }

    fun f(msg : String) : Int{
        return Log.d(TAG, buildLogMsg(msg))
    }

    fun e(msg : String) : Int{
        return Log.e("${TAG}, $ETAG", msg)
    }

    fun e(msg : String, tr: Throwable) : Int{
        return Log.e("${TAG}, $ETAG", msg, tr)
    }

    fun f(tag : String, msg : String) : Int{
        return Log.d("$TAG, $tag",msg)
    }

    fun l():Int{
        return Log.d(TAG, HERE)
    }

    fun el():Int{
        return Log.e(TAG,HERE)
    }

    private fun buildLogMsg(message: String?): String {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append("[")
        sb.append(ste.fileName)
        sb.append(" > ")
        sb.append(ste.methodName)
        sb.append(" > #")
        sb.append(ste.lineNumber)
        sb.append("](")
        sb.append(ste.fileName)
        sb.append(":")
        sb.append(ste.lineNumber)
        sb.append(") ")
        sb.append(message)
        return sb.toString()
    }
}