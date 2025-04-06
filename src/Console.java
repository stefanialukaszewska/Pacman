import javax.swing.*;
import java.awt.*;


public class Console extends JPanel implements Runnable {
    private static final int x_screen = GameFrame.x_screen;
    public static final int y_screen = 70;
    private Color textColor = Color.WHITE;
    JLabel timerText;
    JLabel scoreText;
    JLabel lifesText;
    myTimer timer;
    public static int lifes = 3;
    public static boolean isGameRunning = true;

    public Console() {

        setBackground(Color.BLACK);

        setVisible(true);
        setPreferredSize(new Dimension(x_screen, y_screen));
        setBounds(0, GameFrame.y_screen, x_screen, y_screen);

        setLayout(new GridLayout(1, 3));


        scoreText = new JLabel();
        scoreText.setForeground(textColor);
        scoreText.setText("SCORE: ");
        scoreText.setHorizontalAlignment(SwingConstants.CENTER);
        scoreText.setFont(new Font("Monospaced", Font.BOLD, 15));
        add(scoreText);

        this.timerText = new JLabel();
        timerText.setForeground(textColor);
        timerText.setHorizontalAlignment(SwingConstants.CENTER);
        timerText.setText("TIMER: ");
        timerText.setFont(new Font("Monospaced", Font.BOLD, 15));
        add(timerText);

        this.lifesText = new JLabel();
        lifesText.setHorizontalAlignment(SwingConstants.CENTER);
        lifesText.setForeground(textColor);
        lifesText.setText("LIFES : ");
        lifesText.setFont(new Font("Monospaced", Font.BOLD, 15));
        add(lifesText);

        this.timer = new myTimer();
        Thread timerThread = new Thread(timer);
        timerThread.start();

        Thread timeUpdate = new Thread(this::timeRun);
        timeUpdate.start();
        Thread scoreUpdate = new Thread(this::scoreRun);
        scoreUpdate.start();
        Thread lifeUpdate = new Thread(this::lifesRun);
        lifeUpdate.start();

        if (lifes < 0) isGameRunning = false;

    }

    public void updateTime() {
        timerText.setText("TIMER: " + timer.getSeconds());
    }

    public void updateScore() {
        scoreText.setText("SCORE: " + PointGrid.score);
    }

    public void updateLifes() {
        lifesText.setText("LIFES : " + lifes);
    }


    private void timeRun() {
        while (true) {
            updateTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void scoreRun() {
        while (true) {
            updateScore();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void lifesRun() {
        while (true) {
            updateLifes();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void run() {

    }
}
