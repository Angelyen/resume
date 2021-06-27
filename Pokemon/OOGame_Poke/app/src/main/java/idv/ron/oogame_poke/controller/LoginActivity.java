package idv.ron.oogame_poke.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import idv.ron.oogame_poke.R;


public class LoginActivity extends AppCompatActivity {
    private EditText etuser;                            // 使用者名稱
    private  MediaPlayer mpback = new MediaPlayer();    // 背景音樂
    private ImageButton imageButton;                    // 點擊得login按鈕

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        playsound();
        findViews();
    }

    private void playsound() {
        mpback = MediaPlayer.create(this, R.raw.backmusic_mainpage);
        mpback.start();
        mpback.setLooping(true);
    }

    @SuppressLint("ResourceType")
    private void findViews() {
        etuser = findViewById(R.id.etuser);
        imageButton =(ImageButton) findViewById(R.drawable.login);

    }

    //當點擊login，跳到選擇角色畫面
    public void onLoginClick(View view) {
        String name = etuser.getText().toString();
        mpback.stop();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        Intent intent = new Intent(this, RolePageActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
