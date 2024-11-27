package com.ijcsj.common_library.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class a {
    public static String WORKING_MODE="working_mode";
    public static String SETTING_TEMPERATURE="setting_temperature";
    public static String FILLING_TIME="filling_time";
    public static String PUMP_ON_TIME="pump_on_time";
    public static String IP_ADDRESS="ip_address";
    public static String COOLING_TEMPERATURE="cooling_temperature";
    public static String DELAY_PUMP_START_TIME="delay_pump_start_time";
    public static String USER_LOG_ON="user_log_on";
    public static String USER_LOG_ON_PASSWORD="user_log_on_password";
    public static String ENGINEERING_LOG_ON="engineering_log_on";
    public static String ENGINEERING_LOG_ON_PASSWORD="engineering_log_on_password";


    public static String MANUFACTOR_LOG_ON="manufactor_log_on";
    public static String MANUFACTOR_LOG_ON_PASSWORD="manufactor_log_on_password";
    public static String PID_P="PID_P";
    public static String PID_I="PID_I";
    public static String PID_D="PID_D";
    public static String TEMP_MODE="TEMP_MODE";
    public static String  TEMPERATURE_DIFFERENCE="TEMPERATURE_DIFFERENCE";
    public static String  HIGH_DEVIATION="high_deviation";
    public static String  LOW_DEVIATION="low_deviation";
    public static String  TEMPERATURE_DEVIATION_TIME="Temperature_deviation_time";
    public static String  HEATING_TIMEOUT="Heating_timeout";
    public static String  COOLING_TIMEOUT="Cooling_timeout";
    public static String  EXHAUST_PRESSURE="exhaust_pressure";
    public static String  RETURN_PRESSURE_DIFFERENCE="Return_pressure_difference";
    public static String  HIGH_PRESSURE_DEVIATION="high_pressure_deviation";
    public static String  LOW_PRESSURE_DEVIATION="low_pressure_deviation";
    public static String  MINIMUM_INLET_PRESSURE="minimum_inlet_pressure";
    public static String  MAXIMUM_RETURN_WATER_PRESSURE="Maximum_return_water_pressure";
    public static String  MINIMUM_PUMP_PRESSURE="Minimum_pump_pressure";
    public static String  COAL_COMPENSATION="coal_compensation";
    public static String  COAL_RETURN_COMPENSATION="coal_Return_Compensation";
    public static String  TIME="TIME";
    public static String  INLETVALVE_1="InletValve_1";
    public static String  INLETVALVE_2="InletValve_2";
    public static String  INLETVALVE_3="InletValve_3";
    public static String  INLETVALVE_4="InletValve_4";
    public static String  INLETVALVE_5="InletValve_5";
    public static String  INLETVALVE_6="InletValve_6";
    public static String  INLETVALVE_7="InletValve_7";
    public static String  INLETVALVE_8="InletValve_8";
    public static String  INLETVALVE_9="InletValve_9";
    public static String  INLETVALVE_11="InletValve_11";
    public static String  INLETVALVE_12="InletValve_12";
    public static String ms2DateOnlyDay(long _ms){
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        return format.format(date);
    }

    public static Integer  arrayBitCut(byte[] data, int offset, int len){
        byte[] result= new byte[len%8==0? len/8:len/8+1];
        if (offset+len>data.length*8) return null;
        byte [] temp=data;
        byte [] tempResult=new byte[(len+offset%8)%8==0? (len+offset%8)/8:(len+offset%8)/8+1];
        System.arraycopy(temp,offset/8,tempResult,0,(len+offset%8)%8==0? (len+offset%8)/8:(len+offset%8)/8+1);
        int j=offset%8;
        tempResult[0]=(byte)(tempResult[0]<<j);
        tempResult[0]=(byte)(tempResult[0]>>j);
        if ((len+offset%8)%8!=0) {
            int k = (len + offset % 8) / 8+1, l = (len + offset % 8) % 8;
            for (int m = k; m > 1; m--)
                tempResult[m - 1] = (byte) ((tempResult[m - 1]&0xff) >> (8 - l) | (tempResult[m - 2]&0xff) << l);
            tempResult[0] = (byte) (tempResult[0] >> (8 - l));
        }
        System.arraycopy(tempResult,tempResult.length-result.length,result,0,result.length);
        for (int start = 0, end = result.length - 1; start < end; start++, end--) {
            byte temps = result[end];
            result[end] = result[start];
            result[start] = temps;
        }

        Vector<String>vector=new Vector<>();
        return  Integer.parseInt(new String(result),2);
    }
    public static Integer getLowConactHighEndForByte(byte b,int start,int end){
        String bitStr = "";
        for(int i=start;i<=end;i++){
            bitStr += String.valueOf((b >> (7-i)) & 0x1);
        }
        return Integer.parseInt(bitStr,2);
    }
    public static String byteToBit(byte b) {
        return "" +(byte)((b >> 7) & 0x1) +
                (byte)((b >> 6) & 0x1) +
                (byte)((b >> 5) & 0x1) +
                (byte)((b >> 4) & 0x1) +
                (byte)((b >> 3) & 0x1) +
                (byte)((b >> 2) & 0x1) +
                (byte)((b >> 1) & 0x1) +
                (byte)((b >> 0) & 0x1);
    }
    public static String byteToBits(int b) {
        StringBuffer stringBuffer=new StringBuffer();
       for (int i=0;i<32;i++){
           stringBuffer.append(  (byte)((b >> i) & 0x1));

       }
        return stringBuffer.toString();
    }


    /**
     * 获取字节在内存中某一位的值,采用字符取值方式
     */
    public static Integer getBitByByte(byte b, int index) {
        if(index >= 8) { return null; }
        Integer val = null;
        String binStr = byteToBit(b);
        val = Integer.parseInt(String.valueOf(binStr.charAt(index)));
        return val;
    }

    /**
     * 获取字节在内存中多位的值,采用字符取值方式(包含endIndex位)
     */
    public static Integer getBitByByte(byte b, int begIndex, int endIndex) {
        if(begIndex >= 8 || endIndex >= 8 || begIndex >= endIndex) { return null; }
        Integer val = null;
        String binStr = byteToBit(b);
        val = Integer.parseInt(binStr.substring(begIndex, endIndex +1), 2);
        return val;
    }
    /**
     * Bit转Byte
     */
    public static byte BitToByte(String byteStr) {
        int re, len;
        if (null == byteStr) {
            return 0;
        }
        len = byteStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (byteStr.charAt(0) == '0') {// 正数
                re = Integer.parseInt(byteStr, 2);
            } else {// 负数
                re = Integer.parseInt(byteStr, 2) - 256;
            }
        } else {//4 bit处理
            re = Integer.parseInt(byteStr, 2);
        }
        return (byte) re;
    }
    //10进制转2进制
    public static byte from10To2(Integer i ,Integer i1){
        String binStr = Integer.toBinaryString(i);
        String binStr1 = Integer.toBinaryString(i1);
        String string="";
        String string1="";
        for (int i2=0;i2<4-binStr.length();i2++){
            string="0"+string;
        }
        binStr=string+binStr;

        for (int i2=0;i2<4-binStr1.length();i2++){
            string1="0"+string1;
        }
        binStr1=string1+binStr1;



      return  BitToByte(binStr+binStr1);
    }
    //10进制转2进制
    public static byte from10To2s(Integer i ,Integer i1){
        String binStr = Integer.toBinaryString(i);
        String binStr1 = Integer.toBinaryString(i1);
        /*String  s=binStr+binStr1;*/
        if (binStr.length()==1){
            binStr="0"+binStr;
        }
        return  BitToByte("00000"+binStr1+binStr);
    }
    public static byte from10To2sd(Integer i1){
        String binStr1 = Integer.toBinaryString(i1);
        StringBuilder s= new StringBuilder();
        for (int i=0;i<(8-binStr1.length());i++){
            s.append("0");
        }
        return  BitToByte(s+binStr1);
    }
    public static byte[] int2bytes(int num){
        byte[] result = new byte[4];
        result[0] = (byte)((num >>> 24) & 0xff);//说明一
        result[1] = (byte)((num >>> 16)& 0xff );
        result[2] = (byte)((num >>> 8) & 0xff );
        result[3] = (byte)((num >>> 0) & 0xff );
        return result;
    }

    //高位在前，低位在后
    public static int bytes2int(byte bytes){
        int a = (bytes & 0xff) << 24;//说明二
        return  a;
    }
    public static String byteToHexString(byte mByte) {
        char[] Digit = { '0', '1', '2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] arr = new char[2];
        arr[0] = Digit[(mByte>>>4)&0X0F];// ? >>>
        arr[1] = Digit[mByte&0X0F];
        String tmp = new String(arr);
        return tmp;
    }

    public static Integer getBitByBytes(byte b, int begIndex, int endIndex) {

        Integer val = null;
        String binStr = byteToBit(b);
        char[]bytes=  binStr.toString().substring(begIndex, endIndex+1 ).toCharArray();

        val = Integer.parseInt(new String(bytes), 2);
        return val;
    }

    public static Integer getBitByBytes(byte[] b, int begIndex, int endIndex) {
        Integer val = null;
        try {
            StringBuffer stringBuffer=new StringBuffer();
            for (int i=0;i<b.length;i++){
                stringBuffer.append(byteToBit(b[i]));
            }
            char[]bytes=  stringBuffer.toString().substring(begIndex, endIndex ).toCharArray();
            for (int start = 0, end = bytes.length - 1; start < end; start++, end--) {
                char temp = bytes[end];
                bytes[end] = bytes[start];
                bytes[start] = temp;
            }
            val = Integer.parseInt(new String(bytes), 2);
        }catch (Exception e){

        }

        return val;
    }
    public static Integer getBitByInt(int b, int begIndex, int endIndex) {
        Integer val = null;
        try {
            StringBuffer stringBuffer=new StringBuffer();
            stringBuffer.append(byteToBits(b));
            char[]bytes=  stringBuffer.toString().substring(begIndex, endIndex ).toCharArray();
            for (int start = 0, end = bytes.length - 1; start < end; start++, end--) {
                char temp = bytes[end];
                bytes[end] = bytes[start];
                bytes[start] = temp;
            }
            val = Integer.parseInt(new String(bytes), 2);
        }catch (Exception e){

        }
        return val;
    }
    public static String getBitByInts(int b, int begIndex, int endIndex) {
       String s = null;
        try {
            StringBuffer stringBuffer=new StringBuffer();
            stringBuffer.append(byteToBits(b));
            char[] bytes=  stringBuffer.toString().substring(begIndex, endIndex ).toCharArray();
            for (int start = 0, end = bytes.length - 1; start < end; start++, end--) {
                char temp = bytes[end];
                bytes[end] = bytes[start];
                bytes[start] = temp;
            }
            s=  new String(bytes);
        }catch (Exception e){

        }


        return s;
    }
    public static int[]bytes(byte byData){
        int[]ints=new int[8];
      int   n0 = (byData & 0x01) == 0x01 ? 1 : 0;
      int  n1 = (byData & 0x02) == 0x02 ? 1 : 0;
      int  n2 = (byData & 0x04) == 0x04 ? 1 : 0;
      int  n3 = (byData & 0x08) == 0x08 ? 1 : 0;
      int  n4 = (byData & 0x10) == 0x10 ? 1 : 0;
      int  n5 = (byData & 0x20) == 0x20 ? 1 : 0;
      int  n6 = (byData & 0x40) == 0x40 ? 1 : 0;
      int  n7 = (byData & 0x80) == 0x80 ? 1 : 0;
      ints[0]=n0;
      ints[1]=n1;
      ints[2]=n2;
      ints[3]=n3;
      ints[4]=n4;
      ints[5]=n5;
      ints[6]=n6;
      ints[7]=n7;
        return ints;
    }
    //3位byte转为int
    public static int Byte3Int(byte[] b)
    {
        return ((b[0] & 0xff) << 16) | ((b[1] & 0xff) << 8) | (b[2] & 0xff);
    }

    //4位byte转为int
    public static int Byte4Int(byte[] b)
    {
        return ((b[0] & 0xff) << 24) | ((b[1] & 0xff) << 16) | ((b[2] & 0xff) << 8) | (b[3] & 0xff);
    }

}
