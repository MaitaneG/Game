/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exekutagarria;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
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
public class PaperRoad {
    private JFrame myFrame;
    private Player player;
    private Pertsonaia mascot;
    private Mapa map;
    private Timer crashTimer;
    private final int INITLEVEL;
    
    private JLabel playerLifeLabel;
    private JLabel playerPointsLabel;
    private JLabel playerCoinsLabel;
    
    private double startTime;
    private double endTime;
    private int timeSec;
    
    
    /**
     *
     * @param INITLEVEL  nivel inicial del juego
     */
    public PaperRoad(int INITLEVEL) {
        this.INITLEVEL = INITLEVEL;
        mascot = new Pertsonaia("Papera.png",360, 700);
    }
    
    public PaperRoad(){
        INITLEVEL = 1;
    }
    
    public void start(){
        loadLevel(INITLEVEL);        

    }
    
    public void createCrashListener(){
        crashTimer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle crashRect;
                crashRect = null;
                for(Vehicle vehicle: map.getVehicles()){
                    crashRect = mascot.getRectangle().intersection(vehicle.getRectangle());
                    if(crashRect.getWidth()>0 && crashRect.getHeight()>0){
                        playSound("crash.wav");
                        System.out.println("there has been a Crash! ");
                        mascot.getLabel().setLocation(360,700);
                        player.decreaseLife();
                        playerLifeLabel.setText(Integer.toString(player.getLifes()));
                        if(player.getLifes()==0){
                            JOptionPane.showMessageDialog(null, "GAME OVER!!");
                            myFrame.setVisible(false);
                        }
                        break;
                    }
                }
                if(map.getLevel()==4){
                    Rectangle crashRectTrain;
                    crashRectTrain = null;
                    crashRectTrain = map.getTrain().getRectangle().intersection(mascot.getRectangle());
                    if(crashRectTrain.getWidth()>0 && crashRectTrain.getHeight()>0){
                        playSound("crash.wav");
                        mascot.getLabel().setLocation(360,700);
                        player.decreaseLife();
                        playerLifeLabel.setText(Integer.toString(player.getLifes()));
                        if(player.getLifes()==0){
                            JOptionPane.showMessageDialog(null, "GAME OVER!!");
                            myFrame.setVisible(false);
                        }
                    }
                }
            }
        });
        crashTimer.start();
    }
    
    public void createWinLevelListener(){
        winTimer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(mascot.getLabel().getY()<=0){
                    mascot.getLabel().setLocation(900, 900);
                    JOptionPane.showMessageDialog(null, "NIVEL "+map.getLevel()+" SUPERADO!!");
                    if(map.getLevel()==4){
                        JOptionPane.showMessageDialog(null, "USTED HA GANADO EL JUEGO!! FELICITACIONES");
                        myFrame.setVisible(false);
                    }else{
                        loadLevel(map.getLevel()+1); 
                    } 
                }
                
                Rectangle crashCoinRect;
                crashCoinRect = null;
                for(Coin coin: map.getCoins()){
                    crashCoinRect = mascot.getRectangle().intersection(coin.getRectangle());
                    if(crashCoinRect.getWidth()>0 && crashCoinRect.getHeight()>0){
                        playSound("coin.wav");
                        System.out.println("Player Got a Coin! ");
                        player.IncreaseCoins();
                        playerCoinsLabel.setText(Integer.toString(player.getCoins()));
                        coin.getLabel().setLocation(-100,-100);
                        System.out.println("toal coins: "+player.getCoins());
                        break;
                    }
                } 
                
                
                endTime = System.currentTimeMillis();
                System.out.println("startTime:"+startTime);
                System.out.println("endTime:"+endTime);
                timeSec = (int)((endTime-startTime)/1000);
                TimeLabel.setText(Integer.toString(timeSec));
                
            }
        });
        winTimer.start();
    }
    
    public void loadLevel(int level){
        player.setYcloserToGoal(9999); // initialize coordinates
        
        if(level!=1){
            destroyPreviousLevel();
        }
        
        myFrame = new JFrame("Crossy Road 0.1");   
        myFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setSize(725,782);
        myFrame.setLocationRelativeTo(null); // center frame on monitor
        myFrame.setResizable(false);
        
        
        
        playerPointsLabel = new JLabel(Integer.toString(player.getPoints()));
        playerPointsLabel.setBounds(250,16,100,20);
        myFrame.add(playerPointsLabel);
        
        TimeLabel = new JLabel(Integer.toString(0));
        TimeLabel.setBounds(410,16,100,20);
        myFrame.add(TimeLabel);
        
        playerCoinsLabel = new JLabel(Integer.toString(player.getCoins()));
        playerCoinsLabel.setBounds(550,16,100,20);
        myFrame.add(playerCoinsLabel);
        
        playerLifeLabel = new JLabel(Integer.toString(player.getLifes()));
        playerLifeLabel.setBounds(670,16,100,20);
        myFrame.add(playerLifeLabel);
        
        myFrame.add(mascot.getLabel());
        mascot.getLabel().setLocation(360, 700);
        
        map = new Mapa(level, 0 , 0);
        
        for(Vehicle vehicle: map.getVehicles()){
            myFrame.add(vehicle.getLabel());
        }
        
        if(level==4){   
            myFrame.add(map.getTrain().getLabel());
        }
        
        for(Coin coin: map.getCoins()){
            myFrame.add(coin.getLabel());
        }
        
        myFrame.add(map.getLabel());
        myFrame.setVisible(true);
        myFrame.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        mascot.getLabel().setIcon(mascot.getUpIcon());
                        mascot.getLabel().setLocation(mascot.getLabel().getX(), mascot.getLabel().getY()-50);
                        if(mascot.getLabel().getY()<player.getYcloserToGoal()){
                            player.setYcloserToGoal(mascot.getLabel().getY());
                            player.increasePoints(1);
                            playerPointsLabel.setText(Integer.toString(player.getPoints()));
                            System.out.println("puntos acumulados:" +player.getPoints());
                            
                            if(player.getPoints()==50){
                                playSound("50pts.wav");
                            }
                            
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(mascot.getLabel().getY()<=650){
                            mascot.getLabel().setLocation(mascot.getLabel().getX(), mascot.getLabel().getY()+50);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(mascot.getLabel().getX()>=50){
                            mascot.getLabel().setLocation(mascot.getLabel().getX()-50, mascot.getLabel().getY());
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(mascot.getLabel().getX()<=650){
                            mascot.getLabel().setLocation(mascot.getLabel().getX()+50, mascot.getLabel().getY());
                        }
                        break;
                }
                //System.out.println("Key: "+e.getKeyCode());
            }
        });
        
        createCrashListener();
        
        if(level==1){
            startTime = System.currentTimeMillis();
        }
        
        createWinLevelListener();
        
    }
}
