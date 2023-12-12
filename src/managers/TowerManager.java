package managers;

import enemies.Enemy;
import helperMethods.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImages;
    private int towerCount=0;
    private ArrayList<Tower> towers= new ArrayList<>();


    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImages();

    }


    private void loadTowerImages() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImages = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            towerImages[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
        }

    }
    public void update() {
        for (Tower t: towers){
            t.update();

            attackEnemyInRange(t);
        }

    }

    private void attackEnemyInRange(Tower t) {

            for(Enemy e :playing.getEnemyManager().getEnemies()){
                if(e.isAlive()){
                if(isEnemyInRange(t,e)) {
                    if (t.isCooldownOVer()) {
                        playing.shoot(t, e);
                        t.resetCooldown();
                    }
                }else {
                    
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {

        int range = helperMethods.Utilities.getHypoDistance(t.getX(),t.getY(),e.getX(),e.getY());

        if (range < t.getRange()) {
            return true;
        }
      return false;
    }

    public void draw (Graphics g) {

        for (Tower t: towers){
            g.drawImage(towerImages[t.getTowerType()],t.getX(),t.getY(),null);
        }

    }

    public BufferedImage[] getTowerImages() {
        return towerImages;
    }

    public void addTower(Tower selectedTower,int x, int y) {

        towers.add(new Tower(x,y,towerCount++,selectedTower.getTowerType()));

    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t:towers) {
            if (t.getX()==x)
                if (t.getY()==y)
                    return t;
        }
   return null;
    }
}
