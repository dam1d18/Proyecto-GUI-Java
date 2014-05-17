package pokemonobjetos;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static pokemonobjetos.Juego.*;
import static pokemonobjetos.Lecturas.*;

/**
 *
 * @author Victor
 */
public class PokemonGUI extends JFrame {

    static ObjectContainer bdpokemon;
    static ObjectContainer bdataque;
    public static JButton botoniniciarjuego, botoncambiarpokemon, botoncarrera = new JButton(), botoncombatelibre = new JButton(), botonKanto, botonJohto, botonHoenn, botonSinnoh, botonTeselia, botonsiguienteentrenador = new JButton(),
            botoniraligapokemon = new JButton(), botonjefealtomando = new JButton(), botonatacar = new JButton(), botonestadisticasvictorias = new JButton(),
            botonhallfama = new JButton(), botonestadisticasmodolibre = new JButton(), botontablatipos = new JButton(),
            botonvolveratrasdesdetabla = new JButton(), botonvolveratrasdesdehallfama = new JButton(),
            botonvolveratrasdesdeestadisticas = new JButton();
    public static JButton[] botontipocombate, botonelegirpokemon, botonpokemonelegidosJugador, botonpokemonelegidosIA, botonataques, botonlideres, botonaltomando, botonlistaestadisticas;
    public static JLabel label = new JLabel(), labelfotojugador = new JLabel(), labelfotoIA = new JLabel(), labelpsjugador = new JLabel(), labelpsIA = new JLabel(),
            labelnombreentrenadorjugador = new JLabel(), labelnombreentrenadorIA = new JLabel(), labelfotoentrenadorIA = new JLabel(),
            labelsaludjugador = new JLabel(), labelsaludIA = new JLabel(), labelnombrejugador = new JLabel(),
            labelnombreIA = new JLabel(), fototitulo = new JLabel(), fotohallfama = new JLabel(), paneltipos = new JLabel(), paneltiposcalculo = new JLabel();
    public static JLabel[] lblestadisticas, lblmultiplicador;
    public static JPanel contenedorlistapokemon = new JPanel(), contenedorpokemonsjugador = new JPanel(),
            contenedorpokemonsIA = new JPanel(), contenedorataques = new JPanel(),
            contenedorimagenjugador = new JPanel(), contenedorimagenIA = new JPanel(),
            panelestadojugador = new JPanel(), panelestadoIA = new JPanel(),
            panelcaracteristicas = new JPanel(), panelestadisticas = new JPanel(),
            contenedorlistaestadisticas = new JPanel();
    public static JPanel saludjugador = new JPanel(), saludIA = new JPanel();
    public static JPanel[] saludequipojugador, saludequipoIA;
    public static JLabel[] tipo1, tipo2, tipo1jugador, tipo2jugador, tipo1IA, tipo2IA, fotoataquejugador, estadoequipojugador, estadoequipoIA,
            lblsaludequipojugador, lblsaludequipoIA, estrellajugador, estrellaIA, tipo1estadisticas, tipo2estadisticas;
    public static JLabel fotoataque1, lblataque1, fotoataque2, lblataque2, fotoataque3, lblataque3, fotoataque4, lblataque4,
            ppataque1, ppataque2, ppataque3, ppataque4, estadojugador, estadoIA;
    private static JScrollPane scrollpokemonlista, scrollmensajes, scrolllistaestadisticas;
    public static JTextArea Mensajes = new JTextArea();
    private static String fpokemon = "Juego/Pokemon.txt";
    static Image iconcursormenus = Toolkit.getDefaultToolkit().createImage("Recursos/Cursor/Cursor.png");
    public static Cursor cursormenus = Toolkit.getDefaultToolkit().createCustomCursor(iconcursormenus, new Point(0, 0), "cursormenus");
    public static Image iconcursorpokeball = Toolkit.getDefaultToolkit().createImage("Recursos/Cursor/Pokeball.png");
    public static Cursor cursorpokeball = Toolkit.getDefaultToolkit().createCustomCursor(iconcursorpokeball, new Point(15, 15), "cursorpokeball");
    public static int tipocombate;
    private int contadorpokemonselegidos = 0;
    private static int[] PokemonYaElegidosJugador, PokemonYaElegidosIA;
    private static boolean menuelegirpokemon, menuinicial;
    public static boolean cambiarpokemon = true, atacaiaporcambiarpokemonjugador = false, cambiapokemonIAmuerto = false;
    private static Pokemon[] pokemon;
    public static JPanelCombate panelcombate;
    public static JPanelFondo panelfondo;
    public static int pokemonencombatejugador, pokemonencombateIA;
    public static int ataqueusadojugador, ataqueusadoIA;
    public static int ronda = 1;
    public static int probabilidadshiny = 15;
    public static int tiempo = 4;
    public static boolean modocarrera, kanto = false, johto = false, hoenn = false, sinnoh = false, teselia = false,
            listeneratacar = false, iniciocombate = true, volvermodojuego = false, volverpanel = false,
            volverhallfama = false, volverestadisticas = false;
    private JComboBox ComboTipo1, ComboTipo2;
    public static int entrenadorliga = 1;
    public static int MIN = 0, MAX = 100, INIT = 1;
    public static JSlider sliderecualizador = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
    public static PokemonGUI pokemongui;
    public static JLayerLoop MusicIntro;
    public static JLayerLoop MusicMenus;
    public static JLayerLoop MusicMenuLider;
    public static JLayerLoop MusicMenuAltoMando;
    public static JLayerLoop MusicCombate;
    public static JLayerLoop MusicFinCombate;
    public static JLayerLoop MusicHallFama;
    public static Ecualizador controladorsonido;

    public PokemonGUI() {
        setSize(1280, 799);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        panelfondo = new JPanelFondo();
        panelfondo.CargarImagen("Intro.png");
        setContentPane(panelfondo);
        setIconImage(new ImageIcon(getClass().getResource("Icono/Pokeball.png")).getImage());
        Centrar(this);
        ListenerVentana();
        setCursor(cursormenus);
        botoniniciarjuego = new JButton("Comenzar juego");
        add(botoniniciarjuego);
        setSize(1280, 800);
        botoniniciarjuego.setBounds(550, 170, 150, 75);
        botoniniciarjuego.setFocusable(false);
        botoniniciarjuego.setBackground(Color.LIGHT_GRAY);
        botoniniciarjuego.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MenuElegirModoJuego();
            }
        });
    }

    public void ListenerVentana() {
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent ev) {
                int n = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas salir?, el juego se cerrará", "Salir", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    CerrarDBOO();
                    System.exit(0);
                }
            }
        });
    }

    public static void ConectarPokemon(String database) {
        bdpokemon = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), database);
    }

    public static void ConectarAtaque(String database) {
        bdataque = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), database);
    }

    public void CrearEcualizador() {
        sliderecualizador.setMajorTickSpacing(10);
        sliderecualizador.setMinorTickSpacing(5);
        sliderecualizador.setPaintTicks(true);
        sliderecualizador.setPaintLabels(true);
        sliderecualizador.setBorder(BorderFactory.createTitledBorder(null, "Ajustar Sonido", TitledBorder.CENTER, 0));
        sliderecualizador.setBackground(Color.green);
        sliderecualizador.setBounds(480, 760, 320, 60);
        sliderecualizador.setValue(50);
        add(sliderecualizador);
        sliderecualizador.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent ce) {
                controladorsonido.setVolume(sliderecualizador.getValue());
            }
        });
    }

    public void MenuElegirModoJuego() {
        panelfondo.CargarImagen("1.png");
        if (!volvermodojuego) {
            CrearEcualizador();
            sliderecualizador.setVisible(false);
            botoniniciarjuego.setVisible(false);
            fototitulo.setVisible(false);
            botoncarrera.setText("Modo Carrera");
            botoncarrera.setFocusable(false);
            botoncarrera.setBounds(535, 350, 220, 50);
            add(botoncarrera);

            botonhallfama.setBounds(535, 420, 220, 50);
            botonhallfama.setFocusable(false);
            botonhallfama.setText("Hall de la Fama");
            add(botonhallfama);

            botoncombatelibre.setBounds(535, 490, 220, 50);
            botoncombatelibre.setFocusable(false);
            botoncombatelibre.setText("Combate Libre");
            add(botoncombatelibre);

            botonestadisticasmodolibre.setBounds(535, 560, 220, 50);
            botonestadisticasmodolibre.setFocusable(false);
            botonestadisticasmodolibre.setText("Victorias Modo Libre");
            add(botonestadisticasmodolibre);

            botontablatipos.setBounds(535, 630, 220, 50);
            botontablatipos.setFocusable(false);
            botontablatipos.setText("Tabla de Tipos");
            add(botontablatipos);

            botoncarrera.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    modocarrera = true;
                    MenuElegirGeneracion();
                }
            });

            botonhallfama.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    menuinicial = true;
                    botoncarrera.setVisible(false);
                    botonhallfama.setVisible(false);
                    botoncombatelibre.setVisible(false);
                    botonestadisticasmodolibre.setVisible(false);
                    MenuElegirGeneracion();
                }
            });

            botoncombatelibre.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    modocarrera = false;
                    MenuTipoCombate();
                }
            });

            botonestadisticasmodolibre.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    menuinicial = true;
                    botoncarrera.setVisible(false);
                    botonhallfama.setVisible(false);
                    botoncombatelibre.setVisible(false);
                    botonestadisticasmodolibre.setVisible(false);
                    botontablatipos.setVisible(false);
                    File dir = new File(Lecturas.festadisticasvicrandom);
                    if (dir.exists()) {
                        try {
                            MenuEstadisticasVictorias();
                        } catch (IOException ex) {
                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún Pokémon ha ganado nunca, tienes que jugar más");
                    }
                }
            });

            botontablatipos.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    botoncarrera.setVisible(false);
                    botonhallfama.setVisible(false);
                    botoncombatelibre.setVisible(false);
                    botonestadisticasmodolibre.setVisible(false);
                    botontablatipos.setVisible(false);
                    TablaTipos();
                }
            });
        } else {
            botoncarrera.setVisible(true);
            botonhallfama.setVisible(true);
            botoncombatelibre.setVisible(true);
            botonestadisticasmodolibre.setVisible(true);
            botontablatipos.setVisible(true);
        }
        setSize(1280, 801);
        setSize(1280, 800);
        Centrar(this);
        volvermodojuego = true;
    }

    public void TablaTipos() {
        if (!volverpanel) {
            paneltipos.setBounds(6, 10, 700, 665);
            paneltipos.setVisible(true);
            paneltipos.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/Tabla.png"));
            add(paneltipos);
            botonvolveratrasdesdetabla.setBounds(400, 680, 220, 50);
            add(botonvolveratrasdesdetabla);
            botonvolveratrasdesdetabla.setText("Volver atrás");
            botonvolveratrasdesdetabla.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    paneltipos.setVisible(false);
                    botonvolveratrasdesdetabla.setVisible(false);
                    ComboTipo1.setVisible(false);
                    ComboTipo2.setVisible(false);
                    paneltiposcalculo.setVisible(false);
                    for (int i = 0; i < lblmultiplicador.length; i++) {
                        lblmultiplicador[i].setVisible(false);
                    }
                    MenuElegirModoJuego();
                }
            });
            //paneltiposcalculo.setBounds(null);
            ComboTipo1 = new JComboBox();
            ComboTipo2 = new JComboBox();
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/nnnn.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Acero.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Acero.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Agua.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Agua.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Bicho.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Bicho.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Dragon.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Dragon.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Electrico.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Electrico.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Fantasma.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Fantasma.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Fuego.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Fuego.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Hada.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Hada.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Hielo.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Hielo.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Lucha.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Lucha.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Normal.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Normal.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Planta.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Planta.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Psiquico.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Psiquico.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Roca.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Roca.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Siniestro.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Siniestro.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Tierra.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Tierra.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Veneno.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Veneno.gif"));
            ComboTipo1.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Volador.gif"));
            ComboTipo2.addItem(new ImageIcon("Recursos/Imagenes/Tipos/Volador.gif"));
            ComboTipo1.setMaximumRowCount(ComboTipo1.getModel().getSize());
            ComboTipo2.setMaximumRowCount(ComboTipo2.getModel().getSize());
            ComboTipo1.setBounds(730, 10, 80, 30);
            ComboTipo2.setBounds(939, 10, 80, 30);
            add(ComboTipo1);
            add(ComboTipo2);
            paneltiposcalculo.setBounds(820, 10, 109, 665);
            paneltiposcalculo.setVisible(true);
            paneltiposcalculo.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/TablaCalculo.png"));
            add(paneltiposcalculo);
            lblmultiplicador = new JLabel[18];
            lblmultiplicador[0] = new JLabel();
            lblmultiplicador[0].setBounds(75, 3, 30, 31);
            paneltiposcalculo.add(lblmultiplicador[0]);
            for (int i = 1; i < lblmultiplicador.length; i++) {
                lblmultiplicador[i] = new JLabel();
                lblmultiplicador[i].setBounds(75, 37 * i, 30, 31);
                paneltiposcalculo.add(lblmultiplicador[i]);
            }
        } else {
            paneltipos.setVisible(true);
            botonvolveratrasdesdetabla.setVisible(true);
            ComboTipo1.setVisible(true);
            ComboTipo2.setVisible(true);
            paneltiposcalculo.setVisible(true);
            for (int i = 0; i < lblmultiplicador.length; i++) {
                lblmultiplicador[i].setVisible(true);
            }
        }
        setSize(1040, 768);
        Centrar(this);
        ComboTipo1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo1 = String.valueOf(ComboTipo1.getSelectedItem());
                tipo1 = String.valueOf(tipo1).substring(24, tipo1.length() - 4);
                String tipo2 = String.valueOf(ComboTipo2.getSelectedItem());
                tipo2 = String.valueOf(tipo2).substring(24, tipo2.length() - 4);
                if (tipo1.compareTo(tipo2) != 0) {
                    try {
                        ComprobacionDebilidades(tipo1, tipo2);
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Un pokémon no puede tener 2 veces el mismo tipo!");
                    ComboTipo2.setSelectedIndex(0);
                }
            }
        });
        ComboTipo2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo1 = String.valueOf(ComboTipo1.getSelectedItem());
                tipo1 = String.valueOf(tipo1).substring(24, tipo1.length() - 4);
                String tipo2 = String.valueOf(ComboTipo2.getSelectedItem());
                tipo2 = String.valueOf(tipo2).substring(24, tipo2.length() - 4);
                if (tipo1.compareTo(tipo2) != 0) {
                    try {
                        ComprobacionDebilidades(tipo1, tipo2);
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Un pokémon no puede tener 2 veces el mismo tipo!");
                    ComboTipo2.setSelectedIndex(0);
                }
            }
        });

        volverpanel = true;
    }

    public void ComprobacionDebilidades(String tipo1, String tipo2) throws IOException {
        String tipos[][];
        int i;
        int resultado;
        boolean continuar;
        if (tipo2.compareTo("nnnn") == 0) {
            tipo2 = "n*n*";
        }
        for (int j = 0; j < 18; j++) {
            tipos = Lecturas.DatosTipos(ObtenerTipos(j));
            resultado = 3;
            i = 0;
            continuar = true;
            while (i < tipos[0].length && continuar) {
                if (tipos[0][i].compareTo(tipo1) == 0) {
                    resultado++;
                    continuar = false;
                }
                i++;
            }
            i = 0;
            while (i < tipos[1].length && continuar) {
                if (tipos[1][i].compareTo(tipo1) == 0) {
                    resultado--;
                    continuar = false;
                }
                i++;
            }
            i = 0;
            while (i < tipos[2].length && continuar) {
                if (tipos[2][i].compareTo(tipo1) == 0) {
                    resultado = 0;
                    continuar = false;
                }
                i++;
            }
            if (resultado != 0) {
                continuar = true;
                i = 0;
                while (i < tipos[0].length && continuar) {
                    if (tipos[0][i].compareTo(tipo2) == 0) {
                        resultado++;
                        continuar = false;
                    }
                    i++;
                }
                i = 0;
                while (i < tipos[1].length && continuar) {
                    if (tipos[1][i].compareTo(tipo2) == 0) {
                        resultado--;
                        continuar = false;
                    }
                    i++;
                }
                i = 0;
                while (i < tipos[2].length && continuar) {
                    if (tipos[2][i].compareTo(tipo2) == 0) {
                        resultado = 0;
                        continuar = false;
                    }
                    i++;
                }
            }
            switch (resultado) {
                case 0:
                    lblmultiplicador[j].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/cero.png"));
                    break;
                case 1:
                    lblmultiplicador[j].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/cuarto.png"));
                    break;
                case 2:
                    lblmultiplicador[j].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/mitad.png"));
                    break;
                case 3:
                    lblmultiplicador[j].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/normal.png"));
                    break;
                case 4:
                    lblmultiplicador[j].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/doble.png"));
                    break;
                case 5:
                    lblmultiplicador[j].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/cuadruple.png"));
                    break;
            }
        }
    }

    public String ObtenerTipos(int i) {
        String tipo = "";
        switch (i) {
            case 0:
                tipo = "Acero";
                break;
            case 1:
                tipo = "Agua";
                break;
            case 2:
                tipo = "Bicho";
                break;
            case 3:
                tipo = "Dragon";
                break;
            case 4:
                tipo = "Electrico";
                break;
            case 5:
                tipo = "Fantasma";
                break;
            case 6:
                tipo = "Fuego";
                break;
            case 7:
                tipo = "Hada";
                break;
            case 8:
                tipo = "Hielo";
                break;
            case 9:
                tipo = "Lucha";
                break;
            case 10:
                tipo = "Normal";
                break;
            case 11:
                tipo = "Planta";
                break;
            case 12:
                tipo = "Psiquico";
                break;
            case 13:
                tipo = "Roca";
                break;
            case 14:
                tipo = "Siniestro";
                break;
            case 15:
                tipo = "Tierra";
                break;
            case 16:
                tipo = "Veneno";
                break;
            case 17:
                tipo = "Volador";
                break;
            default:
                break;
        }
        return tipo;
    }

    public void MenuElegirGeneracion() {
        setSize(610, 300);
        Centrar(this);
        kanto = false;
        johto = false;
        hoenn = false;

        botoncarrera.setVisible(false);
        botonhallfama.setVisible(false);
        botoncombatelibre.setVisible(false);
        botonestadisticasmodolibre.setVisible(false);
        botontablatipos.setVisible(false);
        botonKanto = new JButton();
        botonKanto.setBounds(150, 10, 270, 40);
        botonKanto.setFocusable(false);
        botonKanto.setText("Kanto");
        add(botonKanto);

        botonJohto = new JButton();
        botonJohto.setBounds(150, 60, 270, 40);
        botonJohto.setFocusable(false);

        botonJohto.setText("Johto");
        add(botonJohto);
        botonHoenn = new JButton();
        botonHoenn.setBounds(150, 110, 270, 40);
        botonHoenn.setFocusable(false);

        botonHoenn.setText("Hoenn");
        add(botonHoenn);
        botonSinnoh = new JButton();
        botonSinnoh.setBounds(150, 160, 270, 40);
        botonSinnoh.setFocusable(false);

        botonSinnoh.setText("Sinnoh");
        add(botonSinnoh);
        botonTeselia = new JButton();
        botonTeselia.setBounds(150, 210, 270, 40);
        botonTeselia.setFocusable(false);

        botonTeselia.setText("Teselia");
        add(botonTeselia);
        tipocombate = 3;
        Juego.CrearEquipos();
        botonKanto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                kanto = true;
                johto = false;
                hoenn = false;
                sinnoh = false;
                teselia = false;
                if (!menuinicial) {
                    try {
                        ElegirPokemons();
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    File dir = new File(Lecturas.fhallfamaKanto);
                    if (dir.exists()) {
                        botonKanto.setVisible(false);
                        botonJohto.setVisible(false);
                        botonHoenn.setVisible(false);
                        botonSinnoh.setVisible(false);
                        botonTeselia.setVisible(false);
                        try {
                            MenuHallFama();
                        } catch (IOException ex) {
                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún Pokémon ha logrado tamaña hazaña en Kanto");
                    }
                }
            }
        });
        botonJohto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                kanto = false;
                johto = true;
                hoenn = false;
                sinnoh = false;
                teselia = false;
                if (!menuinicial) {
                    try {
                        ElegirPokemons();
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    File dir = new File(Lecturas.fhallfamaJohto);
                    if (dir.exists()) {
                        botonKanto.setVisible(false);
                        botonJohto.setVisible(false);
                        botonHoenn.setVisible(false);
                        botonSinnoh.setVisible(false);
                        botonTeselia.setVisible(false);
                        try {
                            MenuHallFama();
                        } catch (IOException ex) {
                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún Pokémon ha logrado tamaña hazaña en Johto");
                    }
                }
            }
        });
        botonHoenn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                kanto = false;
                johto = false;
                hoenn = true;
                sinnoh = false;
                teselia = false;
                if (!menuinicial) {
                    try {
                        ElegirPokemons();
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    File dir = new File(Lecturas.fhallfamaHoenn);
                    if (dir.exists()) {
                        botonKanto.setVisible(false);
                        botonJohto.setVisible(false);
                        botonHoenn.setVisible(false);
                        botonSinnoh.setVisible(false);
                        botonTeselia.setVisible(false);
                        try {
                            MenuHallFama();
                        } catch (IOException ex) {
                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún Pokémon ha logrado tamaña hazaña en Hoenn");
                    }
                }
            }
        });
        botonSinnoh.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                kanto = false;
                johto = false;
                hoenn = false;
                sinnoh = true;
                teselia = false;
                if (!menuinicial) {
                    try {
                        ElegirPokemons();
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    File dir = new File(Lecturas.fhallfamaSinnoh);
                    if (dir.exists()) {
                        botonKanto.setVisible(false);
                        botonJohto.setVisible(false);
                        botonHoenn.setVisible(false);
                        botonSinnoh.setVisible(false);
                        botonTeselia.setVisible(false);
                        try {
                            MenuHallFama();
                        } catch (IOException ex) {
                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún Pokémon ha logrado tamaña hazaña en Sinnoh");
                    }
                }
            }
        });
        botonTeselia.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                kanto = false;
                johto = false;
                hoenn = false;
                sinnoh = false;
                teselia = true;
                if (!menuinicial) {
                    try {
                        ElegirPokemons();
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    File dir = new File(Lecturas.fhallfamaTeselia);
                    if (dir.exists()) {
                        botonKanto.setVisible(false);
                        botonJohto.setVisible(false);
                        botonHoenn.setVisible(false);
                        botonSinnoh.setVisible(false);
                        botonTeselia.setVisible(false);
                        try {
                            MenuHallFama();
                        } catch (IOException ex) {
                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún Pokémon ha logrado tamaña hazaña en Teselia");
                    }
                }
            }
        });
    }

    public String[] TipoCombate() {
        String[] str = new String[3];
        str[0] = "1vs1";
        str[1] = "3vs3";
        str[2] = "6vs6";
        return str;
    }

    public void MenuTipoCombate() {
        botoncarrera.setVisible(false);
        botonhallfama.setVisible(false);
        botoncombatelibre.setVisible(false);
        botonestadisticasmodolibre.setVisible(false);
        botoniniciarjuego.setVisible(false);
        botontablatipos.setVisible(false);
        setSize(551, 200);
        Centrar(this);
        label.setText("¿Qué tipo de combate desea?");
        label.setBounds(180, 30, 300, 20);
        add(label);
        String[] cadena = TipoCombate();
        botontipocombate = new JButton[3];
        for (int i = 0; i < botontipocombate.length; i++) {
            botontipocombate[i] = new JButton();
            botontipocombate[i].setFocusable(false);
            botontipocombate[i].setBounds(110 * (i + 1), 80, 100, 50);
            botontipocombate[i].setText(cadena[i]);
            add(botontipocombate[i]);
        }
        for (int i = 0; i < botontipocombate.length; i++) {
            botontipocombate[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < botontipocombate.length; i++) {
                        if (e.getSource() == botontipocombate[i]) {
                            tipocombate = i + 1;
                            Juego.CrearEquipos();
                            try {
                                ElegirPokemons();
                            } catch (IOException ex) {
                                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            });
        }
    }

    public void ElegirPokemons() throws IOException {
        panelfondo.CargarImagen("ElegirPokemon.png");
        MusicIntro.stop();
        MusicMenus = new JLayerLoop("Menus.mp3", true);
        MusicMenus.play();
        menuinicial = false;
        menuelegirpokemon = true;
        setSize(1280, 850);
        setResizable(false);
        Centrar(this);
        if (modocarrera) {
            botonKanto.setVisible(false);
            botonJohto.setVisible(false);
            botonHoenn.setVisible(false);
            botonSinnoh.setVisible(false);
            botonTeselia.setVisible(false);
        } else {
            for (int i = 0; i < botontipocombate.length; i++) {
                botontipocombate[i].setVisible(false);
            }
        }
        label.setVisible(false);
        panelcaracteristicas.setBounds(12, 555, 242, 225);
        panelcaracteristicas.setVisible(false);
        panelcaracteristicas.setLayout(null);
        panelestadisticas.setBounds(0, 0, 250, 100);
        panelestadisticas.setLayout(new GridLayout(3, 4));
        //panelcaracteristicas.setBackground(Color.red);
        lblestadisticas = new JLabel[12];
        for (int i = 0; i < lblestadisticas.length; i++) {
            lblestadisticas[i] = new JLabel();
            panelestadisticas.add(lblestadisticas[i]);
        }
        fotoataque1 = new JLabel();
        fotoataque1.setBounds(0, 100, 70, 20);
        panelcaracteristicas.add(fotoataque1);

        fotoataque2 = new JLabel();
        fotoataque2.setBounds(0, 130, 70, 20);
        panelcaracteristicas.add(fotoataque2);

        fotoataque3 = new JLabel();
        fotoataque3.setBounds(0, 160, 70, 20);
        panelcaracteristicas.add(fotoataque3);

        fotoataque4 = new JLabel();
        fotoataque4.setBounds(0, 190, 70, 20);
        panelcaracteristicas.add(fotoataque4);

        lblataque1 = new JLabel();
        lblataque1.setBounds(63, 100, 170, 20);
        panelcaracteristicas.add(lblataque1);

        lblataque2 = new JLabel();
        lblataque2.setBounds(63, 130, 170, 20);
        panelcaracteristicas.add(lblataque2);

        lblataque3 = new JLabel();
        lblataque3.setBounds(63, 160, 170, 20);
        panelcaracteristicas.add(lblataque3);

        lblataque4 = new JLabel();
        lblataque4.setBounds(63, 190, 170, 20);
        panelcaracteristicas.add(lblataque4);

        ppataque1 = new JLabel();
        ppataque1.setBounds(185, 100, 40, 20);
        panelcaracteristicas.add(ppataque1);

        ppataque2 = new JLabel();
        ppataque2.setBounds(185, 130, 40, 20);
        panelcaracteristicas.add(ppataque2);

        ppataque3 = new JLabel();
        ppataque3.setBounds(185, 160, 40, 20);
        panelcaracteristicas.add(ppataque3);

        ppataque4 = new JLabel();
        ppataque4.setBounds(185, 190, 40, 20);
        panelcaracteristicas.add(ppataque4);

        panelcaracteristicas.add(panelestadisticas);
        panelestadisticas.setOpaque(false);
        panelcaracteristicas.setOpaque(false);
        add(panelcaracteristicas);
        botonelegirpokemon = new JButton[Lecturas.NumeroRegistros(fpokemon)];
        tipo1 = new JLabel[Lecturas.NumeroRegistros(fpokemon)];
        tipo2 = new JLabel[Lecturas.NumeroRegistros(fpokemon)];
        int l = 0;
        contenedorlistapokemon.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        PokemonYaElegidosJugador = new int[Juego.npokemonequipo];
        PokemonYaElegidosIA = new int[Juego.npokemonequipo];
        pokemon = Pokemon.ObtenerTodosPokemons(bdpokemon);
        boolean existe = false;
        String[][] hallpokemon = null;
        if (modocarrera) {
            File dir;
            if (PokemonGUI.kanto) {
                dir = new File(Lecturas.fhallfamaKanto);
            } else if (PokemonGUI.johto) {
                dir = new File(Lecturas.fhallfamaJohto);
            } else if (PokemonGUI.hoenn) {
                dir = new File(Lecturas.fhallfamaHoenn);
            } else if (PokemonGUI.sinnoh) {
                dir = new File(Lecturas.fhallfamaSinnoh);
            } else {
                dir = new File(Lecturas.fhallfamaTeselia);
            }
            if (dir.exists()) {
                hallpokemon = Lecturas.MostrarHallFamaOrdenado();
                existe = true;
            }
        }
        int cont;
        boolean salir;
        for (int i = 0; i < botonelegirpokemon.length; i++) {
            //System.out.println(i);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            if (i % 3 == 0) {
                l = 0;
            }
            c.gridx = l;
            c.gridy = i - l;
            botonelegirpokemon[i] = new JButton();
            botonelegirpokemon[i].setText((i + 1) + " - " + pokemon[i].nombre);
            //botonelegirpokemon[i].setFont(new Font("Helvetica", Font.BOLD, 15));

            botonelegirpokemon[i].setLayout(new BoxLayout(botonelegirpokemon[i], BoxLayout.Y_AXIS));
            botonelegirpokemon[i].add(Box.createRigidArea(new Dimension(5, 18)));
            botonelegirpokemon[i].setFocusable(false);
            tipo1[i] = new JLabel();
            //tipo1[i].setBackground(Color.red);
            tipo1[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + pokemon[i].tipo1 + ".gif"));
            botonelegirpokemon[i].add(tipo1[i]);
            botonelegirpokemon[i].add(Box.createRigidArea(new Dimension(5, 10)));
            tipo2[i] = new JLabel();
            //tipo2[i].setBackground(Color.red);
            if (pokemon[i].tipo2.compareTo("n*n*") != 0) {
                tipo2[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + pokemon[i].tipo2 + ".gif"));
            } else {
                botonelegirpokemon[i].add(Box.createRigidArea(new Dimension(5, 18)));
            }
            botonelegirpokemon[i].add(tipo2[i]);

            botonelegirpokemon[i].add(Box.createRigidArea(new Dimension(5, 18)));
            botonelegirpokemon[i].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + (i + 1) + ".png"));
            if (modocarrera && existe) {
                cont = 0;
                salir = false;
                while (cont < hallpokemon.length && !salir) {
                    if (Integer.parseInt(hallpokemon[cont][0]) == pokemon[i].codigo) {
                        botonelegirpokemon[i].setBackground(Color.green);
                        salir = true;
                    }
                    cont++;
                }
            }
            botonelegirpokemon[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean continuar = true, continuarIA = true;
                    int j = 0;
                    for (int i = 0; i < botonelegirpokemon.length; i++) {
                        if (e.getSource() == botonelegirpokemon[i]) {
                            while (j < PokemonYaElegidosJugador.length && continuar) {
                                if (PokemonYaElegidosJugador[j] == i + 1) {
                                    continuar = false;
                                }
                                j++;
                            }
                            if (continuar) {
                                botonelegirpokemon[i].setBackground(Color.yellow);
                                Pokemon p = pokemon[i];
                                p = new Pokemon(p.codigo, p.nombre, p.salud, p.peso, p.velocidad, p.ataque, p.defensa, p.ataqueesp, p.defensaesp, p.tipo1, p.tipo2, p.ataque1, p.ataque2, p.ataque3, p.ataque4);
                                p.naturaleza = Pokemon.EleccionNaturaleza();
                                p.equijugador = true;
                                Juego.equipojugador[contadorpokemonselegidos] = p;
                                botonpokemonelegidosJugador[contadorpokemonselegidos].setLayout(null);
                                botonpokemonelegidosJugador[contadorpokemonselegidos].setVisible(true);
                                botonpokemonelegidosJugador[contadorpokemonselegidos].setText(p.nombre);
                                botonpokemonelegidosJugador[contadorpokemonselegidos].setHorizontalTextPosition(SwingConstants.RIGHT);
                                botonpokemonelegidosJugador[contadorpokemonselegidos].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                                botonpokemonelegidosJugador[contadorpokemonselegidos].setFocusable(false);

                                tipo1jugador[contadorpokemonselegidos] = new JLabel();
                                tipo1jugador[contadorpokemonselegidos].setBounds(5, 15, 50, 20);
                                tipo1jugador[contadorpokemonselegidos].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + pokemon[i].tipo1 + ".gif"));
                                botonpokemonelegidosJugador[contadorpokemonselegidos].add(tipo1jugador[contadorpokemonselegidos]);
                                tipo2jugador[contadorpokemonselegidos] = new JLabel();
                                tipo2jugador[contadorpokemonselegidos].setBounds(5, 45, 50, 20);
                                if (pokemon[i].tipo2.compareTo("n*n*") != 0) {
                                    tipo2jugador[contadorpokemonselegidos].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + pokemon[i].tipo2 + ".gif"));
                                }
                                botonpokemonelegidosJugador[contadorpokemonselegidos].add(tipo2jugador[contadorpokemonselegidos]);

                                botonpokemonelegidosJugador[contadorpokemonselegidos].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + p.codigo + ".png"));

                                estadoequipojugador[contadorpokemonselegidos] = new JLabel();
                                estadoequipojugador[contadorpokemonselegidos].setBounds(200, 7, 30, 30);
                                botonpokemonelegidosJugador[contadorpokemonselegidos].add(estadoequipojugador[contadorpokemonselegidos]);

                                lblsaludequipojugador[contadorpokemonselegidos] = new JLabel();
                                lblsaludequipojugador[contadorpokemonselegidos].setBounds(155, 48, 60, 20);
                                botonpokemonelegidosJugador[contadorpokemonselegidos].add(lblsaludequipojugador[contadorpokemonselegidos]);

                                PokemonYaElegidosJugador[contadorpokemonselegidos] = i + 1;

                                lblsaludequipojugador[contadorpokemonselegidos].setText("" + (int) Juego.equipojugador[contadorpokemonselegidos].salud + "/" + (int) Juego.equipojugador[contadorpokemonselegidos].saludmax);

                            } else {
                                Pokemon p = Pokemon.PokemonElegido(bdpokemon, i + 1);
                                JOptionPane.showMessageDialog(null, "¡" + p.nombre + " ya forma parte de tu equipo!");
                            }
                        }
                    }
                    if (continuar && !modocarrera) {
                        int eleccionIA = (int) (Math.random() * Lecturas.NumeroDePokemons());
                        do {
                            j = 0;
                            continuarIA = true;
                            while (j < PokemonYaElegidosIA.length && continuarIA) {
                                if (PokemonYaElegidosIA[j] == eleccionIA) {
                                    continuarIA = false;
                                }
                                j++;
                            }
                        } while (!continuarIA);
                        Pokemon p = pokemon[eleccionIA];
                        p.naturaleza = Pokemon.EleccionNaturaleza();
                        Juego.equipoIA[contadorpokemonselegidos] = p;

                        botonpokemonelegidosIA[contadorpokemonselegidos].setLayout(null);
                        botonpokemonelegidosIA[contadorpokemonselegidos].setText(p.nombre);
                        botonpokemonelegidosIA[contadorpokemonselegidos].setHorizontalTextPosition(SwingConstants.RIGHT);
                        botonpokemonelegidosIA[contadorpokemonselegidos].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                        botonpokemonelegidosIA[contadorpokemonselegidos].setFocusable(false);

                        tipo1IA[contadorpokemonselegidos] = new JLabel();
                        tipo1IA[contadorpokemonselegidos].setBounds(5, 15, 50, 20);
                        tipo1IA[contadorpokemonselegidos].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo1 + ".gif"));

                        botonpokemonelegidosIA[contadorpokemonselegidos].add(tipo1IA[contadorpokemonselegidos]);
                        tipo2IA[contadorpokemonselegidos] = new JLabel();
                        tipo2IA[contadorpokemonselegidos].setBounds(5, 45, 50, 20);
                        if (p.tipo2.compareTo("n*n*") != 0) {
                            tipo2IA[contadorpokemonselegidos].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo2 + ".gif"));
                        }
                        botonpokemonelegidosIA[contadorpokemonselegidos].add(tipo2IA[contadorpokemonselegidos]);

                        botonpokemonelegidosIA[contadorpokemonselegidos].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + p.codigo + ".png"));

                        estadoequipoIA[contadorpokemonselegidos] = new JLabel();
                        estadoequipoIA[contadorpokemonselegidos].setBounds(200, 7, 30, 30);
                        botonpokemonelegidosIA[contadorpokemonselegidos].add(estadoequipoIA[contadorpokemonselegidos]);

                        lblsaludequipoIA[contadorpokemonselegidos] = new JLabel();
                        lblsaludequipoIA[contadorpokemonselegidos].setBounds(155, 48, 60, 20);
                        botonpokemonelegidosIA[contadorpokemonselegidos].add(lblsaludequipoIA[contadorpokemonselegidos]);
                        lblsaludequipoIA[contadorpokemonselegidos].setText("" + (int) Juego.equipoIA[contadorpokemonselegidos].salud + "/" + (int) Juego.equipoIA[contadorpokemonselegidos].saludmax);

                        PokemonYaElegidosIA[contadorpokemonselegidos] = eleccionIA;

                    }
                    if (continuar) {
                        contadorpokemonselegidos++;
                    }
                    if (contadorpokemonselegidos == Juego.npokemonequipo) {
                        Confirmar();
                    }
                }
            });
            botonelegirpokemon[i].addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent evt) {
                    panelcaracteristicas.setVisible(true);
                    for (int i = 0; i < botonelegirpokemon.length; i++) {
                        if (evt.getSource() == botonelegirpokemon[i]) {
                            if (botonelegirpokemon[i].getBackground() != Color.yellow) {
                                if (botonelegirpokemon[i].getBackground() != Color.green) {
                                    botonelegirpokemon[i].setBackground(Color.cyan);
                                } else {
                                    botonelegirpokemon[i].setBackground(Color.lightGray);
                                }
                            }
                            EstadisticasMouseOver(i);
                        }
                    }
                }
            });
            botonelegirpokemon[i].addMouseListener(new MouseAdapter() {

                public void mouseExited(MouseEvent evt) {
                    for (int i = 0; i < botonelegirpokemon.length; i++) {
                        if (evt.getSource() == botonelegirpokemon[i]) {
                            if (botonelegirpokemon[i].getBackground() != Color.yellow) {
                                if (botonelegirpokemon[i].getBackground() != Color.lightGray) {
                                    EstadisticasExitMouse(i);
                                } else {
                                    botonelegirpokemon[i].setBackground(Color.green);
                                }
                            }
                        }
                    }
                }
            });
            contenedorlistapokemon.add(botonelegirpokemon[i], c);
            l++;
        }
        if (modocarrera) {
            DesactivarLegendarios();
        }
        tipo1jugador = new JLabel[Juego.npokemonequipo];
        tipo2jugador = new JLabel[Juego.npokemonequipo];
        tipo1IA = new JLabel[Juego.npokemonequipo];
        tipo2IA = new JLabel[Juego.npokemonequipo];
        scrollpokemonlista = new JScrollPane(contenedorlistapokemon);
        scrollpokemonlista.getVerticalScrollBar().setUnitIncrement(31);
        scrollpokemonlista.setBounds(280, 39, 990, 740);
        add(scrollpokemonlista);

        contenedorpokemonsjugador.setLayout(null);
        contenedorpokemonsjugador.setBounds(12, 39, 260, 510);
        contenedorpokemonsjugador.setOpaque(false);
        //contenedorpokemonsjugador.setBackground(Color.red);
        add(contenedorpokemonsjugador);

        botonpokemonelegidosJugador = new JButton[Juego.npokemonequipo];
        for (int i = 0; i < botonpokemonelegidosJugador.length; i++) {
            botonpokemonelegidosJugador[i] = new JButton();
            botonpokemonelegidosJugador[i].setBounds(0, 85 * i, 260, 85);
            botonpokemonelegidosJugador[i].setVisible(false);
            contenedorpokemonsjugador.add(botonpokemonelegidosJugador[i]);
        }

        estadoequipojugador = new JLabel[Juego.npokemonequipo];
        lblsaludequipojugador = new JLabel[Juego.npokemonequipo];
        saludequipojugador = new JPanel[Juego.npokemonequipo];
        estadoequipoIA = new JLabel[Juego.npokemonequipo];
        lblsaludequipoIA = new JLabel[Juego.npokemonequipo];
        saludequipoIA = new JPanel[Juego.npokemonequipo];
        contenedorpokemonsIA.setLayout(null);
        contenedorpokemonsIA.setBounds(1002, 39, 260, 510);
        contenedorpokemonsIA.setOpaque(false);
        //contenedorpokemonsIA.setBackground(Color.green);
        add(contenedorpokemonsIA);

        botonpokemonelegidosIA = new JButton[Juego.npokemonequipo];
        for (int i = 0;
                i < botonpokemonelegidosIA.length;
                i++) {
            botonpokemonelegidosIA[i] = new JButton();
            botonpokemonelegidosIA[i].setBounds(0, 85 * i, 260, 85);
            botonpokemonelegidosIA[i].setVisible(false);
            contenedorpokemonsIA.add(botonpokemonelegidosIA[i]);
        }
        ListenersBotonPokemonElegidosJugador();
    }

    public void DesactivarLegendarios() {
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
        int n = 0;
        File dir = new File(fichero);
        if (dir.exists()) {
            try {
                String[][] Poke = Lecturas.MostrarHallFamaOrdenado();
                n = Poke.length;
            } catch (IOException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean activartodo = false;
        int[] Desactivar = new int[0];
        int[] Tooltip = new int[0];
        if (n >= 500) {
            activartodo = true;
        } else if (n >= 400) {
            Desactivar = new int[4];
            Desactivar[0] = 386;
            Desactivar[1] = 491;
            Desactivar[2] = 493;
            Desactivar[3] = 649;

            Tooltip = new int[4];
            Tooltip[0] = 400 - n;
            Tooltip[1] = 400 - n;
            Tooltip[2] = 400 - n;
            Tooltip[3] = 400 - n;

        } else if (n >= 300) {
            Desactivar = new int[13];
            Desactivar[0] = 386;
            Desactivar[1] = 491;
            Desactivar[2] = 493;
            Desactivar[3] = 649;
            Desactivar[4] = 151;
            Desactivar[5] = 251;
            Desactivar[6] = 385;
            Desactivar[7] = 489;
            Desactivar[8] = 490;
            Desactivar[9] = 492;
            Desactivar[10] = 494;
            Desactivar[11] = 647;
            Desactivar[12] = 648;

            Tooltip = new int[13];
            Tooltip[0] = 500 - n;
            Tooltip[1] = 500 - n;
            Tooltip[2] = 500 - n;
            Tooltip[3] = 500 - n;
            Tooltip[4] = 400 - n;
            Tooltip[5] = 400 - n;
            Tooltip[6] = 400 - n;
            Tooltip[7] = 400 - n;
            Tooltip[8] = 400 - n;
            Tooltip[9] = 400 - n;
            Tooltip[10] = 400 - n;
            Tooltip[11] = 400 - n;
            Tooltip[12] = 400 - n;

        } else if (n >= 200) {
            Desactivar = new int[22];
            Desactivar[0] = 386;
            Desactivar[1] = 491;
            Desactivar[2] = 493;
            Desactivar[3] = 649;
            Desactivar[4] = 151;
            Desactivar[5] = 251;
            Desactivar[6] = 385;
            Desactivar[7] = 489;
            Desactivar[8] = 490;
            Desactivar[9] = 492;
            Desactivar[10] = 494;
            Desactivar[11] = 647;
            Desactivar[12] = 648;
            Desactivar[13] = 150;
            Desactivar[14] = 380;
            Desactivar[15] = 381;
            Desactivar[16] = 485;
            Desactivar[17] = 486;
            Desactivar[18] = 488;
            Desactivar[19] = 641;
            Desactivar[20] = 642;
            Desactivar[21] = 645;

            Tooltip = new int[22];
            Tooltip[0] = 500 - n;
            Tooltip[1] = 500 - n;
            Tooltip[2] = 500 - n;
            Tooltip[3] = 500 - n;
            Tooltip[4] = 400 - n;
            Tooltip[5] = 400 - n;
            Tooltip[6] = 400 - n;
            Tooltip[7] = 400 - n;
            Tooltip[8] = 400 - n;
            Tooltip[9] = 400 - n;
            Tooltip[10] = 400 - n;
            Tooltip[11] = 400 - n;
            Tooltip[12] = 400 - n;
            Tooltip[13] = 300 - n;
            Tooltip[14] = 300 - n;
            Tooltip[15] = 300 - n;
            Tooltip[16] = 300 - n;
            Tooltip[17] = 300 - n;
            Tooltip[18] = 300 - n;
            Tooltip[19] = 300 - n;
            Tooltip[20] = 300 - n;
            Tooltip[21] = 300 - n;
            Tooltip[22] = 300 - n;
        } else if (n >= 100) {
            Desactivar = new int[33];
            Desactivar[0] = 386;
            Desactivar[1] = 491;
            Desactivar[2] = 493;
            Desactivar[3] = 649;
            Desactivar[4] = 151;
            Desactivar[5] = 251;
            Desactivar[6] = 385;
            Desactivar[7] = 489;
            Desactivar[8] = 490;
            Desactivar[9] = 492;
            Desactivar[10] = 494;
            Desactivar[11] = 647;
            Desactivar[12] = 648;
            Desactivar[13] = 150;
            Desactivar[14] = 380;
            Desactivar[15] = 381;
            Desactivar[16] = 485;
            Desactivar[17] = 486;
            Desactivar[18] = 488;
            Desactivar[19] = 641;
            Desactivar[20] = 642;
            Desactivar[21] = 645;
            Desactivar[22] = 249;
            Desactivar[23] = 250;
            Desactivar[24] = 382;
            Desactivar[25] = 383;
            Desactivar[26] = 384;
            Desactivar[27] = 483;
            Desactivar[28] = 484;
            Desactivar[29] = 487;
            Desactivar[30] = 643;
            Desactivar[31] = 644;
            Desactivar[32] = 646;

            Tooltip = new int[33];
            Tooltip[0] = 500 - n;
            Tooltip[1] = 500 - n;
            Tooltip[2] = 500 - n;
            Tooltip[3] = 500 - n;
            Tooltip[4] = 400 - n;
            Tooltip[5] = 400 - n;
            Tooltip[6] = 400 - n;
            Tooltip[7] = 400 - n;
            Tooltip[8] = 400 - n;
            Tooltip[9] = 400 - n;
            Tooltip[10] = 400 - n;
            Tooltip[11] = 400 - n;
            Tooltip[12] = 400 - n;
            Tooltip[13] = 300 - n;
            Tooltip[14] = 300 - n;
            Tooltip[15] = 300 - n;
            Tooltip[16] = 300 - n;
            Tooltip[17] = 300 - n;
            Tooltip[18] = 300 - n;
            Tooltip[19] = 300 - n;
            Tooltip[20] = 300 - n;
            Tooltip[21] = 300 - n;
            Tooltip[22] = 200 - n;
            Tooltip[23] = 200 - n;
            Tooltip[24] = 200 - n;
            Tooltip[25] = 200 - n;
            Tooltip[26] = 200 - n;
            Tooltip[27] = 200 - n;
            Tooltip[28] = 200 - n;
            Tooltip[29] = 200 - n;
            Tooltip[30] = 200 - n;
            Tooltip[31] = 200 - n;
            Tooltip[32] = 200 - n;
        } else {
            Desactivar = new int[48];
            Desactivar[0] = 386;
            Desactivar[1] = 491;
            Desactivar[2] = 493;
            Desactivar[3] = 649;
            Desactivar[4] = 151;
            Desactivar[5] = 251;
            Desactivar[6] = 385;
            Desactivar[7] = 489;
            Desactivar[8] = 490;
            Desactivar[9] = 492;
            Desactivar[10] = 494;
            Desactivar[11] = 647;
            Desactivar[12] = 648;
            Desactivar[13] = 150;
            Desactivar[14] = 380;
            Desactivar[15] = 381;
            Desactivar[16] = 485;
            Desactivar[17] = 486;
            Desactivar[18] = 488;
            Desactivar[19] = 641;
            Desactivar[20] = 642;
            Desactivar[21] = 645;
            Desactivar[22] = 249;
            Desactivar[23] = 250;
            Desactivar[24] = 382;
            Desactivar[25] = 383;
            Desactivar[26] = 384;
            Desactivar[27] = 483;
            Desactivar[28] = 484;
            Desactivar[29] = 487;
            Desactivar[30] = 643;
            Desactivar[31] = 644;
            Desactivar[32] = 646;
            Desactivar[33] = 144;
            Desactivar[34] = 145;
            Desactivar[35] = 146;
            Desactivar[36] = 243;
            Desactivar[37] = 244;
            Desactivar[38] = 245;
            Desactivar[39] = 377;
            Desactivar[40] = 378;
            Desactivar[41] = 379;
            Desactivar[42] = 480;
            Desactivar[43] = 481;
            Desactivar[44] = 482;
            Desactivar[45] = 638;
            Desactivar[46] = 639;
            Desactivar[47] = 640;

            Tooltip = new int[48];
            Tooltip[0] = 500 - n;
            Tooltip[1] = 500 - n;
            Tooltip[2] = 500 - n;
            Tooltip[3] = 500 - n;
            Tooltip[4] = 400 - n;
            Tooltip[5] = 400 - n;
            Tooltip[6] = 400 - n;
            Tooltip[7] = 400 - n;
            Tooltip[8] = 400 - n;
            Tooltip[9] = 400 - n;
            Tooltip[10] = 400 - n;
            Tooltip[11] = 400 - n;
            Tooltip[12] = 400 - n;
            Tooltip[13] = 300 - n;
            Tooltip[14] = 300 - n;
            Tooltip[15] = 300 - n;
            Tooltip[16] = 300 - n;
            Tooltip[17] = 300 - n;
            Tooltip[18] = 300 - n;
            Tooltip[19] = 300 - n;
            Tooltip[20] = 300 - n;
            Tooltip[21] = 300 - n;
            Tooltip[22] = 300 - n;
            Tooltip[23] = 200 - n;
            Tooltip[24] = 200 - n;
            Tooltip[25] = 200 - n;
            Tooltip[26] = 200 - n;
            Tooltip[27] = 200 - n;
            Tooltip[28] = 200 - n;
            Tooltip[29] = 200 - n;
            Tooltip[30] = 200 - n;
            Tooltip[31] = 200 - n;
            Tooltip[32] = 200 - n;
            Tooltip[33] = 100 - n;
            Tooltip[34] = 100 - n;
            Tooltip[35] = 100 - n;
            Tooltip[36] = 100 - n;
            Tooltip[37] = 100 - n;
            Tooltip[38] = 100 - n;
            Tooltip[39] = 100 - n;
            Tooltip[40] = 100 - n;
            Tooltip[41] = 100 - n;
            Tooltip[42] = 100 - n;
            Tooltip[43] = 100 - n;
            Tooltip[44] = 100 - n;
            Tooltip[45] = 100 - n;
            Tooltip[46] = 100 - n;
            Tooltip[47] = 100 - n;
        }
        if (!activartodo) {
            String liga;
            if (kanto) {
                liga = "Kanto";
            } else if (johto) {
                liga = "Johto";
            } else if (hoenn) {
                liga = "Hoenn";
            } else if (sinnoh) {
                liga = "Sinnoh";
            } else {
                liga = "Teselia";
            }
            for (int i = 0; i < Desactivar.length; i++) {
                botonelegirpokemon[Desactivar[i] - 1].setEnabled(false);
                botonelegirpokemon[Desactivar[i] - 1].setToolTipText("Necesitas ganar la liga de " + liga + " con " + Tooltip[i] + " más.");
            }
        }
    }

    public void EstadisticasMouseOver(int i) {
        lblestadisticas[0].setText("Ataque ");
        lblestadisticas[1].setText("" + (int) pokemon[i].ataque);

        lblestadisticas[2].setText("Ata. Esp. ");
        lblestadisticas[3].setText("" + +(int) pokemon[i].ataqueesp);

        lblestadisticas[4].setText("Defensa ");
        lblestadisticas[5].setText("" + (int) pokemon[i].defensa);

        lblestadisticas[6].setText("Def. Esp. ");
        lblestadisticas[7].setText("" + (int) pokemon[i].defensaesp);

        lblestadisticas[8].setText("Velocidad ");
        lblestadisticas[9].setText("" + pokemon[i].velocidad);

        lblestadisticas[10].setText("Peso ");
        lblestadisticas[11].setText("" + pokemon[i].peso + " Kg");

        panelestadisticas.setBackground(Color.green);
        AtaquePorCodigo(i);
    }

    public void AtaquePorCodigo(int i) {
        Ataque a = Ataque.AtaqueElegidoNOInicializa(bdataque, Integer.parseInt(pokemon[i].ataque1));
        fotoataque1.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + a.tipo + ".gif"));
        lblataque1.setText(a.nombre);
        ppataque1.setText(a.pp + "/" + a.ppmax);

        double dan = a.daño;
        String ataq = " (" + a.nombre + "-" + a.daño + ") ";
        a = Ataque.AtaqueElegidoNOInicializa(bdataque, Integer.parseInt(pokemon[i].ataque2));
        fotoataque2.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + a.tipo + ".gif"));
        lblataque2.setText(a.nombre);
        ppataque2.setText(a.pp + "/" + a.ppmax);

        dan += a.daño;
        ataq += " (" + a.nombre + "-" + a.daño + ") ";
        a = Ataque.AtaqueElegidoNOInicializa(bdataque, Integer.parseInt(pokemon[i].ataque3));
        fotoataque3.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + a.tipo + ".gif"));
        lblataque3.setText(a.nombre);
        ppataque3.setText(a.pp + "/" + a.ppmax);

        dan += a.daño;
        ataq += " (" + a.nombre + "-" + a.daño + ") ";

        a = Ataque.AtaqueElegidoNOInicializa(bdataque, Integer.parseInt(pokemon[i].ataque4));
        fotoataque4.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + a.tipo + ".gif"));
        lblataque4.setText(a.nombre);
        ppataque4.setText(a.pp + "/" + a.ppmax);

        dan += a.daño;
        ataq += " (" + a.nombre + "-" + a.daño + ") ";
        if (!modocarrera) {
            botonelegirpokemon[i].setToolTipText("" + (int) dan + " || " + ataq);
        }
    }

    public static void RefrescoAtaques() {
        Ataque a;
        for (int k = 0; k < botonataques.length; k++) {
            a = ataquejugador[pokemonencombatejugador][k];
            botonataques[k].setEnabled(true);
            fotoataquejugador[k].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + a.tipo + ".gif"));
            botonataques[k].setText(a.nombre + " " + a.pp + "/" + a.ppmax);
        }
    }

    public void EstadisticasExitMouse(int i) {
        botonelegirpokemon[i].setBackground(null);
        panelcaracteristicas.setVisible(false);
    }

    public void EstadisticasJugadorMouseOver(int i) {
        panelcaracteristicas.setBounds(12, 555, 242, 225);
        lblestadisticas[0].setText("Ataque ");
        lblestadisticas[1].setText("" + (int) Juego.equipojugador[i].ataque);

        lblestadisticas[2].setText("Ata. Esp. ");
        lblestadisticas[3].setText("" + +(int) Juego.equipojugador[i].ataqueesp);

        lblestadisticas[4].setText("Defensa ");
        lblestadisticas[5].setText("" + (int) Juego.equipojugador[i].defensa);

        lblestadisticas[6].setText("Def. Esp. ");
        lblestadisticas[7].setText("" + (int) Juego.equipojugador[i].defensaesp);

        lblestadisticas[8].setText("Velocidad ");
        lblestadisticas[9].setText("" + Juego.equipojugador[i].velocidad);

        lblestadisticas[10].setText("Peso ");
        lblestadisticas[11].setText("" + Juego.equipojugador[i].peso + " Kg");

        if (!menuelegirpokemon) {
            fotoataque1.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataquejugador[i][0].tipo + ".gif"));
            lblataque1.setText(ataquejugador[i][0].nombre);
            ppataque1.setText(ataquejugador[i][0].pp + "/" + ataquejugador[i][0].ppmax);

            fotoataque2.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataquejugador[i][1].tipo + ".gif"));
            lblataque2.setText(ataquejugador[i][1].nombre);
            ppataque2.setText(ataquejugador[i][1].pp + "/" + ataquejugador[i][1].ppmax);

            fotoataque3.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataquejugador[i][2].tipo + ".gif"));
            lblataque3.setText(ataquejugador[i][2].nombre);
            ppataque3.setText(ataquejugador[i][2].pp + "/" + ataquejugador[i][2].ppmax);

            fotoataque4.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataquejugador[i][3].tipo + ".gif"));
            lblataque4.setText(ataquejugador[i][3].nombre);
            ppataque4.setText(ataquejugador[i][3].pp + "/" + ataquejugador[i][3].ppmax);
        } else {
            AtaquePorCodigo(Juego.equipojugador[i].codigo - 1);
        }
    }

    public void EstadisticasJugadorExitMouse(int i) {
        //botonelegirpokemon[i].setBackground(null);
        panelcaracteristicas.setVisible(false);
    }

    public void EstadisticasIAMouseOver(int i) {
        panelcaracteristicas.setBounds(1020, 555, 242, 225);

        lblestadisticas[0].setText("Ataque ");
        lblestadisticas[1].setText("" + (int) Juego.equipoIA[i].ataque);

        lblestadisticas[2].setText("Ata. Esp. ");
        lblestadisticas[3].setText("" + +(int) Juego.equipoIA[i].ataqueesp);

        lblestadisticas[4].setText("Defensa ");
        lblestadisticas[5].setText("" + (int) Juego.equipoIA[i].defensa);

        lblestadisticas[6].setText("Def. Esp. ");
        lblestadisticas[7].setText("" + (int) Juego.equipoIA[i].defensaesp);

        lblestadisticas[8].setText("Velocidad ");
        lblestadisticas[9].setText("" + Juego.equipoIA[i].velocidad);

        lblestadisticas[10].setText("Peso ");
        lblestadisticas[11].setText("" + Juego.equipoIA[i].peso + " Kg");

        fotoataque1.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataqueIA[i][0].tipo + ".gif"));
        lblataque1.setText(ataqueIA[i][0].nombre);
        ppataque1.setText(ataqueIA[i][0].pp + "/" + ataqueIA[i][0].ppmax);

        fotoataque2.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataqueIA[i][1].tipo + ".gif"));
        lblataque2.setText(ataqueIA[i][1].nombre);
        ppataque2.setText(ataqueIA[i][1].pp + "/" + ataqueIA[i][1].ppmax);

        fotoataque3.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataqueIA[i][2].tipo + ".gif"));
        lblataque3.setText(ataqueIA[i][2].nombre);
        ppataque3.setText(ataqueIA[i][2].pp + "/" + ataqueIA[i][2].ppmax);

        fotoataque4.setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + ataqueIA[i][3].tipo + ".gif"));
        lblataque4.setText(ataqueIA[i][3].nombre);
        ppataque4.setText(ataqueIA[i][3].pp + "/" + ataqueIA[i][3].ppmax);

    }

    public void EstadisticasIAExitMouse(int i) {
        //botonelegirpokemon[i].setBackground(null);
        panelcaracteristicas.setVisible(false);
    }

    public void Confirmar() {
        int n;
        if (entrenadorliga == 1 && !Juego.repetircombate) {
            Object[] options = {"Iniciar el combate", "Cambiar algún Pokémon", "Reelegir todos los pokemon"};
            n = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", null,
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        } else {
            n = 0;
        }
        System.out.println("ENTRENADOR LIGA INICIO ---> " + entrenadorliga);
        if (n == 0) {
            if (modocarrera) {
                Pokemon.PokemonLiga(entrenadorliga);
            }
            for (int j = 0; j < botonpokemonelegidosIA.length; j++) {
                botonpokemonelegidosIA[j].setVisible(true);
            }
            if (entrenadorliga == 1 && !Juego.repetircombate) {
                estrellajugador = new JLabel[contadorpokemonselegidos];
                estrellaIA = new JLabel[contadorpokemonselegidos];
                for (int i = 0; i < contadorpokemonselegidos; i++) {
                    saludequipojugador[i] = new JPanel();
                    saludequipoIA[i] = new JPanel();
                    estrellajugador[i] = new JLabel();
                    estrellaIA[i] = new JLabel();
                }
            }
            for (int i = 0; i < contadorpokemonselegidos; i++) {
                saludequipojugador[i].setBounds(135, 70, 98, 7);
                saludequipojugador[i].setBackground(Color.green);
                botonpokemonelegidosJugador[i].add(saludequipojugador[i]);

                saludequipoIA[i].setBounds(135, 70, 98, 7);
                saludequipoIA[i].setBackground(Color.green);
                botonpokemonelegidosIA[i].add(saludequipoIA[i]);

                equipojugador[i].BajarEstadisticasShiny();
                if ((Math.random() * 100) < probabilidadshiny) {
                    botonpokemonelegidosJugador[i].setIcon(new ImageIcon("Recursos/Imagenes/Shiny Frente/" + equipojugador[i].codigo + ".png"));
                    estrellajugador[i].setBounds(176, 10, 16, 16);
                    estrellajugador[i].setIcon(new ImageIcon("Recursos/Imagenes/Shiny Estrella/Estrella.png"));
                    botonpokemonelegidosJugador[i].add(estrellajugador[i]);
                    equipojugador[i].SubirEstadisticasShiny();
                    lblsaludequipojugador[i].setText((int) equipojugador[i].salud + "/" + (int) equipojugador[i].saludmax);
                } else {
                    botonpokemonelegidosJugador[i].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + equipojugador[i].codigo + ".png"));
                    estrellajugador[i].setIcon(null);
                    equipojugador[i].shiny = false;
                }

                equipoIA[i].BajarEstadisticasShiny();
                if ((Math.random() * 100) < probabilidadshiny) {
                    botonpokemonelegidosIA[i].setIcon(new ImageIcon("Recursos/Imagenes/Shiny Frente/" + equipoIA[i].codigo + ".png"));
                    estrellaIA[i].setBounds(176, 10, 16, 16);
                    estrellaIA[i].setIcon(new ImageIcon("Recursos/Imagenes/Shiny Estrella/Estrella.png"));
                    botonpokemonelegidosIA[i].add(estrellaIA[i]);
                    equipoIA[i].SubirEstadisticasShiny();
                    lblsaludequipoIA[i].setText((int) equipoIA[i].salud + "/" + (int) equipoIA[i].saludmax);
                } else {
                    botonpokemonelegidosIA[i].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + equipoIA[i].codigo + ".png"));
                    estrellaIA[i].setIcon(null);
                    equipoIA[i].shiny = false;
                }
            }
            if (modocarrera && entrenadorliga < 10) {
                MenuLideres();
            } else {
                if (entrenadorliga == 1 && !Juego.repetircombate) {
                    IniciarCombate();
                } else {
                    MenuAltoMando();
                }
            }
        } else if (n == 1) {
            for (int i = 0; i < botonelegirpokemon.length; i++) {
                botonelegirpokemon[i].setEnabled(false);
            }
        } else {
            for (int i = 0; i < botonpokemonelegidosJugador.length; i++) {
                botonpokemonelegidosJugador[i].setVisible(false);
                if (modocarrera) {
                    try {
                        String[][] hallpokemon = Lecturas.MostrarHallFamaOrdenado();
                        int cont = 0;
                        boolean salir = false;
                        while (cont < hallpokemon.length && !salir) {
                            if (Integer.parseInt(hallpokemon[cont][0]) == pokemon[(Juego.equipojugador[i].codigo) - 1].codigo) {
                                botonelegirpokemon[(Juego.equipojugador[i].codigo) - 1].setBackground(Color.green);
                                salir = true;
                            }
                            cont++;
                        }
                        if (!salir) {
                            botonelegirpokemon[(Juego.equipojugador[i].codigo) - 1].setBackground(null);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    botonelegirpokemon[(Juego.equipojugador[i].codigo) - 1].setBackground(null);
                }
                tipo1jugador[i].setIcon(null);
                tipo2jugador[i].setIcon(null);
                lblsaludequipojugador[i].setText(null);
                Juego.equipojugador[i] = null;
                contadorpokemonselegidos = 0;
                PokemonYaElegidosJugador[i] = 0;
                if (!modocarrera) {
                    botonpokemonelegidosIA[i].setVisible(false);
                    tipo1IA[i].setIcon(null);
                    tipo2IA[i].setIcon(null);
                    lblsaludequipoIA[i].setText(null);
                    Juego.equipoIA[i] = null;
                    PokemonYaElegidosIA[i] = 0;
                }
            }
        }
    }

    public void MenuLideres() {
        panelfondo.CargarImagen("Lideres.png");
        sliderecualizador.setVisible(true);
        if (entrenadorliga == 1 && !Juego.repetircombate) {
            MusicMenus.stop();
            MusicMenuLider = new JLayerLoop("MenuCarrera/Menu Lider Gym.mp3", true);
            MusicMenuLider.play();
            contenedorlistapokemon.setVisible(false);
            scrollpokemonlista.setVisible(false);
            contenedorpokemonsjugador.setVisible(false);
            contenedorpokemonsIA.setVisible(false);
            panelestadisticas.setVisible(false);
            panelcaracteristicas.setVisible(false);
            //setSize(1280, 850);
            setSize(1280, 768);
            botonlideres = new JButton[8];
            int x = 0;
            int y = 0;
            int columna = 1;
            for (int i = 0; i < botonlideres.length; i++) {
                if (i == 4) {
                    y = 200;
                    columna = 1;
                    x = 0;
                }
                botonlideres[i] = new JButton();
                botonlideres[i].setBounds(45 + x, 150 + y, 270, 150);
                botonlideres[i].setFocusable(false);
                botonlideres[i].setEnabled(false);
                add(botonlideres[i]);
                x = 300 * columna;
                columna++;
            }
            CargarImagenesLideresGimnasio();
            ListenersLideresGimnasio();
        } else {
            MusicFinCombate.stop();
            MusicMenuLider.play();
            contenedorpokemonsjugador.setVisible(false);
            contenedorpokemonsIA.setVisible(false);
            panelcombate.setVisible(false);
            Mensajes.setVisible(false);
            scrollmensajes.setVisible(false);
            contenedorataques.setVisible(false);
            panelestadisticas.setVisible(false);
            panelcaracteristicas.setVisible(false);
            labelnombreentrenadorjugador.setVisible(false);
            labelnombreentrenadorIA.setVisible(false);
            labelfotoentrenadorIA.setVisible(false);
            if (!Juego.repetircombate) {
                CargarMedalla();
            }
            for (int i = 0; i < botonlideres.length; i++) {
                botonlideres[i].setVisible(true);
            }
            if (entrenadorliga == 9) {//9
                botoniraligapokemon.setBounds(250, 550, 780, 180);
                botoniraligapokemon.setIcon(new ImageIcon("Recursos/Imagenes/Miscelanea/LigaPokemon.png"));
                add(botoniraligapokemon);
                botoniraligapokemon.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MenuAltoMando();
                    }
                });
            } else {
                if (!Juego.repetircombate) {
                    botonlideres[entrenadorliga - 1].setEnabled(true);
                }
            }
        }
    }

    public void CargarImagenesLideresGimnasio() {
        String liga;
        if (kanto) {
            liga = "Kanto";
        } else if (johto) {
            liga = "Johto";
        } else if (hoenn) {
            liga = "Hoenn";
        } else if (sinnoh) {
            liga = "Sinnoh";
        } else {
            liga = "Teselia";
        }
        for (int i = 0; i < botonlideres.length; i++) {
            botonlideres[i].setIcon(new ImageIcon("Recursos/Imagenes/Lider " + liga + "/" + (i + 1) + ".png"));
            botonlideres[i].setText(Pokemon.NombreEntrenador(i + 1));
        }
        botonlideres[0].setEnabled(true);
        //PokemonGUI.estadojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
    }

    public void CargarMedalla() {
        String liga;
        if (kanto) {
            liga = "Kanto";
        } else if (johto) {
            liga = "Johto";
        } else if (hoenn) {
            liga = "Hoenn";
        } else if (sinnoh) {
            liga = "Sinnoh";
        } else {
            liga = "Teselia";
        }
        botonlideres[entrenadorliga - 2].setIcon(new ImageIcon("Recursos/Imagenes/Medallas " + liga + "/" + (entrenadorliga - 1) + ".png"));
        botonlideres[entrenadorliga - 2].setText(null);
    }

    public void ListenersLideresGimnasio() {
        for (int i = 0; i < botonlideres.length; i++) {
            botonlideres[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    for (int j = 0; j < botonlideres.length; j++) {
                        if ((e.getSource() == botonlideres[j] && entrenadorliga == (j + 1)) || (e.getSource() == botonlideres[j] && Juego.repetircombate)) {
                            for (int k = 0; k < botonlideres.length; k++) {
                                botonlideres[k].setVisible(false);
                            }
                            IniciarCombate();
                        } else {
                            if (e.getSource() == botonlideres[j] && !Juego.repetircombate) {
                                JOptionPane.showMessageDialog(null, "¡¡Ya has vencido a " + Pokemon.NombreEntrenador(j + 1) + "!!");
                            }
                        }
                    }
                }
            });
        }
    }

    public void MenuAltoMando() {
        panelfondo.CargarImagen("AltoMando.png");
        contenedorpokemonsjugador.setVisible(false);
        contenedorpokemonsIA.setVisible(false);
        panelcombate.setVisible(false);
        Mensajes.setVisible(false);
        scrollmensajes.setVisible(false);
        contenedorataques.setVisible(false);
        panelestadisticas.setVisible(false);
        panelcaracteristicas.setVisible(false);
        labelnombreentrenadorjugador.setVisible(false);
        labelnombreentrenadorIA.setVisible(false);
        labelfotoentrenadorIA.setVisible(false);
        for (int k = 0; k < botonlideres.length; k++) {
            botonlideres[k].setVisible(false);
        }
        botoniraligapokemon.setVisible(false);
        if (entrenadorliga == 9) {
            MusicMenuLider.stop();
            MusicMenuAltoMando = new JLayerLoop("MenuCarrera/Menu Alto Mando.mp3", true);
            MusicMenuAltoMando.play();
            botonaltomando = new JButton[4];
            int x = 0;
            int y = 0;
            for (int i = 0; i < botonaltomando.length; i++) {
                botonaltomando[i] = new JButton();
                if (i == 2) {
                    y = 200;
                    x = 0;
                }
                botonaltomando[i] = new JButton();
                botonaltomando[i].setBounds(45 + x, 150 + y, 270, 150);
                botonaltomando[i].setFocusable(false);
                botonaltomando[i].setEnabled(false);
                add(botonaltomando[i]);
                x = 945;
            }
            CargarImagenesAltoMando();
            ListenersAltoMando();
        } else {
            MusicFinCombate.stop();
            MusicMenuAltoMando.play();
            contenedorpokemonsjugador.setVisible(false);
            contenedorpokemonsIA.setVisible(false);
            panelcombate.setVisible(false);
            Mensajes.setVisible(false);
            scrollmensajes.setVisible(false);
            contenedorataques.setVisible(false);
            panelestadisticas.setVisible(false);
            panelcaracteristicas.setVisible(false);
            labelnombreentrenadorjugador.setVisible(false);
            labelnombreentrenadorIA.setVisible(false);
            labelfotoentrenadorIA.setVisible(false);
            //CargarAltoMandoDerrotado();
            for (int i = 0; i < botonaltomando.length; i++) {
                botonaltomando[i].setVisible(true);
            }
            if (entrenadorliga == 13) {//13
                botonjefealtomando.setText(Pokemon.NombreEntrenador(13));
                botonjefealtomando.setBounds(415, 200, 450, 300);
                String liga;
                if (kanto) {
                    liga = "Kanto";
                } else if (johto) {
                    liga = "Johto";
                } else if (hoenn) {
                    liga = "Hoenn";
                } else if (sinnoh) {
                    liga = "Sinnoh";
                } else {
                    liga = "Teselia";
                }
                botonjefealtomando.setIcon(new ImageIcon("Recursos/Imagenes/Lider " + liga + "/13.png"));
                botonjefealtomando.setVisible(true);
                add(botonjefealtomando);
                if (!Juego.repetircombate) {
                    botonjefealtomando.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int k = 0; k < botonaltomando.length; k++) {
                                botonaltomando[k].setVisible(false);
                            }
                            botonjefealtomando.setVisible(false);
                            IniciarCombate();
                        }
                    });
                }
            } else {
                if (!Juego.repetircombate) {
                    botonaltomando[entrenadorliga - 9].setEnabled(true);
                }
            }
        }
    }

    public void CargarImagenesAltoMando() {
        String liga;
        if (kanto) {
            liga = "Kanto";
        } else if (johto) {
            liga = "Johto";
        } else if (hoenn) {
            liga = "Hoenn";
        } else if (sinnoh) {
            liga = "Sinnoh";
        } else {
            liga = "Teselia";
        }
        for (int i = 0; i < botonaltomando.length; i++) {
            botonaltomando[i].setIcon(new ImageIcon("Recursos/Imagenes/Lider " + liga + "/" + (i + 9) + ".png"));
            botonaltomando[i].setText(Pokemon.NombreEntrenador(i + 9));
        }
        botonaltomando[0].setEnabled(true);
        //PokemonGUI.estadojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
    }

    public void CargarAltoMandoDerrotado() {
        String liga;
        if (kanto) {
            liga = "Kanto";
        } else if (johto) {
            liga = "Johto";
        } else if (hoenn) {
            liga = "Hoenn";
        } else if (sinnoh) {
            liga = "Sinnoh";
        } else {
            liga = "Teselia";
        }
        botonaltomando[entrenadorliga - 10].setIcon(new ImageIcon("Recursos/Imagenes/Medallas " + liga + "/" + (entrenadorliga - 10) + ".png"));
        botonaltomando[entrenadorliga - 10].setText(null);
    }

    public void ListenersAltoMando() {
        for (int i = 0; i < botonaltomando.length; i++) {
            botonaltomando[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < botonaltomando.length; j++) {
                        if ((e.getSource() == botonaltomando[j] && entrenadorliga == (j + 9)) || (e.getSource() == botonaltomando[j] && Juego.repetircombate)) {
                            for (int k = 0; k < botonaltomando.length; k++) {
                                botonaltomando[k].setVisible(false);
                            }
                            IniciarCombate();
                        } else {
                            if (e.getSource() == botonaltomando[j] && !Juego.repetircombate) {
                                JOptionPane.showMessageDialog(null, "¡¡Ya has vencido a " + Pokemon.NombreEntrenador(j + 9) + "!!");
                            }
                        }
                    }
                }
            });
        }
    }

    public void BotonSiguienteEntrenador() {
        botonsiguienteentrenador.setBounds(360, 140, 360, 44);
        contenedorataques.add(botonsiguienteentrenador);
        botonsiguienteentrenador.setFocusable(false);
        botonsiguienteentrenador.setVisible(false);
        botonsiguienteentrenador.setText("Siguiente entrenador");
        botonsiguienteentrenador.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                botonsiguienteentrenador.setVisible(false);
                Confirmar();
            }
        });
    }

    public void ListenersBotonPokemonElegidosJugador() {
        for (int i = 0; i < botonpokemonelegidosJugador.length; i++) {
            botonpokemonelegidosJugador[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < botonpokemonelegidosJugador.length; j++) {
                        if (menuelegirpokemon) {
                            if (e.getSource() == botonpokemonelegidosJugador[j]) {
                                if (j + 1 == contadorpokemonselegidos) {
                                    int n = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres quitar de tu equipo a " + Juego.equipojugador[j].nombre + "?", "", JOptionPane.YES_NO_OPTION);
                                    if (n == 0) {
                                        for (int k = 0; k < botonelegirpokemon.length; k++) {
                                            botonelegirpokemon[k].setEnabled(true);
                                        }
                                        if (modocarrera) {
                                            DesactivarLegendarios();
                                        }
                                        botonpokemonelegidosJugador[j].setVisible(false);
                                        if (modocarrera) {
                                            try {
                                                String[][] hallpokemon = Lecturas.MostrarHallFamaOrdenado();
                                                int cont = 0;
                                                boolean salir = false;
                                                while (cont < hallpokemon.length && !salir) {
                                                    if (Integer.parseInt(hallpokemon[cont][0]) == pokemon[(Juego.equipojugador[j].codigo) - 1].codigo) {
                                                        botonelegirpokemon[(Juego.equipojugador[j].codigo) - 1].setBackground(Color.green);
                                                        salir = true;
                                                    }
                                                    cont++;
                                                }
                                                if (!salir) {
                                                    botonelegirpokemon[(Juego.equipojugador[j].codigo) - 1].setBackground(null);
                                                }
                                            } catch (IOException ex) {
                                                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        } else {
                                            botonelegirpokemon[(Juego.equipojugador[j].codigo) - 1].setBackground(null);
                                        }
                                        tipo1jugador[j].setIcon(null);
                                        tipo2jugador[j].setIcon(null);
                                        lblsaludequipojugador[j].setText(null);
                                        Juego.equipojugador[j] = null;
                                        PokemonYaElegidosJugador[j] = 0;
                                        if (!modocarrera) {
                                            botonpokemonelegidosIA[j].setVisible(false);
                                            tipo1IA[j].setIcon(null);
                                            tipo2IA[j].setIcon(null);
                                            lblsaludequipoIA[j].setText(null);
                                            Juego.equipoIA[j] = null;
                                            PokemonYaElegidosIA[j] = 0;
                                        }
                                        contadorpokemonselegidos--;
                                    }
                                } else {
                                    System.out.println("ELIMINAR POSICION Y ORDENAR");
                                    int n = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres quitar de tu equipo a " + Juego.equipojugador[j].nombre + "?", "", JOptionPane.YES_NO_OPTION);
                                    if (n == 0) {
                                        for (int k = 0; k < botonelegirpokemon.length; k++) {
                                            botonelegirpokemon[k].setEnabled(true);
                                        }
                                        if (modocarrera) {
                                            DesactivarLegendarios();
                                        }
                                        Pokemon p;
                                        if (modocarrera) {
                                            try {
                                                String[][] hallpokemon = Lecturas.MostrarHallFamaOrdenado();
                                                int cont = 0;
                                                boolean salir = false;
                                                while (cont < hallpokemon.length && !salir) {
                                                    if (Integer.parseInt(hallpokemon[cont][0]) == pokemon[(Juego.equipojugador[j].codigo) - 1].codigo) {
                                                        botonelegirpokemon[(Juego.equipojugador[j].codigo) - 1].setBackground(Color.green);
                                                        salir = true;
                                                    }
                                                    cont++;
                                                }
                                                if (!salir) {
                                                    botonelegirpokemon[(Juego.equipojugador[j].codigo) - 1].setBackground(null);
                                                }
                                            } catch (IOException ex) {
                                                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        } else {
                                            botonelegirpokemon[(Juego.equipojugador[j].codigo) - 1].setBackground(null);
                                        }
                                        for (int k = j; k < contadorpokemonselegidos - 1; k++) {
                                            p = Juego.equipojugador[k + 1];
                                            Juego.equipojugador[k] = p;
                                            botonpokemonelegidosJugador[k].setLayout(null);
                                            botonpokemonelegidosJugador[k].setVisible(true);
                                            botonpokemonelegidosJugador[k].setText(p.nombre);
                                            botonpokemonelegidosJugador[k].setHorizontalTextPosition(SwingConstants.RIGHT);
                                            botonpokemonelegidosJugador[k].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                                            botonpokemonelegidosJugador[k].setFocusable(false);
                                            //tipo1jugador[k] = new JLabel();
                                            tipo1jugador[k].setBounds(5, 15, 50, 20);
                                            tipo1jugador[k].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo1 + ".gif"));
                                            botonpokemonelegidosJugador[k].add(tipo1jugador[k]);
                                            //tipo2jugador[k] = new JLabel();
                                            if (p.tipo2.compareTo("n*n*") != 0) {
                                                tipo2jugador[k].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo2 + ".gif"));
                                                tipo2jugador[k].setBounds(5, 45, 50, 20);
                                            } else {
                                                tipo2jugador[k].setIcon(null);
                                            }
                                            botonpokemonelegidosJugador[k].add(tipo2jugador[k]);
                                            botonpokemonelegidosJugador[k].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + p.codigo + ".png"));

                                            estadoequipojugador[k] = new JLabel();
                                            estadoequipojugador[k].setBounds(200, 7, 30, 30);
                                            botonpokemonelegidosJugador[k].add(estadoequipojugador[k]);
                                            lblsaludequipojugador[k].setText((int) p.salud + "/" + (int) p.saludmax);

                                            PokemonYaElegidosJugador[k] = p.codigo;
                                        }
                                        Juego.equipojugador[contadorpokemonselegidos - 2] = Juego.equipojugador[contadorpokemonselegidos - 1];
                                        botonpokemonelegidosJugador[contadorpokemonselegidos - 1].setVisible(false);
                                        tipo1jugador[contadorpokemonselegidos - 1].setIcon(null);
                                        tipo2jugador[contadorpokemonselegidos - 1].setIcon(null);
                                        lblsaludequipojugador[contadorpokemonselegidos - 1].setText(null);
                                        Juego.equipojugador[contadorpokemonselegidos - 1] = null;
                                        PokemonYaElegidosJugador[contadorpokemonselegidos - 1] = 0;
                                        if (!modocarrera) {
                                            botonpokemonelegidosIA[contadorpokemonselegidos - 1].setVisible(false);
                                            tipo1IA[contadorpokemonselegidos - 1].setIcon(null);
                                            tipo2IA[contadorpokemonselegidos - 1].setIcon(null);
                                            lblsaludequipoIA[contadorpokemonselegidos - 1].setText(null);
                                            Juego.equipoIA[contadorpokemonselegidos - 1] = null;
                                            PokemonYaElegidosIA[contadorpokemonselegidos - 1] = 0;
                                        }
                                        contadorpokemonselegidos--;
                                    }
                                }
                            }
                        } else {
                            if (!Pokemon.activarsolounboton) {
                                for (int i = 0; i < botonataques.length; i++) {
                                    PokemonGUI.botonataques[i].setEnabled(true);
                                }
                                PokemonGUI.botoncambiarpokemon.setEnabled(true);
                            } else {
                                for (int i = 0; i < PokemonGUI.botonataques.length; i++) {
                                    System.out.println("ATAQUE USADO: " + ataqueusadojugador);
                                    if (i == ataqueusadojugador) {
                                        PokemonGUI.botonataques[i].setEnabled(true);
                                    } else {
                                        PokemonGUI.botonataques[i].setEnabled(false);
                                    }
                                }
                                PokemonGUI.botoncambiarpokemon.setEnabled(false);
                            }
                            if (e.getSource() == botonpokemonelegidosJugador[j] && cambiarpokemon) {
                                if (j != pokemonencombatejugador) {
                                    if (!iniciocombate) {
                                        equipojugador[pokemonencombatejugador].DevolverEstadisticasBase(equipojugador[pokemonencombatejugador], equipoIA[pokemonencombateIA], ataquejugador[pokemonencombatejugador]);
                                    }
                                    Pokemon.activarsolounboton = false;
                                    iniciocombate = false;
                                    setCursor(cursormenus);
                                    PokemonGUI.panelestadojugador.setVisible(true);
                                    PokemonGUI.panelestadoIA.setVisible(true);

                                    for (int i = 0; i < Juego.equipojugador.length; i++) {
                                        lblsaludequipojugador[i].setText("" + (int) Juego.equipojugador[i].salud + "/" + (int) Juego.equipojugador[i].saludmax);
                                    }

                                    for (int k = 0; k < botonataques.length; k++) {
                                        botonataques[k].setEnabled(true);
                                        fotoataquejugador[k].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + Juego.ataquejugador[j][k].tipo + ".gif"));
                                        botonataques[k].setText(Juego.ataquejugador[j][k].nombre + " " + Juego.ataquejugador[j][k].pp + "/" + Juego.ataquejugador[j][k].ppmax);
                                    }
                                    botoncambiarpokemon.setEnabled(true);
                                    pokemonencombatejugador = j;
                                    if (equipojugador[pokemonencombatejugador].estado.compareTo("Normal") != 0 || equipojugador[pokemonencombatejugador].drenadoras || equipojugador[pokemonencombatejugador].atrapado > 0) {
                                        PokemonGUI.estadojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
                                        //PokemonGUI.estadoequipojugador[pokemonencombatejugador].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipojugador[pokemonencombatejugador].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
                                    } else {
                                        PokemonGUI.estadoequipojugador[pokemonencombatejugador].setIcon(null);
                                        PokemonGUI.estadojugador.setIcon(null);
                                    }
                                    RefrescoVidaJugador();
                                    if (!Juego.equipojugador[j].shiny) {
                                        labelfotojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Espalda/" + Juego.equipojugador[j].codigo + ".png")).getImage()).getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)));
                                    } else {
                                        labelfotojugador.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Shiny Espalda/" + Juego.equipojugador[j].codigo + ".png")).getImage()).getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)));
                                    }
                                    //labelsaludjugador.setText((int) Juego.equipojugador[j].salud + "/" + (int) Juego.equipojugador[j].saludmax);
                                    saludjugador.setVisible(true);
                                    labelnombrejugador.setText(Juego.equipojugador[j].nombre);
                                    if (cambiapokemonIAmuerto) {
                                        int l = pokemonencombateIA;
                                        /*
                                         * if
                                         * (!equipoIA[pokemonencombateIA].Vivo())
                                         * { do { l =
                                         * Metodos.RandomSinRound(Juego.npokemonequipo);
                                         * } while (!Juego.equipoIA[l].Vivo());
                                         * int n =
                                         * JOptionPane.showConfirmDialog(null,
                                         * "El rival va a sacar a " +
                                         * Juego.equipoIA[l].nombre + ".¿Deseas
                                         * cambiar de Pokemon?", "",
                                         * JOptionPane.YES_NO_OPTION); if (n ==
                                         * 0) { for (int k = 0; k <
                                         * botonataques.length; k++) {
                                         * botonataques[k].setVisible(false); }
                                         * botonatacar.setVisible(false); } }
                                         */
                                        pokemonencombateIA = l;
                                        RefrescoVidaIA();
                                        if (!Juego.equipoIA[l].shiny) {
                                            labelfotoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Frente/" + Juego.equipoIA[l].codigo + ".png")).getImage()).getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
                                        } else {
                                            labelfotoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Shiny Frente/" + Juego.equipoIA[l].codigo + ".png")).getImage()).getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
                                        }
                                        //labelsaludIA.setText((int) equipoIA[l].salud + "/" + (int) Juego.equipoIA[l].saludmax);

                                        saludIA.setVisible(true);
                                        labelnombreIA.setText(Juego.equipoIA[l].nombre);
                                        for (int k = 0; k < botonataques.length; k++) {
                                            botonataques[k].setEnabled(true);
                                        }
                                        botoncambiarpokemon.setEnabled(true);
                                    } else {
                                        int l = pokemonencombateIA;
                                        System.out.println(l);
                                        if (!equipoIA[pokemonencombateIA].Vivo()) {
                                            do {
                                                l = Metodos.RandomSinRound(Juego.npokemonequipo);
                                                System.out.println(l);
                                            } while (!Juego.equipoIA[l].Vivo());
                                        }
                                        pokemonencombateIA = l;
                                        if (equipoIA[pokemonencombateIA].estado.compareTo("Normal") != 0 || equipoIA[pokemonencombateIA].drenadoras || equipoIA[pokemonencombateIA].atrapado > 0) {
                                            PokemonGUI.estadoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipoIA[pokemonencombateIA].estado + ".png")).getImage()).getScaledInstance(50, 25, java.awt.Image.SCALE_SMOOTH)));
                                            PokemonGUI.estadoequipoIA[pokemonencombateIA].setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Estados/" + equipoIA[pokemonencombateIA].estado + ".png")).getImage()).getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH)));
                                        } else {
                                            PokemonGUI.estadoequipoIA[pokemonencombateIA].setIcon(null);
                                            PokemonGUI.estadoIA.setIcon(null);
                                        }
                                        if (!Juego.equipoIA[l].shiny) {
                                            labelfotoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Frente/" + Juego.equipoIA[l].codigo + ".png")).getImage()).getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
                                        } else {
                                            labelfotoIA.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Shiny Frente/" + Juego.equipoIA[l].codigo + ".png")).getImage()).getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
                                        }
                                        //labelsaludIA.setText((int) equipoIA[l].salud + "/" + (int) Juego.equipoIA[l].saludmax);
                                        saludIA.setVisible(true);
                                        labelnombreIA.setText(Juego.equipoIA[l].nombre);
                                        for (int k = 0; k < botonataques.length; k++) {
                                            botonataques[k].setVisible(true);
                                        }
                                    }
                                    RefrescoVidaJugador();
                                    RefrescoVidaIA();
                                    if (atacaiaporcambiarpokemonjugador) {
                                        try {
                                            try {
                                                ElegirAtaqueIa();
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        } catch (IOException ex) {
                                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    System.out.println("CODIGO: " + equipojugador[pokemonencombatejugador].codigo);
                                    JLayerLoop sonidopokemon = new JLayerLoop("Pokemon/" + equipojugador[pokemonencombatejugador].codigo + ".mp3", false);
                                    sonidopokemon.play();
                                    cambiarpokemon = false;
                                    cambiapokemonIAmuerto = false;
                                } else {
                                    JOptionPane.showMessageDialog(null, "¡" + equipojugador[pokemonencombatejugador].nombre + " ya está en combate!");
                                    cambiarpokemon = false;
                                    atacaiaporcambiarpokemonjugador = false;
                                    for (int k = 0; k < botonataques.length; k++) {
                                        botonataques[k].setEnabled(true);
                                    }
                                    botoncambiarpokemon.setEnabled(true);
                                    setCursor(cursormenus);
                                }
                            }
                        }
                    }
                }
            });

            botonpokemonelegidosJugador[i].addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent evt) {
                    panelcaracteristicas.setVisible(true);
                    for (int i = 0; i < botonpokemonelegidosJugador.length; i++) {
                        if (evt.getSource() == botonpokemonelegidosJugador[i]) {
                            EstadisticasJugadorMouseOver(i);
                        }
                    }
                }
            });
            botonpokemonelegidosJugador[i].addMouseListener(new MouseAdapter() {

                public void mouseExited(MouseEvent evt) {
                    for (int i = 0; i < botonpokemonelegidosJugador.length; i++) {
                        if (evt.getSource() == botonpokemonelegidosJugador[i]) {
                            EstadisticasJugadorExitMouse(i);
                        }
                    }
                }
            });
            botonpokemonelegidosIA[i].addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent evt) {
                    panelcaracteristicas.setVisible(true);
                    for (int i = 0; i < botonpokemonelegidosIA.length; i++) {
                        if (evt.getSource() == botonpokemonelegidosIA[i]) {
                            EstadisticasIAMouseOver(i);
                        }
                    }
                }
            });
            botonpokemonelegidosIA[i].addMouseListener(new MouseAdapter() {

                public void mouseExited(MouseEvent evt) {
                    for (int i = 0; i < botonpokemonelegidosIA.length; i++) {
                        if (evt.getSource() == botonpokemonelegidosIA[i]) {
                            EstadisticasIAExitMouse(i);
                        }
                    }
                }
            });
        }

    }

    public static void DesactivarBotonAtaques() {
        for (int i = 0; i < botonataques.length; i++) {
            //botonataques[i].setText(null);
            //fotoataquejugador[i].setIcon(null);
            botonataques[i].setEnabled(false);
        }
        botoncambiarpokemon.setEnabled(false);
    }

    public void CrearAtaques() {
        Ataque a;
        for (int i = 0; i < Juego.npokemonequipo; i++) {

            if (Integer.parseInt(Juego.equipojugador[i].ataque1) != 261) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque1));
                Juego.ataquejugador[i][0] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque1));
                Juego.ataquejugador[i][0] = a;
            }
            if ((Integer.parseInt(Juego.equipojugador[i].ataque2) != 261)) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque2));
                Juego.ataquejugador[i][1] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque2));
                Juego.ataquejugador[i][1] = a;
            }
            if ((Integer.parseInt(Juego.equipojugador[i].ataque3) != 261)) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque3));
                Juego.ataquejugador[i][2] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque3));
                Juego.ataquejugador[i][2] = a;
            }
            if (Integer.parseInt(Juego.equipojugador[i].ataque4) != 261) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque4));
                Juego.ataquejugador[i][3] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipojugador[i].ataque4));
                Juego.ataquejugador[i][3] = a;
            }
            a = Ataque.AtaqueElegidoInicializa(bdataque, 223);
            Juego.ataquejugador[i][4] = a;

            if (Integer.parseInt(Juego.equipoIA[i].ataque1) != 261) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque1));
                Juego.ataqueIA[i][0] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque1));
                Juego.ataqueIA[i][0] = a;
            }
            if (Integer.parseInt(Juego.equipoIA[i].ataque2) != 261) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque2));
                Juego.ataqueIA[i][1] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque2));
                Juego.ataqueIA[i][1] = a;
            }
            if (Integer.parseInt(Juego.equipoIA[i].ataque3) != 261) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque3));
                Juego.ataqueIA[i][2] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque3));
                Juego.ataqueIA[i][2] = a;
            }
            if (Integer.parseInt(Juego.equipoIA[i].ataque4) != 261) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque4));
                Juego.ataqueIA[i][3] = a;
            } else if (!Juego.repetircombate) {
                a = Ataque.AtaqueElegidoInicializa(bdataque, Integer.parseInt(Juego.equipoIA[i].ataque4));
                Juego.ataqueIA[i][3] = a;
            }
            a = Ataque.AtaqueElegidoInicializa(bdataque, 223);
            Juego.ataqueIA[i][4] = a;
        }
    }

    public void IniciarCombate() {
        panelfondo.CargarImagen("Combate.png");
        iniciocombate = true;
        pokemonencombatejugador = 7;
        if (modocarrera) {
            if (entrenadorliga >= 9) {
                MusicMenuAltoMando.stop();
            } else {
                MusicMenuLider.stop();
            }
        } else {
            MusicMenus.stop();
        }
        int m = (int) ((Math.random() * 2) + 1);
        System.out.println(m);
        MusicCombate = new JLayerLoop("Batalla/" + m + ".mp3", true);
        MusicCombate.play();
        sliderecualizador.setVisible(true);
        setCursor(cursorpokeball);
        panelcombate = null;
        int n = (int) ((Math.random() * 11) + 1);
        //n = 11;
        panelcombate = new JPanelCombate(n + ".png");
        panelcombate.setBounds(276, 40, 721, 393);
        add(panelcombate);
        if (entrenadorliga == 1 && !Juego.repetircombate) {
            menuelegirpokemon = false;
            //primo
            setSize(1280, 850);
            //setSize(1280, 768);

            labelnombreentrenadorjugador.setBounds(50, 20, 70, 20);
            labelnombreentrenadorjugador.setText("Jugador");
            add(labelnombreentrenadorjugador);

            labelnombreentrenadorIA.setBounds(1050, 20, 150, 20);
            labelnombreentrenadorIA.setText("Rival");
            add(labelnombreentrenadorIA);

            labelfotoentrenadorIA.setBounds(1200, 0, 50, 50);
            add(labelfotoentrenadorIA);

            contenedorlistapokemon.setVisible(false);
            scrollpokemonlista.setVisible(false);
            contenedorataques.setLayout(null);
            contenedorataques.setOpaque(false);
            contenedorataques.setBounds(276, 550, 721, 300);
            //contenedorataques.setBackground(Color.green);
            add(contenedorataques);

            fotoataquejugador = new JLabel[4];
            botonataques = new JButton[4];
            int x = 0;
            int y = 0;
            for (int i = 0; i < botonataques.length; i++) {
                if (i % 2 == 0 && i != 0) {
                    x = 0;
                    y = 70;
                }
                botonataques[i] = new JButton();
                botonataques[i].setBounds(360 * x, y, 360, 70);
                fotoataquejugador[i] = new JLabel();
                fotoataquejugador[i].setBounds(10, 10, 70, 30);
                botonataques[i].setFocusable(false);
                botonataques[i].add(fotoataquejugador[i]);
                contenedorataques.add(botonataques[i]);
                x++;
            }
            botoncambiarpokemon = new JButton();
            botoncambiarpokemon.setText("CAMBIAR POKEMON");
            botoncambiarpokemon.setBounds(0, 140, 360, 44);
            botoncambiarpokemon.setFocusable(false);
            botoncambiarpokemon.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setCursor(cursorpokeball);
                    cambiarpokemon = true;
                    atacaiaporcambiarpokemonjugador = true;
                    for (int i = 0; i < botonataques.length; i++) {
                        botonataques[i].setEnabled(false);
                    }
                    System.out.println("CAMBIA DE POKEMON");
                    //equipoIA[pokemonencombateIA].DevolverEstadisticasBase(equipoIA[pokemonencombateIA], equipojugador[pokemonencombatejugador], ataqueIA[pokemonencombateIA]);
                }
            });
            botoncambiarpokemon.addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent evt) {
                    botoncambiarpokemon.setBackground(new Color(255, 99, 71));
                }

                public void mouseExited(MouseEvent evt) {
                    botoncambiarpokemon.setBackground(null);
                }
            });
            if (Juego.npokemonequipo > 1) {
                contenedorataques.add(botoncambiarpokemon);
            }
        }

        panelcombate.setVisible(true);
        Mensajes.setVisible(true);
        if (entrenadorliga > 1) {
            scrollmensajes.setVisible(true);
        }
        contenedorataques.setVisible(true);
        labelnombreentrenadorjugador.setVisible(true);
        labelnombreentrenadorIA.setVisible(true);
        labelfotoentrenadorIA.setVisible(true);
        contenedorpokemonsjugador.setVisible(true);
        contenedorpokemonsIA.setVisible(true);
        panelestadisticas.setVisible(true);
        panelcaracteristicas.setVisible(true);
        DesactivarBotonAtaques();
        Juego.InicializaAtaques();
        CrearAtaques();
        System.out.println("ENTRENADOR LIGA: " + entrenadorliga);
        if (entrenadorliga == 1 && !Juego.repetircombate) {
            ListenersBotonAtaques();
        }
        DesarrolloCombate();
    }

    public void ListenersBotonAtaques() {
        for (int i = 0; i < botonataques.length; i++) {
            botonataques[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int k = pokemonencombatejugador;
                    for (int j = 0; j < botonataques.length; j++) {
                        if (e.getSource() == botonataques[j]) {
                            if (Juego.equipojugador[pokemonencombatejugador].turnosincapacitado == 0) {
                                if (Juego.ataquejugador[k][j].pp != 0) {
                                    Juego.ataquejugador[k][j].pp--;
                                    botonataques[j].setText(ataquejugador[k][j].nombre + " " + ataquejugador[k][j].pp + "/" + ataquejugador[k][j].ppmax);
                                    Juego.equipojugador[pokemonencombatejugador].ataqueelegido = ataquejugador[k][j].nombre;
                                    ataqueusadojugador = j;
                                    System.out.println("ESTAMOS AQUI");
                                    try {
                                        try {
                                            ElegirAtaqueIa();
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    System.out.println("NO PUEDES USAR ESE ATAQUE");
                                    JOptionPane.showMessageDialog(null, "¡No te quedan más pps en ese ataque!");
                                }
                                int cont = 0;
                                for (int i = 0; i < ataquejugador[pokemonencombatejugador].length - 1; i++) {
                                    if (ataquejugador[pokemonencombatejugador][i].pp == 0) {
                                        cont++;
                                    }
                                }
                                botonatacar.setVisible(false);
                                if (cont == 4) {
                                    for (int l = 0; l < botonataques.length; l++) {
                                        botonataques[l].setVisible(false);
                                    }
                                    if (!listeneratacar) {
                                        botonatacar.addActionListener(new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                ataqueusadojugador = 4;
                                                Juego.equipojugador[pokemonencombatejugador].ataqueelegido = ataquejugador[pokemonencombatejugador][ataqueusadojugador].nombre;
                                                try {
                                                    ElegirAtaqueIa();
                                                } catch (IOException ex) {
                                                    Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (InterruptedException ex) {
                                                    Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        });
                                        listeneratacar = true;
                                    }
                                    botonatacar.setBounds(0, 0, 720, 140);
                                    botonatacar.setText("Atacar");
                                    botonatacar.setVisible(true);
                                    contenedorataques.add(botonatacar);

                                }
                            } else {
                                Juego.equipojugador[pokemonencombatejugador].ataqueelegido += ",";
                                try {
                                    try {
                                        ElegirAtaqueIa();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
            });

            botonataques[i].addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent evt) {
                    if (!iniciocombate) {
                        for (int i = 0; i < botonataques.length; i++) {
                            if (evt.getSource() == botonataques[i]) {
                                switch (ataquejugador[pokemonencombatejugador][i].tipo) {
                                    case "Acero":
                                        botonataques[i].setBackground(new Color(156, 156, 156));
                                        break;
                                    case "Agua":
                                        botonataques[i].setBackground(new Color(103, 210, 241));
                                        break;
                                    case "Bicho":
                                        botonataques[i].setBackground(new Color(149, 250, 0));
                                        break;
                                    case "Dragon":
                                        botonataques[i].setBackground(new Color(119, 124, 238));
                                        break;
                                    case "Electrico":
                                        botonataques[i].setBackground(new Color(255, 255, 0));
                                        break;
                                    case "Fantasma":
                                        botonataques[i].setBackground(new Color(168, 144, 240));
                                        break;
                                    case "Fuego":
                                        botonataques[i].setBackground(new Color(255, 103, 24));
                                        break;
                                    case "Hada":
                                        botonataques[i].setBackground(new Color(244, 189, 201));
                                        break;
                                    case "Hielo":
                                        botonataques[i].setBackground(new Color(119, 221, 255));
                                        break;
                                    case "Lucha":
                                        botonataques[i].setBackground(new Color(187, 85, 68));
                                        break;
                                    case "Normal":
                                        botonataques[i].setBackground(new Color(216, 216, 192));
                                        break;
                                    case "Planta":
                                        botonataques[i].setBackground(new Color(115, 225, 58));
                                        break;
                                    case "Psiquico":
                                        botonataques[i].setBackground(new Color(216, 88, 136));
                                        break;
                                    case "Roca":
                                        botonataques[i].setBackground(new Color(187, 170, 102));
                                        break;
                                    case "Siniestro":
                                        botonataques[i].setBackground(new Color(153, 120, 98));
                                        break;
                                    case "Tierra":
                                        botonataques[i].setBackground(new Color(252, 226, 99));
                                        break;
                                    case "Veneno":
                                        botonataques[i].setBackground(new Color(216, 128, 184));
                                        break;
                                    case "Volador":
                                        botonataques[i].setBackground(new Color(100, 172, 237));
                                        break;
                                }
                            }
                        }
                    }
                }

                public void mouseExited(MouseEvent evt) {
                    for (int i = 0; i < botonataques.length; i++) {
                        if (evt.getSource() == botonataques[i]) {
                            botonataques[i].setBackground(null);
                        }
                    }
                }
            });
        }
    }

    public void ElegirAtaqueIa() throws IOException, InterruptedException {
        int k = pokemonencombateIA;
        int cont = 0;
        for (int i = 0; i < ataqueIA[k].length - 1; i++) {
            if (ataqueIA[k][i].pp == 0) {
                cont++;
            }
        }
        if (Juego.equipoIA[pokemonencombateIA].turnosincapacitado == 0) {
            if (cont != 4) {
                int eligeataqueIA;
                int pp;
                do {
                    eligeataqueIA = Metodos.RandomSinRound(4);
                    pp = ataqueIA[k][eligeataqueIA].pp;
                } while (pp == 0);
                ataqueIA[k][eligeataqueIA].pp--;
                Juego.equipoIA[pokemonencombateIA].ataqueelegido = ataqueIA[k][eligeataqueIA].nombre;
                ataqueusadoIA = eligeataqueIA;
                Juego.Combate();
            } else {
                equipoIA[pokemonencombatejugador].alalcance = true;
                equipoIA[pokemonencombatejugador].volando = false;
                equipoIA[pokemonencombatejugador].enterrado = false;
                equipoIA[pokemonencombatejugador].sumergido = false;
                ataqueusadoIA = 4;
                Juego.Combate();
            }
        } else {
            System.out.println("ATAQUEDELAIADEM: " + ataqueusadoIA);
            Juego.equipoIA[pokemonencombateIA].ataqueelegido += ",";
            Juego.Combate();
        }
    }

    public void DesarrolloCombate() {
        if (entrenadorliga == 1 && !Juego.repetircombate) {
            Mensajes.setBounds(276, 432, 721, 114);
            Mensajes.disable();
            Mensajes.setDisabledTextColor(Color.BLACK);
            add(Mensajes);
            Mensajes.setFont(new Font("Helvetica", Font.BOLD, 12));
            estadojugador = new JLabel();
            estadoIA = new JLabel();
            scrollmensajes = new JScrollPane(Mensajes);
            scrollmensajes.getVerticalScrollBar().setUnitIncrement(28);
            scrollmensajes.setBounds(275, 432, 721, 114);
            add(scrollmensajes);
        }
        //contenedorimagenjugador.setBackground(Color.red);
        labelfotojugador.setBounds(50, 150, 250, 250);
        panelcombate.add(labelfotojugador);
        labelfotoIA.setBounds(450, 80, 200, 200);
        panelcombate.add(labelfotoIA);

        panelestadojugador.setBounds(316, 309, 400, 80);
        panelestadojugador.setBackground(null);
        panelestadojugador.setVisible(false);
        panelestadojugador.setLayout(null);
        panelestadojugador.add(saludjugador);
        panelestadojugador.add(labelsaludjugador);
        panelestadojugador.add(labelnombrejugador);
        panelestadojugador.add(labelpsjugador);
        panelcombate.add(panelestadojugador);

        labelpsjugador.setBounds(10, 50, 40, 30);
        labelpsjugador.setFont(new Font("Rosewood Std Regular", Font.BOLD, 30));
        labelpsjugador.setText("PS");
        estadojugador.setBounds(330, 0, 70, 50);
        panelestadojugador.add(estadojugador);

        saludjugador.setBounds(60, 40, 340, 40);
        saludjugador.setBackground(Color.green);
        saludjugador.setVisible(false);

        labelsaludjugador.setBounds(220, 10, 100, 20);
        labelsaludjugador.setFont(new Font("Helvetica", Font.BOLD, 15));

        labelnombrejugador.setBounds(10, 10, 100, 20);
        labelnombrejugador.setFont(new Font("Helvetica", Font.BOLD, 15));

        panelestadoIA.setBounds(3, 3, 400, 80);
        panelestadoIA.setBackground(null);
        panelestadoIA.setVisible(false);
        panelestadoIA.setLayout(null);
        panelestadoIA.add(saludIA);
        panelestadoIA.add(labelsaludIA);
        panelestadoIA.add(labelnombreIA);
        panelestadoIA.add(labelpsIA);
        panelcombate.add(panelestadoIA);

        labelpsIA.setBounds(10, 50, 40, 30);
        labelpsIA.setFont(new Font("Rosewood Std Regular", Font.BOLD, 30));
        labelpsIA.setText("PS");

        estadoIA.setBounds(330, 0, 70, 50);
        panelestadoIA.add(estadoIA);

        saludIA.setBounds(60, 40, 340, 40);
        saludIA.setBackground(Color.green);
        saludIA.setVisible(false);

        labelsaludIA.setBounds(220, 10, 100, 20);
        labelsaludIA.setFont(new Font("Helvetica", Font.BOLD, 15));

        labelnombreIA.setBounds(10, 10, 100, 20);
        labelnombreIA.setFont(new Font("Helvetica", Font.BOLD, 15));
        if (entrenadorliga == 1) {
            BotonSiguienteEntrenador();
            BotonEstadisticasVictorias();
        }
    }

    public void BotonEstadisticasVictorias() {
        for (int i = 0; i < botonataques.length; i++) {
            botonataques[i].setEnabled(false);
            botoncambiarpokemon.setEnabled(false);
        }
        botonestadisticasvictorias.setBounds(360, 140, 360, 44);
        contenedorataques.add(botonestadisticasvictorias);
        botonestadisticasvictorias.setVisible(false);
        botonestadisticasvictorias.setFocusable(false);
        botonestadisticasvictorias.setText("Estadisticas Victorias");
        botonestadisticasvictorias.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                botonestadisticasvictorias.setVisible(false);
                if (!modocarrera) {
                    try {
                        MenuEstadisticasVictorias();
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        MenuHallFama();
                    } catch (IOException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void MenuEstadisticasVictorias() throws IOException {
        if (!menuinicial) {
            MusicFinCombate.stop();
            MusicHallFama = new JLayerLoop("HallFama/HallFama.mp3", true);
            MusicHallFama.play();
            sliderecualizador.setVisible(false);
            contenedorpokemonsjugador.setVisible(false);
            contenedorpokemonsIA.setVisible(false);
            panelcombate.setVisible(false);
            Mensajes.setVisible(false);
            scrollmensajes.setVisible(false);
            contenedorataques.setVisible(false);
            panelestadisticas.setVisible(false);
            panelcaracteristicas.setVisible(false);
            labelnombreentrenadorjugador.setVisible(false);
            labelnombreentrenadorIA.setVisible(false);
            labelfotoentrenadorIA.setVisible(false);
        } else {
            MusicIntro.stop();
            MusicHallFama = new JLayerLoop("HallFama/HallFama.mp3", true);
            MusicHallFama.play();
            setSize(1280, 860);
            Centrar(this);
        }
        fotohallfama.setBounds(510, 5, 251, 121);
        fotohallfama.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Miscelanea/Pokemon.png")).getImage()).getScaledInstance(250, 120, java.awt.Image.SCALE_SMOOTH)));
        add(fotohallfama);

        int l = 0;
        contenedorlistaestadisticas.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        String[][] Poke = Lecturas.MostrarEstadisticasVicOrdenadas();
        Pokemon p;
        for (int i = 0; i < Poke.length; i++) {
            for (int j = 0; j < Poke[i].length; j++) {
                System.out.print(Poke[i][j] + " | ");
            }
            System.out.println("");
        }
        tipo1estadisticas = new JLabel[Poke.length];
        tipo2estadisticas = new JLabel[Poke.length];
        botonlistaestadisticas = new JButton[Poke.length];
        for (int i = 0; i < botonlistaestadisticas.length; i++) {
            p = Pokemon.PokemonElegido(bdpokemon, Integer.parseInt(Poke[i][0]));
            //System.out.println(i);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            if (i % 3 == 0) {
                l = 0;
            }
            c.gridx = l;
            c.gridy = i - l;
            botonlistaestadisticas[i] = new JButton();
            botonlistaestadisticas[i].setText((i + 1) + " - " + p.nombre + " → " + Poke[i][1]);
            //botonelegirpokemon[i].setFont(new Font("Helvetica", Font.BOLD, 15));

            botonlistaestadisticas[i].setLayout(new BoxLayout(botonlistaestadisticas[i], BoxLayout.Y_AXIS));
            botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 18)));
            botonlistaestadisticas[i].setFocusable(false);
            tipo1estadisticas[i] = new JLabel();
            //tipo1[i].setBackground(Color.red);
            tipo1estadisticas[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo1 + ".gif"));
            botonlistaestadisticas[i].add(tipo1estadisticas[i]);
            botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 10)));
            tipo2estadisticas[i] = new JLabel();
            //tipo2[i].setBackground(Color.red);
            if (p.tipo2.compareTo("n*n*") != 0) {
                tipo2estadisticas[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo2 + ".gif"));
            } else {
                botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 18)));
            }
            botonlistaestadisticas[i].add(tipo2estadisticas[i]);

            botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 18)));
            botonlistaestadisticas[i].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + p.codigo + ".png"));
            contenedorlistaestadisticas.add(botonlistaestadisticas[i], c);
            l++;
        }
        scrolllistaestadisticas = new JScrollPane(contenedorlistaestadisticas);
        scrolllistaestadisticas.getVerticalScrollBar().setUnitIncrement(28);
        scrolllistaestadisticas.setBounds(20, 139, 1240, 640);
        add(scrolllistaestadisticas);
    }

    public void MenuHallFama() throws IOException {
        if (!menuinicial) {
            MusicFinCombate.stop();
            MusicHallFama = new JLayerLoop("HallFama/HallFama.mp3", true);
            MusicHallFama.play();
            contenedorpokemonsjugador.setVisible(false);
            contenedorpokemonsIA.setVisible(false);
            panelcombate.setVisible(false);
            Mensajes.setVisible(false);
            scrollmensajes.setVisible(false);
            contenedorataques.setVisible(false);
            panelestadisticas.setVisible(false);
            panelcaracteristicas.setVisible(false);
            labelnombreentrenadorjugador.setVisible(false);
            labelnombreentrenadorIA.setVisible(false);
            labelfotoentrenadorIA.setVisible(false);
            sliderecualizador.setVisible(false);
        } else {
            MusicIntro.stop();
            MusicHallFama = new JLayerLoop("HallFama/HallFama.mp3", true);
            MusicHallFama.play();
        }
        setSize(1280, 860);
        Centrar(this);
        if (!volverhallfama) {
            fotohallfama.setBounds(510, 5, 251, 121);
            String foto;
            if (kanto) {
                foto = "Kanto";
            } else if (johto) {
                foto = "Johto";
            } else if (hoenn) {
                foto = "Hoenn";
            } else if (sinnoh) {
                foto = "Sinnoh";
            } else {
                foto = "Teselia";
            }
            fotohallfama.setIcon(new ImageIcon(((new ImageIcon("Recursos/Imagenes/Miscelanea/Pokemon " + foto + ".png")).getImage()).getScaledInstance(250, 120, java.awt.Image.SCALE_SMOOTH)));
            add(fotohallfama);
            int l = 0;
            contenedorlistaestadisticas.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            String[][] Poke = Lecturas.MostrarHallFamaOrdenado();
            Pokemon p;
            for (int i = 0; i < Poke.length; i++) {
                for (int j = 0; j < Poke[i].length; j++) {
                    System.out.print(Poke[i][j] + " | ");
                }
                System.out.println("");
            }
            tipo1estadisticas = new JLabel[Poke.length];
            tipo2estadisticas = new JLabel[Poke.length];
            botonlistaestadisticas = new JButton[Poke.length];
            for (int i = 0; i < botonlistaestadisticas.length; i++) {
                p = Pokemon.PokemonElegido(bdpokemon, Integer.parseInt(Poke[i][0]));
                //System.out.println(i);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.5;
                if (i % 3 == 0) {
                    l = 0;
                }
                c.gridx = l;
                c.gridy = i - l;
                botonlistaestadisticas[i] = new JButton();
                botonlistaestadisticas[i].setText(p.codigo + " - " + p.nombre + " → " + Poke[i][1]);
                //botonelegirpokemon[i].setFont(new Font("Helvetica", Font.BOLD, 15));

                botonlistaestadisticas[i].setLayout(new BoxLayout(botonlistaestadisticas[i], BoxLayout.Y_AXIS));
                botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 18)));
                botonlistaestadisticas[i].setFocusable(false);
                tipo1estadisticas[i] = new JLabel();
                //tipo1[i].setBackground(Color.red);
                tipo1estadisticas[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo1 + ".gif"));
                botonlistaestadisticas[i].add(tipo1estadisticas[i]);
                botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 10)));
                tipo2estadisticas[i] = new JLabel();
                //tipo2[i].setBackground(Color.red);
                if (p.tipo2.compareTo("n*n*") != 0) {
                    tipo2estadisticas[i].setIcon(new ImageIcon("Recursos/Imagenes/Tipos/" + p.tipo2 + ".gif"));
                } else {
                    botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 18)));
                }
                botonlistaestadisticas[i].add(tipo2estadisticas[i]);

                botonlistaestadisticas[i].add(Box.createRigidArea(new Dimension(5, 18)));
                botonlistaestadisticas[i].setIcon(new ImageIcon("Recursos/Imagenes/Frente/" + p.codigo + ".png"));
                contenedorlistaestadisticas.add(botonlistaestadisticas[i], c);
                l++;
            }
            scrolllistaestadisticas = new JScrollPane(contenedorlistaestadisticas);
            scrolllistaestadisticas.getVerticalScrollBar().setUnitIncrement(28);
            scrolllistaestadisticas.setBounds(20, 139, 1240, 640);
            add(scrolllistaestadisticas);
        }
        volverhallfama = false;
    }

    public void Centrar(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void IniciarDBOO() throws IOException {
        ConectarPokemon("bdoo/pokemon.db4o");
        ConectarAtaque("bdoo/ataque.db4o");
        Lecturas.ContarNumeroPokemons();
        Lecturas.ContarNumeroAtaques();
        //Pokemon.BorrarTodo(bdpokemon);
        //Lecturas.IntroducirDatosPokemon(bdpokemon);
        //Pokemon.MostrarTodo(bdpokemon);
        //Ataque.BorrarTodo(bdataque);
        //Lecturas.IntroducirDatosAtaque(bdataque);
        //Ataque.MostrarTodo(bdataque);
    }

    public static void CerrarDBOO() {
        bdpokemon.close();
        bdataque.close();
    }

    public static void main(String[] args) throws IOException {
        IniciarDBOO();
        MusicIntro = new JLayerLoop("Intro.mp3", true);
        MusicIntro.play();

        controladorsonido = new Ecualizador();
        controladorsonido.setVolume(50);

        pokemongui = new PokemonGUI();
    }

    public static void RefrescoVidaJugador() {
        if (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].salud == Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax) {
            PokemonGUI.saludjugador.setBackground(Color.green);
            saludequipojugador[pokemonencombatejugador].setBackground(Color.green);
            PokemonGUI.labelsaludjugador.setText((int) Juego.equipojugador[PokemonGUI.pokemonencombatejugador].salud + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
            PokemonGUI.saludjugador.setBounds(60, 40, 340, 40);
        } else {
            double porcentaje = Juego.equipojugador[PokemonGUI.pokemonencombatejugador].salud / Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax;
            if ((porcentaje * 100) > 50) {
                PokemonGUI.saludjugador.setBackground(Color.green);

            } else if ((porcentaje * 100) > 20) {
                PokemonGUI.saludjugador.setBackground(Color.yellow);
            } else {
                PokemonGUI.saludjugador.setBackground(Color.red);
            }
            int anchobarra;
            if ((340 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 340) && (340 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (340 * porcentaje);
            }
            PokemonGUI.labelsaludjugador.setText((int) Juego.equipojugador[PokemonGUI.pokemonencombatejugador].salud + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
            PokemonGUI.saludjugador.setBounds(60, 40, anchobarra, 40);
        }
    }

    public static void RefrescoVidaIA() {
        if (Juego.equipoIA[PokemonGUI.pokemonencombateIA].salud == Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax) {
            PokemonGUI.saludIA.setBackground(Color.green);
            saludequipoIA[pokemonencombateIA].setBackground(Color.green);
            PokemonGUI.labelsaludIA.setText((int) Juego.equipoIA[PokemonGUI.pokemonencombateIA].salud + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
            PokemonGUI.saludIA.setBounds(60, 40, 340, 40);
        } else {
            double porcentaje = Juego.equipoIA[PokemonGUI.pokemonencombateIA].salud / Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax;
            if ((porcentaje * 100) > 50) {
                PokemonGUI.saludIA.setBackground(Color.green);
            } else if ((porcentaje * 100) > 20) {
                PokemonGUI.saludIA.setBackground(Color.yellow);
            } else {
                PokemonGUI.saludIA.setBackground(Color.red);
            }
            int anchobarra;
            if ((340 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 340) && (340 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (340 * porcentaje);
            }
            PokemonGUI.labelsaludIA.setText((int) Juego.equipoIA[PokemonGUI.pokemonencombateIA].salud + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
            PokemonGUI.saludIA.setBounds(60, 40, anchobarra, 40);
        }
    }

    public static void BarraDeVidaJugador() {
        ListenerBarraJugador p = new ListenerBarraJugador();
        p.start();
    }

    public static void BarraDeVidaIA() {
        ListenerBarraIA p = new ListenerBarraIA();
        p.start();
    }

    public static void BajarBarraJugadorPocoAPoco(boolean autoda) {
        if (!autoda) {
            if (Juego.equipoIA[PokemonGUI.pokemonencombateIA].golpe < 0) {
                Juego.equipoIA[PokemonGUI.pokemonencombateIA].golpe = 0;
            }
        }
        double saludgolpe;
        saludgolpe = Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludantesgolpe;

        double porcentaje = saludgolpe / Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax;
        if ((porcentaje * 100) > 50) {
            PokemonGUI.saludjugador.setBackground(Color.green);
            saludequipojugador[pokemonencombatejugador].setBackground(Color.green);

        } else if ((porcentaje * 100) > 20) {
            PokemonGUI.saludjugador.setBackground(Color.yellow);
            saludequipojugador[pokemonencombatejugador].setBackground(Color.yellow);

        } else {
            PokemonGUI.saludjugador.setBackground(Color.red);
            saludequipojugador[pokemonencombatejugador].setBackground(Color.red);

        }
        int anchobarra;
        if ((340 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 340) && (340 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (340 * porcentaje);
        }
        PokemonGUI.labelsaludjugador.setText((int) Juego.equipojugador[pokemonencombatejugador].salud + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
        PokemonGUI.saludjugador.setBounds(60, 40, anchobarra, 40);
        if ((97 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 97) && (97 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (97 * porcentaje);
        }
        saludequipojugador[pokemonencombatejugador].setBounds(135, 70, anchobarra, 7);
        for (int i = (int) saludgolpe; i >= Juego.equipojugador[PokemonGUI.pokemonencombatejugador].salud; i--) {
            porcentaje = i / Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax;
            if ((porcentaje * 100) > 50) {
                PokemonGUI.saludjugador.setBackground(Color.green);
                saludequipojugador[pokemonencombatejugador].setBackground(Color.green);
            } else if ((porcentaje * 100) > 20) {
                PokemonGUI.saludjugador.setBackground(Color.yellow);
                saludequipojugador[pokemonencombatejugador].setBackground(Color.yellow);
            } else {
                PokemonGUI.saludjugador.setBackground(Color.red);
                saludequipojugador[pokemonencombatejugador].setBackground(Color.red);
            }
            if ((340 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 340) && (340 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (340 * porcentaje);
            }
            PokemonGUI.labelsaludjugador.setText(i + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
            PokemonGUI.saludjugador.setBounds(60, 40, anchobarra, 40);
            if ((97 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 97) && (97 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (97 * porcentaje);
            }
            saludequipojugador[pokemonencombatejugador].setBounds(135, 70, anchobarra, 7);
            lblsaludequipojugador[pokemonencombatejugador].setText(i + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i);
        }
        PokemonGUI.labelsaludjugador.setText((int) Juego.equipojugador[pokemonencombatejugador].salud + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
        if (Juego.equipojugador[pokemonencombatejugador].salud <= 0) {
            Juego.equipojugador[pokemonencombatejugador].salud = 0;
            botonpokemonelegidosJugador[pokemonencombatejugador].setEnabled(false);
        }
    }

    public static void BajarBarraIAPocoAPoco(boolean autoda) {
        double saludgolpe;
        double porcentaje;
        int anchobarra;
        if (!autoda) {
            if (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].golpe < 0) {
                Juego.equipojugador[PokemonGUI.pokemonencombatejugador].golpe = 0;
            }
        }
        saludgolpe = Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludantesgolpe;

        porcentaje = saludgolpe / Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax;
        System.out.println(saludgolpe);
        if (saludgolpe <= 0) {
            saludgolpe = 0;
        }
        if ((porcentaje * 100) > 50) {
            PokemonGUI.saludIA.setBackground(Color.green);
            saludequipoIA[pokemonencombateIA].setBackground(Color.green);

        } else if ((porcentaje * 100) > 20) {
            PokemonGUI.saludIA.setBackground(Color.yellow);
            saludequipoIA[pokemonencombateIA].setBackground(Color.yellow);
        } else {
            PokemonGUI.saludIA.setBackground(Color.red);
            saludequipoIA[pokemonencombateIA].setBackground(Color.red);
        }
        if ((340 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 340) && (340 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (340 * porcentaje);
        }
        PokemonGUI.labelsaludIA.setText((int) saludgolpe + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
        PokemonGUI.saludIA.setBounds(60, 40, anchobarra, 40);

        if ((97 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 97) && (97 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (97 * porcentaje);
        }
        saludequipoIA[pokemonencombateIA].setBounds(135, 70, anchobarra, 7);

        for (int i = (int) saludgolpe; i >= Juego.equipoIA[PokemonGUI.pokemonencombateIA].salud; i--) {
            porcentaje = i / Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax;
            if ((porcentaje * 100) > 50) {
                PokemonGUI.saludIA.setBackground(Color.green);
                saludequipoIA[pokemonencombateIA].setBackground(Color.green);
            } else if ((porcentaje * 100) > 20) {
                PokemonGUI.saludIA.setBackground(Color.yellow);
                saludequipoIA[pokemonencombateIA].setBackground(Color.yellow);
            } else {
                PokemonGUI.saludIA.setBackground(Color.red);
                saludequipoIA[pokemonencombateIA].setBackground(Color.red);
            }
            if ((340 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 340) && (340 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (340 * porcentaje);
            }
            PokemonGUI.labelsaludIA.setText(i + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
            PokemonGUI.saludIA.setBounds(60, 40, anchobarra, 40);

            if ((97 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 97) && (97 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (97 * porcentaje);
            }
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            saludequipoIA[pokemonencombateIA].setBounds(135, 70, anchobarra, 7);
            lblsaludequipoIA[pokemonencombateIA].setText(i + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
            System.out.println(i);
        }
        if (Juego.equipoIA[pokemonencombateIA].salud <= 0) {
            botonpokemonelegidosIA[pokemonencombateIA].setEnabled(false);
            Juego.equipoIA[pokemonencombateIA].salud = 0;
        }
        PokemonGUI.labelsaludIA.setText((int) Juego.equipoIA[pokemonencombateIA].salud + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
    }

    public static void SubirBarraJugadorPocoAPoco() {
        double saludantesgolpe;
        saludantesgolpe = Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludantesgolpe;

        double porcentaje = saludantesgolpe / Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax;
        if ((porcentaje * 100) > 50) {
            PokemonGUI.saludjugador.setBackground(Color.green);
            saludequipojugador[pokemonencombatejugador].setBackground(Color.green);

        } else if ((porcentaje * 100) > 20) {
            PokemonGUI.saludjugador.setBackground(Color.yellow);
            saludequipojugador[pokemonencombatejugador].setBackground(Color.yellow);

        } else {
            PokemonGUI.saludjugador.setBackground(Color.red);
            saludequipojugador[pokemonencombatejugador].setBackground(Color.red);

        }
        int anchobarra;
        if ((340 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 340) && (340 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (340 * porcentaje);
        }
        PokemonGUI.labelsaludjugador.setText((int) Juego.equipojugador[pokemonencombatejugador].salud + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
        PokemonGUI.saludjugador.setBounds(60, 40, anchobarra, 40);
        if ((97 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 97) && (97 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (97 * porcentaje);
        }
        saludequipojugador[pokemonencombatejugador].setBounds(135, 70, anchobarra, 7);
        for (int i = (int) saludantesgolpe; i <= Juego.equipojugador[PokemonGUI.pokemonencombatejugador].salud; i++) {
            porcentaje = i / Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax;
            if ((porcentaje * 100) > 50) {
                PokemonGUI.saludjugador.setBackground(Color.green);
                saludequipojugador[pokemonencombatejugador].setBackground(Color.green);
            } else if ((porcentaje * 100) > 20) {
                PokemonGUI.saludjugador.setBackground(Color.yellow);
                saludequipojugador[pokemonencombatejugador].setBackground(Color.yellow);
            } else {
                PokemonGUI.saludjugador.setBackground(Color.red);
                saludequipojugador[pokemonencombatejugador].setBackground(Color.red);
            }
            if ((340 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 340) && (340 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (340 * porcentaje);
            }
            PokemonGUI.labelsaludjugador.setText(i + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
            PokemonGUI.saludjugador.setBounds(60, 40, anchobarra, 40);
            if ((97 * porcentaje) <= (Juego.equipojugador[PokemonGUI.pokemonencombatejugador].saludmax / 97) && (97 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (97 * porcentaje);
            }
            saludequipojugador[pokemonencombatejugador].setBounds(135, 70, anchobarra, 7);
            lblsaludequipojugador[pokemonencombatejugador].setText(i + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i);
        }
        PokemonGUI.labelsaludjugador.setText((int) Juego.equipojugador[pokemonencombatejugador].salud + "/" + (int) Juego.equipojugador[pokemonencombatejugador].saludmax);
        if (Juego.equipojugador[pokemonencombatejugador].salud <= 0) {
            Juego.equipojugador[pokemonencombatejugador].salud = 0;
            botonpokemonelegidosJugador[pokemonencombatejugador].setEnabled(false);
        }
    }

    public static void SubirBarraIAPocoAPoco() {
        double saludantesgolpe;
        double porcentaje;
        int anchobarra;
        saludantesgolpe = Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludantesgolpe;

        porcentaje = saludantesgolpe / Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax;
        System.out.println(saludantesgolpe);
        if (saludantesgolpe <= 0) {
            saludantesgolpe = 0;
        }
        if ((porcentaje * 100) > 50) {
            PokemonGUI.saludIA.setBackground(Color.green);
            saludequipoIA[pokemonencombateIA].setBackground(Color.green);

        } else if ((porcentaje * 100) > 20) {
            PokemonGUI.saludIA.setBackground(Color.yellow);
            saludequipoIA[pokemonencombateIA].setBackground(Color.yellow);
        } else {
            PokemonGUI.saludIA.setBackground(Color.red);
            saludequipoIA[pokemonencombateIA].setBackground(Color.red);
        }
        if ((340 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 340) && (340 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (340 * porcentaje);
        }
        PokemonGUI.labelsaludIA.setText((int) saludantesgolpe + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
        PokemonGUI.saludIA.setBounds(60, 40, anchobarra, 40);

        if ((97 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 97) && (97 * porcentaje) > 0) {
            anchobarra = 1;
        } else {
            anchobarra = (int) (97 * porcentaje);
        }
        saludequipoIA[pokemonencombateIA].setBounds(135, 70, anchobarra, 7);

        for (int i = (int) saludantesgolpe; i <= Juego.equipoIA[PokemonGUI.pokemonencombateIA].salud; i++) {
            porcentaje = i / Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax;
            if ((porcentaje * 100) > 50) {
                PokemonGUI.saludIA.setBackground(Color.green);
                saludequipoIA[pokemonencombateIA].setBackground(Color.green);
            } else if ((porcentaje * 100) > 20) {
                PokemonGUI.saludIA.setBackground(Color.yellow);
                saludequipoIA[pokemonencombateIA].setBackground(Color.yellow);
            } else {
                PokemonGUI.saludIA.setBackground(Color.red);
                saludequipoIA[pokemonencombateIA].setBackground(Color.red);
            }
            if ((340 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 340) && (340 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (340 * porcentaje);
            }
            PokemonGUI.labelsaludIA.setText(i + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
            PokemonGUI.saludIA.setBounds(60, 40, anchobarra, 40);

            if ((97 * porcentaje) <= (Juego.equipoIA[PokemonGUI.pokemonencombateIA].saludmax / 97) && (97 * porcentaje) > 0) {
                anchobarra = 1;
            } else {
                anchobarra = (int) (97 * porcentaje);
            }
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            saludequipoIA[pokemonencombateIA].setBounds(135, 70, anchobarra, 7);
            lblsaludequipoIA[pokemonencombateIA].setText(i + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
            System.out.println(i);
        }
        if (Juego.equipoIA[pokemonencombateIA].salud <= 0) {
            botonpokemonelegidosIA[pokemonencombateIA].setEnabled(false);
            Juego.equipoIA[pokemonencombateIA].salud = 0;
        }
        PokemonGUI.labelsaludIA.setText((int) Juego.equipoIA[pokemonencombateIA].salud + "/" + (int) Juego.equipoIA[pokemonencombateIA].saludmax);
    }

    public static class BarraDeVidaJugador implements Runnable {

        public void run() {
            DesactivarBotonAtaques();
            if (equipoIA[pokemonencombateIA].turnosincapacitado == 0 && equipoIA[pokemonencombateIA].pasar == 0
                    && !equipoIA[pokemonencombateIA].descansar && !equipoIA[pokemonencombateIA].noatacar) {
                if (equipoIA[pokemonencombateIA].ataqueelegido.compareTo("Descansar") != 0) {
                    try {
                        panelcombate.PintarAtaque(equipoIA[pokemonencombateIA], ataqueIA[pokemonencombateIA][ataqueusadoIA]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        panelcombate.PintarAtaque(equipoIA[pokemonencombateIA], ataqueIA[pokemonencombateIA][ataqueusadoIA]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    equipoIA[pokemonencombateIA].descansar = true;
                }
            } else {
                equipoIA[pokemonencombateIA].descansar = false;
                equipoIA[pokemonencombateIA].noatacar = false;
            }
            ataquejugador[pokemonencombatejugador][ataqueusadojugador] = ataquejugador[pokemonencombatejugador][ataqueusadojugador].DevolverAtaqueMetronomo(ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
            if (equipoIA[pokemonencombateIA].salud > equipoIA[pokemonencombateIA].saludantesgolpe) {
                SubirBarraIAPocoAPoco();
                equipoIA[pokemonencombateIA].saludantesgolpe = equipoIA[pokemonencombateIA].salud;
            }
            BajarBarraIAPocoAPoco(true);
            equipoIA[pokemonencombateIA].saludantesgolpe = equipoIA[pokemonencombateIA].salud;
            BajarBarraJugadorPocoAPoco(false);
            Mensajes.setText(str);
            MostrarEstado();
            if (!PokemonGUI.atacaiaporcambiarpokemonjugador && equipojugador[pokemonencombatejugador].Vivo() && equipoIA[pokemonencombateIA].Vivo()) {
                ataquejugador[pokemonencombatejugador][ataqueusadojugador] = ataquejugador[pokemonencombatejugador][ataqueusadojugador].Metronomo(ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
                str += equipojugador[pokemonencombatejugador].nombre + " usó " + ataquejugador[pokemonencombatejugador][ataqueusadojugador].nombre + ".\r\n";
                PokemonGUI.Mensajes.setText(str);
                try {
                    str += equipojugador[pokemonencombatejugador].Atacar(equipojugador[pokemonencombatejugador], equipoIA[pokemonencombateIA], ataquejugador[pokemonencombatejugador][ataqueusadojugador], equipojugador, ataquejugador[pokemonencombatejugador]) + "\r\n";
                } catch (IOException ex) {
                    Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                str += ataquejugador[pokemonencombatejugador][ataqueusadojugador].AtaquesTurnosJugador(equipojugador);
            } else {
                equipojugador[pokemonencombatejugador].saludantesgolpe = equipojugador[pokemonencombatejugador].salud;
            }
            if (equipojugador[pokemonencombatejugador].turnosincapacitado == 0 && equipojugador[pokemonencombatejugador].pasar == 0
                    && equipojugador[pokemonencombatejugador].Vivo() && !equipojugador[pokemonencombatejugador].descansar
                    && !atacaiaporcambiarpokemonjugador && !equipojugador[pokemonencombatejugador].noatacar
                    && equipoIA[pokemonencombateIA].saludantesgolpe > 0) {
                if (equipojugador[pokemonencombatejugador].ataqueelegido.compareTo("Descansar") != 0) {
                    try {
                        panelcombate.PintarAtaque(equipojugador[pokemonencombatejugador], ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        panelcombate.PintarAtaque(equipojugador[pokemonencombatejugador], ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    equipojugador[pokemonencombatejugador].descansar = true;
                }

            } else {
                equipojugador[pokemonencombatejugador].descansar = false;
                equipojugador[pokemonencombatejugador].noatacar = false;
            }
            ataquejugador[pokemonencombatejugador][ataqueusadojugador] = ataquejugador[pokemonencombatejugador][ataqueusadojugador].DevolverAtaqueMetronomo(ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
            if (equipojugador[pokemonencombatejugador].salud > equipojugador[pokemonencombatejugador].saludantesgolpe) {
                SubirBarraJugadorPocoAPoco();
                equipojugador[pokemonencombatejugador].saludantesgolpe = equipojugador[pokemonencombatejugador].salud;
            }
            BajarBarraJugadorPocoAPoco(true);
            BajarBarraIAPocoAPoco(false);
            PokemonGUI.Mensajes.setText(str);
            try {
                ComprobacionesDespuesAtacar();
            } catch (IOException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            PokemonGUI.Mensajes.setText(str);
            MostrarEstado();
            /*
             * for (int i = 0; i < botonataques.length; i++) {
             * //botonataques[i].setText(null);
             * //fotoataquejugador[i].setIcon(null);
             * botonataques[i].setEnabled(true); }
             * botoncambiarpokemon.setEnabled(true);
             */
        }
    }

    public static class ListenerBarraJugador {

        public void start() {
            new Thread(new BarraDeVidaJugador()).start();
        }
    }

    public static class BarraDeVidaIA implements Runnable {

        public void run() {
            DesactivarBotonAtaques();
            if (equipojugador[pokemonencombatejugador].turnosincapacitado == 0
                    && equipojugador[pokemonencombatejugador].pasar == 0 && !equipojugador[pokemonencombatejugador].descansar
                    && !atacaiaporcambiarpokemonjugador && !equipojugador[pokemonencombatejugador].noatacar) {
                if (equipojugador[pokemonencombatejugador].ataqueelegido.compareTo("Descansar") != 0) {
                    try {
                        panelcombate.PintarAtaque(equipojugador[pokemonencombatejugador], ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        panelcombate.PintarAtaque(equipojugador[pokemonencombatejugador], ataquejugador[pokemonencombatejugador][ataqueusadojugador]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    equipojugador[pokemonencombatejugador].descansar = true;
                }
            } else {
                equipojugador[pokemonencombatejugador].descansar = false;
                equipojugador[pokemonencombatejugador].noatacar = false;
            }
            ataqueIA[pokemonencombateIA][ataqueusadoIA] = ataqueIA[pokemonencombateIA][ataqueusadoIA].DevolverAtaqueMetronomo(ataqueIA[pokemonencombateIA][ataqueusadoIA]);
            if (equipojugador[pokemonencombatejugador].salud > equipojugador[pokemonencombatejugador].saludantesgolpe) {
                SubirBarraJugadorPocoAPoco();
                equipojugador[pokemonencombatejugador].saludantesgolpe = equipojugador[pokemonencombatejugador].salud;
            }
            BajarBarraJugadorPocoAPoco(true);
            equipojugador[pokemonencombatejugador].saludantesgolpe = equipojugador[pokemonencombatejugador].salud;
            BajarBarraIAPocoAPoco(false);

            PokemonGUI.Mensajes.setText(str);
            MostrarEstado();
            if (equipojugador[pokemonencombatejugador].Vivo() && equipoIA[pokemonencombateIA].Vivo()) {
                ataqueIA[pokemonencombateIA][ataqueusadoIA] = ataqueIA[pokemonencombateIA][ataqueusadoIA].Metronomo(ataqueIA[pokemonencombateIA][ataqueusadoIA]);
                str += equipoIA[pokemonencombateIA].nombre + " usó " + ataqueIA[pokemonencombateIA][ataqueusadoIA].nombre + ".\r\n";
                PokemonGUI.Mensajes.setText(str);
                try {
                    str += equipoIA[pokemonencombateIA].Atacar(equipoIA[pokemonencombateIA], equipojugador[pokemonencombatejugador], ataqueIA[pokemonencombateIA][ataqueusadoIA], equipoIA, ataqueIA[pokemonencombateIA]) + "\r\n";
                } catch (IOException ex) {
                    Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                str += ataqueIA[pokemonencombateIA][ataqueusadoIA].AtaquesTurnosIA(equipoIA);
            } else {
                equipoIA[pokemonencombateIA].saludantesgolpe = equipoIA[pokemonencombateIA].salud;
            }
            if (equipoIA[pokemonencombateIA].turnosincapacitado == 0 && equipoIA[pokemonencombateIA].pasar == 0
                    && equipoIA[pokemonencombateIA].Vivo() && !equipoIA[pokemonencombateIA].descansar && !equipoIA[pokemonencombateIA].noatacar
                    && equipojugador[pokemonencombatejugador].saludantesgolpe > 0) {
                if (equipoIA[pokemonencombateIA].ataqueelegido.compareTo("Descansar") != 0) {
                    try {
                        panelcombate.PintarAtaque(equipoIA[pokemonencombateIA], ataqueIA[pokemonencombateIA][ataqueusadoIA]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        panelcombate.PintarAtaque(equipoIA[pokemonencombateIA], ataqueIA[pokemonencombateIA][ataqueusadoIA]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    equipoIA[pokemonencombateIA].descansar = true;
                }
            } else {
                equipoIA[pokemonencombateIA].descansar = false;
                equipoIA[pokemonencombateIA].noatacar = false;
            }
            ataqueIA[pokemonencombateIA][ataqueusadoIA] = ataqueIA[pokemonencombateIA][ataqueusadoIA].DevolverAtaqueMetronomo(ataqueIA[pokemonencombateIA][ataqueusadoIA]);
            if (equipoIA[pokemonencombateIA].salud > equipoIA[pokemonencombateIA].saludantesgolpe) {
                SubirBarraIAPocoAPoco();
                equipoIA[pokemonencombateIA].saludantesgolpe = equipoIA[pokemonencombateIA].salud;
            }
            BajarBarraIAPocoAPoco(true);
            BajarBarraJugadorPocoAPoco(false);
            PokemonGUI.Mensajes.setText(str);

            try {
                ComprobacionesDespuesAtacar();
            } catch (IOException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            MostrarEstado();
            PokemonGUI.Mensajes.setText(str);
            /*
             * for (int i = 0; i < botonataques.length; i++) {
             * //botonataques[i].setText(null);
             * //fotoataquejugador[i].setIcon(null);
             * botonataques[i].setEnabled(true); }
             * botoncambiarpokemon.setEnabled(true);
             */
        }
    }

    public static class ListenerBarraIA {

        public void start() {
            new Thread(new BarraDeVidaIA()).start();
        }
    }
}
