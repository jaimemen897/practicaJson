package services;

import lombok.Getter;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * La clase DataBaseManager se encarga de gestionar la conexión y configuración de la base de datos.
 * Proporciona métodos para obtener una instancia única de la clase, abrir y cerrar la conexión con la base de datos,
 * y obtener la conexión activa.
 *
 * @author Eva Gomez, Jaime Medina
 */

@Getter
public class DataBaseManager implements AutoCloseable {
    private static DataBaseManager instance;;
    private static final String propertiesPath = Paths.get("").toAbsolutePath() + File.separator + "resources" + File.separator + "database.properties";
    private static final String initPath = Paths.get("").toAbsolutePath() + File.separator + "resources" + File.separator + "init.sql";
    private static Connection connection;

    private DataBaseManager() {
        openConnection();
    }

    /**
     * Obtiene una instancia única de la clase DataBaseManager.
     * Si no existe una instancia previa, crea una nueva y la devuelve.
     *
     * @return La instancia única de DataBaseManager.
     */
    public static DataBaseManager getInstance() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver: " + e.getMessage());
        }
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    /**
     * Abre la conexión con la base de datos utilizando la configuración cargada desde el archivo properties
     * y ejecuta los scripts SQL de inicialización.
     */
    private void openConnection() {
        try {
            InputStream dbProps = ClassLoader.getSystemResourceAsStream("database.properties");
            Properties properties = new Properties();
            properties.load(dbProps);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String init = properties.getProperty("db.init");
            connection = DriverManager.getConnection(url, user, password);
            Reader reader = new BufferedReader(new FileReader(initPath));
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(reader);

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de propiedades: " + e.getMessage());
        }
    }

    /**
     * Cierra la conexión activa con la base de datos.
     * Si se produce un error al cerrar la conexión, se maneja y se muestra un mensaje de error.
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión con la base de datos: " + e.getMessage());
        }
    }

    /**
     * Obtiene la conexión activa con la base de datos.
     *
     * @return La conexión activa con la base de datos.
     */
    public Connection getConnection() {
        return connection;
    }

}
