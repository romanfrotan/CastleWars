package inputs;

import main.Game;
import main.GameScreen;
import main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

public class KeyBoardListener implements KeyListener {

    private Game game;

    public KeyBoardListener(Game game){
        this.game=game;

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameStates.gameState==EDIT) {
            game.getEditor().keyPressed(e);
        }
  }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
