/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author gallastegui.maitane
 */
public class Vehicle extends GameObject {

    String movementDirection;
    Timer timer;
    int xLimit;

    public Vehicle(String urlstring, String movementDirection, int xInit, int yInit, int xLimit) {
        super(urlstring, xInit, yInit);
        this.movementDirection = movementDirection;
        this.xLimit = xLimit;
        startAutomaticMovement();
    }

    public void startAutomaticMovement() {
        ArrayList<Integer> abiadurak = new ArrayList<>();
        abiadurak.add(2);
        abiadurak.add(5);
        abiadurak.add(8);
        abiadurak.add(12);

        int min = 1;
        int max = 4;
        int abiPos = min + (int) (Math.random() * ((max - min) + 1));

        timer = new Timer(abiadurak.get(abiPos - 1), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (movementDirection) {
                    case "MoveLeft":
                        if (getLabel().getX() > 0) {
                            getLabel().setLocation(getLabel().getX() - 1, getLabel().getY());
                        }else{
                            getLabel().setLocation(650, getyInit());
                        }
                        break;
                    case "MoveRight":
                            if(getLabel().getX()<728){
                                getLabel().setLocation(getLabel().getX()+1, getLabel().getY());
                            }else{
                                getLabel().setLocation(0,getyInit());
                            }
                        break;
                }
            }

        });
    }
}
