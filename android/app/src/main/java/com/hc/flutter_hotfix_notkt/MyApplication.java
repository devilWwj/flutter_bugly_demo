package com.hc.flutter_hotfix_notkt;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;


/*
public class MyApplication extends FlutterApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext());
        appContext = MyApplication.this;

        FlutterPatch.flutterPatchInit();

    }

    private static Context appContext;

    public static Context getAppContext(){
        return  appContext;
    }

}
*/

public class  MyApplication    extends TinkerApplication {
    public MyApplication() {

        super(ShareConstants.TINKER_ENABLE_ALL, "com.hc.flutter_hotfix_notkt.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }


}

