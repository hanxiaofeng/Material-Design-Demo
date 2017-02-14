package com.wangkeke.mdbehaviordemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetActivity extends AppCompatActivity {

    @Bind(R.id.btn_show)
    Button btnShow;
    @Bind(R.id.btn_bottom)
    Button btnBottom;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_layout)
    LinearLayout tabLayout;
    private MyBottomSheetDialog mBottomSheetDialog;
    private BottomSheetBehavior<View> mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createBottomSheetDialog();
        setBehaviorCallback();


    }

    @OnClick({R.id.btn_show, R.id.btn_bottom})
    public void dealClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                if (!mBottomSheetDialog.isShowing()) {
                    mBottomSheetDialog.show();
                } else {
                    mBottomSheetDialog.dismiss();
                }
                break;
            case R.id.btn_bottom:
                // 拿到这个tab_layout对应的BottomSheetBehavior
                mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
        }
    }

    private void createBottomSheetDialog() {

        WindowManager wm = this.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();

        //构造函数的第二个参数可以设置BottomSheetDialog的主题样式
//        mBottomSheetDialog = new BottomSheetDialog(this,R.style.MyBottomDialog);
        mBottomSheetDialog = new MyBottomSheetDialog(this,this);
        //导入底部reycler布局
        View view = LayoutInflater.from(this).inflate(R.layout.recycler_view, null, false);
        mBottomSheetDialog.setContentView(view);

        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        //设置默认弹出高度为屏幕的0.4倍
        mBehavior.setPeekHeight((int) (0.4 * height));

        //设置点击dialog外部不消失
        mBottomSheetDialog.setCanceledOnTouchOutside(false);

        //RecyclerView相关设置
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyRecyclerAdapter(this));
    }

    /**
     * 解决第二次显示不谈出BottomSheetDialog的问题
     */
    private void setBehaviorCallback() {
        View view = mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }
}
