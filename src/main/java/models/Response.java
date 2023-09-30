package models;
import lombok.Getter;

import java.util.List;

/**
 * La clase Response representa una respuesta que contiene una lista de objetos Pokemon.
 * Esta clase se utiliza para encapsular la respuesta de algún servicio o función que devuelve una lista de Pokémon.
 *
 * @author Eva Gomez, Jaime Medina
 */

@Getter
public class Response {
    /**
     * La lista de Pokémon contenida en la respuesta.
     */
    private List<Pokemon> pokemon;
}
