package com.example.xy.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xy on 2016/7/9.
 */
public class Utils {

    public static final String urlString = "http://10.0.2.15";
    public static final int expireTime = 3000; // milliseconds
    public static final String methodGet = "GET";
    public static final String methodPost = "POST";

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String getApplicationVersion(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
//            String packageName = "com.example.xy.myapplication";
//            PackageInfo info = pm.getPackageInfo(packageName, 0);
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
