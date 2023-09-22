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
 > - **public Pokemon pikachuData()**. Mostramos por pantalla los datos de Pikachu, por lo que el método ya no será de tipo String, sino una lista de Pokemons,
 > filtramos por nombre y lo añadimos a la lista para mostrarlo.
 > - **public List<Pokemon> charmanderEvolution()**
 > - **public List<String> fireType()**. Sacamos por pantalla los nombres de todos los pokemon que sean de tipo fuego, para ello filtamos por tipo con un .contains() ya que
 > los tipos estan guardados en una lista.
 > - **public List<String> weaknessesWorE()**. Sacamos por pantalla los nombres de todos los pokemon con debilidades agua o electricidad, como las debilidades están 
 > guardadas en una lista volvemos a compararlas con el .contains(), al ser una debilidad u otra separamos por barras (||).
 > - **public long onlyOneWeakness()**. Sacamos por pantalla el número de pokemon con solo una debilidad, para ello simplemente filtraremos la debilidad a la que sea
 > igual a 1, las contamos con .count() y devolvemos un long.
 > - **public String pokemonWithMaxWeaknesses()**. Devolvemos el nombre del pokemon con más debilidades, para ello usamos .max() para que devuelva el valor más alto y 
 > dentro comparamos las debilidades con Comparator.comparingInt()
 > - **public String pokemonWithMinEvolutions()**. Sacamos por pantalla el nombre del pokemon con menos evoluciones, este método sería igual que el anterior pero esta 
 > vez usariamos .min() para obtener el valor mínimo. 
 > - **public List<String> evolutionNotFire()**.
 > - **public String heaviestPokemon()**. Sacamos por pantalla el pokemon más pesado, usamos .max() y dentro comparamos los pesos de los pokemon con 
 > Comparator.comparingDouble() y lo metemos en la lista para sacarlo por pantalla.
 > - **public String tallestPokemon()**. Este método sería igual que el anterior pero comparando alturas.
 >  - **public Optional<Pokemon> getLongestNamePokemon()**.
