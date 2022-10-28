import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Tue Oct 25 22:28:22 CST 2022
 */



/**
 * @author 1
 */
public class Main_game_interface extends JFrame {
    public My_panel my_panel;
    public Main_game_interface() {
        initComponents();
        my_panel = new My_panel();
        getContentPane().add(my_panel);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        progressBar1 = new JProgressBar();

        //======== this ========
        setMinimumSize(new Dimension(320, 320));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);
            panel1.add(progressBar1);
            progressBar1.setBounds(0, 320, 320, 20);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 320, 320);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JProgressBar progressBar1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables



}

class My_panel extends JPanel {
    My_panel() {
        this.setLayout(null);
        this.setBounds(0, 0, 320, 320);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);



        // update player1
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                if (i == Global.player1_now_x && j == Global.player1_now_y &&
                        !(Global.game_is_over && (Global.game_result == 1 || Global.game_result == 3))) {
                    g.drawImage(Global.player1_image,
                            i * 20, j * 20,i * 20 + 20, j * 20 + 20,
                            0, 0,
                            Global.player1_image.getWidth(null),Global.player1_image.getHeight(null),
                            null);
                }

                else if (Global.player1_map[i][j] == 1) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
                else {
                    g.setColor(Color.WHITE);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
            }
        }

        // update player2
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == Global.player2_now_x && j == Global.player2_now_y &&
                        !(Global.game_is_over && (Global.game_result == 0 || Global.game_result == 2))) {
                    g.drawImage(Global.player2_image,
                            (15 - i) * 20, (15 - j) * 20,(15 - i) * 20 + 20, (15 - j) * 20 + 20,
                            0, 0,
                            Global.player2_image.getWidth(null),Global.player2_image.getHeight(null),
                            null);
                }
                else if (Global.player2_map[i][j] == 1) {
                    g.setColor(Color.green);
                    g.fillRect((15 - i) * 20, (15 - j) * 20, 20, 20);
                }
//                else continue;

            }
        }

        // update bullet
        g.setColor(Color.red);
        for (int i = 0; i < Global.player1_fireMap_pointer; i++) {
            if (Global.player1_fireMap[i][4] == 1) {
                g.fillOval(Global.player1_fireMap[i][2] * 20 + 7,
                        Global.player1_fireMap[i][3] * 20 + 7,
                        5, 5);
            }
        }
        g.setColor(Color.blue);
        for (int i = 0; i < Global.player2_fireMap_pointer; i++) {
            if (Global.player2_fireMap[i][4] == 1) {
                g.fillOval((15 - Global.player2_fireMap[i][2]) * 20 + 7,
                        (15 - Global.player2_fireMap[i][3]) * 20 + 7,
                        5, 5);
            }
        }

        // 爆炸特效
        int x = (Global.game_result == 0 || Global.game_result == 2) ? (15 - Global.player2_now_x) : Global.player1_now_x;
        int y = (Global.game_result == 0 || Global.game_result == 2) ? (15 - Global.player2_now_y) : Global.player1_now_y;
        if (Global.game_is_over && Global.draw_point < 10) {
            g.drawImage(Global.blot_image[Global.draw_point],
                    x * 20 - 5, y * 20 - 5,
                    x * 20 + 25, y * 20 + 25,
                    0, 0,
                    Global.blot_image[Global.draw_point].getWidth(null),
                    Global.blot_image[Global.draw_point].getHeight(null),
                    null);
            Global.draw_point++;
        }

        if (Global.game_is_over) {
            g.setColor(Color.black);
            g.drawString(Global.game_result_text[Global.game_result], 60, 160);
        }

    }
}