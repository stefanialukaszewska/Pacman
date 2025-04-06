import javax.swing.*;

public class BlueGhost extends GhostLabel{
    private ImageIcon iconRight = new ImageIcon("src/images/ghostBLUE.png");

    public BlueGhost(int x, int y) {
        super(x, y);
        setIcon(iconRight);

    }


}
