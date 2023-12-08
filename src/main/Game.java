package main;

import helperMethods.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.*;

public class Game extends JFrame implements Runnable {

   private GameScreen gameScreen;
   private int updates;
   private long lastTimeUPS;
   private Thread gameThread;

   private final double FPS_SET=120.0;
   private final double UPS_SET=60.0;

   //classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private TileManager tileManager;


    public Game () {

        setDefaultCloseOperation(EXIT_ON_CLOSE );
        setLocationRelativeTo(null);

        initClasses();
        createDefaultLevel();
        setResizable(false);
        add(gameScreen);
        pack();// needs to be added after adding game to jpanel!
        setVisible(true);

    }

    private void createDefaultLevel() {
        int [] array=new int[400]; //20*20 (2d Array) = 400
        for(int i= 0;i< array.length;i++){
            array[i]=0; //0=Grass

            LoadSave.CreateLevel("new_Level",array);
        }
    }

    private void initClasses() {
        tileManager=new TileManager();
        render= new Render(this);
        gameScreen=new GameScreen(this);
        menu= new Menu(this);
        playing= new Playing(this);
        settings=new Settings(this);
        editing=new Editing(this);
    }



    private void start(){
        gameThread=new Thread(this) {};
        gameThread.start();
    }

    private void callUPS() {
         if(System.currentTimeMillis() - lastTimeUPS >=1000){
             System.out.println("UPS: "+updates);
             updates = 0;
             lastTimeUPS = System.currentTimeMillis();

         }

    }
        private void updateGame() {
        switch (GameStates.gameState) {
            case EDIT:
                editing.update();
                break;
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
            case SETTINGS:
                break;
        }
    }

    public static void main(String[] args) {
            Game game= new Game();
            game.gameScreen.initInputs();
            game.start();
        }

   @Override
   public void run() {
       double timePerFrame=1000000000.0/FPS_SET;
       double timePerUpdate=1000000000.0/UPS_SET;

       long lastFrame=System.nanoTime();
       long lastUpdate = System.nanoTime();
       long lastTimeCheck=System.currentTimeMillis();

       int frames=0;
       int updates=0;

       long now;

        while (true){

            now=System.nanoTime();

            //render
            if(now- lastFrame >= timePerFrame){
                repaint();
                lastFrame=now;
                frames++;
            }
            //update
            if(now- lastUpdate >=timePerUpdate) {
                updateGame();
                lastUpdate=now;
                updates++;
            }
            //checking FPS and UPS
            if(System.currentTimeMillis()-lastTimeCheck>=1000){
                System.out.println("FPS "+frames+" | UPS: "+updates);
                frames=0;
                updates=0;
                lastTimeCheck=System.currentTimeMillis();
            }
        }
   }

   //Getters

    public Menu getMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }
    public Settings getSettings() {
        return settings;
    }
    public Render getRender(){
        return render;
    }
    public Editing getEditor(){return editing;}
    public TileManager getTileManager(){return tileManager;}
}
