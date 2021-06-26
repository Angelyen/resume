package idv.ron.oogame_poke.model;
import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.skill.Move;

/**
 * 呆呆獸升級成呆河馬級增加「心靈幻象」
 */
public class Slowbro extends Pokemon {
    // 心靈幻象
    private Move psychic;

    /**
     * 建立巨龜級寶可夢
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
    public Slowbro(int image, int back, String name, int yell, int level, int stamina, int attack, int defense, int catchChance, Move fastMove, Move chargeMove) {
        super(image, back, name, yell, level, stamina, attack, defense, catchChance, fastMove, chargeMove);
        setPsychicMove(new Move("心靈幻象", 50));
    }

    public static void getUpLevelPokemon() {
        Pokemon slowbro = new Pokemon(R.drawable.pokemon_slowbro,
                R.drawable.pokemon_slowbro_back,
                "呆河馬",
                R.raw.slowpoke,
                1,
                190,
                177,
                194,
                16,
                new Move("噴水", 20),
                new Move("水之波動", 80)
        );
        Pokemon.getMyPokemons().add(slowbro);
    }

    @Override
    public int attack(Pokemon enemy, Move move) {
        // 總傷害 = 攻擊力 + 技能傷害 + 心靈幻象
        int totalDamage = getAttack() + move.getPower() + getPsychicMove().getPower();
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
        String moveName = move.getName() + ", " + getPsychicMove().getName();
        double resultDamage = this.attack(enemy, move);
        String text = String.format(
                "%s利用%s攻擊 對%s造成 %s 傷害,%n %3$sHP剩下 %s %n",
                this.getName(), moveName, enemy.getName(), resultDamage, enemy.getHp());
        return text;
    }

    public Move getPsychicMove() {
        return psychic;
    }

    public void setPsychicMove(Move psychic) {
        this.psychic = psychic;
    }
//    /**
//     * 取得總防禦
//     *
//     * @return 回傳本身防禦+巨殼防禦
//     */
//    @Override
//    public int getDefense() {
//        return super.getDefense() + shellDefense;
//    }
}
