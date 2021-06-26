package idv.ron.oogame_poke.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Peepee;
import idv.ron.oogame_poke.model.Pokemon;
import idv.ron.oogame_poke.model.Slowbro;

public class UpupActivity extends AppCompatActivity {
    private ImageView oldPoke;          //尚未升級寶可夢
    private ImageView newPoke_peepee;          //升級後寶可夢（皮皮）
    private ImageView newPoke_sloebro;         //升級後寶可夢（呆河馬）
    private Pokemon fieldPokemon;
    private String text;
    private MediaPlayer mpback = new MediaPlayer();
    private static MediaPlayer mpback3 = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upup);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fieldPokemon = (Pokemon) bundle.getSerializable("fieldPokemon");
        }
        text = String.format("%s 進化", fieldPokemon.getName());
        setTitle(text);
        handleViews();
        LevelUP();
        getNextPage();

    }

    private void handleViews() {
        oldPoke = findViewById(R.id.ivOldPoke);
        oldPoke.setImageResource(fieldPokemon.getImage());
        newPoke_peepee = findViewById(R.id.ivNewPoke_peepee);
        newPoke_sloebro = findViewById(R.id.ivNewPoke_slowbro);
        mpback3 = MediaPlayer.create(this, R.raw.backmusic_mainpage);

    }

    //進化的設置與動畫
    private void LevelUP() {
        MediaPlayer mp = MainPageActivity.getMpMainBack();
        mp.pause();           //如果開始對戰，主背景音樂停

        MediaPlayer mp1 = PokemonHuntActivity.getMpMainBack1();
        mp1.pause();           //如果開始對戰，主背景音樂停


        playsound();

        int c =0;
        for(int i=0; i<Pokemon.getMyPokemons().size() ;i++){
            if(Pokemon.getMyPokemons().get(i).getName().equals(fieldPokemon.getName())){
                Pokemon.getMyPokemons().remove(i);
                i--;
                c++;
            }
            if(c==3)
                break;
        }
        oldPoke.startAnimation(getDisappear());

        if (fieldPokemon.getName().equals("皮寶寶")){
            Peepee.getUpLevelPokemon();
            Pokemon.setPeeCount(Pokemon.getPeeCount()-3);
            newPoke_sloebro.startAnimation(getAlphaAnimation_Field());
            newPoke_peepee.startAnimation(getAppear());

        }

        if (fieldPokemon.getName().equals("呆呆獸")){
            Slowbro.getUpLevelPokemon();
            Pokemon.setSlowPokeCount(Pokemon.getSlowPokeCount()-3);
            newPoke_peepee.startAnimation(getAlphaAnimation_Field());
            newPoke_sloebro.startAnimation(getAppear());

        }

    }

    private AnimationSet getDisappear(){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setStartOffset(2000);
        animationSet.setFillAfter(true);
        animationSet.addAnimation(getRotateAnimation());
        animationSet.addAnimation(getAlphaAnimation());
        return animationSet;
    }

    private AnimationSet getAppear(){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setStartOffset(4000);
        animationSet.setFillAfter(true);
        animationSet.addAnimation(getRotateAnimation_new());
        animationSet.addAnimation(getAlphaAnimation_new());
        return animationSet;
    }

    private AlphaAnimation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    private RotateAnimation getRotateAnimation(){
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setRepeatMode(Animation.INFINITE);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        return rotateAnimation;
    }

    private AlphaAnimation getAlphaAnimation_Field() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    private AlphaAnimation getAlphaAnimation_new(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(2000);
        return alphaAnimation;
    }

    private RotateAnimation getRotateAnimation_new(){
        RotateAnimation rotateAnimation = new RotateAnimation(
                0,720,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
        );
        rotateAnimation.setRepeatMode(Animation.INFINITE);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        return rotateAnimation;
    }

    private void playsound() {
        mpback = MediaPlayer.create(this, R.raw.level);
        mpback.start();
    }


    public static MediaPlayer getMpback3() {
        return mpback3;
    }

    //換頁
    private void getNextPage() {
        //設置換頁等待時間
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                mpback3.start();
                Intent intent = new Intent(UpupActivity.this, MainPageActivity.class);
                startActivity(intent);
            }

        };

        timer.schedule(task,6500);
    }
}
