package ui;

import helperMethods.Constants;
import objects.Friendly;
import scenes.Playing;
import java.awt.*;
import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar {

    private MyButton[] friendButtons;
    private MyButton bMenu;
    private Playing playing;
    private Friendly selectedFriendly;
    private Friendly displayedFriendly;



    public ActionBar(int x, int y, int width, int height, Playing playing) {
       super(x,y,width,height);
        this.playing=playing;
        initButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(94, 54, 126));
        g.fillRect(x,y,width,height);
        drawButtons(g);
        drawLives(g);
        drawDisplayedFriend(g);
    }

    private void drawLives(Graphics g) {
        int livesLeft=playing.getEnemyManager().getPlayerLives()-1;
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Lives Left: "+livesLeft,300,670);

    }

    private void drawDisplayedFriend(Graphics g) {
        if(displayedFriendly !=null){
            g.drawImage(playing.getFriendManager().getFriendImages()[displayedFriendly.getFriendType()],500,700,50,50,null);
            g.drawString(Constants.Friends.getInfo(displayedFriendly.getFriendType()),490,670);
            drawDisplayedBorder(g);
            drawDisplayedFriendRange(g);
            

        }
    }

    private void drawDisplayedFriendRange(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(displayedFriendly.getX() + 16 - (int) (displayedFriendly.getRange() * 2) / 2, displayedFriendly.getY() + 16 - (int) (displayedFriendly.getRange() * 2) / 2, (int) displayedFriendly.getRange() * 2,
                (int) displayedFriendly.getRange() * 2);
    }

    private void drawDisplayedBorder(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(displayedFriendly.getX(), displayedFriendly.getY(),32,32);
    }

    public void initButtons() {
        bMenu=new  MyButton("Menu",2,642,100,30);
        friendButtons = new MyButton[3];
        //same layout as Toolblar in editng scene
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int offSet = (int) (w * 1.1f);

        for (int i = 0; i< friendButtons.length; i++) {
            friendButtons[i]=new MyButton("",xStart+offSet*i,yStart,w,h,i);
        }


    }
    private void drawButtons(Graphics g) {
        bMenu.draw(g);

        //use same i form initButtons as id.
        for(MyButton b: friendButtons) {
            //makes it match with action bar background giving a outline effect
            g.setColor(new Color(94, 54, 126));
            g.fillRect(b.x,b.y,b.width,b.height);

           g.drawImage(playing.getFriendManager().getFriendImages()[b.getId()],b.x,b.y,b.width,b.height,null);
           drawButtonFeedback(g,b);
        }

    }
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else {
            for (MyButton b: friendButtons){
                if(b.getBounds().contains(x,y)){
                    selectedFriendly =new Friendly(0,0,-1,b.getId());

                    //send selected friend to playing
                    playing.setSelectedFriend(selectedFriendly);
                    return;
                }

            }
        }

    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for (MyButton b : friendButtons)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else {
            for (MyButton b : friendButtons)
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
            for (MyButton b : friendButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }

    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        for (MyButton b : friendButtons)
            b.resetBooleans();
    }

    public void displayFriend(Friendly t) {
        displayedFriendly =t;



    }
}