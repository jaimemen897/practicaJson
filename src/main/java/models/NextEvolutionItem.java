package models;

import lombok.Getter;

/**
 * La clase NextEvolutionItem representa los datos de la evolución de un Pokémon.
 * Esta clase almacena información como el número y el nombre de la evolución.
 *
 * @author Eva Gomez, Jaime Medina
 */
@Getter
public class NextEvolutionItem {
    /**
     * El número de la evolución del Pokémon.
     * private
     */
    String num;
    /**
     * El nombre de la evolución del Pokémon.
     */
    private String name;
}
