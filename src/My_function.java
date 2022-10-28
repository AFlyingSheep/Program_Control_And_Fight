import java.util.Random;

public class My_function {
    public static void my_function(Player player) {
        while (true) {
        /*
         * You can write your code between "Edit your code".
         * If your function run out, the program will not be stop.
         * It will run over and over again.
         * So, it's time to show your creativity!
         * 横向为X，纵向为Y
         * */
        /* Edit your code */
            for (int i = 0; i < player.MAP_MAX_X / 2; i++) player.go_right();
            while (true) {
                if (player.get_local_x() < player.get_enemy_X()) {
                    player.go_left();
                    player.go_down();
                }
                else {
                    player.go_right();
                    player.go_down();
                }
            }


        /* Edit your code end*/
        }
    }
}
