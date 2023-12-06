package managers;

import helperMethods.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS,WATER,ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles=new ArrayList<>();


    public TileManager() {
        
        loadAtalas();
        CreateTiles();

    }

    private void CreateTiles() {
        tiles.add(GRASS=new Tile(getSprite(8,1)));
        tiles.add(GRASS=new Tile(getSprite(0,6)));
        tiles.add(GRASS=new Tile(getSprite(9,0)));

    }
    //id=array index of sprite added to the tiles array.
    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private void loadAtalas() {

       atlas = LoadSave.getSpriteAtlas();
    }

    private BufferedImage getSprite(int xcord, int ycord) {
        return atlas.getSubimage(xcord*32,ycord*32,32,32);
    }

}
