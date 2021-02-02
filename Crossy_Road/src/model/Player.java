/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gallastegui.maitane
 */
public class Player {
    private String name;
    private int points;
    private int lifes;
    private int coins;

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getLifes() {
        return lifes;
    }

    public int getCoins() {
        return coins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    public void increaseCoins(int coins){
        
    }
    
    public void increasePoints(int points){
        
    }
    
    public void decreaseLife(){
        
    }
}
