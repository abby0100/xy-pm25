package com.example.xy.myapplication;

import android.util.Log;

import com.example.xy.utils.Utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MessageManager {

    private static final String LOG_TAG = "MessageManager";

    private static URL url;
    private String userName = "xy_username";
    private String passWord = "xy_password";
    private static String contentType = "Content-Type";
    private static String former = "application/json";
    private String contentLength = "Content-Length";

    public static void login() {

        try {
            url = new URL(Utils.urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
//            String sessionString = "username=" + userName + "&password=" + passWord;
            String sessionString = "/show";
            JSONObject body = null;
            try {
                body = new JSONObject();
                body.put("pathname", sessionString);
            } catch(Exception e) {
                Log.w(LOG_TAG, "loginInternal exception : " + e.toString());
            }

            httpURLConnection.setConnectTimeout(Utils.expireTime);
            httpURLConnection.setRequestMethod(Utils.methodPost);
            httpURLConnection.setRequestProperty(contentType, former);
            httpURLConnection.connect();

            DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            outputStream.writeBytes(body.toString());
            outputStream.flush();
            outputStream.close();

            int responceCode = httpURLConnection.getResponseCode();
            Log.d(LOG_TAG, "reaponse code : " + responceCode);
            switch (responceCode) {
                case 200:
                    // get response message data
                    String successResult = "";
                    String lineString = "";
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    while ((lineString = reader.readLine()) != null) {
                        successResult = successResult + lineString;
                    }
                    Log.d(LOG_TAG, "reaponse success result : " + successResult);
                    reader.close();
                    break;
                default:
                    // get response message data
                    String failedResult = httpURLConnection.getResponseMessage();
                    Log.d(LOG_TAG, "reaponse failed message : " + failedResult);
                    break;
            }
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, "new url failed");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(LOG_TAG, "url open connection failed");
            e.printStackTrace();
        }
    }
}
