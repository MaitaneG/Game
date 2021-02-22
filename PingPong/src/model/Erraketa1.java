package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Erraketa1 {

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
    public Erraketa1(int altuera) {
        y = 0;
        yNorabidea = 1;
        zabalera = 70;
        kolorea = Color.red;
        this.altuera = altuera;
    }

    public Erraketa1(int altuera, Color kolorea) {
        this.altuera = altuera;
        this.kolorea = kolorea;
        y = 0;
        yNorabidea = 1;
    }

    public Erraketa1(int altuera, Color kolorea, int zabalera) {
        this.altuera = altuera;
        this.kolorea = kolorea;
        this.zabalera = zabalera;
        y = 0;
        yNorabidea = 1;
    }

    //(x + direccionX > 0 && x + direccionX < ancho-largo
    public void mugituErraketa1Beherantz() {
        if (y - 30 > -207) { //(484/2-largo)=207  /// ojo largo=70
            y = y - 30;
        }
    }

    public void mugituErraketa1Gorantz() {
        if (y + zabalera + 30 < altuera - 242) {//(484/2)=242
            y = y + 30;
        }
    }

    public void margotuErraketa1(Graphics2D g) {
        g.fillRoundRect(15, 207 - y, 20, zabalera, 25, 25);
    }

    public Rectangle bolarenLimitea1() {
        return new Rectangle(15, 207 - y, 20, zabalera);
    }
}
