package scenes;

import enemies.Enemy;
import helperMethods.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.FriendManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Friendly;
import ui.ActionBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helperMethods.Constants.Tiles.GRASS_TILE;

public class Playing extends GameScene implements SceneMethods{


    private int[][] lvl;
    private ActionBar actionBar;
    private int mouseX,mouseY;
    private EnemyManager enemyManager;
    private PathPoint start,end;
    private FriendManager friendManager;
    private Friendly selectedFriendly;
    private ProjectileManager projectileManager;
    private WaveManager waveManager;


    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        actionBar =new ActionBar(0,640,640,160,this);
        enemyManager=new EnemyManager(this,start,end);
        friendManager =new FriendManager(this);
        projectileManager= new ProjectileManager(this);
        waveManager=new WaveManager(this);

    }


    public void update() {
        updateTick();
        waveManager.update();

        if(isTimeForNewEnemy()){
            spawnEnemy();
        }

        if(isAllEnemyDead()){
            if(isMoreWaves()){
                waveManager.startTimer();
                if(isWaveTimerOver()){
                    waveManager.increaseWaveIndex();
                    enemyManager.getEnemies().clear();
                    waveManager.resetEnemyIndex();
                }

            }
        }


        enemyManager.update();
        friendManager.update();
        projectileManager.update();

    }

    private boolean isWaveTimerOver() {
        return waveManager.isWaveTimerOver();
    }

    private boolean isMoreWaves() {
        return waveManager.isMoreWaves();
    }

    private boolean isAllEnemyDead() {

        if(waveManager.isMoreEnemies()){
            return false;
        }
        for (Enemy e: enemyManager.getEnemies()){
            if(e.isAlive()){
                return false;
            }
        }
        return true;
    }

    public void setSelectedFriend(Friendly selectedFriendly) {
        this.selectedFriendly = selectedFriendly;
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.getLevelData("new_Level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoint("new_Level");
        start=points.get(0);
        end=points.get(1);
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        updateTick();
        actionBar.draw(g);
        enemyManager.draw(g);
        friendManager.draw(g);
        drawSelectedFriend(g);
        drawHighlight(g);
        projectileManager.draw(g);
    }

    private void drawHighlight(Graphics g) {
        g.setColor(new Color(94, 54, 126));
        g.drawRect(mouseX,mouseY,32,32);
    }

    private void drawSelectedFriend(Graphics g) {
        if(selectedFriendly !=null) {
            g.drawImage(friendManager.getFriendImages()[selectedFriendly.getFriendType()], mouseX, mouseY, null);
        }
    }

    private void drawLevel(Graphics g) {
        for(int y= 0;y < lvl.length; y++){
            for(int x = 0;x < lvl[y].length; x++){
                int id = lvl[y][x];
                if(isAnimated(id)){
                    g.drawImage(getSprite(id,animationIndex),x*32,y*32,null);
                } else
                    g.drawImage(getSprite(id),x*32,y*32,null);
            }
        }
    }


    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            actionBar.mouseClicked(x, y);
        }else {
            if (selectedFriendly !=null){
                if (isTileGrass(mouseX,mouseY)) {
                    if(getFriendAt(mouseX,mouseY)==null) {
                        //placing the friendly, we will pass mouse postion too.
                        friendManager.addFriend(selectedFriendly, mouseX, mouseY);
                        selectedFriendly = null;
                    }
                }
            }else {
                Friendly t = getFriendAt(mouseX,mouseY);

                actionBar.displayFriend(t);

                }
        }
    }

    private Friendly getFriendAt(int x, int y) {
        return friendManager.getFriendAt(x,y);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y/32][x/32];
        int tileType= game.getTileManager().getTile(id).getTileType();

        //only set friend on grass tiles
        return tileType== GRASS_TILE;
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y >= 640) {
            actionBar.mouseMoved(x,y);
        }else{
            mouseX=(x/32)*32;
            mouseY=(y/32)*32;
        }
    }
    @Override
    public void mousePressed(int x, int y) {
        if(y >= 640) {
            actionBar.mousePressed(x,y);
        }
    }
    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x,y);
    }
    @Override
    public void mouseDragged(int x, int y) {
    }
    public void shoot(Friendly t, Enemy e) {
        projectileManager.newProjectile(t,e);
    }
    public void setLevel(int[][] level) {
        this.lvl=lvl;
    }

    public int getTileType(int x,int y){

        int xCord=x/32;
        int yCord=y/32;

        if(xCord<0||xCord >19)
            return 0;
        if(yCord<0||yCord >19)
            return 0;

        int id= lvl[y/32][x/32];

        return game.getTileManager().getTile(id).getTileType();
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            selectedFriendly = null;
        }
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());

    }

    private boolean isTimeForNewEnemy() {
        if(waveManager.isTimeForNewEnemy()) {
            if (waveManager.isMoreEnemies())
                return true;

        }
        return false;
    }


    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }



}
