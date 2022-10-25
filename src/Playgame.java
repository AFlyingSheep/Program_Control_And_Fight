public class Playgame {
    public void play_now() {

        Player player = new Player(1);
        Player enemy = new Player(2);

        int round = 0;
        int repo = 0;

        player.start();
        enemy.start();

        while (true) {
            if (round > 256 || !player.is_Live() || !enemy.is_Live()) break;
            if (repo == 0) {
                // 执行player更新
                Global.player1.release();
                Global.player2.release();

                update_map(player, enemy);
                round++;
            }

            // 执行弹体更新
            update_fire();
            change_live(player, enemy);
            repo = (repo + 1) % Global.BULLET_SPEED;

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public void update_map(Player player1, Player player2) {
//        static int[][] map;             // index: x, y
        Global.player1_map[player1.get_local_x()][player1.get_local_y()] = 1;
        Global.player2_map[player1.get_local_x()][player1.get_local_y()] = 1;
    }
    public void update_fire() {
//        static int[][] player1_fireMap; // index: no, direction, x, y, valid
//        static int[][] player2_fireMap; // index: no, direction, x, y, valid

        // update fire by direction
        for (int i = 0; i < Global.player1_fireMap_pointer; i++) {
            Global.player1_fireMap[i][2] += Global.dv[Global.player1_fireMap[i][1]][0];
            Global.player1_fireMap[i][3] += Global.dv[Global.player1_fireMap[i][1]][1];

            // valid is 0 if bullet out of index
            if (Global.player1_fireMap[i][2] >= Global.MAP_MAX_X
                    || Global.player1_fireMap[i][3] >= Global.MAP_MAX_Y
                    || Global.player1_fireMap[i][2] < 0
                    || Global.player1_fireMap[i][3] < 0)
                Global.player1_fireMap[i][4] = 0;

        }

        for (int i = 0; i < Global.player2_fireMap_pointer; i++) {
            Global.player2_fireMap[i][2] += Global.dv[Global.player2_fireMap[i][1]][0];
            Global.player2_fireMap[i][3] += Global.dv[Global.player2_fireMap[i][1]][1];

            // valid is 0 if bullet out of index
            if (Global.player2_fireMap[i][2] >= Global.MAP_MAX_X
                    || Global.player2_fireMap[i][3] >= Global.MAP_MAX_Y
                    || Global.player2_fireMap[i][2] < 0
                    || Global.player2_fireMap[i][3] < 0)
                Global.player2_fireMap[i][4] = 0;
        }
    }

    public void change_live(Player player1, Player player2) {
        // check player1 islive
        // if role's x and role's y are equal with firemap, kill now.
        for (int i = 0; i < Global.player2_fireMap_pointer; i++) {
            if (player1.get_local_x() == Global.player2_fireMap[i][2]
                    && player1.get_local_y() == Global.player2_fireMap[i][3]) {
                player1.kill();
            }
        }
        for (int i = 0; i < Global.player1_fireMap_pointer; i++) {
            if (player2.get_local_x() == Global.player2_fireMap[i][2]
                    && player2.get_local_y() == Global.player2_fireMap[i][3]) {
                player2.kill();
            }
        }
    }
}
