package com.template.mvvmapp.client.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.template.base.BaseActivity
import com.template.base.util.ScreenUtil
import com.template.mvvmapp.R
import com.template.mvvmapp.app.NavigationUtil
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.layout_guide_entry.view.*
import java.util.*

/**
 * 引导页面
 */
class GuideActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_guide

    private lateinit var entryView: View
    private lateinit var imageViews: ArrayList<View>
    private var leftMax = 0

    override fun initViews() {
        super.initViews()
        entryView = LayoutInflater.from(this)
            .inflate(R.layout.layout_guide_entry, null)
        initData()
        initListener()
    }

    private fun initData() {
        val ids = intArrayOf(
            R.drawable.guide,
            R.drawable.guide,
        )
        imageViews = ArrayList<View>()
        val width: Int = ScreenUtil.dp2px(this, 10f)
        for (i in 0 until ids.size + 1) {
            //创建小圆点
            val point = ImageView(this)
            point.setBackgroundResource(R.mipmap.ic_navi_point_default)
            val params = LinearLayout.LayoutParams(width, width)
            //除了第一个点，其他点都要给它设置左边距
            if (i != 0) {
                params.leftMargin = ScreenUtil.dp2px(this, 12f)
            }
            point.layoutParams = params
            ll_point_group.addView(point)
            if (i == ids.size) {
            } else {
                //将图片封装成imageview的对象
                val view: View = LayoutInflater.from(this@GuideActivity)
                    .inflate(R.layout.layout_guide, null)
                view.iv.setImageResource(ids[i])
                imageViews.add(view)
            }
        }
        viewpage.adapter = MyPagerAdapter()
        viewpage.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == imageViews.size) {
                    iv_skip.visibility = View.GONE;
                    rl_progress.visibility = View.GONE;
                } else {
                    iv_skip.visibility = View.VISIBLE;
                    rl_progress.visibility = View.VISIBLE;
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    inner class MyPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return imageViews.size + 1
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view: View
            if (position < imageViews.size) {
                view = imageViews[position]
                container.addView(view)
            } else {
                view = entryView
                container.addView(view)
            }
            return view
        }
    }

    private fun initListener() {
        //获取树形视图，每次页面布局完成时会调用，获取点间的距离
        iv_white_point.viewTreeObserver
            .addOnGlobalLayoutListener(MyOnGlobalLayoutListener())
        //页面改变时
        viewpage.addOnPageChangeListener(MyOnPageChangeListener())
        entryView.btn_home.setOnClickListener {
            NavigationUtil.gotoHome(this)
            finish()
        }
    }

    inner class MyOnGlobalLayoutListener : OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            //默认会调用俩次，只需要一次，第一次进入就移除
            iv_white_point.viewTreeObserver.removeGlobalOnLayoutListener(this@MyOnGlobalLayoutListener)
            //间距 = 第1个点距离左边距离 - 第0个点距离左边距离
            leftMax = ll_point_group.getChildAt(1).left - ll_point_group.getChildAt(0).left
        }
    }

    inner class MyOnPageChangeListener : OnPageChangeListener {
        /**
         * 当页面滑动回调会调用此方法
         *
         * @param position             当前页面位置
         * @param positionOffset       当前页面滑动百分比
         * @param positionOffsetPixels 滑动的像素数
         */
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            //红点移动的距离 = ViewPager页面的百分比* 间距
            //坐标 = 起始位置 + 红点移动的距离；
            val leftMargin = (position * leftMax + positionOffset * leftMax).toInt()
            //获取控件iv_red_point
            val params = iv_white_point.layoutParams as RelativeLayout.LayoutParams
            //设置iv_red_point属性
            params.leftMargin = leftMargin
            iv_white_point.layoutParams = params
        }

        /**
         * 页面被选中，回调此方法
         *
         * @param position 被选中的页面位置
         * 此作用是最后一张图片显示button
         */
        override fun onPageSelected(position: Int) {}

        /**
         * 当页面滑动状态改变时候
         *
         * @param state
         */
        override fun onPageScrollStateChanged(state: Int) {}
    }
}