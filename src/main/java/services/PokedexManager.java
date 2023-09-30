package services;

import models.Pokemon;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase PokedexManager se encarga de gestionar la interacción con una base de datos que almacena información de Pokémon.
 * Permite realizar operaciones como guardar un Pokémon en la base de datos, buscar Pokémon por nombre y recuperar todos los Pokémon almacenados.
 *
 * <p>La instancia de esta clase se crea mediante el patrón Singleton para garantizar que solo haya una instancia activa
 * de PokedexManager en la aplicación.
 *
 * @author Eva Gomez, Jaime Medina
 */
public class PokedexManager {
    private static PokedexManager instance;
    private final Connection connection;


    /**
     * Constructor privado de PokedexManager. Se utiliza para crear una instancia única de la clase.
     *
     * @param connection La conexión a la base de datos que se utilizará para las operaciones.
     */
    private PokedexManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Obtiene una instancia única de PokedexManager utilizando el patrón Singleton.
     *
     * @param connection La conexión a la base de datos.
     * @return La instancia única de PokedexManager.
     */
    public static PokedexManager getInstance(Connection connection) {
        if (instance == null) {
            instance = new PokedexManager(connection);
        }
        return instance;
    }

    /**
     * Guarda un Pokémon en la base de datos.
     *
     * @param pokemon El Pokémon que se va a guardar en la base de datos.
     */
    public void save(Pokemon pokemon) {
        try {
            String sqlQuery = "INSERT INTO POKEMON (id, num, name, height, weight) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, pokemon.getId());
            statement.setInt(2, Integer.parseInt(pokemon.getNum()));
            statement.setString(3, pokemon.getName());
            statement.setDouble(4, pokemon.getHeight());
            statement.setDouble(5, pokemon.getWeight());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    /**
     * Recupera todos los Pokémon almacenados en la base de datos.
     *
     * @return Una lista de Pokémon almacenados en la base de datos.
     */
    public List<Pokemon> findAll(){
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM POKEMON");
            while (resultSet.next()) {
                pokemons.add(new Pokemon(
                        resultSet.getInt("id"),
                        resultSet.getString("num"),
                        resultSet.getString("name"),
                        resultSet.getDouble("height"),
                        resultSet.getDouble("weight")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll: " + e.getMessage());
        }
        return pokemons;
    }

    /**
     * Busca un Pokémon por nombre en la base de datos.
     *
     * @param nombre El nombre del Pokémon a buscar.
     * @return El Pokémon encontrado o null si no se encuentra.
     */
    public Pokemon findByNombre(String nombre){
        Pokemon pokemon = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM POKEMON WHERE name = ?");
            statement.setString(1, nombre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pokemon = new Pokemon(
                        resultSet.getInt("id"),
                        resultSet.getString("num"),
                        resultSet.getString("name"),
                        resultSet.getDouble("height"),
                        resultSet.getDouble("weight")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en findByNombre: " + e.getMessage());
        }
        return pokemon;
    }
}
