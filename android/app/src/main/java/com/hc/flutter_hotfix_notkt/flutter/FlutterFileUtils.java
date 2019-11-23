package com.hc.flutter_hotfix_notkt.flutter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import dalvik.system.PathClassLoader;

public class FlutterFileUtils {
    public static boolean copyLibraryFile(Context context, String origPath, String destPath) {
        boolean copyIsFinish = false;
        try {
            File dirFile = new File(destPath.substring(0, destPath.lastIndexOf("/")));
            if (dirFile.exists() != true) {
                dirFile.mkdirs();
            }
            FileInputStream is = new FileInputStream(new File(origPath));
            File file = new File(destPath);
            if (file.exists()) {
                if (file.length() == is.available()) {
                    return true;
                }
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return copyIsFinish;
    }



    ///将文件拷贝到私有目录
    public static String copyAssetsAndWrite(Context context, String fileName){
        try {

            File dir = context.getDir("libs", Activity.MODE_PRIVATE);
            File destFile = new File(dir.getAbsolutePath() + File.separator );

            if (destFile.exists() != true) {
                destFile.mkdirs();
            }

            File outFile = new File(destFile,fileName);

            if(outFile.exists()){
                outFile.delete();
            }


            if (!outFile.exists()){
                boolean res = outFile.createNewFile();
                if (res){
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Toast.makeText(context,"downlaod success!", Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }else {
                    Toast.makeText(context,"文件已经存在", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return "";
    }


    private static void createNewNativeDir(Context context, String libPath) throws Exception {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Field declaredField = Class.forName("dalvik.system.BaseDexClassLoader").getDeclaredField("pathList");
        declaredField.setAccessible(true);
        Object pathList = declaredField.get(pathClassLoader);
        // 获取当前类的属性
        Object nativeLibraryDirectories = pathList.getClass().getDeclaredField(
                "nativeLibraryDirectories");
        ((Field) nativeLibraryDirectories).setAccessible(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 获取 DEXPATHList中的属性值
            File[] files = (File[]) ((Field) nativeLibraryDirectories).get(pathList);
            Object filesss = Array.newInstance(File.class, files.length + 1);
            // 添加自定义.so路径
            Array.set(filesss, 0,libPath);
            // 将系统自己的追加上
            for (int i = 1; i < files.length + 1; i++) {
                Array.set(filesss, i, files[i - 1]);
            }
            ((Field) nativeLibraryDirectories).set(pathList, filesss);
        } else {
            ArrayList<File> files = (ArrayList<File>) ((Field) nativeLibraryDirectories).get(pathList);
            ArrayList<File> filesss = (ArrayList<File>) files.clone();
            filesss.add(0,new File(libPath));
            ((Field) nativeLibraryDirectories).set(pathList, filesss);
        }
    }




}
