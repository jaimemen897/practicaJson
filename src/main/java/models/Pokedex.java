package models;

import lombok.Data;

import java.util.List;

@Data
public class Pokedex {
    //lista donde se almacenan los pokemones
    private List<Pokemon> pokemon;

}
