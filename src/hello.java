public class hello {
    public static void main(String[] args) {
        try {
            Global.replay();
            Playgame playgame = new Playgame();



//            while (Global.player1_image == null || Global.player2_image == null)
//                try {
//                    Thread.sleep(10);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            playgame.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
