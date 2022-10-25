import java.util.concurrent.Semaphore;

public class Global {

    static Semaphore player1;
    static Semaphore player2;

    static int[][] player1_map;             // index: x, y
    static int[][] player2_map;             // index: x, y
    static int[][] player1_fireMap; // index: no, direction(up, down, left, right), x, y, valid
    static int[][] player2_fireMap; // index: no, direction(up, down, left, right), x, y, valid

    static int player1_fireMap_pointer;
    static int player2_fireMap_pointer;

    static final int MAP_MAX_X = 16;
    static final int MAP_MAX_Y = 16;

    static final int BULLET_SPEED = 3;

    static final int[][] dv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    Global() {
        replay();
    }

    static void replay() {
        player1 = new Semaphore(1, true);
        player2 = new Semaphore(1, true);

        player1_map = new int[MAP_MAX_X][MAP_MAX_Y];
        player2_map = new int[MAP_MAX_X][MAP_MAX_Y];

        for (int i = 0; i < MAP_MAX_X; i++)
            for (int j = 0; j < MAP_MAX_Y; j++) {
                player1_map[i][j] = 0;
                player2_map[i][j] = 0;
            }

        player1_fireMap = null;
        player2_fireMap = null;

        player1_fireMap_pointer = 0;
        player2_fireMap_pointer = 0;


    }
}
