package com.hc.flutter_hotfix_notkt.flutter;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import io.flutter.app.FlutterActivityEvents;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;

public class HotFixFlutterActivity extends Activity implements FlutterView.Provider, PluginRegistry, HotFixFlutterActivityDelegate.ViewFactory {
    private static final String TAG = "FlutterActivity";
    private final HotFixFlutterActivityDelegate delegate = new HotFixFlutterActivityDelegate(this, this);
    private final FlutterActivityEvents eventDelegate;
    private final FlutterView.Provider viewProvider;
    private final PluginRegistry pluginRegistry;

    public HotFixFlutterActivity() {
        this.eventDelegate = this.delegate;
        this.viewProvider = this.delegate;
        this.pluginRegistry = this.delegate;
    }

    @Override
    public FlutterView getFlutterView() {
        return this.viewProvider.getFlutterView();
    }

    @Override
    public FlutterView createFlutterView(Context context) {
        return null;
    }

    @Override
    public FlutterNativeView createFlutterNativeView() {
        return null;
    }

    @Override
    public boolean retainFlutterNativeView() {
        return false;
    }

    @Override
    public final boolean hasPlugin(String key) {
        return this.pluginRegistry.hasPlugin(key);
    }

    @Override
    public final <T> T valuePublishedByPlugin(String pluginKey) {
        return this.pluginRegistry.valuePublishedByPlugin(pluginKey);
    }

    @Override
    public final Registrar registrarFor(String pluginKey) {
        return this.pluginRegistry.registrarFor(pluginKey);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HotFix", "onCreate: super onCreate");
        this.eventDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.eventDelegate.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.eventDelegate.onResume();
    }

    @Override
    protected void onDestroy() {
        this.eventDelegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!this.eventDelegate.onBackPressed()) {
            super.onBackPressed();
        }

    }

    @Override
    protected void onStop() {
        this.eventDelegate.onStop();
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.eventDelegate.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.eventDelegate.onPostResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        this.eventDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!this.eventDelegate.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        this.eventDelegate.onNewIntent(intent);
    }

    @Override
    public void onUserLeaveHint() {
        this.eventDelegate.onUserLeaveHint();
    }

    @Override
    public void onTrimMemory(int level) {
        this.eventDelegate.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        this.eventDelegate.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.eventDelegate.onConfigurationChanged(newConfig);
    }
}