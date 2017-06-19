package com.zhh.hyman.gitdemo;

import android.util.Log;

/**
 * 命令
 */

public class CommandItemDelegate extends PhoneNumberItemDelegate {

    private static final String TAG = "Command";

    public CommandItemDelegate(PhoneNumberListener phoneNumberListener, int itemRealWidth,
                               int itemRealHeight, int itemWidth,int itemHeight) {
        super(phoneNumberListener,itemRealWidth,itemRealHeight,itemWidth,itemHeight);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.phone_number_item_command;
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.id.phone_number_item_command_button;
    }

    @Override
    protected void onClickMainView(PhoneNumberBean phoneNumberBean, int position) {
        int action = phoneNumberBean.getAction();
        Log.i(TAG, "action = " + action);
        if (PhoneNumberBean.ACTION_COMMAND_SEND == action){
            phoneNumberListener.send();
        }
    }

    @Override
    public boolean isForViewType(PhoneNumberBean item, int position) {
        return item.getType() == PhoneNumberBean.TYPE_COMMAND;
    }
}
