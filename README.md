# Práctica Pokemons de Jaime Medina y Eva Gómez
## Funcionamiento principal
<br>

**¿DE QUE TRATA?**
> En este proyecto hemos realizado la lectura de un fichero de tipo JSON, así como la obtención de datos a través
 de la api de colecciones funcionales. Para procesar estos datos creamos la clase Pokemon  en el que tenemos como atributos todos estos campos.
<br>

## Base de datos
**DBM**
>En la carpeta services nos encontramos con la clase DataBaseManager, esta clase se encarga de lo relacionado con la base de datos. En esta clase el método a destacar es openConnection, en él cargamos las propiedades del archivo database.properties con la clase Properties, establecemos la conexión con DriverManager y ejecutamos el archivo init.sql en el que tenemos la sentencia para crear la tabla Pokemon.
**PokedexManager**
>

## Controllers
**PokemonController**
>Es la responsable de gestionar la información y las operaciones relacionadas con Pokémon. Proporciona una interfaz para acceder y manipular los datos de la Pokedex, así como realizar consultas y cálculos específicos sobre los Pokémon almacenados, algunos de los métodos a destacar serían:

