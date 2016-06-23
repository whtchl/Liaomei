package com.example.tchl.liaomei.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.squareup.picasso.Picasso;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/**
 * Created by tchl on 2016-06-22.
 */
/*public class RxLiaomei {

    public static Observable<Uri> saveImageAndGetPathObservable(Context context, String url, String title) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.with(context).load(url).get();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
                if (bitmap == null) {
                    subscriber.onError(new Exception("无法下载到图片"));
                }
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).flatMap(bitmap -> {
            File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = title.replace('/', '-') + ".jpg";
            File file = new File(appDir, fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                assert bitmap != null;
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Uri uri = Uri.fromFile(file);
            // 通知图库更新
            Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            context.sendBroadcast(scannerIntent);
            return Observable.just(uri);
        }).subscribeOn(Schedulers.io());
    }
}*/


public class RxLiaomei {
    final static String TAG = "RxLiaomei tchl";
     public static rx.Observable<Uri> saveImageAndGetPathObservable(Context context, String url, String title){
         return rx.Observable.create(new rx.Observable.OnSubscribe<Bitmap>(){
             @Override
             public void call(Subscriber<? super Bitmap> subscriber) {
                 Bitmap bitmap = null;
                 try{
                     bitmap = Picasso.with(context).load(url).get();
                     Log.e(TAG,"Picasso get bitmap");
                 }catch (IOException e){
                     subscriber.onError(e);
                 }
                 if(bitmap == null){
                     subscriber.onError(new Exception("无法下载到图片"));
                 }
                 subscriber.onNext(bitmap);
                 subscriber.onCompleted();
             }
         }).flatMap(new Func1<Bitmap, rx.Observable<Uri>>() {
             @Override
             public rx.Observable<Uri> call(Bitmap bitmap) {
                 File appDir = new File(Environment.getExternalStorageDirectory(),"Liaomei");
                 if(!appDir.exists()){
                     appDir.mkdir();
                 }

                 String fileName = title.replace('/', '-') + ".jpg";
                 Log.e("RxLiaomei","title:"+title+"  fileName:"+fileName);
                 File file = new File(appDir,fileName);
                 try{
                     FileOutputStream outputStream = new FileOutputStream(file);
                     assert  bitmap !=null;
                     bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                     outputStream.flush();
                     outputStream.close();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
                 Uri uri = Uri.fromFile(file);
                 Log.e(TAG,"uri:"+uri.toString());
                 Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri);
                 context.sendBroadcast(scannerIntent);
                 return Observable.just(uri);
             }
         }).subscribeOn(Schedulers.io());
     }

}
/*public class RxLiaomei {
    final static String TAG = "RxLiaomei tchl";
     public static rx.Observable<Uri> saveImageAndGetPathObservable(Context context, String url, String title){
         return rx.Observable.create(new rx.Observable.OnSubscribe<Bitmap>(){
             @Override
             public void call(Subscriber<? super Bitmap> subscriber) {
                 Bitmap bitmap = null;
                 try{
                     bitmap = Picasso.with(context).load(url).get();
                     Log.e(TAG,"Picasso get bitmap");
                 }catch (IOException e){
                     subscriber.onError(e);
                 }
                 if(bitmap == null){
                     subscriber.onError(new Exception("无法下载到图片"));
                 }
                 subscriber.onNext(bitmap);
                 subscriber.onCompleted();
             }
         }).flatMap(bitmap -> {
             File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
             if (!appDir.exists()) {
                 appDir.mkdir();
             }
             String fileName = title.replace('/', '-') + ".jpg";
             File file = new File(appDir, fileName);
             try {
                 FileOutputStream outputStream = new FileOutputStream(file);
                 assert bitmap != null;
                 bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                 outputStream.flush();
                 outputStream.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }

             Uri uri = Uri.fromFile(file);
             // 通知图库更新
             Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
             context.sendBroadcast(scannerIntent);
             return Observable.just(uri);

         }).subscribeOn(Schedulers.io());
     }

}*/
