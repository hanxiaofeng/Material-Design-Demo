package com.wangkeke.daynightdemo;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ZhiHuActivity extends AppCompatActivity {

    /**
     * 日间模式
     */
    private final static int DAY_THEME = 1;

    /**
     * 夜间模式
     */
    private final static int NIGHT_THEME = 2;

    private Button btnSwitch;

    private TextView tvColor;

    private RelativeLayout rootLayout;

    private ImageView imageView;

    private int width;
    private int height;
    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu);
        getInitData();
        initView();
    }

    /**
     * 获取屏幕宽高和状态栏高度
     */
    private void getInitData() {
        WindowManager wm = this.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
    }

    private void initView() {
        btnSwitch = (Button) findViewById(R.id.btn_switch);
        rootLayout = (RelativeLayout) findViewById(R.id.activity_zhi_hu);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvColor = (TextView) findViewById(R.id.tv_color);
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchDayNightTheme();
            }
        });

        //加载当前的主题
        if (getCurrentTheme() == DAY_THEME) {
//            setMyTheme(DAY_THEME);
            setDayThemeInfo();
        } else if (getCurrentTheme() == NIGHT_THEME) {
//            setMyTheme(NIGHT_THEME);
            setNightThemeInfo();
        } else {
//            setMyTheme(DAY_THEME);
            setDayThemeInfo();
        }
    }

    /**
     * 设置模式
     *
     * @param dayTheme
     */
    private void setMyTheme(int dayTheme) {
        switch (dayTheme) {
            case DAY_THEME:
                setDayTheme();
                break;
            case NIGHT_THEME:
                setNightTheme();
                break;
            default:
                setDayTheme();
                break;
        }
    }

    /**
     * 设置夜间模式
     */
    private void setNightTheme() {
        final ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(width, height-statusBarHeight));
        Bitmap bitmap = loadBitmapFromView(rootLayout);
        imageView.setImageBitmap(bitmap);
        rootLayout.addView(imageView);
        //设置新主题
        setNightThemeInfo();
        int colorA = Color.parseColor("#ffffff");
        int colorB = Color.parseColor("#333444");
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(imageView, "backgroundColor", colorA, colorB);
        objectAnimator.setDuration(800);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rootLayout.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    boolean flag = true;

    /**
     * 设置夜间模式具体代码
     */
    private void setNightThemeInfo() {
        rootLayout.setBackgroundColor(Color.parseColor("#333444"));
        tvColor.setTextColor(Color.parseColor("#666666"));
        imageView.setImageResource(R.mipmap.night_icon);
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f, 1f);
        animator.setDuration(1200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int value = (int) (currentValue*100);
                Log.e("zhihu", "onAnimationUpdate: "+value);
                if(value < 5 && flag)
                {
                    imageView.setImageResource(R.mipmap.night_icon);
                    flag = !flag;
                }
            }
        });
        animator.start();*/
    }

    /**
     * 设置日渐模式具体代码
     */
    private void setDayThemeInfo() {
        rootLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvColor.setTextColor(Color.parseColor("#222222"));
        imageView.setImageResource(R.mipmap.day_icom);
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f, 1f);
        animator.setDuration(1200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int value = (int) (currentValue*100);
                if(value < 5)
                {
                    imageView.setImageResource(R.mipmap.day_icom);
                }
            }
        });
        animator.start();*/
    }

    /**
     * 设置日间模式
     */
    private void setDayTheme() {
        final ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(width, height-statusBarHeight));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Bitmap bitmap = loadBitmapFromView(rootLayout);
        imageView.setImageBitmap(bitmap);
        rootLayout.addView(imageView);
        //设置新主题
        setDayThemeInfo();

        int colorA = Color.parseColor("#333444");
        int colorB = Color.parseColor("#ffffff");
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(imageView, "backgroundColor", colorA, colorB);
        objectAnimator.setDuration(800);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rootLayout.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    /**
     * 测试方法
     * @param imageView
     */
    private void testDay(final ImageView imageView) {
        //设置imageView渐隐动画
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("zhihu", "zhihu -- onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("zhihu", "zhihu -- onAnimationEnd");
                rootLayout.removeView(imageView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(animation);
    }

    /**
     * 获取view截图对应的bitmap
     * @param v
     * @return
     */
    public Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(width, height-statusBarHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    /**
     * 切换日渐模式或夜间模式
     */
    private void switchDayNightTheme() {
        int curMode = getCurrentTheme();
        switch (curMode) {
            case DAY_THEME:
                saveCurrentTheme(NIGHT_THEME);
                setMyTheme(NIGHT_THEME);
                break;
            case NIGHT_THEME:
                saveCurrentTheme(DAY_THEME);
                setMyTheme(DAY_THEME);
                break;
            default:
                saveCurrentTheme(DAY_THEME);
                setMyTheme(DAY_THEME);
                break;
        }
    }


    /**
     * 保存当前模式
     *
     * @param mode
     */
    private void saveCurrentTheme(int mode) {
        SharedPreferences preferences = getSharedPreferences("AppTheme", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("mode", mode);
        editor.apply();
    }


    /**
     * 获取当前模式
     *
     * @m mode
     */
    private int getCurrentTheme() {
        SharedPreferences preferences = getSharedPreferences("AppTheme", Context.MODE_PRIVATE);
        int currentMode = preferences.getInt("mode", 0);
        return currentMode;
    }


}
