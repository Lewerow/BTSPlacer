package views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 01.04.14.
 */
public class DrawingPanel extends JPanel {
    private Image image;

    public DrawingPanel() {
        super();
    }

    public DrawingPanel(Image image) {
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, null);
    }
}
