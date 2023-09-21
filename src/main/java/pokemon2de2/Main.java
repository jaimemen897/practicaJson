package pokemon2de2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.PokemonController;
import models.Pokemon;

import java.util.ArrayList;

public class Main {

    //obtener el nombre de los 10 primeros pokemons con funciones lambda
   public void obtenerNombre() {
        ArrayList<Pokemon> pk = new ArrayList<>();
        System.out.println("Obtener el nombre de los 10 primeros pokemons");
        pk.stream()
                .limit(10)
                .map(Pokemon::getName)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
       Main m = new Main();
       m.obtenerNombre();
    }
}
