package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * La clase Pokemon representa a un Pokémon en el mundo Pokémon.
 * Cada instancia de esta clase contiene información detallada sobre un Pokémon, como su imagen, huevos, caramelo, número,
 * peso, tipo, debilidades, nombre, promedio de apariciones, multiplicadores, identificador, tiempo de aparición,
 * altura, probabilidad de aparición, evolución previa, cantidad de caramelo necesaria para evolucionar y evolución siguiente.
 * La clase proporciona métodos para acceder a información específica del Pokémon, como su peso y altura en formatos
 * numéricos, así como para convertir la información del Pokémon en formato CSV.
 *
 * @author Eva Gomez, Jaime Medina
 */

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

    /**
     * Constructor completo para la clase Pokemon.
     * @param img           La URL de la imagen del Pokémon.
     * @param egg           El tipo de huevo del Pokémon.
     * @param candy         El tipo de caramelo del Pokémon.
     * @param num           El número del Pokémon.
     * @param weight        El peso del Pokémon.
     * @param type          La lista de tipos del Pokémon.
     * @param weaknesses    La lista de debilidades del Pokémon.
     * @param name          El nombre del Pokémon.
     * @param avgSpawns     El promedio de apariciones del Pokémon.
     * @param multipliers   La lista de multiplicadores del Pokémon.
     * @param id            El identificador del Pokémon.
     * @param spawnTime     El tiempo de aparición del Pokémon.
     * @param height        La altura del Pokémon.
     * @param spawnChance   La probabilidad de aparición del Pokémon.
     * @param prevEvolution La lista de evoluciones previas del Pokémon.
     * @param candyCount    La cantidad de caramelo necesaria para evolucionar al Pokémon.
     * @param nextEvolution La lista de evoluciones siguientes del Pokémon.
     */

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

    /**
     * Constructor simplificado para la clase Pokemon que se utiliza para crear instancias con información básica.
     *
     * @param id     El identificador del Pokémon.
     * @param num    El número del Pokémon.
     * @param name   El nombre del Pokémon.
     * @param height La altura del Pokémon en metros.
     * @param weight El peso del Pokémon en kilogramos.
     */

    public Pokemon(int id, String num, String name, double height, double weight) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.height = height + " m";
        this.weight = weight + " kg";
    }

    /**
     * Obtiene el peso del Pokémon en formato numérico.
     *
     * @return El peso del Pokémon como un valor numérico.
     */
    public double getWeight() {
        String[] split = weight.split(" ");
        return Double.parseDouble(split[0]);
    }

    /**
     * Obtiene la altura del Pokémon en formato numérico.
     *
     * @return La altura del Pokémon como un valor numérico.
     */
    public double getHeight() {
        String[] split = height.split(" ");
        return Double.parseDouble(split[0]);
    }

    /**
     * Convierte la información del Pokémon en formato CSV.
     *
     * @return Una cadena que contiene el id, num, name, height y weight del Pokémon separados por ';'.
     */
    public String toCSV() { //Devuelve el id, num, name, height y weight separados por ; para usarlos al imprimir el CSV
        return id + ";" + num + ";" + name + ";" + height + ";" + weight;
    }

}
