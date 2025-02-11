/**
 * Copyright 2014 Looped Labs pvt. Ltd. http://loopedlabs.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loopedlabs.util.debug;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

/**
 * This class is for Development purpose , to see the Debug Messages
 *
 * @author Looped Labs Pvt. Ltd.
 * @version 0.9.4
 * @since 2015-03-15
 */

public class DebugLog {
    private static boolean debugMode = false;

    public static void logTrace() {
        if (debugMode) {
            final StackTraceElement[] ste = Thread.currentThread()
                    .getStackTrace();
            Log.d(ste[3].getClassName(), ste[3].getMethodName());
        }
    }

    public static void logTrace(String s) {
        if (debugMode) {
            final StackTraceElement[] ste = Thread.currentThread()
                    .getStackTrace();
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + s);
        }
    }

    public static void logTrace(int s) {
        logTrace(String.valueOf(s));
    }

    public static void logTrace(double s) {
        logTrace(String.valueOf(s));
    }

    public static void logTrace(boolean s) {
        logTrace(String.valueOf(s));
    }

    public static void logTrace(Bundle b) {
        if (debugMode) {
            final StackTraceElement[] ste = Thread.currentThread()
                    .getStackTrace();
            JSONObject json = new JSONObject();
            Set<String> keys = b.keySet();
            for (String key : keys) {
                try {
                    json.put(key, JSONObject.wrap(b.get(key)));
                } catch(JSONException e) {
                    logException(e);
                }
            }
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + json.toString());
        }
    }

    public static void logTrace(Uri uri) {
        if (debugMode) {
            final StackTraceElement[] ste = Thread.currentThread()
                    .getStackTrace();
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + "URI String : " + uri.toString());
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + "URI Scheme : " + uri.getScheme());
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + "URI Authority : " + uri.getAuthority());
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + "URI Host : " + uri.getHost());
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + "URI Path : " + uri.getPath());
            Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + "URI Query : " + uri.getQuery());
        }
    }
    public static void logError(String s) {
        final StackTraceElement[] ste = Thread.currentThread()
                .getStackTrace();
        Log.d(ste[3].getClassName(), ste[3].getMethodName() + " --- " + s);
    }

    public static void logException(Exception e) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        Log.d(ste[3].getClassName(),
                ste[3].getMethodName() + " --- " + e.toString());
        Log.e(ste[3].getMethodName() + " --- ", e.toString(), e);
    }

    public static void logException(String msg, Exception e) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        Log.d(ste[3].getClassName(),
                ste[3].getMethodName() + " --- " + e.toString());
        Log.e(ste[3].getMethodName() + " --- ", msg, e);
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 3];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 3] = hexArray[v >>> 4];
            hexChars[j * 3 + 1] = hexArray[v & 0x0F];
            hexChars[j * 3 + 2] = ' ';
        }
        return new String(hexChars);
    }

    public static String bytesToHex(byte b) {
        char[] hexChars = new char[3];
        int v = b & 0xFF;
        hexChars[0] = hexArray[v >>> 4];
        hexChars[1] = hexArray[v & 0x0F];
        hexChars[2] = ' ';
        return new String(hexChars);
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        DebugLog.debugMode = debugMode;
    }
}
