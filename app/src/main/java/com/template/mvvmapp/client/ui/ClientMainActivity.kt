package com.template.mvvmapp.client.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.template.mvvmapp.R
import com.template.mvvmapp.app.Constants
import com.template.mvvmapp.base.BaseActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import com.template.mvvmapp.event.ReplaceFragmentEvent
import android.widget.Toast
import kotlin.system.exitProcess


class ClientMainActivity : BaseActivity() {

    private var currentPos = -1

    private var currentFragment: Fragment = OneFragment()

    /**
     * 最后按下的时间
     */
    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_main)
    }

    override fun initViews() {
        super.initViews()
        selectItem(Constants.FRAGMENT_ONE, false)
    }

    override fun initEvents() {
        super.initEvents()
        LiveEventBus
            .get(ReplaceFragmentEvent::class.java)
            .observe(this, {
                selectItem(it.index, it.needPop)
            })
    }

    private fun selectItem(pos: Int, needPop: Boolean) {
        //点击的正是当前正在显示的，直接返回
        if (currentPos == pos) return
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction().setCustomAnimations(
            R.animator.card_flip_right_in,
            R.animator.card_flip_left_out,
            R.animator.card_flip_left_in,
            R.animator.card_flip_right_out
        )
        if (needPop) {
            transaction.setCustomAnimations(
                R.animator.card_flip_left_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_right_in,
                R.animator.card_flip_left_out,
            )
        }
        transaction.hide(currentFragment)
        currentPos = pos
        val fragment = manager.findFragmentByTag(getTag(pos))
        //通过findFragmentByTag判断是否已存在目标fragment，若存在直接show，否则去add
        if (fragment != null) {
            currentFragment = fragment
            transaction.show(fragment)
        } else {
            transaction.add(R.id.fl_container, getFragment(pos), getTag(pos))
        }
        if (currentPos==0){
            manager.fragments.map {
                if (it != fragment){
                    transaction.remove(it)
                }
            }
        }
        transaction.commitAllowingStateLoss()
    }

    private fun getFragment(pos: Int): Fragment {
        when (pos) {
            Constants.FRAGMENT_ONE -> currentFragment = OneFragment()
            Constants.FRAGMENT_TWO -> currentFragment =  TwoFragment()
            Constants.FRAGMENT_THREE -> currentFragment = ThreeFragment()
            else -> currentFragment = OneFragment()
        }
        return currentFragment
    }

    private fun getTag(pos: Int): String? {
        return "fg_tag_$pos"
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // 判断按下的是不是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() === KeyEvent.ACTION_DOWN) {
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