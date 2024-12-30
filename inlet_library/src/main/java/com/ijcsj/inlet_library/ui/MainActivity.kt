package com.ijcsj.inlet_library.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.DataBaseDatabase.Companion.getDatabase
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.common_library.util.Hexs.encodeHexStr
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.a
import com.ijcsj.inlet_library.R
import com.ijcsj.inlet_library.databinding.ActivityMainBinding
import com.ijcsj.inlet_library.servicel.VersionsServices
import com.ijcsj.inlet_library.viewmodel.MainViewModel
import com.ijcsj.ui_library.anko.countDownCoroutines
import com.ijcsj.ui_library.utils.immersive
import com.ijcsj.ui_library.widget.marqueeview.MarqueeView
import com.ijcsj.ui_library.widget.tab.top.HiTabViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess


@Route(path = "/Inlet/MainActivity")
class MainActivity : MvvmBaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel by viewModel<MainViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() =R.layout.activity_main

    private var currentItemIndex = 0
    private var exitTime: Long = 0
    var gson= Gson()
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }
    var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0) {

                viewDataBinding?. marqueeView?.visibility=View.VISIBLE
            }
        }
    }


    override fun onAgainCreate(savedInstanceState: Bundle?) {
        immersive(darkMode = false)
        viewModel.initModel()
        viewModel.infoList.observe(this){
            viewDataBinding?.tabBottomLayout?.inflateInfo(it)
            val tabViewAdapter = HiTabViewAdapter(supportFragmentManager, viewModel.infoList.value)
            viewDataBinding?.fragmentTabView?.adapter = tabViewAdapter
            viewDataBinding?.tabBottomLayout?.defaultSelected(it[currentItemIndex])
        }
        viewDataBinding?.tabBottomLayout?.addTabSelectedChangeListener { index, prevInfo, nextInfo ->
            viewDataBinding?.fragmentTabView?.currentItem = index
            if (index==2){
                var isUserLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.USER_LOG_ON,false)
                if (isUserLogOn==false){
                    ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",0).navigation()
                }
            }else{
                ShuJuMMkV.getInstances()?.putBoolean(a.USER_LOG_ON,false)
                ShuJuMMkV.getInstances()?.putBoolean(a.ENGINEERING_LOG_ON,false)
                ShuJuMMkV.getInstances()?.putBoolean(a.MANUFACTOR_LOG_ON,false)
            }
            LiveDataBus.get().with("tabBottomLayout", Int::class.java ).postValue(index)
            this@MainActivity.currentItemIndex = index
        }

        LiveDataBus.get().with(Socketcan.CAN_100, CanFrame::class.java).observe(this){
            if (it.can_id==256){
                Log.w("ouyang", "CAN_100 " + it.can_id + "  " + " data:  " + encodeHexStr(it.data) + "  "+it.dd)
               viewModel.addFormData(it)
            }
        }
        LiveDataBus.get().with(Socketcan.CAN_101, CanFrame::class.java).observe(this){
            if (it.can_id==257){
                viewModel.setCan101Data(it)
            }
        }
        viewDataBinding?.tabBottomLayout?.setBottomLineHeight(0f)
        viewDataBinding?.tabBottomLayout?.setTabHeight(45f)
        viewDataBinding?.tabBottomLayout?.setTabHeights( 49f)
        viewDataBinding?.tabBottomLayout?.setBottomLineColor("#00ffffff")
  /*      "android.permission.FOREGROUND_SERVICE_DATA_SYNC"*/
        XXPermissions.with(this)
            // 申请单个权限
            .permission(
                Permission.CAMERA,
                Permission.ACCESS_FINE_LOCATION,
                Permission.WRITE_SETTINGS,
            )
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    VersionsServices.enqueueWork(applicationContext)
                }
                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {

                }
            })
        viewModel.message.observe(this){
            toastUtils(it)
        }
        LiveDataBus.get().with("exitProcess", Boolean::class.java ).observe(this){
            if (it){
                countDownCoroutines(4, lifecycleScope,
                    onTick = { second ->
                        if (second==1){
                            exitProcess(0);
                        }
                    }, onStart = null, onFinish = {

                    })
            }
        }
        viewModel.messages.observe(this){
            var a= viewDataBinding?. marqueeView as MarqueeView

          var   list=    viewModel.dddMessages
            var boolean=false;
            if (list.size!=it.size){
                boolean=true
            }else{
                for (element in list){
                    if (element in  it) { } else {
                        boolean=true;
                        break
                    }
                }
                if (!boolean){
                    for (i in list.indices) {
                        if (list[i]!=it[i]){
                            boolean=true;
                            break
                        }
                    }
                }
            }


          if (list.size!=it.size){
              boolean=true
          }
            if (list.isEmpty()){
                boolean=true
            }
           if (boolean){
               LiveDataBus.get().with("HistoryFragment.CAN_101",Boolean::class.java).postValue(true)
               viewModel.dddMessages=it

               viewDataBinding?. marqueeView?.removeAllViews();
               viewDataBinding?. marqueeView?.visibility=View.INVISIBLE
               viewDataBinding?. marqueeView?.startWithList( viewModel.dddMessages);
               handler.removeMessages(0)
               handler.sendEmptyMessageDelayed(0, 1000)
           }
        }
        LiveDataBus.get().with("CAN_LiveDataBus", CanFrame::class.java ).observe(this){
            it?.let {
               viewModel.CAN_LiveDataBus(it)
            }
        }
    }
    var rRunnable=Runnable{
        Log.w("ouyang","rRunnable  ")


    }

    override fun onRestart() {
        super.onRestart()
        var isUserLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.USER_LOG_ON,false)
        var isUserLogOns=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        var isUserLogOns2=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isUserLogOn==false&&isUserLogOns==false&&isUserLogOns2==false){
            viewDataBinding?.fragmentTabView?.currentItem = 0
            viewDataBinding?.tabBottomLayout?.defaultSelected( viewModel.infoList.value!![0])
        }
    }

    public override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }
    @SuppressLint("MissingSuperCall")
    override fun  onBackPressed( ) {
        if (System.currentTimeMillis() - exitTime > 2000) {
            toastUtils( "再按一次退出程序")
            exitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()

    }

}