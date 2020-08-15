package com.zinc.example.androidlab.util;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Environment;
import android.os.Looper;
import android.os.RemoteException;
import android.os.storage.StorageManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


	public static void addStrToTxt(String txtPath, String content){
//	    Log.i("zzx", "addStrToTxt content: "+content);
        try {
            File file = new File(txtPath);
            if( !file.exists() ){
                file.getParentFile().mkdirs();
            }else {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(new File(txtPath));
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(content);
            bw.close();
            osw.close();
            fos.close();
//            Log.i("zzx", "addStrToTxt file success! path: "+file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
//            Log.i("zzx", "addStrToTxt Exception: "+e.toString());
        }
    }

	public static List<String> readTxt(File file){
		List<String> files = new ArrayList<String>();
		BufferedReader reader = null;
        String temp = null;
        try{  
        	reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()),"UTF-8"));
        	while((temp=reader.readLine())!=null){  
        		files.add(temp);
        	}  
        } catch(Exception e){
            e.printStackTrace();  
        } finally{  
            if(reader != null){  
                try{  
                    reader.close();
                }  
                catch(Exception e){
                    e.printStackTrace();  
                }  
            }  
        }  
        return files;
	}
	
	public static String readTxt(String path){
		BufferedReader reader = null;
		String temp = null;
		StringBuffer sb = new StringBuffer();
		try{  
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
			while((temp=reader.readLine())!=null){  
				sb.append(temp);
			}  
		} catch(Exception e){
			e.printStackTrace();  
		} finally{  
			if(reader != null){  
				try{  
					reader.close();
				}  
				catch(Exception e){
					e.printStackTrace();  
				}  
			}  
		}  
		return sb.toString();
	}

    public static List<String> readTxtByLine(String path){
        BufferedReader reader = null;
        List<String> stringList = new ArrayList<>();
        String temp = null;
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            while((temp=reader.readLine())!=null){
                stringList.add(temp);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(reader != null){
                try{
                    reader.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return stringList;
    }
	
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            File file = new File(newPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            return false;
        }
    }
    
    public static byte[] readFile2Bytes(String path){
        byte yuvData[] = null;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(path);
            int length = fi.available();
            yuvData = new byte[length];
            fi.read(yuvData);
        } catch (Exception e) {
            yuvData = null;
            e.printStackTrace();
        } finally {
            if( fi != null ){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return yuvData;
    }

    /**
     * 获得App应用的大小
     * @param context
     * @param pkgName
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void getPkgSize(final Context context, String pkgName) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // getPackageSizeInfo是PackageManager中的一个private方法，所以需要通过反射的机制来调用
        Method method = PackageManager.class.getMethod("getPackageSizeInfo",
                String.class, IPackageStatsObserver.class);
        // 调用 getPackageSizeInfo 方法，需要两个参数：1、需要检测的应用包名；2、回调
        method.invoke(context.getPackageManager(), pkgName,
                new IPackageStatsObserver.Stub() {
                    @Override
                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                        // 子线程中默认无法处理消息循环，自然也就不能显示Toast，所以需要手动Looper一下
                        Looper.prepare();
                        // 从pStats中提取各个所需数据
                        Toast.makeText(context, "缓存大小=" + Formatter.formatFileSize(context, pStats.cacheSize) +
                                        "\n数据大小=" + Formatter.formatFileSize(context, pStats.dataSize) +
                                        "\n程序大小=" + Formatter.formatFileSize(context, pStats.codeSize),
                                Toast.LENGTH_LONG).show();
                        // 遍历一次消息队列，弹出Toast
                        Looper.loop();
                    }
                });

    }

    public static void getPkgSizeInfo(Context context, String pkgName){
        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        StorageStatsManager storageStatsManager;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            storageStatsManager = (StorageStatsManager)context.getSystemService(Context.STORAGE_STATS_SERVICE);
            StorageStats storageStats = null;
            try {
                storageStats = storageStatsManager.queryStatsForUid(ai.storageUuid, ai.uid);
            } catch (IOException e) {
                e.printStackTrace();
            }


            Log.i("zzx", "getPkgSizeInfo: "+" data:");
            Toast.makeText(context, "缓存大小=" + Formatter.formatFileSize(context, storageStats.getCacheBytes()) +
                    "\n数据大小=" + Formatter.formatFileSize(context, storageStats.getDataBytes()) +
                    "\n程序大小=" + Formatter.formatFileSize(context, storageStats.getAppBytes()), Toast.LENGTH_LONG).show();
        }

    }
}
