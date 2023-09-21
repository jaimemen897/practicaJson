package models;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public class Pokemon {
    private String img;

    @Override
    public String toString() {
        return "Pokemon{" +
                "img='" + img + '\'' +
                ", egg='" + egg + '\'' +
                ", candy='" + candy + '\'' +
                ", num='" + num + '\'' +
                ", weight='" + weight + '\'' +
                ", type=" + type +
                ", weaknesses=" + weaknesses +
                ", name='" + name + '\'' +
                ", avgSpawns=" + avgSpawns +
                ", multipliers=" + multipliers +
                ", id=" + id +
                ", spawnTime='" + spawnTime + '\'' +
                ", height='" + height + '\'' +
                ", spawnChance=" + spawnChance +
                ", prevEvolution=" + prevEvolution +
                ", candyCount=" + candyCount +
                ", nextEvolution=" + nextEvolution +
                '}';
    }

    private String egg;
    private String candy;
    private String num;
    private String weight;
    private List<String> type;
    private List<String> weaknesses;
    private String name;
    @SerializedName("avg_spawns")
    private int avgSpawns;
    private List<Object> multipliers;
    private int id;
    @SerializedName("spawn_time")
    private String spawnTime;
    private String height;
    @SerializedName("spawn_chance")
    private Object spawnChance;
    @SerializedName("prev_evolution")
    private List<PrevEvolutionItem> prevEvolution;
    @SerializedName("candy_count")
    private int candyCount;
    @SerializedName("next_evolution")
    private List<NextEvolutionItem> nextEvolution;

    public String getImg() {
        return img;
    }

    public String getEgg() {
        return egg;
    }

    public String getCandy() {
        return candy;
    }

    public String getNum() {
        return num;
    }

    public String getWeight() {
        return weight;
    }

    public List<String> getType() {
        return type;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public String getName() {
        return name;
    }

    public int getAvgSpawns() {
        return avgSpawns;
    }

    public List<Object> getMultipliers() {
        return multipliers;
    }

    public int getId() {
        return id;
    }

    public String getSpawnTime() {
        return spawnTime;
    }

    public String getHeight() {
        return height;
    }

    public Object getSpawnChance() {
        return spawnChance;
    }

    public List<PrevEvolutionItem> getPrevEvolution() {
        return prevEvolution;
    }

    public int getCandyCount() {
        return candyCount;
    }

    public List<NextEvolutionItem> getNextEvolution() {
        return nextEvolution;
    }

}
