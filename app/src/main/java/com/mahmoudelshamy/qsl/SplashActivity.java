package com.mahmoudelshamy.qsl;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 2500;
    private static final int BALL_ANIM_DURATION = 1500;
    private static final int BG_ANIM_DURATION = 5000;

    private ImageView mImageBackground;
    private ImageView mImageBall;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // define objects
        mImageBackground = (ImageView) findViewById(R.id.image_background);
        mImageBall = (ImageView) findViewById(R.id.image_ball);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                // start get started activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };

        // start splash
        mHandler.postDelayed(mRunnable, SPLASH_DURATION);
    }

    /**
     * overridden method
     */
    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();

        // animate background
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        Random random = new Random();
        mImageBackground.animate()
                .setStartDelay(200)
                .scaleX(1.1F).scaleY(1.1F)
                .translationXBy(getNextTranslation(random, isTablet)).translationYBy(getNextTranslation(random, isTablet))
                .setInterpolator(new DecelerateInterpolator()).setDuration(BG_ANIM_DURATION)
                .start();
    }

    /**
     * method, used to return new translation value for background animation
     */
    private static float getNextTranslation(Random random, boolean isTablet) {
        boolean bool = random.nextBoolean();
        for (float f1 = 0.0F; ; f1 = -50.0F) {
            float f2 = 0.0F;
            if (bool) {
                f2 = 50.0F;
            }
            float f3 = f1 + random.nextFloat() * (f2 - f1);
            if (isTablet) {
                f3 *= 1.5F;
            }
            return f3;
        }
    }

    /**
     * overridden method
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            // create and start ball animation
            float fromY = 0 - mImageBall.getHeight();
            float toY = mImageBall.getY();
            ObjectAnimator animator = ObjectAnimator.ofFloat(mImageBall, "y", fromY, toY);
            animator.setDuration(BALL_ANIM_DURATION);
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
        }
    }

    /**
     * overridden method
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // cancel splash runnable
        mHandler.removeCallbacks(mRunnable);
    }
}
