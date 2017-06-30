package com.noonight.pc.retrofittutor.DebugHelper;

import android.util.Log;

/**
 * Created by ayur on 30.06.2017.
 */

public class DebugHelper {
    public static void LogD(Class mClass, String text) {
        Log.d("Debug log: " + mClass.getSimpleName(), text);
    }
}
