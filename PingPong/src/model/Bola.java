package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bola {

    private int x, y, xNorabidea, yNorabidea, altuera, zabalera;
    private int diametroa;
    private Color kolorea;

    
    /**
     * Create the frame.
     * Bolaren konstruktoreak.
     * @param zabalera jasotzen du.
     * @param altuera jasotzen du.
     */
    public Bola(int zabalera, int altuera) {
        this.zabalera = zabalera;
        this.altuera = altuera;
        x = 0;
        y = 0;
        xNorabidea = 27;
        yNorabidea = 27;
        diametroa = 20;
        kolorea = Color.red;
    }
/**
 * Bolaren konstruktorea.
 * @param zabalera jasotzen du.
 * @param altuera jasotzen du.
 * @param diametroa jasotzen du.
 */
    public Bola(int zabalera, int altuera, int diametroa) {
        this.zabalera = zabalera;
        this.altuera = altuera;
        this.diametroa = diametroa;
        x = 0;
        y = 0;
        xNorabidea = 27;
        yNorabidea = 27;
    }
/**
 * Bolaren konstruktorea.
 * @param zabalera jasotzen du.
 * @param altuera jasotzen du.
 * @param diametroa jasotzen du.
 * @param coloret jasotzen du.
 */
    public Bola(int zabalera, int altuera, int diametroa, Color coloret) {
        this.zabalera = zabalera;
        this.altuera = altuera;
        this.diametroa = diametroa;
        this.kolorea = coloret;
        x = 0;
        y = 0;
        xNorabidea = 27;
        yNorabidea = 27;
    }
/**
 * Eskuinera iristen denean eta leihoaren zabalera gainditzen duenean.
 * Leihoaren altuera ken pilotaren diametroa* 3, paneletik atera ez dadin.
 * baldintza hauekin bola mugitzen da.
 */
    public void bolaMugitu() {
        if (x + xNorabidea > zabalera - 40) {
            xNorabidea = -27;
        }
        if (x + xNorabidea < 0) {
            xNorabidea = 27;
        }

        if (y + yNorabidea > altuera - 60) {
            yNorabidea = -27;
        }

        if (y + yNorabidea < 0) {
            yNorabidea = 27;
        }

        x = x + xNorabidea;
        y = y + yNorabidea;

    }
    /**
     * 
     * @param g bola margotzeko ezaugarriak.
     */

    public void bolaMargotu(Graphics2D g) {

        g.fillOval(5 + x, 30 + y, 20, 20);
    }

    public Rectangle bolarenLimitea() {
        return new Rectangle(5 + x, 30 + y, diametroa, diametroa);

    }
/**
 * bola errebotatzeko balditza.
 */
    public void bolaErrebotatu() {

        if (x + xNorabidea > 40) {
            xNorabidea = xNorabidea - 27;
        } else {
            xNorabidea = xNorabidea + 27;
        }

    }
/**
 * 
 * @return metedo hau konprobatzen du ea bola raketa bat ez den zerbait ukitzen badu.
 */
    public boolean fondoaUkitu() {
        return x + xNorabidea > zabalera - 40 || x + xNorabidea < 0;
    }
}
