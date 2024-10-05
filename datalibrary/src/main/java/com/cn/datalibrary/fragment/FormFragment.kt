package com.cn.datalibrary.fragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import com.blankj.utilcode.util.ColorUtils.getColor
import com.cn.datalibrary.R
import com.cn.datalibrary.databinding.FragmentFormBinding
import com.cn.datalibrary.util.CustomXAxisRenderer
import com.cn.datalibrary.viewmodel.FormViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.ValueFormatter
import com.ijcsj.common_library.bean.BackFlowBase
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.util.ExcelChart
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.ui_library.stateview.StateView
import org.koin.androidx.viewmodel.ext.android.viewModel


class FormFragment : MvvmBaseFragment<FragmentFormBinding, FormViewModel>() {
    override val viewModel by viewModel<FormViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_form

    override fun onBinding() {
        viewDataBinding?.viewModel = viewModel
        viewDataBinding?.content?.let {
            mStateView = StateView.inject(it)
        }

    }

    override fun onAgainCreate() {
    /*    mStateView?.showLoading()*/
        observe()
        viewModel.initModel()
        // 3.4 节电详情页

        initChat()

    }

    override fun onAgainCreates() {

    }

    private fun initChat() {
       var char= viewDataBinding?.chart1 as LineChart
        char.setBackgroundColor(resources.getColor(R.color.color_00000000))
        char.description?.isEnabled = false
        char.description?.isEnabled = false
        char.setNoDataText("暂无数据");
        // 2. X 轴样式
        val xAxis: XAxis? =  char.xAxis
        xAxis?.isEnabled = true
        xAxis?.setDrawGridLines(true)
        xAxis?.setDrawAxisLine(true)
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        xAxis?.axisLineWidth = resources.getDimension(R.dimen.tvcommon_px1)
        xAxis?.axisLineColor = resources.getColor(R.color.color_979797)
        xAxis?.textSize = resources.getDimension(R.dimen.tvcommon_sp16)
        xAxis?.textColor = resources.getColor(R.color.white_60alpha)
        xAxis?.setLabelCount(10)
       var mLegend  = char.legend;
        mLegend.setTextColor(Color.WHITE);

        // xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        xAxis?.gridColor = getColor(R.color.x_grid_color)
        // 3. Y轴样式
        char.axisRight?.isEnabled = false  // disable dual axis (only use LEFT axis)
        var yAxis: YAxis? =  char.axisLeft
        yAxis?.axisMaximum = 60f
        yAxis?.axisMinimum = 0f
        yAxis?.isEnabled = true
        yAxis?.axisLineWidth = resources.getDimension(R.dimen.tvcommon_px1)
        yAxis?.axisLineColor = resources.getColor(R.color.color_979797)
        yAxis?.textSize = resources.getDimension(R.dimen.tvcommon_sp16)
      //  yAxis?.setAxisMinimum(-20f); // 设置Y轴的最小值
        yAxis?.textColor = resources.getColor(R.color.color_979797)
        yAxis?.setDrawGridLines(true)
        yAxis?.setDrawAxisLine(true)
        char.extraBottomOffset = 2 * 8f;
        char.setXAxisRenderer( CustomXAxisRenderer( char.viewPortHandler,  char.xAxis,  char.getTransformer(YAxis.AxisDependency.LEFT)));
        var medium = 24;
        var larger = 24;
        var limit = 50;
        //char.isScaleYEnabled=false

        // 创建一个大小为5的Int数组，其中包含初始化的值
     /*   val colors = IntArray(4)
        colors[0] = Color.parseColor("#FF4936");
        colors[1] = Color.parseColor("#FF4936");
        colors[2] = Color.parseColor("#FF4936");
        colors[3] = Color.parseColor("#0048FF");*/
       // val renderer = char.renderer as MyLineChartRenderer

       // renderer.setHeartLine(true, medium,larger,limit, colors);
    }
//(it[value as Int].data as TemperatureBase).eventTime

    fun observe(){
        viewModel.dataTitleListd.observe(this){
           viewModel. initModel()
        }
        LiveDataBus.get().with("temperature_base", Boolean::class.java ).observe(this){

        }
        viewModel.dataTitleList.observe(this){
            if (it.size!=3){
                return@observe
            }
            var char= viewDataBinding?.chart1 as LineChart
            val xAxis: XAxis? =  char.xAxis
            xAxis?.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    try {
                        var f=value-(value.toInt()).toFloat()
                        if (f==0f){
                             return DateUtil.formatTimes((it[1].values[value.toInt()-1].data as BackFlowBase).dateTime)
                          //   return "00:00\n09-23"
                        }else{
                            return ""
                        }
                    }catch (e:Exception){
                        return ""
                    }
                }
            }
            xAxis?.setLabelCount(it[1].values.size);
           if (it[1].values.size>24){
                char?.setScaleMinima(2f,3f)
            }else if (it[1].values.size>14){
              char?.setScaleMinima(2f,2f )
            }else if (it[1].values.size>6){
               char?.setScaleMinima(2f,2f )
           }else{
              char?.setScaleMinima(1f,1f)
            }
            char?.setScaleMinima(1f,1f)
            var int=0.0.toFloat()
            var int1=0.0.toFloat()
            for (i in 0 until it[0].values.size) {
                if (it[0].values[i].y>int){
                    int= it[0].values[i].y
                }
            }
            for (i in 0 until it[1].values.size) {
                if (it[1].values[i].y<int1){
                    int1= it[1].values[i].y
                }
            }
          /*  for (i in 0 until it[1].values.size) {
                if (it[1].values[i].y>int){
                    int= it[1].values[i].y
                }
            }

            for (i in 0 until it[2].values.size) {
                if (it[2].values[i].y>int){
                    int= it[2].values[i].y
                }
            }*/
            var yAxis: YAxis? =  char.axisLeft
            yAxis?.axisMaximum = int+10
            yAxis?.setAxisMinimum(int1-5f)
            yAxis?.setStartAtZero(false)
            val data = LineData(it[0],it[1],it[2])
            char.setData(data)
            char.invalidate();
        }
    }

}