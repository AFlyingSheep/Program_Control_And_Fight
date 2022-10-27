public class hello {
    public static void main(String[] args) {
        try {
            Playgame playgame = new Playgame();
            Global.replay();
            playgame.start();





        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
