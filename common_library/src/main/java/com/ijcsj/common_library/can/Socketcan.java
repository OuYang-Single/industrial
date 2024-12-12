package com.ijcsj.common_library.can;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.ConvertUtils;
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
    public static int CAN_104=0x104;
    public static int CAN_103=0x103;
    public static int CAN_108=0x108;
    public static int CAN_106=0x106;
    public static int CAN_107=0x107;
   private  List<String>list;
    CanFrame canFrame=new CanFrame();
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

  public static int idd=0;
    public void loop(Application app){
        Observable.interval(0, 80, TimeUnit.MILLISECONDS)
                .map((mTimer -> mTimer + 1))
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Long, ObservableSource<CanFrame>>() {
                    @Override
                    public ObservableSource<CanFrame> apply(Long aLong) throws Throwable {
                        try {
                            Log.w("MainFragment","ObservableSource apply -111 "+canFrame.can_id+"  ");
                            Socketcan.CanRead(canFrame,fd);
                    /*        if ( canFrame.can_id==0x101){
                                switch (idd){
                                    case 0:
                                        canFrame.data=new byte[]{0x56,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 1:
                                        canFrame.data=new byte[]{0x52,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 2:
                                        canFrame.data=new byte[]{0x42,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 3:
                                        canFrame.data=new byte[]{0x02,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 4:
                                        canFrame.data=new byte[]{0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 5:
                                        canFrame.data=new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 6:
                                        canFrame.data=new byte[]{0x03,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 7:
                                        canFrame.data=new byte[]{0x0C,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 8:
                                        canFrame.data=new byte[]{0x08,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 9:
                                        canFrame.data=new byte[]{0x04,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                    case 10:
                                        canFrame.data=new byte[]{0x40,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                                        break;
                                }
                            }*/

                            StringBuilder string= new StringBuilder();
                            for (int i=0;i<canFrame.data.length;i++){
                                string.append(" ").append( Integer.toHexString(canFrame.data[i]  & 0x1FFFFFFF));
                            }
                            List<DatasBase> datasBases=   DataBaseDatabase.Companion.getDatabase(app).backFlowBaseDao().getCanId(Integer.toHexString(canFrame.can_id & 0x1FFFFFFF));
                            if (!datasBases.isEmpty()){
                                if (!datasBases.get(datasBases.size()-1).getData().equals(Hexs.INSTANCE.encodeHexStr(canFrame.data))){
                                    addData( canFrame,app);
                                }
                            }else {
                                addData( canFrame,app);
                            }

                            Log.w("MainFragment","ObservableSource  "+canFrame.can_id+"  "+ Integer.toHexString(canFrame.can_id & 0x1FFFFFFF)+" data:  "+  Hexs.INSTANCE.encodeHexStr(canFrame.data)+"  ");
                            for (String canId:list){
                                if (Integer.parseInt(canId)==canFrame.can_id){
                                    LiveDataBus.get().with("CAN_"+canId, CanFrame.class ).postValue(canFrame);
                                }
                            }
                            Log.w("MainFragment","ObservableSource apply -000 "+canFrame.can_id+"  ");
                        }catch (Exception e){
                            Log.w("MainFragment","ObservableSource apply 11"+canFrame.can_id+"  "+e.toString());
                        }
                        return  Observable.just(canFrame);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public static void addData(CanFrame canFrame,Application app){
        DatasBase datasBase=new DatasBase();
        datasBase.setData( Hexs.INSTANCE.encodeHexStr(canFrame.data));
        datasBase.setCanId( Integer.toHexString(canFrame.can_id & 0x1FFFFFFF));
        datasBase.setType(true);
        datasBase.setDateTime(new Date(System.currentTimeMillis()));
        datasBase.setTime(DateUtil.formatTime(new Date(System.currentTimeMillis()),"YYYY-MM-dd HH:mm:ss"));
        DataBaseDatabase.Companion.getDatabase(app).backFlowBaseDao().insert(datasBase);
        LiveDataBus.get().with("CanWrites", Boolean.class ).postValue(true);
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

                        DatasBase datasBase=new DatasBase();
                        datasBase.setData( Hexs.INSTANCE.encodeHexStr(data));
                        datasBase.setCanId( Integer.toHexString(canId & 0x1FFFFFFF));
                        datasBase.setType(false);
                        datasBase.setDateTime(new Date(System.currentTimeMillis()));
                        datasBase.setTime(DateUtil.formatTime(new Date(System.currentTimeMillis()),"YYYY-MM-dd HH:mm:ss"));
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
                .subscribe();

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
