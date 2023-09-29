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

@Getter
public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;
    //lee el directorio donde se encuentra el proyecto
    private final String dir = Paths.get("").toAbsolutePath() + File.separator + "data";


    private PokemonController() {
        loadPokedex();
    }

    public static PokemonController getInstance() { //hacemos el patrón singleton para que solo se pueda instanciar una vez
        if (instance == null) {
            instance = new PokemonController();
        }
        return instance;
    }

    private void loadPokedex() { //cargamos el fichero json en la variable pokedex y mostramos el número de pokemon que hay
        String paisesFile = dir + File.separator + "pokemon.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Leemos el fichero y lo convertimos en un objeto Pokedex con la librería Gson
        try (Reader reader = Files.newBufferedReader(Paths.get(paisesFile))) {
            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {
            }.getType());
            System.out.println("Pokedex loaded! There are: " + pokedex.getPokemon().size());
        } catch (Exception e) { //si no se puede cargar el fichero, mostramos un mensaje de error
            System.out.println("Error loading Pokedex!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> tenFirstNames() { //devuelve una lista con los 10 primeros nombres de los pokemon
        return pokedex.getPokemon().stream()
                .limit(10)
                .map(Pokemon::getName)
                .toList();
    }

    public List<String> fiveLastNames() { //devuelve una lista con los 5 últimos nombres de los pokemon
        return pokedex.getPokemon().stream()
                .skip(pokedex.getPokemon().size() - 5)
                .map(Pokemon::getName)
                .toList();
    }

    public Pokemon pikachuData() { //devuelve los datos de un pokemon, en este caso de Pikachu
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getName().equals("Pikachu"))
                .findFirst()
                .orElse(null);

    }

    public List<String> fireType() { //devuelve una lista con los nombres y tipos de los pokemon de tipo fuego
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getType().contains("Fire"))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getType())
                .toList();
    }

    public List<String> weaknessesWorE() { //devuelve una lista con los nombres y debilidades de los pokemon de tipo agua o eléctrico
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses().contains("Water") || pokemon.getWeaknesses().contains("Electric"))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getWeaknesses())
                .toList();
    }

    public List<Pokemon> charmanderEvolution() { //devuelve una lista con el pokemon que evoluciona de Charmander y sus datos
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


    public long onlyOneWeakness() { //devuelve el número de pokemon que solo tienen una debilidad
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses().size() == 1)
                .count();
    }

    public String mostWeaknesses() { //devuelve el nombre del pokemon que tiene más debilidades
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingInt(pokemon -> pokemon.getWeaknesses().size()))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getWeaknesses().size())
                .orElse("");
    }

    public String lessEvolutions() { //devuelve el nombre del pokemon que tiene menos evoluciones
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution() != null)
                .min(Comparator.comparingInt(pokemon -> pokemon.getNextEvolution().size()))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getNextEvolution().size())
                .orElse("");
    }

    public List<String> evolutionNotFire() { //devuelve una lista con los nombres de los pokemon que evolucionan de
                                        // otro pokemon que no sea de tipo fuego
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

    public String heaviestPokemon() { //devuelve el nombre del pokemon más pesado
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingDouble(Pokemon::getWeight))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getWeight())
                .orElse("");
    }

    public String tallestPokemon() { //devuelve el nombre del pokemon más alto
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingDouble(Pokemon::getHeight))
                .map(pokemon -> pokemon.getName() + " " + pokemon.getHeight())
                .orElse("");
    }

    public String getLongestNamePokemon() { //devuelve el nombre del pokemon con el nombre más largo
        return pokedex.getPokemon().stream()
                .max(Comparator.comparingInt(p -> p.getName().length()))
                .map(Pokemon::getName)
                .orElse("");
    }

    public Double getAverageWeight() { //devuelve la media de peso de los pokemon
        return pokedex.getPokemon().stream()
                .mapToDouble(Pokemon::getWeight)
                .average()
                .orElse(0.0);
    }

    public Double getAverageHeight() { //devuelve la media de altura de los pokemon
        return pokedex.getPokemon().stream()
                .mapToDouble(Pokemon::getHeight)
                .average()
                .orElse(0.0);

    }

    public Double getAverageEvolution() { //devuelve la media de evoluciones de los pokemon
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution() != null)
                .mapToDouble(pokemon -> pokemon.getNextEvolution().size())
                .average()
                .orElse(0);

    }

    public Double getAverageWeaknesses() { //devuelve la media de debilidades de los pokemon
        return pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses() != null)
                .mapToDouble(pokemon -> pokemon.getWeaknesses().size())
                .average()
                .orElse(0.0);
    }

    public List<String> getPokemonByType() { //devuelve una lista con los nombres de los pokemon agrupados por tipo
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

  public Map<String, List<String>> getPokemonByWeaknesses() { //devuelve un mapa con las debilidades de los pokemon
                                                       // y los nombres de los pokemon que tienen esa debilidad
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

    public Map<Integer, List<String>> getPokemonByEvolutionNum() { //devuelve un mapa con el número de evoluciones de
                                 // los pokemon y los nombres de los pokemon que tienen ese número de evoluciones
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


    public String mostCommonWeakness(){ //devuelve la debilidad que más se repite
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

    public void exportToCSV() { //exporta los datos de los pokemon a un fichero csv
        String content = "id;num;name;height;weight\n";
        //pasamos los datos de los pokemon a un string y los guardamos en el fichero csv
        content += pokedex.getPokemon().stream().map(Pokemon::toCSV).collect(Collectors.joining("\n"));
        try {
            //creamos el fichero csv en el directorio del proyecto
            Files.writeString(Paths.get(dir + File.separator + "pokemon.csv"), content);
        } catch (IOException e) {
            //si no se puede crear el fichero, mostramos un mensaje de error
            throw new RuntimeException(e);
        }
    }

    public void readCSV() { //mostamos los datos del fichero csv por pantalla
        try (Stream<String> stream = Files.lines(Paths.get(dir + File.separator + "pokemon.csv"))) {
            stream.skip(1).map(line -> line.split(";")).forEach(line -> {
                System.out.println(Arrays.toString(line));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}












