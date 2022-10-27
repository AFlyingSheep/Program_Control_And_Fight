import java.util.concurrent.Semaphore;

public class Player extends Thread{
    private int local_x;
    private int local_y;
    private boolean isLive;

    // enemy's map!
    private int[][] enemy_map;

    // fireMap
    private int[][] my_fire_map;
    private int my_fire_point;

    int choose;

    // direction
    private int direction; // 0: up 1: down 2: left 3:right

    private Semaphore semaphore;
    private Semaphore over_player_update;

    Player(int choose)
    {
        local_x = 0;
        local_y = 0;
        isLive = true;
        this.choose = choose;
        semaphore = (choose == 1 ? Global.player1 : Global.player2);
        enemy_map = (choose == 2 ? Global.player1_map : Global.player2_map);
        over_player_update = (choose == 1 ? Global.over_player_update1 : Global.over_player_update2);
        direction = 1;
    }

    // 控制与操作
    public boolean go_right()
    {
        get_Sem();
        update_last();
        if (local_x < Global.MAP_MAX_Y - 1
                && enemy_map[Global.MAP_MAX_X - local_x - 1][Global.MAP_MAX_Y - local_y - 1] == 0)
        {
            local_x++;
            direction = 3;
            release_Sem();
//            System.out.println("right");
            return true;
        }
        else {
            release_Sem();
            return false;
        }
    }

    public boolean go_left()
    {
        get_Sem();
        update_last();
        if (local_x > 0 &&
                enemy_map[Global.MAP_MAX_X - local_x - 1][Global.MAP_MAX_Y - local_y - 1] == 0)
        {
            local_x--;
            direction = 2;
            release_Sem();
//            System.out.println("left");
            return true;
        }
        else {
            release_Sem();
            return false;
        }
    }

    public boolean go_down()
    {
        get_Sem();
        update_last();
        if (local_y < Global.MAP_MAX_X - 1
                && enemy_map[Global.MAP_MAX_X - local_x - 1][Global.MAP_MAX_Y - local_y - 1] == 0)
        {
            local_y++;
            direction = 1;
            release_Sem();
//            System.out.println("down");
            return true;
        }
        else {
            release_Sem();
            return false;
        }
    }

    public boolean go_up()
    {
        get_Sem();
        update_last();
        if (local_y > 0
                && enemy_map[Global.MAP_MAX_X - local_x - 1][Global.MAP_MAX_Y - local_y - 1] == 0)
        {
            local_y--;
            direction = 0;
            release_Sem();
//            System.out.println("up");
            return true;
        }
        else {
            release_Sem();
            return false;
        }
    }

    public boolean fire()
    {
        get_Sem();
//        System.out.println("Fire now!");
        my_fire_map = (choose == 1 ? Global.player1_fireMap : Global.player2_fireMap);
        my_fire_point = (choose == 1? Global.player1_fireMap_pointer : Global.player2_fireMap_pointer);

        my_fire_map[my_fire_point][0] = 0;
        my_fire_map[my_fire_point][1] = direction;
        my_fire_map[my_fire_point][2] = local_x;
        my_fire_map[my_fire_point][3] = local_y;
        my_fire_map[my_fire_point][4] = 1;

        if (choose == 1) Global.player1_fireMap_pointer += 1;
        else Global.player2_fireMap_pointer += 1;

        release_Sem();

        return true;
    }

    public int get_enemy_X() {
        return (choose == 1 ? Global.player2_last_x : Global.player1_last_x);
    }

    public int get_enemy_Y() {
        return (choose == 1 ? Global.player2_last_y : Global.player1_last_y);
    }

    public void stop_state() {
        get_Sem();
        release_Sem();
    }

    // 状态查看
    public boolean is_Live()
    {
        return isLive;
    }

    public int get_local_x()
    {
        return local_x;
    }

    public int get_local_y()
    {
        return local_y;
    }

    public void get_Sem() {
        try {
            semaphore.acquire();
//            Global.read_data.acquire();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void release_Sem() {
        over_player_update.release();
        Global.read_data.release();
//        Global.read_data1.release();
    }

    public void kill() {
        this.isLive = false;
    }

    public void update_last() {
        if (choose == 1) {
            Global.player1_last_x = this.local_x;
            Global.player1_last_y = this.local_y;
        }
        else {
            Global.player2_last_x = this.local_x;
            Global.player2_last_y = this.local_y;
        }
    }

    @Override
    public void run() {
        while (true) {
            // stop_state();
            switch (choose) {
                case 1: {
                    My_function.my_function(this);
                    break;
                }
                case 2: {
                    Enemy_function.my_function(this);
                    break;
                }
            }
        }
    }
}
