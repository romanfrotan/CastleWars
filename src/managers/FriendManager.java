package managers;

import enemies.Enemy;
import helperMethods.LoadSave;
import objects.Friendly;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class FriendManager {
    private Playing playing;
    private BufferedImage[] friendImages;
    private int friendCount =0;
    private ArrayList<Friendly> friendlies = new ArrayList<>();


    public FriendManager(Playing playing) {
        this.playing = playing;
        loadFriendImages();

    }


    private void loadFriendImages() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        friendImages = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            friendImages[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
        }

    }
    public void update() {
        for (Friendly t: friendlies){
            t.update();

            attackEnemyInRange(t);
        }

    }

    private void attackEnemyInRange(Friendly f) {

            for(Enemy e :playing.getEnemyManager().getEnemies()){
                if(e.isAlive()){
                if(isEnemyInRange(f,e)) {
                    if (f.isCooldownOVer()) {
                        playing.shoot(f, e);
                        f.resetCooldown();
                    }
                }else {
                    
                }
            }
        }
    }

    private boolean isEnemyInRange(Friendly f, Enemy e) {

        int range = helperMethods.Utilities.getHypoDistance(f.getX(),f.getY(),e.getX(),e.getY());

        if (range < f.getRange()) {
            return true;
        }
      return false;
    }

    public void draw (Graphics g) {

        for (Friendly t: friendlies){
            g.drawImage(friendImages[t.getFriendType()],t.getX(),t.getY(),null);
        }

    }

    public BufferedImage[] getFriendImages() {
        return friendImages;
    }

    public void addFriend(Friendly selectedFriendly, int x, int y) {

        friendlies.add(new Friendly(x,y, friendCount++, selectedFriendly.getFriendType()));

    }

    public Friendly getFriendAt(int x, int y) {
        for (Friendly f: friendlies) {
            if (f.getX()==x)
                if (f.getY()==y)
                    return f;
        }
   return null;
    }

}
