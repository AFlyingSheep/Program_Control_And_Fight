import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.concurrent.Semaphore;
import java.awt.event.*;
import java.applet.*;

import static sun.applet.AppletResourceLoader.getImage;

public class Global {

    // ******************* variable about game result *******************
    static boolean game_is_over;

    // index:
    // 0: player1 beats player2
    // 1: player2 beats player1
    // 2: player1 area larger than player2
    // 3: player2 area larger than player1
    // 4: player2 area equals player1
    static int game_result;

    static final String[] game_result_text = {
            "Player1 beats Player2, Player1 Win!" ,
            "Player2 beats Player1, Player2 Win!" ,
            "Player1's area larger than Player2, Player1 Win!" ,
            "Player2's area larger than Player1, Player2 Win!" ,
            "Draw!!!"
    };

    // ******************* variable about game resource *******************
    static Image player1_image;
    static Image player2_image;
    static Image[] blot_image;

    static int draw_point;

    static int[][] player1_map;             // index: x, y
    static int[][] player2_map;             // index: x, y
    static int[][] player1_fireMap; // index: no, direction(up, down, left, right), x, y, valid
    static int[][] player2_fireMap; // index: no, direction(up, down, left, right), x, y, valid

    static int player1_fireMap_pointer;
    static int player2_fireMap_pointer;

    static final int[][] dv = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    //  ******************* variable about game synchronization *******************
    static Semaphore player1;
    static Semaphore player2;

    static Semaphore read_data;
    static Semaphore read_data1;
    static Semaphore read_data2;

    static Semaphore over_player_update1;
    static Semaphore over_player_update2;

    // ******************* variable about game setting *******************
    // must be setting in advance

    static final int MAP_MAX_X = 16;
    static final int MAP_MAX_Y = 16;

    static final int BULLET_SPEED = 3;
    static final int GAME_SPEED = 30;
    static final int MAX_ROUND = 256;

    static final int GAME_CELL_SIZE = 50;

    // ******************* information about game *******************
    static int player1_last_x;
    static int player1_last_y;

    static int player2_last_x;
    static int player2_last_y;

    static int player1_now_x;
    static int player1_now_y;

    static int player2_now_x;
    static int player2_now_y;

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

        player1_map[0][0] = 1;
        player2_map[0][0] = 1;

        player1_fireMap_pointer = 0;
        player2_fireMap_pointer = 0;

        player1_last_x = 0;
        player1_last_y = 0;
        player2_last_x = 0;
        player2_last_y = 0;

        player1_now_x = 0;
        player1_now_y = 0;
        player2_now_x = 0;
        player2_now_y = 0;

        game_is_over = false;
        game_result = 0;

        draw_point = 0;

        try {
            player1_image = ImageIO.read(new FileInputStream("src/image/player1.jpg"));
            player2_image = ImageIO.read(new FileInputStream("src/image/player2.jpg"));

            blot_image = new Image[10];

            for (int i = 1; i <= 10; i++) {
                blot_image[i - 1] = ImageIO.read(new FileInputStream("src/image/blot/" + i + ".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
