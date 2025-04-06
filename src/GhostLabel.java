

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GhostLabel extends JLabel implements Runnable {
    private static final int UNIT_SIZE = 25;
    public int speed = 10;
    private int pixelsPerMove = 1;
    private ImageIcon icon;
    private int ghostX;
    private int ghostY;
    private char direction = 'D';
    private char previousDirection = ' ';

    Random rand = new Random();

    private int startX;
    private int startY;

    public GhostLabel(int x, int y) {
        this.startX = x;
        this.startY = y;
        ghostX = x;
        ghostY = y;
        this.setBounds(ghostX, ghostY, UNIT_SIZE, UNIT_SIZE);
        setOpaque(false);
        setIcon(icon);
        startMovement();

    }


    private boolean canMoveLeft() {
        int X = (ghostX - 1 + UNIT_SIZE) / UNIT_SIZE;
        int Y = ghostY / UNIT_SIZE;
        return ghostY % UNIT_SIZE == 0 && ghostX > UNIT_SIZE && Maps.Blocks[Y][X - 1] != 1;
    }

    private boolean canMoveRight() {
        int X = ghostX / UNIT_SIZE;
        int Y = ghostY / UNIT_SIZE;
        return ghostY % UNIT_SIZE == 0 && ghostX < GameFrame.x_screen - UNIT_SIZE && Maps.Blocks[Y][X + 1] != 1;
    }

    private boolean canMoveUp() {
        int X = ghostX / UNIT_SIZE;
        int Y = (ghostY - 1 + UNIT_SIZE) / UNIT_SIZE;
        return ghostX % UNIT_SIZE == 0 && ghostY > UNIT_SIZE && Maps.Blocks[Y - 1][X] != 1;
    }

    private boolean canMoveDown() {
        int X = ghostX / UNIT_SIZE;
        int Y = ghostY / UNIT_SIZE;
        return ghostX % UNIT_SIZE == 0 && ghostY < GameFrame.x_screen - UNIT_SIZE && Maps.Blocks[Y + 1][X] != 1;
    }

    private void updateAvailableDirections(List<Character> availableDirs) {
        if (canMoveLeft()) availableDirs.add('L');
        if (canMoveRight()) availableDirs.add('R');
        if (canMoveUp()) availableDirs.add('U');
        if (canMoveDown()) availableDirs.add('D');
    }

    private char oppositeDir(char dir) {
        char opDir = ' ';
        switch (dir) {
            case 'L':
                opDir = 'R';
                break;
            case 'R':
                opDir = 'L';
                break;
            case 'U':
                opDir = 'D';
                break;
            case 'D':
                opDir = 'U';
                break;
        }
        return opDir;
    }

    private char nextDirection() {

        List<Character> availableDirs = new ArrayList<>();
        updateAvailableDirections(availableDirs);

        char nextDirection = availableDirs.get(rand.nextInt(availableDirs.size()));
        // this.previousDirection = direction;
        while (nextDirection == oppositeDir(previousDirection)) {
            double randd = Math.random();
            if (randd < 0.1) {
                return nextDirection;
            } else nextDirection = availableDirs.get(rand.nextInt(availableDirs.size()));
        }
        return nextDirection;


    }

    public void startMovement() {
        Thread move = new Thread(this);
        move.start();
    }


    public void ghostMoves() {

        switch (direction) {
            case 'L':
                if (canMoveLeft()) {
                    ghostX -= pixelsPerMove;
                    previousDirection = direction;
                } else direction = nextDirection();
                break;
            case 'R':
                if (canMoveRight()) {
                    ghostX += pixelsPerMove;
                    previousDirection = direction;
                } else direction = nextDirection();
                break;
            case 'U':
                if (canMoveUp()) {
                    ghostY -= pixelsPerMove;
                    previousDirection = direction;
                } else direction = nextDirection();
                break;
            case 'D':
                if (canMoveDown()) {
                    ghostY += pixelsPerMove;
                    previousDirection = direction;
                } else direction = nextDirection();
                break;
        }
        setLocation(ghostX, ghostY);

        checkCollision();
    }

    @Override
    public void run() {
        while (true) {
            ghostMoves();

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetPosition() {
        ghostX = startX;
        ghostY = startY;
        PacmanLabel.resetPacmanPosition();
    }

    private void checkCollision() {
        int x1 = ghostX + 5;
        int x2 = ghostX - 5;
        int y1 = ghostY + 5;
        int y2 = ghostY - 5;

        if (PacmanLabel.getPacmanX() < x1 && PacmanLabel.getPacmanX() > x2 && PacmanLabel.getPacmanY() < y1 && PacmanLabel.getPacmanY() > y2) {
            Console.lifes--;
            resetPosition();
            if (Console.lifes < 0) Console.isGameRunning = false;

        }
    }
}


