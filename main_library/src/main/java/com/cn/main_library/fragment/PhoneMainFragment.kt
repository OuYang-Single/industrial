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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alibaba.android.arouter.facade.annotation.Route
import com.cn.main_library.R
import com.cn.main_library.databinding.FragmentMainPhoneBinding
import com.cn.main_library.viewmodel.MainViewModel
import com.google.gson.Gson
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.util.NetworkStrengthUtil
import com.ijcsj.ui_library.stateview.StateView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

@Route(path = "/mainFragment/PhoneMainFragment")
class PhoneMainFragment : MvvmBaseFragment<FragmentMainPhoneBinding, MainViewModel>() {
    override val viewModel by viewModel<MainViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_main_phone
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
        ( viewDataBinding?.rvProject?.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false //取消刷新闪屏动画
         observe()
         viewModel.initModel()
        Observable.interval(0, 2000, TimeUnit.MILLISECONDS)
            .map<Long> { mTimer: Long -> mTimer + 1 }
            .subscribeOn(Schedulers.io())
            .map {
                context?.let {
                    val wifiManager = it.getSystemService(Context.WIFI_SERVICE) as WifiManager
                    val rssi = wifiManager.connectionInfo.rssi
                    Log.w("WifiStrengthReceiver", "rssi  $rssi")
                    val strengthPercentage: Int =
                        NetworkStrengthUtil.getNetworkStrengthPercentage(context)
                    var s=0
                    if (strengthPercentage==-1){
                         s= NetworkStrengthUtil.getSignalLevel(rssi)
                    }else{
                        s=strengthPercentage
                    }

                   viewModel.mainBase.signalStrength="信号强度: $s %"
                }
            }
           .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun onAgainCreates() {

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
    }

     fun updateTime() {
        var weekday= DateUtil.getWeekdayFromDates(DateUtil.getCurrentTime())
        viewModel.mainBase.time="当前时间    "+DateUtil.formatTime(DateUtil.getCurrentTime(), "yyyy-MM-dd")+"   $weekday"
        viewModel.mainBase.times=DateUtil.formatTime(DateUtil.getCurrentTime(), " HH:mm")
        if (null != secondHandler) {
            val message = Message()
            message.what = 1
            secondHandler!!.sendMessageDelayed(message, 1000)
        }
     }

    fun observe(){
        viewModel.projectBaseList.observe(this){
            if (viewDataBinding?.rvProject?.adapter==null){
                viewDataBinding?.rvProject?.adapter=viewModel.phoneMainAdapter
            }
            viewModel.phoneMainAdapter.refresh(it)
            viewModel.projectBaseLists=it
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