# Práctica Pokemons
## Realizada por Jaime Medina y Eva Gómez
<br>

**¿DE QUE TRATA?**
> En este proyecto hemos realizado la lectura de un fichero de tipo JSON, así como la obtención de unos datos concretos que se nos pedían a través
> de la api de colecciones funcionales. Después, hemos exportado algunos de sus datos (id del pokemon, número, nombre, peso y altura) a un archivo
> de tipo CSV, leído e impreso esos datos por pantalla y realizado el ejecutable. Finalmente dichos datos exportados al CSV, los hemos introducido
> en una base de datos, en nuestro caso de tipo H2, encapsulando los datos de conexión en un manejador y siendo leídos de un fichero de
> **(propiedades o entornos)**. Para la comprobación debíamos realizar un SELECT sacando todas las operaciones realizadas inicialmente por pantalla,
> además de realizar otro SELECT para sacar la información de Pikachu.
<br>

>Para este proyecto contamos con **(2)** directorios:
- **Controllers**
  - En él tenemos la clase PokemonController en el que realizamos todos los métodos necesarios para mostrar los datos que se nos piden. Estos métodos son:

 > - **public static PokemonController getInstance()**. Implementando el patrón Singleton, consiguiendo así que haya una única copia de PokemonController y
 > que todas las partes de nuestro programa puedan entrar a esta clase de manera más eficiente.<br>
 > - **private void loadPokedex()**. Con esta clase leemos todos los datos del JSON utilizando la biblioteca Gson y los introducimos en la clase pokedex
 > (en la que tenemos la lista de pokemons). Si la conversión del JSON se realizó con éxito, nos muestra un mensaje que indica que la Pokedex se ha cargado
 > correctamente y muestra la cantidad de Pokémon en ella (pokedex.getPokemon().size()). Por otro lado, si algo sale mal durante el proceso de lectura o
 > conversión del JSON, tiramos una excepción mostrando un mensaje de error en la consola y proporcionándonos la información sobre el error.
 > - **public Pokemon getPokemon(int index)**. Con este método simplemente pasamos por parámetro el indice de tipo Integer del pokemon que queremos mostrar
 > - **public List<String> tenFirstNames()**. Con este método obtenemos los 10 primeros nombres de los pokemons, para ellos limitamos la lista a 10 y extraemos
 > únicamente los nombres con un map, finalmente los metemos en una lista para mostrarlos (.toList()). Como vamos a devolver una lista de nombres, el método
 > sera tipo List<String>.
 > - **public List<String> fiveLastNames()**. Este método es muy parecido, pero al querer los 5 últimos nombres el cambio que haremos será omitir con un skip
 > todos los pokemons menos los cinco últimos, restamos al total de la lista 5, por lo que nos quedaría el skip de todos los pokemon menos esos 5 restados.
 > - **public List<Pokemon> pikachuData()**. Mostramos por pantalla los datos de Pikachu, por lo que el método ya no será de tipo String, sino una lista de Pokemons,
 > filtramos por nombre y lo añadimos a la lista para mostrarlo.
 > - **public List<String> charmanderEvolution()**. Mostramos las evoluciones de un pokemon, en este caso Charmander. Para ello debemos filtrar por evolución no null,
 > ya que si es null no podrá mostrarse, y por nombre, en este caso Charmander. Con un map devolvemos la lista de evoluciones (nextEvolution) por cada pokemon que cumpla
 > las condiciones anteriores, y con un flatMap extraemos solo el nombre de las evoluciones, finalmente lo devolvemos en una lista.
