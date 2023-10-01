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
>
**PokedexManager**
> La clase PokedexManager se encarga de gestionar la interacción con una base de datos que almacena información de Pokémon. Permite realizar operaciones como guardar un Pokémon en la base de datos, buscar Pokémon por nombre y recuperar todos los Pokémon almacenados.

## Controllers
**PokemonController**
>Es la responsable de gestionar la información y las operaciones relacionadas con Pokémon. Proporciona una interfaz para acceder y manipular los datos de la Pokedex, así como realizar consultas y cálculos específicos sobre los Pokémon almacenados, algunos de los métodos a destacar serían:

public Double getAverageWeaknesses() 
> Calculamos la media de debilidades entre los pokemons, para ello convertimos el stream de debilidades en un doubleStream y sacamos la media con **.average()**.

public String mostCommonWeakness()
> Sacamos la debilidad más común agrupando los pokemon por debilidad con **Collectors.groupingBy()** y sacamos el máximo valor de la lista con **.max()**.

public void exportToCSV() 
> Exporta los datos de Pokemon a un CSV usando **.toCSV()**, con **joining(\n)** unimos los strings y los separamos con un salto de linea.

 public void readCSV() 
 > Lee los datos de Pokémon desde un archivo CSV, con **.skip(1)** saltamos la primera línea, usamos map para convertir cada línea en un array de Strings y luego imprimimos cada array.
