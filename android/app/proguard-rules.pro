# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

##可能需要配置混淆，否则，rlease 模式下， activity 等不能用

-allowaccessmodification
-dontoptimize

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

-keepattributes *JavascriptInterface*
-keepclassmembers class com.didapinche.booking.common.activity.WebviewActivity$JsOperation {
   public *;
 }
 -keepclassmembers class com.didapinche.booking.common.activity.WebviewForYuanfenActivity$JsOperation {
   public *;
 }
 -keepclassmembers class com.didapinche.booking.common.activity.WebviewForStartAdActivity$JsOperation {
   public *;
 }

-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault
-dontskipnonpubliclibraryclassmembers

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-ignorewarnings

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# Log
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-dontwarn com.tendcloud.**
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# Serializable
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-dontwarn android.support.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *;}

-dontwarn org.apache.**
-keep class org.apache.commons.** {*;}
-keep interface org.apache.commons.** {*;}
-keep class org.apache.http.**{*;}

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient
-keep public class android.webkit.WebView
-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient
-keep class com.baidu.** {*;}
-keep class vi.com.gdi.bgl.android.** {*;}
-keep class mapsdkvi.com.gdi.bgl.android.** {*;}
-keep class com.umeng.analytics.** {*;}

# ormlite
-keep class com.j256.**
-keepclassmembers class com.j256.** { *; }
-keep enum com.j256.**
-keepclassmembers enum com.j256.** { *; }
-keep interface com.j256.**
-keepclassmembers interface com.j256.** { *; }
-keepclassmembers class * {
    @com.j256.ormlite.field.DatabaseField *;
}
-keepclassmembers class * {
    public <init>(android.content.Context);
}

-keep class com.didapinche.booking.entity.** {*;}
-keep class com.didapinche.booking.common.entity.** {*;}
-keep class com.didapinche.booking.me.entity.** {*;}
-keep class com.didapinche.booking.passenger.entity.** {*;}
-keep class com.didapinche.booking.driver.entity.** {*;}
-keep class com.didapinche.booking.comment.entity.** {*;}
-keep class com.didapinche.booking.setting.entity.** {*;}
-keep class com.didapinche.booking.home.entity.** {*;}
-keep class com.didapinche.booking.map.entity.** {*;}
-keep class com.didapinche.booking.msg.entity.** {*;}
-keep class com.didapinche.booking.friend.entity.** {*;}
-keep class com.didapinche.booking.company.entity.** {*;}
-keep class com.didapinche.booking.setting.entity.**{*;}
-keep class com.didapinche.booking.im.internal.command.**{*;}
-keep class com.didapinche.booking.widget.ViewWrapper{*;}
#反射改变TabLayout下划线宽度，不能混淆此类
-keep class android.support.design.widget.TabLayout{*;}

# alipay
#-keep class com.alipay.android.app.IAlixPay{*;}
#-keep class com.alipay.android.app.IAlixPay$Stub{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
#-keep class com.alipay.sdk.app.PayTask{ public *;}
#-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.sdk.app.H5PayCallback {
    <fields>;
    <methods>;
}
-keep class com.alipay.android.phone.mrpc.core.** { *; }
-keep class com.alipay.apmobilesecuritysdk.** { *; }
-keep class com.alipay.mobile.framework.service.annotation.** { *; }
-keep class com.alipay.mobilesecuritysdk.face.** { *; }
-keep class com.alipay.tscenter.biz.rpc.** { *; }
-keep class org.json.alipay.** { *; }
-keep class com.alipay.tscenter.** { *; }
-keep class com.ta.utdid2.** { *;}
-keep class com.ut.device.** { *;}

# google gson
-keep class org.json { *; }
-keep class com.google.gson.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.** { *;}

#xiaomi push
-keep class com.didapinche.booking.xmpush.** {*;}
-keep class com.didapinche.booking.xmpush.MiPushReceiver {*;}
-dontwarn com.xiaomi.**
-keep class com.xiaomi.** {*;}

-keep class com.tencent.stat{ *; }
-keep class com.tencent.connect{ *; }
-keep class com.tencent.stat.**{ *; }
-keep class com.tencent.connect.**{ *; }

-keep class com.tencent.mm.opensdk.** { *; }
-keep class com.tencent.wxop.** { *; }
-keep class com.tencent.mm.sdk.** { *; }

-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn **.R$*
-keep class m.framework.**{*;}

-keep public class com.didapinche.booking.activity.WebviewActivity$* {
    public <fields>;
    public <methods>;
}

-keep public class com.didapinche.booking.activity.WebviewForYuanfenActivity$* {
    public <fields>;
    public <methods>;
}

-keep public class com.tendcloud.** { public protected *;}

-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{
public *;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{
public *;
}

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**



# For XML inflating, keep views' constructoricon.png
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}



-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature


-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}

-keep class com.huawei.gamebox.plugin.gameservice.**{*;}

-keep public class com.huawei.android.hms.agent.** extends android.app.Activity { public *; protected *; }
-keep interface com.huawei.android.hms.agent.common.INoProguard {*;}
-keep class * extends com.huawei.android.hms.agent.common.INoProguard {*;}


-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepattributes *Annotation*
-keepattributes *JavascriptInterface*


##########################################################################
# release log

-assumenosideeffects class android.util.Log {
	public static boolean isLoggable(java.lang.String, int);
	public static int v(...);
	public static int i(...);
	public static int w(...);
	public static int d(...);
	public static int e(...);
}




-keep class android.support.** {*;}
-dontwarn android.support.**

# end of android support
##########################################################################


##########################################################################
# annotation.

-keep public class android.annotation.** { *; }
-dontwarn android.annotation.**

# end of annotation
##########################################################################


##########################################################################
# Gson
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}
-dontwarn com.google.gson.**

## end of Gson
##########################################################################

##########################################################################
# google-play-service

-dontwarn com.google.**.R
-dontwarn com.google.**.R$*

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}



-keep class **.R$* { *; }



#保证Fragment类名不被混淆
-keepnames class * extends android.support.v4.app.Fragment





## support:appcompat-v7
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}




# 针对 Flutter 里面从插件，加了对 lifecycle 包的混淆
## Android architecture components: Lifecycle
# LifecycleObserver's empty constructor is considered to be unused by proguard
-keepclassmembers class * implements android.arch.lifecycle.LifecycleObserver {
    <init>(...);
}
# ViewModel's empty constructor is considered to be unused by proguard
-keepclassmembers class * extends android.arch.lifecycle.ViewModel {
    <init>(...);
}
# keep Lifecycle State and Event enums values
-keepclassmembers class android.arch.lifecycle.Lifecycle$State { *; }
-keepclassmembers class android.arch.lifecycle.Lifecycle$Event { *; }
# keep methods annotated with @OnLifecycleEvent even if they seem to be unused
# (Mostly for LiveData.LifecycleBoundObserver.onStateChange(), but who knows)
-keepclassmembers class * {
    @android.arch.lifecycle.OnLifecycleEvent *;
}

-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}

-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation { *; }
-keep interface * extends java.lang.annotation.Annotation { *; }

# Bugly混淆规则
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# 避免影响升级功能，需要keep住support包的类
-keep class android.support.**{*;}
