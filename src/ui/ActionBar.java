package ui;

import helperMethods.Constants;
import objects.Tower;
import scenes.Playing;
import java.awt.*;
import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar {

    private MyButton[] towerButtons;
    private MyButton bMenu;
    private Playing playing;
    private Tower selectedTower;
    private Tower displayedTower;



    public ActionBar(int x, int y, int width, int height, Playing playing) {
       super(x,y,width,height);
        this.playing=playing;
        initButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(94, 54, 126));
        g.fillRect(x,y,width,height);
        drawButtons(g);

        drawDisplayedTower(g);
    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower !=null){
            g.drawImage(playing.getTowerManager().getTowerImages()[displayedTower.getTowerType()],500,700,50,50,null);
            g.drawString(Constants.Towers.getInfo(displayedTower.getTowerType()),490,670);
            drawDisplayedBorder(g);
            drawDisplayedTowerRange(g);
            

        }
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(displayedTower.getX()+16-(int)displayedTower.getRange()/2,displayedTower.getY()+16-(int)displayedTower.getRange()/2,(int)displayedTower.getRange(),(int)displayedTower.getRange());
    }

    private void drawDisplayedBorder(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(displayedTower.getX(),displayedTower.getY(),32,32);
    }

    public void initButtons() {
        bMenu=new  MyButton("Menu",2,642,100,30);
        towerButtons = new MyButton[3];
        //same layout as Toolblar in editng scene
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int offSet = (int) (w * 1.1f);

        for (int i=0;i<towerButtons.length;i++) {
            towerButtons[i]=new MyButton("",xStart+offSet*i,yStart,w,h,i);
        }


    }
    private void drawButtons(Graphics g) {
        bMenu.draw(g);

        //use same i form initButtons as id.
        for(MyButton b: towerButtons) {
            //makes it match with action bar background giving a outline effect
            g.setColor(new Color(94, 54, 126));
            g.fillRect(b.x,b.y,b.width,b.height);

           g.drawImage(playing.getTowerManager().getTowerImages()[b.getId()],b.x,b.y,b.width,b.height,null);
           drawButtonFeedback(g,b);
        }

    }
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else {
            for (MyButton b:towerButtons){
                if(b.getBounds().contains(x,y)){
                    selectedTower=new Tower(0,0,-1,b.getId());

                    //send selected tower to playing
                    playing.setSelectedTower(selectedTower);
                    return;
                }

            }
        }

    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for (MyButton b : towerButtons)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else {
            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else
            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }

    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        for (MyButton b : towerButtons)
            b.resetBooleans();
    }

    public void displayTower(Tower t) {
        displayedTower=t;



    }
}