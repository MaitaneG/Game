/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personaje;

/**
 *
 * @author gallastegui.maitane
 */
public class Mapa extends GameObject {
    int level;
    int numRoads;
    int limitLeft;
    int limitRight;
    int limitUp;

    public Mapa(int level, int xInit, int yInit) {
        super(xInit, yInit);
        this.level = level;
    }
    
    
}
