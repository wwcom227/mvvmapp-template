package com.template.mvvmapp.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Html
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.template.mvvmapp.R
import com.template.mvvmapp.widget.NumberPickerView
import mehdi.sakout.fancybuttons.FancyButton


object Dialogs {

    /**
     * 显示一个自定义的信息弹出框
     *
     * @param context 上下文
     * @param title   标题
     * @param msg     信息
     * @param cancelable 是否点击旁边可取消
     * @param showClose 是否显示关闭按钮
     * @param positiveText            肯定的文字描述
     * @param positiveOnClickListener 肯定的监听
     * @param negativeText            否定的文字描述
     * @param negativeOnClickListener 否定的监听
     */
    fun showMessageDialog(
        context: Context,
        iconRes: Int? = null,
        title: CharSequence?,
        msg: CharSequence?,
        cancelable: Boolean = false,
        showClose: Boolean = false,
        htmlEnable: Boolean = true,
        onCloseClickListener: DialogInterface.OnClickListener? = null,
        positiveStressStyle: Boolean = false,
        positiveText: CharSequence?,
        positiveOnClickListener: DialogInterface.OnClickListener?,
        negativeText: CharSequence? = null,
        negativeOnClickListener: DialogInterface.OnClickListener? = null
    ): Dialog {
        val dialog = Dialog(context, R.style.AlertDialogStyle)
        val window = dialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val inflater = (context as Activity).layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_normal, null)
        val ivIcon = view.findViewById<ImageView>(R.id.iv_icon)
        if (iconRes != null) {
            ivIcon.setImageResource(iconRes)
        } else {
            ivIcon.visibility = View.GONE
        }
        if (TextUtils.isEmpty(title)) {
            val titleView = view.findViewById<View>(R.id.ll_title)
            titleView.visibility = View.GONE
        } else {
            val tvTitle = view.findViewById<TextView>(R.id.tv_title)
            tvTitle.text = title
        }
        val tvMsg = view.findViewById<TextView>(R.id.tv_msg)
        if (!TextUtils.isEmpty(msg)) {
            if (htmlEnable) {
                tvMsg.text = Html.fromHtml(msg.toString(), Html.FROM_HTML_MODE_COMPACT)
            } else {
                tvMsg.text = msg
            }
            tvMsg.movementMethod = LinkMovementMethod.getInstance()
        } else {
            tvMsg.visibility = View.GONE
        }
        val ivClose = view.findViewById<ImageView>(R.id.iv_close)
        if (showClose) {
            ivClose.visibility = View.VISIBLE
            ivClose.setOnClickListener {
                onCloseClickListener?.onClick(dialog, 1)
                dialog.dismiss()
            }
        } else {
            ivClose.visibility = View.GONE
        }

        val btnConfirm = view.findViewById<FancyButton>(R.id.btn_confirm)
        btnConfirm.setOnClickListener {
            if (positiveOnClickListener != null) {
                positiveOnClickListener.onClick(dialog, 1)
            } else {
                dialog.dismiss()
            }
        }
        if (positiveText != null) {
            btnConfirm.setText(positiveText.toString())
            if (positiveStressStyle) {
                btnConfirm.setBackgroundColor(Color.parseColor("#FF716E"))
            }
        } else {
            btnConfirm.visibility = View.GONE
        }
        val btnCancel = view.findViewById<FancyButton>(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            if (negativeOnClickListener != null) {
                negativeOnClickListener.onClick(dialog, 1)
            } else {
                dialog.dismiss()
            }
        }
        if (negativeText != null) {
            btnCancel.setText(negativeText.toString())
        } else {
            btnCancel.visibility = View.GONE
        }

        dialog.setContentView(view)
        dialog.setCancelable(cancelable)
        dialog.show()
        return dialog
    }

    /**
     * 显示一个自定义的信息冒泡弹出框
     *
     * @param context 上下文
     * @param title   标题
     * @param msg     信息
     * @param cancelable 是否点击旁边可取消
     * @param showClose 是否显示关闭按钮
     * @param positiveText            肯定的文字描述
     * @param positiveOnClickListener 肯定的监听
     * @param negativeText            否定的文字描述
     * @param negativeOnClickListener 否定的监听
     */
    fun showMessagePopDialog(
        context: Context,
        iconRes: Int? = null,
        title: CharSequence?,
        msg: CharSequence?,
        cancelable: Boolean = false,
        showClose: Boolean = false,
        htmlEnable: Boolean = true,
        onCloseClickListener: DialogInterface.OnClickListener? = null,
        positiveText: CharSequence?,
        positiveOnClickListener: DialogInterface.OnClickListener?,
        negativeText: CharSequence? = null,
        negativeOnClickListener: DialogInterface.OnClickListener? = null
    ): Dialog {
        val dialog = Dialog(context, R.style.dialog_pop)
        val inflater = (context as Activity).layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_pop, null)
        dialog.setContentView(view)
        val window = dialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setGravity(Gravity.BOTTOM)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setWindowAnimations(R.style.dialog_animation_pop_up)
        dialog.setCancelable(cancelable)

        if (TextUtils.isEmpty(title)) {
            val titleView = view.findViewById<View>(R.id.ll_title)
            titleView.visibility = View.GONE
        } else {
            val tvTitle = view.findViewById<TextView>(R.id.tv_title)
            tvTitle.text = title
        }
        val tvMsg = view.findViewById<TextView>(R.id.tv_msg)
        if (!TextUtils.isEmpty(msg)) {
            if (htmlEnable) {
                tvMsg.text = Html.fromHtml(msg.toString(), Html.FROM_HTML_MODE_COMPACT)
            } else {
                tvMsg.text = msg
            }
            tvMsg.movementMethod = LinkMovementMethod.getInstance()
        } else {
            tvMsg.visibility = View.GONE
        }
        val ivClose = view.findViewById<ImageView>(R.id.iv_close)
        if (showClose) {
            ivClose.visibility = View.VISIBLE
            ivClose.setOnClickListener {
                onCloseClickListener?.onClick(dialog, 1)
                dialog.dismiss()
            }
        } else {
            ivClose.visibility = View.GONE
        }

        val btnConfirm = view.findViewById<FancyButton>(R.id.btn_confirm)
        btnConfirm.setOnClickListener {
            if (positiveOnClickListener != null) {
                positiveOnClickListener.onClick(dialog, 1)
            } else {
                dialog.dismiss()
            }
        }
        if (positiveText != null) {
            btnConfirm.setText(positiveText.toString())
            if (iconRes != null) {
                btnConfirm.setIconPosition(FancyButton.POSITION_LEFT)
                btnConfirm.setIconResource(iconRes)
            }
        } else {
            btnConfirm.visibility = View.GONE
        }
        val btnCancel = view.findViewById<FancyButton>(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            if (negativeOnClickListener != null) {
                negativeOnClickListener.onClick(dialog, 1)
            } else {
                dialog.dismiss()
            }
        }
        if (negativeText != null) {
            btnCancel.setText(negativeText.toString())
        } else {
            btnCancel.visibility = View.GONE
        }

        dialog.show()
        return dialog
    }


    /**
     * 显示一个自定义的选择冒泡弹出框
     *
     * @param context 上下文
     * @param title   标题
     * @param msg     信息
     * @param cancelable 是否点击旁边可取消
     * @param showClose 是否显示关闭按钮
     * @param positiveText            肯定的文字描述
     * @param positiveOnClickListener 肯定的监听
     * @param negativeText            否定的文字描述
     * @param negativeOnClickListener 否定的监听
     */
    fun showPickerPopDialog(
        context: Context,
        iconRes: Int? = null,
        title: CharSequence?,
        pickerData: Array<String>,
        pickedValue: Int? = 0,
        cancelable: Boolean = false,
        showClose: Boolean = false,
        onCloseClickListener: DialogInterface.OnClickListener? = null,
        positiveText: CharSequence?,
        positiveOnClickListener: DialogInterface.OnClickListener?,
        negativeText: CharSequence? = null,
        negativeOnClickListener: DialogInterface.OnClickListener? = null
    ): Dialog {
        val dialog = Dialog(context, R.style.dialog_pop)
        val inflater = (context as Activity).layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_pop_picker, null)
        dialog.setContentView(view)
        val window = dialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setGravity(Gravity.BOTTOM)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setWindowAnimations(R.style.dialog_animation_pop_up)
        dialog.setCancelable(cancelable)

        if (TextUtils.isEmpty(title)) {
            val titleView = view.findViewById<View>(R.id.ll_title)
            titleView.visibility = View.GONE
        } else {
            val tvTitle = view.findViewById<TextView>(R.id.tv_title)
            tvTitle.text = title
        }
        val pickerView = view.findViewById<NumberPickerView>(R.id.picker)
        pickerView.refreshByNewDisplayedValues(pickerData)
        if (pickedValue != null) {
            pickerView.value = pickedValue
        }
        val ivClose = view.findViewById<ImageView>(R.id.iv_close)
        if (showClose) {
            ivClose.visibility = View.VISIBLE
            ivClose.setOnClickListener {
                onCloseClickListener?.onClick(dialog, 1)
                dialog.dismiss()
            }
        } else {
            ivClose.visibility = View.GONE
        }

        val btnConfirm = view.findViewById<FancyButton>(R.id.btn_confirm)
        btnConfirm.setOnClickListener {
            if (positiveOnClickListener != null) {
                positiveOnClickListener.onClick(dialog, pickerView.value)
            } else {
                dialog.dismiss()
            }
        }
        if (positiveText != null) {
            btnConfirm.setText(positiveText.toString())
            if (iconRes != null) {
                btnConfirm.setIconPosition(FancyButton.POSITION_LEFT)
                btnConfirm.setIconResource(iconRes)
            }
        } else {
            btnConfirm.visibility = View.GONE
        }
        val btnCancel = view.findViewById<FancyButton>(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            if (negativeOnClickListener != null) {
                negativeOnClickListener.onClick(dialog, 1)
            } else {
                dialog.dismiss()
            }
        }
        if (negativeText != null) {
            btnCancel.setText(negativeText.toString())
        } else {
            btnCancel.visibility = View.GONE
        }

        dialog.show()
        return dialog
    }
}