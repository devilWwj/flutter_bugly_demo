package com.hc.flutter_hotfix_notkt;

import android.os.Bundle;
import android.widget.Toast;

import com.hc.flutter_hotfix_notkt.flutter.HotFixFlutterActivity;
import com.tencent.bugly.crashreport.CrashReport;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
public class MainActivity extends HotFixFlutterActivity {
  private static final String CHANNEL = "com.hc.flutter";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    GeneratedPluginRegistrant.registerWith(this);
    new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(
            new MethodChannel.MethodCallHandler() {
              @Override
              public void onMethodCall(MethodCall call, MethodChannel.Result result) {

                if(call.method.equals( "report")){
                    String msg = call.arguments.toString();
                  Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
                  CrashReport.postCatchedException(new Throwable(msg));
                }


                if(call.method.equals("nativePage")){

                  Toast.makeText(MainActivity.this,"this is bug! ",Toast.LENGTH_SHORT).show();

                }

              }
            });

  }
}
