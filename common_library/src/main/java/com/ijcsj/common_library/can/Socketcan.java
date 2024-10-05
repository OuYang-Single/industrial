package com.ijcsj.common_library.can;

import android.util.Log;

import com.ijcsj.common_library.bean.CanFrame;
import com.ijcsj.common_library.util.LiveDataBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
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
    public static int CAN_103=0x103;
    public static int CAN_108=0x108;
    public static int CAN_106=0x106;
   private  List<String>list;
    CanFrame canFrame=new CanFrame();
    public void add(String canId){
        list.add(canId);
    }

    /**
     * 开启循环任务
     * 0 开始延时时间 1000 每次间隔执行时间
     */
    public void timeLoop() {
        list=new ArrayList<>();
        list.add("153");
        list.add("152");
        list.add("85");
        list.add("256");
        list.add("258");
        list.add("257");
        list.add("265");

       Observable.interval(0, 400, TimeUnit.MILLISECONDS)
                .map((mTimer -> mTimer + 1))
               .subscribeOn(Schedulers.io())
               .flatMap(new Function<Long, ObservableSource<CanFrame>>() {
                   @Override
                   public ObservableSource<CanFrame> apply(Long aLong) throws Throwable {
                       Socketcan.CanRead(canFrame,fd);
                       StringBuilder string= new StringBuilder();
                       for (int i=0;i<canFrame.data.length;i++){
                           string.append(" ").append(canFrame.data[i]);
                       }
                       Log.w("MainFragment","ObservableSource  "+canFrame.can_id+"  "+ Integer.toHexString(canFrame.can_id & 0x1FFFFFFF)+" "+string.toString());
                       for (String canId:list){
                           if (Integer.parseInt(canId)==canFrame.can_id){
                               LiveDataBus.get().with("CAN_"+canId, CanFrame.class ).postValue(canFrame);
                           }
                       }
                       return  Observable.just(canFrame);
                   }
               }).observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }





    public static int fd;

    public native static  int OpenCan(String canx);
    public native static  int CanWrite(int fd,int canId, byte[] data);
    public native static CanFrame CanRead(CanFrame mcanFrame, int time);
    public native static  int CloseCan(int fd);

    public List<String> getList() {
        return list;
    }
}
