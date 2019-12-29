
package oop;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
	
public class GamePanel extends JLayeredPane implements MouseListener, MouseMotionListener {
    
     GameScreen.PlantType pickingPlant = GameScreen.PlantType.None;
     
     GameScreen.IsShovel pickingShovel = GameScreen.IsShovel.None;
    
    
    boolean isGrabbed = false;
    
    GridLayOut gridact;

    
    Image peashooterImage, peashooter;
    Image freezePeashooterImage;
    Image sunflowerImage;
    Image peaImage;
    Image freezePeaImage;
    Image walnutImage, mowerImage;
    Image sunBoard;
    Image backgroundImage, shovelImage;

    Image normalzombie, coneHeadZombie;
    Image normalZombieImage;
    Image coneHeadZombieImage;
    
    GridLayOut[] grid;
    GridLayOut[] grids;
    
    ArrayList<ArrayList<Zombie>> laneZombies;
    ArrayList<ArrayList<Pea>> lanePeas;
    
    static ArrayList <LawnMower> mowers;
    
    static public ArrayList<LawnMower> getMowerList(){
    	return mowers;
    }
    
    public static void removeMower(LawnMower m) {
    	mowers.remove(m);
    }
   //ArrayList<ArrayList<lawnMower>> laneMowers;
    
    ArrayList<Sun> sunList;

    Timer repaint;
    
    Timer move;
    
    Timer sunProducer;
    
    
    Timer zombieProducer;
    
    JLabel sunScoreboard;

   
      

    int mouseX , mouseY;

    private int sunScore;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public GamePanel(JLabel sunScoreboard){
        setSize(1000,752);
        setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.sunScoreboard = sunScoreboard;
        setSunScore(10000);  //pool avalie

        backgroundImage  = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();
        
        shovelImage = new ImageIcon(this.getClass().getResource("/images/shovel.png")).getImage();
        
        

        peashooterImage = new ImageIcon(this.getClass().getResource("/images/peashooter.gif")).getImage();
        //peashooterImage = peashooter.getScaledInstance(65, 65, Image.SCALE_DEFAULT);
        
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("/images/frozenPeaShooter.png")).getImage();
        
        sunflowerImage = new ImageIcon(this.getClass().getResource("/images/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("/images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("/images/freezepea.png")).getImage();
        walnutImage = new ImageIcon(this.getClass().getResource("/images/walnut_full_life.gif")).getImage();
        mowerImage = new ImageIcon(this.getClass().getResource("/images/lawn_mower.gif")).getImage();
        
        
        normalzombie = new ImageIcon(this.getClass().getResource("/images/zombie_normal.gif")).getImage();
        normalZombieImage = normalzombie.getScaledInstance(63, 100, Image.SCALE_DEFAULT);
        
        coneHeadZombie = new ImageIcon(this.getClass().getResource("/images/coneheadzombie.gif")).getImage();
        coneHeadZombieImage = coneHeadZombie.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

       

        lanePeas = new ArrayList<>();
        lanePeas.add(new ArrayList<>()); //line 1
        lanePeas.add(new ArrayList<>()); //line 2
        lanePeas.add(new ArrayList<>()); //line 3
        lanePeas.add(new ArrayList<>()); //line 4
        lanePeas.add(new ArrayList<>()); //line 5
        
        laneZombies = new ArrayList<>();
        laneZombies.add(new ArrayList<>()); //line 1
        laneZombies.add(new ArrayList<>()); //line 2
        laneZombies.add(new ArrayList<>()); //line 3
        laneZombies.add(new ArrayList<>()); //line 4
        laneZombies.add(new ArrayList<>()); //line 5
        
       
        sunList = new ArrayList<>();
        
        mowers = new ArrayList<>();
        

        grid = new GridLayOut[40];
        
        
        for (int i = 0; i < 40; i++) {
            
            GridLayOut box = new GridLayOut();
            
            box.setLocation(250 + (i%8)*92, 100 + (i/8)*120);
            
            
            //place plant
            box.setAction(new Planting((i%8),(i/8)));
            
            
            
            grid[i] = box;
            
            
            add(box,new Integer(0));
            
            
        }
        
       

        

        
        
            

        repaint = new Timer(25,(ActionEvent e) -> {
            repaint();
        });
        
        repaint.start();
        
        move = new Timer(60,(ActionEvent e) -> move());
        
        move.start();
        
        
        
        sunProducer = new Timer(30000,(ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(this,rnd.nextInt(800)+100,0,rnd.nextInt(300)+200);
            sunList.add(sta);
            add(sta,new Integer(1));
        });
        
        sunProducer.start();
        

        zombieProducer = new Timer(7000,(ActionEvent e) -> {
            
            String[] zombieType = {"NormalZombie", "ConeHeadZombie"};
            
            Random rnd = new Random();
            
            
            int l = rnd.nextInt(5);
            
            
            int r = rnd.nextInt(zombieType.length);
            
            
           
             Zombie  z = Zombie.getZombie(zombieType[r],GamePanel.this,l);
                    
            
            laneZombies.get(l).add(z);
            
        });
        zombieProducer.start();
        
        
        mowers.add(new LawnMower(GamePanel.this, 0));
        mowers.add(new LawnMower(GamePanel.this, 1));
        mowers.add(new LawnMower(GamePanel.this, 2));
        mowers.add(new LawnMower(GamePanel.this, 3));
        mowers.add(new LawnMower(GamePanel.this, 4));
        
    }

    private void move(){
        
        for (int i = 0; i < 5 ; i++) {
            
            for(Zombie zombie : laneZombies.get(i)){
                
                zombie.move();
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                
                Pea pea = lanePeas.get(i).get(j);
                
                pea.move();
            }
           

        }

        for (int i = 0; i < sunList.size() ; i++) {
            sunList.get(i).drop();
        }
        
       for(int i = 0; i < mowers.size(); i++){
                LawnMower tempMower = mowers.get(i);
        	tempMower.move();
	
        }
      
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        g.drawImage(backgroundImage,0,0,null);
        
        
       
        

        //Draw Plants
        for (int i = 0; i < 40; i++) {
            
            GridLayOut grid1 = grid[i];
            
            if(grid1.locatePlant != null){
                
                Plant plant = grid1.locatePlant;
                
                if(plant instanceof Peashooter){
                    g.drawImage(peashooterImage,270 + (i%8)*90,124 + (i/8)*120,null);                  
                }
                
                if(plant instanceof FreezePeashooter){
                    g.drawImage(freezePeashooterImage,270 + (i%8)*90,124 + (i/8)*120,null);
                }
                
                if(plant instanceof Sunflower){
                    g.drawImage(sunflowerImage,270 + (i%8)*90,124 + (i/8)*120,null);
                }
                
                if(plant instanceof Walnut){ 
                    g.drawImage(walnutImage,270 + (i%8)*90,124 + (i/8)*120,null);
                }

        }
            
    }
//        for (int i = 0; i < 5 ; i++) {           
            for(int i=0; i<mowers.size(); i++){
               LawnMower l = mowers.get(i);
                    g.drawImage(mowerImage,l.myX,120+(i*120),null);              
                
            }
//    }
      

        for (int i = 0; i < 5 ; i++) {
            for(Zombie z : laneZombies.get(i)){
                if(z instanceof NormalZombie){
                    g.drawImage(normalZombieImage,z.posX,109+(i*120),null);
                }else if(z instanceof ConeHeadZombie){
                    g.drawImage(coneHeadZombieImage,z.posX,109+(i*120),null);
                }
                
            }
            
            

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea p = lanePeas.get(i).get(j);
                if(p instanceof FreezePea){
                    g.drawImage(freezePeaImage, p.posX, 130 + (i * 120), null);
                }else {
                    g.drawImage(peaImage, p.posX, 130 + (i * 120), null);
                }
            }

        }

//        if(!"".equals(pickingPlant)){
//            System.out.println(pickingPlant);
//            if(pickingPlant == GameScreen.PlantType.Sunflower) {
//                g.drawImage(sunflowerImage,mouseX,mouseY,null);
//            }
//
//        } 
//        if(!"".equals(pickingShovel)){
//            System.out.println(pickingShovel);
//            if(pickingShovel == GameScreen.IsShovel.Shovel) {
//                g.drawImage(shovelImage,mouseX,mouseY,null);
//            }
//
//        } 


    }
    
    


    class Planting implements ActionListener {
        
        int gridX,gridY;

        public Planting(int gridX, int gridY){
            this.gridX = gridX;
            this.gridY = gridY;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(pickingShovel == GameScreen.IsShovel.Shovel){
                
                if(grid[gridX + gridY*8].locatePlant != null){
                    grid[gridX + gridY*8].removePlant();
                }
            }
            
            if(pickingPlant == GameScreen.PlantType.Sunflower){
                
                //check if box is empty or not
                if(getSunScore()>=50 && grid[gridX + gridY*8].locatePlant == null) {
                    
                    grid[gridX + gridY * 8].setPlant(new Sunflower(GamePanel.this, gridX, gridY));
                    
                    setSunScore(getSunScore()-50);
                    
                }
            }
            if(pickingPlant == GameScreen.PlantType.Peashooter){
                
                if(getSunScore() >= 100 && grid[gridX + gridY*8].locatePlant == null) {
                   
                    grid[gridX + gridY * 8].setPlant(new Peashooter(GamePanel.this, gridX, gridY));
                   
                    setSunScore(getSunScore()-100);
                
                }
            }

            if(pickingPlant == GameScreen.PlantType.FreezePeashooter){
                
                if(getSunScore() >= 175 && grid[gridX + gridY*8].locatePlant == null) {
                   
                    grid[gridX + gridY*8].setPlant(new FreezePeashooter(GamePanel.this, gridX, gridY));
                    
                    setSunScore(getSunScore()-175);
                }
            }
            
            if(pickingPlant == GameScreen.PlantType.Walnut){
                
                if(getSunScore() >= 50 && grid[gridX + gridY*8].locatePlant == null) {
                   
                    grid[gridX + gridY*8].setPlant(new Walnut(GamePanel.this, gridX, gridY));
                   
                    setSunScore(getSunScore()-50);
                }
            }
            
            
            
            pickingShovel = GameScreen.IsShovel.None;
            
            pickingPlant = GameScreen.PlantType.None;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
         // System.out.println("Mouse dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        
       
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        

        System.out.println(mouseX + ", " + mouseY);

    }
    @Override
    public void mousePressed(MouseEvent e) {
            
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
