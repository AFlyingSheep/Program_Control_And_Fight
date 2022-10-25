import java.util.concurrent.Semaphore;

public class Player extends Thread{
    private int local_x;
    private int local_y;
    private boolean isLive;

    // 对方的map！！
    private int[][] enemy_map;

    private int choose;

    Semaphore semaphore;

    Player(int choose)
    {
        local_x = 0;
        local_y = 0;
        isLive = true;
        this.choose = choose;
        semaphore = (choose == 1 ? Global.player1 : Global.player2);
        enemy_map = (choose == 2? Global.player1_map : Global.player2_map);
    }

    // 控制与操作
    public boolean go_right()
    {
        if (local_x < Global.MAP_MAX_Y - 1 && enemy_map[local_x + 1][local_y] == 0)
        {
            local_x++;
            get_Sem();
            System.out.println("right");
            return true;
        }
        else return false;
    }

    public boolean go_left()
    {
        if (local_x > 0 && enemy_map[local_x - 1][local_y] == 0)
        {
            local_x--;
            get_Sem();
            System.out.println("left");
            return true;
        }
        else return false;
    }

    public boolean go_down()
    {
        if (local_y < Global.MAP_MAX_X - 1 && enemy_map[local_x][local_y + 1] == 0)
        {
            local_y++;
            get_Sem();
            System.out.println("down");
            return true;
        }
        else return false;
    }

    public boolean go_up()
    {
        if (local_y > 0 && enemy_map[local_x][local_y - 1] == 0)
        {
            local_y--;
            get_Sem();
            System.out.println("up");
            return true;
        }
        else return false;
    }

    public boolean fire()
    {
        get_Sem();

        return true;
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
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void kill() {
        this.isLive = false;
    }

    @Override
    public void run() {
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
