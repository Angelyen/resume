package idv.ron.oogame_poke.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Pokemon;

public class MainPageActivity extends AppCompatActivity {
    private String text;
    static TextView etenergy;
    private ImageView chi;
    private ImageView xia;
    private ImageView kang;
    static int energy = 100;            //體力值
    private int index;                  //人物指引
    private static MediaPlayer mpMainBack = new MediaPlayer();      //主背景音樂
    private static MediaPlayer yell = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String etuser = bundle.getString("etuser");
            text = String.format("%s 的Pokemon", etuser);            //設置標題
            index = bundle.getInt("index", index);              //指引選角
        }
        String title = text;
        setTitle(title);
        playsound();
        findview();         //角色
        handleViews();      //寶可夢
    }


    //設置角色的ImageView  getString(R.string.textPokemonRunAway), fieldPokemon.getName());
    private void findview() {
        etenergy = findViewById(R.id.tvenergy);
        etenergy.setText(String.format(getString(R.string.textenergy), getEnergy()));

        chi = findViewById(R.id.ivRole0);
        xia = findViewById(R.id.ivRole1);
        kang = findViewById(R.id.ivRole2);
        if(index == 0){
            xia.startAnimation(getalphaAnimation_disappear());
            kang.startAnimation(getalphaAnimation_disappear());
        }
        else if(index ==1){
            chi.startAnimation(getalphaAnimation_disappear());
            kang.startAnimation(getalphaAnimation_disappear());
        }
        else{
            xia.startAnimation(getalphaAnimation_disappear());
            chi.startAnimation(getalphaAnimation_disappear());
        }

    }

    // 取得野生寶可夢後將圖片顯示在ImageView上
    private void handleViews() {
        List<Pokemon> fieldPokemons = Pokemon.getFieldPokemons();
        List<ImageView> imageViews = new ArrayList<>();

        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        ImageView imageView5 = findViewById(R.id.imageView5);

        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        imageViews.add(imageView5);

        for (int i = 0; i < imageViews.size(); i++) {
            final Pokemon fieldPokemon = fieldPokemons.get(i);
            imageViews.get(i).setImageResource(fieldPokemon.getImage());
            // 點擊野生寶可夢後跳出選單讓user選擇捕捉方式
            imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(fieldPokemon, view);
                }
            });
        }
    }

    //寶可夢的點擊選單
    private void showPopupMenu(final Pokemon fieldPokemon, View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        //出現寶可夢的叫聲
         yell = MediaPlayer.create(this, fieldPokemon.getYell());
         yell.start();

        popupMenu.inflate(R.menu.hunt_menu);
        if (fieldPokemon.getName().equals("皮寶寶") && Pokemon.getPeeCount()< 3)  {    //如果皮寶寶數量不足以升級
            popupMenu.getMenu().removeItem(R.id.LevelUp);
        }

        else if(fieldPokemon.getName().equals("呆呆獸") && Pokemon.getSlowPokeCount()< 3){      //如果呆呆獸數量不足以升級
            popupMenu.getMenu().removeItem(R.id.LevelUp);
        }
        else if(fieldPokemon.getName().equals("青綿鳥") || fieldPokemon.getName().equals("溶食獸")
                || fieldPokemon.getName().equals("波克比")){
            popupMenu.getMenu().removeItem(R.id.LevelUp);       //如果為皮寶寶與呆呆獸以外的寶可夢則不需要升級選單
        }

        if(energy<10){        //如果體力用光，不可以再捕捉寶可夢
            popupMenu.getMenu().removeItem(R.id.ballHunt);
            popupMenu.getMenu().removeItem(R.id.myPokemonHunt);
        }
        // 如果沒有寶可夢，移除「使用我的寶可夢」選項，只能用精靈球來獵捕野生寶可夢
        else if (Pokemon.getMyPokemons().size() == 0 || energy < 15 ) {
            popupMenu.getMenu().removeItem(R.id.myPokemonHunt);
        }

        //當點擊popuMenu的MenuItem時
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()) {

                    // 選擇「使用我的寶可夢」會開啟百寶箱頁面供user挑選作戰用的寶可夢
                    case R.id.myPokemonHunt:
                        intent.setClass(MainPageActivity.this, PokemonActivity.class);
                        break;
                    // 選擇「升級」開啟升級頁面
                    case R.id.LevelUp:
                        intent.setClass(MainPageActivity.this, UpupActivity.class);
                        mpMainBack.stop();
                        break;
                    // 預設是使用精靈球捕捉，會開啟精靈球捕捉頁面
                    default:
                        intent.setClass(MainPageActivity.this, BallHuntActivity.class);
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("fieldPokemon", fieldPokemon);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });

        popupMenu.show();
    }


    // 點擊包包會開啟百寶箱
    public void onBackpackClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.inflate(R.menu.mypackage_menu);

        //當點擊popuMenu的MenuItem時
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()){

                    case R.id.MyPackage:    //選擇「我的寶可夢」開啟我的百寶箱
                        intent.setClass(MainPageActivity.this, PokemonActivity.class);
                        break;

                     default:               //預設「結束遊戲」進入結束畫面
                         intent.setClass(MainPageActivity.this, LastActivity.class);
                         break;
                }
                startActivity(intent);
                return true;
            }
        });
        popupMenu.show();
    }


    private AlphaAnimation getalphaAnimation_disappear (){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    private void playsound() {
        mpMainBack = MediaPlayer.create(this, R.raw.backmusic_mainpage);
        mpMainBack.start();
        mpMainBack.setLooping(true);
    }


    public static MediaPlayer getMpMainBack() {
        return mpMainBack;
    }
    public static int getEnergy() {
        return energy;
    }
}

