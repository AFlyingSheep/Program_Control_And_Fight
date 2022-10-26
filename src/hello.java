public class hello {
    public static void main(String[] args) {
        try {
            Playgame playgame = new Playgame();
            Global.replay();
            playgame.play_now();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
