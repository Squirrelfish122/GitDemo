package com.zhh.hyman.gitdemo;

import android.util.Log;

/**
 * 图片
 */

public class PictureCommandItemDelegate extends PhoneNumberItemDelegate {

    private static final String TAG = "PictureCommand";

    public PictureCommandItemDelegate(PhoneNumberListener phoneNumberListener, int itemRealWidth,
                                      int itemRealHeight, int itemWidth,int itemHeight) {
        super(phoneNumberListener,itemRealWidth,itemRealHeight,itemWidth,itemHeight);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.phone_number_item_picture_command;
    }

    @Override
    public boolean isForViewType(PhoneNumberBean item, int position) {
        return item.getType() == PhoneNumberBean.TYPE_PICTURE_COMMAND;
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.id.phone_number_item_picture_command_button;
    }

    @Override
    protected void onClickMainView(PhoneNumberBean phoneNumberBean, int position) {
        int action = phoneNumberBean.getAction();
        Log.i(TAG,"action = " + action);
        if (PhoneNumberBean.ACTION_PICTURE_COMMAND_DELETE == action){
            phoneNumberListener.deleteNumber();
        }
    }
}
