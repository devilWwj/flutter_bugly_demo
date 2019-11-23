package com.hc.flutter_hotfix_notkt;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.hc.flutter_hotfix_notkt.flutter.MyFlutterMain;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.lib.library.TinkerLoadLibrary;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import java.io.File;
import java.util.Locale;

import io.flutter.view.FlutterMain;



public class SampleApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "hcc";
    private Application mContext;

    @SuppressLint("LongLogTag")
    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);

        Log.i(TAG, "SampleApplicationLike: 测试是否执行");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplication();

        Log.i(TAG, "onCreate: 是否执行 onCreate");
        load_library_hack();
        if(BuildConfig.DEBUG){
            FlutterMain.startInitialization(mContext);
        }else {
            MyFlutterMain.startInitialization(mContext);
        }

        configTinker();
      //

    }


    // 使用Hack的方式（测试成功）,flutter 加载，也是通过这种方式成功的。
    public void load_library_hack( ) {
        Log.i(TAG, "load_library_hack: ");
        String CPU_ABI = Build.CPU_ABI;
        // 将tinker library中的 CPU_ABI架构的so 注册到系统的library path中。
        try {
            ///
            Toast.makeText(mContext,"开始加载 so,abi:" + CPU_ABI,Toast.LENGTH_SHORT).show();
            //  TinkerLoadLibrary.installNavitveLibraryABI(this, CPU_ABI);
            //这个路径写死试一下,也就是，获取的 CPU_ABI，不准。
            TinkerLoadLibrary.installNavitveLibraryABI(mContext, "armeabi-v7a");

            //    TinkerLoadLibrary.loadLibraryFromTinker(MainActivity.this, "lib/armeabi", "app");

            Toast.makeText(mContext,"加载 so 完成",Toast.LENGTH_SHORT).show();
            ///data/data/${package_name}/tinker/lib
            Tinker tinker = Tinker.with(mContext);
            TinkerLoadResult loadResult = tinker.getTinkerLoadResultIfPresent();

            if (loadResult.libs == null) {
                Log.i("hcc", "load_library_hack: " + "没有获取到 Libs 的路径。。。");
                return;
            }
            File soDir = new File(loadResult.libraryDirectory, "lib/" + "armeabi-v7a/libapp.so");

            if (soDir.exists()){
                Log.i("hcc", "load_library_hack: ,so 库文件的路径是:" + soDir.getAbsolutePath());

                if(!BuildConfig.DEBUG){
                    Log.i(TAG, "load_library_hack: 开始设置 tinker 路径");
               //     MyFlutterMain.setFixAppLibPath(soDir.getAbsolutePath());
                }
            }else {
                Log.i("hcc", "load_library_hack: so 库文件路径不存在。。。 ");
            }
        }catch (Exception e){
            Toast.makeText(mContext,e.toString(),Toast.LENGTH_SHORT).show();
        }

    }


    private void configTinker() {
        Log.i(TAG, "configTinker: ");
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(mContext, "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(mContext,
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Toast.makeText(mContext, "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(mContext, "补丁下载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(mContext, "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(mContext, "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };



        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(mContext, false);
        Bugly.init(mContext, "你自己的appid", true);
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(getApplication());
        // Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId

    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }


}

