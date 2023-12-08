package main;

public enum GameStates {

    PLAYING,
    MENU,
    EDIT,
    SETTINGS;


    public static GameStates gameState = MENU;

    public static void SetGameState(GameStates state){
        gameState = state;
    }
}
