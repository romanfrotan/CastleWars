package enemies;

import java.awt.*;

import static helperMethods.Constants.Direction.*;

public abstract class Enemy {

    private float x,y;
    private Rectangle bounds;
    private int health;
    private int ID;
    private int enemyType;
    private int lastDirection;

    public Enemy(float x,float y, int id, int enemyType) {

        this.x=x;
        this.y=y;
        this.ID=id;
        this.enemyType=enemyType;
        bounds= new Rectangle((int)x,(int)y,32,32);
        lastDirection=-1;

    }
    public void move(float speed, int dir){

        lastDirection=dir;
        switch (dir){
            case LEFT:
                this.x -=speed;
                break;
            case UP:
                this.y-=speed;
                break;
            case RIGHT:
                this.x+=speed;
                break;
            case DOWN:
                this.y+=speed;
                break;
        }

    }
    public void setPosition(int x, int y) {
        //dont use for move this is for position fix
        this.x=x;
        this.y=y;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDirection() {
        return lastDirection;
    }
}
