package com.zhh.hyman.gitdemo;

/**
 * 数字
 */

public class NumberItemDelegate extends PhoneNumberItemDelegate {

    private String TAG = "NumberItemDelegate";

    public NumberItemDelegate(PhoneNumberListener phoneNumberListener, int itemRealWidth,
                              int itemRealHeight, int itemWidth,int itemHeight) {
        super(phoneNumberListener,itemRealWidth,itemRealHeight,itemWidth,itemHeight);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.phone_number_item_number;
    }

    @Override
    public boolean isForViewType(PhoneNumberBean item, int position) {
        return item.getType() == PhoneNumberBean.TYPE_NUMBER;
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.id.phone_number_item_number_button;
    }

    @Override
    protected void onClickMainView(PhoneNumberBean phoneNumberBean, int position) {
        phoneNumberListener.addNumber(phoneNumberBean.getText());
    }
}
