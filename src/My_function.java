import java.util.Random;

public class My_function {
    public static void my_function(Player player) {
        while (true) {
        /*
         * You can write your code between "Edit your code".
         * If your function run out, the program will not be stop.
         * It will run over and over again.
         * So, it's time to show your creativity!
         * */
        /* Edit your code */
//            for (int i = 0; i < 16; i++) {
//                player.go_right();
//            }
//            player.go_down();
//            player.fire();
//
//            for (int i = 0; i < 16; i++) {
//                player.go_left();
//            }
//            player.go_up();
//            player.fire();
            for (int i = 0; i < 8; i++) player.go_right();
            while (true) {
                Random random = new Random();
                int x = random.nextInt(5);
                switch (x) {
                    case 0: {
                        player.go_up();
                        break;
                    }
                    case 1: {
                        player.go_down();
                        break;
                    }
                    case 2: {
                        player.go_right();
                        break;
                    }
                    case 3: {
                        player.go_left();
                        break;
                    }
                    case 4: {
                        player.fire();
                        break;
                    }
                }
            }

        /* Edit your code end*/
        }
    }
}
