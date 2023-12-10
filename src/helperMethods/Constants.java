package helperMethods;


public class Constants {

    public static class Direction {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN= 3;

    }

    public static class Tiles {

        public static final int WATER_TILE=0;
        public static final int GRASS_TILE=1;
        public static final int ROAD_TILE=2;

    }

    public static class Enemies {

        public static final int ORC=0;
        public static final int BAT=1;
        public static final int KNIGHT=2;
        public static final int WOLF=3;

        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case KNIGHT:
                    return .30f;
                case BAT:
                    return .70f;
                case ORC:
                    return .50f;
                case  WOLF:
                    return .80f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case KNIGHT:
                    return 230;
                case BAT:
                    return 55;
                case ORC:
                    return 150;
                case  WOLF:
                    return 90;
            }
            return 0;
        }
        }

    public static class Towers {

        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static String getInfo(int towerType) {
            switch (towerType) {
                case ARCHER:
                    return "A fierce sharpshooter";
                case WIZARD:
                    return "A mystical Wizard";
                case CANNON:
                    return "A mighty Cannon!";
            }
            return "";
        }

        public static float getDamage(int towerType) {
            switch (towerType) {
                case ARCHER:
                    return 20.f;
                case WIZARD:
                    return 7.0f;
                case CANNON:
                    return 40.0f;
            }
            return 0;
        }

        public static float getRange(int towerType) {
            switch (towerType) {
                case ARCHER:
                    return 100;
                case WIZARD:
                    return 100;
                case CANNON:
                    return 100;
            }
            return 0;
        }

        public static float getCooldown(int towerType) {
            switch (towerType) {
                case ARCHER:
                    return 10;
                case WIZARD:
                    return 10;
                case CANNON:
                    return 10;
            }
            return 0;
        }

    }

}
