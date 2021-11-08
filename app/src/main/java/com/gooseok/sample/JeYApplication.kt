package com.gooseok.sample

import android.app.Application
import android.os.Looper
import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter

class JeYApplication : Application() {
    private var mUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    override fun onCreate() {
        super.onCreate()

        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandlerApplication())

        // Pull Request를 위한 테스트 예제
    }

    /**
     * 메시지로 변환
     *
     * @param th
     * @return
     */
    private fun getStackTrace(th: Throwable): String {

        val result = StringWriter()
        val printWriter = PrintWriter(result)

        var cause: Throwable? = th
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        val stacktraceAsString = result.toString()
        printWriter.close()

        return stacktraceAsString
    }

    internal inner class UncaughtExceptionHandlerApplication : Thread.UncaughtExceptionHandler {

        override fun uncaughtException(thread: Thread, ex: Throwable) {

            //예외상황이 발행 되는 경우 작업
            Log.e("JTest",getStackTrace(ex))
            Log.d("JTest","Error")
            //            new Thread() {
            //                @Override
            //                public void run() {
            //                    // UI쓰레드에서 토스트 뿌림
            //                    Looper.prepare();
            //                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT)
            //                            .show();
            //                    Looper.loop();
            //                }
            //            }.start();
            //
            //// 쓰레드 잠깐 쉼
            //            try {
            //                Thread.sleep(2000);
            //            } catch (InterruptedException e) {
            //            }

            //예외처리를 하지 않고 DefaultUncaughtException으로 넘긴다.
            mUncaughtExceptionHandler!!.uncaughtException(thread, ex)
            if (Thread.currentThread() === Looper.getMainLooper().thread) {
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(10)
            } else {
                //                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent().setAction(Const.Action.SPOT_STOP));
            }

        }

    }
}