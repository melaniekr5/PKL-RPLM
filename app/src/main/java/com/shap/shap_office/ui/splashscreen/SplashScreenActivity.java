package com.shap.shap_office.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shap.shap_office.MainActivity;
import com.shap.shap_office.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivLogo;
    TextView tvText, tvText1;
    CircleImageView cvWhite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        cvWhite = findViewById(R.id.cv_circle);
        ivLogo = findViewById(R.id.iv_logo);
        tvText = findViewById(R.id.tv_text);
        tvText1 = findViewById(R.id.tv_text1);
        //ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setSplash();

    }

    public void setSplash() {

        final Intent intent = new Intent(this, MainActivity.class);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        cvWhite.startAnimation(animation);
        ivLogo.startAnimation(animation);
        tvText.startAnimation(animation);
        tvText1.startAnimation(animation);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };

        thread.start();
    }
}
