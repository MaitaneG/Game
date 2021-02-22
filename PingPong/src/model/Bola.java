package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bola {

    private int x, y, xNorabidea, yNorabidea, altuera, zabalera;
    private int diametroa;
    private Color kolorea;

    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     *
     * @param zabalera
     * @param altuera
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

    public Bola(int zabalera, int altuera, int diametroa) {
        this.zabalera = zabalera;
        this.altuera = altuera;
        this.diametroa = diametroa;
        x = 0;
        y = 0;
        xNorabidea = 27;
        yNorabidea = 27;
    }

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

    public void bolaMugitu() {
        //CUANDO llega a la derecha y supera el ancho dela ventana 
        // lerestamos -1 asi vamos hacia la izquierda
        if (x + xNorabidea > zabalera - 40) {
            xNorabidea = -27;
        }
        if (x + xNorabidea < 0) {
            xNorabidea = 27;
        }

        if (y + yNorabidea > altuera - 60) {//el alto de la ventana menos el diametroa *3 de la pelota para que no se salga del panel
            yNorabidea = -27;
        }

        if (y + yNorabidea < 0) {
            yNorabidea = 27;
        }

        x = x + xNorabidea;//// nota ojo
        y = y + yNorabidea;

    }

    public void bolaMargotu(Graphics2D g) {

        g.fillOval(5 + x, 30 + y, 20, 20);
    }

    public Rectangle bolarenLimitea() {
        return new Rectangle(5 + x, 30 + y, diametroa, diametroa);

    }

    public void bolaErrebotatu() {

        if (x + xNorabidea > 40) {
            xNorabidea = xNorabidea - 27;
        } else {
            xNorabidea = xNorabidea + 27;
        }

    }

    public boolean fondoaUkitu() {
        return x + xNorabidea > zabalera - 40 || x + xNorabidea < 0;
    }
}
