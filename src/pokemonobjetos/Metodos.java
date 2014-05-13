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

    public static String ComprobarNota(double calif) {
        int nota = (int) calif;
        String calificacion;
        switch (nota) {
            case -4:
            case -3:
            case -2:
            case -1:
            case 0:
                calificacion = "Desastroso";
                break;
            case 1:
            case 2:
                calificacion = "Muy deficiente";
                break;
            case 3:
            case 4:
                calificacion = "Insuficiente";
                break;
            case 5:
                calificacion = "Suficiente";
                break;
            case 6:
                calificacion = "Bien";
                break;
            case 7:
            case 8:
                calificacion = "Notable";
                break;
            case 9:
                calificacion = "Sobresaliente";
                break;
            case 10:
                calificacion = "Perfecto, sobresaliente";
                break;
            default:
                calificacion = "";
                break;
        }
        return calificacion;
    }

    public static int ConvertirLetraNumero(String l) {
        l = l.toLowerCase();
        int n;
        switch (l) {
            case "a":
                n = 1;
                break;
            case "b":
                n = 2;
                break;
            case "c":
                n = 3;
                break;
            case "d":
                n = 4;
                break;
            default:
                n = 5;
                break;
        }
        return n;
    }

    public static String[] EleccionPregunta() {
        String[] l = new String[5];
        for (int j = 0; j < 5; j++) {
            switch (j) {
                case 0:
                    l[j] = "a";
                    break;
                case 1:
                    l[j] = "b";
                    break;
                case 2:
                    l[j] = "c";
                    break;
                case 3:
                    l[j] = "d";
                    break;
                case 4:
                    l[j] = "n";
                    break;
                default:
                    break;
            }
        }
        return l;
    }
/*
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomSinRound(4));
        }
    }
    */
}
