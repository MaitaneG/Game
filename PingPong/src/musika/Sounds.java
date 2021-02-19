/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musika;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {

    private Clip soinua1;
    private Clip soinua2;

    public Sounds() {
        try {
            this.soinua1 = AudioSystem.getClip();
            soinua1.open(AudioSystem.getAudioInputStream(new File("src/musika/musicaDurante.wav")));
        } catch (Exception e) {
            System.out.println("" + e);
        }
        try {
            this.soinua2 = AudioSystem.getClip();
            soinua2.open(AudioSystem.getAudioInputStream(new File("src/musika/musicaFinal.wav")));
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void soinuaHasi() {

        soinua1.start();

    }

    public void soinuaItzali() {

        // Comienza la reproducción
        soinua1.close();

    }

    public void gameOversound() {

        soinua2.start();

    }

}
