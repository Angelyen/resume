package idv.ron.oogame_poke.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.action.Fight;
import idv.ron.oogame_poke.model.skill.Move;

/**
 * 寶可夢精靈，參看寶可夢Go全圖鑑（http://www.otaku-hk.com/pkmgo/en/pokedex）
 */
public class Pokemon implements Fight, Serializable {
    private static List<Pokemon> myPokemons = new ArrayList<>();
    private static List<Pokemon> pee = new ArrayList<>();
    private static int birdCount = 0;
    private static int slowPokeCount = 0;
    private static int peeCount = 0;
    private static int longCount = 0;
    private static int bokpeCount = 0;


    // 圖片
    private int image;
    //背影照
    private int back;
    // 名稱
    private String name;
    //叫聲
    private int yell;
    // 等級
    private int level;
    // 耐力
    private int stamina;
    // 血
    private int hp;
    // 攻擊力
    private int attack;
    // 防禦力
    private int defense;
    // 捕捉率
    private int catchChance;
    // 基本技能
    private Move fastMove;
    // 特別技能
    private Move chargeMove;

    /**
     * 建立寶可夢精靈
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

    public Pokemon(int image, int back, String name, int yell, int level, int stamina, int attack, int defense, int catchChance, Move fastMove, Move chargeMove) {
        this.image = image;
        this.back = back;
        this.name = name;
        this.yell = yell;
        this.level = level;
        this.stamina = stamina;
        // 血 = 等級 x 耐力
        this.hp = getFullHp();
        this.attack = attack;
        this.defense = defense;
        this.catchChance = catchChance;
        this.fastMove = fastMove;
        this.chargeMove = chargeMove;
    }

    @Override
    public int attack(Pokemon enemy, Move move) {
        // 總傷害 = 攻擊力 + 技能傷害
        int totalDamage = getAttack() + move.getPower();
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
        double resultDamage = this.attack(enemy, move);
        String text = String.format(
                "%s利用%s攻擊 對%s造成 %s 傷害,%n %3$sHP剩下 %s %n",
                this.getName(), move.getName(), enemy.getName(), resultDamage, enemy.getHp());
        return text;
    }


    /**
     * 取得野生寶可夢
     * @return 回傳野生寶可夢
     */
    public static List<Pokemon> getFieldPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        Pokemon greenbird = new Pokemon(R.drawable.pokemon_greenbird,
                R.drawable.pokemon_greenbird_back,
                "青綿鳥",
                R.raw.greenbird,
                1,
                90,
                76,
                139,
                10,
                new Move("啄", 12),
                new Move("冰凍光束", 90)
        );
        Pokemon slowpoke = new Pokemon(R.drawable.pokemon_slowpoke,
                R.drawable.pokemon_slowpoke_back,
                "呆呆獸",
                R.raw.slowpoke,
                1,
                180,
                109,
                109,
                40,
                new Move("念力", 20),
                new Move("心靈幻象", 100)
        );
        Pokemon loong = new Pokemon(R.drawable.pokemon_loong,
                R.drawable.pokemon_loong_back,
                "溶食獸",
                R.raw.loong,
                1,
                140,
                80,
                99,
                25,
                new Move("碎石", 15),
                new Move("灰塵射擊", 130)
        );
        Pokemon bokpe = new Pokemon(R.drawable.pokemon_bokpe,
                R.drawable.pokemon_bokpe_back,
                "波克比",
                R.raw.bokpe,
                1,
                70,
                67,
                116,
                12,
                new Move("啄擊", 10),
                new Move("原始之力", 70)
        );

        Pokemon pee = new Pokemon(R.drawable.pokemon_pee,
                R.drawable.pokemon_pee_back,
                "皮寶寶",
                R.raw.pee,
                1,
                100,
                75,
                91,
                12,
                new Move("意念頭槌",12),
                new Move("信號光線",75)
        );

        pokemons.add(greenbird);
        pokemons.add(slowpoke);
        pokemons.add(loong);
        pokemons.add(bokpe);
        pokemons.add(pee);

        return pokemons;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public int getYell() {
        return yell;
    }

    public void setYell(int yell) {
        this.yell = yell;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getHp() {
        return hp;
    }

    public int getFullHp() {
        return level * stamina;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCatchChance() {
        return catchChance;
    }

    public void setCatchChance(int catchChance) {
        this.catchChance = catchChance;
    }

    public Move getFastMove() {
        return fastMove;
    }

    public void setFastMove(Move fastMove) {
        this.fastMove = fastMove;
    }

    public Move getChargeMove() {
        return chargeMove;
    }

    public void setChargeMove(Move chargeMove) {
        this.chargeMove = chargeMove;
    }

    public static int getPeeCount() {
        return peeCount;
    }

    public static void setPeeCount(int peeCount) {
        Pokemon.peeCount = peeCount;
    }

    public static int getLongCount() {
        return longCount;
    }

    public static int getBirdCount() {
        return birdCount;
    }

    public static void setBirdCount(int birdCount) {
        Pokemon.birdCount = birdCount;
    }

    public static int getBokpeCount() {
        return bokpeCount;
    }

    public static void setBokpeCount(int bokpeCount) {
        Pokemon.bokpeCount = bokpeCount;
    }

    public static int getSlowPokeCount() {
        return slowPokeCount;
    }

    public static void setSlowPokeCount(int slowPokeCount) {
        Pokemon.slowPokeCount = slowPokeCount;
    }

    public static void setLongCount(int longCount) {
        Pokemon.longCount = longCount;
    }

    public static List<Pokemon> getMyPokemons() {
        return myPokemons;
    }



}
