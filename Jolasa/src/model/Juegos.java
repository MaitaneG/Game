/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import letratipoa.Fuentes;

public class Juegos<bolita> extends javax.swing.JFrame implements KeyListener {

    //private final BufferStrategy estrategia;
    private JPanel panel;
    private Bola bolita;
    private Raqueta raquetita1;
    private Raqueta2 raquetita2;
    private int golpes, golpes2;
    private JLabel labelcontador;
    private JLabel labelcontador2;
    Fuentes letramota;

    /**
     * Launch the application.
     *
     * @param args
     * @throws InterruptedException //OJO AQUI
     */
    public static void main(String[] args) throws InterruptedException {
        Juegos frame = new Juegos();
        frame.setVisible(true);

        for (int i = 0; i < 179; i++) {
            Thread.sleep(180 - i);
            frame.createBufferStrategy(3);
            frame.repaint();
            frame.moverMundo();
            BufferStrategy strategy = frame.getBufferStrategy();
            Graphics g = strategy.getDrawGraphics();
            g.dispose();
            strategy.show();

        }
        Thread.sleep(2);

    }

    /**
     * Constructor de la clase juego.
     */
    public Juegos() {
        init();

    }

    public final void init() {

        setTitle("Ping Pong ");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 100, 640, 450);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);
        letramota = new Fuentes();

        labelcontador = new JLabel("0");
        labelcontador.setForeground(Color.WHITE);
        labelcontador.setFont(letramota.MyFont(Font.BOLD, 25));
        labelcontador.setBounds(93, 5, 73, 18);
        panel.add(labelcontador);

        labelcontador2 = new JLabel("0");
        labelcontador2.setForeground(Color.WHITE);
        labelcontador2.setFont(letramota.MyFont(Font.BOLD, 25));
        labelcontador2.setBounds(485, 5, 56, 16);
        panel.add(labelcontador2);

        bolita = new Bola(getWidth(), getHeight());
        raquetita1 = new Raqueta(getHeight());
        raquetita2 = new Raqueta2(getHeight());
        addKeyListener(this);
        golpes = 0;
        golpes2 = 0;

    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        getContentPane().setBackground(Color.black);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, (RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setColor(Color.WHITE);
        g2d.fillRect(310, 20, 15, 450);
        g2d.setColor(Color.RED);
        raquetita1.pintarRaqueta1(g2d);
        g2d.setColor(Color.YELLOW);
        raquetita2.pintarRaqueta2(g2d);
        g2d.setColor(Color.WHITE);
        bolita.pintarBola(g2d);

    }

    public void moverMundo() {
        bolita.moverBola();
        if (colision()) {
            bolita.rebotaBola();
            golpes = golpes + 1;
            labelcontador.setText(String.valueOf(golpes / 2));

        } else if (colision2()) {
            bolita.rebotaBola();
            golpes2 = golpes2 + 1;

            labelcontador2.setText(String.valueOf(golpes2 / 2));

        } else if (bolita.TocaFondo()) {

            gameOver();
        }

    }

    public boolean colision() {
        return bolita.limiteBola().intersects(raquetita1.limiteRaqueta1());
    }

    public boolean colision2() {
        return bolita.limiteBola().intersects(raquetita2.limiteRaqueta2());
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
                raquetita1.moverRaquetaarriba1();
                break;
            case KeyEvent.VK_S:
                raquetita1.moverRaquetaabajo1();
                break;

        }

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                raquetita2.moverRaquetaarriba2();
                break;

            case KeyEvent.VK_DOWN:
                raquetita2.moverRaquetaabajo2();
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

    public void gameOver() {
        //Sonido.FONDO.stop();
        //Sonido.GAMEOVER.play();
        if (golpes > golpes2) {
            JOptionPane.showMessageDialog(this, "El jugador 1 ganó", "Game Over", JOptionPane.YES_NO_OPTION);
            System.exit(0);
        } else if (golpes < golpes2) {

            JOptionPane.showMessageDialog(this, "El jugador 2 ganó", "Game Over", JOptionPane.YES_NO_OPTION);
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "Empate", "Game Over", JOptionPane.YES_NO_OPTION);
            System.exit(0);
        }
    }

}
