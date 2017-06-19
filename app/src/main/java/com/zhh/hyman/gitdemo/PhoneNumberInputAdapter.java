package com.zhh.hyman.gitdemo;

import android.content.Context;

import com.zhy.adapter.abslistview.MultiItemTypeAdapter;

import java.util.List;

/**
 *
 */

public class PhoneNumberInputAdapter extends MultiItemTypeAdapter<PhoneNumberBean> {

    public PhoneNumberInputAdapter(Context context, List<PhoneNumberBean> datas,
                                   PhoneNumberListener phoneNumberListener, int itemRealWidth,
                                   int itemRealHeight, int itemWidth,int itemHeight) {
        super(context, datas);
        addItemViewDelegate(new NumberItemDelegate(phoneNumberListener,itemRealWidth,itemRealHeight,itemWidth,itemHeight));
        addItemViewDelegate(new CommandItemDelegate(phoneNumberListener,itemRealWidth,itemRealHeight,itemWidth,itemHeight));
        addItemViewDelegate(new PictureCommandItemDelegate(phoneNumberListener,itemRealWidth,itemRealHeight,itemWidth,itemHeight));
    }
}
