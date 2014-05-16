/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonobjetos;

import com.db4o.ObjectContainer;
import java.io.*;

/**
 *
 * @author Victor
 */
public class Lecturas {

    private static BufferedReader lectura;
    private static RandomAccessFile random;
    private static String fpokemon = "Juego/Pokemon.txt";
    private static String fataques = "Juego/Ataques.txt";
    private static String fataquesrandom = "Juego/Ataques.dat";
    private static String ftipos = "Juego/Tipos.txt";
    public static String festadisticasvicrandom = "Juego/Estadisticas.dat";
    public static String fhallfamaKanto = "Juego/HallFamaKanto.dat";
    public static String fhallfamaJohto = "Juego/HallFamaJohto.dat";
    public static String fhallfamaHoenn = "Juego/HallFamaHoenn.dat";
    public static String fhallfamaSinnoh = "Juego/HallFamaSinnoh.dat";
    public static String fhallfamaTeselia = "Juego/HallFamaTeselia.dat";
    private static String linea;
    private static String camporandom;
    private static int salir = 0;
    private static String campo[];
    private static int contadorpokemon;
    private static int contadorataques;
    private static int contadorestadisticas = 0;

    static int TipoCombate(int opt) {
        if (opt == 1) {
            return 1;
        } else {
            if (opt == 2) {
                return 3;
            } else {
                return 6;
            }
        }
    }

    public static int ContarNumeroPokemons() {
        contadorpokemon = NumeroRegistros(fpokemon);
        return contadorpokemon;
    }

    public static int NumeroDePokemons() {
        return contadorpokemon;
    }

    public static int ContarNumeroAtaques() {
        contadorataques = NumeroRegistros(fataques);
        return contadorataques;
    }

    public static int NumeroAtaques() {
        return contadorataques;
    }

    public static int NumeroRegistros(String fichero) {
        String linealeida;
        int numeroLineas = 0;
        try {
            BufferedReader lectura = new BufferedReader(new FileReader(fichero));
            while ((linealeida = lectura.readLine()) != null) {
                numeroLineas++;
            }
            lectura.close();
        } catch (IOException iox) {
            System.out.println("Error: " + iox);
        }
        return numeroLineas;
    }

    public static void IntroducirDatosPokemon(ObjectContainer bd) throws IOException {
        lectura = new BufferedReader(new FileReader(fpokemon));
        Pokemon pokemon;
        int i = 0;
        while ((linea = lectura.readLine()) != null) {
            campo = linea.split("\t");
            if (i == 28) {
                pokemon = new Pokemon((i + 1), "Nidoran ♀", 200 + ((Double.parseDouble(campo[1]) / 714) * 1057), Double.parseDouble(campo[2]), Double.parseDouble(campo[3]), Double.parseDouble(campo[4]), Double.parseDouble(campo[5]), Double.parseDouble(campo[6]), Double.parseDouble(campo[7]), campo[8], campo[9], campo[10], campo[11], campo[12], campo[13]);
            } else if (i == 31) {
                pokemon = new Pokemon((i + 1), "Nidoran ♂", 200 + ((Double.parseDouble(campo[1]) / 714) * 1057), Double.parseDouble(campo[2]), Double.parseDouble(campo[3]), Double.parseDouble(campo[4]), Double.parseDouble(campo[5]), Double.parseDouble(campo[6]), Double.parseDouble(campo[7]), campo[8], campo[9], campo[10], campo[11], campo[12], campo[13]);
            } else {
                pokemon = new Pokemon((i + 1), campo[0], 200 + ((Double.parseDouble(campo[1]) / 714) * 1057), Double.parseDouble(campo[2]), Double.parseDouble(campo[3]), Double.parseDouble(campo[4]), Double.parseDouble(campo[5]), Double.parseDouble(campo[6]), Double.parseDouble(campo[7]), campo[8], campo[9], campo[10], campo[11], campo[12], campo[13]);
            }
            bd.store(pokemon);
            i++;
        }
        lectura.close();
    }

    public static void IntroducirDatosAtaque(ObjectContainer bd) throws IOException {
        lectura = new BufferedReader(new FileReader(fataques));
        Ataque ataque;
        while ((linea = lectura.readLine()) != null) {
            campo = linea.split("\t");
            ataque = new Ataque(Integer.parseInt(campo[0]), campo[1], campo[2], Double.parseDouble(campo[3]), Double.parseDouble(campo[4]), Integer.parseInt(campo[5]), Integer.parseInt(campo[6]), campo[7], campo[8], campo[9]);
            bd.store(ataque);
        }
        lectura.close();
    }

    public static String[][] DatosTipos(String tipoataque) throws FileNotFoundException, IOException {
        String tipos[][] = new String[3][];
        lectura = new BufferedReader(new FileReader(ftipos));
        while ((linea = lectura.readLine()) != null) {
            campo = linea.split("\t");
            if (campo[0].compareTo(tipoataque) == 0) {
                tipos[0] = campo[1].split(",");
                tipos[1] = campo[2].split(",");
                tipos[2] = campo[3].split(",");
                lectura.close();
                return tipos;
            }
        }
        lectura.close();
        return null;
    }

    public static void EstadisticasVicRandom(Pokemon Pokemonj) throws FileNotFoundException, IOException {
        File dir = new File(festadisticasvicrandom);
        String[] Pokemon = new String[1];
        Pokemon[0] = String.valueOf(Pokemonj.codigo);
        int num;
        contadorestadisticas = 0;
        salir = 0;
        if (!dir.exists()) {
            //No existe
            random = new RandomAccessFile(festadisticasvicrandom, "rw"); // E/S
            if (Pokemon[0].length() < 25) {
                for (int k = Pokemon[0].length(); k < 25; k++) {
                    Pokemon[0] += " ";
                }
            } else {
                Pokemon[0] = Pokemon[0].substring(0, 25);
            }
            camporandom = "1";
            if (camporandom.length() < 25) {
                for (int k = camporandom.length(); k < 25; k++) {
                    camporandom += " ";
                }
            } else {
                camporandom = camporandom.substring(0, 25);
            }
            random.writeUTF(Pokemon[0]);
            random.writeUTF(camporandom);
            random.writeUTF("eof");
        } else {
            //Existe
            random = new RandomAccessFile(festadisticasvicrandom, "rw"); // E/S
            random.seek(0);
            while (!"eof".equals((camporandom = random.readUTF())) && salir == 0) {
                if (camporandom.trim().compareTo(Pokemon[0].trim()) != 0) {
                    random.readUTF();
                    contadorestadisticas++;
                } else {
                    String numstring;
                    num = Integer.parseInt(random.readUTF().trim());
                    num++;
                    numstring = String.valueOf(num);
                    if (contadorestadisticas == 0) {
                        random.seek(0);
                    } else {
                        random.seek((contadorestadisticas * ((25 + 2) * 2)));
                    }
                    if (numstring.length() < 25) {
                        for (int k = numstring.length(); k < 25; k++) {
                            numstring += " ";
                        }
                    }
                    random.writeUTF(camporandom);
                    random.writeUTF(numstring);
                    salir = 1;
                }
            }
            if (salir == 0) {
                random.seek((contadorestadisticas) * ((25 + 2) * 2));
                if (Pokemon[0].length() < 25) {
                    for (int k = Pokemon[0].length(); k < 25; k++) {
                        Pokemon[0] += " ";
                    }
                } else {
                    Pokemon[0] = Pokemon[0].substring(0, 25);
                }
                camporandom = "1";
                if (camporandom.length() < 25) {
                    for (int k = camporandom.length(); k < 25; k++) {
                        camporandom += " ";
                    }
                } else {
                    camporandom = camporandom.substring(0, 25);
                }
                random.writeUTF(Pokemon[0]);
                random.writeUTF(camporandom);
                random.writeUTF("eof");
            }
        }
        random.close();
    }

    public static String[][] MostrarEstadisticasVicOrdenadas() throws FileNotFoundException, IOException {
        random = new RandomAccessFile(festadisticasvicrandom, "r"); // E/S
        int l = 0;
        while (!"eof".equals((camporandom = random.readUTF()))) {
            random.readUTF();
            l++;
        }
        String[][] Pokemones = new String[l][2];
        l = 0;
        random.seek(0);
        while (!"eof".equals(camporandom = random.readUTF())) {
            Pokemones[l][0] = camporandom.trim();
            Pokemones[l][1] = random.readUTF().trim();
            l++;
        }
        int aux = 0;
        int aux2 = 0;
        String aux3;
        for (int i = 0; i < Pokemones.length - 1; i++) {
            for (int j = i + 1; j < Pokemones.length; j++) {
                if (Integer.parseInt(Pokemones[j][1]) > Integer.parseInt(Pokemones[i][1])) {
                    aux = Integer.parseInt(Pokemones[j][1]);
                    Pokemones[j][1] = Pokemones[i][1];
                    aux2 = aux;
                    Pokemones[i][1] = String.valueOf(aux2);

                    aux3 = Pokemones[j][0];
                    Pokemones[j][0] = Pokemones[i][0];
                    Pokemones[i][0] = aux3;
                }
            }
        }
        random.close();
        return Pokemones;
    }

    public static void HallFamaRandom(Pokemon Pokemonj) throws FileNotFoundException, IOException {
        String fichero;
        if (PokemonGUI.kanto) {
            fichero = fhallfamaKanto;
        } else if (PokemonGUI.johto) {
            fichero = fhallfamaJohto;
        } else if (PokemonGUI.hoenn) {
            fichero = fhallfamaHoenn;
        } else if (PokemonGUI.sinnoh) {
            fichero = fhallfamaSinnoh;
        } else {
            fichero = fhallfamaTeselia;
        }
        File dir = new File(fichero);
        String[] Pokemon = new String[1];
        Pokemon[0] = "" + Pokemonj.codigo;
        int num;
        contadorestadisticas = 0;
        salir = 0;
        if (!dir.exists()) {
            //No existe
            random = new RandomAccessFile(fichero, "rw"); // E/S
            if (Pokemon[0].length() < 25) {
                for (int k = Pokemon[0].length(); k < 25; k++) {
                    Pokemon[0] += " ";
                }
            } else {
                Pokemon[0] = Pokemon[0].substring(0, 25);
            }
            camporandom = "1";
            if (camporandom.length() < 25) {
                for (int k = camporandom.length(); k < 25; k++) {
                    camporandom += " ";
                }
            } else {
                camporandom = camporandom.substring(0, 25);
            }
            random.writeUTF(Pokemon[0]);
            random.writeUTF(camporandom);
            random.writeUTF("eof");
        } else {
            //Existe
            random = new RandomAccessFile(fichero, "rw"); // E/S
            random.seek(0);
            while (!"eof".equals((camporandom = random.readUTF())) && salir == 0) {
                if (camporandom.trim().compareTo(Pokemon[0].trim()) != 0) {
                    random.readUTF();
                    contadorestadisticas++;
                } else {
                    String numstring;
                    num = Integer.parseInt(random.readUTF().trim());
                    num++;
                    numstring = String.valueOf(num);
                    if (contadorestadisticas == 0) {
                        random.seek(0);
                    } else {
                        random.seek((contadorestadisticas * ((25 + 2) * 2)));
                    }
                    if (numstring.length() < 25) {
                        for (int k = numstring.length(); k < 25; k++) {
                            numstring += " ";
                        }
                    }
                    random.writeUTF(camporandom);
                    random.writeUTF(numstring);
                    salir = 1;
                }
            }
            if (salir == 0) {
                random.seek((contadorestadisticas) * ((25 + 2) * 2));
                if (Pokemon[0].length() < 25) {
                    for (int k = Pokemon[0].length(); k < 25; k++) {
                        Pokemon[0] += " ";
                    }
                } else {
                    Pokemon[0] = Pokemon[0].substring(0, 25);
                }
                camporandom = "1";
                if (camporandom.length() < 25) {
                    for (int k = camporandom.length(); k < 25; k++) {
                        camporandom += " ";
                    }
                } else {
                    camporandom = camporandom.substring(0, 25);
                }
                random.writeUTF(Pokemon[0]);
                random.writeUTF(camporandom);
                random.writeUTF("eof");
            }
        }
        random.close();
    }

    public static String[][] MostrarHallFamaOrdenado() throws FileNotFoundException, IOException {
        String fichero;
        if (PokemonGUI.kanto) {
            fichero = fhallfamaKanto;
        } else if (PokemonGUI.johto) {
            fichero = fhallfamaJohto;
        } else if (PokemonGUI.hoenn) {
            fichero = fhallfamaHoenn;
        } else if (PokemonGUI.sinnoh) {
            fichero = fhallfamaSinnoh;
        } else {
            fichero = fhallfamaTeselia;
        }
        random = new RandomAccessFile(fichero, "r"); // E/S
        int l = 0;
        while (!"eof".equals((camporandom = random.readUTF()))) {
            random.readUTF();
            l++;
        }
        String[][] Pokemones = new String[l][2];
        l = 0;
        random.seek(0);
        while (!"eof".equals(camporandom = random.readUTF())) {
            Pokemones[l][0] = camporandom.trim();
            Pokemones[l][1] = random.readUTF().trim();
            l++;
        }
        int aux = 0;
        int aux2 = 0;
        String aux3;
        for (int i = 0; i < Pokemones.length - 1; i++) {
            for (int j = i + 1; j < Pokemones.length; j++) {
                if (Integer.parseInt(Pokemones[j][0]) < Integer.parseInt(Pokemones[i][0])) {
                    aux = Integer.parseInt(Pokemones[j][0]);
                    Pokemones[j][0] = Pokemones[i][0];
                    aux2 = aux;
                    Pokemones[i][0] = String.valueOf(aux2);

                    aux3 = Pokemones[j][1];
                    Pokemones[j][1] = Pokemones[i][1];
                    Pokemones[i][1] = aux3;
                }
            }
        }
        random.close();
        return Pokemones;
    }
}
