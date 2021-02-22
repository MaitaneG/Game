package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Erraketa2 {

    private int y, yNorabidea, altuera, zabalera;
    private Color kolorea;

    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     *
     * @param altuera
     */
    public Erraketa2(int altuera) {
        y = 0;
        yNorabidea = 1;
        zabalera = 70;
        kolorea = Color.red;
        this.altuera = altuera;
    }

    public Erraketa2(int altuera, Color kolorea) {
        this.altuera = altuera;
        this.kolorea = kolorea;
        y = 0;
        yNorabidea = 1;
    }

    public Erraketa2(int altuera, Color kolorea, int zabalera) {
        this.altuera = altuera;
        this.kolorea = kolorea;
        this.zabalera = zabalera;
        y = 0;
        yNorabidea = 1;
    }

    //(x + direccionX > 0 && x + direccionX < ancho-largo 
    public void mugituErraketa2Beherantz() {
        if (y - 30 > -207) {
            y = y - 30;
        }
    }

    public void mugituErraketa2Gorantz() {
        if (y + zabalera + 30 < altuera - 242) {
            y = y + 30;
        }
    }

    public void margotuErraketa2(Graphics2D g) {
        g.fillRoundRect(603, 207 - y, 20, zabalera, 25, 25);
    }

    public Rectangle bolarenLimitea2() {
        return new Rectangle(603, 207 - y, 20, zabalera);
    }
}
