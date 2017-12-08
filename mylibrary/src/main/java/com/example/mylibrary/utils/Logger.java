package com.example.mylibrary.utils;

import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
    /**
     * log tag
     */
    private static final String tagConst = "sona.";
    private String tagName = "SONA";
    private static int logLevel = Log.VERBOSE;

    private static Logger inst;
    private Lock lock;

    private boolean NOLOG = false;

    private Logger() {
        lock = new ReentrantLock();
    }

    public static synchronized Logger getLogger() {
        if (inst == null) {
            inst = new Logger();
        }
        return inst;
    }

    public static synchronized Logger getLogger(boolean log) {
        Logger logger = getLogger();
        logger.NOLOG = !log;
        return logger;
    }

    public void setLogEnable(boolean log) {
        this.NOLOG = !log;
    }

    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            tagName = tagConst + st.getFileName();
            return "line:" + st.getLineNumber() + "_" + st.getMethodName() + "_";
        }
        return null;
    }

    private String createMessage(String msg) {
        String functionName = getFunctionName();
        return (functionName == null ? msg : (functionName + "_" + msg));
    }

    public void method() {
        if (NOLOG) {
            return;
        }
        i("********************************************************");
        i("**********" + getFunctionName());
        i("********************************************************");
    }

    public void i(String msg) {
        if (NOLOG) {
            return;
        }
        if (logLevel <= Log.INFO) {
            lock.lock();
            try {
                String message = createMessage(msg);
                Log.i(tagName, message);
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * log.v
     */
    public void v(String format) {
        if (NOLOG) {
            return;
        }
        if (logLevel <= Log.VERBOSE) {
            lock.lock();
            try {
                String message = createMessage(format);
                Log.v(tagName, message);
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * log.d
     */
    public void d(String format) {
        if (NOLOG) {
            return;
        }
        if (logLevel <= Log.DEBUG) {
            lock.lock();
            try {
                String message = createMessage(format);
                //case android studio log length ~~ 4000 max
                int length = message.length();
                int lengthLogcat = 3000;
                for (int i = 0; i < Integer.MAX_VALUE; i = i + lengthLogcat) {
                    if (length > i + lengthLogcat) {
                        Log.d(i == 0 ? tagName : ">>", message.substring(i, i + lengthLogcat));

                    } else if (length > i) {
                        Log.d(i == 0 ? tagName : ">>", message.substring(i));

                    } else {
                        break;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * log.e
     */
    public void e(String format) {
        if (NOLOG) {
            return;
        }
        if (logLevel <= Log.ERROR) {
            lock.lock();
            try {
                String message = createMessage(format);
                Log.e(tagName, message);
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * log.d
     */
    public void w(String format) {
        if (NOLOG) {
            return;
        }
        if (logLevel <= Log.WARN) {
            lock.lock();
            try {
                String message = createMessage(format);
                Log.w(tagName, message);
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * set log level
     */

    public void setLevel(int l) {
        lock.lock();
        try {
            logLevel = l;
        } finally {
            lock.unlock();
        }
    }

}