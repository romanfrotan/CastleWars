package objects;

import helperMethods.Constants;

public class Tower {

    private int x,y,id,towerType,damage;
    private float range,cooldown;
    private int cooldownTick;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDamage();
        setRange();
        setCooldown();
    }


    public boolean isCooldownOVer() {

        if(cooldownTick>cooldown){
            return true;
        }
    return false;
    }

    public void resetCooldown() {
        cooldownTick=0;
    }

    public void update() {
        cooldownTick++;
    }



    private void setCooldown() {
        cooldown= Constants.Towers.getCooldown(towerType);
    }

    private void setRange() {
        range= Constants.Towers.getRange(towerType);
    }

    private void setDamage() {
        damage=Constants.Towers.getDamage(towerType);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }



}
