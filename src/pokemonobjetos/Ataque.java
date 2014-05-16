/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonobjetos;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import static pokemonobjetos.PokemonGUI.pokemonencombateIA;
import static pokemonobjetos.PokemonGUI.pokemonencombatejugador;

/**
 *
 * @author Victor
 */
public class Ataque {

    int codigo;
    String nombre;
    String tipo;
    double daño;
    double precision;
    int pp;
    int ppmax;
    int critico;
    String categoria;
    String efectovisual;
    String foto;
    static boolean metronomo = false;
    static int ppmetronomo;

    Ataque(int codigo, String nombre, String tipo, double daño, double precision, int pp, int ppmax, int critico, String categoria, String efectovisual, String foto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.daño = daño;
        this.precision = precision;
        this.pp = pp;
        this.ppmax = ppmax;
        this.critico = critico;
        this.categoria = categoria;
        this.efectovisual = efectovisual;
        this.foto = foto;
    }

    Ataque(int codigo, String nombre, String tipo, double daño, double precision, int pp, int critico, String categoria, String efectovisual, String foto) {
        this(codigo, nombre, tipo, daño, precision, pp, pp, critico, categoria, efectovisual, foto);
    }

    public String toString() {
        String str = this.nombre + " " + this.tipo;
        return str;
    }

    public static void MostrarResultado(ObjectSet res) {
        while (res.hasNext()) {
            System.out.println(res.next());
        }
    }

    public static void MostrarTodo(ObjectContainer bd) {
        Ataque a = new Ataque(0, null, null, 0, 0, 0, 0, null, null, null);
        ObjectSet res = bd.queryByExample(a);
        MostrarResultado(res);
    }

    public static void BorrarTodo(ObjectContainer bd) {
        ObjectSet res = bd.queryByExample(new Ataque(0, null, null, 0, 0, 0, 0, null, null, null));
        Ataque a;
        while (res.hasNext()) {
            a = (Ataque) res.next();
            bd.delete(a);
        }
    }

    public static Ataque AtaqueElegidoInicializa(ObjectContainer bd, int cod) {
        Ataque a = new Ataque(cod, null, null, 0, 0, 0, 0, null, null, null);
        ObjectSet res = bd.queryByExample(a);
        a = (Ataque) res.next();
        a = new Ataque(a.codigo, a.nombre, a.tipo, a.daño, a.precision, a.pp, a.critico, a.categoria, a.efectovisual, a.foto);
        return a;
    }

    public static Ataque AtaqueElegidoNOInicializa(ObjectContainer bd, int cod) {
        Ataque a = new Ataque(cod, null, null, 0, 0, 0, 0, null, null, null);
        ObjectSet res = bd.queryByExample(a);
        a = (Ataque) res.next();
        return a;
    }

    void AtaquesPrioritarios(Pokemon j1, Ataque a1) {
        switch (a1.nombre) {
            case "Acua jet":
            case "Puno bala":
            case "Ataque rapido":
            case "Ultrapuno":
            case "Canto helado":
            case "Sombra vil":
            case "Rapidez":
            case "Onda vacio":
            case "Golpe bajo":
            case "Velocidad extrema":
            case "Ojitos tiernos":
                System.out.print(j1.nombre.trim() + " ataca primero, ");
                j1.prioridad = true;
                break;
            default:
                break;
        }
    }

    public Ataque Metronomo(Ataque a) {
        if (this.codigo == 134) {
            metronomo = true;
            ppmetronomo = this.pp;
            System.out.println("----> " + ppmetronomo);
            int i;
            do {
                i = (int) (Math.random() * Lecturas.NumeroAtaques() + 1);
            } while (i == 134);
            System.out.println("AQUI --> " + i);
            a = AtaqueElegidoInicializa(PokemonGUI.bdataque, i);
        } else {
            metronomo = false;
        }
        return a;
    }

    public Ataque DevolverAtaqueMetronomo(Ataque a) {
        if (metronomo) {
            a = AtaqueElegidoInicializa(PokemonGUI.bdataque, 134);
            a.pp = ppmetronomo;
        }
        return a;
    }

    void NoQuitarPP() {
        System.out.println("*******************************>>>" + this.nombre + " SUBIR PP");
        this.pp++;
        Ataque.ppmetronomo++;
    }

    public static Ataque Esquema(Ataque a1, Ataque a2) {
        System.out.println(a1.codigo);
        a1 = AtaqueElegidoInicializa(PokemonGUI.bdataque, a2.codigo);
        return a1;
    }

    public String AtaquesTurnosJugador(Pokemon[] equipo) {
        String str = "";
        switch (this.nombre) {
            case "Velo sagrado":
                for (int i = 0; i < equipo.length; i++) {
                    equipo[i].protegeestado = true;
                }
                Juego.veloSagradoJugador = 5;
                str += equipo[pokemonencombatejugador].nombre + " envolvió a todo su equipo con un velo sagrado que les protege de los problemas de estado.";
                break;
        }
        return str;
    }

    public String AtaquesTurnosIA(Pokemon[] equipo) {
        String str = "";
        switch (this.nombre) {
            case "Velo sagrado":
                for (int i = 0; i < equipo.length; i++) {
                    equipo[i].protegeestado = true;
                }
                Juego.veloSagradoIA = 5;
                str += equipo[pokemonencombateIA].nombre + " envolvió a todo su equipo con un velo sagrado que les protege de los problemas de estado.";
                break;
        }
        return str;
    }

    public static String Pasivas(Pokemon j1, Pokemon j2, Ataque ataq) {
        String str = "";
        switch (j1.codigo) {
            //Espesura
            case 1:
            case 2:
            case 3:
            case 152:
            case 153:
            case 154:
            case 252:
            case 253:
            case 254:
                if (j1.salud < j1.saludmax / 3 && ataq.tipo.compareTo("Planta") == 0) {
                    j1.golpe += j1.golpe * 0.5;
                }
                break;
            //Mar de llamas
            case 4:
            case 5:
            case 6:
            case 155:
            case 156:
            case 157:
            case 255:
            case 256:
            case 257:
                if (j1.salud < j1.saludmax / 3 && ataq.tipo.compareTo("Fuego") == 0) {
                    j1.golpe += j1.golpe * 0.5;
                }
                break;
            //Torrente
            case 7:
            case 8:
            case 9:
            case 23:
            case 24:
            case 158:
            case 159:
            case 160:
            case 258:
            case 259:
            case 260:
                if (j1.salud < j1.saludmax / 3 && ataq.tipo.compareTo("Agua") == 0) {
                    j1.golpe += j1.golpe * 0.5;
                }
                break;
            //Polvo escudo, Anula los efectos secundarios que pueden provocar los ataques de los oponentes al poseedor de la habilidad,
            //como la parálisis en golpe cuerpo o el retroceso en sorpresa. No anula efectos secundarios que afectan al usuario que 
            //realizó el ataque, como el aumento de velocidad al usar nitrocarga. 

            //Mudar
            case 11:
            case 14:
            case 147:
            case 148:
            case 247:
            case 266:
            case 268:
            case 336:
                if (j1.estado.compareTo("Normal") != 0 && Math.random() * 100 < 33) {
                    j1.estado = "Normal";
                    str += j1.nombre + " mudó su piel y el estado se disipó.";
                }
                break;
            //Enjambre
            case 15:
            case 267:
            case 123:
            case 212:
            case 165:
            case 166:
            case 167:
            case 168:
            case 214:
            case 313:
                if (j1.salud < j1.saludmax / 3 && ataq.tipo.compareTo("Bicho") == 0) {
                    j1.golpe += j1.golpe * 0.5;
                }
                break;
            //Tumbos está mal hecho
            case 16:
            case 17:
            case 18:
            case 327:
            case 84:
            case 85:
                if (j1.estado.compareTo("Confuso") == 0) {
                    j1.evasion += 10;
                }
                break;
            //Agallas
            //Francotirador
            case 116:
            case 117:
            case 230:
            case 223:
            case 224:
            case 21:
            case 22:
                if (j1.golpec) {
                    j1.golpe += j1.golpe * 0.5;
                }
                break;
            //Intimidacion
            //Electricidad estatica
            case 172:
            case 25:
            case 26:
            case 239:
            case 125:
            case 179:
            case 180:
            case 181:
            case 100:
            case 101:
            case 310:
                if (Math.random() * 100 < 30) {
                    if (j2.estado.compareTo("Normal") == 0 && (j1.categoriaataque.compareTo("F") == 0 || j2.categoriaataque.compareTo("F") == 0)
                            && j2.tipo1.compareTo("Electrico") != 0 && j2.tipo2.compareTo("Electrico") != 0) {
                        j2.velocidad = j2.velocidad / 4;
                        j2.estado = "Paralizado";
                        System.out.println(j2.nombre.trim() + " quedó paralizado al entrar en contacto con " + j1.nombre + ".");
                        str += j2.nombre.trim() + " quedó paralizado al entrar en contacto con " + j1.nombre + ".";
                    }
                }
                break;
            //Punto toxico
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 211:
            case 315:
                if (Math.random() * 100 < 30) {
                    if (j2.estado.compareTo("Normal") == 0 && (j1.categoriaataque.compareTo("F") == 0 || j2.categoriaataque.compareTo("F") == 0)) {
                        j2.estado = "Envenenado";
                        System.out.println(j2.nombre.trim() + " quedó envenenado al entrar en contacto con " + j1.nombre + ".");
                        str += j2.nombre.trim() + " quedó envenenado al entrar en contacto con " + j1.nombre + ".";
                    }
                }
                break;
            //rivalidad
        }
        switch (j1.codigo) {
            //Francotirador
            case 15:
            case 167:
            case 168:
                if (j1.golpec) {
                    j1.golpe += j1.golpe * 0.5;
                }
                break;
            //Punto toxico
            case 117:
                if (Math.random() * 100 < 30) {
                    if (j2.estado.compareTo("Normal") == 0 && (j1.categoriaataque.compareTo("F") == 0 || j2.categoriaataque.compareTo("F") == 0)) {
                        j2.estado = "Envenenado";
                        System.out.println(j2.nombre.trim() + " quedó envenenado al entrar en contacto con " + j1.nombre + ".");
                        str += j2.nombre.trim() + " quedó envenenado al entrar en contacto con " + j1.nombre + ".";
                    }
                }
                break;
        }
        return str;
    }
}
