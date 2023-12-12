package objects;

import helperMethods.Constants;

public class Friendly {

    private int x,y,id, friendType,damage;
    private float range,cooldown;
    private int cooldownTick;

    public Friendly(int x, int y, int id, int friendType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.friendType = friendType;
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
        cooldown= Constants.Friends.getCooldown(friendType);
    }

    private void setRange() {
        range= Constants.Friends.getRange(friendType);
    }

    private void setDamage() {
        damage= Constants.Friends.getDamage(friendType);
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

    public int getFriendType() {
        return friendType;
    }

    public int getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }


}
