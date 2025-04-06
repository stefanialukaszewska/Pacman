import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    Frame frame = new Frame();
    Menu(){

        Buttons b1 = new Buttons("New game",140);
        Buttons b2 = new Buttons("High Scores",200);
        Buttons b3 = new Buttons("Exit",260);
        JLabel title = new JLabel("PACMAN");
        title.setFont(new Font("Monospaced",Font.BOLD,45));
        title.setForeground(Color.YELLOW);

        title.setBounds(160,0,500,200);

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuMaps();
                frame.dispose();

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Highscores();
                frame.dispose();
            }
        });

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(title);

    }
}
