/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exekutagarria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Coin;
import model.GameObject;
import model.Mapa;
import model.Pertsonaia;
import model.Player;
import model.Vehicle;

/**
 *
 * @author gallastegui.maitane
 */
public class Menu {

    private JFrame menuFrame;

    public Menu() {
        menuFrame = new JFrame("Paper Road-Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(null);
        menuFrame.setSize(728, 782);
        menuFrame.setLocationRelativeTo(null); 
        menuFrame.setResizable(false);
        
        JButton jolastuBotoia = new JButton("Jolastu");
        jolastuBotoia.setSize(150, 50);
        jolastuBotoia.setLocation(280, 400);
        
        JButton irtenBotoia = new JButton("Irten");
        irtenBotoia.setSize(150, 50);
        irtenBotoia.setLocation(280, 480);
        
        menuFrame.add(jolastuBotoia);
        menuFrame.add(irtenBotoia);
        
        jolastuBotoia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               PaperRoad jolasa=new PaperRoad();
               jolasa.hasi();
            }
        });
        
        irtenBotoia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
    }
    /**
     * Muestra el Menu.
     */
    public void erakutsi(){
        menuFrame.setVisible(true);
    }
    
    /**
     * Esconde el Menu.
     */
    public void eskutatu(){
        menuFrame.setVisible(false);
    }
}
