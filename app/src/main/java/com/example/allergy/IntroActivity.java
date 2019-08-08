package com.example.allergy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView; //인트로gif파일

import com.bumptech.glide.Glide; //인트로gif파일
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget; //인트로gif파일

public class IntroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //인트로 gif파일 여기부터
        ImageView image = (ImageView) findViewById(R.id.iv1);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(image);
        Glide.with(this).load(R.raw.intro).into(gifImage);
        //인트로 gif파일 여기까지

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}