import javax.swing.*;


public class RedGhost extends GhostLabel{
    private ImageIcon iconRight = new ImageIcon("src/images/ghostRED.png");
    //int rand

    public RedGhost(int x, int y) {
        super(x, y);
        setIcon(iconRight);


    }

}

