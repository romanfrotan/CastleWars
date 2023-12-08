package scenes;

import helperMethods.LoadSave;
import main.Game;
import objects.Tile;
import ui.ToolBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Editing extends GameScene implements  SceneMethods  {

    private int[][] lvl;
    private int mouseX,mouseY;
    private int lastTileX, lastTileY,lastTileId;
    private Tile selectedTile;
    private boolean drawSelect;
    private ToolBar toolBar;
    private int animationIndex;
    private int tick;
    private int animationSpeed=15;


    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        toolBar=new ToolBar(0,640,640,100,this);
    }
    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_Level");
    }

    @Override
    public void render(Graphics g) {
        updateTick();
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);

    }

    private void updateTick() {
        tick++;

        if(tick>=animationSpeed){
            tick=0;
            animationIndex++;
            if(animationIndex>=4){
                animationIndex=0;
            }
        }
    }

    private void drawLevel(Graphics g) {
        for(int y= 0;y < lvl.length; y++){
            for(int x = 0;x < lvl[y].length; x++){
                int id = lvl[y][x];
                if(isAnimated(id)){
                    g.drawImage(getSprite(id,animationIndex),x*32,y*32,null);
                } else
                    g.drawImage(getSprite(id),x*32,y*32,null);
            }
        }
    }

    private boolean isAnimated(int id) {
        return game.getTileManager().isSpriteAnimated(id);
    }

    private BufferedImage getSprite(int spriteId){
        return game.getTileManager().getSprite(spriteId);
    }

    private BufferedImage getSprite(int spriteId,int animationIndex){
        return game.getTileManager().getAnimatedSprite(spriteId,animationIndex);
    }


    private void drawSelectedTile(Graphics g) {
        if(selectedTile !=null && drawSelect) {
            g.drawImage(selectedTile.getSprite(),mouseX,mouseY,32,32,null);
        }
    }
    public void saveLevel(){
        LoadSave.SaveLevel("new_level",lvl);
        game.getPlaying().setLevel(lvl);
    }

    public void setSelectedTile(Tile tile){
        this.selectedTile=tile;
        drawSelect =true;
    }
    private void changeTile(int x, int y) {
        if (selectedTile !=null) {

            int tileX = x / 32;
            int tileY = y / 32;

            //so we don't call this method everytime the mouse moves a pixle
            if(lastTileX == tileX && lastTileY==tileY && lastTileId == selectedTile.getId())
                return;
            lastTileX= tileX;
            lastTileY=tileY;

            lvl[tileY][tileX] = selectedTile.getId();

        }
    }


    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 640) {
            toolBar.mouseClicked(x,y);
        }else{
            changeTile(mouseX,mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y >= 640) {
            toolBar.mouseMoved(x,y);
            drawSelect=false;
        }else{
            drawSelect=true;
            mouseX=(x/32)*32;
            mouseY=(y/32)*32;

        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(y >= 640) {
            toolBar.mousePressed(x,y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        toolBar.mouseReleased(x,y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {

        } else {
            changeTile(x, y);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_R){
            toolBar.rotateSprite();;
        }
    }

}
