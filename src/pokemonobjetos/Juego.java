/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonobjetos;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static pokemonobjetos.Juego.ataquejugador;
import static pokemonobjetos.PokemonGUI.CerrarDBOO;
import static pokemonobjetos.PokemonGUI.ataqueusadoIA;
import static pokemonobjetos.PokemonGUI.ataqueusadojugador;
import static pokemonobjetos.PokemonGUI.botonataques;
import static pokemonobjetos.PokemonGUI.cambiarpokemon;
import static pokemonobjetos.PokemonGUI.entrenadorliga;
import static pokemonobjetos.PokemonGUI.labelnombreIA;
import static pokemonobjetos.PokemonGUI.lblsaludequipoIA;
import static pokemonobjetos.PokemonGUI.lblsaludequipojugador;
import static pokemonobjetos.PokemonGUI.pokemonencombateIA;
import static pokemonobjetos.PokemonGUI.pokemonencombatejugador;
import static pokemonobjetos.PokemonGUI.ronda;
import static pokemonobjetos.PokemonGUI.saludIA;

/**
 *
 * @author Victor
 */
public class Juego {

    public static int npokemonequipo;
    public static Pokemon[] equipojugador, equipoIA;
    public static Ataque[][] ataquejugador, ataqueIA;
    public static boolean finalizar = false;
    public static String str = "";
    public static int continuaciones = 3;
    public static boolean repetircombate = false, comprobarambasvidas = false;
    public static int veloSagradoJugador = 0;
    public static int veloSagradoIA = 0;

    public static void CrearEquipos() {
        npokemonequipo = Lecturas.TipoCombate(PokemonGUI.tipocombate);
        equipojugador = new Pokemon[npokemonequipo];
        equipoIA = new Pokemon[npokemonequipo];
    }

    public static void InicializaAtaques() {
        ataquejugador = new Ataque[npokemonequipo][5];
        ataqueIA = new Ataque[npokemonequipo][5];
    }

    public static void Combate() throws IOException, InterruptedException {
        str = "";
        if (!PokemonGUI.atacaiaporcambiarpokemonjugador) {
            ataquejugador[pokemonencombatejugador][ataqueusadojugador].AtaquesPrioritarios(equipojugador[pokemonencombatejugador], ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
        }
        ataqueIA[pokemonencombateIA][ataqueusadoIA].AtaquesPrioritarios(equipoIA[pokemonencombateIA], ataqueIA[pokemonencombateIA][ataqueusadoIA]);
        AtacarPokemon();
    }

    public static void AtacarPokemon() throws IOException, InterruptedException {
        if (equipojugador[pokemonencombatejugador].Orden(equipojugador[pokemonencombatejugador], equipoIA[pokemonencombateIA]) == 1) {
            if (!PokemonGUI.atacaiaporcambiarpokemonjugador) {
                ataquejugador[pokemonencombatejugador][ataqueusadojugador] = ataquejugador[pokemonencombatejugador][ataqueusadojugador].Metronomo(ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
                str += equipojugador[pokemonencombatejugador].nombre + " usó " + ataquejugador[pokemonencombatejugador][ataqueusadojugador].nombre + ".\r\n";
                PokemonGUI.Mensajes.setText(str);
                str += equipojugador[pokemonencombatejugador].Atacar(equipojugador[pokemonencombatejugador], equipoIA[pokemonencombateIA], ataquejugador[pokemonencombatejugador][ataqueusadojugador], equipojugador, ataquejugador[pokemonencombatejugador]) + "\r\n";
                str += ataquejugador[pokemonencombatejugador][ataqueusadojugador].AtaquesTurnosJugador(equipojugador);
            }
            PokemonGUI.BarraDeVidaIA();
        } else {
            ataqueIA[pokemonencombateIA][ataqueusadoIA] = ataqueIA[pokemonencombateIA][ataqueusadoIA].Metronomo(ataqueIA[pokemonencombateIA][ataqueusadoIA]);
            str += equipoIA[pokemonencombateIA].nombre + " usó " + ataqueIA[pokemonencombateIA][ataqueusadoIA].nombre + ".\r\n";
            PokemonGUI.Mensajes.setText(str);
            str += equipoIA[pokemonencombateIA].Atacar(equipoIA[pokemonencombateIA], equipojugador[pokemonencombatejugador], ataqueIA[pokemonencombateIA][ataqueusadoIA], equipoIA, ataqueIA[pokemonencombateIA]) + "\r\n";
            str += ataqueIA[pokemonencombateIA][ataqueusadoIA].AtaquesTurnosIA(equipoIA);
            PokemonGUI.BarraDeVidaJugador();
        }
    }

    public static void ComprobacionesDespuesAtacar() throws IOException, FileNotFoundException, InterruptedException {
        for (int i = 0; i < botonataques.length; i++) {
            botonataques[i].setText(ataquejugador[pokemonencombatejugador][i].nombre + " " + ataquejugador[pokemonencombatejugador][i].pp + "/" + ataquejugador[pokemonencombatejugador][i].ppmax);
        }
        Pokemon.Esquema();
        if (equipojugador[pokemonencombatejugador].Vivo() && equipoIA[pokemonencombateIA].Vivo()) {
            //str = "";
            TransformacionDittoJugador();
            TransformacionDittoIA();
            equipojugador[pokemonencombatejugador].retroceder = false;
            equipoIA[pokemonencombateIA].retroceder = false;
            equipojugador[pokemonencombatejugador].noatacar = false;
            equipoIA[pokemonencombateIA].noatacar = false;
            ronda++;
            if (veloSagradoJugador > 0) {
                veloSagradoJugador--;
                if (veloSagradoJugador == 0) {
                    for (int i = 0; i < equipojugador.length; i++) {
                        equipojugador[i].protegeestado = false;
                    }
                    str += "La protección de Velo Sagrado de tu equipo desapareció";
                }
            }
            if (veloSagradoIA > 0) {
                veloSagradoIA--;
                if (veloSagradoIA == 0) {
                    for (int i = 0; i < equipoIA.length; i++) {
                        equipoIA[i].protegeestado = false;
                    }
                    str += "La protección de Velo Sagrado del equipo rival desapareció";
                }
            }
            System.out.println("ATAQUEUSADO: " + ataqueusadojugador);
            if (!Pokemon.activarsolounboton) {
                for (int i = 0; i < botonataques.length; i++) {
                    PokemonGUI.botonataques[i].setEnabled(true);
                }
                PokemonGUI.botoncambiarpokemon.setEnabled(true);
            } else {
                for (int i = 0; i < PokemonGUI.botonataques.length; i++) {
                    if (i == ataqueusadojugador) {
                        PokemonGUI.botonataques[i].setEnabled(true);
                    } else {
                        PokemonGUI.botonataques[i].setEnabled(false);
                    }
                }
                PokemonGUI.botoncambiarpokemon.setEnabled(false);
            }
            // if (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].turnosincapacitado == 0) {
            Juego.equipojugador[PokemonGUI.pokemonencombatejugador].golpe = 0;
            //}
            //if (Juego.equipoIA[PokemonGUI.pokemonencombateIA].turnosincapacitado == 0) {
            Juego.equipoIA[PokemonGUI.pokemonencombateIA].golpe = 0;
            //}
        } else {
            str += equipojugador[pokemonencombatejugador].MismoDestino(equipojugador[pokemonencombatejugador], equipoIA[pokemonencombateIA]);
            str += equipoIA[pokemonencombateIA].MismoDestino(equipoIA[pokemonencombateIA], equipojugador[pokemonencombatejugador]);
            str += Vencedor(equipojugador[pokemonencombatejugador], equipoIA[pokemonencombateIA]);
            // if (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].turnosincapacitado == 0) {
            Juego.equipojugador[PokemonGUI.pokemonencombatejugador].golpe = 0;
            //}
            //if (Juego.equipoIA[PokemonGUI.pokemonencombateIA].turnosincapacitado == 0) {
            Juego.equipoIA[PokemonGUI.pokemonencombateIA].golpe = 0;
            //}
            PokemonGUI.RefrescoVidaJugador();
            PokemonGUI.RefrescoVidaIA();
            PokemonGUI.Mensajes.setText(str);
            if (!ChequeoPokemonVivos(equipojugador) && !ChequeoPokemonVivos(equipoIA)) {
                JOptionPane.showMessageDialog(null, "¡Has empatado!");
                finalizar = true;
                PokemonGUI.MusicCombate.stop();
                PokemonGUI.MusicFinCombate = new JLayerLoop("Victoria/Derrota.mp3", false);
                PokemonGUI.MusicFinCombate.play();
            } else {
                if (!ChequeoPokemonVivos(equipojugador)) {
                    JOptionPane.showMessageDialog(null, "¡Has perdido catastróficamente!");
                    finalizar = true;
                    PokemonGUI.MusicCombate.stop();
                    PokemonGUI.MusicFinCombate = new JLayerLoop("Victoria/Derrota.mp3", false);
                    PokemonGUI.MusicFinCombate.play();
                }
                if (!ChequeoPokemonVivos(equipoIA)) {
                    JOptionPane.showMessageDialog(null, "¡Has ganado el combate!");
                    if (!PokemonGUI.modocarrera) {
                        finalizar = true;
                        PokemonGUI.MusicCombate.stop();
                        PokemonGUI.MusicFinCombate = new JLayerLoop("Victoria/Victoria.mp3", false);
                        PokemonGUI.MusicFinCombate.play();
                    } else {
                        if (entrenadorliga < 13) {
                            PokemonGUI.MusicCombate.stop();
                            PokemonGUI.MusicFinCombate = new JLayerLoop("Victoria/Victoria.mp3", false);
                            PokemonGUI.MusicFinCombate.play();
                            System.out.println("----> " + entrenadorliga);
                            JOptionPane.showMessageDialog(null, "¡Has vencido al lider de gimnasio " + Pokemon.NombreEntrenador(PokemonGUI.entrenadorliga) + ", prepárate para " + Pokemon.NombreEntrenador(PokemonGUI.entrenadorliga + 1));
                            repetircombate = false;

                            cambiarpokemon = true;
                            for (int i = 0; i < PokemonGUI.botonataques.length; i++) {
                                PokemonGUI.botonataques[i].setEnabled(false);
                            }
                            PokemonGUI.botoncambiarpokemon.setEnabled(false);
                            for (int i = 0; i < PokemonGUI.botonpokemonelegidosJugador.length; i++) {
                                PokemonGUI.botonpokemonelegidosJugador[i].setEnabled(false);
                            }
                        }
                        entrenadorliga++;
                        if (entrenadorliga < 14) {
                            PokemonGUI.botonsiguienteentrenador.setText("Siguiente entrenador");
                            PokemonGUI.botonsiguienteentrenador.setVisible(true);
                        } else {
                            finalizar = true;
                            PokemonGUI.MusicCombate.stop();
                            PokemonGUI.MusicFinCombate.stop();
                            PokemonGUI.MusicFinCombate = new JLayerLoop("Victoria/Final Victory.mp3", false);
                            PokemonGUI.MusicFinCombate.play();
                            JOptionPane.showMessageDialog(null, "¡Eres todo un maestro pokemon, ENHORABUENA!");
                            JOptionPane.showMessageDialog(null, "¡No puedes hacerlo mejor!");
                        }
                    }
                }
            }
            if (finalizar) {
                if (!PokemonGUI.modocarrera) {
                    for (int i = 0; i < PokemonGUI.botonataques.length; i++) {
                        PokemonGUI.botonataques[i].setEnabled(false);
                    }
                    PokemonGUI.botonatacar.setVisible(false);
                    PokemonGUI.botoncambiarpokemon.setEnabled(false);
                    PokemonGUI.botonestadisticasvictorias.setVisible(true);
                } else {
                    if (entrenadorliga == 14) {
                        for (int i = 0; i < equipojugador.length; i++) {
                            Lecturas.HallFamaRandom(equipojugador[i]);
                        }
                        for (int i = 0; i < PokemonGUI.botonataques.length; i++) {
                            PokemonGUI.botonataques[i].setEnabled(false);
                        }
                        PokemonGUI.botonatacar.setVisible(false);
                        PokemonGUI.botoncambiarpokemon.setEnabled(false);
                        PokemonGUI.botonestadisticasvictorias.setText("Hall de la fama");
                        PokemonGUI.botonestadisticasvictorias.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, Pokemon.NombreEntrenador(PokemonGUI.entrenadorliga) + " te machacó.");
                        int m = 1;
                        do {
                            if (continuaciones > 0) {
                                int n = JOptionPane.showConfirmDialog(null, "¿Deseas intentarlo de nuevo? - Continuaciones: " + continuaciones, "", JOptionPane.YES_NO_OPTION);
                                if (n == 0) {
                                    System.out.println("---> " + entrenadorliga);
                                    continuaciones--;
                                    repetircombate = true;
                                    cambiarpokemon = true;
                                    finalizar = false;
                                    for (int i = 0; i < PokemonGUI.botonataques.length; i++) {
                                        PokemonGUI.botonataques[i].setEnabled(false);
                                    }
                                    PokemonGUI.botoncambiarpokemon.setEnabled(false);
                                    for (int i = 0; i < PokemonGUI.botonpokemonelegidosJugador.length; i++) {
                                        PokemonGUI.botonpokemonelegidosJugador[i].setEnabled(false);
                                    }
                                    PokemonGUI.botonsiguienteentrenador.setText("Repetir combate");
                                    PokemonGUI.botonsiguienteentrenador.setVisible(true);
                                    m = 0;
                                } else {
                                    m = JOptionPane.showConfirmDialog(null, "¿Estás seguro? el juego finalizará", "", JOptionPane.YES_NO_OPTION);
                                    if (m == 0) {
                                        CerrarDBOO();
                                        System.exit(0);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No te quedan más continuaciones, inténtalo en otro momento.");
                                CerrarDBOO();
                                System.exit(0);
                            }
                        } while (m != 0);
                    }
                }
                //PokemonGUI.bdpokemon.close();
                //PokemonGUI.bdataque.close();
            }
        }
    }

    static void MostrarEstado() {
        if (equipojugador[pokemonencombatejugador].estado.compareTo("Normal") != 0 || equipojugador[pokemonencombatejugador].drenadoras || equipojugador[pokemonencombatejugador].atrapado > 0) {
            PokemonGUI.estadojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
            //PokemonGUI.estadoequipojugador[pokemonencombatejugador].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
        } else {
            PokemonGUI.estadoequipojugador[pokemonencombatejugador].setIcon(null);
            PokemonGUI.estadojugador.setIcon(null);
        }

        for (int i = 0; i < equipojugador.length; i++) {
            if (equipojugador[i].estado.compareTo("Normal") != 0) {
                PokemonGUI.estadoequipojugador[i].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[i].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
            } else {
                PokemonGUI.estadoequipojugador[i].setIcon(null);
            }
            lblsaludequipojugador[i].setText("" + (int) equipojugador[i].salud + "/" + (int) equipojugador[i].saludmax);
            lblsaludequipoIA[i].setText("" + (int) equipoIA[i].salud + "/" + (int) equipoIA[i].saludmax);
        }
        if (equipoIA[pokemonencombateIA].estado.compareTo("Normal") != 0 || equipoIA[pokemonencombateIA].drenadoras || equipoIA[pokemonencombateIA].atrapado > 0) {
            PokemonGUI.estadoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipoIA[pokemonencombateIA].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
            PokemonGUI.estadoequipoIA[pokemonencombateIA].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipoIA[pokemonencombateIA].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
        } else {
            PokemonGUI.estadoequipoIA[pokemonencombateIA].setIcon(null);
            PokemonGUI.estadoIA.setIcon(null);
        }

        for (int i = 0; i < equipoIA.length; i++) {
            if (equipoIA[i].estado.compareTo("Normal") != 0) {
                PokemonGUI.estadoequipoIA[i].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipoIA[i].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
            } else {
                PokemonGUI.estadoequipoIA[i].setIcon(null);
            }
        }
    }

    static String Vencedor(Pokemon j1, Pokemon j2) throws FileNotFoundException, IOException, InterruptedException {
        String stri = "";
        if (!j1.Vivo() && !j2.Vivo()) {
            System.out.println("¡Han caido a la vez!");
            stri += "¡¡Han caido a la vez!!";
            j1.estado = "Debilitado";
            j2.estado = "Debilitado";
            MostrarEstado();
            if (ChequeoPokemonVivos(equipojugador) && ChequeoPokemonVivos(equipoIA)) {
                PokemonGUI.cambiarpokemon = true;
                JOptionPane.showMessageDialog(null, equipojugador[pokemonencombatejugador].nombre + " se debilitó, elige otro Pokémon");
                PokemonGUI.DesactivarBotonAtaques();
                PokemonGUI.pokemongui.setCursor(PokemonGUI.cursorpokeball);
            }
        } else {
            System.out.println("");
            System.out.println("**************************************");
            stri += "**************************************\r\n";
            if (j1.Vivo()) {
                j2.estado = "Debilitado";
                MostrarEstado();
                System.out.println(j2.nombre.trim() + " se debilitó.");
                stri += j2.nombre + " se debilitó.\r\n";
                System.out.println("¡¡VENCEDOR " + j1.nombre.trim() + " EN XXX RONDAS!!");
                stri += "¡¡VENCEDOR " + j1.nombre.trim() + " EN " + PokemonGUI.ronda + " RONDAS!!\r\n";
                if (!PokemonGUI.modocarrera) {
                    Lecturas.EstadisticasVicRandom(j1);
                }
                //Lecturas.EstadisticasDerRandom(Pokemonj2);
                j1.victoriasconsecutivas++;
                System.out.println(j1.nombre.trim() + " ha ganado " + j1.victoriasconsecutivas + " peleas en este combate.");
                stri += j1.nombre + " ha ganado " + j1.victoriasconsecutivas + " peleas en este combate.\r\n";
                if (ChequeoPokemonVivos(equipoIA)) {
                    CambiarPokemonIA();
                }
            } else {
                j1.estado = "Debilitado";
                MostrarEstado();
                System.out.println(j1.nombre.trim() + " se debilitó.");
                stri += j1.nombre + " se debilitó.\r\n";
                System.out.println("¡¡GANA " + j2.nombre.trim() + " EN XXX RONDAS!!");
                stri += "¡¡GANA " + j2.nombre + " EN " + PokemonGUI.ronda + " RONDAS!!\r\n";
                if (!PokemonGUI.modocarrera) {
                    Lecturas.EstadisticasVicRandom(j2);
                }
                //Lecturas.EstadisticasDerRandom(Pokemonj1);
                j2.victoriasconsecutivas++;
                System.out.println(j2.nombre.trim() + " ha ganado " + j2.victoriasconsecutivas + " peleas en este combate.");
                stri += j2.nombre + " ha ganado " + j2.victoriasconsecutivas + " peleas en este combate.\r\n";

                if (ChequeoPokemonVivos(equipojugador)) {
                    PokemonGUI.cambiarpokemon = true;
                    JOptionPane.showMessageDialog(null, equipojugador[pokemonencombatejugador].nombre + " se debilitó, elige otro Pokémon");
                    PokemonGUI.DesactivarBotonAtaques();
                    PokemonGUI.botonatacar.setVisible(true);
                    PokemonGUI.pokemongui.setCursor(PokemonGUI.cursorpokeball);
                }

                PokemonGUI.estadoIA.setIcon(null);
            }
            System.out.println("**************************************");
            stri += "**************************************\r\n";
            System.out.println("j1: " + j1.estado);
            System.out.println("j2: " + j2.estado);
        }
        return stri;
    }

    public static void CambiarPokemonIA() {
        int l = pokemonencombateIA;
        do {
            l = Metodos.RandomSinRound(Juego.npokemonequipo);
        } while (!Juego.equipoIA[l].Vivo());
        int n = JOptionPane.showConfirmDialog(null, "El rival va a sacar a " + Juego.equipoIA[l].nombre + ".¿Deseas cambiar de Pokemon?", "", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            PokemonGUI.cambiarpokemon = true;
            PokemonGUI.DesactivarBotonAtaques();
            PokemonGUI.estadoIA.setIcon(null);
            PokemonGUI.pokemongui.setCursor(PokemonGUI.cursorpokeball);
            Pokemon.activarsolounboton = false;
        } else {
            for (int k = 0; k < botonataques.length; k++) {
                botonataques[k].setEnabled(true);
            }
            PokemonGUI.botoncambiarpokemon.setEnabled(true);
        }
        if (equipojugador[pokemonencombatejugador].estado.compareTo("Normal") != 0 || equipojugador[pokemonencombatejugador].drenadoras || equipojugador[pokemonencombatejugador].atrapado > 0) {
            PokemonGUI.estadojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
            //PokemonGUI.estadoequipojugador[pokemonencombatejugador].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
        } else {
            PokemonGUI.estadoequipojugador[pokemonencombatejugador].setIcon(null);
            PokemonGUI.estadojugador.setIcon(null);
        }
        pokemonencombateIA = l;
        if (equipoIA[pokemonencombateIA].estado.compareTo("Normal") != 0 || equipoIA[pokemonencombateIA].drenadoras || equipoIA[pokemonencombateIA].atrapado > 0) {
            PokemonGUI.estadoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipoIA[pokemonencombateIA].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
            PokemonGUI.estadoequipoIA[pokemonencombateIA].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipoIA[pokemonencombateIA].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
        } else {
            PokemonGUI.estadoequipoIA[pokemonencombateIA].setIcon(null);
            PokemonGUI.estadoIA.setIcon(null);
        }
        if (!equipoIA[l].shiny) {
            PokemonGUI.labelfotoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Frente/" + Juego.equipoIA[l].codigo + ".png")).getImage()).getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
        } else {
            PokemonGUI.labelfotoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Shiny Frente/" + Juego.equipoIA[l].codigo + ".png")).getImage()).getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
        }
//labelsaludIA.setText((int) equipoIA[l].salud + "/" + (int) Juego.equipoIA[l].saludmax);
        saludIA.setVisible(true);
        labelnombreIA.setText(Juego.equipoIA[l].nombre);
        //PokemonGUI.BarraDeVidaIA();
        if (n == 0) {
            PokemonGUI.cambiapokemonIAmuerto = true;
        }
    }

    static boolean ChequeoPokemonVivos(Pokemon[] equipo) {
        boolean salida = false;
        for (int i = 0; i < equipo.length; i++) {
            Pokemon pokemon = equipo[i];
            if (!salida) {
                if (pokemon.Vivo()) {
                    salida = true;
                }
            }
        }
        return salida;
    }

    static void TransformacionDittoJugador() {
        if (equipojugador[pokemonencombatejugador].nombre.compareTo("Ditto") == 0) {
            for (int l = 0; l < PokemonGUI.botonataques.length; l++) {
                PokemonGUI.botonataques[l].setText(ataquejugador[pokemonencombatejugador][l].nombre + " " + ataquejugador[pokemonencombatejugador][l].pp + "/" + ataquejugador[pokemonencombatejugador][l].ppmax);
                PokemonGUI.fotoataquejugador[l].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + Juego.ataquejugador[pokemonencombatejugador][l].tipo + ".gif"));
            }
            PokemonGUI.labelfotojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Espalda/" + Juego.equipojugador[pokemonencombatejugador].codigo + ".png")).getImage()).getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)));
            PokemonGUI.tipo1jugador[pokemonencombatejugador].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + equipojugador[pokemonencombatejugador].tipo1 + ".gif"));
            if (equipojugador[pokemonencombatejugador].tipo2.compareTo("n*n*") != 0) {
                PokemonGUI.tipo2jugador[pokemonencombatejugador].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + equipojugador[pokemonencombatejugador].tipo2 + ".gif"));
            }
            System.out.println(equipojugador[pokemonencombatejugador].tipo1 + "   " + equipojugador[pokemonencombatejugador].tipo2);
        }
    }

    static void TransformacionDittoIA() {
        if (equipoIA[pokemonencombateIA].nombre.compareTo("Ditto") == 0) {
            PokemonGUI.labelfotoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Frente/" + Juego.equipoIA[pokemonencombateIA].codigo + ".png")).getImage()).getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)));
        }
    }
}
