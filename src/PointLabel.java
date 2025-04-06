import javax.swing.*;

public class PointLabel extends JLabel {

    private ImageIcon pointIcon = new ImageIcon("src/images/point.png");
    int x;
    int y;
    PointLabel(){
        setIcon(pointIcon);
        setOpaque(false);
        setLocation(0,100);
        setSize(25,25);
    }
    public void setXY(int x,int y){
        this.x=x;
        this.y=y;
    }
}
