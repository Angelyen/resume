package idv.ron.oogame_poke.controller;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Pokemon;

// 我的寶可夢捕捉頁面
public class PokemonHuntActivity extends AppCompatActivity {
    private Pokemon fieldPokemon, myPokemon;
    private ImageView ivFieldPokemon, ivMyPokemon;
    private TextView tvResult;
    private Toast toast;
    private Toast appear;
    private boolean myTurn = true;
    private SoundPool sound = null;
    private int music;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pokemon_hunt);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fieldPokemon = (Pokemon) bundle.getSerializable("fieldPokemon");
            myPokemon = (Pokemon) bundle.getSerializable("myPokemon");
        }
        String title = String.format(
                getString(R.string.textPokemonHunt),
                myPokemon.getName(), fieldPokemon.getName());
        setTitle(title);
        String text = String.format(getString(R.string.textPokemonAppear),fieldPokemon.getYell());
        appear = Toast.makeText(PokemonHuntActivity.this, text, Toast.LENGTH_SHORT);
        appear.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 600,-650);
        appear.show();

//        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
//        music = sound.load(this, R.raw.piano,1);
//        sound.play(music,1.0F,1.0F,0,0,1.0F);

        handleViews();
    }

    private void handleViews() {
        tvResult = findViewById(R.id.tvResult);

        ivFieldPokemon = findViewById(R.id.ivFieldPokemon);
        ivFieldPokemon.setImageResource(fieldPokemon.getImage());

        ivMyPokemon = findViewById(R.id.ivMyPokemon);
        ivMyPokemon.setImageResource(myPokemon.getBack());
        ivMyPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = myPokemon.attackResult(fieldPokemon, myPokemon.getFastMove());
                tvResult.append(text + "\n");
                attackAnimation(myTurn, ivMyPokemon, ivFieldPokemon);

                // 野生寶可夢沒有HP就結束，回到第一頁（MainActivity）
                if (fieldPokemon.getHp() <= 0) {
                    MainPageActivity.energy -= 5;
                    checkEnergy(MainPageActivity.energy);
                    catchResult(true);
                    // 將我的寶可夢設為disable，避免user繼續點擊攻擊
                    ivMyPokemon.setEnabled(false);
                    return;
                }
                text = fieldPokemon.attackResult(myPokemon, fieldPokemon.getFastMove());
                tvResult.append(text + "\n");
                if (myPokemon.getHp() <= 0) {
                    MainPageActivity.energy -= 15;
                    checkEnergy(MainPageActivity.energy);
                    catchResult(false);
                }
            }
        });
    }

    /**
     * 設定攻擊與防禦寶可夢的動畫
     * @param myTurn 是否輪到我的寶可夢攻擊
     * @param myPokeView 我的寶可夢的ImageView
     * @param fieldPokeView 野生寶可夢的ImageView
     */
    private void attackAnimation(boolean myTurn, View myPokeView, View fieldPokeView) {
        // 設定攻擊寶可夢的動畫

            Animation attackAnimation_My = getattackMy();
            myPokeView.startAnimation(attackAnimation_My);

            Animation attackAnimation_field = getattackField();
            fieldPokeView.startAnimation(attackAnimation_field);
            //        if (myTurn) {
//    }
    }


    /**
     * 捕捉結果
     * @param catchSuccess 是否捕捉成功
     */
    private void catchResult(boolean catchSuccess) {
        if (catchSuccess) {
            // 抓到寶可夢，先將該寶可夢的HP加滿後再存入我的百寶箱內
            fieldPokemon.setHp(fieldPokemon.getFullHp());
            Pokemon.getMyPokemons().add(fieldPokemon);
            // 晃動寶可夢的ImageView代表被有效攻擊
            Animation shakeAnimation = getShakeAnimation();
            shakeAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // 當晃動完畢後寶可夢變成透明（代表被抓而消失了）
                    Animation animationset = getDied();
                    animationset.setFillAfter(true);
                    animationset.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        // 當ImageView變透明後，Toast「抓到xxx」並關閉此頁回到MainActivity
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (toast != null) {
                                toast.cancel();
                            }
                            String text = String.format(
                                    getString(R.string.textPokemonCaught), fieldPokemon.getName());
                            toast = Toast.makeText(PokemonHuntActivity.this,
                                    text, Toast.LENGTH_SHORT);
                            toast.setGravity(
                                    Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                                    -200, 0);
                            toast.show();
                            finish();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    ivFieldPokemon.startAnimation(animationset);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            ivFieldPokemon.startAnimation(shakeAnimation);
        } else {
            if (toast != null) {
                toast.cancel();
            }
            String text = String.format(
                    getString(R.string.textPokemonRunAway), fieldPokemon.getName());
            toast = Toast.makeText(PokemonHuntActivity.this, text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                    200, 0);
            toast.show();
            finish();
        }
    }




    private TranslateAnimation getShakeAnimation() {
        TranslateAnimation shakeAnimation = new TranslateAnimation(0, 10, 0, 0);
        shakeAnimation.setDuration(0);
        CycleInterpolator cycleInterpolator = new CycleInterpolator(5);
        shakeAnimation.setInterpolator(cycleInterpolator);
        return shakeAnimation;
    }


    private AlphaAnimation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setStartOffset(350);
        alphaAnimation.setDuration(300);
        return alphaAnimation;
    }

    private AnimationSet getDied(){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(getattackField2());
        animationSet.addAnimation(getAlphaAnimation());
        return animationSet;
    }
    private TranslateAnimation getattackField (){
        TranslateAnimation translateAnimation = new TranslateAnimation(0,-325,0,325);
        translateAnimation.setDuration(300);
        translateAnimation.setFillBefore(true);
        return translateAnimation;
    }

    private TranslateAnimation getattackField2 (){
        TranslateAnimation translateAnimation = new TranslateAnimation(0,-325,0,325);
        translateAnimation.setDuration(300);
        translateAnimation.setFillBefore(true);
        return translateAnimation;
    }

    private TranslateAnimation getattackMy (){
        TranslateAnimation translateAnimation = new TranslateAnimation(0,325,0,-325);
        translateAnimation.setDuration(300);
        translateAnimation.setFillBefore(true);
        return translateAnimation;
    }

    public void checkEnergy(int energy){
        if(energy<=0){
            Toast endToast = null;
            if (toast != null) {
                toast.cancel();
            }
            String text = String.format(getString(R.string.textEnergyLost));
            endToast = Toast.makeText(PokemonHuntActivity.this,
                    text, Toast.LENGTH_SHORT);
            endToast.setGravity(
                    Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                    0, 0);
            endToast.show();
            Intent intent = new Intent(this, LastActivity.class);


        }
    }







}