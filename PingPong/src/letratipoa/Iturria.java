package letratipoa;

import java.awt.Font;
import java.io.File;

/**
 *
 * @author moneo.asier
 */
public class Iturria {

    private Font iturri = null;

    public Iturria() {

        String iturrIzena = "ARCADECLASSIC.TTF";

        try {

            iturri = Font.createFont(Font.TRUETYPE_FONT, new File("src/letratipoa/ARCADECLASSIC.TTF"));
        } catch (Exception ex) {

            System.err.println(iturrIzena + " ez da kargatu iturria");
            iturri = new Font("Arial", Font.PLAIN, 14);
        }

    }

    public Font nireIturria(int estiloa, float tamaina) {
        Font niturria = iturri.deriveFont(estiloa, tamaina);
        return niturria;
    }

}
