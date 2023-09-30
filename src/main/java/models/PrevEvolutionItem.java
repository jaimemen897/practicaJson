package models;

import lombok.Getter;

/**
 * La clase PrevEvolutionItem representa un ítem que contiene información sobre una pre-evolución de un Pokémon.
 * Cada ítem de esta clase tiene un número y un nombre asociado.
 *
 * @author Eva Gomez, Jaime Medina
 */
@Getter
public class PrevEvolutionItem {
    /**
     * El número de la pre-evolución del Pokémon.
     */
    private String num;
    /**
     * El nombre de la pre-evolución del Pokémon.
     */
    private String name;
}
