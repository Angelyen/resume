package idv.ron.oogame_poke.controller;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Pokemon;

// 精靈球捕捉頁面
public class BallHuntActivity extends AppCompatActivity {
    private static final String TAG = "BallHuntActivity";
    private Pokemon fieldPokemon;
    private ImageView ivFieldPokemon, ivBall;
    private Toast toast;
    private Toast appear;
    private SoundPool sound = null;
    private int music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_hunt);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fieldPokemon = (Pokemon) bundle.getSerializable("fieldPokemon");
        }
        String title = String.format(
                getString(R.string.textPokemonHunt),
                getString(R.string.textBallHunt), fieldPokemon.getName());
        setTitle(title);
        String text = String.format(getString(R.string.textPokemonAppear),fieldPokemon.getYell());
        appear = Toast.makeText(BallHuntActivity.this, text, Toast.LENGTH_SHORT);
        appear.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, -300,-100);
        appear.show();

        handleViews();
    }

    private void handleViews() {
        ivFieldPokemon = findViewById(R.id.ivFieldPokemon);
        ivFieldPokemon.setImageResource(fieldPokemon.getImage());

        ivBall = findViewById(R.id.ivBall);
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        music = sound.load(this, R.raw.piano,1);
        sound.play(music,1.0F,1.0F,0,0,1.0F);
        ivBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.play(music,1.0F,1.0F,0,0,1.0F);
                // 點擊精靈球後依照亂數與捕捉率來決定是否抓到寶可夢
                boolean catchSuccess = catchSuccess(fieldPokemon.getCatchChance());
                // 捕捉成功將精靈球設為disable，避免user繼續點擊攻擊
                if (catchSuccess) {
                    MainPageActivity.energy -=10;
                    checkEnergy(MainPageActivity.energy);
                    ivBall.setEnabled(false);
                }
                startCatchAnimation(catchSuccess);
            }
        });
    }

    private void startCatchAnimation(boolean catchSuccess) {
        // 設定精靈球動畫
        Animation ballAnimation = getAnimationSet(catchSuccess);
        ivBall.startAnimation(ballAnimation);


//        SoundPool.Builder spb = new SoundPool.Builder();
//        spb.setMaxStreams(10);
//        spb.setAudioAttributes(null);
//        SoundPool sp = spb.build();

//        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
//        int music = sound.load(this, R.raw.piano,1);
//        sound.play(music,1,1,0,0,1);
        if (catchSuccess) {
            // 抓到寶可夢，先存入我的百寶箱內
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
                    Animation alphaAnimation = getAlphaAnimation();
                    alphaAnimation.setFillAfter(true);
                    alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        // 當ImageView變透明後，Toast「抓到xxx」並關閉此頁回到前頁
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (toast != null) {
                                toast.cancel();
                            }
                            String text = String.format(
                                    getString(R.string.textPokemonCaught), fieldPokemon.getName());
                            toast = Toast.makeText(
                                    BallHuntActivity.this, text, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 200, -100);
                            toast.show();
                            finish();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    ivFieldPokemon.startAnimation(alphaAnimation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            ivFieldPokemon.startAnimation(shakeAnimation);
        } else {
            // 讓寶可夢暫時消失，代表沒抓到
            Animation shakeAnimation_uncuccess = getShakeAnimation_uncuccess();
            shakeAnimation_uncuccess.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                // 消失後Toast「逃跑了」
                @Override
                public void onAnimationEnd(Animation animation) {
                    // 之前已經有Toast，立即cancel後再重新產生
                    if (toast != null) {
                        toast.cancel();
                    }
                    String text = String.format(getString(R.string.textPokemonDodge), fieldPokemon.getName());
                    toast = Toast.makeText(BallHuntActivity.this, text, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 200, 0);
                    toast.show();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            ivFieldPokemon.startAnimation(shakeAnimation_uncuccess);
        }

    }

    private boolean catchSuccess(int catchChance) {
        // 產生1~100亂數
        int chance = (int) (Math.random() * 100) + 1;
        Log.d(TAG, "chance = " + chance + "; catchSuccess = " + catchChance);
        // 產生的亂數小於等於捕捉率即代表捕捉到
        return chance <= catchChance;
    }

    private TranslateAnimation getBallTranslateAnimation(boolean catchSuccess) {
        // 取得畫面高度
        View parentView = (View) ivBall.getParent();
        // 球要向上移動至畫面上方，所以y為負值，需要乘以-1
        float distance = -1 * (parentView.getHeight() - parentView.getPaddingBottom() -
                parentView.getPaddingTop() - ivBall.getHeight());
        long duration = 500;
        TranslateAnimation translateAnimation;
        // 如果抓寶可夢不成功，球移動至畫面上方；抓寶可夢成功，球停在寶可夢下方1/2處，動畫時間也僅為1/2
        if (catchSuccess) {
            distance = distance / 2 + (ivFieldPokemon.getHeight() / 2);
            duration /= 2;
            translateAnimation = new TranslateAnimation(0, 0, 0, distance);
            translateAnimation.setDuration(duration);
            translateAnimation.setFillAfter(true);
        } else {
            translateAnimation = new TranslateAnimation(0, 0, 0, distance);
            translateAnimation.setDuration(duration);
            translateAnimation.setRepeatMode(Animation.RESTART);
        }
        return translateAnimation;
    }

    private RotateAnimation getRotateAnimation(){
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 1100,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(200);
        rotateAnimation.setRepeatMode(Animation.INFINITE);
        rotateAnimation.setRepeatCount(1);
        return rotateAnimation;
    }

    private TranslateAnimation getShakeAnimation() {
        TranslateAnimation shakeAnimation = new TranslateAnimation(0, 10, 0, 0);
        shakeAnimation.setStartOffset(200);
        shakeAnimation.setDuration(500);
        CycleInterpolator cycleInterpolator = new CycleInterpolator(5);
        shakeAnimation.setInterpolator(cycleInterpolator);
        return shakeAnimation;
    }

    private TranslateAnimation getShakeAnimation_uncuccess() {
        TranslateAnimation shake = new TranslateAnimation(0, -200, 0, 0);
        shake.setStartOffset(200);
        shake.setDuration(300);
        CycleInterpolator cycleInterpolator = new CycleInterpolator(1);
        shake.setInterpolator(cycleInterpolator);
        return shake;
    }

    private AlphaAnimation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(300);
        return alphaAnimation;
    }

    private AnimationSet getAnimationSet(boolean catchSuccess){
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = getRotateAnimation();
        animationSet.addAnimation(rotateAnimation);

        TranslateAnimation translateAnimation = getBallTranslateAnimation(catchSuccess);
        animationSet.addAnimation(translateAnimation);
        return animationSet;
    }

    public void checkEnergy(int energy){

        if(energy<=0){
            Toast endToast = null;
            if (toast != null) {
                toast.cancel();
            }
            String text = String.format(getString(R.string.textEnergyLost));
            endToast = Toast.makeText(BallHuntActivity.this, text, Toast.LENGTH_SHORT);
            endToast.setGravity(
                    Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                    0, -500);
            endToast.show();
            Intent intent = new Intent(this, LastActivity.class);


        }
    }
//    private getMusic(){
//        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
//        int music = sound.load(this, R.raw.piano,1);
//        sound.play(music,1,1,0,0,1);
//        return;
//    }
}