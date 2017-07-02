package com.ixzus.rxapp

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), IToolbar, IActivity {
    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        toolbar("首页", true)
        dialog.setOnClickListener {
            //            val fdialog = FactoryDialog.create(fragmentManager)
//            fdialog.setLayoutRes(R.layout.dialog_net)
//                    .setDialogViewListener(object : BaseDialog.DialogViewListener {
//                        override fun dismiss() {
//                        }
//
//                        override fun bindView(v: View?) {
//                            (v?.find<ImageView>(R.id.iv) as ImageView).setOnClickListener { fdialog.dismiss() }
//                        }
//
//                    })
//                    .setmOutsideCancel(true)
//                    .setmBackCancel(true)
//                    .setmTag("dialogtwo")
//                    .show()
            val bean = getIntent().getParcelableExtra<ActivityBean>("ActivityBean")
            bean.dialog.show()
        }
    }

    override fun initData() {
    }

    override fun toolbar(backText: String?, isBack: Boolean, title: String?) {
        if (isBack) {
            toolbar_back.visibility = View.VISIBLE
            toolbar_line.visibility = View.VISIBLE
        }
        backText?.let { toolbar_back_text.text = backText }
        title?.let { toolbar_title.text = title }
    }

}
