import controllers.PokemonController;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PokemonController pokemonController = PokemonController.getInstance();
        /*System.out.println(pokemonController.getPokemon(0));
        System.out.println(pokemonController.getLongestNamePokemon().get());
        System.out.println("Media de peso: " + pokemonController.getAverageWeight());
        System.out.println("Media de altura: " + pokemonController.getAverageHeight());
        System.out.println("Media de evoluciones: " + pokemonController.getAverageEvolution());
        System.out.println("Media de debilidades: " + pokemonController.getAverageWeaknesses());
        System.out.println("Pokemon agrupados por tipo: " + pokemonController.getPokemonByType());
        System.out.println("Pokemon agrupados por debilidad: " + pokemonController.getNumberOfPokemonByWeaknesses());*/
        /*Map<String, List<Pokemon>> mapa = pokemonController.getPokemonByType();

        for (Map.Entry<String, List<Pokemon>> entry : mapa.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().size());
        }*/

        /*Map<String, Long> mapa2 = pokemonController.getNumberOfPokemonByWeaknesses();

        for (Map.Entry<String, Long> entry : mapa2.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/

        /*System.out.println(pokemonController.getMostCommonWeakness());*/

        pokemonController.readCSV();
    }
}
