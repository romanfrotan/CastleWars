package managers;

import helperMethods.Constants;
import helperMethods.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Towers.*;


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
