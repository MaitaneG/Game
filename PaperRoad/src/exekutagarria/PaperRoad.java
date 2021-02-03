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
    private JFrame nireFrame;
    private Player jokalaria;
    private Pertsonaia pertsonaia;
    private Mapa mapa;
    private Timer golpeTimer;
    private Timer irabaziTimer;
    private final int LEHENGOMAILA;
    
    private JLabel jokalariBizitzaLabel;
    private JLabel jokalariPuntuakLabel;
    private JLabel jokalariTxanponaLabel;
    private JLabel denboraLabel;
    private JLabel figura;
    
    private double hasieraDenbora;
    private double bukaerakoDenbora;
    private int denboraSegundu;
    
    
    /**
     *
     * @param LEHENGOMAILA  nivel inicial del juego
     */
    public PaperRoad(int LEHENGOMAILA) {
        this.LEHENGOMAILA = LEHENGOMAILA;
        pertsonaia = new Pertsonaia("Papera.png",360, 700);
    }
    
    public PaperRoad(){
        LEHENGOMAILA = 1;
    }
    
    public void hasi(){
        mailaKargatu(LEHENGOMAILA);        

    }
    
    public void sortuGolpeListener(){
        golpeTimer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle crashRect;
                crashRect = null;
                for(Vehicle vehicle: mapa.getVehicles()){
                    crashRect = pertsonaia.getRectangle().intersection(vehicle.getRectangle());
                    if(crashRect.getWidth()>0 && crashRect.getHeight()>0){
                        System.out.println("there has been a Crash! ");
                        pertsonaia.getLabel().setLocation(360,700);
                        jokalaria.decreaseLife();
                        jokalariBizitzaLabel.setText(Integer.toString(jokalaria.getLifes()));
                        if(jokalaria.getLifes()==0){
                            JOptionPane.showMessageDialog(null, "GAME OVER!!");
                            nireFrame.setVisible(false);
                        }
                        break;
                    }
                }
            }
        });
        golpeTimer.start();
    }
    
    public void sortuIrabaziMailaListener(){
        irabaziTimer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(pertsonaia.getLabel().getY()<=0){
                    pertsonaia.getLabel().setLocation(900, 900);
                    JOptionPane.showMessageDialog(null, "NIVEL "+mapa.getLevel()+" SUPERADO!!");
                    if(mapa.getLevel()==4){
                        JOptionPane.showMessageDialog(null, "USTED HA GANADO EL JUEGO!! FELICITACIONES");
                        nireFrame.setVisible(false);
                    }else{
                        mailaKargatu(mapa.getLevel()+1); 
                    } 
                }
                
                Rectangle crashCoinRect;
                crashCoinRect = null;
                for(Coin coin: mapa.getCoins()){
                    crashCoinRect = pertsonaia.getRectangle().intersection(coin.getRectangle());
                    if(crashCoinRect.getWidth()>0 && crashCoinRect.getHeight()>0){
                        System.out.println("Player Got a Coin! ");
                        jokalaria.increaseCoins();
                        jokalariTxanponaLabel.setText(Integer.toString(jokalaria.getCoins()));
                        coin.getLabel().setLocation(-100,-100);
                        System.out.println("toal coins: "+jokalaria.getCoins());
                        break;
                    }
                } 
                
                
                bukaerakoDenbora = System.currentTimeMillis();
                System.out.println("startTime:"+hasieraDenbora);
                System.out.println("endTime:"+bukaerakoDenbora);
                denboraSegundu = (int)((bukaerakoDenbora-hasieraDenbora)/1000);
                denboraLabel.setText(Integer.toString(denboraSegundu));
                
            }
        });
        irabaziTimer.start();
    }
    
    public void mailaKargatu(int level){
        Pertsonaia pertsona=new Pertsonaia("Papera.png",360, 700);
        if(level==1){
            jokalaria= new Player(0,0,3,pertsona);
        }
        jokalaria.setYcloserToGoal(9999); // initialize coordinates
        
        
        
        
        if(level!=1){
            borratuAurrekoMaila();
        }
        
        nireFrame = new JFrame("Paper Road 0.1");   
        nireFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        nireFrame.setLayout(null);
        nireFrame.setSize(725,782);
        nireFrame.setLocationRelativeTo(null); // center frame on monitor
        nireFrame.setResizable(false);
        
        
        
        jokalariPuntuakLabel = new JLabel("Puntuak:   "+Integer.toString(jokalaria.getPoints()));
        jokalariPuntuakLabel.setBounds(200,16,100,20);
        nireFrame.add(jokalariPuntuakLabel);
        
        denboraLabel = new JLabel("Denbora:   "+Integer.toString(0));
        denboraLabel.setBounds(360,16,100,20);
        nireFrame.add(denboraLabel);
        
        jokalariTxanponaLabel = new JLabel("Txanponak:   "+Integer.toString(jokalaria.getCoins()));
        jokalariTxanponaLabel.setBounds(500,16,100,20);
        nireFrame.add(jokalariTxanponaLabel);
        
        jokalariBizitzaLabel = new JLabel("Bizitzak:   "+Integer.toString(jokalaria.getLifes()));
        jokalariBizitzaLabel.setBounds(620,16,100,20);
        nireFrame.add(jokalariBizitzaLabel);
        
        
        
        nireFrame.add(pertsona.getLabel());
        pertsona.getLabel().setLocation(360, 700);
        
        mapa = new Mapa(level, 0 , 0);
        
        for(Vehicle vehicle: mapa.getVehicles()){
            nireFrame.add(vehicle.getLabel());
        } 
        
        for(Coin coin: mapa.getCoins()){
            nireFrame.add(coin.getLabel());
        }
        
        nireFrame.add(mapa.getLabel());
        nireFrame.setVisible(true);
        nireFrame.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        pertsona.getLabel().setIcon(pertsona.getAurreraIcon());
                        pertsona.getLabel().setLocation(pertsona.getLabel().getX(), pertsona.getLabel().getY()-50);
                        if(pertsona.getLabel().getY()<jokalaria.getYcloserToGoal()){
                            jokalaria.setYcloserToGoal(pertsona.getLabel().getY());
                            jokalaria.increasePoints(1);
                            jokalariPuntuakLabel.setText(Integer.toString(jokalaria.getPoints()));
                            System.out.println("puntos acumulados:" +jokalaria.getPoints());
                            
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(pertsona.getLabel().getY()<=650){
                            pertsona.getLabel().setLocation(pertsona.getLabel().getX(), pertsona.getLabel().getY()+50);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(pertsona.getLabel().getX()>=50){
                            pertsona.getLabel().setLocation(pertsona.getLabel().getX()-50, pertsona.getLabel().getY());
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(pertsona.getLabel().getX()<=650){
                            pertsona.getLabel().setLocation(pertsona.getLabel().getX()+50, pertsona.getLabel().getY());
                        }
                        break;
                }
                //System.out.println("Key: "+e.getKeyCode());
            }
        });
        
        sortuGolpeListener();
        
        if(level==1){
            hasieraDenbora = System.currentTimeMillis();
        }
        
        sortuIrabaziMailaListener();
        
    }
    
    public void borratuAurrekoMaila(){
        golpeTimer.stop();
        irabaziTimer.stop();
        golpeTimer=null;
        irabaziTimer=null;
        mapa=null;
        nireFrame.setVisible(false);
        nireFrame=null;
    }
    
    public static void main(String[] args) {
        System.out.println("INICIANDO CROSSY ROAD");
        
        Menu menua = new Menu();
        
        menua.erakutsi();
        
    }
}
