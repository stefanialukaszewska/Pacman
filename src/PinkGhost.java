import javax.swing.*;

public class PinkGhost extends GhostLabel{
    private ImageIcon iconRight = new ImageIcon("src/images/ghostPINK.png");

    public PinkGhost(int x, int y) {
        super(x, y);
        setIcon(iconRight);
    }
}
