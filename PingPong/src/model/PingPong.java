/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import musika.Sounds;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import letratipoa.Fuentes;

public class PingPong extends javax.swing.JFrame implements KeyListener {

    //private final BufferStrategy estrategia;
    private JPanel panela;
    private Bola bola;
    private Erraketa1 erraketa1;
    private Erraketa2 erraketa2;
    private int golpe1, golpe2;
    private JLabel labelkontagailu1;
    private JLabel labelkontagailu2;
    private static Sounds soinua = new Sounds();
    ;
    private Fuentes letramota;

    /**
     * Launch the application.
     *
     * @param args
     * @throws InterruptedException //OJO AQUI
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
     * Constructor de la clase juego.
     */
    public PingPong() {
        hasieratu();

    }

    public final void hasieratu() {

        setTitle("Ping Pong ");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 100, 640, 450);
        panela = new JPanel();
        panela.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panela);
        panela.setLayout(null);
        letramota = new Fuentes();

        labelkontagailu1 = new JLabel("0");
        labelkontagailu1.setForeground(Color.WHITE);
        labelkontagailu1.setFont(letramota.MyFont(Font.BOLD, 25));
        labelkontagailu1.setBounds(93, 5, 73, 18);
        panela.add(labelkontagailu1);

        labelkontagailu2 = new JLabel("0");
        labelkontagailu2.setForeground(Color.WHITE);
        labelkontagailu2.setFont(letramota.MyFont(Font.BOLD, 25));
        labelkontagailu2.setBounds(485, 5, 56, 16);
        panela.add(labelkontagailu2);

        bola = new Bola(getWidth(), getHeight());
        erraketa1 = new Erraketa1(getHeight());
        erraketa2 = new Erraketa2(getHeight());
        addKeyListener(this);
        golpe1 = 0;
        golpe2 = 0;

    }

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

    public void mugituObjetuak() throws InterruptedException {
        bola.bolaMugitu();
        if (talka1Erraketa()) {
            bola.bolaErrebotatu();
            golpe1 = golpe1 + 1;
            labelkontagailu1.setText(String.valueOf(golpe1 / 2));

        } else if (talka2Erraketa()) {
            bola.bolaErrebotatu();
            golpe2 = golpe2 + 1;

            labelkontagailu2.setText(String.valueOf(golpe2 / 2));

        } else if (bola.fondoaUkitu()) {
            soinua.soinuaItzali();
            soinua.gameOversound();
            gameOver();

        }

    }

    public boolean talka1Erraketa() {
        return bola.bolarenLimitea().intersects(erraketa1.bolarenLimitea1());
    }

    public boolean talka2Erraketa() {
        return bola.bolarenLimitea().intersects(erraketa2.bolarenLimitea2());
    }

    @Override
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

    public void berriroHasi() throws InterruptedException {

        int aukera = JOptionPane.showConfirmDialog(this, "Berriro jolastu nahi duzu?", "Ping Pong", JOptionPane.YES_NO_OPTION);
        if (aukera == JOptionPane.NO_OPTION) {
            System.exit(0);
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

    public void reset() {
        
        labelkontagailu1.setText("0");
        labelkontagailu2.setText("0");
        golpe1=0;
        golpe2=0;
        bola = new Bola(getWidth(), getHeight());
        erraketa1 = new Erraketa1(getHeight());
        erraketa2 = new Erraketa2(getHeight());
    }

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
