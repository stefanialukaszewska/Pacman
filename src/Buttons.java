import javax.swing.*;
import java.awt.*;

public class Buttons extends JButton {
    Buttons(String s,int y){
        this.setText(s);
        this.setFocusable(false);
        this.setFont(new Font("Monospaced",Font.BOLD,18));
        this.setBounds(160,y,160,50);
        this.setBackground(Color.YELLOW);
    }
}
