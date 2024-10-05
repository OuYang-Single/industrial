package com.ijcsj.inlet_library.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import android.widget.ProgressBar
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allenliu.versionchecklib.v2.AllenVersionChecker
import com.allenliu.versionchecklib.v2.builder.UIData
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.inlet_library.R
import com.ijcsj.inlet_library.base.MainBases
import com.ijcsj.inlet_library.model.MainModel
import com.ijcsj.ui_library.utils.AppGlobals
import com.ijcsj.ui_library.widget.tab.bottom.HiTabBottomInfo
import com.lxj.xpopup.core.BasePopupView
import com.yaoxiaowen.download.FileInfo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel (override val model: MainModel,var mainBase: MainBases) : MvmBaseViewModel<IBaseView, MainModel>() {

    private val _infoList = MutableLiveData<List<HiTabBottomInfo<*>>>()
    val infoList: LiveData<List<HiTabBottomInfo<*>>> get() = _infoList

    private val _phoneInfoList = MutableLiveData<List<HiTabBottomInfo<*>>>()
    val phoneInfoList: LiveData<List<HiTabBottomInfo<*>>> get() = _phoneInfoList
    private val _messages = MutableLiveData<List<String>>()
    val messages: LiveData<List<String>> get() = _messages

    var basePopupView: BasePopupView?=null;


    public override fun initModel() {
        //getFaceSdkSign()
        //tximInit()

        val defaultColor: Int? = AppGlobals.get()?.resources?.getColor(R.color.color_main_tsd)
        val tintColor: Int? =AppGlobals.get()?.resources?.getColor(R.color.color_main_tsd_n)
        var  infoLists = mutableListOf<HiTabBottomInfo<*>>()
        val homeInfo = HiTabBottomInfo<Int>(
            "",
            R.mipmap.ic_home,
            R.mipmap.ic_home,
            tintColor,
            defaultColor,
            "/mainFragment/MainFragment"
        )

        val queryInfo = HiTabBottomInfo<Int>(
            "",
            R.mipmap.ic_query,
            R.mipmap.ic_query,
            tintColor,
            defaultColor,
            "/bata/BataMainFragment"
        )
      /*  val alarmClockInfo = HiTabBottomInfo<Int>(
            "",
            R.mipmap.ic_alarm_clock,
            R.mipmap.ic_alarm_clock,
            tintColor,
            defaultColor,
            "/online/OnlineVideoFragment"
        )
        val warnInfo = HiTabBottomInfo<Int>(
            "",
            R.mipmap.ic_warn,
            R.mipmap.ic_warn,
            tintColor,
            defaultColor,
            "/online/OnlineVideoFragment"
        )*/
        val setUPInfo = HiTabBottomInfo<Int>(
            "",
            R.mipmap.ic_set_up,
            R.mipmap.ic_set_up,
            tintColor,
            defaultColor,
            "/setting/SettingMainFragment"
        )
        val bookInfo = HiTabBottomInfo<Int>(
            "",
            R.mipmap.ic_my,
            R.mipmap. ic_my,
            tintColor,
            defaultColor,
            "/my/MyFragment"
        )
        infoLists.add(homeInfo)
        infoLists.add(queryInfo)
    /*    infoLists.add(alarmClockInfo)
        infoLists.add(warnInfo)*/
        infoLists.add(setUPInfo)
        infoLists.add(bookInfo)
        _infoList.postValue(infoLists)
        //init()

        val phoneDefaultColor: Int? = AppGlobals.get()?.resources?.getColor(R.color.phone_color_main_tsd)
        val phoneTintColor: Int? =AppGlobals.get()?.resources?.getColor(R.color.phone_color_main_tsd_n)
        var  phoneInfoLists = mutableListOf<HiTabBottomInfo<*>>()
        val phoneHomeInfo = HiTabBottomInfo<Int>(
            "首页",
            R.mipmap.ic_home_phone,
            R.mipmap.ic_home_phone,
            phoneDefaultColor,
            phoneTintColor,
            "/mainFragment/PhoneMainFragment"
        )
        phoneInfoLists.add(phoneHomeInfo)
        _phoneInfoList.postValue(phoneInfoLists)
    }



    fun getAppVersion(context: Context): Int {
        var versionCode = 0
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionCode = packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            // 应用程序没有找到异常
            e.printStackTrace()
        }
        return versionCode
    }


    /*弹窗更新apk弹窗*/
    fun  init(){
        AppGlobals.get().let {
            AllenVersionChecker
                .getInstance()
                .downloadOnly(
                    UIData.create().setDownloadUrl("https://app-cdn.lynkco.com/android/lynkco-64-v3.3.8.apk")
                )
                .setRunOnForegroundService(true)
            .setForceUpdateListener {  }
              .executeMission( AppGlobals.get());
        }


    }

    fun downLoad(fileInfo: FileInfo?) {
       var progressBar= basePopupView?.findViewById<ProgressBar>(R.id.progress)
        fileInfo?.let {
            var progress = (it.downloadLocation.toFloat() / it.size.toFloat() * 100).toInt()
            progressBar?.progress = progress;
        }

    }

    fun addFormData(canFrame: CanFrame){
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            withContext(Dispatchers.IO){
                model.addFormData(canFrame)
            }
        }
    }
    fun setCan101Datas(it: CanFrame) {
        var data1=  Hexs. getBitByByte(it.data[0],0,1)
        var data2=  Hexs. getBitByByte(it.data[0],2,3)
        var data3=  Hexs. getBitByByte(it.data[0],4,5)
        var data4=  Hexs. getBitByByte(it.data[0],6,7)
        var data5=  Hexs. getBitByByte(it.data[1],0,1)
        var data6=  Hexs. getBitByByte(it.data[1],2,3)
        var data7=  Hexs. getBitByByte(it.data[1],4,5)
        var data8=  Hexs. getBitByByte(it.data[1],6,7)
        var data9=  Hexs. getBitByByte(it.data[2],0,1)
        var data10=  Hexs. getBitByByte(it.data[2],2,3)
        var data11=  Hexs. getBitByByte(it.data[2],4,5)
        var data12=  Hexs. getBitByByte(it.data[2],6,7)
        var data13=  Hexs. getBitByByte(it.data[3],0,1)
        var data14=  Hexs. getBitByByte(it.data[3],2)
        var data15=  Hexs. getBitByByte(it.data[3],3)
        var data16=  Hexs. getBitByByte(it.data[3],6,7)
        val data: ObservableList<Int> = ObservableArrayList()
        data.add(data1)
        data.add(data2)
        data.add(data3)
        data.add(data4)
        data.add(data5)
        data.add(data6)
        data.add(data7)
        data.add(data8)
        data.add(data9)
        data.add(data10)
        data.add(data11)
        data.add(data12)
        data.add(data13)
        data.add(data14)
        data.add(data15)
        data.add(data16)
        var valueData=false;
        for (i in 0 until data.size) {
            if (data[i]==1){
                valueData=true
                break
            }
        }
        mainBase.isWarn=valueData

    }
    fun setCan101Data(it: CanFrame) {
        setCan101Datas(it)
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            withContext(Dispatchers.IO){
                val messages: ArrayList<String> = ArrayList()
               var int= model.addHistoryBase(it,messages)
                if (int>0){
                    _messages.postValue(messages)
                }

            }
        }
    }


}