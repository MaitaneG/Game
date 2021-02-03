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
    private int points;
    private int lifes;
    private int coins;
    private int YcloserToGoal;
    private Pertsonaia pertsona;
    
    public Player(int points, int coins, int lifes, Pertsonaia pertsona) {
        this.points = points;
        this.coins = coins;
        this.lifes = lifes;
        this.pertsona=pertsona;
    }
    
    public Player(){
        this.points=0;
        this.coins=0;
        this.YcloserToGoal=9999;
        this.lifes=3;
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

    public void setPoints(int points) {
        this.points = points;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    public int getYcloserToGoal() {
        return YcloserToGoal;
    }

    /**
     *
     * @param YcloserToGoal posicion y del jugador al momento de avanzar mas a la meta
     */
    public void setYcloserToGoal(int YcloserToGoal) {
        this.YcloserToGoal = YcloserToGoal;
    }
    
    public void increaseCoins(){
        coins++;
    }
    
    public void increasePoints(int points){
         this.points += points;
    }
    
    public void decreaseLife(){
        lifes--;
    }
}
