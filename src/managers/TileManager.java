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
        int id=0;
        tiles.add(GRASS=new Tile(getSprite(8,1),id++,"Grass"));
        tiles.add(GRASS=new Tile(getSprite(0,6),id++,"Water"));
        tiles.add(GRASS=new Tile(getSprite(9,0),id++,"Road"));

    }
    //id=array index of sprite added to the tiles array.
    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private void loadAtalas() {

       atlas = LoadSave.getSpriteAtlas();
    }
    public Tile getTile(int id){
        return tiles.get(id);
    }

    private BufferedImage getSprite(int xcord, int ycord) {
        return atlas.getSubimage(xcord*32,ycord*32,32,32);
    }

}
