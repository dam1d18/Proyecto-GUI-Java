/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonobjetos;

/**
 *
 * @author Victor
 */
public class Metodos {

    public static int Random(int num) {
        int random;
        random = RedondearAlAlza(Math.random() * num);
        return random;
    }

    public static int RandomSinRound(int num) {
        int random;
        random = (int) (Math.random() * num);
        return random;
    }

    public static int RedondearAlAlza(double doble) {
        int entero;
        entero = (int) Math.ceil(doble);
        return entero;
    }

    public static double Redondear(double numero, int decimales) {
        double random = (Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales));
        return random;
    }

    public static double Maximo(double[] vector) {
        double max;
        max = vector[0];
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] > max) {
                max = vector[i];
            }
        }
        return max;
    }

    public static double Minimo(double[] vector) {
        double min;
        min = vector[0];
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] < min) {
                min = vector[i];
            }
        }
        return min;
    }
}
