package managers;

import events.Wave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WaveManager {

    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit =60*1;
    private int enemySpawnTick=enemySpawnTickLimit;
    private int enemyIndex,waveIndex;
    private int waveTickLimit=60*1;
    private int waveTick=0;
    private boolean waveStartTimer;
    private boolean waveTickTimerOver;





    public WaveManager(Playing playing) {
        this.playing=playing;
        createWaves();

    }

public void createWaves() {

    Random rand = new Random();

    int lives=playing.getEnemyManager().getPlayerLives();

        if(lives>0) {
            for (int i = 0; i < 95; i++) {

                int r1 = rand.nextInt(3);
                int r2 = rand.nextInt(3);
                int r3 = rand.nextInt(3);
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,1,2,3))));

            }
        }

}

public ArrayList<Wave> getWaves () {
        return waves;
}

    public boolean isTimeForNewEnemy() {
            return enemySpawnTick >= enemySpawnTickLimit;
    }
public void update() {
        if(enemySpawnTick<enemySpawnTickLimit) {
            enemySpawnTick++;
        }
        if (waveStartTimer){
            waveTick++;
            if(waveTick>=waveTickLimit) {
                waveTickTimerOver=true;
            }

        }
}
public void increaseWaveIndex(){
        waveIndex++;
        waveTickTimerOver=false;
        waveStartTimer=false;
}
public int getNextEnemy () {
        enemySpawnTick=0;
   return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
}
public boolean isMoreEnemies() {
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
}

    public boolean isMoreWaves() {
        return waveIndex +1 < waves.size();
    }

    public void startTimer() {
        waveStartTimer=true;
    }

    public boolean isWaveTimerOver() {
        return waveTickTimerOver;
    }

    public void resetEnemyIndex() {
        enemyIndex=0;
    }
    public void setWaves (){
        waves.clear();
    }
}
