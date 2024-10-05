package com.ijcsj.inlet_library.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.FrameMetrics.ANIMATION_DURATION
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.common_library.util.Constant
import com.ijcsj.inlet_library.R
import com.ijcsj.inlet_library.adapter.SimpleAdapter
import com.ijcsj.inlet_library.databinding.ActivityInletBinding
import com.ijcsj.inlet_library.databinding.ActivityIntroduceBinding
import com.ijcsj.inlet_library.viewmodel.InletViewModel
import com.ijcsj.ui_library.anko.dp
import com.ijcsj.ui_library.utils.immersive
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.utils.BannerUtils
import com.zhpan.indicator.enums.IndicatorSlideMode.Companion.WORM
import com.zhpan.indicator.enums.IndicatorStyle.Companion.ROUND_RECT
import org.koin.androidx.viewmodel.ext.android.viewModel
@Route(path = "/Inlet/IntroduceActivity")
class IntroduceActivity : MvvmBaseActivity<ActivityIntroduceBinding, InletViewModel>() {
    override val viewModel by viewModel<InletViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() =R.layout.activity_introduce
  var binding:  BannerViewPager<Int>?=null
    private val icTitle = arrayOf(com.ijcsj.ui_library.R.mipmap.ic_bg_inlet1_title, com.ijcsj.ui_library.R.mipmap.ic_bg_inlet2_title, com.ijcsj.ui_library.R.mipmap.ic_bg_inte3_title)
    private val icSubTitle = arrayOf(com.ijcsj.ui_library.R.mipmap.ic_bg_iniet1_subtitle, com.ijcsj.ui_library.R.mipmap.ic_bg_inlet2_subtitle, com.ijcsj.ui_library.R.mipmap.ic_bg_inte3_subtitle)
    override fun onBinding() {

        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {

        ShuJuMMkV.instance?.putBoolean(Constant.CONSTANT_FIRST_ENTRY, true)
        binding= viewDataBinding?.bannerView as BannerViewPager<Int>?
        binding?.apply {
            setIndicatorStyle(ROUND_RECT)
            setIndicatorSlideMode(WORM)
            setIndicatorSliderWidth(8.dp,20.dp)
            setIndicatorHeight(8.dp)
            setIndicatorSliderColor(Color.parseColor("#C5DBF8"),Color.parseColor("#ffffff"))
            setIndicatorMargin(0,0,0,70.dp)
            adapter = SimpleAdapter()
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    BannerUtils.log("position:$position")
                    updateUI(position)
                }
            })
        }?.create()
        viewModel.introduceData()
        viewModel.introduceDataList.observe(this){
            binding?.refreshData(it)
        }
    }

    fun updateUI(position: Int){
        viewDataBinding?.imgTitle?.setImageResource(icTitle[position])
        viewDataBinding?.imgSubTitle?.setImageResource(icSubTitle[position])
       val translationAnim = ObjectAnimator.ofFloat(  viewDataBinding?.imgTitle, "translationX", 0f, 90f)
        translationAnim.apply {
            duration = ANIMATION_DURATION.toLong()
            interpolator = DecelerateInterpolator()
        }
        val alphaAnimator = ObjectAnimator.ofFloat(  viewDataBinding?.imgTitle, "alpha", 0f, 1f)
        alphaAnimator.apply {
            duration = ANIMATION_DURATION.toLong()
        }

       /* val translationAnims = ObjectAnimator.ofFloat(  viewDataBinding?.imgSubTitle, "translationX", -120f, 0f)
        translationAnims.apply {
            duration = ANIMATION_DURATION.toLong()
            interpolator = DecelerateInterpolator()
        }
        val alphaAnimators= ObjectAnimator.ofFloat(  viewDataBinding?.imgSubTitle, "alpha", 0f, 1f)
        alphaAnimators.apply {
            duration = ANIMATION_DURATION.toLong()
        }*/
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(translationAnim, alphaAnimator)
        animatorSet.start()

        if (position == 0) {
            viewDataBinding?.sbIntroduceSkip?.visibility = View.VISIBLE
            ObjectAnimator
                .ofFloat(  viewDataBinding?.sbIntroduceSkip, "alpha", 0f, 1f)
                .setDuration(ANIMATION_DURATION.toLong()).start()
        } else {
            viewDataBinding?.sbIntroduceSkip?.visibility = View.GONE
        }


        if (position == 2&& viewDataBinding?.sbIntroduceExperienceNow?.visibility == View.GONE) {
            viewDataBinding?.sbIntroduceExperienceNow?.visibility = View.VISIBLE
            ObjectAnimator
                .ofFloat(  viewDataBinding?.sbIntroduceExperienceNow, "alpha", 0f, 1f)
                .setDuration(ANIMATION_DURATION.toLong()).start()
        } else {
            viewDataBinding?.sbIntroduceExperienceNow?.visibility = View.GONE
        }

    }

}