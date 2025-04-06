import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PacmanLabel extends JLabel implements KeyListener, Runnable {
    ImageIcon mouthOpenR = new ImageIcon("src/images/openYellow_R.png");
    ImageIcon mouthOpenL = new ImageIcon("src/images/openYellow_L.png");
    ImageIcon mouthOpenU = new ImageIcon("src/images/openYellow_U.png");
    ImageIcon mouthOpenD = new ImageIcon("src/images/openYellow_D.png");

    ImageIcon mouthClosed = new ImageIcon("src/images/closedYellow.png");
    ImageIcon mouthOpen;
    ImageIcon pacmonIcon;
    private static int pacmanX, pacmanY;
    boolean isMouthOpen = true;
    private char direction = 'L';
    private int pixelsPerMove = 1;
    private boolean directionChanged = false;
    private static int startX;
    private static int startY;
    public static int speed=5;


    PacmanLabel() {

        getStartXY();
        pacmanX = startX;
        pacmanY = startY;
        this.setBounds(pacmanX, pacmanY, 25, 25);


        setOpaque(false);

        setFocusable(true);
        addKeyListener(this);
        startMovement();
        startAnimation();



    }

    public void startAnimation() {
        Thread animation = new Thread(this::animationMouth);
        animation.start();
    }

    public void startMovement() {
        Thread move = new Thread(this::move);
        move.start();
    }

    public static void resetPacmanPosition(){
        pacmanX = startX;
        pacmanY = startY;
    }


    private boolean checkL() {
        if (pacmanY % 25 != 0 || pacmanX == GameFrame.UNIT_SIZE) return false;
        else if (Maps.Blocks[pacmanY / GameFrame.UNIT_SIZE][(pacmanX + GameFrame.UNIT_SIZE - pixelsPerMove) / GameFrame.UNIT_SIZE - 1] == 1)
            return false;
        else return true;

    }

    private boolean checkR() {
        if (pacmanY % 25 != 0 || pacmanX == GameFrame.x_screen - GameFrame.UNIT_SIZE) return false;
        else if (Maps.Blocks[pacmanY / GameFrame.UNIT_SIZE][pacmanX / GameFrame.UNIT_SIZE + 1] == 1) return false;
        else return true;
    }

    private boolean checkU() {
        if (pacmanX % 25 != 0 || pacmanY == GameFrame.UNIT_SIZE) return false;
        else if (Maps.Blocks[(pacmanY + GameFrame.UNIT_SIZE - pixelsPerMove) / GameFrame.UNIT_SIZE - 1][pacmanX / GameFrame.UNIT_SIZE] == 1)
            return false;
        else return true;
    }

    private boolean checkD() {
        if (pacmanX % 25 != 0 || pacmanY == GameFrame.x_screen - GameFrame.UNIT_SIZE) return false;
        else if (Maps.Blocks[pacmanY / GameFrame.UNIT_SIZE + 1][pacmanX / GameFrame.UNIT_SIZE] == 1) return false;
        else return true;
    }
    private void getStartXY(){
        for (int i = 0; i < Maps.Blocks.length; i++) {
            for (int j = 0; j < Maps.Blocks[0].length; j++) {
                if(Maps.Blocks[i][j]==2){
                    this.startX = j*GameFrame.UNIT_SIZE;
                    this.startY = i*GameFrame.UNIT_SIZE;
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(direction == 'U' || direction == 'D'){
            switch (key) {
                case KeyEvent.VK_UP:
                    direction = 'U';
                    directionChanged = true;
                    break;
                case KeyEvent.VK_DOWN:
                    direction = 'D';
                    directionChanged = true;
                    break;
            }
            if(pacmanY%GameFrame.UNIT_SIZE==0){
                switch (key){
                    case KeyEvent.VK_LEFT:
                        direction = 'L';
                        directionChanged = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        direction = 'R';
                        directionChanged = true;
                        break;
                }
            }
        }
        else if(direction == 'L' || direction == 'R'){
            switch (key){
                case KeyEvent.VK_LEFT:
                    direction = 'L';
                    directionChanged = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = 'R';
                    directionChanged = true;
                    break;
            }
            if(pacmanX%GameFrame.UNIT_SIZE==0){
                switch (key){
                    case KeyEvent.VK_UP:
                        direction = 'U';
                        directionChanged = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        direction = 'D';
                        directionChanged = true;
                        break;
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void pacmanMoves() {
        switch (direction) {
            case 'L':
                if (checkL()) pacmanX -= pixelsPerMove;
                break;
            case 'R':
                if (checkR()) pacmanX += pixelsPerMove;
                break;
            case 'U':
                if (checkU()) pacmanY -= pixelsPerMove;
                break;
            case 'D':
                if (checkD()) pacmanY += pixelsPerMove;
                break;

        }
        setLocation(pacmanX, pacmanY);

    }

    @Override
    public void run() {
    }



    private void animationMouth() {
        while (true) {
            if (directionChanged || isMouthOpen) {
                switch (direction) {
                    case 'L':
                        mouthOpen = mouthOpenL;
                        break;
                    case 'R':
                        mouthOpen = mouthOpenR;
                        break;
                    case 'U':
                        mouthOpen = mouthOpenU;
                        break;
                    case 'D':
                        mouthOpen = mouthOpenD;
                        break;
                }
                directionChanged = false;
                setIcon(isMouthOpen ? mouthClosed : mouthOpen);
                isMouthOpen = !isMouthOpen;
            } else {
                setIcon(isMouthOpen ? mouthClosed : mouthOpen);
                isMouthOpen = !isMouthOpen;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void move() {
        while (true) {
            pacmanMoves();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getPacmanX(){
        return pacmanX;
    }
    public static int getPacmanY(){
        return pacmanY;
    }
}
