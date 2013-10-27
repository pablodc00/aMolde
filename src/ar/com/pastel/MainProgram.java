package ar.com.pastel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author pablo.dcristofaro
 * 
 */
public class MainProgram {

    private static String[][] molde = { { "", "", "" }, { "", "", "" }, { "", "", "" } };

    private static int ricos = 0;

    /**
     * Con esta lista controlo que no se cuenten dos veces por ejemplo: D1,D2,D3 y D1,D3,D2 o bien C1, M, C3 y M, C2, C3
     * ya que es una permutacion donde no importa el orden y estos ejemplos representan un mismo ingrediente.
     */
    private static List<String> duplicados = new ArrayList<String>();

    /**
     * @param args
     */
    public static void main(String[] args) {

        int n = 9; // Tipos para escoger
        int r = 9; // Elementos elegidos

        String[] ingredientes1 = "D1,D2,D3,F1,F2,F3,C1,C2,C3".split(",");
        permutar(ingredientes1, "", n, r);

        String[] ingredientes2 = "M,D2,D3,F1,F2,F3,C1,C2,C3".split(",");
        permutar(ingredientes2, "", n, r);

        String[] ingredientes3 = "D1,D2,D3,M,F2,F3,C1,C2,C3".split(",");
        permutar(ingredientes3, "", n, r);

        String[] ingredientes4 = "D1,D2,D3,F1,F2,F3,M,C2,C3".split(",");
        permutar(ingredientes4, "", n, r);

        System.out.println("Se pueden realizar una cantidad de " + String.valueOf(ricos) + " pasteles ricos.");
    }

    private static void permutar(String[] elem, String act, int n, int r) {
        if (n == 0) {
            // System.out.println(act);
            pasarAMolde(act);
            verificaRico();
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    permutar(elem, act + elem[i] + ", ", n - 1, r);
                }
            }
        }
    }

    private static void pasarAMolde(String act) {
        String[] stringMolde = act.split(",");
        molde[0][0] = stringMolde[0].trim();
        molde[0][1] = stringMolde[1].trim();
        molde[0][2] = stringMolde[2].trim();
        molde[1][0] = stringMolde[3].trim();
        molde[1][1] = stringMolde[4].trim();
        molde[1][2] = stringMolde[5].trim();
        molde[2][0] = stringMolde[6].trim();
        molde[2][1] = stringMolde[7].trim();
        molde[2][2] = stringMolde[8].trim();
    }

    /**
     * Imprime el molde;
     */
    private static void imprimeMolde() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(molde[i][j] + ", ");
            }
        }
        System.out.println("");
    }

    /**
     * Recorre el molde en el estado actual y evalua si es rico o no.
     * 
     * @return 1 o 0
     */
    private static void verificaRico() {

        String idCombinacion = armaIdCombinacion();

        if (!idCombinacion.trim().isEmpty()) {
            if (!duplicados.contains(idCombinacion)) {
                duplicados.add(idCombinacion);
                ricos++;
                imprimeMolde();
            }
        }
    }

    /**
     * Retorna un Id para identifica una combinacion de Ingredientes, para que no se cuenten como diferentes y luego
     * sean sumados por ej: D1, D2, D3 y D1, D3, D1 o bien M, C2, C3 y C1, M, C3
     * 
     * @return Id
     */
    private static String armaIdCombinacion() {
        String id = "";
        String[] fila = { "", "", "" };
        String[] columna = { "", "", "" };

        // filas
        for (int i = 0; i < fila.length; i++) {
            fila[0] = molde[i][0].trim();
            fila[1] = molde[i][1].trim();
            fila[2] = molde[i][2].trim();

            if (evaluate(fila)) {
                id = id.concat(getFlagDuplicado(fila, i, "Fil"));
            }
        }

        // columans
        for (int i = 0; i < columna.length; i++) {
            columna[0] = molde[0][i].trim();
            columna[1] = molde[1][i].trim();
            columna[2] = molde[2][i].trim();

            if (evaluate(columna)) {
                id = id.concat(getFlagDuplicado(columna, i, "Col"));
            }
        }

        return id;
    }

    /**
     * Retorna un String para agregar en la list de control de duplicados. Para que no se cuenten como distintos por ej:
     * D1,D2,M y M,D2,D3, ya que en este caso es la combinacion de un mismo ingrediente y no importa el orden.
     * 
     * Requiere que se haya evaluado previamente si la fila o columna es un ingrediente valido mediante el metodo
     * private static boolean evaluate(String[] filaColumna)
     * 
     * @param filaColumna
     * @param i
     * @return String
     */
    private static String getFlagDuplicado(String[] filaColumna, int index, String esFilaOColumna) {
        String cadena = filaColumna[0].substring(0, 1);
        if (!cadena.equals(filaColumna[1].substring(0, 1))) {
            cadena = cadena.concat(filaColumna[1].substring(0, 1));
        } else if (!cadena.equals(filaColumna[2].substring(0, 1))) {
            cadena = cadena.concat(filaColumna[2].substring(0, 1));
        }
        cadena = cadena.concat(esFilaOColumna);
        cadena = cadena.concat(String.valueOf(index));

        return cadena;
    }

    /**
     * Evalua si el array de tres elementos contiene 3 ingredientes iguales o bien 2 iguales y una Masita
     * 
     * @param filaColumna
     * @return boolean
     */
    private static boolean evaluate(String[] filaColumna) {
        String primerElemento = filaColumna[0].substring(0, 1);

        if ((filaColumna[1].startsWith(primerElemento) || filaColumna[1].equals("M"))
                && (filaColumna[2].startsWith(primerElemento) || filaColumna[2].equals("M"))) {
            return true;
        }
        return false;
    }

}
