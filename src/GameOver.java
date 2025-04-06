import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver {
    Frame frame = new Frame();
    GameOver(){
        frame.setLayout(new GridLayout(3,1));
        JLabel gameover = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameover.setForeground(Color.YELLOW);
        if(PointGrid.score == PointGrid.howManyPoints) gameover.setText("YOU WON");
        else gameover.setText("GAME OVER");
        gameover.setFont(new Font("Monospaced",Font.BOLD,70));



        JButton buttonExit = new JButton("EXIT");
        buttonExit.setFont(new Font("Monospaced",Font.BOLD,20));
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonExit.setBackground(Color.BLACK);
        buttonExit.setFocusable(false);

        frame.add(gameover);
        frame.add(buttonExit);



    }
}
