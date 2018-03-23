package com.liufu.testbugly;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.qzs.voiceannouncementlibrary.VoiceUtils;

import tyrantgit.explosionfield.ExplosionField;

public class MainActivity extends AppCompatActivity {

    private Button btn_test;
    private ImageView img;

    private Bundle bind, unbind, add, minus;

    NotificationManager mNotificationManager;
    Notification.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btn_test = (Button) findViewById(R.id.btn_test);
//        img = (ImageView) findViewById(R.id.img);
//        final ExplosionField explosionField = ExplosionField.attach2Window(this);
//        btn_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(MainActivity.this, "这是热更新之后的版本", Toast.LENGTH_LONG).show();
////                String url = "yaooapatriarch://";
////                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
////                startActivity(intent);
//                explosionField.explode(btn_test);
//            }
//        });
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                explosionField.explode(img);
//            }
//        });

        // 创建一个NotificationManager的引用
        mNotificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        // 定义Notification的各种属性
        mBuilder = new Notification.Builder(this)
                .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("有新消息呢")
                .setContentTitle("这是个标题")
                .setContentText("这是个内容")
                .setDefaults(Notification.DEFAULT_ALL)                                      //设置默认的提示音
                .setPriority(Notification.PRIORITY_DEFAULT)                                 //设置该通知的优先级
                .setOngoing(false)                                                          //让通知左右滑的时候不能取消通知
                .setPriority(Notification.PRIORITY_DEFAULT)                                 //设置该通知的优先级
                .setWhen(System.currentTimeMillis())                                        //设置通知时间，默认为系统发出通知的时间，通常不用设置
                .setAutoCancel(true);

        //处理点击Notification的逻辑
        Intent resultIntent = new Intent(this, TestActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what","5");
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,5,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //VoiceUtils.with(MainActivity.this).Play("1234.45",true);
                //发送
                mNotificationManager.notify(1, mBuilder.build());
            }
        });

        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoiceUtils.with(MainActivity.this).Play("234.78",false);
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoiceUtils.with(MainActivity.this).Play("4.45",true);
            }
        });

        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoiceUtils.with(MainActivity.this).Play("0.45",false);
            }
        });
    }
}
