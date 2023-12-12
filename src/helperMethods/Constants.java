package helperMethods;


public class Constants {

    public static class Direction {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class Tiles {

        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;

    }

    public static class Enemies {


        public static final int GHOST = 0;
        public static final int KNIGHT = 1;
        public static final int GOBLIN = 2;
        public static final int SKELETON = 3;

        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case KNIGHT:
                    return .40f;
                case GHOST:
                    return .65f;
                case SKELETON:
                    return .50f;
                case GOBLIN:
                    return .61f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case KNIGHT:
                    return 300;
                case GHOST:
                    return 120;
                case SKELETON:
                    return 245;
                case GOBLIN:
                    return 230;
            }
            return 0;
        }
    }

    public static class Friends {

        public static final int ROUGE = 1;
        public static final int ARCHER = 2;
        public static final int MAGE = 0;

        public static String getInfo(int friend) {
            switch (friend) {
                case ARCHER:
                    return "Archer";
                case MAGE:
                    return "Mage";
                case ROUGE:
                    return "Rouge";
            }
            return "";
        }

        public static int getDamage(int friend) {
            switch (friend) {
                case ARCHER:
                    return 12;
                case MAGE:
                    return 24;
                case ROUGE:
                    return 15;
            }
            return 0;
        }

        public static float getRange(int friend) {
            switch (friend) {
                case ARCHER:
                    return 80;
                case MAGE:
                    return 120;
                case ROUGE:
                    return 100;
            }
            return 0;
        }

        public static float getCooldown(int friend) {
            switch (friend) {
                case ARCHER:
                    return 13;
                case MAGE:
                    return 28;
                case ROUGE:
                    return 18;
            }
            return 0;
        }

    }

    public static class Projectiles {
        public static final int BOOMERANG = 0;
        public static final int ARROW = 1;
        public static final int SPELL = 2;



        public static float getSpeed(int type) {

            switch (type) {
                case SPELL:
                    return 2f;
                case ARROW:
                    return 3f;
                case BOOMERANG:
                    return 1f;
            }
            return 0f;
        }

    }


}
