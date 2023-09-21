package pokemon2de2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Pokemon;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LeerJson {

    void leerJson() {
        String rutaDelArchivo = "C:/Users/Eva/Documents/2daw/dwservidor/ProyectoJSON/practicaJson/src/data/pokemon.json";
        Gson gson = new Gson();
        try {
            String json = Files.readString(Path.of(rutaDelArchivo));
            Type pokemonListType = new TypeToken<List<Pokemon>>() {
            }.getType();
            List<Pokemon> pokemons = gson.fromJson(json, pokemonListType);

            pokemons.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LeerJson js = new LeerJson();
        js.leerJson();
    }
}
