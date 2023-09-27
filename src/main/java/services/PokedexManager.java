package services;

import models.Pokemon;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokedexManager {
    /*private static final String csvFilePath = "src" + File.separator + "data" + File.separator + "pokemon.csv";*/
    private static PokedexManager instance;
    private final Connection connection;

    private PokedexManager(Connection connection) {
        this.connection = connection;
    }

    public static PokedexManager getInstance(Connection connection) {
        if (instance == null) {
            instance = new PokedexManager(connection);
        }
        return instance;
    }

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
