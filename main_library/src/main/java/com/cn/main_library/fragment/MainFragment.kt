package com.cn.main_library.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alibaba.android.arouter.facade.annotation.Route
import com.cn.main_library.R
import com.cn.main_library.databinding.FragmentMainBinding
import com.cn.main_library.viewmodel.MainViewModel
import com.google.gson.Gson
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.NetworkStrengthUtil
import com.ijcsj.common_library.util.a
import com.ijcsj.ui_library.stateview.StateView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

@Route(path = "/mainFragment/MainFragment")
class MainFragment : MvvmBaseFragment<FragmentMainBinding, MainViewModel>() {
    override val viewModel by viewModel<MainViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_main
    var gson:Gson?=null
    var wifi:WifiStrengthReceiver=WifiStrengthReceiver()
    var secondHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            updateTime()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION)
        context?.let {
            it.registerReceiver(wifi, intentFilter)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onBinding() {
        viewDataBinding?.viewModel = viewModel
        viewDataBinding?.content?.let {
            mStateView = StateView.inject(it)
        }

    }

    override fun onAgainCreate() {
         gson=Gson()
         observe()
         viewModel.initModel()
        Observable.interval(0, 2000, TimeUnit.MILLISECONDS)
            .map<Long> { mTimer: Long -> mTimer + 1 }
            .subscribeOn(Schedulers.io())
            .map {
                try {
                    context?.let {
                        val wifiManager = it.getSystemService(Context.WIFI_SERVICE) as WifiManager
                        val rssi = wifiManager.connectionInfo.rssi
                        Log.w("WifiStrengthReceiver", "rssi  $rssi")
                        var s=    NetworkStrengthUtil.getSignalLevel(rssi)
                        viewModel.mainBase.signalStrength="信号强度: $s %"
                    }
                }catch (e:Exception){

                }

            }
           .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{})
    }

    override fun onAgainCreates() {
        ( viewDataBinding?.rvProjectS?.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false //取消刷新闪屏动画
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.let {
            it.unregisterReceiver(wifi)
        }
    }

    override fun onResume() {
        super.onResume()
        updateTime()
        var d=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
        viewModel.mainBase.settingTemperature=(d!!.toFloat()/10.toFloat()).toString()
        var ad= ShuJuMMkV.getInstances()?.getString(a.TIME,"")
        if (TextUtils.isEmpty(ad)){
            ShuJuMMkV.getInstances()?.putString(a.TIME,DateUtil.formatTime(DateUtil.getCurrentTime(), "yyyyMMddHHmm"))
        }
        Log.w("fragment_main","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w("fragment_main","onPause")
    }

     fun updateTime() {
        viewModel.mainBase.time=DateUtil.formatTime(DateUtil.getCurrentTime(), "yyyy-MM-dd HH:mm:ss")
        if (null != secondHandler) {
            val message = Message()
            message.what = 1
            secondHandler!!.sendMessageDelayed(message, 1000)
        }
         ( viewDataBinding?.rvProject?.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false //取消刷新闪屏动画

     }

    fun observe(){
        LiveDataBus.get().with("tabBottomLayout", Int::class.java ).observe(this){
            if (it==0){
                viewModel.initModel()
            }
        }
        viewModel.projectBaseList.observe(this){
            if (viewDataBinding?.rvProject?.adapter==null){
                viewDataBinding?.rvProject?.adapter=viewModel.adapter
            }
            viewModel.adapter.refresh(it)
            viewModel.projectBaseLists=it
        }
        viewModel.projectBaseListd.observe(this){
            if (viewDataBinding?.rvProjectS?.adapter==null){
                viewDataBinding?.rvProjectS?.adapter=viewModel.adapters
            }
            viewModel.adapters.refresh(it)
            viewModel.projectBaseListsd=it
        }
        LiveDataBus.get().with(Socketcan.CAN_100, CanFrame::class.java).observe(this){
            if (it.can_id==256){
                viewModel.setCan100Data(it)
            }
        }
        LiveDataBus.get().with(Socketcan.CAN_099, CanFrame::class.java).observe(this){
            viewModel.projectBaseLists?.let { data->
                if (it.can_id==153){
                    viewModel.setCan099Data(it,data)
                }
            }
        }
        LiveDataBus.get().with(Socketcan.CAN_102, CanFrame::class.java).observe(this){
            if (it.can_id==258){
                viewModel.setCan102Data(it)
            }
        }
        LiveDataBus.get().with(Socketcan.CAN_101, CanFrame::class.java).observe(this){
            if (it.can_id==257){
                viewModel.setCan101Data(it)
            }
        }
        LiveDataBus.get().with("tabBottomLayout", Int::class.java ).observe(this){
            var d=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
            viewModel.mainBase.settingTemperature=(d!!.toFloat()/10.toFloat()).toString()
            var ad= ShuJuMMkV.getInstances()?.getString(a.TIME,"")
            if (TextUtils.isEmpty(ad)){
                ShuJuMMkV.getInstances()?.putString(a.TIME,DateUtil.formatTime(DateUtil.getCurrentTime(), "yyyyMMddHHmm"))
            }
        }
    }
    class WifiStrengthReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (WifiManager.RSSI_CHANGED_ACTION == p1?.action) {
                val wifiManager = p0?.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val rssi = wifiManager.connectionInfo.rssi
                Log.w("WifiStrengthReceiver", "rssi  $rssi")
            }
        }
    }
}