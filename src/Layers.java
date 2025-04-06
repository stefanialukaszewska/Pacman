import javax.swing.*;
import java.awt.*;

public class Layers extends JLayeredPane {

    PacmanLabel pacmanLabel;
    MapGrid map;

    PointGrid points;

    Layers() {
        Console console = new Console();
        this.setPreferredSize(new Dimension(GameFrame.x_screen, GameFrame.y_screen + Console.y_screen));
        setOpaque(true);
        setBackground(Color.gray);


        this.pacmanLabel = new PacmanLabel();
        this.map = new MapGrid();
        this.points = new PointGrid();


        this.add(points, Integer.valueOf(2));
        this.add(map, Integer.valueOf(1));
        this.add(pacmanLabel, Integer.valueOf(5));
        this.add(console, Integer.valueOf(3));


        BlueGhost blueG = new BlueGhost(GameFrame.x_screen - 2 * GameFrame.UNIT_SIZE, GameFrame.UNIT_SIZE);
        this.add(blueG, Integer.valueOf(4));
        RedGhost redG = new RedGhost(GameFrame.UNIT_SIZE, GameFrame.UNIT_SIZE);
        this.add(redG, Integer.valueOf(4));
        PinkGhost pinkG = new PinkGhost(GameFrame.UNIT_SIZE, GameFrame.y_screen - 2 * GameFrame.UNIT_SIZE);
        this.add(pinkG, Integer.valueOf(4));
        YellowGhost yellowG = new YellowGhost(GameFrame.x_screen - 2 * GameFrame.UNIT_SIZE, GameFrame.y_screen - 2 * GameFrame.UNIT_SIZE);
        this.add(yellowG, Integer.valueOf(4));
    }

}
