package com.liufu.testbugly;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.tinker.TinkerLoadReporter;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.tinker.TinkerPatchListener;
import com.tencent.bugly.beta.tinker.TinkerPatchReporter;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * Created by mac on 2017/8/31.
 */

public class MainApplication extends DefaultApplicationLike {
    public static final String TAG = "Tinker.MainApplication";

    public MainApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(getApplication(), "d4f3e5db78", true);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

//        // 安装tinker
//        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
//        Beta.installTinker(this);
        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        LoadReporter loadReporter = new TinkerLoadReporter(getApplication());
        // or you can just use DefaultPatchReporter
        PatchReporter patchReporter = new TinkerPatchReporter(getApplication());
        // or you can just use DefaultPatchListener
        PatchListener patchListener = new TinkerPatchListener(getApplication());
        // you can set your own upgrade patch if you need
        AbstractPatch upgradePatchProcessor = new UpgradePatch();
        // you can set your own repair patch if you need
        AbstractPatch repairPatchProcessor = new UpgradePatch();
        TinkerManager.TinkerPatchResultListener patchResultListener = new TinkerManager.TinkerPatchResultListener() {
            @Override
            public void onPatchResult(PatchResult result) {
                // you can get the patch result
                if (result.isSuccess) {
                    Toast.makeText(getApplication(), "为了更好的体验app，请重新启动App", Toast.LENGTH_LONG).show();
//                            System.exit(0);

                } else {

                }
            }
        };

        Beta.installTinker(this, loadReporter, patchReporter, patchListener, patchResultListener, upgradePatchProcessor);
//        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
}
