import javax.swing.*;

public class YellowGhost extends GhostLabel{
    private ImageIcon iconRight = new ImageIcon("src/images/ghostYELLOW.png");

    public YellowGhost(int x, int y) {
        super(x, y);
        setIcon(iconRight);
    }
}
