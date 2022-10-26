import java.util.concurrent.Semaphore;

public class Global {

//    static Semaphore semaphore_public;

    static Semaphore player1;
    static Semaphore player2;

    static Semaphore read_data;
    static Semaphore read_data1;
    static Semaphore read_data2;

    static Semaphore over_player_update1;
    static Semaphore over_player_update2;

    static int[][] player1_map;             // index: x, y
    static int[][] player2_map;             // index: x, y
    static int[][] player1_fireMap; // index: no, direction(up, down, left, right), x, y, valid
    static int[][] player2_fireMap; // index: no, direction(up, down, left, right), x, y, valid

    static int player1_fireMap_pointer;
    static int player2_fireMap_pointer;

    static final int MAP_MAX_X = 16;
    static final int MAP_MAX_Y = 16;

    static int player1_last_x;
    static int player1_last_y;

    static int player2_last_x;
    static int player2_last_y;

    static final int BULLET_SPEED = 3;

    static final int MAX_ROUND = 256;

    static final int[][] dv = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    Global() {
        replay();
    }

    static void replay() {
        player1 = new Semaphore(0, true);
        player2 = new Semaphore(0, true);
//        semaphore_public = new Semaphore(1, false);

        read_data1 = new Semaphore(0);
        read_data2 = new Semaphore(2);

        read_data = new Semaphore(0);

        over_player_update1 = new Semaphore(0, true);
        over_player_update2 = new Semaphore(0, true);

        player1_map = new int[MAP_MAX_X][MAP_MAX_Y];
        player2_map = new int[MAP_MAX_X][MAP_MAX_Y];

        player1_fireMap = new int[MAX_ROUND + 10][5];
        player2_fireMap = new int[MAX_ROUND + 10][5];

        for (int i = 0; i < MAP_MAX_X; i++)
            for (int j = 0; j < MAP_MAX_Y; j++) {
                player1_map[i][j] = 0;
                player2_map[i][j] = 0;
            }

        player1_fireMap_pointer = 0;
        player2_fireMap_pointer = 0;

        player1_last_x = 0;
        player1_last_y = 0;
        player2_last_x = 0;
        player2_last_y = 0;

    }
}
