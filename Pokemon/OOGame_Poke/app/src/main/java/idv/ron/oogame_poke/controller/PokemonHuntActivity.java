package idv.ron.oogame_poke.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
    private boolean myTurn = true;
    private static MediaPlayer mpMainBack1 = new MediaPlayer();         //主背景音樂


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pokemon_hunt);
        Bundle bundle = getIntent().getExtras();

        //寶可夢對戰標題
        if (bundle != null) {
            fieldPokemon = (Pokemon) bundle.getSerializable("fieldPokemon");
            myPokemon = (Pokemon) bundle.getSerializable("myPokemon");
        }
        String title = String.format(
                getString(R.string.textPokemonHunt),
                myPokemon.getName(), fieldPokemon.getName());
        setTitle(title);
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

                    //如果以對戰方式戰勝，每次消耗5點耐力值
                    MainPageActivity.energy -= 5;
                    MainPageActivity.etenergy.setText(String.format(getString(R.string.textenergy), MainPageActivity.getEnergy()));
                    checkEnergy(MainPageActivity.energy);
                    catchResult(true);
                    // 將我的寶可夢設為disable，避免user繼續點擊攻擊
                    ivMyPokemon.setEnabled(false);
                    return;
                }
                text = fieldPokemon.attackResult(myPokemon, fieldPokemon.getFastMove());
                tvResult.append(text + "\n");
                if (myPokemon.getHp() <= 0) {
                    //如果對戰失敗，每次消耗15點耐力值
                    MainPageActivity.energy -= 15;
                    MainPageActivity.etenergy.setText(String.format(getString(R.string.textenergy), MainPageActivity.getEnergy()));
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
            myPokeView.startAnimation(attackAnimation_My);      //我的寶可夢

            Animation attackAnimation_field = getattackField();
            fieldPokeView.startAnimation(attackAnimation_field);    //野生寶可夢

    }


    /**
     * 捕捉結果
     * @param catchSuccess 是否捕捉成功
     */
    private void catchResult(boolean catchSuccess) {
        if (catchSuccess) {
            // 抓到寶可夢，先將該寶可夢的HP加滿後再存入我的百寶箱內。並計算現有個數
            fieldPokemon.setHp(fieldPokemon.getFullHp());
            Pokemon.getMyPokemons().add(fieldPokemon);

            if (fieldPokemon.getName().equals("皮寶寶")){
                Pokemon.setPeeCount(Pokemon.getPeeCount()+1);
            }
            else if(fieldPokemon.getName().equals("呆呆獸")){
                Pokemon.setSlowPokeCount(Pokemon.getSlowPokeCount()+1);
            }
            else if(fieldPokemon.getName().equals("青綿鳥")){
                Pokemon.setBirdCount(Pokemon.getBirdCount()+1);
            }
            else if(fieldPokemon.getName().equals("溶食獸")){
                Pokemon.setLongCount(Pokemon.getLongCount()+1);
            }
            else if(fieldPokemon.getName().equals("波克比")){
                Pokemon.setBokpeCount(Pokemon.getBokpeCount()+1);
            }

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

                        // 當ImageView變透明後，Toast「抓到xxx，現在有幾隻」並關閉此頁回到MainActivity
                        @SuppressLint("StringFormatMatches")
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (toast != null) {
                                toast.cancel();
                            }
                            String text= "";

                            if (fieldPokemon.getName().equals("皮寶寶")) {
                                   text = String.format(getString(R.string.textPokemonCaught),fieldPokemon.getName()," ",Pokemon.getPeeCount());
                            }

                            else if(fieldPokemon.getName().equals("呆呆獸")){
                                text = String.format(getString(R.string.textPokemonCaught),fieldPokemon.getName()," ",Pokemon.getSlowPokeCount());
                            }
                            else if(fieldPokemon.getName().equals("青綿鳥")){
                                text = String.format(getString(R.string.textPokemonCaught),fieldPokemon.getName()," ",Pokemon.getBirdCount());
                            }
                            else if(fieldPokemon.getName().equals("溶食獸")){
                                text = String.format(getString(R.string.textPokemonCaught),fieldPokemon.getName()," ",Pokemon.getLongCount());
                            }
                            else if(fieldPokemon.getName().equals("波克比")){
                                text = String.format(getString(R.string.textPokemonCaught),fieldPokemon.getName()," ",Pokemon.getBokpeCount());
                            }

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
            //寶可夢逃走
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


        mpMainBack1 = MediaPlayer.create(this, R.raw.backmusic_mainpage);     //主畫面音樂再次播放
        mpMainBack1.setLooping(true);
        mpMainBack1.start();


        MediaPlayer mpFightBackt = PokemonActivity.getMpFightBack();    //對戰音樂停
        mpFightBackt.stop();


    }



    private TranslateAnimation getShakeAnimation() {
        TranslateAnimation shakeAnimation = new TranslateAnimation(0, 10, 0, 0);
        shakeAnimation.setDuration(0);
        CycleInterpolator cycleInterpolator = new CycleInterpolator(5);
        shakeAnimation.setInterpolator(cycleInterpolator);
        return shakeAnimation;
    }

    private AnimationSet getDied(){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(getattackField2());
        animationSet.addAnimation(getAlphaAnimation());
        return animationSet;
    }

    private AlphaAnimation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setStartOffset(350);
        alphaAnimation.setDuration(300);
        return alphaAnimation;
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

    private void checkEnergy(int energy){
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

    public static MediaPlayer getMpMainBack1() {
        return mpMainBack1;
    }







}