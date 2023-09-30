package models;

import lombok.Data;

import java.util.List;

/**
 * La clase Pokedex representa una lista de Pokémon en la aplicación.
 * Contiene una lista de objetos de la clase Pokemon.
 *
 * @author Eva Gomez, Jaime Medina
 */
@Data
public class Pokedex {
    /**
     * La lista de Pokémon en la Pokedex.
     */
    private List<Pokemon> pokemon;

    public List<Pokemon> getPokemon() {
        return pokemon;
    }
}
