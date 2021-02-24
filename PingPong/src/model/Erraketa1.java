package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Erraketa1 {
    
    private int y, yNorabidea, altuera, zabalera;
    private Color kolorea;
    
    /**
     * Erraketa1-ren kontrokturea da
     *
     * @param altuera bakarrik jasotzen du.
     */
    public Erraketa1(int altuera) {
        y = 0;
        yNorabidea = 1;
        zabalera = 70;
        kolorea = Color.red;
        this.altuera = altuera;
    }
    
    /**
     * Erraketa1-ren konstruktorea da
     * @param altuera jasotzen du
     * @param kolorea jasotzen du
     */
    public Erraketa1(int altuera, Color kolorea) {
        this.altuera = altuera;
        this.kolorea = kolorea;
        y = 0;
        yNorabidea = 1;
    }

    /**
     * Erraketa1-ren konstruktorea da
     * @param altuera jasotzen du
     * @param kolorea jasotzen du
     * @param zabalera jasotzen du
     */
    public Erraketa1(int altuera, Color kolorea, int zabalera) {
        this.altuera = altuera;
        this.kolorea = kolorea;
        this.zabalera = zabalera;
        y = 0;
        yNorabidea = 1;
    }
    
    /**
     * Erraketa behera mugitzeko eta limite bat dauka erraketa pantailatik ez irtetzeko.
     */
    public void mugituErraketa1Beherantz() {
        if (y - 30 > -207) { //(484/2-zabalera)=207  
            y = y - 30;
        }
    }
    
    /**
     * Erraketa1 gora mugitzeko eta limite bat dauka erraketa pantailatik ez irtetzeko.
     */
    public void mugituErraketa1Gorantz() {
        if (y + zabalera + 30 < altuera - 242) {//(484/2)=242
            y = y + 30;
        }
    }
    
    /**
     * Erraketa1 margotzeko
     * @param g jasotzen du
     */
    public void margotuErraketa1(Graphics2D g) {
        g.fillRoundRect(15, 207 - y, 20, zabalera, 25, 25);
    }
    
    /**
     * Bolarekin noiz talka egiten duen jakitzeko.
     * @return bueltzen du Rectangle motatako objetu bat limiteak zehazteko
     */
    public Rectangle bolarenLimitea1() {
        return new Rectangle(15, 207 - y, 20, zabalera);
    }
}
