package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonControllerTest {

    @Test
    void getLongestNamePokemon() {
        PokemonController pokemonController = PokemonController.getInstance();
        assertEquals("Wailord", pokemonController.getLongestNamePokemon().get().getName());
    }
}