/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author gallastegui.maitane
 */
public class Mapa extends GameObject {
    private int level;
    private int numRoads;
    private int limitLeft;
    private int limitRight;
    private int limitUp;
    private ArrayList<Integer> YRoadsCoordinates;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Coin> coins;
    
    public Mapa(int level, int xInit, int yInit) {
        super("Mapa"+level+".png", xInit, yInit);
        this.level = level;
        loadRoadsCoordinates();
        createCars();
        createCoins();
    }
    
    public void createCars(){
        vehicles = new ArrayList<Vehicle>();
        
        // load cars evenly comming from left and right
        for(int i=0;i<numRoads;i++){
            int Min =1;
            int Max =6;
            int carNum = Min+(int)(Math.random()*((Max-Min)+1));

            if((i%2)==0){
                vehicles.add(new Vehicle("auto"+carNum+"LEFT.png", "MoveLeft",limitRight, YRoadsCoordinates.get(i), limitLeft));
            }
            else{
                vehicles.add(new Vehicle("auto"+carNum+".png", "MoveRight",limitLeft, YRoadsCoordinates.get(i), limitRight));
            }
        }   
    }
    
    public void createCoins(){
        coins = new ArrayList<>();
        
        for(int i=0;i<10;i++){
            int xMin =1;
            int xMax =600;
            int XCoord = xMin+(int)(Math.random()*((xMax-xMin)+1));

            int yMin =1;
            int yMax =600;
            int yCoord = yMin+(int)(Math.random()*((yMax-yMin)+1));
            coins.add(new Coin("coin.png", XCoord+50, yCoord+50));
        }
    }
    
    public void loadRoadsCoordinates(){
        YRoadsCoordinates = new ArrayList<>();
        switch(level){
            case 1:
                numRoads=6;
                limitLeft = 0;
                limitRight = 650;
                YRoadsCoordinates.add(600+12);
                YRoadsCoordinates.add(550+12);
                YRoadsCoordinates.add(500+12);
                YRoadsCoordinates.add(450+12);
                YRoadsCoordinates.add(300+12);
                YRoadsCoordinates.add(250+12);
                YRoadsCoordinates.add(200+12);
                YRoadsCoordinates.add(150+12);
                break;
            
            case 2:
                numRoads = 6;
                limitLeft = 0;
                limitRight = 650;
                YRoadsCoordinates.add(500+12);
                YRoadsCoordinates.add(450+12);
                YRoadsCoordinates.add(300+12);
                YRoadsCoordinates.add(250+12);
                YRoadsCoordinates.add(100+12);
                YRoadsCoordinates.add(50+12);
                break;
            case 3:
                numRoads = 6;
                YRoadsCoordinates.add(500+12);
                YRoadsCoordinates.add(450+12);
                YRoadsCoordinates.add(400+12);
                YRoadsCoordinates.add(350+12);
                YRoadsCoordinates.add(100+12);
                YRoadsCoordinates.add(50+12);
        }
    }
    
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     *
     * @return el nivel del mapa.
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @return monedas del mapa.
     */
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    
}
