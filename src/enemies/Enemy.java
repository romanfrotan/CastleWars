package enemies;

import helperMethods.Constants;

import java.awt.*;

import static helperMethods.Constants.Direction.*;

public abstract class Enemy {

    protected float x,y;
    protected Rectangle bounds;
    protected int health;
    protected int ID;
    protected int enemyType;
    protected int lastDirection;
    protected int maxHealth;
    protected boolean alive=true;

    public Enemy(float x,float y, int id, int enemyType) {

        this.x=x;
        this.y=y;
        this.ID=id;
        this.enemyType=enemyType;
        bounds= new Rectangle((int)x,(int)y,32,32);
        lastDirection=-1;
        setStartHealth();

    }

    public float getBarFloat() {
        return (float)health/maxHealth;
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
        updateHitBox();

    }
        public void updateHitBox() {
            bounds.x=(int) x;
            bounds.y=(int) y;
        }
    public void setPosition(int x, int y) {
        //dont use for move this is for position fix
        this.x=x;
        this.y=y;

    }

    private void setStartHealth() {
        health=Constants.Enemies.GetStartHealth(enemyType);
        maxHealth=health;
    }
    public void hurt(int damage) {
        this.health-=damage;
        if (health<=0) {
            alive=false;
        }
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

    public boolean isAlive() {
        return alive;
    }



}
