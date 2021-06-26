package idv.ron.oogame_poke.controller;


import android.content.Intent;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.PopupMenu;
import java.util.ArrayList;
import java.util.List;
import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Pokemon;


public class MainPageActivity extends AppCompatActivity {
    private String text;
    static int energy = 10;
    private ImageView chi;
    private ImageView xia;
    private ImageView kang;
    private ImageView bag;
    private SoundPool sound = null;
    private int music;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            text = String.format("%s 的Pokemon", name);
            index = bundle.getInt("index", index);
        }
        String title = text;
        setTitle(title);
        findview();
        handleViews();
    }

    private void findview() {
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

    private void handleViews() {


        // 取得野生寶可夢後將圖片顯示在ImageView上
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

    private void showPopupMenu(final Pokemon fieldPokemon, View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.hunt_menu);
        if(energy<10){
            popupMenu.getMenu().removeItem(R.id.ballHunt);
            popupMenu.getMenu().removeItem(R.id.myPokemonHunt);
        }
        // 如果沒有寶可夢，移除「使用我的寶可夢」選項，只能用精靈球來獵捕野生寶可夢
        else if (Pokemon.getMyPokemons().size() == 0 || energy <= 15 ) {
            popupMenu.getMenu().removeItem(R.id.myPokemonHunt);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()) {
                    // 選擇「使用我的寶可夢」會開啟百寶箱頁面供user挑選作戰用的寶可夢
                    case R.id.myPokemonHunt:
                        intent.setClass(MainPageActivity.this, PokemonActivity.class);
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


//     點擊主角會開啟百寶箱
    public void onBackpackClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.inflate(R.menu.mypackage_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()){
                    case R.id.MyPackage:
                        intent.setClass(MainPageActivity.this, PokemonActivity.class);
                        break;

                     default:
                         intent.setClass(MainPageActivity.this, LastActivity.class);
                         break;
                }
                        startActivity(intent);
                return true;
            }
        });
//        Intent intent = new Intent(this, PokemonActivity.class);
        popupMenu.show();
    }



    private AlphaAnimation getalphaAnimation_disappear (){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }


}

