package models;

import lombok.Getter;

import java.util.List;

@Getter
public class Response {
    //En esta clase se crea un objeto de tipo Response que contiene una lista de pokemones
    // para poder acceder a ellos desde el controlador
    private List<Pokemon> pokemon;

}
