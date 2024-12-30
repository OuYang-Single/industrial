package com.ijcsj.common_library.can;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.blankj.utilcode.util.ConvertUtils;
import com.google.gson.Gson;
import com.ijcsj.common_library.bean.CanFrame;
import com.ijcsj.common_library.bean.DataBaseDatabase;
import com.ijcsj.common_library.bean.DatasBase;
import com.ijcsj.common_library.util.DateUtil;
import com.ijcsj.common_library.util.Hexs;
import com.ijcsj.common_library.util.LiveDataBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Socketcan {
    static {
        System.loadLibrary("android_socketcan");
    }
    public static String CAN_099="CAN_"+153;
    public static String CAN_100="CAN_"+256;
    public static String CAN_102="CAN_"+258;
    public static String CAN_101="CAN_"+257;
    public static String CAN_109="CAN_"+265;
    public static int CAN_105=0x105;
    public static int CAN_104=0x104;
    public static int CAN_103=0x103;
    public static int CAN_108=0x108;
    public static int CAN_106=0x106;
    public static int CAN_107=0x107;
   private  List<String>list;
   private HashMap<String,CanFrame> listdd;
   Gson gson=new Gson();
   Handler handler;

    public void add(String canId){
        list.add(canId);
    }

    static  Application app;
    /**
     * 开启循环任务
     * 0 开始延时时间 1000 每次间隔执行时间
     */
    public void timeLoop(Application app) {
        this.app=app;
        list=new ArrayList<>();
        listdd=new HashMap<>();
        list.add("153");
        list.add("152");
        list.add("85");
        list.add("256");
        list.add("258");
        list.add("257");
        list.add("265");
     /*   down();
        bitrate();
        up();*/
        loop(app);
    }
    final   CanFrame canFrame=new CanFrame();
  public static   Disposable d;
  public static int idd=0;
    Long dd= (long) 0L;
   final byte[] datas=  new byte[]{(byte) (0x93&0xff),(byte) (0x73&0xff),(byte) (0x00&0xff),(byte) (0x6d&0xff),0x00,0x00,0x00,0x00};;
    public void loop(Application app){
        Observable.interval(0, 5, TimeUnit.MILLISECONDS)
                .map((mTimer -> mTimer + 1))
                .subscribeOn(Schedulers.io())
                .map(new Function<Long, CanFrame>() {
                    @Override
                    public CanFrame apply(Long aLong) throws Throwable {
                        CanFrame canFrames = null;
                        final   Long dd= (long) canFrame.dd+1;
                        canFrame.dd=dd;
                        Socketcan.CanRead(canFrame,fd);
                        if (listdd.get(canFrame.can_id+"")!=null){
                            canFrames=listdd.get(canFrame.can_id+"");
                            canFrames.data=canFrame.data;
                            canFrames.can_id=canFrame.can_id;
                            canFrames.can_dlc=canFrame.can_dlc;
                            canFrames.dd=dd;
                        }else {
                             canFrames=gson.fromJson(gson.toJson(canFrame),CanFrame.class);
                             canFrames.dd=dd;
                             listdd.put(canFrame.can_id+"",canFrames);
                        }
                        return canFrames;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CanFrame>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Socketcan.this.d=d;
                    }

                    @Override
                    public void onNext(@NonNull CanFrame canFrames) {
                        Log.w("MainFragment","ObservableSource apply  "+canFrames.can_id+"  "+canFrames.can_id);
                        LiveDataBus.get().with("CAN_"+canFrames.can_id, CanFrame.class ).postValue(canFrames);
                        LiveDataBus.get().with("CAN_LiveDataBus", CanFrame.class ).postValue(canFrames);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w("MainFragment","ObservableSource apply 11"+"  "+e.toString());
                        loop( app);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



  public static void down(){

      try {
          // 获取运行时环境
          Process process = Runtime.getRuntime().exec("adb shell ifconfig can0 down"); // 执行 adb shell ls 命令
          BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

          StringBuilder output = new StringBuilder();
          String line;

          // 逐行读取命令输出
          while ((line = reader.readLine()) != null) {
              output.append(line).append("\n"); // 将输出按行串联
          }

          process.waitFor(); // 等待命令执行完成
          System.out.println("Command Output: \n" + output.toString()); // 打印命令输出
      } catch (IOException e) {
          e.printStackTrace(); // 捕获异常并打印
      } catch (InterruptedException e) {
          e.printStackTrace(); // 捕获异常并打印
      }
  }

    public static void up(){
        try {
            // 获取运行时环境
            Process process = Runtime.getRuntime().exec("adb shell ifconfig can0 up"); // 执行 adb shell ls 命令
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;

            // 逐行读取命令输出
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n"); // 将输出按行串联
            }

            process.waitFor(); // 等待命令执行完成
            System.out.println("Command Output: \n" + output.toString()); // 打印命令输出
        } catch (IOException e) {
            e.printStackTrace(); // 捕获异常并打印
        } catch (InterruptedException e) {
            e.printStackTrace(); // 捕获异常并打印
        }
    }
    public static void bitrate(){

        try {
            // 获取运行时环境
            Process process = Runtime.getRuntime().exec("adb shell canconfig can0 bitrate 500000 ctrlmode triple-sampling on"); // 执行 adb shell ls 命令
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;

            // 逐行读取命令输出
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n"); // 将输出按行串联
            }

            process.waitFor(); // 等待命令执行完成
            System.out.println("Command Output: \n" + output.toString()); // 打印命令输出
        } catch (IOException e) {
            e.printStackTrace(); // 捕获异常并打印
        } catch (InterruptedException e) {
            e.printStackTrace(); // 捕获异常并打印
        }
    }
    public static int fd;

    public static int CanWrites (int fd,int canId, byte[] data){
       int i=  CanWrite( fd, canId,  data);
        Observable.just("")
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String string) throws Throwable {
                        final Date date=  new Date(System.currentTimeMillis());
                        final DatasBase datasBase=new DatasBase();
                        datasBase.setData( Hexs.INSTANCE.encodeHexStr(data));
                        datasBase.setCanId( Integer.toHexString(canId & 0x1FFFFFFF));
                        datasBase.setType(false);
                        datasBase.setDateTime(date);
                        datasBase.setTime(DateUtil.formatTime(date, "YYYY-MM-dd HH:mm:ss"));
                        if (i>0){
                            DataBaseDatabase.Companion.getDatabase(app).backFlowBaseDao().insert(datasBase);
                        }else{
                            DataBaseDatabase.Companion.getDatabase(app).backFlowBaseDao().insert(datasBase);
                        }
                        LiveDataBus.get().with("CanWrites", Boolean.class ).postValue(true);
                        return Observable.just("");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return i;
    }
    public native static  int OpenCan(String canx);
    public native static  int CanWrite(int fd,int canId, byte[] data);
    public native static CanFrame CanRead(CanFrame mcanFrame, int time);
    public native static  int CloseCan(int fd);

    public List<String> getList() {
        return list;
    }
}
