package idv.ron.oogame_poke.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import idv.ron.oogame_poke.R;

// 首頁
public class MainActivity extends AppCompatActivity {
    //皮卡丘的ImageView
    private ImageView ivPikachu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    //找到皮卡丘的View並做出動畫，做完後換到下一頁
    private void findViews() {
        ivPikachu = findViewById(R.id.ivPihachu);
        TranslateAnimation translateAnimation = getTranslateAnimation();
        ivPikachu.startAnimation(translateAnimation);
        getNextPage();
    }

    private TranslateAnimation getTranslateAnimation() {
        TranslateAnimation roll = new TranslateAnimation(0, 1100, 0, 0);
        roll.setStartOffset(1000);
        roll.setDuration(3000);
        roll.setFillAfter(true);
        CycleInterpolator cycleInterpolator = new CycleInterpolator(1);
        roll.setInterpolator(cycleInterpolator);
        return roll;
    }

    //換頁
    private void getNextPage() {
        //設置換頁等待時間
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        timer.schedule(task,2000);
    }




}
