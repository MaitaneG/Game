package exekutagarria;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;
import musika.Soinua;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import letratipoa.Iturria;
import model.Bola;
import model.Erraketa1;
import model.Erraketa2;

public class PingPong extends javax.swing.JFrame implements KeyListener {

    private JPanel panela;
    private Bola bola;
    private Erraketa1 erraketa1;
    private Erraketa2 erraketa2;
    private int golpe1, golpe2;
    private JLabel labelKontagailu1;
    private JLabel labelKontagailu2;
    private static Soinua soinua = new Soinua();
    private JFrame kredituak;
    private JPanel kreditupanela;
    private ImageIcon argazkia;
    private JLabel argazkilabel;
    private JLabel izena1;
    private JLabel izena2;
    private JLabel izenburua;
    private Iturria letraMota;

    /**
     * Launch the application.
     *
     * JFramea sortu eta metodoei deitu.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        PingPong markoa = new PingPong();

        int aukera = JOptionPane.showConfirmDialog(markoa, "Jolastu nahi duzu?", "Ping Pong", JOptionPane.YES_NO_OPTION);
        if (aukera == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else if (aukera == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(markoa, "1. Jokalari gorria (W, S) vs 2. Jokalari horia (Gorako gezia, Beherako gezia)", "Ping Pong", JOptionPane.INFORMATION_MESSAGE);
            markoa.setVisible(true);

            soinua.soinuaHasi();
            for (int i = 0; i < 179; i++) {
                Thread.sleep(180 - i);
                markoa.createBufferStrategy(3);
                markoa.repaint();
                markoa.mugituObjetuak();
                BufferStrategy strategy = markoa.getBufferStrategy();
                Graphics g = strategy.getDrawGraphics();
                g.dispose();
                strategy.show();

            }

            Thread.sleep(2);
        }
    }

    /**
     * Jokuaren kontruktorea
     */
    public PingPong() {
        hasieratu();

    }

    /**
     * Hemen jolasaren datuak hasieratzen du.
     */
    public void hasieratu() {

        setTitle("Ping Pong ");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 100, 640, 450);
        panela = new JPanel();
        panela.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panela);
        panela.setLayout(null);
        letraMota = new Iturria();

        labelKontagailu1 = new JLabel("0");
        labelKontagailu1.setForeground(Color.WHITE);
        labelKontagailu1.setFont(letraMota.nireIturria(Font.BOLD, 25));
        labelKontagailu1.setBounds(93, 5, 73, 18);
        panela.add(labelKontagailu1);

        labelKontagailu2 = new JLabel("0");
        labelKontagailu2.setForeground(Color.WHITE);
        labelKontagailu2.setFont(letraMota.nireIturria(Font.BOLD, 25));
        labelKontagailu2.setBounds(485, 5, 56, 16);
        panela.add(labelKontagailu2);

        bola = new Bola(getWidth(), getHeight());
        erraketa1 = new Erraketa1(getHeight());
        erraketa2 = new Erraketa2(getHeight());
        addKeyListener(this);
        golpe1 = 0;
        golpe2 = 0;

    }

    /**
     * Objetuak (mapa, bola, erraketak) marrazteko
     *
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        getContentPane().setBackground(Color.black);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, (RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setColor(Color.WHITE);
        g2d.fillRect(310, 20, 15, 450);
        g2d.setColor(Color.RED);
        erraketa1.margotuErraketa1(g2d);
        g2d.setColor(Color.YELLOW);
        erraketa2.margotuErraketa2(g2d);
        g2d.setColor(Color.WHITE);
        bola.bolaMargotu(g2d);

    }

    /**
     * Objetuak mugitzen ditu eta erraketekin talka bat badago kolpeak kontatzen
     * ditu. Fondoa ikutzen badu gameOver metodoari deitzen dio.
     *
     * @throws InterruptedException
     */
    public void mugituObjetuak() throws InterruptedException {
        bola.bolaMugitu();
        if (talka1Erraketa()) {
            bola.bolaErrebotatu();
            golpe1 = golpe1 + 1;
            labelKontagailu1.setText(String.valueOf(golpe1 / 2));
        } else if (talka2Erraketa()) {
            bola.bolaErrebotatu();
            golpe2 = golpe2 + 1;
            labelKontagailu2.setText(String.valueOf(golpe2 / 2));
        } else if (bola.fondoaUkitu()) {
            soinua.soinuaItzali();
            soinua.gameOverSoinua();
            gameOver();
        }
    }

    /**
     * Konprobatzen du, lehenengo erraktarekin talka bat egon den.
     *
     * @return lehengo erraketarekin talka bat badago true bueltatzen du,
     * bestela false
     */
    public boolean talka1Erraketa() {
        return bola.bolarenLimitea().intersects(erraketa1.bolarenLimitea1());
    }

    /**
     * Konprobatzen du, bigarren erraktarekin talka bat egon den.
     *
     * @return bigarren erraketarekin talka bat badago true bueltatzen du,
     * bestela false
     */
    public boolean talka2Erraketa() {
        return bola.bolarenLimitea().intersects(erraketa2.bolarenLimitea2());
    }

    @Override
    /**
     * Tekla bat klikatzean funtzionatzen du. W klikatzen bada erraketa 1 gora
     * doa. S klikatzen bada erraketa 1 behara doa gorako gezia klikatzen bada
     * erraketa 2 gora doa. beherako gezia klikatzen bada erraketa 2 behera doa.
     */
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                erraketa1.mugituErraketa1Gorantz();
                break;
            case KeyEvent.VK_S:
                erraketa1.mugituErraketa1Beherantz();
                break;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                erraketa2.mugituErraketa2Gorantz();
                break;
            case KeyEvent.VK_DOWN:
                erraketa2.mugituErraketa2Beherantz();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Linea hauek komentatuta daude, ezin izan dugu inplementatu
     * kredituak,agertzen ziren jokua exekutatzen zegoen bitartean eta ez zen
     * gelditzen.
     *
     * @throws InterruptedException
     */
    public void kredituakbistaratu() throws InterruptedException {

        kredituak = new JFrame();
        kredituak.setTitle("Kredituak");
        kredituak.setResizable(false);
        kredituak.setBounds(450, 100, 640, 450);
        kreditupanela = new JPanel();
        kreditupanela.setBorder(new EmptyBorder(5, 5, 5, 5));
        kredituak.setContentPane(kreditupanela);
        kreditupanela.setLayout(null);
        kreditupanela.setBackground(Color.black);

        argazkia = new ImageIcon("src/argazkia/argazkia.PNG");
        Image arg = argazkia.getImage();
        Image argazkineurri = arg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        argazkia = new ImageIcon(argazkineurri);

        argazkilabel = new JLabel();
        argazkilabel.setBounds(220, 30, argazkineurri.getWidth(rootPane), argazkineurri.getHeight(rootPane));
        argazkilabel.setIcon(argazkia);
        kredituak.add(argazkilabel);

        izenburua = new JLabel("Ping Pong");
        izenburua.setForeground(Color.WHITE);
        izenburua.setFont(letraMota.nireIturria(Font.BOLD, 20));
        izenburua.setBounds(250, 200, 250, 30);
        kredituak.add(izenburua);

        izena1 = new JLabel("Maitane    Gallastegui");
        izena1.setForeground(Color.WHITE);
        izena1.setFont(letraMota.nireIturria(Font.BOLD, 14));
        izena1.setBounds(250, 230, 250, 30);
        kredituak.add(izena1);

        izena2 = new JLabel("Asier    Moneo");
        izena2.setForeground(Color.WHITE);
        izena2.setFont(letraMota.nireIturria(Font.BOLD, 14));
        izena2.setBounds(250, 250, 250, 30);
        kredituak.add(izena2);

        kredituak.setVisible(true);
        kredituak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Berri jolastu nahi duen galdetzen du Baietz esaten badu, berriro
     * kargatuko da partida Ezetz esaten badu, jolasa itxiko da
     *
     * @throws InterruptedException
     */
    public void berriroHasi() throws InterruptedException {

        int aukera = JOptionPane.showConfirmDialog(this, "Berriro jolastu nahi duzu?", "Ping Pong", JOptionPane.YES_NO_OPTION);
        if (aukera == JOptionPane.NO_OPTION) {
            kredituakbistaratu();
            while (true) {
                this.setVisible(false);
            }

        } else if (aukera == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "1. Jokalari gorria (W, S) vs 2. Jokalari horia (Gorako gezia, Beherako gezia)", "Ping Pong", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(true);

            reset();

            for (int i = 0; i < 179; i++) {
                Thread.sleep(180 - i);
                this.createBufferStrategy(3);
                this.repaint();
                this.mugituObjetuak();
                BufferStrategy strategy = this.getBufferStrategy();
                Graphics g = strategy.getDrawGraphics();
                g.dispose();
                strategy.show();
            }
            Thread.sleep(2);
        }

    }

    /**
     * Kontagailuak 0-ra jartzen du Golpeak 0-ra jartzen du Bolak eta erraketak
     * hasierako posizioan jartzen ditu
     */
    public void reset() {
        labelKontagailu1.setText("0");
        labelKontagailu2.setText("0");
        golpe1 = 0;
        golpe2 = 0;
        bola = new Bola(getWidth(), getHeight());
        erraketa1 = new Erraketa1(getHeight());
        erraketa2 = new Erraketa2(getHeight());
    }

    /**
     * Game Over mezua agertzen da, eta berriroHasi metodoari deitzen dio.
     *
     * @throws InterruptedException
     */
    public void gameOver() throws InterruptedException {

        if (golpe1 > golpe2) {

            JOptionPane.showMessageDialog(this, "1. Jokalaria irabazi du", "Game Over", JOptionPane.YES_NO_OPTION);
            berriroHasi();

        } else if (golpe1 < golpe2) {

            JOptionPane.showMessageDialog(this, "2. Jokalaria irabazi du", "Game Over", JOptionPane.YES_NO_OPTION);
            berriroHasi();

        } else if (golpe1 == 0 && golpe2 == 0) {

            JOptionPane.showMessageDialog(this, "1. Jokalaria irabazi du", "Game Over", JOptionPane.YES_NO_OPTION);
            berriroHasi();

        } else {

            JOptionPane.showMessageDialog(this, "Berdinketa", "Game Over", JOptionPane.YES_NO_OPTION);
            berriroHasi();

        }
    }

}
