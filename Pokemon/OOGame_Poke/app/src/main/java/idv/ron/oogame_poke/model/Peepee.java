package idv.ron.oogame_poke.model;

import java.util.List;

import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.skill.Move;

/**
 * 皮寶寶升級成皮皮增加「魅惑之聲」技巧
 */

public class Peepee extends Pokemon {
    // 魅惑之聲
    private Move singMove;

    /**
     * 建立皮皮寶可夢
     *
     * @param image       圖片
     * @param name        名稱
     * @param level       等級
     * @param stamina     耐力
     * @param attack      攻擊力
     * @param defense     防禦力
     * @param catchChance 捕捉率
     * @param fastMove    基本技能
     * @param chargeMove  特別技能
     */


    public Peepee(int image, int back, String name, int yell, int level, int stamina, int attack, int defense, int catchChance, Move fastMove, Move chargeMove) {

        super(image, back, name, yell, level, stamina, attack, defense, catchChance, fastMove, chargeMove);
        setSingMove(new Move("魅惑之聲", 40));

    }
    public static void getUpLevelPokemon() {
        Pokemon peepee = new Pokemon(R.drawable.pokemon_peepee,
                R.drawable.pokemon_peepee_back,
                "皮皮",
                R.raw.pee,
                1,
                140,
                107,
                116,
                24,
                new Move("魅惑之聲", 40),
                new Move("冰凍光束", 70)
        );
        Pokemon.getMyPokemons().add(peepee);
    }


    @Override
    public int attack(Pokemon enemy, Move move) {
        // 總傷害 = 攻擊力 + 技能傷害 + 魅惑之聲
        int totalDamage = getAttack() + move.getPower() + getSingMove().getPower();
        // 敵人結果傷害 = 總傷害*1.5無條件捨去後 - 敵人防禦，結果傷害為負值則改為0
        int resultDamage = (int)(totalDamage*1.5) - enemy.getDefense();
        resultDamage = resultDamage >= 0 ? resultDamage : 0;

        // 敵人依照結果傷害計算損失的HP，HP為負值則改為0
        int hp = enemy.getHp() - resultDamage;
        enemy.setHp(hp > 0 ? hp : 0);
        return resultDamage;
    }

    @Override
    public String attackResult(Pokemon enemy, Move move) {
        String moveName = move.getName() + ", " + getSingMove().getName();
        double resultDamage = this.attack(enemy, move);
        String text = String.format(
                "%s利用%s攻擊 對%s造成 %s 傷害,%n %3$sHP剩下 %s %n",
                this.getName(), moveName, enemy.getName(), resultDamage, enemy.getHp());
        return text;
    }

    public Move getSingMove() {
        return singMove;
    }

    public void setSingMove(Move singMove) {
        this.singMove = singMove;
    }
}
