/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author gallastegui.maitane
 */
public class GameObject {
    int xInit;
    int yInit;
    URL link;
    ImageIcon icon;
    JLabel label;
    Rectangle rect;

    public GameObject(String link, int xInit, int yInit) {
        this.xInit = xInit;
        this.yInit = yInit;
        this.link=this.getClass().getClassLoader().getResource("images/"+link);
        this.icon=new ImageIcon(link);
        this.label=new JLabel(icon);
        this.label.setBounds(xInit, yInit, icon.getIconWidth(), icon.getIconHeight());
        this.rect=label.getBounds();
    }
    
    public int getxInit() {
        return xInit;
    }

    public int getyInit() {
        return yInit;
    }

    public URL getLink() {
        return link;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public JLabel getLabel() {
        return label;
    }

    public Rectangle getRect() {
        return rect;
    }
    
    public Rectangle getRectangle(){
        rect=label.getBounds();
        return rect;
    }
    
}
