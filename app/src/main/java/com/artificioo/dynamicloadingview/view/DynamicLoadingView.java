package com.artificioo.dynamicloadingview.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.artificioo.dynamicloadingview.R;

public class DynamicLoadingView extends LinearLayout {
    private int[] ICONS;
    private int[] COLORS;

    public DynamicLoadingView(Context context) {
        super(context);
        init(null, 0);
    }

    public DynamicLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DynamicLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyleAttr) {
        inflate(getContext(), R.layout.view_loading, this);
    }

    public void start() {
        LinearLayout parentView = (LinearLayout) getChildAt(0);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            LinearLayout dynamicRow = (LinearLayout) parentView.getChildAt(i);
            for (int j = 0; j < dynamicRow.getChildCount(); j++) {
                ImageView dynamicImage = (ImageView) dynamicRow.getChildAt(j);
                int iconResource = getRandomIcon();
                dynamicImage.setAlpha(0f);
                dynamicImage.setImageResource(iconResource);

                int colorId = getRandomColor();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dynamicImage.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(
                            getContext(), colorId)));
                } else {
                    dynamicImage.setColorFilter(getResources().getColor(colorId));
                }
                makeViewAnimation(dynamicImage);
            }
        }
    }

    public void setIcons(int[] icons) {
        this.ICONS = icons;
    }

    public void setColors(int[] colors) {
        this.COLORS = colors;
    }

    private void makeViewAnimation(final ImageView view) {
        final Animation fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        final Animation fadeOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        fadeInAnimation.setStartOffset((long) (Math.random() * 1000));

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(fadeOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setImageResource(getRandomIcon());
                int colorId = getRandomColor();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(
                            getContext(), colorId)));
                } else {
                    view.setColorFilter(getResources().getColor(colorId));
                }
                view.startAnimation(fadeInAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.setAlpha(1f);
        view.startAnimation(fadeInAnimation);
    }

    private int getRandomIcon() {
        return ICONS[(int) (Math.random() * ICONS.length)];
    }

    private int getRandomColor() {
        return COLORS[(int) (Math.random() * COLORS.length)];
    }
}
