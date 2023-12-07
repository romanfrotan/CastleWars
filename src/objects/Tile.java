package objects;

import java.awt.image.BufferedImage;



public class Tile {

    private BufferedImage sprite;
    private int id;
    private String name;

    public Tile(BufferedImage sprite,int id, String name) {
        this.name=name;
        this.sprite=sprite;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public BufferedImage getSprite(){

        return sprite;
    }
}
