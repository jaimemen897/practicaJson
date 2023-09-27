import controllers.PokemonController;
import models.Pokedex;
import models.Pokemon;
import services.DataBaseManager;
import services.PokedexManager;

public class Main {
    public static void main(String[] args) {
        PokedexManager pokedexManager = PokedexManager.getInstance(DataBaseManager.getInstance().getConnection());
        PokemonController pokemonController = PokemonController.getInstance();

        pokemonController.readCSV();

        for (Pokemon pokemon : pokemonController.getPokedex().getPokemon()) {
            pokedexManager.save(pokemon);
        }

        System.out.println("There are " + pokedexManager.findAll().size() + " pokemons in the database");
        System.out.println(pokedexManager.findByNombre("Pikachu"));

        System.out.println("Obtener el nombre de los 10 primeros pokemons");
        System.out.println(pokemonController.tenFirstNames());
        System.out.println("--------------------------------------------");
        System.out.println("Obtener el nombre de los 5 últimos pokemons");
        System.out.println(pokemonController.fiveLastNames());
        System.out.println("--------------------------------------------");
        System.out.println("Obtener los datos de Pikachu");
        System.out.println(pokemonController.pikachuData());
        System.out.println("--------------------------------------------");
        System.out.println("Obtener la evolución de Charmander");
        System.out.println(pokemonController.charmanderEvolution());
        System.out.println("--------------------------------------------");
        System.out.println("Obtener el nombre de los pokemons de tipo fire");
        System.out.println(pokemonController.fireType());
        System.out.println("--------------------------------------------");
        System.out.println("Obtener el nombre de los pokemons con debilidad water o electric");
        System.out.println(pokemonController.weaknessesWorE());
        System.out.println("--------------------------------------------");
        System.out.println("Número de pokemons con solo una debilidad");
        System.out.println(pokemonController.onlyOneWeakness());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemon con más debilidades");
        System.out.println(pokemonController.mostWeaknesses());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemon con menos evoluciones");
        System.out.println(pokemonController.lessEvolutions());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemon con una evolucion que no es de tipo fire");
        System.out.println(pokemonController.evolutionNotFire());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemon más pesado");
        System.out.println(pokemonController.heaviestPokemon());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemon más alto");
        System.out.println(pokemonController.tallestPokemon());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemon con el nombre mas largo");
        System.out.println(pokemonController.getLongestNamePokemon());
        System.out.println("--------------------------------------------");
        System.out.println("Media de peso de los pokemons");
        System.out.println(pokemonController.getAverageWeight());
        System.out.println("--------------------------------------------");
        System.out.println("Media de altura de los pokemons");
        System.out.println(pokemonController.getAverageHeight());
        System.out.println("--------------------------------------------");
        System.out.println("Media de evoluciones de los pokemons");
        System.out.println(pokemonController.getAverageEvolution());
        System.out.println("--------------------------------------------");
        System.out.println("Media de debilidades de los pokemons");
        System.out.println(pokemonController.getAverageWeaknesses());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemons agrupados por tipo");
        System.out.println(pokemonController.getPokemonByType());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemons agrupados por debilidad");
        System.out.println(pokemonController.getPokemonByWeaknesses());
        System.out.println("--------------------------------------------");
        System.out.println("Pokemons agrupados por número de evoluciones");
        System.out.println(pokemonController.getPokemonByEvolutionNum());
        System.out.println("--------------------------------------------");
        System.out.println("Sacar la debilidad más común");
        System.out.println(pokemonController.mostCommonWeakness());
        System.out.println("--------------------------------------------");
        pokemonController.exportToCSV();
        pokemonController.readCSV();
    }

}

