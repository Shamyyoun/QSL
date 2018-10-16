package utils;

import android.util.Log;

/**
 * Created by Shamyyoun on 11/13/2015.
 */
public class LogUtil {
    private static final String TAG = "QSL_LOG";
    private static final boolean DEBUG_MODE = true;

    /**
     * method, used to show info msg
     */
    public static void i(String msg) {
        if (DEBUG_MODE) {
            Log.i(TAG, msg);
        }
    }

    /**
     * method, used to show error msg
     */
    public static void e(String msg) {
        if (DEBUG_MODE) {
            Log.e(TAG, msg);
        }
    }

    /**
     * method, used to show verbose msg
     */
    public static void v(String msg) {
        if (DEBUG_MODE) {
            Log.v(TAG, msg);
        }
    }

    /**
     * method, used to show debug msg
     */
    public static void d(String msg) {
        if (DEBUG_MODE) {
            Log.d(TAG, msg);
        }
    }

    /**
     * method, used to show warn msg
     */
    public static void w(String msg) {
        if (DEBUG_MODE) {
            Log.w(TAG, msg);
        }
    }
}
