package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
@Data
public class Pokemon {
    private String img;
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

    public Pokemon(String img, String egg, String candy, String num, String weight, List<String> type, List<String> weaknesses, String name, double avgSpawns, List<Object> multipliers, int id, String spawnTime, String height, Object spawnChance, List<PrevEvolutionItem> prevEvolution, int candyCount, List<NextEvolutionItem> nextEvolution) {
        this.img = img;
        this.egg = egg;
        this.candy = candy;
        this.num = num;
        this.weight = weight;
        this.type = type;
        this.weaknesses = weaknesses;
        this.name = name;
        this.avgSpawns = avgSpawns;
        this.multipliers = multipliers;
        this.id = id;
        this.spawnTime = spawnTime;
        this.height = height;
        this.spawnChance = spawnChance;
        this.prevEvolution = prevEvolution;
        this.candyCount = candyCount;
        this.nextEvolution = nextEvolution;
    }

    public Pokemon(int id, String num, String name, double height, double weight) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.height = height + " m";
        this.weight = weight + " kg";
    }

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
