El proyecto se puede abrir desde Eclipse.
Estan subidos los archivos de configuracion de Eclipse.
El build path esta configurado para Java 7, pero si usas Java 6
hay que cambiar el build path de Eclipse.

Es una sola clase Java que es de tipo main, se puede correr desde Eclipse,
imprime los resultados en la consola.


Nota:
solo para ordenar y tener todas las posibles combinaciones de ingredientes
los llame:
D1,D2,D3 a los dulces,
F1,F2,F3 a las frutas,
C1,C2,C3 a los confites,
M a la masita.

Ahora bien, tomo como una combinacion D1,D2,D3 es lo mismo que D1,D3,D2 y no la cuento 2 veces,
es decir, una permutacion donde no importa el orden.
Lo mismo para el caso de F1,M,F3 y F1,F2,M, son lo mismo y lo cuento una sola vez.
Lo mismo para el caso en mas de una fila:
por ej: si en una combinacion de ingredientes donde el Molde en la fila 1 tengo D1,D2,D3 y en la fila 2 tengo F1,F2,F3,
cuando tenga otra combinacion de ingredientes donde el Molde en la fila 1 tengo D3,D2,D1 y en la fila 2 tengo F2,F1,F3,
lo cuento como una sola combinacion. Para esto armo un identificados por combinacion representado en List<String> duplicados
donde se va a generar un codigo que me va a indicar, par el ejemplo dado, un mismo valor, por lo tanto al controlar
dicha variable no lo voy a contar esa combinacion de ingredientes 2 veces, solo se va a contar 1 vez.


Pablo D'Cristofaro
pablodc00@gmail.com
