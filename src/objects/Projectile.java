package objects;

import java.awt.geom.Point2D;

public class Projectile {

    private Point2D.Float position;
    private int id,projectileType;
    private boolean active =true;
    private float xSpeed,ySpeed,rotation;
    private int damage;

    public float getRotation() {
        return rotation;
    }

    public Projectile(float x, float y, float xSpeed, float ySpeed, int damage, float rotation, int id, int projectileType) {

        position = new Point2D.Float(x, y);
        this.projectileType = projectileType;
        this.id = id;
        this.xSpeed=xSpeed;
        this.ySpeed=ySpeed;
        this.damage=damage;
        this.rotation=rotation;
    }


    public void move () {
        position.x += xSpeed;
        position.y += ySpeed;
    }

    public Point2D.Float getPosition() {
        return position;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public int getDamage() {
        return damage;
    }

}
