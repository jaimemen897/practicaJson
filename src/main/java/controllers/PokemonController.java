package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import models.NextEvolutionItem;
import models.Pokedex;
import models.Pokemon;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * La clase PokemonController es responsable de gestionar la información y las operaciones relacionadas con Pokémon.
 * Proporciona una interfaz para acceder y manipular los datos de la Pokedex, así como realizar consultas y cálculos específicos
 * sobre los Pokémon almacenados.
 *
 * <p>Esta clase utiliza una instancia de Pokedex para cargar y administrar los datos de Pokémon desde un archivo JSON.
 * Ofrece diversas operaciones para obtener información sobre los Pokémon, como nombres, tipos, debilidades, evoluciones y más.
 * También permite exportar datos a un archivo CSV y leer datos desde un archivo CSV.
 *
 * @author Eva Gomez, Jaime Medina
 */
@Getter
public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;
    private final String dir = Paths.get("").toAbsolutePath() + File.separator + "data";


    private PokemonController() {
        loadPokedex();
    }

    public static PokemonController getInstance() {
        if (instance == null) {
            instance = new PokemonController();
        }
        return instance;
    }

    private void loadPokedex() {
        String paisesFile = dir + File.separator + "pokemon.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        try (Reader reader = Files.newBufferedReader(Paths.get(paisesFile))) {
            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {
            }.getType());
            System.out.println("Pokedex loaded! There are: " + pokedex.getPokemon().size());
        } catch (Exception e) {
            System.out.println("Error loading Pokedex!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Devuelve una lista con los nombres de los primeros 10 Pokémon en la Pokedex.
     *
     * @return Una lista de nombres de Pokémon.
     */
    public List<String> tenFirstNames() {
        return pokedex.getPokemon().stream()
                .limit(10)
                .map(Pokemon::getName)
                .toList();
    }

    /**
     * Devuelve una lista con los nombres de los últimos 5 Pokémon en la Pokedex.
     *
     * @return Una lista de nombres de Pokémon.
     */
    public List<String> fiveLastNames() {
        return pokedex.getPokemon().stream()
                .skip(pokedex.getPokemon().size() - 5)
                .map(Pokemon::getName)
                .toList();
    }

    /**
     * Obtiene los datos del Pokémon "Pikachu" de la Pokedex.
     *
     * @return El Pokémon "Pikachu" o null si no se encuentra.
     */
    public Pokemon pikachuData() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getName().equals("Pikachu"))
                .findFirst()
                .orElse(null);

    }

    /**
     * Devuelve una lista de nombres de Pokémon que son del tipo "Fuego".
     *
     * @return Una lista de nombres de Pokémon y sus tipos.
     */
    public List<String> fireType() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getType().contains("Fire"))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getType())
                .toList();
    }

    /**
     * Devuelve una lista de nombres de Pokémon que son débiles ante "Agua" o "Eléctrico".
     *
     * @return Una lista de nombres de Pokémon y sus debilidades.
     */
    public List<String> weaknessesWorE() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses().contains("Water") || pokemon.getWeaknesses().contains("Electric"))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getWeaknesses())
                .toList();
    }

    /**
     * Obtiene una lista de Pokémon que evolucionan a partir de "Charmander".
     *
     * @return Una lista de Pokémon evolucionados de "Charmander".
     */
    public List<Pokemon> charmanderEvolution() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getName().equals("Charmander"))
                .flatMap(pokemon -> pokemon.getNextEvolution().stream())
                .map(NextEvolutionItem::getName)
                .map(name -> pokedex.getPokemon().stream()
                        .filter(pokemon -> pokemon.getName().equals(name))
                        .findFirst()
                        .orElse(null))
                .toList();
    }

    /**
     * Cuenta la cantidad de Pokémon que tienen una única debilidad.
     *
     * @return El número de Pokémon con una única debilidad.
     */
    public long onlyOneWeakness() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses().size() == 1)
                .count();
    }

    /**
     * Obtiene el nombre del Pokémon con más debilidades en la Pokedex.
     *
     * @return El nombre del Pokémon con más debilidades.
     */
    public String mostWeaknesses() {
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingInt(pokemon -> pokemon.getWeaknesses().size()))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getWeaknesses().size())
                .orElse("");
    }

    /**
     * Obtiene el nombre del Pokémon con menos evoluciones en la Pokedex.
     *
     * @return El nombre del Pokémon con menos evoluciones.
     */
    public String lessEvolutions() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution() != null)
                .min(Comparator.comparingInt(pokemon -> pokemon.getNextEvolution().size()))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getNextEvolution().size())
                .orElse("");
    }

    /**
     * Obtiene una lista de nombres de Pokémon que evolucionan pero no son de tipo "Fuego".
     *
     * @return Una lista de nombres de Pokémon con evolución que no son de tipo "Fuego".
     */
    public List<String> evolutionNotFire() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution() != null)
                .flatMap(pokemon -> pokemon.getNextEvolution().stream())
                .map(NextEvolutionItem::getName)
                .filter(name -> pokedex.getPokemon().stream()
                        .filter(pokemon -> !pokemon.getType().contains("Fire"))
                        .map(Pokemon::getName)
                        .toList()
                        .contains(name))
                .toList();
    }

    /**
     * Obtiene el nombre del Pokémon más pesado en la Pokedex.
     *
     * @return El nombre del Pokémon más pesado.
     */
    public String heaviestPokemon() {
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingDouble(Pokemon::getWeight))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getWeight())
                .orElse("");
    }

    /**
     * Obtiene el nombre del Pokémon más alto en la Pokedex.
     *
     * @return El nombre del Pokémon más alto.
     */
    public String tallestPokemon() {
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingDouble(Pokemon::getHeight))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getHeight())
                .orElse("");
    }


    /**
     * Obtiene el nombre del Pokémon con el nombre más largo en la Pokedex.
     *
     * @return El nombre del Pokémon con el nombre más largo.
     */
    public String getLongestNamePokemon() {
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingInt(p -> p.getName().length()))
                .map(Pokemon::getName)
                .orElse("");
    }


    /**
     * Obtiene el nombre del Pokémon con el nombre más largo en la Pokedex.
     *
     * @return El nombre del Pokémon con el nombre más largo.
     */
    public Double getAverageWeight() {
        return pokedex.getPokemon().stream()
                .mapToDouble(Pokemon::getWeight)
                .average()
                .orElse(0.0);
    }

    /**
     * Calcula la altura promedio de todos los Pokémon en la Pokedex.
     *
     * @return La altura promedio de los Pokémon.
     */
    public Double getAverageHeight() {
        return pokedex.getPokemon().stream()
                .mapToDouble(Pokemon::getHeight)
                .average()
                .orElse(0.0);

    }

    /**
     * Calcula el promedio de la cantidad de evoluciones de los Pokémon en la Pokedex.
     *
     * @return El promedio de evoluciones de los Pokémon.
     */
    public Double getAverageEvolution() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution() != null)
                .mapToDouble(pokemon -> pokemon.getNextEvolution().size())
                .average()
                .orElse(0);

    }

    /**
     * Calcula el promedio de la cantidad de debilidades de los Pokémon en la Pokedex.
     *
     * @return El promedio de debilidades de los Pokémon.
     */
    public Double getAverageWeaknesses() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses() != null)
                .mapToDouble(pokemon -> pokemon.getWeaknesses().size())
                .average()
                .orElse(0.0);
    }

    /**
     * Obtiene una lista de tipos de Pokémon presentes en la Pokedex, junto con los nombres de los Pokémon de cada tipo.
     *
     * @return Una lista de tipos de Pokémon y los nombres de los Pokémon de cada tipo.
     */
    public List<String> getPokemonByType() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getType() != null)
                .flatMap(pokemon -> pokemon.getType().stream())
                .distinct()
                .map(type -> pokedex.getPokemon().stream()
                        .filter(pokemon -> pokemon.getType() != null)
                        .filter(pokemon -> pokemon.getType().contains(type))
                        .map(types -> type + " " + types.getName())
                        .toList())
                .flatMap(Collection::stream)
                .toList();
    }

    /**
     * Obtiene un mapa que relaciona las debilidades de Pokémon con los nombres de los Pokémon que tienen cada debilidad.
     *
     * @return Un mapa que asocia debilidades con nombres de Pokémon.
     */
  public Map<String, List<String>> getPokemonByWeaknesses() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses() != null)
                .flatMap(pokemon -> pokemon.getWeaknesses().stream())
                .distinct()
                .collect(Collectors.toMap(
                        weakness -> weakness,
                        weakness -> pokedex.getPokemon().stream()
                                .filter(pokemon -> pokemon.getWeaknesses() != null)
                                .filter(pokemon -> pokemon.getWeaknesses().contains(weakness))
                                .map(Pokemon::getName)
                                .toList()
                ));
    }

    /**
     * Obtiene un mapa que agrupa los Pokémon por la cantidad de evoluciones que tienen.
     *
     * @return Un mapa que agrupa Pokémon por cantidad de evoluciones.
     */
    public Map<Integer, List<String>> getPokemonByEvolutionNum() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution() != null)
                .collect(Collectors.groupingBy(
                        pokemon -> pokemon.getNextEvolution().size(),
                        Collectors.mapping(
                                Pokemon::getName,
                                Collectors.toList()
                        )
                ));
    }

    /**
     * Obtiene la debilidad más común entre los Pokémon de la Pokedex.
     *
     * @return La debilidad más común.
     */
    public String mostCommonWeakness(){
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses() != null)
                .flatMap(pokemon -> pokemon.getWeaknesses().stream())
                .collect(Collectors.groupingBy(
                        weakness -> weakness,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("");
    }

    /**
     * Exporta los datos de Pokémon a un archivo CSV.
     */
    public void exportToCSV() {
        String content = "id;num;name;height;weight\n";
        content += pokedex.getPokemon().stream().map(Pokemon::toCSV).collect(Collectors.joining("\n"));
        try {
            Files.writeString(Paths.get(dir + File.separator + "pokemon.csv"), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lee los datos de Pokémon desde un archivo CSV.
     */
    public void readCSV() {
        try (Stream<String> stream = Files.lines(Paths.get(dir + File.separator + "pokemon.csv"))) {
            stream.skip(1).map(line -> line.split(";")).forEach(line -> {
                System.out.println(Arrays.toString(line));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}












