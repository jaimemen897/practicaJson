package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;
    private final String dir = Paths.get("").toAbsolutePath() + File.separator + "src" + File.separator + "data";


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

        // Actualizar a try-with-resources
        try (Reader reader = Files.newBufferedReader(Paths.get(paisesFile))) {
            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {
            }.getType());
            System.out.println("Pokedex loaded! There are: " + pokedex.getPokemon().size());
        } catch (Exception e) {
            System.out.println("Error loading Pokedex!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Pokemon getPokemon(int index) {
        return pokedex.getPokemon().get(index);
    }

    /*Pokemon con el nombre más largo*/
    public Optional<Pokemon> getLongestNamePokemon() {
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingInt(p -> p.getName().length()));
    }

    /*Media de peso de los pokemon*/
    public Double getAverageWeight() {
        return pokedex.getPokemon().stream()
                .mapToDouble(Pokemon::getWeight)
                .average()
                .orElse(0.0);
    }

    /*Media de altura de los pokemon*/
    public Double getAverageHeight() {
        return pokedex.getPokemon().stream()
                .mapToDouble(Pokemon::getHeight)
                .average()
                .orElse(0.0);

    }

    /*Media de evoluciones de los pokemon*/
    public Double getAverageEvolution() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution() != null)
                .mapToDouble(pokemon -> pokemon.getNextEvolution().size())
                .average()
                .orElse(0.0);

    }

    /*Media de debilidades de los pokemon*/
    public Double getAverageWeaknesses() {
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses() != null)
                .mapToDouble(pokemon -> pokemon.getWeaknesses().size())
                .average()
                .orElse(0.0);
    }

    /*Pokemon agrupados por tipo*/
    public Map<String, List<Pokemon>> getPokemonByType() {
        return pokedex.getPokemon().stream()
                .flatMap(pokemon -> pokemon.getType().stream().map(type -> new AbstractMap.SimpleEntry<>(type, pokemon)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    /*Numero de pokemon agrupados por debilidad*/
    public Map<String, Long> getNumberOfPokemonByWeaknesses() {
        return pokedex.getPokemon().stream()
                .flatMap(pokemon -> pokemon.getWeaknesses().stream().map(weakness -> new AbstractMap.SimpleEntry<>(weakness, pokemon)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting()));
    }

    /*Pokemon agrupados por numero de evoluciones*/
    /*public Map<Integer, List<Pokemon>> getPokemonByNumberOfEvolutions() {
        return pokedex.getPokemon().stream()
                .flatMap(pokemon -> pokemon.getNextEvolution().stream().map(evolution -> new AbstractMap.SimpleEntry<>(evolution.getNum(), pokemon)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }*/

    /*Sacar la debilidad más común*/
    public List<String> getMostCommonWeakness() {
        return pokedex.getPokemon().stream()
                .flatMap(pokemon -> pokemon.getWeaknesses().stream())// usamos flatMap para que nos devuelva un stream de strings(stream es una lista de strings y el flatmap es un map pero de streams
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()))// agrupamos por debilidad(groupBy) y contamos(counting)
                .entrySet().stream()//convertimos el map en un stream de entrySet para poder ordenarlo y limitarlo a 1
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))// ordenamos por value de mayor a menor usando reverse porque por defecto ordena de menor a mayor
                .limit(1)//limitamos a 1 para que nos de la debilidad mas comun
                .map(Map.Entry::getKey)// mapeamos para que nos devuelva solo la debilidad y no el numero de veces que aparece
                .toList();
    }

    public void exportToCSV() {
        String content = "id;num;name;height;weight\n";
        content += pokedex.getPokemon().stream().map(Pokemon::toCSV).collect(Collectors.joining("\n"));
        try {
            Files.writeString(Paths.get(dir + File.separator + "pokemon.csv"), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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












