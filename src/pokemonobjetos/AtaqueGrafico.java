package pokemonobjetos;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

/**
 *
 * @author Víctor Jurado Usón
 */
public class AtaqueGrafico {

    public int x;
    public int y;
    public boolean movx = true;
    public boolean movy = true;
    public int ancho;
    public boolean masancho;
    public int alto;
    public boolean masalto;
    public Color colorTipo;
    public Image dibujo;
    public static int maxanchoaltoJugador = 100;
    public static int maxanchoaltoIA = 50;
    public static boolean equijugador;
    public static String efectovisual;
    public static boolean fondo;
    public static int contadorrayo;

    public AtaqueGrafico(Pokemon p, Ataque a) {
        fondo = false;
        efectovisual = a.efectovisual;
        switch (a.efectovisual) {
            case "D":
            case "DV":
                if (p.equijugador) {
                    equijugador = true;
                    x = 220;
                    y = 210;
                    ancho = maxanchoaltoJugador;
                    alto = maxanchoaltoJugador;
                    movy = false;
                } else {
                    equijugador = false;
                    x = 520;
                    y = 150;
                    ancho = 50;
                    alto = 50;
                    movx = false;
                }
                break;
            case "R":
                if (p.equijugador) {
                    equijugador = true;
                    x = 520;
                    y = 150;
                    ancho = maxanchoaltoJugador;
                    alto = maxanchoaltoJugador;
                } else {
                    equijugador = false;
                    x = 130;
                    y = 210;
                    ancho = 50;
                    alto = 50;
                }
                break;
            case "P":
                if (p.equijugador) {
                    equijugador = true;
                    x = 100;
                    y = 190;
                    ancho = maxanchoaltoJugador;
                    alto = maxanchoaltoJugador;
                } else {
                    equijugador = false;
                    x = 490;
                    y = 130;
                    ancho = 50;
                    alto = 50;
                }
                break;
            case "F":
                fondo = true;
                x = -50;
                y = -20;
                alto = 771;
                ancho = 413;
                break;
            case "Rayo":
                if (p.equijugador) {
                    equijugador = true;
                    x = 210;
                    y = 220;
                    ancho = 50;
                    alto = 50;
                    movy = false;
                } else {
                    equijugador = false;
                    x = 520;
                    y = 150;
                    ancho = 50;
                    alto = 50;
                    movx = false;
                }
                break;
            default:
                break;
        }
        dibujo = ObtenerImagen(a);
        Colorear(a);
    }

    public Image ObtenerImagen(Ataque a) {
        Image img;
        try {
            img = new ImageIcon(getClass().getResource("Ataques/" + a.foto + ".png")).getImage();
        } catch (Exception e) {
            img = null;
        }
        return img;
    }

    public void Perspectiva() {
        switch (efectovisual) {
            case "D":
            case "R":
            case "DV":
                if (equijugador) {
                    if (ancho > 50) {
                        ancho--;
                    }
                    if (alto > 50) {
                        alto--;
                    }
                } else {
                    if (ancho < 100) {
                        ancho++;
                    }
                    if (alto < 100) {
                        alto++;
                    }
                }
                break;
            case "P":
                if (equijugador) {
                    if (ancho >= 220) {
                        masancho = false;
                    } else if (ancho <= 170) {
                        masancho = true;
                    }
                    if (masancho) {
                        ancho++;
                    } else {
                        ancho--;
                    }
                    if (alto >= 2200) {
                        masalto = false;
                    } else if (alto <= 170) {
                        masalto = true;
                    }

                    if (masalto) {
                        alto++;
                    } else {
                        alto--;
                    }
                } else {
                    if (ancho >= 110) {
                        masancho = false;
                    } else if (ancho <= 80) {
                        masancho = true;
                    }
                    if (masancho) {
                        ancho++;
                    } else {
                        ancho--;
                    }
                    if (alto >= 110) {
                        masalto = false;
                    } else if (alto <= 80) {
                        masalto = true;
                    }

                    if (masalto) {
                        alto++;
                    } else {
                        alto--;
                    }
                }
                break;
            case "F":
                if (ancho >= 433) {
                    masancho = false;
                } else if (ancho <= 415) {
                    masancho = true;
                }
                if (masancho) {
                    ancho += 5;
                } else {
                    ancho -= 5;
                }
                if (alto >= 771) {
                    masalto = false;
                } else if (alto <= 730) {
                    masalto = true;
                }

                if (masalto) {
                    alto++;
                } else {
                    alto--;
                }
                break;
            default:
                break;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(colorTipo);
        Perspectiva();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (dibujo != null) {
            g.drawImage(dibujo, x, y, alto, ancho, null);
        } else if (efectovisual.compareTo("P") == 0 || efectovisual.compareTo("Rayo)") == 0) {
            g.drawOval(x, y, alto, ancho);
        } else {
            g.fillOval(x, y, ancho, alto);
        }
    }

    public void MovimientoAtaque(Ataque a) {
        switch (a.efectovisual) {
            case "D":
            case "DV":
                if (movx) {
                    x += 6;
                } else {
                    x -= 6;
                }
                if (movy) {
                    y++;
                } else {
                    y--;
                }
                break;
            case "R":
            case "P":
            case "F":
                //No se mueve, solo se redimensiona
                break;
            case "Rayo":
                if (JPanelCombate.contadorpaint == contadorrayo) {
                    if (movx) {
                        x += 6;
                    } else {
                        x -= 6;
                    }
                    if (movy) {
                        y++;
                    } else {
                        y--;
                    }
                    contadorrayo++;
                }
                break;
            default:
                if (movx) {
                    x += 6;
                } else {
                    x -= 6;
                }
                if (movy) {
                    y++;
                } else {
                    y--;
                }
                break;
        }
    }

    public void Colorear(Ataque a) {
        switch (a.tipo) {
            case "Acero":
                colorTipo = new Color(156, 156, 156);
                break;
            case "Agua":
                colorTipo = new Color(103, 210, 241);
                break;
            case "Bicho":
                colorTipo = new Color(149, 250, 0);
                break;
            case "Dragon":
                colorTipo = new Color(119, 124, 238);
                break;
            case "Electrico":
                colorTipo = new Color(255, 255, 0);
                break;
            case "Fantasma":
                colorTipo = new Color(168, 144, 240);
                break;
            case "Fuego":
                colorTipo = new Color(255, 103, 24);
                break;
            case "Hada":
                colorTipo = new Color(244, 189, 201);
                break;
            case "Hielo":
                colorTipo = new Color(119, 221, 255);
                break;
            case "Lucha":
                colorTipo = new Color(187, 85, 68);
                break;
            case "Normal":
                colorTipo = new Color(216, 216, 192);
                break;
            case "Planta":
                colorTipo = new Color(115, 225, 58);
                break;
            case "Psiquico":
                colorTipo = new Color(216, 88, 136);
                break;
            case "Roca":
                colorTipo = new Color(187, 170, 102);
                break;
            case "Siniestro":
                colorTipo = new Color(153, 120, 98);
                break;
            case "Tierra":
                colorTipo = new Color(252, 226, 99);
                break;
            case "Veneno":
                colorTipo = new Color(216, 128, 184);
                break;
            case "Volador":
                colorTipo = new Color(100, 172, 237);
                break;
        }
    }
}
