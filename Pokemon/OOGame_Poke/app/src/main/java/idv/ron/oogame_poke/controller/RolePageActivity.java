package idv.ron.oogame_poke.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import idv.ron.oogame_poke.R;

public class RolePageActivity extends AppCompatActivity {
    private ImageView role;
    private String name;
    private String text;
    private int index =0;
    private ImageView chi;
    private ImageView xia;
    private ImageView kang;
    private boolean people = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_page);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            text = String.format("%s 的Pokemon", name);
        }
        String title = text;
        setTitle(title);
        findview();

    }

    private void findview() {
        chi = findViewById(R.id.RoleView0);
        xia = findViewById(R.id.RoleView1);
        kang = findViewById(R.id.RoleView2);
        chi.startAnimation(getalphaAnimation_disappear());
        xia.startAnimation(getalphaAnimation_disappear());
        kang.startAnimation(getalphaAnimation_disappear());
    }

    public void onRole1Click(View view){
        if(!people) {
            chi.startAnimation(getalphaAnimation_appear());
            role = chi;
            people = true;
            index = 0;
        }
        else {
            xia.startAnimation(getalphaAnimation_disappear());
            kang.startAnimation(getalphaAnimation_disappear());
            chi.startAnimation(getalphaAnimation_appear());
            index = 0;
        }
    }

    public void onRole2Click(View view){
        if(!people) {
            xia.startAnimation(getalphaAnimation_appear());
            role = xia;
            people = true;
            index = 1;
        }
        else {
            chi.startAnimation(getalphaAnimation_disappear());
            kang.startAnimation(getalphaAnimation_disappear());
            xia.startAnimation(getalphaAnimation_appear());
            index = 1;

        }

    }

    public void onRole3Click(View view){
        if(!people) {
            kang.startAnimation(getalphaAnimation_appear());
            role = kang;
            people = true;
            index =2;
        }
        else {
            xia.startAnimation(getalphaAnimation_disappear());
            chi.startAnimation(getalphaAnimation_disappear());
            kang.startAnimation(getalphaAnimation_appear());
            index =2;

        }
    }

    public void onNextClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putInt("index", index);
        Intent intent = new Intent(this, MainPageActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private AlphaAnimation getalphaAnimation_disappear (){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    private AlphaAnimation getalphaAnimation_appear (){
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(600);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }


}
