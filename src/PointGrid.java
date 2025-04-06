

import javax.swing.*;
import java.awt.*;

public class PointGrid extends JLabel implements Runnable {

    public static int[][] isPointHere;
    private static final int ROWS = Maps.getHeight();
    private static final int COLS = Maps.getWidth();
    private static final int UNIT_SIZE = 25;
    public static int score = 0;
    public static int howManyPoints = 0;

    PointGrid() {
        this.setLayout(null);
        setSize(new Dimension(GameFrame.x_screen, GameFrame.y_screen));
        setLocation(0, 0);

        Thread update = new Thread(this);
        update.start();
        getPoint();
    }

    private static boolean isPoint(int row, int col) {
        return Maps.Blocks[row][col] == 0;
    }

    private void checkForCollision() {
        int pacmanX = PacmanLabel.getPacmanX();
        int pacmanY = PacmanLabel.getPacmanY();
        int gridX = pacmanX / UNIT_SIZE;
        int gridY = pacmanY / UNIT_SIZE;

        if (pacmanX % UNIT_SIZE == 0 && pacmanY % UNIT_SIZE == 0 && isPointHere[gridY][gridX] == 0) {
            isPointHere[gridY][gridX] = 1;
            score++;
            updatePoints();
        }
    }
    public static int getPoint(){
        for(int[] a : isPointHere){
            for(int b : a){
                if(b == 0) howManyPoints++;
            }
        }
        return howManyPoints;
    }


    @Override
    public void run() {
        while (true) {
            checkForCollision();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePoints() {
        removeAll();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (isPoint(i, j) && isPointHere[i][j] == 0) {
                    PointLabel point = new PointLabel();
                    point.setSize(UNIT_SIZE, UNIT_SIZE);
                    point.setLocation(j * UNIT_SIZE, i * UNIT_SIZE);
                    add(point);

                }
            }
        }
        revalidate();
        repaint();
    }
}
