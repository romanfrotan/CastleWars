package managers;

import enemies.Enemy;
import helperMethods.LoadSave;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helperMethods.Constants.Tiles.*;
import static helperMethods.Constants.Direction.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImages;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float speed = 0.5f;


    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImages = new BufferedImage[4];
        addEnemy(3 + 32, 9 * 32);
        loadEnemyImages();

    }

    private void loadEnemyImages() {
        //from our sprite_atlas in res folder, orc is at x=0 y=1.
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        enemyImages[0] = atlas.getSubimage(0 * 32, 1 * 32, 32, 32);
        enemyImages[1] = atlas.getSubimage(1 * 32, 1 * 32, 32, 32);
        enemyImages[2] = atlas.getSubimage(2 * 32, 1 * 32, 32, 32);
        enemyImages[3] = atlas.getSubimage(3 * 32, 1 * 32, 32, 32);

    }

    public void update() {
        for (Enemy enemy : enemies) {
            //is next tile road (position,dir)
            if (isNextTileRoad(enemy)) {
                //move
            }
        }
    }

    public boolean isNextTileRoad(Enemy e) {

        //tile at new possible position
        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDirection()));
        int newY = (int) (e.getY() + getSpeedandHeight(e.getLastDirection()));

        if (getTileType(newX, newY) == ROAD_TILE) {
            e.move(speed, e.getLastDirection());

        } else if (isAtEnd(e)) {
            //reached the end

        } else {
            setNewDirectionandMove(e);

        }


        return false;
    }

    private void setNewDirectionandMove(Enemy e) {
        int dir = e.getLastDirection();

        //move into the current tile fully

        int xCord = (int)(e.getX() / 32);
        int yCord = (int)(e.getY() / 32);

        fixEnemyOffsetTile(e,dir,xCord,yCord);

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int) (e.getY() + getSpeedandHeight(UP));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE)
                e.move(speed, UP);
            else
                e.move(speed, DOWN);
        } else {
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT));
            if (getTileType(newX, (int) e.getY()) == ROAD_TILE)
                e.move(speed, RIGHT);
            else
                e.move(speed, LEFT);

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
    return false;
    }

    private int getTileType(int x, int y) {
      return playing.getTileType(x,y);
    }

    private float getSpeedandHeight(int dir) {
        if(dir==UP){
            return -speed;
        }else if(dir==DOWN){
            return speed+32; //to account for enemy height
        }
        return 0;
    }


    private float getSpeedAndWidth(int dir) {

        if(dir==LEFT){
            return -speed;
        }else if(dir==RIGHT){
            return speed+32; //to account for enemy width.
        }
        return 0;
    }

    public void draw(Graphics g) {

    for (Enemy enemy:enemies) {
        drawEnemy(enemy, g);
    }
}

private void drawEnemy(Enemy enemy,Graphics g) {

    g.drawImage(enemyImages[0],(int)enemy.getX(),(int)enemy.getY(),null);
}

public void addEnemy(int x, int y){
    enemies.add(new Enemy(x,y,0,0));

}



}
