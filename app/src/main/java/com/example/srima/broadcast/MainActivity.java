package com.example.srima.broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Provider;

public class MainActivity extends AppCompatActivity {
Context context;
    private TextView mTextViewInfo;
    private TextView mTextViewPercentage;
    private ProgressBar mProgressBar;

    private int mProgressStatus = 0;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        // Get the battery scale
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        // Display the battery scale in TextView
        mTextViewInfo.setText("Battery Scale : " + scale);
        // get the battery level
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        // Display the battery level in TextView
        mTextViewInfo.setText(mTextViewInfo.getText() + "\nBattery Level : " + level);

        // Calculate the battery charged percentage
        float percentage = level/ (float) scale;
        // Update the progress bar to display current battery charged percentage
        mProgressStatus = (int)((percentage)*100);

        // Show the battery charged percentage text inside progress bar
        mTextViewPercentage.setText("" + mProgressStatus + "%");

        // Show the battery charged percentage in TextView
        mTextViewInfo.setText(mTextViewInfo.getText() +
                "\nPercentage : "+ mProgressStatus + "%");

        // Display the battery charged percentage in progress bar
      //  mProgressBar.setProgress(mProgressStatus);
    }
};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        // Initialize a new IntentFilter instance
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        // Register the broadcast receiver
        context.registerReceiver(mBroadcastReceiver,iFilter);

        // Get the widgets reference from XML layout
        mTextViewInfo = (TextView) findViewById(R.id.tv_info);
        mTextViewPercentage = (TextView) findViewById(R.id.tv_percentage);
       // mProgressBar = (ProgressBar) findViewById(R.id.pb);
        }
    }


