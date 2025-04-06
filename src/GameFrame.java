import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame implements Runnable {
    public static int UNIT_SIZE = 25;
    public static int x_screen = Maps.getWidth() * UNIT_SIZE;
    public static int y_screen = Maps.getHeight() * UNIT_SIZE;
    private Layers layers = new Layers();
    ;

    public static boolean isGameRunning = true;


    GameFrame() {
        setTitle("PACMAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        Thread isRunning = new Thread(this);
        isRunning.start();

        this.add(layers);


        pack();
        setVisible(true);

    }


    @Override
    public void run() {
        while (true) {
            if (Console.lifes < 1 || PointGrid.score == PointGrid.howManyPoints) {
                isGameRunning = false;
                this.dispose();

                String playername = getNameFromDialog();
                Score newHighScore = new Score(playername, PointGrid.score);
                ScoresManager.addScore(newHighScore);
                new GameOver();


                break;
            } else {
                layers.setVisible(true);
                this.getContentPane().setBackground(Color.BLACK);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getNameFromDialog() {
        return JOptionPane.showInputDialog(null, "Please enter your name:", "Name Input", JOptionPane.QUESTION_MESSAGE);
    }


}
