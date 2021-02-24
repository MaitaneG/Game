package letratipoa;

import java.awt.Font;
import java.io.File;

/**
 * Gure letra mota propioa jarri nahi dugu.
 *
 * @author moneo.asier
 */
public class Iturria {

    private Font iturri = null;
/**
 * Iturriaren konstruktorea.
 */
    public Iturria() {
        String iturrIzena = "ARCADECLASSIC.TTF";
        try {
            iturri = Font.createFont(Font.TRUETYPE_FONT, new File("src/letratipoa/ARCADECLASSIC.TTF"));
        } catch (Exception ex) {
            System.err.println(iturrIzena + " ez da kargatu iturria");
            iturri = new Font("Arial", Font.PLAIN, 14);
        }
    }

    /**
     *
     * @param estiloa jasotzen du.
     * @param tamaina jasotzen du.
     * @return iturria bueltatzen du.
     */

    public Font nireIturria(int estiloa, float tamaina) {
        Font niturria = iturri.deriveFont(estiloa, tamaina);
        return niturria;
    }
}
