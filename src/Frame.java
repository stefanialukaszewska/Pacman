import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Frame(){
        this.setTitle("PACMAN");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
