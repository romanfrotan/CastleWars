package main;

import inputs.KeyBoardListener;
import inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private Game game;
    private Dimension size;

    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;

    public GameScreen(Game game) {

        this.game=game;
        setPanelSize();

    }
    public void initInputs() {
        myMouseListener= new MyMouseListener(game);
        keyBoardListener= new KeyBoardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyBoardListener);

        requestFocus();
    }


    private void setPanelSize() {
        size = new Dimension(640,740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);
    }
}
