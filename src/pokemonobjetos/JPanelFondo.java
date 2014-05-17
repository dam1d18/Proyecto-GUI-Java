package pokemonobjetos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelFondo extends JPanel {

    private Image imagenfondo;

    public JPanelFondo() {
        setLayout(null);
    }

    public void CargarImagen(String nombreImagen) {
        imagenfondo = new ImageIcon(getClass().getResource("Fondos/" + nombreImagen)).getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(imagenfondo, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}
