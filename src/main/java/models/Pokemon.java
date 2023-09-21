package models;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
@Setter
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
    private double avgSpawns;
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

    public double getWeight() {
        String[] split = weight.split(" ");
        return Double.parseDouble(split[0]);
    }

    public double getHeight() {
        String[] split = height.split(" ");
        return Double.parseDouble(split[0]);
    }

    /*Devuelve el id, num, name, height y weight separados por ; para usarlos al imprimir el CSV*/
    public String toCSV() {
        return id + ";" + num + ";" + name + ";" + height + ";" + weight;
    }

}
