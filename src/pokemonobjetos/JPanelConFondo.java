package pokemonobjetos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelConFondo extends JPanel {

    public static int tiempo = 35;
    private Image imagenfondo;
    public Image imagen;
    public static AtaqueGrafico ataque;
    public static boolean pintarataque = false;
    public static int contadorpaint;
    public static boolean equijugador;

    public JPanelConFondo(String nombreImagen) {
        setLayout(null);
        imagenfondo = new ImageIcon(getClass().getResource("Terrenos/" + nombreImagen)).getImage();
    }

    public void PintarAtaque(Pokemon p, Ataque a) throws InterruptedException {
        int contador;
        equijugador = p.equijugador;
        pintarataque = true;
        ataque = new AtaqueGrafico(p, a);
        contadorpaint = 1;
        AtaqueGrafico.contadorrayo = 1;
        switch (a.efectovisual) {
            case "D":
                if (p.equijugador) {
                    while (ataque.y != 150) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                } else {
                    while (ataque.y != 215) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                }
                break;
            case "DV":
                if (p.equijugador) {
                    while (ataque.y != 160) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                    ataque.movx = !ataque.movx;
                    ataque.movy = !ataque.movy;
                    AtaqueGrafico.equijugador = !AtaqueGrafico.equijugador;
                    while (ataque.y != 220) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                } else {
                    while (ataque.y != 215) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                    ataque.movx = !ataque.movx;
                    ataque.movy = !ataque.movy;
                    AtaqueGrafico.equijugador = !AtaqueGrafico.equijugador;
                    while (ataque.y != 160) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                }
                break;
            case "R":
                contador = 0;
                while (contador < 80) {
                    ataque.MovimientoAtaque(a);
                    repaint();
                    contador++;
                    Thread.sleep(tiempo);
                }
                break;
            case "P":
                contador = 0;
                while (contador < 80) {
                    ataque.MovimientoAtaque(a);
                    repaint();
                    contador++;
                    Thread.sleep(tiempo);
                }
                break;
            case "F":
                contador = 0;
                while (contador < 80) {
                    ataque.MovimientoAtaque(a);
                    repaint();
                    contador++;
                    Thread.sleep(tiempo);
                }
                AtaqueGrafico.fondo = false;
                break;
            case "Rayo":
                if (p.equijugador) {
                    while (ataque.y != 160) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                } else {
                    while (ataque.y != 215) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                }
                break;
            default:
                if (p.equijugador) {
                    while (ataque.y != 141) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                } else {
                    while (ataque.y != 210) {
                        ataque.MovimientoAtaque(a);
                        repaint();
                        Thread.sleep(tiempo);
                    }
                }
                break;
        }
        ataque.x = 10000;
        repaint();
        pintarataque = false;
    }

    public void paint(Graphics g) {
        if (!AtaqueGrafico.fondo) {
            g.drawImage(imagenfondo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
            if (pintarataque) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                if (ataque.efectovisual.compareTo("Rayo") != 0) {
                    ataque.paint(g2d);
                } else {
                    int x = ataque.x;
                    int y = ataque.y;
                    if (equijugador) {
                        for (int i = 0; i < contadorpaint; i++) {
                            ataque.x = x;
                            ataque.y = y;
                            ataque.x += (i * 6);
                            ataque.y -= i;
                            if (ataque.y > 160) {
                                ataque.paint(g2d);
                            }
                        }
                    } else {
                        for (int i = 0; i < contadorpaint; i++) {
                            ataque.x = x;
                            ataque.y = y;
                            ataque.x -= (i * 6);
                            ataque.y += i;
                            if (ataque.y < 210) {
                                ataque.paint(g2d);
                            }
                        }
                    }
                    ataque.x = x;
                    ataque.y = y;
                    contadorpaint++;
                }
            }
        } else {
            g.drawImage(ataque.dibujo, 0, 0, ataque.alto, ataque.ancho, this);
            setOpaque(false);
            super.paint(g);
            ataque.Perspectiva();
        }
    }
}
