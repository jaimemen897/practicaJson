import controllers.PokemonController;
import models.Pokedex;

public class Main {
    public static void main(String[] args) {
        PokemonController pokemonController = PokemonController.getInstance();
        System.out.println(pokemonController.getPokemon(0));
    }
}
