import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MapGrid extends JLabel {
    private static final int ROWS = Maps.getHeight();
    private static final int COLS = Maps.getWidth();
    private static final int UNIT_SIZE = 25;

    Color WALL_COLOR = Color.blue;

    MapGrid() {
        this.setLayout(new GridLayout(ROWS, COLS));
        setSize(new Dimension(GameFrame.x_screen,GameFrame.y_screen));
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel panel = new JPanel();
                if (isWall(i, j)) {
                    panel.setBackground(WALL_COLOR);
                }

                else {
                    panel.setBackground(Color.BLACK);
                }
                add(panel);


            }
        }

    }

    private static boolean isWall ( int row, int col){
        if (Maps.Blocks[row][col] == 1) return true;
        else return false;

    }

}