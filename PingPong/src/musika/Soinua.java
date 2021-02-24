package musika;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Soinua {

    private Clip soinua1;
    private Clip soinua2;
    
    /**
     * Soinuaren konstruktorea
     */
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
    
    /**
     * Jolasaren musika pizteko
     */
    public void soinuaHasi() {
        soinua1.start();
    }
    /**
     * Jolasaren musika berrizteko (Hau egin dugu hasieran arazoak ematen zigulako)
     */
    public void soinuaBerriztu() {
        soinua1.drain();
    }
    
    /**
     * Jolasaren musika itzaltzeko
     */
    public void soinuaItzali() {
        soinua1.close();

    }
    /**
     * Game Over soinua ateratzeko
     */
    public void gameOverSoinua() {
        soinua2.start();

    }
    
    /**
     * Game over soinua itzaltzeko
     */
    public void gameOverAmaitu() {
        soinua2.close();
    }

}
