package main;

public enum GameStates {

    PLAYING,
    MENU,
    EDIT;



    public static GameStates gameState = MENU;

    public static void SetGameState(GameStates state){
        gameState = state;
    }
}
