import controllers.PokemonController;
import models.Pokemon;
import services.DataBaseManager;
import services.PokedexManager;

public class Main {
    public static void main(String[] args) {
        PokedexManager pokedexManager = PokedexManager.getInstance(DataBaseManager.getConnection());
        PokemonController pokemonController = PokemonController.getInstance();

        pokemonController.readCSV();

        for (Pokemon pokemon : pokemonController.getPokedex().getPokemon()) {
            pokedexManager.save(pokemon);
        }

        System.out.println("There are " + pokedexManager.findAll().size() + " pokemons in the database");
        System.out.println(pokedexManager.findByNombre("Pikachu"));

    }

}

