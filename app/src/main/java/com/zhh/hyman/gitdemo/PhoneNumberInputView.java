package com.zhh.hyman.gitdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 */

public class PhoneNumberInputView extends LinearLayout {

    private static final String TAG = "PhoneNumberInputView";
    private static final int COLUMN_COUNT_MIN_VALUE = 3;

    private int gridViewMarginTop = 83;

    private int numberItemWidth = 120;
    private int numberItemHeight = 120;

    private int rowInterval = 50;
    private int columnInterval = 40;

    private int columnCount = 3;

    private int itemCount;

    private int itemWidth = numberItemWidth + rowInterval;
    private int itemHeight = numberItemHeight + columnInterval;

    private int width;
    private int height;

    private PhoneNumberCommandListener phoneNumberCommandListener;
    private PhoneNumberListener phoneNumberListener;

    private Context context;

    private GridView gridView;

    private PhoneNumberInputAdapter phoneNumberInputAdapter;

    private ArrayList<PhoneNumberBean> phoneNumberBeans;
    private TextView numberTextView;

    public PhoneNumberInputView(Context context, int columnCount, PhoneNumberCommandListener phoneNumberCommandListener) {
        super(context);
        this.context = context;
        if (columnCount > COLUMN_COUNT_MIN_VALUE) {
            this.columnCount = columnCount;
        }
        this.phoneNumberCommandListener = phoneNumberCommandListener;
        initView();
    }

    public PhoneNumberInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initData(attrs);
        initView();
    }

    public PhoneNumberInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initData(attrs);
        initView();
    }

    private void initData(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PhoneNumberInputView);

        gridViewMarginTop = typedArray.getInt(R.styleable.PhoneNumberInputView_gridViewMarginTop, 83);
        numberItemWidth = typedArray.getInt(R.styleable.PhoneNumberInputView_numberItemWidth, 120);
        numberItemHeight = typedArray.getInt(R.styleable.PhoneNumberInputView_numberItemHeight, 120);
        rowInterval = typedArray.getInt(R.styleable.PhoneNumberInputView_rowInterval, 50);
        columnInterval = typedArray.getInt(R.styleable.PhoneNumberInputView_columnInterval, 40);
        columnCount = typedArray.getInt(R.styleable.PhoneNumberInputView_columnCount, 3);

        typedArray.recycle();

        itemWidth = numberItemWidth + rowInterval;
        itemHeight = numberItemHeight + columnInterval;
    }

    private void initView() {
        this.setOrientation(VERTICAL);
        initListener();

        initPhoneNumberBeans();
        itemCount = phoneNumberBeans.size();

        initTotalWidthAndHeight();

        View inputView = LayoutInflater.from(context).inflate(R.layout.phone_number_input, null, false);
        this.addView(inputView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        RelativeLayout headLayout = (RelativeLayout) inputView.findViewById(R.id.phone_number_head_root);
        ViewGroup.LayoutParams layoutParams = headLayout.getLayoutParams();
        layoutParams.width = width - rowInterval;
        Log.i(TAG, "layoutParams.width = " + layoutParams.width);
        headLayout.setLayoutParams(layoutParams);

        numberTextView = (TextView) inputView.findViewById(R.id.phone_number_head_number_txt);
        gridView = (GridView) inputView.findViewById(R.id.phone_number_grid_view);
        gridView.setNumColumns(columnCount);

        gridViewMarginTop -= columnInterval / 2;

        LinearLayout.LayoutParams gridLayoutParams = (LinearLayout.LayoutParams) gridView.getLayoutParams();
        gridLayoutParams.topMargin = gridViewMarginTop;
        gridView.setLayoutParams(gridLayoutParams);

        phoneNumberInputAdapter = new PhoneNumberInputAdapter(context, phoneNumberBeans, phoneNumberListener,
                itemWidth, itemHeight, numberItemWidth, numberItemHeight);
        gridView.setAdapter(phoneNumberInputAdapter);
    }

    private void initTotalWidthAndHeight() {
        width = itemWidth * columnCount;
        //        int rowCount = itemCount / columnCount + (itemCount % columnCount) == 0 ? 0 : 1;
        //        height = itemHeight * rowCount;

        //        Log.i(TAG, "width = " + width + ",height = " + height);
        Log.i(TAG, "width = " + width);

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            layoutParams.width = width;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        this.setLayoutParams(layoutParams);
    }

    private void initPhoneNumberBeans() {
        phoneNumberBeans = new ArrayList<>();
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "1", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "2", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "3", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "4", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "5", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "6", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "7", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "8", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "9", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_PICTURE_COMMAND, "",
                R.drawable.action_delete, PhoneNumberBean.ACTION_PICTURE_COMMAND_DELETE));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_NUMBER, "0", 0, 0));
        phoneNumberBeans.add(new PhoneNumberBean(PhoneNumberBean.TYPE_COMMAND, "发送", 0,
                PhoneNumberBean.ACTION_COMMAND_SEND));
    }


    private void initListener() {
        Log.i(TAG, "gridViewMarginTop = " + gridViewMarginTop + ",numberItemWidth = " + numberItemWidth
                + ",numberItemHeight = " + numberItemHeight + ",rowInterval = " + rowInterval
                + ",columnInterval = " + columnInterval + ",columnCount " + columnCount);

        Log.i(TAG, "itemWidth = " + itemWidth + ",itemHeight = " + itemHeight);

        if (phoneNumberCommandListener == null) {
            phoneNumberCommandListener = new PhoneNumberCommandListener() {
                @Override
                public void send(String number) {
                    Toast.makeText(context, "send : " + number, Toast.LENGTH_SHORT).show();
                }
            };
        }
        phoneNumberListener = new PhoneNumberListener() {
            @Override
            public void addNumber(String currentNumber) {
                Log.i(TAG, "addNumber = " + currentNumber);
                addPhoneNumber(currentNumber);
            }

            @Override
            public void deleteNumber() {
                Log.i(TAG, "deleteNumber");
                deletePhoneNumber();
            }

            @Override
            public void send() {
                Log.i(TAG, "send");
                sendPhoneNumber();
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void addPhoneNumber(String needAddPhoneNumber) {
        String trim = numberTextView.getText().toString().trim();
        Log.i(TAG, "addPhoneNumber , trim = " + trim);
        int length = trim.length();
        if (length >= 13) {
            Log.i(TAG, "addPhoneNumber , phone number length is enough , can't add");
            return;
        }

        if (length == 3 || length == 8) {
            trim += " ";
        }
        trim += needAddPhoneNumber;
        Log.i(TAG, "addPhoneNumber after, " + trim);
        numberTextView.setText(trim);
    }

    private void deletePhoneNumber() {
        String trim = numberTextView.getText().toString().trim();
        Log.i(TAG, "deletePhoneNumber , trim = " + trim);
        int length = trim.length();
        if (length <= 0) {
            Log.i(TAG, "deletePhoneNumber , phone number length is 0 , can't delete");
            return;
        }

        int endIndex = length;
        if (length == 5 || length == 10) {
            endIndex -= 2;
        } else {
            endIndex--;
        }
        String substring = trim.substring(0, endIndex);
        Log.i(TAG, "deletePhoneNumber after, " + substring);
        numberTextView.setText(substring);
    }

    private void sendPhoneNumber() {
        String trim = numberTextView.getText().toString().trim();
        Log.i(TAG, "send , trim = " + trim);
        if (trim.length() < 13) {
            // TODO: 2017/6/19
            Toast.makeText(context, "电话号码长度不够", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "phone number length is wrong");
            return;
        }
        if (phoneNumberCommandListener != null) {
            String replace = trim.replace(" ", "");
            Log.i(TAG, "send , replace = " + replace);
            phoneNumberCommandListener.send(replace);
        }
    }
}
