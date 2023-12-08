package managers;

import helperMethods.ImageFix;
import helperMethods.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    public Tile GRASS, WATER, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R, BL_WATER_CORNER,
            TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, T_WATER, R_WATER, B_WATER, L_WATER, TL_ISLE, TR_ISLE,
            BR_ISLE, BL_ISLE;

    private BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> roadsStraight = new ArrayList<>();
    public ArrayList<Tile> roadsCorner = new ArrayList<>();
    public ArrayList<Tile> watersCorner = new ArrayList<>();
    public ArrayList<Tile> watersBeach = new ArrayList<>();
    public ArrayList<Tile> watersIsland = new ArrayList<>();



    public ArrayList<Tile> getRoadsStraight() {
        return roadsStraight;
    }

    public ArrayList<Tile> getRoadsCorner() {
        return roadsCorner;
    }

    public ArrayList<Tile> getWatersCorner() {
        return watersCorner;
    }

    public ArrayList<Tile> getWatersBeach() {
        return watersBeach;
    }

    public ArrayList<Tile> getWatersIsland() {
        return watersIsland;
    }

    public TileManager() {

        loadAtalas();
        createTiles();

    }

    private void createTiles() {

        int id = 0;

        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, "Grass"));
        tiles.add(WATER = new Tile(getAnimatedSprites(0, 0), id++, "Water"));
        roadsStraight.add(ROAD_LR = new Tile(getSprite(8, 0), id++, "Road_LR"));
        roadsStraight.add(ROAD_TB = new Tile(ImageFix.getRotatedImg(getSprite(8, 0), 90), id++, "TB_Road"));

        roadsCorner.add(ROAD_B_TO_R = new Tile(getSprite(7, 0), id++, "Road_Bottom_To_Right"));
        roadsCorner.add(ROAD_L_TO_B = new Tile(ImageFix.getRotatedImg(getSprite(7, 0), 90), id++, "Road_Left_To_Bottom"));
        roadsCorner.add(ROAD_L_TO_T = new Tile(ImageFix.getRotatedImg(getSprite(7, 0), 180), id++, "Road_Left_To_Top"));
        roadsCorner.add(ROAD_T_TO_R = new Tile(ImageFix.getRotatedImg(getSprite(7, 0), 270), id++, "Road_Top_To_Right"));

        watersCorner.add(BL_WATER_CORNER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(5,0),0), id++, "BL_Corner"));
        watersCorner.add(TL_WATER_CORNER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(5,0),90), id++, "TL_Corner"));
        watersCorner.add(TR_WATER_CORNER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(5,0),180), id++, "TR_Corner"));
        watersCorner.add(BR_WATER_CORNER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(5,0),270), id++, "BR_Corner"));



        watersBeach.add(T_WATER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(6,0),0), id++, "T_Water"));
        watersBeach.add(R_WATER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(6,0),90), id++, "R_Water"));
        watersBeach.add(B_WATER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(6,0),180), id++, "B_Water"));
        watersBeach.add(L_WATER = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(6,0),270), id++, "L_Water"));

        watersIsland.add(TL_ISLE = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(4,0),0), id++, "TL_Isle"));
        watersIsland.add(TR_ISLE = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(4,0),90), id++, "TR_Isle"));
        watersIsland.add(BR_ISLE = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(4,0),180), id++, "BR_Isle"));
        watersIsland.add(BL_ISLE = new Tile(ImageFix.getBuildRotateImg(getAnimatedSprites(0, 0),getSprite(4,0),270), id++, "BL_Isle"));

        tiles.addAll(roadsStraight);
        tiles.addAll(roadsCorner);
        tiles.addAll(watersCorner);
        tiles.addAll(watersBeach);
        tiles.addAll(watersIsland);
    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[] { getSprite(firstX, firstY), getSprite(secondX, secondY) };
    }

    private void loadAtalas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public boolean isSpriteAnimated(int id) {
       return tiles.get(id).isAnimation();
    }

    public BufferedImage getAnimatedSprite(int id,int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }
    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }


    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }


    private BufferedImage[] getAnimatedSprites(int xCord, int yCord) {
        BufferedImage[] array = new BufferedImage[4];

       for(int i=0; i<4 ;i++) {
           array[i]=getSprite(xCord+i,yCord);
       }
       return array;
    }

}
