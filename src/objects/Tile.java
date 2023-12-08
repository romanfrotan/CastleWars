package objects;

import java.awt.image.BufferedImage;



public class Tile {

    private BufferedImage[] sprite;
    private int id;
    private int tileType;


    public Tile(BufferedImage sprite, int id, int tileType) {
        this.sprite=new BufferedImage[1];
        this.sprite[0]=sprite;
        this.id=id;
        this.tileType=tileType;

    }
    public Tile(BufferedImage[] sprite,int id,int tileType) {
        this.tileType=tileType;
        this.sprite=sprite;
        this.id=id;
    }

    public int getId() {

        return id;
    }

    public BufferedImage getSprite(){
        return sprite[0];
    }

    public BufferedImage getSprite(int animationIndex){
        return sprite[animationIndex];
    }

    public boolean isAnimation(){
        if (sprite.length>1) {
            return true;
        }
        return false;
    }

    public int getTileType() {
        return tileType;
    }

}
