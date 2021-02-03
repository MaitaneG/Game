/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author gallastegui.maitane
 */
public class Pertsonaia extends GameObject{
    ImageIcon aurreraIcon;

    public Pertsonaia(String link,int xInit, int yInit) {
        super(link,xInit, yInit);
        //aurreraIcon=new ImageIcon(this.getClass().getResource("/images/"+link));
    }

    public ImageIcon getAurreraIcon() {
        return aurreraIcon;
    }
}
