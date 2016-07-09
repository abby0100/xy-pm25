package com.example.xy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xy.utils.Utils;


public class PrefaceActivity extends Activity {

    private final String LOG_TAG = "PrefaceActivity";
    private TextView version;
    private Button startButton;

    private int REQUEST_CODE = 0x10;
    private class MSG {
        private final static int START_MAIN_ACTIVITY = 0x01;
    }
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.start_button:
                    mHandler.sendEmptyMessage(MSG.START_MAIN_ACTIVITY);
                    break;
                default:
                    break;
            }
        }
    };
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG.START_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setAction("com.example.xy.start");
                    startActivityForResult(intent, REQUEST_CODE);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preface);

        getApplicationVersion();
    }

    private void getApplicationVersion() {
        String versionName = Utils.getApplicationVersion(this);

        version = (TextView) findViewById(R.id.version);
        version.setText(getString(R.string.app_version,versionName));
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(mClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "requestCode: " + requestCode);

        if (requestCode == REQUEST_CODE) {
            Log.d(LOG_TAG, "resultCode: " + resultCode);

            if (resultCode == RESULT_OK) {

                if (data != null) {
                    String finishedTime = data.getStringExtra(MainActivity.TIME);
                    Log.d(LOG_TAG, "finishedTime: " + finishedTime);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
