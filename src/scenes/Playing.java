package scenes;

import enemies.Orc;
import helperMethods.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.TowerManager;
import objects.PathPoint;
import objects.Tower;
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
    private TowerManager towerManager;
    private Tower selectedTower;

    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        actionBar =new ActionBar(0,640,640,160,this);
        enemyManager=new EnemyManager(this,start,end);
        towerManager=new TowerManager(this);

    }

    public void update() {
        updateTick();
        enemyManager.update();
        towerManager.update();
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
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
        towerManager.draw(g);
        drawSelectedTower(g);
        drawHighlight(g);
    }

    private void drawHighlight(Graphics g) {
        g.setColor(new Color(94, 54, 126));
        g.drawRect(mouseX,mouseY,32,32);
    }

    private void drawSelectedTower(Graphics g) {
        if(selectedTower!=null) {
            g.drawImage(towerManager.getTowerImages()[selectedTower.getTowerType()], mouseX, mouseY, null);
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
            if (selectedTower !=null){
                if (isTileGrass(mouseX,mouseY)) {
                    if(getTowerAt(mouseX,mouseY)==null) {
                        //placing the tower, we will pass mouse postion too.
                        towerManager.addTower(selectedTower, mouseX, mouseY);
                        selectedTower = null;
                    }
                }
            }else {
                Tower t = getTowerAt(mouseX,mouseY);

                actionBar.displayTower(t);

                }
        }
    }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x,y);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y/32][x/32];
        int tileType= game.getTileManager().getTile(id).getTileType();

        //only set tower on grass tiles
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

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            selectedTower= null;
        }
    }
}
