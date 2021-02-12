/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letratipoa;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.InputStream;

/**
 *
 * @author moneo.asier
 */
public class Fuentes {

    private Font font = null;


    /* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2
 * tamanio = float
     */
    public Fuentes() {
        String fontName = "ARCADECLASSIC.TTF";

        try {

            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/letratipoa/ARCADECLASSIC.TTF"));
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontName + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);
        }

    }

    public Font MyFont(int estilo, float tamanio) {
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }

}
