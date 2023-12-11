package scenes;

import main.Game;
import ui.MyButton;

import java.awt.*;

import static java.awt.SystemColor.menu;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{

    private MyButton bPlaying,bEdit,bQuit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }


    private void initButtons() {

        int w=150;
        int h=w/3;
        int x=250;
        int y=150;
        int yOffset =100;

        bPlaying=new MyButton("Let's Play!",x,y,w,h);
        bEdit=new MyButton("Level Editor",x,y+yOffset,w,h);
        bQuit=new MyButton("Quit",x,y+yOffset*3,w,h);

    }

    @Override
    public void render(Graphics g) {

        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bPlaying.getBounds().contains(x,y)){
            SetGameState(PLAYING);
        }else if (bEdit.getBounds().contains(x,y)){
            SetGameState(EDIT);
        }else if (bQuit.getBounds().contains(x,y)){
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bQuit.setMouseOver(false);
        if(bPlaying.getBounds().contains(x,y)){
            bPlaying.setMouseOver(true);
        }else if(bEdit.getBounds().contains(x,y)){
            bEdit.setMouseOver(true);
        }else if(bQuit.getBounds().contains(x,y)){
            bQuit.setMouseOver(true);
        }
    }


    @Override
    public void mousePressed(int x, int y) {
        if(bPlaying.getBounds().contains(x,y)){
            bPlaying.setMousePressed(true);
        }else if(bEdit.getBounds().contains(x,y)) {
            bEdit.setMousePressed(true);
        }else if(bQuit.getBounds().contains(x,y)) {
            bQuit.setMousePressed(true);
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        bEdit.resetBooleans();
        bPlaying.resetBooleans();
        bQuit.resetBooleans();


    }

    @Override
    public void mouseDragged(int x, int y) {

    }
    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bQuit.draw(g);
    }

}
