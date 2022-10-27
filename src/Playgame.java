public class Playgame extends Thread{

    @Override
    public void run(){
        Player player = new Player(1);
        Player enemy = new Player(2);

        int round = 0;
        int repo = 0;

        player.start();
        enemy.start();

        try {
            while (!(round > 256 || !player.is_Live() || !enemy.is_Live())) {
                if (repo % Global.BULLET_SPEED == 0) {
                    // run an instruction
                    Global.player1.release();
                    Global.player2.release();

                    Global.over_player_update1.acquire(1);
                    Global.over_player_update2.acquire(1);

                    update_map(player, enemy);
                    round++;

                    int p1_x = player.get_local_x(), p1_y = player.get_local_y(), p2_x = enemy.get_local_x(), p2_y = enemy.get_local_y();

                    Global.read_data.acquire(2);

                    // execute read data

                    System.out.println("[round="+ round + "], last_location is "
                            + Global.player1_last_x + "-" + Global.player1_last_y
                            + " , alive = " + player.is_Live()
                    );
                    System.out.println("[round="+ round + "], location is "
                            + p1_x + "-" + p1_y
                            + " , alive = " + player.is_Live()
                    );



//                System.out.println("[round="+ round + "], location is "
//                        + p2_x + "-" + p2_y
//                        + " , alive = " + enemy.is_Live()
//                );
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // execute read data end
                }
                // 执行弹体更新
                update_fire();
                change_live(player, enemy);
                repo = (repo + 1) % Global.BULLET_SPEED;

                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            player.stop();
            enemy.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (player.is_Live() ^ enemy.is_Live()) {
            if (player.is_Live()) winner(player);
            else winner(enemy);
        }
        else {
            int res = calculate_area();
            switch (res) {
                case 1: {
                    winner(player);
                    break;
                }
                case -1: {
                    winner(enemy);
                    break;
                }
                case 0: {
                    System.out.println("Draw!");
                }
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
        // if role's x and role's y are equal with changed enemy's fire's map, kill now.
        for (int i = 0; i < Global.player2_fireMap_pointer; i++) {
            if (player1.get_local_x() == Global.MAP_MAX_X - Global.player2_fireMap[i][2] - 1
                    && player1.get_local_y() == Global.MAP_MAX_Y - Global.player2_fireMap[i][3] - 1) {
                player1.kill();
            }
        }
        for (int i = 0; i < Global.player1_fireMap_pointer; i++) {
            if (player2.get_local_x() == Global.MAP_MAX_X - Global.player1_fireMap[i][2] - 1
                    && player2.get_local_y() == Global.MAP_MAX_Y - Global.player1_fireMap[i][3] - 1) {
                player2.kill();
            }
        }
    }

    public int calculate_area() {
        int player1 = 0, player2 = 0;
        for (int i = 0; i < Global.MAP_MAX_X; i++) {
            for (int j = 0; j < Global.MAP_MAX_Y; j++) {
                if (Global.player1_map[i][j] == 1) player1++;
                if (Global.player2_map[i][j] == 1) player2++;
            }
        }
        if (player1 > player2) return 1;
        else if (player1 == player2) return 0;
        else return -1;
    }

    public void winner(Player player) {
        System.out.println("Winner is: Player" + player.choose + "!");
    }
}
