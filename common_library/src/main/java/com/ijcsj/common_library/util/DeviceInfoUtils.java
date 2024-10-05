package com.ijcsj.common_library.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import android.R.string;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

/*
 * DeviceInfoUtils.java

 *
 */
public class DeviceInfoUtils {

    /**
     * 获取设备宽度（px）
     * 
     */
    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     */
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取设备的唯一标识， 需要 “android.permission.READ_Phone_STATE”权限
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI(Context context) {
        Method method = null;
        String deviceId=null;
        try {
            method = TelephonyManager.class.getDeclaredMethod("getImei");
            // 获取TelephonyManager服务的引用
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            // 在非QUIET_MODE_ENABLED的设备上，需要设置QUIET_MODE
            method.setAccessible(true);
             deviceId = (String) method.invoke(telephonyManager);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {

        }

        if (deviceId == null) {
            return "UnKnown";
        } else {
            return deviceId;
        }
    }

    /**
     * 获取厂商名
     * **/
    public static String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取产品名
     * **/
    public static String getDeviceProduct() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机主板名
     */
    public static String getDeviceBoard() {
        return android.os.Build.BOARD;
    }

    /**
     * 设备名
     * **/
    public static String getDeviceDevice() {
        return android.os.Build.DEVICE;
    }

    /**
     * 
     * 
     * fingerprit 信息
     * **/
    public static String getDeviceFubgerprint() {
        return android.os.Build.FINGERPRINT;
    }

    /**
     * 硬件名
     * 
     * **/
    public static String getDeviceHardware() {
        return android.os.Build.HARDWARE;
    }
    public static String getCpuSerialNumber(Context context) {
        String serial = null;
        try {
            // 尝试获取WiFi MAC地址作为CPU序列号
            serial = getWifiMacAddress(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serial;
    }

    private static String getWifiMacAddress(Context context) throws IOException {
        String macAddress = null;
        try {
            WifiManager wifiManager = (WifiManager)context. getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiManager.getConnectionInfo();
             macAddress = info.getMacAddress();
            // 使用获取到的MAC地址
        } catch (Exception e) {
            // 处理异常
        }
        return macAddress;
    }
    /**
     * 主机
     * 
     * **/
    public static String getDeviceHost() {
        return android.os.Build.HOST;
    }

    /**
     * 
     * 显示ID
     * **/
    public static String getDeviceDisplay() {
        return android.os.Build.DISPLAY;
    }

    /**
     * ID
     * 
     * **/
    public static String getDeviceId() {
        return android.os.Build.ID;
    }

    /**
     * 获取手机用户名
     * 
     * **/
    public static String getDeviceUser() {
        return android.os.Build.USER;
    }

    /**
     * 获取手机 硬件序列号
     * **/
    public static String getDeviceSerial() {
        return android.os.Build.SERIAL;
    }

    /**
     * 获取手机Android 系统SDK
     * 
     * @return
     */
    public static int getDeviceSDK() {
        return android.os.Build.VERSION.SDK_INT;
    }
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getUniqueId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String  getInstallTime(Context context){
        try {
            PackageManager pm =context. getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            long installTime = pi.firstInstallTime;
           return  longToTime(installTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String  getTime(){
        return  longToTime(System.currentTimeMillis());

    }
    public static String  getTimes(){
        return  longToTimes(System.currentTimeMillis());

    }
    public static String getWiFiIPAddress(Context context) {
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiMgr != null && wifiMgr.getConnectionInfo() != null) {
            int ipAddress = wifiMgr.getConnectionInfo().getIpAddress();
            return Formatter.formatIpAddress(ipAddress);
        }
        return getLocalIPAddress();
    }

    public static String getMobileIPAddress(Context context) {
        try {
            String ipAddress = new String();
            java.net.InetAddress inetAddress = java.net.InetAddress.getLocalHost();
            ipAddress = inetAddress.getHostAddress();
            return ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getLocalIPAddress();

    }
    private static String getLocalIPAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {

                NetworkInterface intf = en.nextElement();

                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {

                    InetAddress inetAddress = enumIpAddr.nextElement();

                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        // return inetAddress.getAddress().toString();
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("BaseScanTvDeviceClient", "获取本机IP false =" +  ex.toString());
        }

        return null;
    }
    public static String longToTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault());
        return sdf.format(new Date(timeInMillis));
    }
    public static String longToTimes(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timeInMillis));
    }
    @SuppressLint("MissingPermission")
    public static String getDeviceSerialNumber() {
        String serial = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serial = Build.getSerial();
            } else {
                serial = Build.SERIAL;
            }
        } catch (Exception ignored) {
            // Handle the exception
        }
        return serial;
    }
    /**
     * 获取手机Android 版本
     * 
     * @return
     */
    public static String getDeviceAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取当前手机系统语言。
     */
    public static String getDeviceDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     */
    public static String getDeviceSupportLanguage() {
        Log.e("wangjie", "Local:" + Locale.GERMAN);
        Log.e("wangjie", "Local:" + Locale.ENGLISH);
        Log.e("wangjie", "Local:" + Locale.US);
        Log.e("wangjie", "Local:" + Locale.CHINESE);
        Log.e("wangjie", "Local:" + Locale.TAIWAN);
        Log.e("wangjie", "Local:" + Locale.FRANCE);
        Log.e("wangjie", "Local:" + Locale.FRENCH);
        Log.e("wangjie", "Local:" + Locale.GERMANY);
        Log.e("wangjie", "Local:" + Locale.ITALIAN);
        Log.e("wangjie", "Local:" + Locale.JAPAN);
        Log.e("wangjie", "Local:" + Locale.JAPANESE);
        return Locale.getAvailableLocales().toString();
    }
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static String getCPUModel() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
                if (array[i].toLowerCase().contains("model name")) {
                    return array[i + 1];
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
     /* new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
    MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE)*/
    public static String getDeviceAllInfo(Context context) {

        return "\n\n1. IMEI:\n\t\t" + getIMEI(context)

        + "\n\n2. 设备宽度:\n\t\t" + getDeviceWidth(context)

        + "\n\n3. 设备高度:\n\t\t" + getDeviceHeight(context)

        + "\n\n4. 是否有内置SD卡:\n\t\t" + SDCardUtils.isSDCardMount()

        + "\n\n5. RAM 信息:\n\t\t" + SDCardUtils.getRAMInfo(context)

        + "\n\n6. 内部存储信息\n\t\t" + SDCardUtils.getStorageInfo(context, 0)

        + "\n\n7. SD卡 信息:\n\t\t" + SDCardUtils.getStorageInfo(context, 1)

        + "\n\n8. 是否联网:\n\t\t" + isNetworkConnected(context)

        + "\n\n9. 网络类型:\n\t\t" + getConnectedNetworkType(context)

        + "\n\n10. 系统默认语言:\n\t\t" + getDeviceDefaultLanguage()

        + "\n\n11. 硬件序列号(设备名):\n\t\t" + android.os.Build.SERIAL

        + "\n\n12. 手机型号:\n\t\t" + android.os.Build.MODEL

        + "\n\n13. 生产厂商:\n\t\t" + android.os.Build.MANUFACTURER

        + "\n\n14. 手机Fingerprint标识:\n\t\t" + android.os.Build.FINGERPRINT

        + "\n\n15. Android 版本:\n\t\t" + android.os.Build.VERSION.RELEASE

        + "\n\n16. Android SDK版本:\n\t\t" + android.os.Build.VERSION.SDK_INT

        + "\n\n17. 安全patch 时间:\n\t\t" + android.os.Build.VERSION.SECURITY_PATCH



        + "\n\n19. 版本类型:\n\t\t" + android.os.Build.TYPE

        + "\n\n20. 用户名:\n\t\t" + android.os.Build.USER

        + "\n\n21. 产品名:\n\t\t" + android.os.Build.PRODUCT

        + "\n\n22. ID:\n\t\t" + android.os.Build.ID

        + "\n\n23. 显示ID:\n\t\t" + android.os.Build.DISPLAY

        + "\n\n24. 硬件名:\n\t\t" + android.os.Build.HARDWARE

        + "\n\n25. 产品名:\n\t\t" + android.os.Build.DEVICE

        + "\n\n26. Bootloader:\n\t\t" + android.os.Build.BOOTLOADER

        + "\n\n27. 主板名:\n\t\t" + android.os.Build.BOARD

        + "\n\n28. CodeName:\n\t\t" + android.os.Build.VERSION.CODENAME
                + "\n\n29. 语言支持:\n\t\t" + getDeviceSupportLanguage();

    }

    public static int getConnectedNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") Network network = cm.getActiveNetwork();
        if (null == network) {
            return -1;
        }
        @SuppressLint("MissingPermission") NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
        if (null == capabilities) {
            return -1;
        }
        if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return NetworkCapabilities.TRANSPORT_CELLULAR;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return NetworkCapabilities.TRANSPORT_WIFI;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)) {
                return NetworkCapabilities.TRANSPORT_BLUETOOTH;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return NetworkCapabilities.TRANSPORT_ETHERNET;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                return NetworkCapabilities.TRANSPORT_VPN;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)) {
                return NetworkCapabilities.TRANSPORT_WIFI_AWARE;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN)) {
                return NetworkCapabilities.TRANSPORT_LOWPAN;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_USB)) {
                return NetworkCapabilities.TRANSPORT_USB;
            }
        }
        return -1;
    }


}
