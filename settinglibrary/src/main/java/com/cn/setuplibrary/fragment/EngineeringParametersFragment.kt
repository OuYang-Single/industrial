package com.cn.setuplibrary.fragment

import android.view.View
import android.view.inputmethod.EditorInfo
import com.cn.setuplibrary.viewmodel.EngineeringParametersViewModel
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.a
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentEngineeringParametersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat


class EngineeringParametersFragment: MvvmBaseFragment<FragmentEngineeringParametersBinding, EngineeringParametersViewModel>() {
    override val viewModel by viewModel<EngineeringParametersViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_engineering_parameters
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }


    override fun onAgainCreate() {
        observe()
        viewDataBinding?.etPicP?.setOnEditorActionListener { p0, p1, p2 ->
            if (p1 === EditorInfo.IME_ACTION_DONE) {

                try {
                    var d111=p0.text.toString().toFloat()
                    if (d111<0|| d111>2.55){
                        toastUtils("加热比例超出范围")
                        return@setOnEditorActionListener true
                    }

                    val bigDecimal = BigDecimal(d111.toString())
                    val formattedValue: String =   bigDecimal.setScale(2, BigDecimal.ROUND_DOWN).toString()
                    d111=formattedValue.toFloat()
                    viewModel.engineerIngBean.pinP=d111.toString()
                    var bytes2=ByteArray(8)
                    var d0=  (d111*100).toInt()
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.PID_I,"1")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.PID_D,"10")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.COAL_COMPENSATION,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.COAL_RETURN_COMPENSATION,"0")
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]= (d1!!.toInt() and 0xff).toByte()
                    bytes2[2]= (d2!!.toInt() and 0xff).toByte()
                    bytes2[3]= (d3!!.toInt() and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt() and 0xff).toByte()

                    var a1=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_108,bytes2)
                    if (a1>0){
                        ShuJuMMkV.getInstances()?.putString(a.PID_P,(d111*100).toInt().toString())
                    }else{
                        toastUtils("发送失败，请重试")
                    }
                }catch (e:Exception){
                    toastUtils("请输入正确格式")
                }

            }
            return@setOnEditorActionListener false
         }
        viewDataBinding?.etPicI?.setOnEditorActionListener { p0, p1, p2 ->
            if (p1 === EditorInfo.IME_ACTION_DONE) {
                try {
                    var d111=p0.text.toString().toFloat()
                    if (d111<0|| d111>0.255){
                        toastUtils("加热积分超出范围")
                        return@setOnEditorActionListener true
                    }
                    var d12=0f
                    val bigDecimal = BigDecimal(d111.toString())
                    val formattedValue: String =   bigDecimal.setScale(3, BigDecimal.ROUND_DOWN).toString()
                    d12=formattedValue.toFloat()
                    viewModel.engineerIngBean.pinI=d12.toString()
                    var bytes2=ByteArray(8)
                    var d0=    ShuJuMMkV.getInstances()?.getString(a.PID_P,"100")
                    var d1=  (d12*1000).toInt()
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.PID_D,"10")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.COAL_COMPENSATION,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.COAL_RETURN_COMPENSATION,"0")
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]= (d1!!.toInt() and 0xff).toByte()
                    bytes2[2]= (d2!!.toInt() and 0xff).toByte()
                    bytes2[3]= (d3!!.toInt() and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt() and 0xff).toByte()
                    var a1=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_108,bytes2)
                    if (a1>0){
                        ShuJuMMkV.getInstances()?.putString(a.PID_I,(d12*1000).toInt().toString())
                    }else{
                        toastUtils("发送失败，请重试")
                    }
                }catch (e:Exception){
                    toastUtils("请输入正确格式")
                }
            }
            return@setOnEditorActionListener false
        }
        viewDataBinding?.etPicD?.setOnEditorActionListener { p0, p1, p2 ->
            if (p1 === EditorInfo.IME_ACTION_DONE) {
                try {
                    var d111=p0.text.toString().toFloat()
                    if (d111<0|| d111>2.55){
                        toastUtils("加热微分超出范围")
                        return@setOnEditorActionListener true
                    }
                    val bigDecimal = BigDecimal(d111.toString())
                    val formattedValue: String =   bigDecimal.setScale(2, BigDecimal.ROUND_DOWN).toString()
                    d111=formattedValue.toFloat()
                    viewModel.engineerIngBean.pinD=d111.toString()
                    var bytes2=ByteArray(8)
                    var d0=    ShuJuMMkV.getInstances()?.getString(a.PID_P,"100")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.PID_I,"1")
                    var d2=  (d111*100).toInt()
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.COAL_COMPENSATION,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.COAL_RETURN_COMPENSATION,"0")
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]= (d1!!.toInt() and 0xff).toByte()
                    bytes2[2]= (d2!!.toInt() and 0xff).toByte()
                    bytes2[3]= (d3!!.toInt() and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt() and 0xff).toByte()

                    var a1=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_108,bytes2)
                    if (a1>0){
                        ShuJuMMkV.getInstances()?.putString(a.PID_D,(d111*100).toInt().toString())
                    }else{
                        toastUtils("发送失败，请重试")
                    }
                }catch (e:Exception){
                    toastUtils("请输入正确格式")
                }
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onAgainCreates() {
        viewModel.initModel()
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn != null) {
            viewDataBinding?.llPidP?.visibility = if (isEngineeringLogOn){View.GONE}else{View.VISIBLE}
            viewDataBinding?.llPidI?.visibility = if (isEngineeringLogOn){View.GONE}else{View.VISIBLE}
            viewDataBinding?.llPidD?.visibility = if (isEngineeringLogOn){View.GONE}else{View.VISIBLE}
        }
    }

    fun observe(){
        viewModel.message.observe(this){
            toastUtils(it)
        }
        LiveDataBus.get().with("onClickBut1", Boolean::class.java ).observe(this){
            viewModel.initModel()
            var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
            if (isEngineeringLogOn != null) {
                viewDataBinding?.llPidP?.visibility = if (isEngineeringLogOn){View.GONE}else{View.VISIBLE}
                viewDataBinding?.llPidI?.visibility = if (isEngineeringLogOn){View.GONE}else{View.VISIBLE}
                viewDataBinding?.llPidD?.visibility = if (isEngineeringLogOn){View.GONE}else{View.VISIBLE}
            }
        }

    }
}