package idv.ron.oogame_poke.controller;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Pokemon;

// 百寶箱頁面，列出我所有的寶可夢
public class PokemonActivity extends AppCompatActivity {
    private Pokemon fieldPokemon;
    private boolean setListener;
    private static MediaPlayer mpFightBack = new MediaPlayer();         //攻擊的背景音樂



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        setTitle(R.string.textMyPokemons);
        // 如果是因為捕捉野生寶可夢才來到此頁，fieldPokemon不為null
        if (getIntent().getExtras() != null) {
            fieldPokemon = (Pokemon) getIntent().getExtras().getSerializable("fieldPokemon");
            setListener = true;
            mpFightBack = MediaPlayer.create(this, R.raw.backmusic_fight);       //點擊之後，攻擊背景音樂開始播放
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //如果沒有任何一隻寶可夢就列出「沒有寶可夢」，如果有就列出寶可夢
        RecyclerView rvPokemon = findViewById(R.id.rvPokemon);
        if (Pokemon.getMyPokemons().size() == 0) {
            Toast.makeText(this, R.string.textNoPokemon, Toast.LENGTH_SHORT).show();
            return;
        }
        rvPokemon.setLayoutManager(new GridLayoutManager(
                this, 3));
        rvPokemon.setAdapter(new PokemonAdapter(this, Pokemon.getMyPokemons()));
    }

    private class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
        Context context;
        List<Pokemon> myPokemons;

        PokemonAdapter(Context context, List<Pokemon> myPokemons) {
            this.context = context;
            this.myPokemons = myPokemons;
        }

        @Override
        public int getItemCount() {
            return myPokemons.size();
        }
        //寶可夢的ImageView、名字、血量
        class PokemonViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView tvName, tvHp;

            PokemonViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                tvName = itemView.findViewById(R.id.tvName);
                tvHp = itemView.findViewById(R.id.tvHp);
            }
        }

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_view_pokemon, parent, false);
            return new PokemonViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
            final Pokemon myPokemon = myPokemons.get(position);
            holder.imageView.setImageResource(myPokemon.getImage());
            holder.tvName.setText(myPokemon.getName());
            String hpInfo = myPokemon.getHp() + "/" + myPokemon.getFullHp();
            holder.tvHp.setText(hpInfo);

            // true代表因為捕捉野生寶可夢才來到此頁，user可點選自己的寶可夢與野生的對戰
            if (setListener) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MediaPlayer mp = MainPageActivity.getMpMainBack();
                        mp.stop();           //如果開始對戰，主背景音樂停

                        MediaPlayer mp1 = PokemonHuntActivity.getMpMainBack1();
                        mp1.stop();          //如果開始對戰，主背景音樂停

                        MediaPlayer mp2 = UpupActivity.getMpback3();
                        mp2.stop();          //如果開始對戰，主背景音樂停

                        mpFightBack.start();
                        mpFightBack.setLooping(true);
                        Intent intent = new Intent(PokemonActivity.this,
                                PokemonHuntActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("fieldPokemon", fieldPokemon);
                        bundle.putSerializable("myPokemon", myPokemon);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        // 對戰完畢會回到此頁，不可再點擊寶可夢對戰，因此取消listener
                        setListener = false;
                    }
                });
            }
        }

    }
    public static MediaPlayer getMpFightBack() {
        return mpFightBack;
    }

}
