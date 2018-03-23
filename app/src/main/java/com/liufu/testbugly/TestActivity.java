package com.liufu.testbugly;

import android.app.NotificationManager;
import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tv = (TextView) findViewById(R.id.tv);
        tv.setText(getIntent().getStringExtra("what"));
        ((NotificationManager)getSystemService(Service.NOTIFICATION_SERVICE)).cancel(1);
    }
}
