package com.example.mylibrary.commom;

import android.os.Looper;
import android.util.Printer;

/**
 * 类描述：配合LogMonitor检测程序运行耗时情况
 * 创建人 Chris
 * 创建时间：2017/4/14 14:12
 */

public class BlockDetectByPrinter {
    public static void start() {

        Looper.getMainLooper().setMessageLogging(new Printer() {

            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";

            @Override
            public void println(String x) {
                if (x.startsWith(START)) {
                    LogMonitor.getInstance().startMonitor();
                }
                if (x.startsWith(END)) {
                    LogMonitor.getInstance().removeMonitor();
                }
            }
        });

    }
}
