package musika;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Soinua {

    private Clip soinua1;
    private Clip soinua2;

    public Soinua() {
        try {
            this.soinua1 = AudioSystem.getClip();
            soinua1.open(AudioSystem.getAudioInputStream(new File("src/musika/musikaBitartean.wav")));
        } catch (Exception e) {
            System.out.println("" + e);
        }
        try {
            this.soinua2 = AudioSystem.getClip();
            soinua2.open(AudioSystem.getAudioInputStream(new File("src/musika/musikaBukaera.wav")));
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void soinuaHasi() {
        //Soinua hasten da
        soinua1.start();

    }

    public void soinuaBerriztu() {

        soinua1.drain();

    }

    public void soinuaItzali() {

        // Soinua bukatzen da
        soinua1.close();

    }

    public void gameOverSoinua() {
        //Bukaerako soinua hasten da
        soinua2.start();

    }

    public void gameOverAmaitu() {

        soinua2.close();

    }

}
