package com.zhh.hyman.gitdemo;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;


/**
 *
 */

public abstract class PhoneNumberItemDelegate implements ItemViewDelegate<PhoneNumberBean> {

    public static final String TAG = "PhoneNumberItemDelegate";

    protected int itemRealWidth;
    protected int itemRealHeight;
    protected int itemWidth;
    protected int itemHeight;

    protected PhoneNumberListener phoneNumberListener;

    protected View mainView;

    public PhoneNumberItemDelegate(PhoneNumberListener phoneNumberListener, int itemRealWidth, int itemRealHeight, int itemWidth, int itemHeight) {
        this.itemRealWidth = itemRealWidth;
        this.itemRealHeight = itemRealHeight;
        this.itemWidth = itemWidth;
        this.itemHeight = itemHeight;
        this.phoneNumberListener = phoneNumberListener;
    }

    protected void setPhoneNumberListener(PhoneNumberListener phoneNumberListener) {
        this.phoneNumberListener = phoneNumberListener;
    }

    protected abstract int getMainViewLayoutId();

    protected abstract void onClickMainView(PhoneNumberBean phoneNumberBean, int position);

    @Override
    public void convert(ViewHolder holder, final PhoneNumberBean phoneNumberBean, final int position) {
        updateViewSize(holder.getConvertView(), itemRealWidth, itemRealHeight);

        int mainViewLayoutId = getMainViewLayoutId();
        Log.i(TAG,"mainViewLayoutId = " + mainViewLayoutId);
        mainView = holder.getView(mainViewLayoutId);
        Log.i(TAG,"mainView = " + mainView);

        updateViewSize(mainView, itemWidth, itemHeight);
        if (PhoneNumberBean.TYPE_PICTURE_COMMAND == phoneNumberBean.getType()) {
            holder.setImageResource(mainViewLayoutId, phoneNumberBean.getImageResourceId());
        } else {
            holder.setText(mainViewLayoutId, phoneNumberBean.getText());
        }

        holder.setOnClickListener(mainViewLayoutId, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMainView(phoneNumberBean, position);
            }
        });

        if (mainView != null && position == 0) {
            mainView.requestFocus();
        }
    }

    protected void updateViewSize(View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }
}
