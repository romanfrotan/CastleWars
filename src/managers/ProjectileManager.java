package managers;

import enemies.Enemy;
import helperMethods.Constants;
import helperMethods.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Projectiles.*;
import static helperMethods.Constants.Towers.*;

public class ProjectileManager {
    private Playing playing;
    private BufferedImage[] projectileImages;
    private ArrayList<Projectile> projectiles= new ArrayList<>();
    private int projectileId=0;



    public ProjectileManager (Playing playing) {
        this.playing=playing;
        ImportImages();

    }

    private void ImportImages () {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImages=new BufferedImage[3];
         for(int i=0;i<3;i++) {
             projectileImages[i] = atlas.getSubimage((7 + i) * 32, 32, 32,32);
         }

    }

    public void update(){
            for(Projectile p:projectiles) {
                if(p.isActive()) {
                    p.move();
                    if(isProjectileHit(p)){
                        p.setActive(false);
                    }else {

                    }
                }
            }

    }

    private boolean isProjectileHit(Projectile p) {
        for(Enemy e :playing.getEnemyManager().getEnemies()){
            if(e.getBounds().contains(p.getPosition())){
                e.hurt(p.getDamage());
                return true;
            }
        }

        return false;
    }

    public void newProjectile (Tower t, Enemy e) {
        int type=getProjectileType(t);

        int xDistance= (int)(t.getX()-e.getX());
        int yDistance= (int)(t.getY()-e.getY());

        int totalDistance= Math.abs(xDistance)+Math.abs(yDistance);

        float xPercent = (float) Math.abs(xDistance)/totalDistance;

        float xSpeed= xPercent * Constants.Projectiles.getSpeed(type);
        float ySpeed=Constants.Projectiles.getSpeed(type) - xSpeed;

        if (t.getX()>e.getX()){
            xSpeed *=-1;
        }
        if (t.getY()>e.getY()) {
            ySpeed *=-1;
        }

        float arcValue= (float)Math.atan(yDistance/(float)xDistance);
        float rotate = (float)Math.toDegrees(arcValue);

        if(xDistance<0) {
            rotate += 180;
        }

        projectiles.add(new Projectile(t.getX()+16,t.getY()+16,xSpeed,ySpeed,t.getDamage(),rotate,projectileId++,type));

    }

    private int getProjectileType(Tower t) {
        switch (t.getTowerType()) {
            case ARCHER:
                return ARROW;
            case WIZARD:
                return CHAINS;
            case CANNON:
                return BOMB;
        }
        return 0;
    }



    public void draw(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;

        for (Projectile p : projectiles)
            if (p.isActive()) {
                graphics2D.translate(p.getPosition().x, p.getPosition().y);
                graphics2D.rotate(Math.toRadians(p.getRotation()));
                graphics2D.drawImage(projectileImages[p.getProjectileType()], -16, -16, null);
                graphics2D.rotate(-Math.toRadians(p.getRotation()));
                graphics2D.translate(-p.getPosition().x, -p.getPosition().y);
            }

    }
}
