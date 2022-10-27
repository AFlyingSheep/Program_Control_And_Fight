public class hello {
    public static void main(String[] args) {
        try {
            Playgame playgame = new Playgame();
            Global.replay();
            playgame.start();

            Main_game_interface main_game_interface = new Main_game_interface();
            main_game_interface.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
