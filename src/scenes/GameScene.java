package scenes;

import main.Game;

import java.awt.image.BufferedImage;

public class GameScene {
    protected Game game;
    protected int animationSpeed=15;
    protected int tick;
    protected int animationIndex;

    public GameScene(Game game){
        this.game=game;
    }

    public Game getGame() {
        return game;
    }


    protected void updateTick() {
        tick++;

        if(tick>=animationSpeed){
            tick=0;
            animationIndex++;
            if(animationIndex>=4){
                animationIndex=0;
            }
        }
    }
    protected BufferedImage getSprite(int spriteId){
        return game.getTileManager().getSprite(spriteId);
    }

    protected BufferedImage getSprite(int spriteId,int animationIndex){
        return game.getTileManager().getAnimatedSprite(spriteId,animationIndex);
    }


    protected boolean isAnimated(int id) {
        return game.getTileManager().isSpriteAnimated(id);
    }


}
