package com.template.mvvmapp.client.ui

import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.Toast
import com.template.base.BaseActivity
import com.template.mvvmapp.R
import com.template.mvvmapp.app.AppConfig
import com.template.mvvmapp.app.NavigationUtil
import com.template.mvvmapp.view.Dialogs
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlin.system.exitProcess

/**
 * app-启动页面
 */
class LauncherActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_launcher

    override fun initViews() {
        super.initViews()
        startAnimation()
    }

    private fun startAnimation() {
        val sa = ScaleAnimation(
            0.8f, 1.0f, 0.8f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        sa.duration = 1500
        sa.fillAfter = true

        val ass = AnimationSet(false)//动画对象
        ass.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(Ani: Animation) {
            }

            override fun onAnimationRepeat(ani: Animation) {
            }

            override fun onAnimationEnd(ani: Animation) {
                goto()
            }
        })
        ass.addAnimation(sa)
        logo.startAnimation(ass)//启动动画
    }

    private fun goto() {
        if (AppConfig.isAgreeUserProtocol != true) {
            Dialogs.showMessageDialog(
                context = this,
                title = "用户协议与隐私政策",
                msg = getProtocolMsgSpan(),
                htmlEnable = false,
                positiveText = "同意",
                positiveOnClickListener = { dialog, _ ->
                    AppConfig.isAgreeUserProtocol = true
                    dialog.dismiss()
                    goto()
                },
                negativeText = "不同意并退出",
                negativeOnClickListener = { dialog, _ ->
                    dialog.dismiss()
                    this.finish()
                },
                onCloseClickListener = { _, _ ->
                    this.finish()
                }
            )
            return
        }

        val intent = if (AppConfig.isFirstLaunch!!) {
            AppConfig.isFirstLaunch = false
            Intent(this, GuideActivity::class.java)
        } else {
            Intent(this, FragmentMainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

    private fun getProtocolMsgSpan(): SpannableString {
        val spanStr =
            SpannableString("我们非常重视您的个人信息和隐私保护，在您使用产品前，请务必仔细阅读《用户协议》和《隐私政策》，并充分理解协议条款内容。\n\n如您同意《用户协议》和《隐私政策》，请点击“同意”按钮，开始使用我们的产品和服务！")
        //设置文字的单击事件
        spanStr.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                NavigationUtil
                NavigationUtil.gotoWebView(
                    this@LauncherActivity,
                    "xxx",
                    "用户协议"
                )
            }
        }, 33, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        //设置文字的前景色
        spanStr.setSpan(
            ForegroundColorSpan(Color.parseColor("#58AAC8")),
            33,
            38,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //设置文字的单击事件
        spanStr.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                NavigationUtil.gotoWebView(
                    this@LauncherActivity,
                    "http://www.baidu.com",
                    "隐私条款",
                )
            }
        }, 40, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        //设置文字的前景色
        spanStr.setSpan(
            ForegroundColorSpan(Color.parseColor("#58AAC8")),
            40,
            46,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spanStr
    }

    /**
     * 最后按下的时间
     */
    private var exitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                exitProcess(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
