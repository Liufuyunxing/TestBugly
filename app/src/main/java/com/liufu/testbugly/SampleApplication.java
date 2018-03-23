package com.liufu.testbugly;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by liufu on 2017/8/31.
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.liufu.testbugly.MainApplication",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
