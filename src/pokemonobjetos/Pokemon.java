package pokemonobjetos;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static pokemonobjetos.Juego.ataqueIA;
import static pokemonobjetos.Juego.ataquejugador;
import static pokemonobjetos.Juego.equipoIA;
import static pokemonobjetos.Juego.equipojugador;
import static pokemonobjetos.Juego.str;
import static pokemonobjetos.PokemonGUI.RefrescoAtaques;
import static pokemonobjetos.PokemonGUI.atacaiaporcambiarpokemonjugador;
import static pokemonobjetos.PokemonGUI.ataqueusadoIA;
import static pokemonobjetos.PokemonGUI.ataqueusadojugador;
import static pokemonobjetos.PokemonGUI.botonpokemonelegidosIA;
import static pokemonobjetos.PokemonGUI.estadoequipoIA;
import static pokemonobjetos.PokemonGUI.labelfotoentrenadorIA;
import static pokemonobjetos.PokemonGUI.labelnombreentrenadorIA;
import static pokemonobjetos.PokemonGUI.lblsaludequipoIA;
import static pokemonobjetos.PokemonGUI.pokemonencombateIA;
import static pokemonobjetos.PokemonGUI.pokemonencombatejugador;
import static pokemonobjetos.PokemonGUI.tipo1IA;
import static pokemonobjetos.PokemonGUI.tipo2IA;

public class Pokemon {

    static int ataquemax = 200;
    static int ataquemin = 5;
    static int ataqueespmax = 200;
    static int ataqueespmin = 5;
    static int defensamin = 5;
    static int defensamax = 400;
    static int defensaespmin = 5;
    static int defensaespmax = 400;
    static int precisionmin = 50;
    static int precisionmax = 150;
    static int evasionmax = 200;
    static int evasionmin = 25;
    static int velocidadmin = 0;
    static int velocidadmax = 250;
    static double porcentajevidaefectos = 0.04;
    static boolean activarsolounboton = false;
    int codigo;
    String nombre;
    double salud;
    double saludmax;
    double peso;
    double velocidad;
    double velocidadbase;
    double ataque;
    double ataquebase;
    double defensa;
    double defensabase;
    double antesgolpe;
    double ataqueesp;
    double ataqueespbase;
    double defensaesp;
    double defensaespbase;
    int turnosincapacitado = 0;
    String tipo1;
    String tipo2;
    String ataque1;
    String ataque2;
    String ataque3;
    String ataque4;
    boolean equijugador;
    String estado = "Normal";
    boolean prioridad;
    boolean golpec;
    boolean descansar = false;
    boolean alalcance = true;
    boolean volando = false;
    boolean sumergido = false;
    boolean enterrado = false;
    double precision = 100;
    double evasion = 100;
    int subidacritico = 0;
    double golpe = 0;
    int ataque1pp;
    int ataque1ppditto;
    int ataque2pp;
    int ataque2ppditto;
    int ataque3pp;
    int ataque3ppditto;
    int ataque4pp;
    int ataque4ppditto;
    String ataqueelegido = "nada";
    String ataqueelegidoanterior = "nada";
    String tipoataque;
    int numequipo;
    int numataques;
    int numeroPokemon;
    boolean noatacar = false;
    int pasar = 0;
    double aleatorio;
    int victoriasconsecutivas = 0;
    String tipo1ditto;
    String tipo2ditto;
    String naturaleza;
    boolean drenadoras = false;
    boolean mismodestino = false;
    boolean nopuedeafectar = false;
    int turnossincurase = 0;
    int atrapado = 0;
    boolean retroceder = false;
    String categoriaataque = "O";
    boolean muertedirecta = true;
    int contador;
    boolean shiny = false;
    boolean protegeestado = false;
    double saludantesgolpe;

    Pokemon(int codigo, String nombre, double salud, double saludmax, double saludantesgolpe, double peso, double velocidad, double velocidadbase, double ataque, double ataquebase, double defensa, double defensabase, double ataqueesp, double ataqueespbase, double defensaesp, double defensaespbase, String tipo1, String tipo2, String ataque1, String ataque2, String ataque3, String ataque4) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.salud = salud;
        this.saludmax = saludmax;
        this.saludantesgolpe = saludantesgolpe;
        this.peso = peso;
        this.velocidad = velocidad;
        this.velocidadbase = velocidadbase;
        this.ataque = ataque;
        this.ataquebase = ataquebase;
        this.defensa = defensa;
        this.defensabase = defensabase;
        this.ataqueesp = ataqueesp;
        this.ataqueespbase = ataqueespbase;
        this.defensaesp = defensaesp;
        this.defensaespbase = defensaespbase;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.ataque1 = ataque1;
        this.ataque2 = ataque2;
        this.ataque3 = ataque3;
        this.ataque4 = ataque4;
    }

    Pokemon(int codigo, String nombre, double salud, double peso, double velocidad, double ataque, double defensa, double ataqueesp, double defensaesp, String tipo1, String tipo2, String ataque1, String ataque2, String ataque3, String ataque4) {
        this(codigo, nombre, salud, salud, salud, peso, velocidad, velocidad, ataque, ataque, defensa, defensa, ataqueesp, ataqueesp, defensaesp, defensaesp, tipo1, tipo2, ataque1, ataque2, ataque3, ataque4);
    }

    public String toString() {
        String str;
        if (this.tipo2.compareTo("n*n*") == 0) {
            str = this.nombre + " " + this.tipo1;
        } else {
            str = this.nombre + " " + this.tipo1 + " " + this.tipo2;
        }
        return str;
    }

    public static void MostrarResultado(ObjectSet res) {
        while (res.hasNext()) {
            System.out.println(res.next());
        }
    }

    public static void MostrarTodo(ObjectContainer bd) {
        Pokemon p = new Pokemon(0, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null);
        ObjectSet res = bd.queryByExample(p);
        MostrarResultado(res);
    }

    public static void BorrarTodo(ObjectContainer bd) {
        ObjectSet res = bd.queryByExample(new Pokemon(0, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null));
        Pokemon p;
        while (res.hasNext()) {
            p = (Pokemon) res.next();
            bd.delete(p);
        }
    }

    public static String[] ElegirPokemons(ObjectContainer bd) {
        Pokemon p = new Pokemon(0, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null);
        ObjectSet res = bd.queryByExample(p);
        String[] poke = new String[Lecturas.NumeroDePokemons()];
        int i = 0;
        while (res.hasNext()) {
            poke[i] = res.next().toString();
            i++;
        }
        return poke;
    }

    public static Pokemon[] ObtenerTodosPokemons(ObjectContainer bd) {
        Pokemon p = new Pokemon(0, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null);
        ObjectSet res = bd.queryByExample(p);
        Pokemon[] poke = new Pokemon[Lecturas.ContarNumeroPokemons()];
        int i = 0;
        while (res.hasNext()) {
            p = (Pokemon) res.next();
            poke[i] = p;
            System.out.println(i);
            i++;
        }
        return poke;
    }

    public static Pokemon PokemonElegido(ObjectContainer bd, int cod) {
        Pokemon p = new Pokemon(cod, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null);
        ObjectSet res = bd.queryByExample(p);
        p = (Pokemon) res.next();
        return p;
    }

    public static Pokemon PokemonEstadisticas(ObjectContainer bd, String nombre) {
        Pokemon p = new Pokemon(0, nombre, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null);
        ObjectSet res = bd.queryByExample(p);
        p = (Pokemon) res.next();
        return p;
    }

    void Estadisticas() {
        String str = this.nombre;

        if (this.tipo2.trim().compareTo("n*n*") == 0) {
            System.out.println("Tipo: " + this.tipo1);
        } else {
            System.out.println("Tipo: " + this.tipo1.trim() + "/" + this.tipo2);
        }
    }

    boolean Vivo() {
        if (this.salud > 0) {
            return true;
        } else {
            return false;
        }
    }

    int Orden(Pokemon j1, Pokemon j2) {
        double jugador;
        double ia;
        if (j1.prioridad == true && j2.prioridad == false) {
            return 1;
        }
        if (j1.prioridad == false && j2.prioridad == true) {
            return 2;
        }
        if (j1.velocidad == j2.velocidad) {
            jugador = Math.random() * 100;
            ia = Math.random() * 100;
            j1.prioridad = false;
            j2.prioridad = false;
            if (jugador >= ia) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (j1.velocidad > j2.velocidad) {
                return 1;
            } else {
                return 2;
            }

        }
    }

    double Golpe(String nombreataque, double daño, double hit, int critico, String categoria, Pokemon j1, Pokemon j2) {
        boolean pegadoble = VueloBuceoExcavar(nombreataque, daño, j1, j2);
        double calculo;
        if (j2.alalcance == false && !pegadoble) {
            calculo = -50000;
        } else {
            if (Math.random() * 100 > (j1.precision * (hit / j2.evasion)) && !pegadoble) {
                return -50000;
            } else {
                if (categoria.compareTo("F") == 0) {
                    if (Math.random() * 100 <= CalculoCritico(critico, j1)) {
                        j1.golpec = true;
                        calculo = ((this.ataque / 100 * daño) * 2);
                    } else {
                        j1.golpec = false;
                        calculo = ((this.ataque / 100 * daño));
                    }
                } else {
                    if (Math.random() * 100 <= CalculoCritico(critico, j1)) {
                        j1.golpec = true;
                        calculo = ((this.ataqueesp / 100 * daño) * 2);
                    } else {
                        j1.golpec = false;
                        calculo = ((this.ataqueesp / 100 * daño));
                    }
                }
            }
        }
        if (pegadoble) {
            calculo += calculo;
        }
        return calculo;
    }

    public boolean VueloBuceoExcavar(String nombreataque, double daño, Pokemon j1, Pokemon j2) {
        boolean dobledaño = false;
        if (j2.volando) {
            if (nombreataque.compareTo("Gancho alto") == 0 || nombreataque.compareTo("Tornado") == 0 || nombreataque.compareTo("Trueno") == 0
                    || nombreataque.compareTo("Ciclon") == 0 || nombreataque.compareTo("Antiaereo") == 0 || nombreataque.compareTo("Remolino") == 0
                    || nombreataque.compareTo("Vendaval") == 0) {
                dobledaño = true;
                System.out.println("PEGADOBLE VOLANDO");
            }
        }
        if (j2.sumergido) {
            if (nombreataque.compareTo("Torbellino") == 0 || nombreataque.compareTo("Surf") == 0) {
                dobledaño = true;
                System.out.println("PEGADOBLE SUMERGIDO");
            }
        }
        if (j2.enterrado) {
            if (nombreataque.compareTo("Terremoto") == 0 || nombreataque.compareTo("Magnitud") == 0 || nombreataque.compareTo("Fisura") == 0) {
                dobledaño = true;
                System.out.println("PEGADOBLE ENTERRADO");
            }
        }
        return dobledaño;
    }

    double CalculoCritico(int critico, Pokemon j1) {
        double resultadocritico = 0;
        if (critico + j1.subidacritico > 5) {
            critico = 5;
        } else {
            critico += subidacritico;
        }
        switch (critico) {
            case 1:
                resultadocritico = 6.25;
                break;
            case 2:
                resultadocritico = 12.5;
                break;
            case 3:
                resultadocritico = 25;
                break;
            case 4:
                resultadocritico = 33.3;
                break;
            case 5:
                resultadocritico = 50;
                break;
            default:
                resultadocritico = 0;
                break;
        }
        j1.subidacritico = 0;
        return resultadocritico;
    }

    String Atacar(Pokemon j1, Pokemon j2, Ataque ataq, Pokemon[] equipo, Ataque[] ataquesj1) throws FileNotFoundException, IOException {
        String str = "";
        str += ComprobarEstado(j1, j2, ataq.daño);
        tipoataque = ataq.tipo;
        //j1.ataqueelegido = ataq.nombre;
        j1.ataqueelegidoanterior = j1.ataqueelegido;
        j1.categoriaataque = ataq.categoria;
        antesgolpe = ataq.daño;
        saludantesgolpe = salud;
        if (j1.Vivo()) {
            if (!j1.noatacar) {
                if (j1.pasar == 0) {
                    str += j1.EfectoDescansar(j1, j1.ataqueelegido);
                }
                if (j1.turnosincapacitado == 0) {
                    if (j1.descansar == false) {
                        //eleccionj1 es la eleccion del ataque 0 a 3
                        str += AtaquesModificadoresDaño(j1, j2, ataq.daño, ataqueelegido, tipoataque, ataqueusadojugador, ataqueusadoIA, ataquesj1);
                        if (ataq.categoria.compareTo("F") == 0) {
                            golpe = ((j1.Golpe(ataq.nombre, antesgolpe, ataq.precision, ataq.critico, ataq.categoria, j1, j2) * 100) * (((Math.random() * 15) + 85) / 100)) / j2.defensa;
                        } else {
                            golpe = ((j1.Golpe(ataq.nombre, antesgolpe, ataq.precision, ataq.critico, ataq.categoria, j1, j2) * 100) * (((Math.random() * 15) + 85) / 100)) / j2.defensaesp;
                        }
                        //Sin completar las pasivas, quedan descativadas.
                        //str += Ataque.Pasivas(j1, j2, ataq); 
                        str += j1.IgnorarDefensas(j1, j2, ataq);
                        if (golpe < 0) {
                            System.out.println(j1.nombre.trim() + " ha fallado.");
                            str += j1.nombre + " ha fallado.";
                            if (ataqueelegido.trim().compareTo("Patada salto alta") == 0) {
                                if (j1.salud - (j1.saludmax / 2) > 0) {
                                    j1.salud -= j1.saludmax / 2;
                                } else {
                                    j1.salud = 0;
                                }
                                if (j1.Vivo()) {
                                    str += j1.nombre + " cayó mal y se hizo " + (int) (j1.saludmax / 2) + " de daño.";
                                } else {
                                    System.out.println(j1.nombre.trim() + " lo hizo tan sumamente mal que murió al caer.");
                                    str += j1.nombre.trim() + " lo hizo tan sumamente mal que murió al caer";
                                }
                            }
                        } else {
                            if (ataq.categoria.compareTo("O") != 0) {
                                if (TipoAtaqueIgualPokemon(j1, ataq.tipo) && !nopuedeafectar) {
                                    golpe += golpe * 0.5;
                                }
                                int n;
                                if (!nopuedeafectar) {
                                    n = ComprobarTipo(ataq.tipo, j1, j2);
                                } else {
                                    nopuedeafectar = false;
                                    n = 3;
                                }
                                if (ataq.nombre.compareTo("Liofilizacion") == 0 && (j2.tipo1.compareTo("Agua") == 0 || j2.tipo2.compareTo("Agua") == 0)) {
                                    n++;
                                }
                                if (ataq.nombre.compareTo("Explosion") == 0 || ataq.nombre.compareTo("Autodestruccion") == 0) {
                                    if (n == 0) {
                                        n = 3;
                                    }
                                }
                                switch (n) {
                                    case 0:
                                        System.out.println(j2.nombre.trim() + " no se ve afectado por " + j1.ataqueelegido.trim() + ".");
                                        str += j2.nombre.trim() + " no se ve afectado por " + j1.ataqueelegido.trim() + ".";
                                        golpe = 0;
                                        break;
                                    case 1:
                                        golpe = golpe / 4;
                                        if (golpec == true) {
                                            if (golpe != 0) {
                                                System.out.println(j1.nombre.trim() + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño.");
                                                str += j1.nombre + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ";
                                            }
                                        } else {
                                            if (golpe != 0) {
                                                System.out.println(j1.nombre.trim() + " ha hecho un golpe de " + (int) golpe + " de daño.");
                                                str += j1.nombre + " ha hecho un golpe de " + (int) golpe + " de daño. ";
                                            }
                                        }
                                        str += j1.Efecto(j1, j2, golpe, ataq.critico, ataqueelegido, tipoataque, equipo);
                                        System.out.println("ES MUY POCO EFECTIVO...");
                                        str += "ES MUY POCO EFECTIVO...";
                                        break;

                                    case 2:
                                        golpe = golpe / 2;
                                        if (golpec == true) {
                                            if (golpe != 0) {
                                                System.out.print(j1.nombre.trim() + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ";
                                            }
                                        } else {
                                            if (golpe != 0) {
                                                System.out.print(j1.nombre.trim() + " ha hecho un golpe de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un golpe de " + (int) golpe + " de daño. ";
                                            }
                                        }
                                        str += j1.Efecto(j1, j2, golpe, ataq.critico, ataqueelegido, tipoataque, equipo);
                                        System.out.println("ES POCO EFECTIVO...");
                                        str += "ES POCO EFECTIVO...";
                                        break;

                                    case 3:
                                        if (golpec == true) {
                                            if (golpe != 0) {
                                                System.out.println(j1.nombre.trim() + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ";
                                            }
                                        } else {
                                            if (golpe != 0) {
                                                System.out.println(j1.nombre.trim() + " ha hecho un golpe de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un golpe de " + (int) golpe + " de daño. ";
                                            }
                                        }
                                        str += j1.Efecto(j1, j2, golpe, ataq.critico, ataqueelegido, tipoataque, equipo);
                                        break;
                                    case 4:
                                        golpe = golpe * 2;
                                        if (golpec == true) {
                                            if (golpe != 0) {
                                                System.out.print(j1.nombre.trim() + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ";
                                            }
                                        } else {
                                            if (golpe != 0) {
                                                System.out.print(j1.nombre.trim() + " ha hecho un golpe de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un golpe de " + (int) golpe + " de daño. ";
                                            }
                                        }
                                        str += j1.Efecto(j1, j2, golpe, ataq.critico, ataqueelegido, tipoataque, equipo);
                                        System.out.println("¡¡ES MUY EFECTIVO!!");
                                        str += "¡¡ES MUY EFECTIVO!!";
                                        break;
                                    case 5:
                                        golpe = golpe * 4;
                                        if (golpec == true) {
                                            if (golpe != 0) {
                                                System.out.print(j1.nombre.trim() + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un GOLPE CRÍTICO de " + (int) golpe + " de daño. ";
                                            }
                                        } else {
                                            if (golpe != 0) {
                                                System.out.print(j1.nombre.trim() + " ha hecho un golpe de " + (int) golpe + " de daño. ");
                                                str += j1.nombre + " ha hecho un golpe de " + (int) golpe + " de daño. ";
                                            }
                                        }
                                        str += j1.Efecto(j1, j2, golpe, ataq.critico, ataqueelegido, tipoataque, equipo);
                                        System.out.println("¡¡ES SUPER EFECTIVO!!");
                                        str += "¡¡ES SUPER EFECTIVO!!";
                                        break;
                                }
                                str += AtaquesOneShot(j1, j2, ataq.daño, ataqueelegido, tipoataque, ataqueusadojugador, ataqueusadoIA, ataquesj1);

                                if (j2.salud - golpe <= 0) {
                                    j2.salud = 0;
                                } else {
                                    j2.salud -= golpe;
                                }
                                if (j2.nombre.trim().compareTo("Kecleon") == 0) {
                                    if (j2.tipo1.compareTo(ataq.tipo) != 0) {
                                        j2.tipo1 = ataq.tipo;
                                        System.out.println("Kecleon cambió su tipo a " + j2.tipo1 + ".");
                                        str += "Kecleon cambió su tipo a " + j2.tipo1 + ".";
                                    }
                                }
                            } else {
                                str += j1.Efecto(j1, j2, golpe, ataq.critico, ataqueelegido, tipoataque, equipo);
                            }
                        }
                    } else {
                        ataq.NoQuitarPP();
                        System.out.println(j1.nombre.trim() + " está agotado, necesita descansar.");
                        str += j1.nombre + " está agotado, necesita descansar.";
                    }
                } else {
                    if (j1.estado.compareTo("Normal") == 0 || j1.estado.compareTo("Envenenado") == 0 || j1.estado.compareTo("Quemado") == 0 || j1.estado.compareTo("Paralizado") == 0 || j1.estado.compareTo("Confuso") == 0) {
                        if (j1.turnosincapacitado == 1) {
                            j1.turnosincapacitado--;
                            j1.pasar = 0;
                            j1.alalcance = true;
                            j1.volando = false;
                            j1.enterrado = false;
                            j1.sumergido = false;
                            str += j1.Atacar(j1, j2, ataq, equipo, ataquesj1);
                            if (j1.equijugador) {
                                for (int i = 0; i < PokemonGUI.botonataques.length; i++) {
                                    PokemonGUI.botonataques[i].setEnabled(true);
                                }
                                PokemonGUI.botoncambiarpokemon.setEnabled(true);
                                activarsolounboton = false;
                            }
                        } else {
                            j1.pasar = 1;
                            j1.turnosincapacitado--;
                        }
                    } else {
                        ataq.NoQuitarPP();
                        System.out.println(j1.nombre.trim() + " está completamente " + j1.estado + ".");
                        str += j1.nombre + " está completamente " + j1.estado + ".";
                        j1.alalcance = true;
                        j1.volando = false;
                        j1.enterrado = false;
                        j1.sumergido = false;
                        j1.turnosincapacitado = 0;
                    }
                }
            } else {
                if (ataqueelegido.compareTo(",") != 0) {
                    ataq.NoQuitarPP();
                }
                if (j1.retroceder) {
                    System.out.println(j1.nombre.trim() + " retrocedió.");
                    str += j1.nombre + " retrocedió.";
                    j1.noatacar = false;
                    j1.retroceder = false;
                }
            }
            if (turnossincurase > 0) {
                turnossincurase--;
            }
        }
        j1.prioridad = false;
        return str;
    }

    boolean TipoAtaqueIgualPokemon(Pokemon Pokemon, String tipoataque) {
        if (Pokemon.tipo1.trim().compareTo(tipoataque.trim()) == 0 || Pokemon.tipo2.trim().compareTo(tipoataque.trim()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    int ComprobarTipo(String tipoataque, Pokemon j1, Pokemon tipoj2) throws FileNotFoundException, IOException {
        String tipos[][];
        int i = 0;
        int resultado = 3;
        boolean continuar = true;
        if (j1.ataqueelegido.trim().compareTo("Poder oculto") == 0) {
            tipoataque = j1.naturaleza;
        }

        tipos = Lecturas.DatosTipos(tipoataque);

        while (i < tipos[0].length && continuar) {
            if (tipos[0][i].compareTo(tipoj2.tipo1) == 0) {
                resultado++;
                continuar = false;
            }
            i++;
        }
        i = 0;
        while (i < tipos[1].length && continuar) {
            if (tipos[1][i].trim().compareTo(tipoj2.tipo1.trim()) == 0) {
                resultado--;
                continuar = false;
            }
            i++;
        }
        i = 0;
        while (i < tipos[2].length && continuar) {
            if (tipos[2][i].trim().compareTo(tipoj2.tipo1.trim()) == 0) {
                resultado = 0;
                continuar = false;
            }
            i++;
        }
        if (resultado != 0) {
            continuar = true;
            i = 0;
            while (i < tipos[0].length && continuar) {
                if (tipos[0][i].trim().compareTo(tipoj2.tipo2.trim()) == 0) {
                    resultado++;
                    continuar = false;
                }
                i++;
            }
            i = 0;
            while (i < tipos[1].length && continuar) {
                if (tipos[1][i].trim().compareTo(tipoj2.tipo2.trim()) == 0) {
                    resultado--;
                    continuar = false;
                }
                i++;
            }
            i = 0;
            while (i < tipos[2].length && continuar) {
                if (tipos[2][i].trim().compareTo(tipoj2.tipo2.trim()) == 0) {
                    resultado = 0;
                    continuar = false;
                }
                i++;
            }
        }
        return resultado;
    }

    static boolean VivosEquipo(Pokemon[] Pokemon) {
        int cont = 0;
        for (int i = 0; i < Pokemon.length; i++) {
            if (Pokemon[i].Vivo() == true) {
                cont++;
            }
        }
        if (cont > 1) {
            return true;
        } else {
            return false;
        }
    }

    String AtacarSinPP() {
        return "Atacar";
    }

    String IgnorarDefensas(Pokemon j1, Pokemon j2, Ataque ataq) {
        String str = "";
        switch (j1.ataqueelegido) {
            case "Contraataque":
                if (j2.categoriaataque.trim().compareTo("F") == 0 && j2.golpe > 0) {
                    if (j1.velocidad < j2.velocidad || j2.prioridad) {
                        golpe = j2.golpe * 2;
                        j1.golpec = false;
                        nopuedeafectar = true;
                    } else {
                        System.out.println("¡Contraataque falló!");
                        str += "¡Contraataque falló!";
                    }
                } else {
                    System.out.println("¡Contraataque falló!");
                    str += "¡Contraataque falló!";
                }
                break;
            case "Manto espejo":
                if (j2.categoriaataque.trim().compareTo("E") == 0 && j2.golpe > 0) {
                    if (j1.velocidad < j2.velocidad || j2.prioridad) {
                        golpe = j2.golpe * 2;
                        j1.golpec = false;
                        nopuedeafectar = true;
                    } else {
                        str += "¡Manto espejo falló!";
                    }
                } else {
                    str += "¡Manto espejo falló!";
                }
                break;
            case "Onda mental":
            case "Psicocarga":
                golpe = ((j1.Golpe(ataq.nombre, antesgolpe, ataq.precision, ataq.critico, ataq.categoria, j1, j2) * 100) * (((Math.random() * 15) + 85) / 100)) / j2.defensa;
                break;
            case "Espada santa":
                golpe = ((j1.Golpe(ataq.nombre, antesgolpe, ataq.precision, ataq.critico, ataq.categoria, j1, j2) * 100) * (((Math.random() * 15) + 85) / 100)) / j2.defensabase;
                break;
        }
        return str;
    }

    public String AtaquesOneShot(Pokemon j1, Pokemon j2, double golpes, String ataqueelegido, String tipoataque, int eleccionj1, int eleccionj2, Ataque[] ataquesj1) {
        String str = "";
        switch (ataqueelegido.trim()) {
            case "Sacrificio":
                str += "¡¡" + j1.nombre + " se sacrificó ante " + j2.nombre + "!!\r\n";
                str += j1.nombre + " realizó " + (int) j1.salud + " de daño.";
                if (j2.salud - j1.salud > 0) {
                    j2.salud -= j1.salud;
                } else {
                    j2.salud = 0;
                }
                golpe = j1.salud;
                j1.salud = 0;
                break;
            case "Fisura":
            case "Guillotina":
            case "Frio polar":
            case "Perforador":
                if (Math.random() * 100 <= 30) {
                    System.out.println(ataqueelegido.trim() + " de " + this.nombre.trim() + " fue letal, ha realizado " + (int) j2.salud + " de daño.");
                    str += ataqueelegido.trim() + " de " + this.nombre.trim() + " fue letal, ha realizado " + (int) j2.salud + " de daño.";
                    golpe = j2.salud;
                    j2.salud = 0;
                } else {
                    System.out.println("¡¡" + j1.nombre.trim() + " falló " + ataqueelegido.trim() + "!!");
                    str += "¡¡" + j1.nombre.trim() + " falló " + ataqueelegido.trim() + "!!";
                }
                break;
        }
        return str;
    }

    public String AtaquesModificadoresDaño(Pokemon j1, Pokemon j2, double golpes, String ataqueelegido, String tipoataque, int eleccionj1, int eleccionj2, Ataque[] ataquesj1) throws FileNotFoundException, IOException {
        double calculo;
        int i = 0;
        String str = "";
        switch (ataqueelegido.trim()) {
            case "Cuerpopesado":
            case "Golpe calor":
                calculo = j1.peso / j2.peso;
                if (calculo < 2) {
                    antesgolpe = 40;
                } else {
                    if (calculo < 3) {
                        antesgolpe = 60;
                    } else {
                        if (calculo < 4) {
                            antesgolpe = 80;
                        } else {
                            if (calculo < 5) {
                                antesgolpe = 100;
                            } else {
                                antesgolpe = 120;
                            }
                        }
                    }
                }
                break;
            case "Descanso":
                if (!j1.protegeestado) {
                    j1.estado = "Dormido";
                    j1.contador = 10;
                    j1.salud = j1.saludmax;
                    str += j1.nombre + " se echó a dormir y recuperó toda su vitalidad.";
                } else {
                    str += j1.nombre + " intentó echarse a dormir pero no pudo debido a su protección contra problemas de estado.";
                }
                break;
            case "Espabila":
                if (j2.estado.compareTo("Dormido") == 0) {
                    antesgolpe = antesgolpe * 2;
                    j2.estado = "Normal";
                    str += j1.nombre + " espabiló a " + j2.nombre + " y le despertó.";
                }
                break;
            case "Estimulo":
                if (j2.estado.compareTo("Paralizado") == 0) {
                    antesgolpe = antesgolpe * 2;
                    j2.estado = "Normal";
                    str += j1.nombre + " estimuló a " + j2.nombre + " y le curó la paralisis.";
                }
                break;
            case "Giro bola":
                antesgolpe = (j2.velocidad / j1.velocidad) * 25;
                if (antesgolpe > 150) {
                    antesgolpe = 150;
                } else if (antesgolpe < 1) {
                    antesgolpe = 1;
                }
                break;
            case "Salmuera":
                if ((j2.saludmax / 2) > j2.salud) {
                    antesgolpe = antesgolpe * 2;
                }
                break;
            case "Come suenos":
            case "Ronquido":
                if (j2.estado.compareTo("Dormido") != 0) {
                    antesgolpe = 0;
                    str += "El " + j1.ataqueelegido + " de " + j1.nombre + " no tuvo ningún efecto al estar despierto.";
                }
                break;
            case "Azote":
            case "Inversion":
                calculo = (j1.salud / j1.saludmax) * 100;
                if (calculo >= 67) {
                    antesgolpe = 20;
                } else {
                    if (calculo >= 34) {
                        antesgolpe = 40;
                    } else {
                        if (calculo >= 20) {
                            antesgolpe = 80;
                        } else {
                            if (calculo >= 9) {
                                antesgolpe = 100;
                            } else {
                                if (calculo >= 3) {
                                    antesgolpe = 150;
                                } else {
                                    antesgolpe = 200;
                                }
                            }
                        }
                    }
                }
                break;
            case "Carga toxica":
                if (j2.estado.compareTo("Envenenado") == 0) {
                    antesgolpe += antesgolpe;
                }
                break;
            case "Imagen":
                if (j2.estado.compareTo("Quemado") == 0 || j2.estado.compareTo("Envenenado") == 0 || j2.estado.compareTo("Paralizado") == 0) {
                    antesgolpe += antesgolpe;
                }
                break;
            case "Patada baja":
            case "Hierba lazo":
                calculo = 20;
                if (j2.peso <= 10) {
                    calculo = 20;
                } else {
                    if (j2.peso <= 25) {
                        calculo = 40;
                    } else {
                        if (j2.peso <= 50) {
                            calculo = 60;
                        } else {
                            if (j2.peso <= 100) {
                                calculo = 80;
                            } else {
                                if (j2.peso <= 200) {
                                    calculo = 100;
                                } else {
                                    calculo = 120;
                                }
                            }
                        }
                    }
                }
                antesgolpe = calculo;
                break;
            case "Magnitud":
                calculo = (int) (Math.random() * 6) + 4;
                antesgolpe = calculo * 10;
                System.out.println(j1.nombre.trim() + "¡¡Magnitud " + calculo + "!!");
                str += j1.nombre + "¡¡Magnitud " + calculo + "!!";
                break;
            case "Poder oculto":
                do {
                    calculo = (int) ((Math.random() * 100) - 30);
                } while (calculo < 30);
                antesgolpe = calculo;
                break;
            case "Salpicar":
            case "Estallido":
                calculo = ((antesgolpe * j1.salud) / j1.saludmax);
                antesgolpe = calculo;
                break;
            case "Estrujon":
                calculo = antesgolpe * (j2.salud / j2.saludmax);
                antesgolpe = calculo;
                break;
            case "Desenrollar":
                if (ataqueelegidoanterior.compareTo(ataqueelegido) == 0) {
                }
                break;
            case "As oculto":
                switch (j1.ataque4pp) {
                    case 0:
                        antesgolpe = 200;
                        break;
                    case 1:
                        antesgolpe = 80;
                        break;
                    case 2:
                        antesgolpe = 60;
                        break;
                    case 3:
                        antesgolpe = 50;
                        break;
                    case 4:
                        antesgolpe = 40;
                        break;
                }
                break;
            case "Transformacion":
                j1.ataque = j2.ataque;
                j1.ataqueesp = j2.ataqueesp;
                j1.defensaesp = j2.defensaesp;
                j1.tipo1ditto = j1.tipo1;
                j1.tipo2ditto = j1.tipo2;
                j1.tipo1 = j2.tipo1;
                j1.tipo2 = j2.tipo2;
                j1.defensa = j2.defensa;
                j1.velocidad = j2.velocidad;
                j1.ataque1 = j2.ataque1;
                j1.ataque2 = j2.ataque2;
                j1.ataque3 = j2.ataque3;
                j1.ataque4 = j2.ataque4;
                j1.codigo = j2.codigo;
                ataque1ppditto = ataquesj1[0].pp;
                ataque2ppditto = ataquesj1[1].pp;
                ataque3ppditto = ataquesj1[2].pp;
                ataque4ppditto = ataquesj1[3].pp;

                ataquesj1[0] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j2.ataque1));
                ataquesj1[1] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j2.ataque2));
                ataquesj1[2] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j2.ataque3));
                ataquesj1[3] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j2.ataque4));

                System.out.println(j1.nombre.trim() + " adoptó todas las características de " + j2.nombre.trim());
                str += j1.nombre + " adoptó todas las características de " + j2.nombre;
                System.out.println(j1.tipo1 + "  " + j1.tipo2);
                break;
            default:
                break;
        }
        return str;
    }

    void DevolverEstadisticasBase(Pokemon j1, Pokemon j2, Ataque[] ataquesj1) {
        if (j2.Vivo() && j1.nombre.trim().compareTo("Kecleon") == 0) {
            j1.estado = "Normal";
        }
        if (j2.Vivo() && j1.nombre.trim().compareTo("Ditto") == 0 && j1.codigo != 132) {
            j1.ataque1 = String.valueOf(195);
            j1.ataque2 = String.valueOf(195);
            j1.ataque3 = String.valueOf(195);
            j1.ataque4 = String.valueOf(195);
            j1.ataque = j1.ataquebase;
            j1.ataqueesp = j1.ataqueespbase;
            j1.defensaesp = j1.defensaespbase;
            j1.tipo1 = j1.tipo1ditto;
            j1.tipo2 = j1.tipo2ditto;
            j1.defensa = j1.defensabase;
            j1.velocidad = j1.velocidadbase;
            j1.codigo = 132;

            PokemonGUI.tipo1jugador[pokemonencombatejugador].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + j1.tipo1 + ".gif"));
            if (j1.tipo2.compareTo("n*n*") != 0) {
                PokemonGUI.tipo2jugador[pokemonencombatejugador].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + j1.tipo2 + ".gif"));
            }

            ataquesj1[0] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j1.ataque1));
            ataquesj1[1] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j1.ataque2));
            ataquesj1[2] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j1.ataque3));
            ataquesj1[3] = Ataque.AtaqueElegidoInicializa(PokemonGUI.bdataque, Integer.parseInt(j1.ataque4));
            ataquesj1[0].pp = ataque1ppditto;
            ataquesj1[1].pp = ataque2ppditto;
            ataquesj1[2].pp = ataque3ppditto;
            ataquesj1[3].pp = ataque4ppditto;
        } else {
            j1.descansar = false;
        }
        j1.drenadoras = false;
        j1.mismodestino = false;
    }

    String MismoDestino(Pokemon j1, Pokemon j2) {
        String str = "";
        if (!j1.Vivo() && j2.mismodestino && j2.Vivo() && j2.muertedirecta) {
            j2.salud = 0;
            j2.estado = "Debilitado";
            System.out.println(j1.nombre.trim() + " se llevó a " + j2.nombre.trim() + ".");
            str += j1.nombre.trim() + " se llevó a " + j2.nombre.trim() + ".";
            j2.mismodestino = false;
            j1.golpe = j2.salud;
            if (j1.equijugador) {
                PokemonGUI.BajarBarraIAPocoAPoco(false);
            } else {
                PokemonGUI.BajarBarraJugadorPocoAPoco(false);
            }
            PokemonGUI.Mensajes.setText(str);
        } else {
            j2.mismodestino = false;
        }
        return str;
    }

    public static void Esquema() {
        if (equipojugador[pokemonencombatejugador].Vivo() && ataquejugador[pokemonencombatejugador][ataqueusadojugador].codigo == 261 && !atacaiaporcambiarpokemonjugador) {
            ataquejugador[pokemonencombatejugador][ataqueusadojugador] = Ataque.Esquema(ataquejugador[pokemonencombatejugador][ataqueusadojugador], ataqueIA[pokemonencombateIA][ataqueusadoIA]);
            str += equipojugador[pokemonencombatejugador].nombre + " copió el ataque de " + equipoIA[pokemonencombateIA];
            switch (ataqueusadojugador) {
                case 0:
                    equipojugador[pokemonencombatejugador].ataque1 = String.valueOf(ataquejugador[pokemonencombatejugador][ataqueusadojugador].codigo);
                    break;
                case 1:
                    equipojugador[pokemonencombatejugador].ataque2 = String.valueOf(ataquejugador[pokemonencombatejugador][ataqueusadojugador].codigo);
                    break;
                case 2:
                    equipojugador[pokemonencombatejugador].ataque3 = String.valueOf(ataquejugador[pokemonencombatejugador][ataqueusadojugador].codigo);
                    break;
                case 3:
                    equipojugador[pokemonencombatejugador].ataque4 = String.valueOf(ataquejugador[pokemonencombatejugador][ataqueusadojugador].codigo);
                    break;
                default:
                    break;
            }
            RefrescoAtaques();
        }
        atacaiaporcambiarpokemonjugador = false;
        if (equipoIA[pokemonencombateIA].Vivo() && ataqueIA[pokemonencombateIA][ataqueusadoIA].codigo == 261) {
            ataqueIA[pokemonencombateIA][ataqueusadoIA] = Ataque.Esquema(ataqueIA[pokemonencombateIA][ataqueusadoIA], ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
            str += equipoIA[pokemonencombateIA].nombre + " copió el ataque de " + equipojugador[pokemonencombatejugador];
            switch (ataqueusadoIA) {
                case 0:
                    equipoIA[pokemonencombateIA].ataque1 = String.valueOf(ataqueIA[pokemonencombateIA][ataqueusadoIA].codigo);
                    break;
                case 1:
                    equipoIA[pokemonencombateIA].ataque2 = String.valueOf(ataqueIA[pokemonencombateIA][ataqueusadoIA].codigo);
                    break;
                case 2:
                    equipoIA[pokemonencombateIA].ataque3 = String.valueOf(ataqueIA[pokemonencombateIA][ataqueusadoIA].codigo);
                    break;
                case 3:
                    equipoIA[pokemonencombateIA].ataque4 = String.valueOf(ataqueIA[pokemonencombateIA][ataqueusadoIA].codigo);
                    break;
                default:
                    break;
            }
        }
    }

    public String Efecto(Pokemon j1, Pokemon j2, double golpes, int critico, String ataqueelegido, String tipoataque, Pokemon[] equipo) {
        String str = "";
        double golp = golpes;
        double calculo;
        boolean nopuedebajar = false;
        int i;
        switch (ataqueelegido.trim()) {
            case "Foco energia":
                j1.subidacritico = 2;
                System.out.println(j1.nombre.trim() + " se está preparando para luchar.");
                str += j1.nombre + " se está preparando para luchar.";
                break;
            case "Disparo demora":
            case "Puntapie":
            case "Electrotela":
                if (j2.velocidad - 20 > velocidadmin) {
                    j2.velocidad -= 20;
                    System.out.println("La velocidad de " + j2.nombre.trim() + " bajó.");
                    str += "La velocidad de " + j2.nombre + " bajó.";
                } else {
                    j2.velocidad = velocidadmin;
                    str += "La velocidad de " + j2.nombre + " no puede bajar más.";
                }
                break;
            case "Niebla clara":
                j2.ataque = j2.ataquebase;
                j2.defensa = j2.defensabase;
                j2.ataqueesp = j2.ataqueespbase;
                j2.defensaesp = j2.defensaespbase;
                j2.velocidad = j2.velocidadbase;
                j2.precision = 100;
                j2.evasion = 100;
                j2.subidacritico = 0;
                str += j1.nombre + " envolvió el lugar con una clara neblina y devolvió las estadísticas de " + j2.nombre + " a su estado natural";
                break;
            case "Agilidad":
                if (j1.velocidad + 40 < velocidadmax) {
                    j1.velocidad += 40;
                    System.out.println("La velocidad de " + j1.nombre.trim() + " subió.");
                    str += "La velocidad de " + j1.nombre.trim() + " subió.";
                } else {
                    j1.velocidad = velocidadmax;
                    str += "La velocidad de " + j1.nombre.trim() + " no puede subir más.";
                }
                break;
            case "Cara susto":
                if (j2.velocidad - 40 > velocidadmin) {
                    j2.velocidad -= 40;
                    System.out.println("La velocidad de " + j2.nombre.trim() + " bajó mucho.");
                    str += "La velocidad de " + j2.nombre.trim() + " bajó mucho.";
                } else {
                    j2.velocidad = velocidadmin;
                    str += "La velocidad de " + j2.nombre.trim() + " no puede subir más.";
                }
                break;
            case "Ataque arena":
            case "Pantalla humo":
            case "Bofeton lodo":
            case "Destello":
            case "Kinetico":
                if (j2.precision - 10 >= precisionmin) {
                    j2.precision -= 10;
                } else {
                    j2.precision = precisionmin;
                }
                System.out.println(j1.nombre.trim() + " bajó la precisión a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                str += j1.nombre + " bajó la precisión a " + j2.nombre + " con " + ataqueelegido + ".";
                break;
            case "Pulso noche":
                if (Math.random() * 100 < 40) {
                    if (j2.precision - 10 >= precisionmin) {
                        j2.precision -= 10;
                    } else {
                        j2.precision = precisionmin;
                    }
                    str += j1.nombre + " hizo que cayera la noche y bajó la precisión de " + j2.nombre;
                }
                break;
            case "Meditacion":
                if (j1.ataque + 20 <= ataquemax) {
                    j1.ataque += 20;
                } else {
                    j1.ataque = ataquemax;
                }
                str += j1.nombre + " aumentó su ataque";
                break;
            case "Cambio de marcha":
                if (j1.ataque + 20 <= ataquemax) {
                    j1.ataque += 20;
                } else {
                    j1.ataque = ataquemax;
                }
                if (j1.velocidad + 40 <= velocidadmax) {
                    j1.velocidad += 40;
                } else {
                    j1.velocidad = velocidadmax;
                }
                str += j1.nombre + " aumentó su velocidad y su ataque";
                break;
            case "Danza dragon":
                if (j1.ataque + 20 <= ataquemax) {
                    j1.ataque += 20;
                } else {
                    j1.ataque = ataquemax;
                }
                if (j1.velocidad + 20 <= velocidadmax) {
                    j1.velocidad += 20;
                } else {
                    j1.velocidad = velocidadmax;
                }
                str += j1.nombre + " aumentó su velocidad y su ataque";
                break;
            case "Afilagarras":
                if (j1.ataque + 20 <= ataquemax) {
                    j1.ataque += 20;
                } else {
                    j1.ataque = ataquemax;
                }
                if (j1.precision + 10 <= precisionmax) {
                    j1.precision += 10;
                } else {
                    j1.precision = precisionmax;
                }
                str += j1.nombre + " se afiló las garras y subió su ataque y precisión.";
                break;
            case "Agua lodosa":
            case "Bomba fango":
                if (Math.random() * 100 < 30) {
                    if (j2.precision - 10 >= precisionmin) {
                        j2.precision -= 10;
                    } else {
                        j2.precision = precisionmin;
                    }
                    System.out.println(j1.nombre.trim() + " bajó la precisión de " + j2.nombre + " con " + ataqueelegido.trim() + ".");
                    str += j1.nombre + "bajó la precisión de " + j2.nombre + " con " + ataqueelegido.trim() + ".";
                }
                break;
            case "Pulpocanon":
                if (Math.random() * 100 < 50) {
                    if (j2.precision - 10 >= precisionmin) {
                        j2.precision -= 10;
                    } else {
                        j2.precision = precisionmin;
                    }
                    System.out.println(j1.nombre.trim() + " bajó la precisión de " + j2.nombre + " con " + ataqueelegido.trim() + ".");
                    str += j1.nombre + "bajó la precisión de " + j2.nombre + " con " + ataqueelegido.trim() + ".";
                }
                break;
            case "Disparo espejo":
                if (Math.random() * 100 < 30) {
                    if (j2.precision - 10 >= precisionmin) {
                        j2.precision -= 10;
                    } else {
                        j2.precision = precisionmin;
                    }
                    System.out.println(j1.nombre.trim() + " bajó la precisión de " + j2.nombre + " con " + ataqueelegido.trim() + ".");
                    str += j1.nombre + "bajó la precisión de " + j2.nombre + " con " + ataqueelegido.trim() + ".";
                }
                break;
            case "Alivio":
                if (j1.estado.compareTo("Envenenado") == 0) {
                    str += j1.nombre + " extrajo el veneno de su cuerpo.";
                    j1.estado = "Normal";
                } else if (j1.estado.compareTo("Quemado") == 0) {
                    str += j1.nombre + " hizo sanar la brutal quemadura que sufría.";
                    j1.ataque += 40;
                    j1.estado = "Normal";
                } else if (j1.estado.compareTo("Paralizado") == 0) {
                    str += j1.nombre + " eliminó su parálisis al instante.";
                    j1.velocidad = j1.velocidad * 4;
                    j1.estado = "Normal";
                } else {
                    str += "Alivio de " + j1.nombre + "no eliminó ningún problema de estado";
                }
                break;
            case "Aromaterapia":
                for (int j = 0; j < equipo.length; j++) {
                    if (equipo[j].estado.compareTo("Debilitado") != 0 && equipo[j].estado.compareTo("Congelado") != 0 && equipo[j].estado.compareTo("Maldito") != 0 && equipo[j].estado.compareTo("Confuso") != 0 && equipo[j].estado.compareTo("Enamorado") != 0) {
                        switch (equipo[j].estado) {
                            case "Quemado":
                                equipo[j].ataque += 40;
                                break;
                            case "Paralizado":
                                equipo[j].velocidad = equipo[j].velocidad * 4;
                                break;
                            default:
                                break;
                        }
                        equipo[j].estado = "Normal";
                    }
                }
                break;
            case "Campana cura":
                for (int j = 0; j < equipo.length; j++) {
                    if (equipo[j].estado.compareTo("Debilitado") != 0 && equipo[j].estado.compareTo("Maldito") != 0 && equipo[j].estado.compareTo("Confuso") != 0 && equipo[j].estado.compareTo("Enamorado") != 0) {
                        switch (equipo[j].estado) {
                            case "Quemado":
                                equipo[j].ataque += 40;
                                break;
                            case "Paralizado":
                                equipo[j].velocidad = equipo[j].velocidad * 4;
                                break;
                            default:
                                break;
                        }
                        equipo[j].estado = "Normal";
                    }
                }
                System.out.println(j1.nombre.trim() + " devolvió a todos los miembros del equipo a su estado natural.");
                str += j1.nombre + " devolvió a todos los miembros del equipo a su estado natural.";
                break;
            case "Fortaleza":
            case "Rizo defensa":
            case "Refugio":
                if (this.defensa < defensamax) {
                    this.defensa += 40;
                    System.out.println("La defensa de " + this.nombre.trim() + " aumentó.");
                    str += "La defensa de " + this.nombre + " aumentó.";
                } else {
                    this.defensa = defensamax;
                    System.out.println("La defensa de " + this.nombre.trim() + " no puede aumentar más.");
                    str += "La defensa de " + this.nombre + " no puede aumentar más.";
                }
                break;

            case "Armadura acida":
            case "Defensa ferrea":
                if (this.defensa < defensamax) {
                    this.defensa += 80;
                    System.out.println("La defensa de " + this.nombre.trim() + " aumentó.");
                    str += "La defensa de " + this.nombre + " aumentó.";
                } else {
                    this.defensa = defensamax;
                    System.out.println("La defensa de " + this.nombre.trim() + " no puede aumentar más.");
                    str += "La defensa de " + this.nombre + " no puede aumentar más.";
                }
                break;
            case "Rizo algodon":
                if (this.defensa < defensamax) {
                    this.defensa += 120;
                    System.out.println("La defensa de " + this.nombre.trim() + " aumentó.");
                    str += "La defensa de " + this.nombre + " aumentó.";
                } else {
                    this.defensa = defensamax;
                    System.out.println("La defensa de " + this.nombre.trim() + " no puede aumentar más.");
                    str += "La defensa de " + this.nombre + " no puede aumentar más.";
                }
                break;
            case "Concha filo":
                if (Math.random() * 100 < 50) {
                    if (j2.defensa - 10 >= defensamin) {
                        j2.defensa -= 10;
                        System.out.println("La defensa de " + j2.nombre.trim() + " bajó.");
                        str += "La defensa de " + j2.nombre + " bajó.";
                    } else {
                        j2.defensa = defensamin;
                        System.out.println("La defensa de " + j2.nombre.trim() + " no puede bajar más.");
                        str += "La defensa de " + j2.nombre.trim() + " no puede bajar más.";
                    }
                }
                break;
            case "Malicioso":
                if (j2.defensa - 10 > defensamin) {
                    j2.defensa -= 10;
                    System.out.println("La defensa de " + j2.nombre.trim() + " bajó.");
                    str += "La defensa de " + j2.nombre + " bajó.";
                } else {
                    j2.defensa = defensamin;
                    System.out.println("La defensa de " + j2.nombre.trim() + " no puede bajar más.");
                    str += "La defensa de " + j2.nombre + " no puede bajar más.";
                }
                break;
            case "Garra brutal":
                if (Math.random() * 100 < 50) {
                    if (j2.defensa - 10 > defensamin) {
                        j2.defensa -= 10;
                        System.out.println("La defensa de " + j2.nombre.trim() + " bajó.");
                        str += "La defensa de " + j2.nombre + " bajó.";
                    } else {
                        j2.defensa = defensamin;
                        System.out.println("La defensa de " + j2.nombre.trim() + " no puede bajar más.");
                        str += "La defensa de " + j2.nombre + " no puede bajar más.";
                    }
                }
                break;
            case "Cola ferrea":
                if (Math.random() * 100 < 10) {
                    if (j2.defensa - 10 > defensamin) {
                        j2.defensa -= 10;
                        System.out.println("La defensa de " + j2.nombre.trim() + " bajó.");
                        str += "La defensa de " + j2.nombre.trim() + " bajó.";
                    } else {
                        j2.defensa = defensamin;
                        System.out.println("La defensa de " + j2.nombre.trim() + " no puede bajar más.");
                        str += "La defensa de " + j2.nombre.trim() + " no puede bajar más.";
                    }
                }
                break;
            case "Golpe roca":
                if (Math.random() * 100 < 10) {
                    if (j2.defensa - 10 > defensamin) {
                        j2.defensa -= 10;
                        System.out.println("La defensa de " + j2.nombre.trim() + " bajó.");
                        str += "La defensa de " + j2.nombre.trim() + " bajó.";
                    } else {
                        j2.defensa = defensamin;
                        System.out.println("La defensa de " + j2.nombre.trim() + " no puede bajar más.");
                        str += "La defensa de " + j2.nombre.trim() + " no puede bajar más.";
                    }
                }
                break;
            case "Absorber":
                calculo = 0.1 * golpes;
                if (j2.salud - calculo < 0) {
                    calculo = j2.salud * 0.1;
                }
                if (this.salud + calculo <= this.saludmax) {
                    this.salud += calculo;
                } else {
                    this.salud = this.saludmax;
                }
                System.out.println(this.nombre.trim() + " absorbió " + (int) calculo + " de salud.");
                str += this.nombre.trim() + " absorbió " + (int) calculo + " de salud.";
                break;
            case "Chupavidas":
                calculo = 0.15 * golpes;
                if (j2.salud - calculo < 0) {
                    calculo = j2.salud * 0.15;
                }
                if (this.salud + calculo <= this.saludmax) {
                    this.salud += calculo;
                } else {
                    this.salud = this.saludmax;
                }
                System.out.println(this.nombre.trim() + " absorbió " + (int) calculo + " de salud.");
                str += this.nombre.trim() + " absorbió " + (int) calculo + " de salud.";
                break;
            case "Megaagotar":
                calculo = 0.2 * golpes;
                if (j2.salud - calculo < 0) {
                    calculo = j2.salud * 0.2;
                }
                if (this.salud + calculo <= this.saludmax) {
                    this.salud += calculo;
                } else {
                    this.salud = this.saludmax;
                }
                System.out.println(this.nombre.trim() + " absorbió " + (int) calculo + " de salud.");
                str += this.nombre.trim() + " absorbió " + (int) calculo + " de salud.";
                break;
            case "Gigadrenado":
                calculo = 0.3 * golpes;
                if (j2.salud - calculo < 0) {
                    calculo = j2.salud * 0.3;
                }
                if (this.salud + calculo <= this.saludmax) {
                    this.salud += calculo;
                } else {
                    this.salud = this.saludmax;
                }
                System.out.println(this.nombre.trim() + " absorbió " + (int) calculo + " de salud.");
                str += this.nombre.trim() + " absorbió " + (int) calculo + " de salud.";
                break;
            case "Come suenos":
            case "Puno drenaje":
            case "Asta drenaje":
            case "Carga parabola":
                calculo = 0.5 * golpes;
                if (this.salud + calculo <= this.saludmax) {
                    this.salud += calculo;
                } else {
                    this.salud = this.saludmax;
                }
                System.out.println(this.nombre.trim() + " absorbió " + (int) calculo + " de salud de " + j2.nombre.trim() + ".");
                str += this.nombre.trim() + " absorbió " + (int) calculo + " de salud de " + j2.nombre.trim() + ".";
                break;
            case "Beso drenaje":
                calculo = 0.75 * golpes;
                if (this.salud + calculo <= this.saludmax) {
                    this.salud += calculo;
                } else {
                    this.salud = this.saludmax;
                }
                System.out.println(this.nombre.trim() + " absorbió " + (int) calculo + " de salud.");
                str += this.nombre.trim() + " absorbió " + (int) calculo + " de salud.";
                break;
            case "Doble filo":
            case "Derribo":
            case "Sumision":
            case "Voltio cruel":
            case "Atacar":
                calculo = 0.25 * golpes;
                if (this.salud - calculo > 0) {
                    this.salud -= calculo;
                    System.out.println(this.nombre.trim() + " también se hizo " + (int) (calculo) + " de daño a si mismo.");
                    str += this.nombre.trim() + " también se hizo " + (int) (calculo) + " de daño a si mismo.";
                } else {
                    this.salud = 0;
                    System.out.println(this.nombre.trim() + " murió a consecuencia del impacto sufrido contra " + j2.nombre.trim() + ".");
                    str += this.nombre.trim() + " murió a consecuencia del impacto sufrido contra " + j2.nombre.trim() + ".";
                }
                break;
            case "Pajaro osado":
            case "Mazazo":
            case "Placaje electrico":
            case "Envite igneo":
                calculo = 0.33 * golpes;
                if (this.salud - calculo > 0) {
                    this.salud -= calculo;
                    System.out.println(this.nombre.trim() + " también se hizo " + (int) (calculo) + " de daño a si mismo.");
                    str += this.nombre.trim() + " también se hizo " + (int) (calculo) + " de daño a si mismo.";
                } else {
                    this.salud = 0;
                    System.out.println(this.nombre.trim() + " murió a consecuencia del impacto sufrido contra " + j2.nombre.trim() + ".");
                    str += this.nombre.trim() + " murió a consecuencia del impacto sufrido contra " + j2.nombre.trim() + ".";
                }
                break;
            case "Testarazo":
            case "Luz aniquiladora":
                calculo = 0.5 * golpes;
                if (this.salud - calculo > 0) {
                    this.salud -= calculo;
                    System.out.println(this.nombre.trim() + " también se hizo " + (int) (calculo) + " de daño a si mismo.");
                    str += this.nombre.trim() + " también se hizo " + (int) (calculo) + " de daño a si mismo.";
                } else {
                    this.salud = 0;
                    System.out.println(this.nombre.trim() + " murió a consecuencia del impacto sufrido contra " + j2.nombre.trim() + ".");
                    str += this.nombre.trim() + " murió a consecuencia del impacto sufrido contra " + j2.nombre.trim() + ".";
                }
                break;
            case "Falsotortazo":
                if ((j2.salud - golpes) <= 0) {
                    j2.salud = 1;
                    golpe = 0;
                    System.out.println("El objetivo no puede morir con Falsotortazo.");
                    str += "El objetivo no puede morir con Falsotortazo.";
                }
                break;
            case "Autodestruccion":
            case "Explosion":
                System.out.println(this.nombre.trim() + " se inmoló con " + ataqueelegido.trim() + ".");
                str += this.nombre.trim() + " se inmoló con " + ataqueelegido.trim() + ".";
                this.salud = 0;
                break;
            case "Drenadoras":
                j2.drenadoras = true;
                System.out.println(j2.nombre.trim() + " fue invadido por las drenadoras.");
                str += j2.nombre.trim() + " fue invadido por las drenadoras.";
                break;
            case "Salpicadura":
                System.out.println("La " + ataqueelegido.trim() + " de " + j1.nombre.trim() + " no tuvo ningún efecto.");
                str += "La " + ataqueelegido.trim() + "de " + j1.nombre.trim() + " no tuvo ningún efecto.";
                break;
            case "Psicoonda":
                do {
                    aleatorio = Math.random() * 100;
                } while (aleatorio < 40 || aleatorio > 90);
                golpe = aleatorio;
                System.out.println(ataqueelegido.trim() + " realizó " + (int) aleatorio + " de daño.");
                str += ataqueelegido.trim() + " realizó " + (int) aleatorio + " de daño.";
                break;
            case "Recuperacion":
            case "Amortiguador":
            case "Sintesis":
            case "Respiro":
            case "Luz lunar":
            case "Pulso cura":
            case "Batido":
            case "Sol matinal":
                if (j1.salud == j1.saludmax && turnossincurase > 0) {
                    System.out.println(ataqueelegido.trim() + " ha fallado.");
                    str += ataqueelegido.trim() + " ha fallado.";
                } else {
                    calculo = j1.saludmax / 3;
                    if (j1.salud + calculo <= j1.saludmax) {
                        j1.salud += calculo;
                        System.out.println(j1.nombre.trim() + " se curó con " + ataqueelegido.trim() + " " + (int) calculo + " de salud.");
                        str += j1.nombre.trim() + " se curó con " + ataqueelegido + " " + (int) calculo + " de salud.";
                    } else {
                        calculo = j1.saludmax - j1.salud;
                        j1.salud = j1.saludmax;
                        System.out.println(j1.nombre.trim() + " se curó con " + ataqueelegido.trim() + " " + (int) calculo + " de salud.");
                        str += j1.nombre + " se curó con " + ataqueelegido + " " + (int) calculo + " de salud.";
                    }
                }
                break;
            case "Anticura":
                if (j2.turnossincurase == 0) {
                    j2.turnossincurase = 5;
                    str += j1.nombre + " impide que " + j2.nombre + " se cure durante los próximos 5 turnos.";
                } else {
                    str += "Anticura ha fallado.";
                }
                break;
            case "Teletransporte":
                System.out.println(j1.nombre.trim() + " intentó teletransportarse, pero no tuvo ningún efecto.");
                str += j1.nombre + " intentó teletransportarse, pero no tuvo ningún efecto.";
                break;
            case "Hidropulso":
            case "Treparrocas":
                if (Math.random() * 100 < 20) {
                    if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                        j2.estado = "Confuso";
                        j2.contador = 10;
                        System.out.println(j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".");
                        str += j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".";
                    } else {
                        System.out.println(j2.nombre.trim() + " ya está confuso.");
                        str += j2.nombre.trim() + " ya está confuso.";
                    }
                }
                break;
            case "Supersonico":
            case "Rayo confuso":
            case "Punodinamico":
            case "Beso dulce":
            case "Danza caos":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                    j2.estado = "Confuso";
                    j2.contador = 10;
                    System.out.println(j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".");
                    str += j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".";
                } else {
                    System.out.println(j2.nombre.trim() + " ya está confuso.");
                    str += j2.nombre.trim() + " ya está confuso.";
                }
                break;
            case "Doble rayo":
                if (Math.random() * 100 < 10) {
                    if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                        j2.estado = "Confuso";
                        j2.contador = 10;
                        System.out.println(j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".");
                        str += j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".";
                    } else {
                        System.out.println(j2.nombre.trim() + " ya está confuso.");
                        str += j2.nombre.trim() + " ya está confuso.";
                    }
                }
                break;
            case "Vendaval":
                if (Math.random() * 100 < 30) {
                    if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                        j2.estado = "Confuso";
                        j2.contador = 10;
                        System.out.println(j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".");
                        str += j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".";
                    } else {
                        System.out.println(j2.nombre.trim() + " ya está confuso.");
                        str += j2.nombre.trim() + " ya está confuso.";
                    }
                }
                break;
            case "Camelo":
                if (j2.estado.compareTo("Normal") != 0 && !j2.protegeestado) {
                    if (j2.ataque + 20 < ataquemax) {
                        j2.ataque += 20;
                    } else {
                        j2.ataque = ataquemax;
                    }
                    j2.estado = "Confuso";
                    str += j1.nombre + " subió el ataque a " + j2.nombre + " y este se confundió";
                }
                break;
            case "Doble ataque":
                for (i = 0; i < 2; i++) {
                    if (j2.salud - golpe > 0) {
                        j2.salud -= golpe;
                        if (Math.random() * 100 < 20) {
                            j2.estado = "Envenenado";
                            str += j1.nombre + "envenenó a " + j2.nombre + ". ";
                        }
                    } else {
                        j2.salud = 0;
                    }
                    str += "Golpe " + (i + 1) + " realizó " + (int) golpe + " de daño.";
                }
                break;
            case "Colmillo venenoso":
            case "Residuos":
            case "Picotazo venenoso":
            case "Bomba lodo":
            case "Lanza mugre":
            case "Puya nociva":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado && (Math.random() * 100) < 30) {
                    if (j2.tipo1.trim().compareTo("Veneno") != 0 && j2.tipo2.trim().compareTo("Veneno") != 0 && j2.tipo1.trim().compareTo("Acero") != 0 && j2.tipo2.trim().compareTo("Acero") != 0) {
                        System.out.println(j1.nombre.trim() + " envenenó a " + j2.nombre.trim() + " con " + ataqueelegido + ".");
                        str += j1.nombre.trim() + " envenenó a " + j2.nombre.trim() + " con " + ataqueelegido + ".";
                        j2.estado = "Envenenado";
                    } else {
                        if (j2.tipo1.trim().compareTo("Veneno") == 0 || j2.tipo2.trim().compareTo("Veneno") == 0) {
                            System.out.println(j2.nombre.trim() + " es de tipo veneno y no se ve afectado por tóxico.");
                            str += j2.nombre.trim() + " es de tipo veneno y no se ve afectado por tóxico.";
                        } else {
                            System.out.println(j2.nombre.trim() + " es de tipo acero y no se ve afectado por tóxico.");
                            str += j2.nombre.trim() + " es de tipo acero y no se ve afectado por tóxico.";
                        }
                    }
                }
                break;
            case "Trampa venenosa":
                if (j2.estado.compareTo("Envenenado") == 0) {
                    if (j2.ataque > ataquemin) {
                        this.ataque -= 20;
                    } else {
                        this.ataque = ataquemin;
                    }
                    if (j2.ataqueesp > ataqueespmin) {
                        this.ataqueesp -= 20;
                    } else {
                        this.ataqueesp = ataqueespmin;
                    }
                    if (j2.velocidad > velocidadmin) {
                        this.velocidad -= 20;
                    } else {
                        this.velocidad = velocidadmin;
                    }
                    str += "El ataque, ataque especial y velocidad de " + j2.nombre + " bajó.";
                }
                break;
            case "Toxico":
            case "Polvo veneno":
            case "Gas venenoso":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                    if (j2.tipo1.trim().compareTo("Veneno") != 0 && j2.tipo2.trim().compareTo("Veneno") != 0 && j2.tipo1.trim().compareTo("Acero") != 0 && j2.tipo2.trim().compareTo("Acero") != 0) {
                        System.out.println(j1.nombre.trim() + " envenenó a " + j2.nombre.trim() + " con " + ataqueelegido + ".");
                        str += j1.nombre.trim() + " envenenó a " + j2.nombre.trim() + " con " + ataqueelegido + ".";
                        j2.estado = "Envenenado";
                    } else {
                        if (j2.tipo1.trim().compareTo("Veneno") == 0 && j2.tipo2.trim().compareTo("Veneno") == 0) {
                            System.out.println(j2.nombre.trim() + " es de tipo veneno y no se ve afectado por " + ataqueelegido + ".");
                            str += j2.nombre.trim() + " es de tipo veneno y no se ve afectado por " + ataqueelegido + ".";
                        } else {
                            System.out.println(j2.nombre.trim() + " es de tipo acero y no se ve afectado por " + ataqueelegido + ".");
                            str += j2.nombre.trim() + " es de tipo acero y no se ve afectado por " + ataqueelegido + ".";
                        }
                    }
                }
                break;
            case "Onda trueno":
            case "Paralizador":
            case "Moflete estatico":
            case "Deslumbrar":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                    if (j2.tipo1.trim().compareTo("Electrico") != 0 && j2.tipo2.trim().compareTo("Electrico") != 0) {
                        System.out.println(j1.nombre.trim() + " paralizó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                        str += j1.nombre.trim() + " paralizó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                        j2.velocidad = j2.velocidad / 4;
                        j2.estado = "Paralizado";
                    } else {
                        System.out.println(j2.nombre.trim() + " es de tipo eléctrico y no se ve afectado por " + ataqueelegido.trim() + ".");
                        str += j2.nombre.trim() + " es de tipo eléctrico y no se ve afectado por " + ataqueelegido.trim() + ".";
                    }
                } else {
                    System.out.println(j2.nombre.trim() + " ya está paralizado.");
                    str += j2.nombre.trim() + " ya está paralizado.";
                }
                break;
            case "Bote":
            case "Lenguetazo":
                if (Math.random() * 100 < 10) {
                    if (j2.estado.compareTo("Normal") == 0 && j2.tipo1.compareTo("Normal") != 0 && j2.tipo2.compareTo("Normal") != 0 && !j2.protegeestado) {
                        j2.velocidad = j2.velocidad / 4;
                        j2.estado = "Paralizado";
                        System.out.println(j2.nombre.trim() + " quedó paralizado por el " + ataqueelegido.trim() + ".");
                        str += j2.nombre.trim() + " quedó paralizado por el " + ataqueelegido.trim() + ".";
                    }
                }
                break;
            case "Dragoaliento":
            case "Golpe cuerpo":
            case "Palmeo":
                if (Math.random() * 100 < 30) {
                    if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                        j2.velocidad = j2.velocidad / 4;
                        j2.estado = "Paralizado";
                        System.out.println(j2.nombre.trim() + " quedó paralizado por el " + ataqueelegido.trim() + ".");
                        str += j2.nombre.trim() + " quedó paralizado por el " + ataqueelegido.trim() + ".";
                    }
                }
                break;
            case "Fuego fatuo":
            case "Infierno":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                    if (j2.tipo1.trim().compareTo("Fuego") != 0 && j2.tipo2.trim().compareTo("Fuego") != 0) {
                        System.out.println(j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                        str += j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                        j2.ataque -= 40;
                        j2.estado = "Quemado";
                    } else {
                        System.out.println(j2.nombre.trim() + " es de tipo fuego y no se ve afectado por " + ataqueelegido.trim() + ".");
                        str += j2.nombre.trim() + " es de tipo fuego y no se ve afectado por " + ataqueelegido.trim() + ".";
                    }
                } else {
                    System.out.println(j2.nombre.trim() + " ya está quemado.");
                    str += j2.nombre.trim() + " ya está quemado.";
                }
                break;
            case "Fuego sagrado":
                if (Math.random() * 100 < 50) {
                    if (j2.estado.compareTo("Normal") == 0 || j2.estado.compareTo("Congelado") == 0 && !j2.protegeestado) {
                        if (j2.tipo1.trim().compareTo("Fuego") != 0 && j2.tipo2.trim().compareTo("Fuego") != 0) {
                            System.out.println(j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                            str += j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                            j2.ataque -= 40;
                            j2.estado = "Quemado";
                        } else {
                            System.out.println(j2.nombre.trim() + " es de tipo fuego y no se ve afectado por " + ataqueelegido.trim() + ".");
                            str += j2.nombre.trim() + " es de tipo fuego y no se ve afectado por " + ataqueelegido.trim() + ".";
                        }
                    } else {
                        System.out.println(j2.nombre.trim() + " ya está quemado.");
                        str += j2.nombre.trim() + " ya está quemado.";
                    }
                }
                break;
            case "Escaldar":
                if (Math.random() * 100 < 30) {
                    if (j2.estado.compareTo("Normal") == 0 || j2.estado.compareTo("Congelado") == 0 && !j2.protegeestado) {
                        if (j2.tipo1.trim().compareTo("Fuego") != 0 && j2.tipo2.trim().compareTo("Fuego") != 0) {
                            System.out.println(j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                            str += j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                            j2.ataque -= 40;
                            j2.estado = "Quemado";
                        } else {
                            System.out.println(j2.nombre.trim() + " es de tipo fuego y no se ve afectado por el calor de " + ataqueelegido.trim() + ".");
                            str += j2.nombre.trim() + " es de tipo fuego y no se ve afectado por el calor de " + ataqueelegido.trim() + ".";
                        }
                    } else {
                        if (j2.protegeestado) {
                            str += j2.nombre + " está protedigo frente a los problemas de estado";
                        } else {
                            System.out.println(j2.nombre.trim() + " ya está quemado.");
                            str += j2.nombre.trim() + " ya está quemado.";
                        }
                    }
                }
                break;
            case "Llama azul":
                if (Math.random() * 100 < 20) {
                    if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                        if (j2.tipo1.trim().compareTo("Fuego") != 0 && j2.tipo2.trim().compareTo("Fuego") != 0) {
                            System.out.println(j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                            str += j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                            j2.ataque -= 40;
                            j2.estado = "Quemado";
                        } else {
                            System.out.println(j2.nombre.trim() + " es de tipo fuego y no se ve afectado por el calor de " + ataqueelegido.trim() + ".");
                            str += j2.nombre.trim() + " es de tipo fuego y no se ve afectado por el calor de " + ataqueelegido.trim() + ".";
                        }
                    } else {
                        System.out.println(j2.nombre.trim() + " ya está quemado.");
                        str += j2.nombre.trim() + " ya está quemado.";
                    }
                }
                break;
            case "Maldicion":
                if (j1.salud - (j1.saludmax / 2) < 0) {
                    j1.salud = 0;
                } else {
                    j1.salud -= j1.saludmax / 2;
                }
                System.out.println(j1.nombre.trim() + " maldijo a " + j2.nombre.trim() + " y se realizó a si mismo " + (int) j1.saludmax / 2 + " de daño.");
                str += j1.nombre.trim() + " maldijo a " + j2.nombre.trim() + " y se realizó a si mismo " + (int) j1.saludmax / 2 + " de daño.";
                j2.estado = "Maldito";
                break;
            case "Canto":
            case "Beso amoroso":
            case "Hipnosis":
            case "Somnifero":
            case "Espora":
            case "Brecha negra":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                    j2.estado = "Dormido";
                    j2.contador = 10;
                    System.out.println(j1.nombre.trim() + " durmió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".");
                    str += j1.nombre.trim() + " durmió a " + j2.nombre.trim() + " con " + j1.ataqueelegido.trim() + ".";
                } else {
                    System.out.println(j2.nombre.trim() + " ya está " + j2.estado.trim() + ".");
                    str += j2.nombre.trim() + " ya está " + j2.estado.trim() + ".";
                }
                break;

            case "Doble bofeton":
            case "Recurrente":
            case "Pin misil":
            case "Puno cometa":
            case "Carambano":
            case "Golpes furia":
            case "Empujon":
            case "Pedrada":
            case "Clavocanon":
            case "Presa":
            case "Ataque oseo":
            case "Ataque furia":
            case "Plumerazo":
            case "Shuriken de agua":
                numataques = (int) ((Math.random() * 4) + 2);
                for (i = 0; i < numataques; i++) {
                    if (Math.random() * 100 > 85) {
                        System.out.println(j1.nombre.trim() + " falló, el golpe " + (i + 1) + ".");
                        str += j1.nombre.trim() + " falló, el golpe " + (i + 1) + ".\r\n";
                    } else {
                        System.out.println(j1.nombre.trim() + " realiza " + (int) golp + " de daño, golpe " + (i + 1) + ".");
                        str += j1.nombre.trim() + " realiza " + (int) golp + " de daño, golpe " + (i + 1) + ".\r\n";
                        golpe += golp;
                    }
                }
                golpe -= golp;
                System.out.println(j1.nombre.trim() + " golpeó " + i + " veces, infligiendo " + (int) golpe + " de daño con " + ataqueelegido.trim() + ".");
                str += j1.nombre.trim() + " golpeó " + i + " veces, infligiendo " + (int) golpe + " de daño con " + ataqueelegido.trim() + ".";
                break;
            case "Doble patada":
            case "Golpe bis":
            case "Rueda doble":
                for (i = 0; i < 2; i++) {
                    System.out.println(j1.nombre.trim() + " realiza " + (int) golp + " de daño, golpe " + (i + 1) + ".");
                    str += j1.nombre.trim() + " realiza " + (int) golp + " de daño, golpe " + (i + 1) + ".";
                    golpe += golp;
                }
                golpe -= golp;
                System.out.println(j1.nombre.trim() + " golpeó " + i + " veces, infligiendo " + (int) golpe + " de daño con " + ataqueelegido.trim() + ".");
                str += j1.nombre.trim() + " golpeó " + i + " veces, infligiendo " + (int) golpe + " de daño con " + ataqueelegido.trim() + ".";
                break;
            case "Doble golpe":
                for (i = 0; i < 2; i++) {
                    if (Math.random() * 100 > 90) {
                        System.out.println(j1.nombre.trim() + " falló, el golpe " + (i + 1) + ".");
                        str += j1.nombre.trim() + " falló, el golpe " + (i + 1) + ".";
                    } else {
                        System.out.println(j1.nombre.trim() + " realiza " + (int) golp + " de daño, golpe " + (i + 1) + ".");
                        str += j1.nombre.trim() + " realiza " + (int) golp + " de daño, golpe " + (i + 1) + ".";
                        golpe += golp;
                    }
                }
                golpe -= golp;
                System.out.println(j1.nombre.trim() + " golpeó " + i + " veces, infligiendo " + (int) golpe + " de daño con " + ataqueelegido.trim() + ".");
                str += j1.nombre.trim() + " golpeó " + i + " veces, infligiendo " + (int) golpe + " de daño con " + ataqueelegido.trim() + ".";
                break;
            case "Superdiente":
                golpe = j2.salud / 3;
                System.out.println("Superdiente ha inflijido " + (int) golpe + " de daño.");
                str += "Superdiente ha inflijido " + (int) golpe + " de daño.";
                break;
            case "Bomba sonica":
                golpe = 20;
                System.out.println("Bomba sónica infligió 20 de daño");
                str += "Bomba sónica infligió 20 de daño";
                break;
            case "Triataque":
                if (Math.random() * 100 < 20) {
                    if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                        aleatorio = Math.random() * 3;
                        switch ((int) aleatorio) {
                            case 0:
                                if (j2.tipo1.trim().compareTo("Electrico") != 0 && j2.tipo2.trim().compareTo("Electrico") != 0 && (Math.random() * 100) < 20) {
                                    j2.velocidad = j2.velocidad / 4;
                                    j2.estado = "Paralizado";
                                    System.out.println(j1.nombre.trim() + " dejó paralizado a " + j2.nombre.trim() + ".");
                                    str += j1.nombre.trim() + " dejó paralizado a " + j2.nombre.trim() + ".";
                                }
                                break;
                            case 1:
                                if (j2.tipo1.trim().compareTo("Fuego") != 0 && j2.tipo2.trim().compareTo("Fuego") != 0 && (Math.random() * 100) < 20) {
                                    System.out.println(j1.nombre.trim() + " quemó a " + j2.nombre.trim() + ".");
                                    str += j1.nombre.trim() + " quemó a " + j2.nombre.trim() + ".";
                                    j2.ataque -= 40;
                                    j2.estado = "Quemado";
                                }
                                break;
                            case 2:
                                if (j2.tipo1.trim().compareTo("Hielo") != 0 && j2.tipo2.trim().compareTo("Hielo") != 0 && (Math.random() * 100) < 20) {
                                    System.out.println(j1.nombre.trim() + " congeló a " + j2.nombre.trim() + ".");
                                    str += j1.nombre.trim() + " congeló a " + j2.nombre.trim() + ".";
                                    j2.estado = "Congelar";
                                }
                                break;
                        }
                    }
                }
                break;
            case "Ala acero":
            case "Garra metal":
                if (Math.random() * 100 < 10) {
                    if ((j1.ataque + 20) < ataquemax) {
                        j1.ataque += 20;
                    } else {
                        j1.ataque = ataquemax;
                    }
                }
                break;
            case "Carantona":
                if (Math.random() * 100 < 10) {
                    if (j2.ataque - 20 > ataquemin) {
                        j2.ataque -= 20;
                        str += "La potencia de " + j2.nombre.trim() + " bajó con " + ataqueelegido.trim() + ".";
                    } else {
                        j2.ataque = ataquemin;
                        str += "La potencia de " + j2.nombre.trim() + " no puede bajar más.";
                    }
                }
                break;
            case "Hiperrayo":
            case "Giga impacto":
            case "Anillo igneo":
            case "Hidrocanon":
            case "Planta feroz":
            case "Romperrocas":
            case "Distorsion":
                j1.ataqueelegido = "Descansar";
                break;
            case "Giro fuego":
            case "Tenaza":
            case "Atadura":
            case "Repeticion":
            case "Ciclón hojas":
                if (j2.atrapado == 0) {
                    j2.atrapado = 6;
                    System.out.println(j1.nombre.trim() + " atrapó a " + j2.nombre.trim());
                    str += j1.nombre.trim() + " atrapó a " + j2.nombre.trim();
                }
                break;
            case "Fuerza lunar":
                if (Math.random() * 100 < 30) {
                    calculo = j2.ataqueesp - 20;
                    if (calculo > ataqueespmin) {
                        j2.ataqueesp -= 20;
                    } else {
                        j2.ataqueesp = ataqueespmin;
                    }
                    System.out.println("El ataque especial de " + j2.nombre.trim() + " bajó con " + ataqueelegido.trim() + ".");
                    str += "El ataque especial de " + j2.nombre.trim() + " bajó con " + ataqueelegido.trim() + ".";
                }
                break;
            case "Danza llama":
                if (Math.random() * 100 < 50) {
                    calculo = j1.ataqueesp + 20;
                    if (calculo <= ataqueespmax) {
                        j1.ataqueesp += 20;
                    } else {
                        j1.ataqueesp = ataqueespmax;
                    }
                    str += j1.nombre + " subió su ataque especial.";
                }
                break;
            case "Sofoco":
            case "Psicoataque":
                calculo = j1.ataqueesp - 40;
                if (calculo > ataqueespmin) {
                    j1.ataqueesp -= 40;
                } else {
                    j1.ataqueesp = ataqueespmin;
                }
                System.out.println("El ataque especial de " + j1.nombre.trim() + " bajó mucho.");
                str += "El ataque especial de " + j1.nombre.trim() + " bajó mucho.";
                break;
            case "Estoicismo":
            case "Alarido":
            case "Llama embrujada":
                calculo = j2.ataqueesp - 20;
                if (calculo > ataqueespmin) {
                    j2.ataqueesp -= 20;
                } else {
                    j2.ataqueesp = ataqueespmin;
                }
                System.out.println("El ataque especial de " + j2.nombre.trim() + " bajó.");
                str += "El ataque especial de " + j2.nombre.trim() + " bajó.";
                break;
            case "Bola neblina":
                if (Math.random() * 100 < 50) {
                    calculo = j2.ataqueesp - 20;
                    if (calculo > ataqueespmin) {
                        j2.ataqueesp -= 20;
                    } else {
                        j2.ataqueesp = ataqueespmin;
                    }
                    System.out.println("El ataque especial de " + j2.nombre.trim() + " bajó.");
                    str += "El ataque especial de " + j2.nombre.trim() + " bajó.";
                }
                break;
            case "Rafaga":
                calculo = j1.ataqueesp + 60;
                if (calculo < ataqueespmax) {
                    j1.ataqueesp += 60;
                    str += "El ataque especial de " + j1.nombre + " subió muchísimo.";
                } else {
                    j1.ataqueesp = ataqueespmax;
                    str += "El ataque especial de " + j1.nombre + " no puede subir más.";
                }
                break;
            case "Electrocanon":
                if (j2.estado.compareTo("Normal") == 0 && j2.tipo1.compareTo("Electrico") != 0 && j2.tipo2.compareTo("Electrico") != 0 && !j2.protegeestado) {
                    j2.velocidad = j2.velocidad / 4;
                    j2.estado = "Paralizado";
                    System.out.println(j1.nombre.trim() + " dejó paralizado a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                    str += j1.nombre.trim() + " dejó paralizado a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                }
                break;
            case "Confusion":
            case "Psicorrayo":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                    if (Math.random() * 100 < 10) {
                        j2.estado = "Confuso";
                        System.out.println(j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                        str += j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                    }
                }
                break;
            case "Puno mareo":
                if (j2.estado.compareTo("Normal") == 0 && !j2.protegeestado) {
                    if (Math.random() * 100 < 20) {
                        j2.estado = "Confuso";
                        System.out.println(j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                        str += j1.nombre.trim() + " confundió a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                    }
                }
                break;
            //Potencia y defensa
            case "Tambor":
                if (j1.salud > (j1.saludmax / 2)) {
                    j1.salud = j1.salud / 2;
                    j1.ataque = ataquemax;
                    str += j1.nombre + " potenció su ataque al máximo";
                } else {
                    str += "A " + j1.nombre + " no le quedan suficientes fuerzas para usar Tambor";
                }

                break;
            case "Tormenta de diamantes":
                if (Math.random() * 100 < 50) {
                    if (this.defensa + 40 < defensamax) {
                        j1.defensa += 40;
                        System.out.println(j1.nombre.trim() + " aumentó su defensa.");
                        str += j1.nombre.trim() + " aumentó su defensa.";
                    } else {
                        j1.defensa = defensamax;
                        System.out.println(j1.nombre.trim() + " no puede aumentar más su defensa.");
                        str += j1.nombre.trim() + " no puede aumentar más su defensa.";
                    }
                }
                break;
            case "Cosquillas":
                if (j1.ataque - 20 > ataquemin) {
                    j1.ataque += 20;
                } else {
                    nopuedebajar = true;
                    j1.ataque = ataquemin;
                }
                if (j1.defensa - 20 > defensamin) {
                    j1.defensa -= 20;
                } else {
                    nopuedebajar = true;
                    j1.defensa = defensamin;
                }
                if (!nopuedebajar) {
                    str += "El ataque y la defensa de " + j2.nombre + " bajó.";
                } else {
                    str += "El ataque y la defensa de " + j2.nombre + " no puede bajar más";
                }
                break;
            case "Rompecoraza":
                if (j1.defensa - 20 > defensamin) {
                    j1.defensa -= 20;
                } else {
                    if (defensa != defensamin) {
                        j1.defensa = defensamin;
                    } else {
                        nopuedebajar = true;
                    }
                }
                if (j1.defensaesp - 20 > defensaespmin) {
                    j1.defensaesp -= 20;
                } else {
                    if (defensaesp != defensaespmin) {
                        j1.defensaesp = defensaespmin;
                    } else {
                        nopuedebajar = true;
                    }
                }
                if (j1.defensa != defensamin && j1.defensaesp != defensaespmin && !nopuedebajar) {
                    if (j1.ataque + 40 < ataquemax) {
                        j1.ataque += 40;
                    } else {
                        j1.ataque = ataquemax;
                    }
                    if (j1.ataqueesp + 40 < ataqueespmax) {
                        j1.ataqueesp += 40;
                    } else {
                        j1.ataqueesp = ataqueespmax;
                    }
                    if (j1.velocidad + 40 < velocidadmax) {
                        j1.velocidad += 40;
                    } else {
                        j1.velocidad = velocidadmax;
                    }
                    str += j1.nombre + " bajó su capacidad defensiva para aumentar su poder ofensivo y velocidad.";
                } else {
                    str += j1.nombre + " no puede perder más capacidad defensiva.";
                }
                break;

            case "Doble equipo":
            case "Reduccion":
                if (j1.evasion + 20 < evasionmax) {
                    j1.evasion += 20;
                } else {
                    j1.evasion = evasionmax;
                }
                str += "La evasión de " + j1.nombre + " aumentó.";
                break;
            case "Dulce aroma":
                if (j2.evasion - 20 > evasionmin) {
                    j2.evasion -= 20;
                } else {
                    j2.evasion = evasionmin;
                }
                str += "La evasión de " + j2.nombre + " disminuyó.";
                break;
            case "Grunido":
            case "Ojitos tiernos":
            case "Camaraderia":
                calculo = 20;
                if (j2.ataque - calculo > ataquemin) {
                    j2.ataque -= calculo;
                    System.out.println("El ataque de " + j2.nombre.trim() + " bajó.");
                    str += "El ataque de " + j2.nombre.trim() + " bajó.";
                } else {
                    j2.ataque = ataquemin;
                    System.out.println("El ataque de " + j2.nombre.trim() + " no puede bajar más.");
                    str += "El ataque de " + j2.nombre.trim() + " no puede bajar más.";
                }
                break;
            case "Puno meteoro":
                if (this.ataque + 20 < ataquemax) {
                    j1.ataque += 20;
                    System.out.print(j1.nombre.trim() + " aumentó su ataque.");
                    str += j1.nombre.trim() + " aumentó su ataque.";
                } else {
                    j1.ataque = ataquemax;
                    System.out.print(j1.nombre.trim() + " no puede aumentar más su ataque.");
                    str += j1.nombre.trim() + " no puede aumentar más su ataque.";
                }
                break;
            case "Aguijon letal":
                calculo = j2.salud - golpe;
                if (calculo <= 0) {
                    if (this.ataque + 40 < ataquemax) {
                        j1.ataque += 40;
                    } else {
                        j1.ataque = ataquemax;
                    }
                    str += "El ataque de " + j1.nombre + " subió al ver como moría " + j2.nombre + ".";
                }
                break;
            case "Latigo":
                calculo = 20;
                if (j2.defensa - calculo >= defensamin) {
                    j2.defensa -= calculo;
                    System.out.println("La defensa de " + j2.nombre.trim() + " bajó.");
                    str += "La defensa de " + j2.nombre.trim() + " bajó.";
                } else {
                    j2.defensa = defensamin;
                    System.out.println("La defensa de " + j2.nombre.trim() + " no puede bajar más.");
                    str += "La defensa de " + j2.nombre.trim() + " no puede bajar más.";
                }
                break;
            case "Chirrido":
                calculo = 40;
                if (j2.defensa - calculo >= defensamin) {
                    j2.defensa -= calculo;
                    System.out.println("La defensa de " + j2.nombre.trim() + " bajó.");
                    str += "La defensa de " + j2.nombre.trim() + " bajó.";
                } else {
                    j2.defensa = defensamin;
                    System.out.println("La defensa de " + j2.nombre.trim() + " no puede bajar más.");
                    str += "La defensa de " + j2.nombre.trim() + " no puede bajar más.";
                }
                break;
            case "Poder pasado":
            case "Viento plata":
            case "Viento aciago":
                if (Math.random() * 100 < 10) {
                    String st = "";
                    if (this.ataque + 20 < ataquemax) {
                        j1.ataque += 20;
                        System.out.print(j1.nombre.trim() + " aumentó su ataque y");
                        st += j1.nombre.trim() + " aumentó su ataque y";
                    } else {
                        j1.ataque = ataquemax;
                        System.out.print(j1.nombre.trim() + " no puede aumentar más su ataque y");
                        st += j1.nombre.trim() + " no puede aumentar más su ataque y";
                    }
                    if (this.defensa + 40 < defensamax) {
                        j1.defensa += 40;
                        System.out.println(j1.nombre.trim() + " aumentó su defensa.");
                        st += j1.nombre.trim() + " aumentó su defensa.";
                    } else {
                        j1.defensa = defensamax;
                        System.out.println(j1.nombre.trim() + " no puede aumentar más su defensa.");
                        st += j1.nombre.trim() + " no puede aumentar más su defensa.";
                    }
                    str += st;
                }
                break;
            case "Enrosque":
                if (this.ataque + 20 < ataquemax) {
                    j1.ataque += 20;
                    System.out.print(j1.nombre.trim() + " aumentó su ataque y");
                    str += j1.nombre.trim() + " aumentó su ataque y";
                } else {
                    j1.ataque = ataquemax;
                    System.out.print(j1.nombre.trim() + " no puede aumentar más su ataque y");
                    str += j1.nombre.trim() + " no puede aumentar más su ataque y";
                }
                if (this.defensa + 40 < defensamax) {
                    j1.defensa += 40;
                    System.out.println(" aumentó su defensa.");
                    str += " aumentó su defensa.";
                } else {
                    j1.defensa = defensamax;
                    System.out.println(" no puede aumentar más su defensa.");
                    str += " no puede aumentar más su defensa.";
                }
                break;
            case "Fuerza bruta":
                if (Math.random() * 100 < 10) {
                    if (this.ataque - 40 > ataquemin) {
                        j1.ataque -= 20;
                        System.out.print(j1.nombre.trim() + " perdió energía y");
                        str += j1.nombre.trim() + " perdió energía y";
                    } else {
                        j1.ataque = ataquemin;
                        System.out.print(j1.nombre.trim() + " no puede perder más energía y");
                        str += j1.nombre.trim() + " no puede perder más energía y";
                    }
                    if (j1.defensa - 40 > defensamin) {
                        j1.defensa -= 40;
                        System.out.print(j1.nombre.trim() + " perdió capacidad defensiva.");
                        str += j1.nombre.trim() + " perdió capacidad defensiva.";
                    } else {
                        j1.defensa = defensamin;
                        System.out.print(j1.nombre.trim() + " no puede perder más capacidad defensiva.");
                        str += j1.nombre.trim() + " no puede perder más capacidad defensiva.";
                    }
                }
                break;
            case "Resplandor":
                if (Math.random() * 100 < 50) {
                    if (j2.defensaesp - 20 > defensaespmin) {
                        j2.defensaesp -= 20;
                        str += "La defensa especial de " + j2.nombre + " bajó";
                    } else {
                        j2.defensaesp = defensaespmin;
                    }
                }
                break;
            case "A bocajarro":
                str += j1.nombre + " bajó la defensa y la defensa especial de " + j2.nombre;
                if (j2.defensa - 20 > defensamin) {
                    j2.defensa -= 20;
                } else {
                    j2.defensa = defensamin;
                }
                if (j2.defensaesp - 20 > defensaespmin) {
                    j2.defensaesp -= 20;
                } else {
                    j2.defensaesp = defensaespmin;
                }
                break;
            case "Paz mental":
                if (j1.ataqueesp + 20 < ataqueespmax) {
                    j1.ataqueesp += 20;
                } else {
                    j1.ataqueesp = ataqueespmax;
                }
                if (j1.defensaesp + 20 < defensaespmax) {
                    j1.defensaesp += 20;
                } else {
                    j1.defensaesp = defensaespmax;
                }
                str += j1.nombre + " subió su ataque especial y defensa especial.";
                break;
            case "Danza aleteo":
                if (j1.ataqueesp + 20 < ataqueespmax) {
                    j1.ataqueesp += 20;
                } else {
                    j1.ataqueesp = ataqueespmax;
                }
                if (j1.defensaesp + 20 < defensaespmax) {
                    j1.defensaesp += 20;
                } else {
                    j1.defensaesp = defensaespmax;
                }
                if (j1.velocidad + 20 < velocidadmax) {
                    j1.velocidad += 20;
                } else {
                    j1.velocidad = velocidadmax;
                }
                str += j1.nombre + " bailó grácilmente y aumentó su ataque especial, defensa especial y velocidad.";
                break;
            case "Foco resplador":
                if (Math.random() * 100 < 10) {
                    str += j1.nombre + " bajó la defensa especial de " + j2.nombre + ".";
                    if (j2.defensaesp - 20 > defensaespmin) {
                        j2.defensaesp -= 20;
                    } else {
                        j2.defensaesp = defensaespmin;
                    }
                }
                break;
            case "Llanto falso":
                str += j1.nombre + " comenzó a llorar amargamente y bajó mucho la defensa especial de " + j2.nombre + ".";
                if (j2.defensaesp - 40 > defensaespmin) {
                    j2.defensaesp -= 40;
                } else {
                    j2.defensaesp = defensaespmin;
                }
                break;
            case "Eco metalico":
                str += j1.nombre + " bajó la defensa especial de " + j2.nombre;
                if (j2.defensaesp - 40 > defensaespmin) {
                    j2.defensaesp -= 40;
                } else {
                    j2.defensaesp = 0;
                }
                break;
            case "Fogonazo":
                if (Math.random() * 100 < 40) {
                    str += "La defensa especial de " + j2.nombre + " bajó mucho.";
                    if (j2.defensaesp - 40 > defensaespmin) {
                        j2.defensaesp -= 40;
                    } else {
                        j2.defensaesp = 0;
                    }
                }
                break;
            case "Danza pluma":
                if (j2.ataque - 40 > ataquemin) {
                    j2.ataque -= 40;
                } else {
                    j2.ataque = ataquemin;
                }
                str += "El ataque de " + j2.nombre + " bajó mucho.";
                break;
            case "Legado":
                j1.salud = 0;
                if (j2.ataque - 40 > ataquemin) {
                    j2.ataque -= 40;
                } else {
                    j2.ataque = ataquemin;
                }
                System.out.println(j1.nombre.trim() + " se suicidó y provocó que el ataque de " + j2.nombre.trim() + " bajase mucho.");
                str += j1.nombre.trim() + " se suicidó y provocó que el ataque de " + j2.nombre.trim() + " bajase mucho.";
                break;
            case "Mismodestino":
                j2.mismodestino = true;
                System.out.println(j1.nombre.trim() + " intenta llevarse a " + j2.nombre.trim() + ".");
                str += j1.nombre + " intenta llevarse a " + j2.nombre + ".";
                break;
            case "Psico-cambio":
                if (j1.estado.compareTo("Normal") != 0 && !j2.protegeestado) {
                    j2.estado = j1.estado;
                    j1.estado = "Normal";
                    System.out.println(j1.nombre.trim() + " traspasó su estado a " + j2.nombre.trim() + ".");
                    str += j1.nombre.trim() + " traspasó su estado a " + j2.nombre.trim() + ".";
                } else {
                    if (j1.estado.compareTo("Normal") == 0) {
                        str += j1.nombre + " se encuentra perfectamente y no puede traspasar su estado.";
                    } else {
                        str += j1.nombre + " no pudo traspasar su estado a " + j2.nombre + " porque está protegido.";
                    }
                }
                break;
            case "Isofuerza":
                calculo = (j1.ataque + j2.ataque) / 2;
                j1.ataque = calculo;
                j2.ataque = calculo;
                calculo = (j1.ataqueesp + j2.ataqueesp) / 2;
                j1.ataqueesp = calculo;
                j2.ataqueesp = calculo;
                str += j1.nombre + " equilibró las fuerzas contra " + j2.nombre;
                break;
            //Retroceso    
            case "Mordisco":
            case "Paranormal":
                if (Math.random() * 100 < 10) {
                    j2.noatacar = true;
                    j2.retroceder = true;
                }
                break;
            case "Brazo pincho":
                if (Math.random() * 100 < 30) {
                    j2.noatacar = true;
                    j2.retroceder = true;
                }
                break;
            case "Pulso Umbrio":
                if (Math.random() * 100 < 20) {
                    j2.noatacar = true;
                    j2.retroceder = true;
                }
                break;
            default:
                break;
        }
        if (!j2.protegeestado) {
            switch (tipoataque) {
                case "Veneno":
                    if (ataqueelegido.trim().compareTo("Bomba acida") != 0 && ataqueelegido.trim().compareTo("Acido") != 0 && ataqueelegido.trim().compareTo("Carga toxica") != 0 && ataqueelegido.trim().compareTo("Eructo") != 0) {
                        if (j2.estado.compareTo("Normal") == 0 && j2.tipo1.trim().compareTo("Veneno") != 0 && j2.tipo2.trim().compareTo("Veneno") != 0 && j2.tipo1.trim().compareTo("Acero") != 0 && j2.tipo2.trim().compareTo("Acero") != 0 && (Math.random() * 100) < 10) {
                            System.out.println(j1.nombre.trim() + " envenenó a " + j2.nombre.trim() + ".");
                            str += j1.nombre.trim() + " envenenó a " + j2.nombre.trim() + ".";
                            j2.estado = "Envenenado";
                        }
                    }
                    break;
                case "Fuego":
                    if (ataqueelegido.trim().compareTo("Anillo igneo") != 0 && ataqueelegido.trim().compareTo("Sofoco") != 0 && ataqueelegido.trim().compareTo("Nitrocarga") != 0 && ataqueelegido.compareTo("Patada ignea") != 0) {
                        if (ataqueelegido.compareTo("Llamarada") != 0 || ataqueelegido.compareTo("Humareda") != 0) {
                            if (j2.estado.compareTo("Normal") == 0 && j2.tipo1.trim().compareTo("Fuego") != 0 && j2.tipo2.trim().compareTo("Fuego") != 0 && (Math.random() * 100) < 10) {
                                System.out.println(j1.nombre.trim() + " quemó a " + j2.nombre.trim() + ".");
                                str += j1.nombre.trim() + " quemó a " + j2.nombre.trim() + ".";
                                j2.ataque -= 40;
                                j2.estado = "Quemado";
                            }
                        } else {
                            if (j2.estado.compareTo("Normal") == 0 && j2.tipo1.trim().compareTo("Fuego") != 0 && j2.tipo2.trim().compareTo("Fuego") != 0 && (Math.random() * 100) < 30) {
                                System.out.println(j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                                str += j1.nombre.trim() + " quemó a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                                j2.ataque -= 40;
                                j2.estado = "Quemado";
                            }
                        }
                    }
                    if (ataqueelegido.trim().compareTo("Patada ignea") != 0) {
                        if (j2.estado.compareTo("Hielo") == 0) {
                            j2.estado = "Normal";
                            System.out.println(j2.nombre.trim() + " se descongeló con " + ataqueelegido.trim() + ".");
                            str += j2.nombre.trim() + " se descongeló con " + ataqueelegido.trim() + ".";
                        }
                    }
                    break;
                case "Electrico":
                    if (ataqueelegido.trim().compareTo("Rayo carga") != 0 && ataqueelegido.trim().compareTo("Chispazo") != 0 && ataqueelegido.trim().compareTo("Bola voltio") != 0 && ataqueelegido.trim().compareTo("Chispa") != 0 && ataqueelegido.trim().compareTo("Onda voltio") != 0) {
                        if (j2.tipo1.compareTo("Tierra") != 0 && j2.tipo2.compareTo("Tierra") != 0) {
                            if (j2.estado.compareTo("Normal") == 0 && j2.tipo1.trim().compareTo("Electrico") != 0 && j2.tipo2.trim().compareTo("Electrico") != 0 && (Math.random() * 100) < 10) {
                                j2.velocidad = j2.velocidad / 4;
                                j2.estado = "Paralizado";
                                System.out.println(j1.nombre.trim() + " dejó paralizado a " + j2.nombre.trim() + ".");
                                str += j1.nombre.trim() + " dejó paralizado a " + j2.nombre.trim() + ".";
                            }
                        }
                    }
                    break;

                case "Hielo":
                    if (ataqueelegido.trim().compareTo("Rayo aurora") != 0 && ataqueelegido.trim().compareTo("Viento hielo") != 0 && ataqueelegido.trim().compareTo("Alud") != 0 && ataqueelegido.trim().compareTo("Canto helado") != 0 && ataqueelegido.trim().compareTo("Frio polar") != 0) {
                        if (j2.estado.compareTo("Normal") == 0 && j2.tipo1.trim().compareTo("Hielo") != 0 && j2.tipo2.trim().compareTo("Hielo") != 0 && (Math.random() * 100) < 10) {
                            System.out.println(j1.nombre.trim() + " dejó congelado a " + j2.nombre.trim() + ".");
                            str += j1.nombre.trim() + " dejó congelado a " + j2.nombre.trim() + ".";
                            j2.estado = "Congelado";
                            j2.contador = 10;
                        }
                    } else {
                        if (Math.random() * 100 < 10) {
                            j2.ataque -= 10;
                            System.out.println(j1.nombre.trim() + " bajó el ataque a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".");
                            str += j1.nombre.trim() + " bajó el ataque a " + j2.nombre.trim() + " con " + ataqueelegido.trim() + ".";
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return str;
    }

    String EfectoDescansar(Pokemon j1, String ataqueelegido) {
        String str = "";
        if (j1.noatacar == false) {
            boolean entra = false;
            switch (ataqueelegido.trim()) {
                case "Rayo solar":
                    System.out.println(j1.nombre.trim() + " está preparándose.");
                    str += j1.nombre.trim() + " está preparándose.";
                    entra = true;
                    break;
                case "Ataque aereo":
                    System.out.println(j1.nombre.trim() + " está concentrando energía vital.");
                    str += j1.nombre.trim() + " está concentrando energía vital.";
                    entra = true;
                    break;
                case "Vuelo":
                    System.out.println(j1.nombre.trim() + " voló alto hacia el cielo.");
                    str += j1.nombre.trim() + " voló alto hacia el cielo.";
                    j1.volando = true;
                    j1.alalcance = false;
                    entra = true;
                    break;
                case "Excavar":
                    System.out.println(j1.nombre.trim() + " se enterró bajo tierra.");
                    str += j1.nombre.trim() + " se enterró bajo tierra.";
                    j1.enterrado = true;
                    j1.alalcance = false;
                    entra = true;
                    break;
                case "Bote":
                    System.out.println(j1.nombre.trim() + " saltó muy alto.");
                    str += j1.nombre.trim() + " saltó muy alto.";
                    j1.alalcance = false;
                    entra = true;
                    break;
                case "Buceo":
                    System.out.println(j1.nombre.trim() + " se sumergió.");
                    str += j1.nombre.trim() + " se sumergió.";
                    j1.sumergido = true;
                    j1.alalcance = false;
                    entra = true;
                    break;
                case "Golpe fantasma":
                    System.out.println(j1.nombre.trim() + " desapareció entre las tinieblas.");
                    str += j1.nombre.trim() + " desapareció entre las tinieblas.";
                    j1.alalcance = false;
                    entra = true;
                    break;
                case "Deseo oculto":
                    System.out.println(j1.nombre.trim() + " está preparándose.");
                    str += j1.nombre.trim() + " está preparándose.";
                    entra = true;
                    break;
                default:
                    break;
            }
            if (entra) {
                j1.pasar = 1;
                j1.turnosincapacitado = 2;
                if (j1.equijugador) {
                    System.out.println("ENTRA");
                    activarsolounboton = true;
                }
            }
        }
        return str;
    }

    static public String ComprobarEstado(Pokemon j1, Pokemon j2, double dañojugador) {
        String str = "";
        if (j1.estado.compareTo("Normal") != 0 || j1.drenadoras || j1.atrapado > 0) {
            str += j1.EfectoEstados(j1, j2, dañojugador);
        }
        return str;
    }

    public String EfectoEstados(Pokemon j1, Pokemon j2, double daño) {
        String str = "";
        double restasalud;
        if (j1.drenadoras) {
            restasalud = j1.saludmax * porcentajevidaefectos;
            j1.salud -= restasalud;
            if (j2.salud + restasalud <= j2.saludmax) {
                j2.salud += restasalud;
            }
            if (j1.salud - restasalud > 0) {
                j1.salud -= restasalud;
                System.out.println("Las drenadoras inflijen " + (int) restasalud + " a " + j1.nombre.trim() + " y sanan " + (int) restasalud + " a " + j2.nombre.trim() + ".");
                str += "Las drenadoras inflijen " + (int) restasalud + " a " + j1.nombre.trim() + " y sanan " + (int) restasalud + " a " + j2.nombre.trim() + ".";
            } else {
                j1.salud = 0;
                j1.muertedirecta = false;
                System.out.println(j1.nombre.trim() + " murió a consecuencia del parasitamiento de las drenadoras.");
                str += j1.nombre.trim() + " murió a consecuencia del parasitamiento de las drenadoras.";
            }
        }
        if (j1.atrapado > 0) {
            restasalud = j1.saludmax * porcentajevidaefectos;
            j1.atrapado -= ((int) (Math.random() * 3) + 1);
            if (j1.atrapado <= 0) {
                j1.atrapado = 0;
                System.out.println(j1.nombre.trim() + " escapó del atrapamiento.");
                str += j1.nombre.trim() + " escapó del atrapamiento.";
            } else {
                if (j1.salud - restasalud > 0) {
                    j1.salud -= restasalud;
                    System.out.println(j1.nombre.trim() + " está completamente atrapado, recibe " + (int) restasalud + " de daño.");
                    str += j1.nombre.trim() + " está completamente atrapado, recibe " + (int) restasalud + " de daño.";
                } else {
                    j1.salud = 0;
                    j1.muertedirecta = false;
                    System.out.println(j1.nombre.trim() + " murió a consecuencia del brutal atrapamiento.");
                    str += j1.nombre.trim() + " murió a consecuencia del brutal atrapamiento.";
                }
            }
        }
        switch (j1.estado.trim()) {

            case "Envenenado":
                restasalud = j1.saludmax * porcentajevidaefectos;
                if ((j1.salud - restasalud) < 0) {
                    j1.salud = 0;
                    j1.muertedirecta = false;
                    System.out.println("El veneno supuró y " + j1.nombre.trim() + " no pudo aguantar más.");
                    str += "El veneno supuró y " + j1.nombre.trim() + " no pudo aguantar más.";
                } else {
                    j1.salud -= restasalud;
                    System.out.println("El veneno resta " + (int) restasalud + " salud a " + j1.nombre.trim() + ".");
                    str += "El veneno resta " + (int) restasalud + " salud a " + j1.nombre.trim() + ".";
                }
                break;
            case "Quemado":
                restasalud = j1.saludmax * porcentajevidaefectos;
                if ((j1.salud - restasalud) < 0) {
                    j1.salud = 0;
                    j1.muertedirecta = false;
                    System.out.println(j1.nombre.trim() + " no resistió más la brutal quemadura que arrastraba.");
                    str += j1.nombre.trim() + " no resistió más la brutal quemadura que arrastraba.";
                } else {
                    j1.salud -= restasalud;
                    System.out.println(j1.nombre.trim() + " se resiente por la quemadura y le resta " + (int) restasalud + " de salud.");
                    str += j1.nombre.trim() + " se resiente por la quemadura y le resta " + (int) restasalud + " de salud.";
                }
                break;
            case "Maldito":
                restasalud = j1.saludmax * 0.15;
                if ((j1.salud - restasalud) < 0) {
                    j1.salud = 0;
                    j1.muertedirecta = false;
                    System.out.println("La maldición consumió a " + j1.nombre.trim() + ".");
                    str += "La maldición consumió a " + j1.nombre.trim() + ".";
                } else {
                    j1.salud -= restasalud;
                    System.out.println("La maldición de " + j1.nombre.trim() + " persiste y realiza " + (int) restasalud + " de daño.");
                    str += "La maldición de " + j1.nombre.trim() + " persiste y realiza " + (int) restasalud + " de daño.";
                }
                break;
            case "Paralizado":
                if (Math.random() * 100 < 25) {
                    j1.noatacar = true;
                    System.out.println(j1.nombre.trim() + " está completamente paralizado.");
                    str += j1.nombre.trim() + " está completamente paralizado.";
                }
                break;
            case "Confuso":
                j1.contador -= ((Math.random() * 3) + 1);
                if (j1.contador <= 0) {
                    j1.contador = 0;
                    System.out.println(j1.nombre.trim() + " salío de la confusión.");
                    str += j1.nombre.trim() + " salío de la confusión.";
                    j1.estado = "Normal";
                } else {
                    if (Math.random() * 100 < 40) {
                        restasalud = daño * 0.25;
                        if ((j1.salud - restasalud) < 0) {
                            j1.salud = 0;
                            j1.muertedirecta = false;
                            System.out.println(j1.nombre.trim() + " está tan confuso que terminó suicidándose.");
                            str += j1.nombre.trim() + " está tan confuso que terminó suicidándose.";
                        } else {
                            System.out.println(j1.nombre.trim() + " está tan confuso que se hirió a si mismo infligiéndose " + (int) restasalud + " de daño.");
                            str += j1.nombre.trim() + " está tan confuso que se hirió a si mismo infligiéndose " + (int) restasalud + " de daño.";
                            j1.noatacar = true;
                        }
                    }
                }
                break;

            case "Congelado":
            case "Dormido":
                j1.contador -= ((Math.random() * 2) + 1);
                if (j1.contador <= 0) {
                    j1.contador = 0;
                    System.out.println(j1.nombre.trim() + " salió del estado " + j1.estado.trim() + ".");
                    str += j1.nombre.trim() + " salió del estado " + j1.estado.trim() + ".";
                    j1.estado = "Normal";
                } else {
                    if (ataqueelegido.trim().compareTo("Congelado") == 0) {
                        System.out.println(j1.nombre.trim() + " sigue " + j1.estado.trim() + ".");
                        str += j1.nombre.trim() + " sigue " + j1.estado.trim() + ".";
                    } else {
                        System.out.println(j1.nombre.trim() + " sigue profundamente " + j1.estado.trim() + ".");
                        str += j1.nombre.trim() + " sigue profundamente " + j1.estado.trim() + ".";
                    }
                    j1.noatacar = true;
                }
                break;
            default:
                break;
        }
        return str;
    }

    public static String EleccionNaturaleza() {
        String naturaleza = "";
        int aleatorio;
        aleatorio = (int) (Math.random() * 16);
        switch (aleatorio) {
            case 0:
                naturaleza = "Acero";
                break;
            case 1:
                naturaleza = "Agua";
                break;
            case 2:
                naturaleza = "Bicho";
                break;
            case 3:
                naturaleza = "Dragon";
                break;
            case 4:
                naturaleza = "Electrico";
                break;
            case 5:
                naturaleza = "Fantasma";
                break;
            case 6:
                naturaleza = "Fuego";
                break;
            case 7:
                naturaleza = "Hielo";
                break;
            case 8:
                naturaleza = "Lucha";
                break;
            case 9:
                naturaleza = "Planta";
                break;
            case 10:
                naturaleza = "Psiquico";
                break;
            case 11:
                naturaleza = "Roca";
                break;
            case 12:
                naturaleza = "Siniestro";
                break;
            case 13:
                naturaleza = "Tierra";
                break;
            case 14:
                naturaleza = "Veneno";
                break;
            case 15:
                naturaleza = "Volador";
                break;
        }
        return naturaleza;
    }

    public void SubirEstadisticasShiny() {
        double subirstats = 0.1;
        this.salud += salud * subirstats;
        this.saludmax += saludmax * subirstats;
        this.velocidad += velocidad * subirstats;
        this.ataque += ataque * subirstats;
        this.defensa += defensa * subirstats;
        this.ataqueesp += ataqueesp * subirstats;
        this.defensaesp += defensaesp * subirstats;
        this.shiny = true;
    }

    public void BajarEstadisticasShiny() {
        if (this.shiny) {
            double bajarstats = 0.1;
            this.salud -= salud * bajarstats;
            this.saludmax -= saludmax * bajarstats;
            this.velocidad -= velocidad * bajarstats;
            this.ataque -= ataque * bajarstats;
            this.defensa -= defensa * bajarstats;
            this.ataqueesp -= ataqueesp * bajarstats;
            this.defensaesp -= defensaesp * bajarstats;
        }
    }

    public static String NombreEntrenador(int n) {
        String str = "";
        if (PokemonGUI.kanto) {
            switch (n) {
                case 1: //Brock
                    str = "Brock";
                    break;
                case 2: //Misty
                    str = "Misty";
                    break;
                case 3: //LT. Surge
                    str = "LT. Surge";
                    break;
                case 4: //Erika
                    str = "Erika";
                    break;
                case 5: //Koga
                    str = "Koga";
                    break;
                case 6: //Sabrina
                    str = "Sabrina";
                    break;
                case 7: //Blaine
                    str = "Blaine";
                    break;
                case 8: //Giovanni
                    str = "Giovanni";
                    break;
                case 9: //Lorelei
                    str = "Lorelei";
                    break;
                case 10: //Bruno
                    str = "Bruno";
                    break;
                case 11: //Agatha
                    str = "Agatha";
                    break;
                case 12: //Lance
                    str = "Lance";
                    break;
                case 13: //Gary
                    str = "Gary";
                    break;
                default:
                    break;
            }
        } else if (PokemonGUI.johto) {
            switch (n) {
                case 1: //Pegaso
                    str = "Pegaso";
                    break;
                case 2: //Antón
                    str = "Antón";
                    break;
                case 3: //Blanca
                    str = "Blanca";
                    break;
                case 4: //Morti
                    str = "Morti";
                    break;
                case 5: //Aníbal
                    str = "Aníbal";
                    break;
                case 6: //Yasmina
                    str = "Yasmina";
                    break;
                case 7: //Fredo
                    str = "Fredo";
                    break;
                case 8: //Débora
                    str = "Débora";
                    break;
                case 9: //Mento
                    str = "Mento";
                    break;
                case 10: //Koga
                    str = "Koga";
                    break;
                case 11: //Bruno
                    str = "Bruno";
                    break;
                case 12: //Karen
                    str = "Karen";
                    break;
                case 13: //Lance
                    str = "Lance";
                    break;
                default:
                    break;
            }
        } else if (PokemonGUI.hoenn) {
            switch (n) {
                case 1: //Petra
                    str = "Petra";
                    break;
                case 2: //Marcial
                    str = "Marcial";
                    break;
                case 3: //Erico
                    str = "Erico";
                    break;
                case 4: //Candela
                    str = "Candela";
                    break;
                case 5: //Norman
                    str = "Norman";
                    break;
                case 6: //Alana
                    str = "Alana";
                    break;
                case 7: //Vito y Leti
                    str = "Vito y Leti";
                    break;
                case 8: //Plubio
                    str = "Plubio";
                    break;
                case 9: //Sixto
                    str = "Sixto";
                    break;
                case 10: //Fátima
                    str = "Fátima";
                    break;
                case 11: //Nivea
                    str = "Nivea";
                    break;
                case 12: //Dracón
                    str = "Dracón";
                    break;
                case 13: //Máximo Peñas
                    str = "Máximo Peñas";
                    break;
                default:
                    break;
            }
        } else if (PokemonGUI.sinnoh) {
            switch (n) {
                case 1: //Roco
                    str = "Roco";
                    break;
                case 2: //Gardenia
                    str = "Gardenia";
                    break;
                case 3: //Brega
                    str = "Brega";
                    break;
                case 4: //Mananti
                    str = "Mananti";
                    break;
                case 5: //Fantina
                    str = "Fantina";
                    break;
                case 6: //Aceron
                    str = "Aceron";
                    break;
                case 7: //Inverna
                    str = "Inverna";
                    break;
                case 8: //Lectro
                    str = "Lectro";
                    break;
                case 9: //Alecran
                    str = "Alecran";
                    break;
                case 10: //Gaia
                    str = "Gaia";
                    break;
                case 11: //Fausto
                    str = "Fausto";
                    break;
                case 12: //Delos
                    str = "Delos";
                    break;
                case 13: //Cintia
                    str = "Cintia";
                    break;
                default:
                    break;
            }
        } else {
            switch (n) {
                case 1: //Millo, Maíz y Zeo
                    str = "Millo, Maíz y Zeo";
                    break;
                case 2: //Aloe
                    str = "Aloe";
                    break;
                case 3: //Camus
                    str = "Camus";
                    break;
                case 4: //Camila
                    str = "Camila";
                    break;
                case 5: //Yakon
                    str = "Yakon";
                    break;
                case 6: //Gerania
                    str = "Gerania";
                    break;
                case 7: //Junco
                    str = "Junco";
                    break;
                case 8: //Lirio e Iris
                    str = "Lirio e Iris";
                    break;
                case 9: //Anis
                    str = "Anis";
                    break;
                case 10: //Aza
                    str = "Aza";
                    break;
                case 11: //Catleya
                    str = "Catleya";
                    break;
                case 12: //Lotto
                    str = "Lotto";
                    break;
                case 13: //Mirto
                    str = "Mirto";
                    break;
                default:
                    break;
            }
        }
        return str;
    }

    public static void PokemonLiga(int n) {
        int[] ListaPokemon = new int[6];
        System.out.println(n);
        if (PokemonGUI.kanto) {
            switch (n) {
                case 1: //Brock
                    ListaPokemon[0] = 95;
                    ListaPokemon[1] = 141;
                    ListaPokemon[2] = 139;
                    ListaPokemon[3] = 76;
                    ListaPokemon[4] = 248;
                    ListaPokemon[5] = 208;
                    break;
                case 2: //Misty
                    ListaPokemon[0] = 121;
                    ListaPokemon[1] = 55;
                    ListaPokemon[2] = 195;
                    ListaPokemon[3] = 117;
                    ListaPokemon[4] = 350;
                    ListaPokemon[5] = 119;
                    break;
                case 3: //LT. Surge
                    ListaPokemon[0] = 26;
                    ListaPokemon[1] = 101;
                    ListaPokemon[2] = 82;
                    ListaPokemon[3] = 125;
                    ListaPokemon[4] = 310;
                    ListaPokemon[5] = 181;
                    break;
                case 4: //Erika
                    ListaPokemon[0] = 45;
                    ListaPokemon[1] = 71;
                    ListaPokemon[2] = 114;
                    ListaPokemon[3] = 182;
                    ListaPokemon[4] = 189;
                    ListaPokemon[5] = 103;
                    break;
                case 5: //Koga
                    ListaPokemon[0] = 110;
                    ListaPokemon[1] = 89;
                    ListaPokemon[2] = 49;
                    ListaPokemon[3] = 168;
                    ListaPokemon[4] = 169;
                    ListaPokemon[5] = 317;
                    break;
                case 6: //Sabrina
                    ListaPokemon[0] = 65;
                    ListaPokemon[1] = 122;
                    ListaPokemon[2] = 196;
                    ListaPokemon[3] = 202;
                    ListaPokemon[4] = 97;
                    ListaPokemon[5] = 376;
                    break;
                case 7: //Blaine
                    ListaPokemon[0] = 78;
                    ListaPokemon[1] = 59;
                    ListaPokemon[2] = 38;
                    ListaPokemon[3] = 324;
                    ListaPokemon[4] = 219;
                    ListaPokemon[5] = 126;
                    break;
                case 8: //Giovanni
                    ListaPokemon[0] = 31;
                    ListaPokemon[1] = 115;
                    ListaPokemon[2] = 34;
                    ListaPokemon[3] = 112;
                    ListaPokemon[4] = 53;
                    ListaPokemon[5] = 105;
                    break;
                case 9: //Lorelei
                    ListaPokemon[0] = 131;
                    ListaPokemon[1] = 124;
                    ListaPokemon[2] = 87;
                    ListaPokemon[3] = 91;
                    ListaPokemon[4] = 221;
                    ListaPokemon[5] = 144;
                    break;
                case 10: //Bruno
                    ListaPokemon[0] = 107;
                    ListaPokemon[1] = 106;
                    ListaPokemon[2] = 68;
                    ListaPokemon[3] = 237;
                    ListaPokemon[4] = 208;
                    ListaPokemon[5] = 95;
                    break;
                case 11: //Agatha
                    ListaPokemon[0] = 94;
                    ListaPokemon[1] = 24;
                    ListaPokemon[2] = 169;
                    ListaPokemon[3] = 200;
                    ListaPokemon[4] = 65;
                    ListaPokemon[5] = 93;
                    break;
                case 12: //Lance
                    ListaPokemon[0] = 149;
                    ListaPokemon[1] = 130;
                    ListaPokemon[2] = 142;
                    ListaPokemon[3] = 230;
                    ListaPokemon[4] = 373;
                    ListaPokemon[5] = 334;
                    break;
                case 13: //Gary
                    ListaPokemon[0] = 18;
                    ListaPokemon[1] = 130;
                    ListaPokemon[2] = 65;
                    ListaPokemon[3] = 103;
                    ListaPokemon[4] = 112;
                    ListaPokemon[5] = 6;
                    break;
                default:
                    break;
            }
            labelfotoentrenadorIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Lider Kanto/" + n + ".png")).getImage()).getScaledInstance(45, 30, java.awt.Image.SCALE_SMOOTH)));
        } else if (PokemonGUI.johto) {
            switch (n) {
                case 1: //Pegaso
                    ListaPokemon[0] = 164;
                    ListaPokemon[1] = 277;
                    ListaPokemon[2] = 18;
                    ListaPokemon[3] = 279;
                    ListaPokemon[4] = 22;
                    ListaPokemon[5] = 85;
                    break;
                case 2: //Antón
                    ListaPokemon[0] = 123;
                    ListaPokemon[1] = 12;
                    ListaPokemon[2] = 15;
                    ListaPokemon[3] = 127;
                    ListaPokemon[4] = 214;
                    ListaPokemon[5] = 212;
                    break;
                case 3: //Blanca
                    ListaPokemon[0] = 241;
                    ListaPokemon[1] = 36;
                    ListaPokemon[2] = 234;
                    ListaPokemon[3] = 242;
                    ListaPokemon[4] = 301;
                    ListaPokemon[5] = 40;
                    break;
                case 4: //Morti
                    ListaPokemon[0] = 94;
                    ListaPokemon[1] = 302;
                    ListaPokemon[2] = 354;
                    ListaPokemon[3] = 203;
                    ListaPokemon[4] = 105;
                    ListaPokemon[5] = 185;
                    break;
                case 5: //Aníbal
                    ListaPokemon[0] = 57;
                    ListaPokemon[1] = 62;
                    ListaPokemon[2] = 107;
                    ListaPokemon[3] = 106;
                    ListaPokemon[4] = 308;
                    ListaPokemon[5] = 286;
                    break;
                case 6: //Yasmina
                    ListaPokemon[0] = 208;
                    ListaPokemon[1] = 82;
                    ListaPokemon[2] = 227;
                    ListaPokemon[3] = 212;
                    ListaPokemon[4] = 376;
                    ListaPokemon[5] = 303;
                    break;
                case 7: //Fredo
                    ListaPokemon[0] = 87;
                    ListaPokemon[1] = 221;
                    ListaPokemon[2] = 124;
                    ListaPokemon[3] = 362;
                    ListaPokemon[4] = 365;
                    ListaPokemon[5] = 131;
                    break;
                case 8: //Débora
                    ListaPokemon[0] = 230;
                    ListaPokemon[1] = 130;
                    ListaPokemon[2] = 142;
                    ListaPokemon[3] = 149;
                    ListaPokemon[4] = 373;
                    ListaPokemon[5] = 334;
                    break;
                case 9: //Mento
                    ListaPokemon[0] = 178;
                    ListaPokemon[1] = 103;
                    ListaPokemon[2] = 282;
                    ListaPokemon[3] = 124;
                    ListaPokemon[4] = 97;
                    ListaPokemon[5] = 326;
                    break;
                case 10: //Koga
                    ListaPokemon[0] = 168;
                    ListaPokemon[1] = 49;
                    ListaPokemon[2] = 205;
                    ListaPokemon[3] = 89;
                    ListaPokemon[4] = 169;
                    ListaPokemon[5] = 110;
                    break;
                case 11: //Bruno
                    ListaPokemon[0] = 237;
                    ListaPokemon[1] = 107;
                    ListaPokemon[2] = 95;
                    ListaPokemon[3] = 106;
                    ListaPokemon[4] = 68;
                    ListaPokemon[5] = 208;
                    break;
                case 12: //Karen
                    ListaPokemon[0] = 197;
                    ListaPokemon[1] = 94;
                    ListaPokemon[2] = 229;
                    ListaPokemon[3] = 198;
                    ListaPokemon[4] = 45;
                    ListaPokemon[5] = 359;
                    break;
                case 13: //Lance
                    ListaPokemon[0] = 130;
                    ListaPokemon[1] = 149;
                    ListaPokemon[2] = 142;
                    ListaPokemon[3] = 6;
                    ListaPokemon[4] = 230;
                    ListaPokemon[5] = 373;
                    break;
                default:
                    break;
            }
            labelfotoentrenadorIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Lider Johto/" + n + ".png")).getImage()).getScaledInstance(45, 30, java.awt.Image.SCALE_SMOOTH)));
        } else if (PokemonGUI.hoenn) {
            switch (n) {
                case 1: //Petra
                    ListaPokemon[0] = 299;
                    ListaPokemon[1] = 76;
                    ListaPokemon[2] = 95;
                    ListaPokemon[3] = 141;
                    ListaPokemon[4] = 139;
                    ListaPokemon[5] = 348;
                    break;
                case 2: //Marcial
                    ListaPokemon[0] = 68;
                    ListaPokemon[1] = 237;
                    ListaPokemon[2] = 297;
                    ListaPokemon[3] = 308;
                    ListaPokemon[4] = 107;
                    ListaPokemon[5] = 106;
                    break;
                case 3: //Erico
                    ListaPokemon[0] = 82;
                    ListaPokemon[1] = 310;
                    ListaPokemon[2] = 101;
                    ListaPokemon[3] = 26;
                    ListaPokemon[4] = 181;
                    ListaPokemon[5] = 125;
                    break;
                case 4: //Candela
                    ListaPokemon[0] = 324;
                    ListaPokemon[1] = 219;
                    ListaPokemon[2] = 78;
                    ListaPokemon[3] = 59;
                    ListaPokemon[4] = 229;
                    ListaPokemon[5] = 257;
                    break;
                case 5: //Norman
                    ListaPokemon[0] = 289;
                    ListaPokemon[1] = 264;
                    ListaPokemon[2] = 115;
                    ListaPokemon[3] = 242;
                    ListaPokemon[4] = 335;
                    ListaPokemon[5] = 295;
                    break;
                case 6: //Alana
                    ListaPokemon[0] = 277;
                    ListaPokemon[1] = 279;
                    ListaPokemon[2] = 227;
                    ListaPokemon[3] = 334;
                    ListaPokemon[4] = 357;
                    ListaPokemon[5] = 164;
                    break;
                case 7: //Vito y Leti
                    ListaPokemon[0] = 338;
                    ListaPokemon[1] = 337;
                    ListaPokemon[2] = 344;
                    ListaPokemon[3] = 358;
                    ListaPokemon[4] = 282;
                    ListaPokemon[5] = 326;
                    break;
                case 8: //Plubio
                    ListaPokemon[0] = 350;
                    ListaPokemon[1] = 321;
                    ListaPokemon[2] = 340;
                    ListaPokemon[3] = 272;
                    ListaPokemon[4] = 319;
                    ListaPokemon[5] = 119;
                    break;
                case 9: //Sixto
                    ListaPokemon[0] = 262;
                    ListaPokemon[1] = 275;
                    ListaPokemon[2] = 319;
                    ListaPokemon[3] = 332;
                    ListaPokemon[4] = 359;
                    ListaPokemon[5] = 342;
                    break;
                case 10: //Fátima
                    ListaPokemon[0] = 356;
                    ListaPokemon[1] = 354;
                    ListaPokemon[2] = 302;
                    ListaPokemon[3] = 94;
                    ListaPokemon[4] = 200;
                    ListaPokemon[5] = 292;
                    break;
                case 11: //Nivea
                    ListaPokemon[0] = 365;
                    ListaPokemon[1] = 362;
                    ListaPokemon[2] = 131;
                    ListaPokemon[3] = 144;
                    ListaPokemon[4] = 215;
                    ListaPokemon[5] = 124;
                    break;
                case 12: //Dracón
                    ListaPokemon[0] = 330;
                    ListaPokemon[1] = 334;
                    ListaPokemon[2] = 373;
                    ListaPokemon[3] = 230;
                    ListaPokemon[4] = 149;
                    ListaPokemon[5] = 384;
                    break;
                case 13: //Máximo Peñas
                    ListaPokemon[0] = 227;
                    ListaPokemon[1] = 376;
                    ListaPokemon[2] = 348;
                    ListaPokemon[3] = 306;
                    ListaPokemon[4] = 344;
                    ListaPokemon[5] = 346;
                    break;
                default:
                    break;
            }
            labelfotoentrenadorIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Lider Hoenn/" + n + ".png")).getImage()).getScaledInstance(45, 30, java.awt.Image.SCALE_SMOOTH)));
        } else if (PokemonGUI.sinnoh) {
            switch (n) {
                case 1: //Roco
                    ListaPokemon[0] = 409;
                    ListaPokemon[1] = 476;
                    ListaPokemon[2] = 76;
                    ListaPokemon[3] = 558;
                    ListaPokemon[4] = 567;
                    ListaPokemon[5] = 369;
                    break;
                case 2: //Gardenia
                    ListaPokemon[0] = 389;
                    ListaPokemon[1] = 465;
                    ListaPokemon[2] = 455;
                    ListaPokemon[3] = 407;
                    ListaPokemon[4] = 470;
                    ListaPokemon[5] = 357;
                    break;
                case 3: //Brega
                    ListaPokemon[0] = 448;
                    ListaPokemon[1] = 475;
                    ListaPokemon[2] = 454;
                    ListaPokemon[3] = 392;
                    ListaPokemon[4] = 68;
                    ListaPokemon[5] = 308;
                    break;
                case 4: //Mananti
                    ListaPokemon[0] = 419;
                    ListaPokemon[1] = 395;
                    ListaPokemon[2] = 423;
                    ListaPokemon[3] = 457;
                    ListaPokemon[4] = 319;
                    ListaPokemon[5] = 272;
                    break;
                case 5: //Fantina
                    ListaPokemon[0] = 593;
                    ListaPokemon[1] = 479;
                    ListaPokemon[2] = 487;
                    ListaPokemon[3] = 442;
                    ListaPokemon[4] = 477;
                    ListaPokemon[5] = 429;
                    break;
                case 6: //Aceron
                    ListaPokemon[0] = 462;
                    ListaPokemon[1] = 437;
                    ListaPokemon[2] = 530;
                    ListaPokemon[3] = 411;
                    ListaPokemon[4] = 306;
                    ListaPokemon[5] = 227;
                    break;
                case 7: //Inverna
                    ListaPokemon[0] = 460;
                    ListaPokemon[1] = 478;
                    ListaPokemon[2] = 473;
                    ListaPokemon[3] = 471;
                    ListaPokemon[4] = 461;
                    ListaPokemon[5] = 362;
                    break;
                case 8: //Lectro
                    ListaPokemon[0] = 405;
                    ListaPokemon[1] = 466;
                    ListaPokemon[2] = 479;
                    ListaPokemon[3] = 604;
                    ListaPokemon[4] = 523;
                    ListaPokemon[5] = 596;
                    break;
                case 9: //Alecran
                    ListaPokemon[0] = 212;
                    ListaPokemon[1] = 214;
                    ListaPokemon[2] = 469;
                    ListaPokemon[3] = 452;
                    ListaPokemon[4] = 416;
                    ListaPokemon[5] = 269;
                    break;
                case 10: //Gaia
                    ListaPokemon[0] = 450;
                    ListaPokemon[1] = 472;
                    ListaPokemon[2] = 464;
                    ListaPokemon[3] = 76;
                    ListaPokemon[4] = 340;
                    ListaPokemon[5] = 185;
                    break;
                case 11: //Fausto
                    ListaPokemon[0] = 392;
                    ListaPokemon[1] = 78;
                    ListaPokemon[2] = 467;
                    ListaPokemon[3] = 229;
                    ListaPokemon[4] = 136;
                    ListaPokemon[5] = 428;
                    break;
                case 12: //Delos
                    ListaPokemon[0] = 437;
                    ListaPokemon[1] = 203;
                    ListaPokemon[2] = 475;
                    ListaPokemon[3] = 122;
                    ListaPokemon[4] = 196;
                    ListaPokemon[5] = 65;
                    break;
                case 13: //Cintia
                    ListaPokemon[0] = 445;
                    ListaPokemon[1] = 468;
                    ListaPokemon[2] = 407;
                    ListaPokemon[3] = 604;
                    ListaPokemon[4] = 448;
                    ListaPokemon[5] = 628;
                    break;
                default:
                    break;
            }
            labelfotoentrenadorIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Lider Sinnoh/" + n + ".png")).getImage()).getScaledInstance(45, 30, java.awt.Image.SCALE_SMOOTH)));
        } else {
            switch (n) {
                case 1: //Millo, Maíz y Zeo
                    ListaPokemon[0] = 497;
                    ListaPokemon[1] = 512;
                    ListaPokemon[2] = 503;
                    ListaPokemon[3] = 516;
                    ListaPokemon[4] = 631;
                    ListaPokemon[5] = 514;
                    break;
                case 2: //Aloe
                    ListaPokemon[0] = 531;
                    ListaPokemon[1] = 586;
                    ListaPokemon[2] = 115;
                    ListaPokemon[3] = 463;
                    ListaPokemon[4] = 573;
                    ListaPokemon[5] = 206;
                    break;
                case 3: //Camus
                    ListaPokemon[0] = 542;
                    ListaPokemon[1] = 558;
                    ListaPokemon[2] = 589;
                    ListaPokemon[3] = 617;
                    ListaPokemon[4] = 632;
                    ListaPokemon[5] = 416;
                    break;
                case 4: //Camila
                    ListaPokemon[0] = 523;
                    ListaPokemon[1] = 587;
                    ListaPokemon[2] = 181;
                    ListaPokemon[3] = 618;
                    ListaPokemon[4] = 604;
                    ListaPokemon[5] = 405;
                    break;
                case 5: //Yakon
                    ListaPokemon[0] = 530;
                    ListaPokemon[1] = 553;
                    ListaPokemon[2] = 537;
                    ListaPokemon[3] = 623;
                    ListaPokemon[4] = 473;
                    ListaPokemon[5] = 330;
                    break;
                case 6: //Gerania
                    ListaPokemon[0] = 581;
                    ListaPokemon[1] = 528;
                    ListaPokemon[2] = 521;
                    ListaPokemon[3] = 561;
                    ListaPokemon[4] = 630;
                    ListaPokemon[5] = 567;
                    break;
                case 7: //Junco
                    ListaPokemon[0] = 614;
                    ListaPokemon[1] = 584;
                    ListaPokemon[2] = 615;
                    ListaPokemon[3] = 461;
                    ListaPokemon[4] = 365;
                    ListaPokemon[5] = 87;
                    break;
                case 8: //Lirio e Iris
                    ListaPokemon[0] = 612;
                    ListaPokemon[1] = 621;
                    ListaPokemon[2] = 635;
                    ListaPokemon[3] = 567;
                    ListaPokemon[4] = 373;
                    ListaPokemon[5] = 334;
                    break;
                case 9: //Anis
                    ListaPokemon[0] = 563;
                    ListaPokemon[1] = 593;
                    ListaPokemon[2] = 609;
                    ListaPokemon[3] = 623;
                    ListaPokemon[4] = 426;
                    ListaPokemon[5] = 478;
                    break;
                case 10: //Aza
                    ListaPokemon[0] = 560;
                    ListaPokemon[1] = 625;
                    ListaPokemon[2] = 452;
                    ListaPokemon[3] = 359;
                    ListaPokemon[4] = 430;
                    ListaPokemon[5] = 553;
                    break;
                case 11: //Catleya
                    ListaPokemon[0] = 579;
                    ListaPokemon[1] = 518;
                    ListaPokemon[2] = 576;
                    ListaPokemon[3] = 561;
                    ListaPokemon[4] = 437;
                    ListaPokemon[5] = 376;
                    break;
                case 12: //Lotto
                    ListaPokemon[0] = 538;
                    ListaPokemon[1] = 539;
                    ListaPokemon[2] = 620;
                    ListaPokemon[3] = 534;
                    ListaPokemon[4] = 454;
                    ListaPokemon[5] = 448;
                    break;
                case 13: //Mirto
                    ListaPokemon[0] = 617;
                    ListaPokemon[1] = 626;
                    ListaPokemon[2] = 584;
                    ListaPokemon[3] = 621;
                    ListaPokemon[4] = 589;
                    ListaPokemon[5] = 637;
                    break;
                default:
                    break;
            }
            labelfotoentrenadorIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Lider Teselia/" + n + ".png")).getImage()).getScaledInstance(45, 30, java.awt.Image.SCALE_SMOOTH)));
        }

        labelnombreentrenadorIA.setText(NombreEntrenador(n));
        if (Juego.repetircombate) {
            n++;
        }
        if (n == 1 && !Juego.repetircombate) {
            for (int i = 0; i < ListaPokemon.length; i++) {
                tipo1IA[i] = new JLabel();
                tipo2IA[i] = new JLabel();
                estadoequipoIA[i] = new JLabel();
                lblsaludequipoIA[i] = new JLabel();
            }
        }
        for (int i = 0; i < ListaPokemon.length; i++) {
            Pokemon p = PokemonElegido(PokemonGUI.bdpokemon, ListaPokemon[i]);
            p.naturaleza = Pokemon.EleccionNaturaleza();
            Juego.equipoIA[i] = p;

            botonpokemonelegidosIA[i].setLayout(null);
            botonpokemonelegidosIA[i].setText(p.nombre);
            botonpokemonelegidosIA[i].setHorizontalTextPosition(SwingConstants.RIGHT);
            botonpokemonelegidosIA[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            botonpokemonelegidosIA[i].setFocusable(false);

            tipo1IA[i].setBounds(5, 15, 50, 20);
            tipo1IA[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo1 + ".gif"));

            botonpokemonelegidosIA[i].add(tipo1IA[i]);
            if (p.tipo2.compareTo("n*n*") != 0) {
                tipo2IA[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo2 + ".gif"));
                tipo2IA[i].setBounds(5, 45, 50, 20);
            } else {
                tipo2IA[i].setIcon(null);
            }
            botonpokemonelegidosIA[i].add(tipo2IA[i]);

            botonpokemonelegidosIA[i].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + p.codigo + ".png"));

            estadoequipoIA[i].setBounds(200, 7, 30, 30);
            botonpokemonelegidosIA[i].add(estadoequipoIA[i]);

            lblsaludequipoIA[i].setBounds(155, 48, 60, 20);
            botonpokemonelegidosIA[i].add(lblsaludequipoIA[i]);
            lblsaludequipoIA[i].setText("" + (int) Juego.equipoIA[i].salud + "/" + (int) Juego.equipoIA[i].saludmax);
            botonpokemonelegidosIA[i].setEnabled(true);

            Juego.equipoIA[i].estado = "Normal";
            Juego.equipoIA[i].salud = Juego.equipoIA[i].saludmax;
            Juego.equipoIA[i].salud = Juego.equipoIA[i].saludmax;
            Juego.equipoIA[i].ataque = Juego.equipoIA[i].ataquebase;
            Juego.equipoIA[i].defensa = Juego.equipoIA[i].defensabase;
            Juego.equipoIA[i].ataqueesp = Juego.equipoIA[i].ataqueespbase;
            Juego.equipoIA[i].defensaesp = Juego.equipoIA[i].defensaespbase;
            Juego.equipoIA[i].velocidad = Juego.equipoIA[i].velocidadbase;
            Juego.equipoIA[i].precision = 100;
            Juego.equipoIA[i].evasion = 100;
            Juego.equipoIA[i].turnossincurase = 0;
            Juego.equipoIA[i].atrapado = 0;
            Juego.equipoIA[i].retroceder = false;
            Juego.equipoIA[i].alalcance = true;
            Juego.equipoIA[i].volando = false;
            Juego.equipoIA[i].enterrado = false;
            Juego.equipoIA[i].sumergido = false;
            Juego.equipoIA[i].descansar = false;

            Juego.equipojugador[i].estado = "Normal";
            Juego.equipojugador[i].salud = Juego.equipojugador[i].saludmax;
            Juego.equipojugador[i].ataque = Juego.equipojugador[i].ataquebase;
            Juego.equipojugador[i].defensa = Juego.equipojugador[i].defensabase;
            Juego.equipojugador[i].ataqueesp = Juego.equipojugador[i].ataqueespbase;
            Juego.equipojugador[i].defensaesp = Juego.equipojugador[i].defensaespbase;
            Juego.equipojugador[i].velocidad = Juego.equipojugador[i].velocidadbase;
            Juego.equipojugador[i].precision = 100;
            Juego.equipojugador[i].evasion = 100;
            Juego.equipojugador[i].turnossincurase = 0;
            Juego.equipojugador[i].atrapado = 0;
            Juego.equipojugador[i].retroceder = false;
            Juego.equipojugador[i].alalcance = true;
            Juego.equipojugador[i].volando = false;
            Juego.equipojugador[i].enterrado = false;
            Juego.equipojugador[i].sumergido = false;
            Juego.equipojugador[i].descansar = false;

            PokemonGUI.botonpokemonelegidosJugador[i].setEnabled(true);
            if (n > 1) {
                PokemonGUI.lblsaludequipoIA[i].setText("" + (int) Juego.equipoIA[i].salud + "/" + (int) Juego.equipoIA[i].saludmax);
                PokemonGUI.estadoequipoIA[i].setIcon(null);
                PokemonGUI.lblsaludequipojugador[i].setText("" + (int) Juego.equipojugador[i].salud + "/" + (int) Juego.equipojugador[i].saludmax);
                PokemonGUI.estadoequipojugador[i].setIcon(null);
            }
        }
        Juego.veloSagradoJugador = 0;
        Juego.veloSagradoIA = 0;
        if (n > 1) {
            PokemonGUI.labelfotoIA.setIcon(null);
            PokemonGUI.estadoIA.setIcon(null);
            PokemonGUI.panelestadoIA.setVisible(false);

            PokemonGUI.labelfotojugador.setIcon(null);
            PokemonGUI.estadojugador.setIcon(null);
            PokemonGUI.panelestadojugador.setVisible(false);
        }
        if (Juego.repetircombate) {
            n--;
        }
    }
}
