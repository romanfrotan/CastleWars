package managers;

import enemies.*;
import helperMethods.LoadSave;
import objects.PathPoint;
import scenes.Playing;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Enemies.*;
import static helperMethods.Constants.Tiles.*;
import static helperMethods.Constants.Direction.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImages;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    //private float speed = 0.5f;
    private PathPoint start,end;


    public EnemyManager(Playing playing, PathPoint start,PathPoint end) {
        this.playing = playing;
        enemyImages = new BufferedImage[4];
        this.start=start;
        this.end=end;
        addEnemy(ORC);
        addEnemy(BAT);
        addEnemy(KNIGHT);
        addEnemy(WOLF);

        loadEnemyImages();

    }

    private void loadEnemyImages() {
        //from our sprite_atlas in res folder, orc is at x=0 y=1.
        BufferedImage atlas = LoadSave.getSpriteAtlas();

        for(int i =0;i<4;i++){
            enemyImages[i] = atlas.getSubimage(i * 32, 32, 32, 32);
        }

    }

    public void update() {
        for (Enemy enemy : enemies) {
            updateEnemyMove(enemy);
        }
    }

    public void updateEnemyMove(Enemy e) {

        if(e.getLastDirection()==-1){
            setNewDirectionandMove(e);
        }

        //tile at new possible position
        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDirection(),e.getEnemyType()));
        int newY = (int) (e.getY() + getSpeedandHeight(e.getLastDirection(),e.getEnemyType()));

        if (getTileType(newX, newY) == ROAD_TILE) {
            e.move(GetSpeed(e.getEnemyType()), e.getLastDirection());

        } else if (isAtEnd(e)) {
            System.out.println("Lives Lost");

        } else {
            setNewDirectionandMove(e);

        }


    }


    private void setNewDirectionandMove(Enemy e) {
        int dir = e.getLastDirection();

        //move into the current tile fully

        int xCord = (int)(e.getX() / 32);
        int yCord = (int)(e.getY() / 32);

        fixEnemyOffsetTile(e,dir,xCord,yCord);

        if(isAtEnd(e)){
            return;
        }

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int) (e.getY() + getSpeedandHeight(UP,e.getEnemyType()));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE)
                e.move(GetSpeed(e.getEnemyType()), UP);
            else
                e.move(GetSpeed(e.getEnemyType()), DOWN);
        } else {
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT,e.getEnemyType()));
            if (getTileType(newX, (int) e.getY()) == ROAD_TILE)
                e.move(GetSpeed(e.getEnemyType()), RIGHT);
            else
                e.move(GetSpeed(e.getEnemyType()), LEFT);

        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {

        switch (dir) {

            case RIGHT:
                if(xCord<19)
                    xCord++;
                break;
            case DOWN:
                if(yCord<19)
                    yCord++;
                break;
        }
        e.setPosition(xCord*32,yCord*32);

    }

    private boolean isAtEnd(Enemy e) {
        if(e.getX()== end.getxCord()*32)
            if(e.getY()==end.getyCord()*32)
                return true;
        return false;
    }

    private int getTileType(int x, int y) {
      return playing.getTileType(x,y);
    }

    private float getSpeedandHeight(int dir,int enemyType) {
        if(dir==UP){
            return -GetSpeed(enemyType);
        }else if(dir==DOWN){
            return GetSpeed(enemyType)+32; //to account for enemy height
        }
        return 0;
    }


    private float getSpeedAndWidth(int dir,int enemyType) {

        if(dir==LEFT){
            return -GetSpeed(enemyType);
        }else if(dir==RIGHT){
            return GetSpeed(enemyType)+32; //to account for enemy width.
        }
        return 0;
    }

    public void draw(Graphics g) {

    for (Enemy enemy:enemies) {
        drawEnemy(enemy, g);
    }
}

private void drawEnemy(Enemy enemy,Graphics g) {

    g.drawImage(enemyImages[enemy.getEnemyType()],(int)enemy.getX(),(int)enemy.getY(),null);
}

public void addEnemy(int enemyType){

          int x = start.getxCord()*32;
          int y = start.getyCord()*32;
        switch (enemyType) {

            case ORC:
                enemies.add(new Orc(x,y,0));
                break;
            case BAT:
                enemies.add(new Bat(x,y,0));
                break;
            case WOLF:
                enemies.add(new Wolf(x,y,0));
                break;
            case KNIGHT:
                enemies.add(new Knight(x,y,0));
                break;

        }
}

}
