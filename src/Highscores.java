
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Highscores {
    Frame frame = new Frame();
    private ScoresManager scoresManager;
    private ScoreListPanel scoreListPanel;

    public Highscores() {

        scoresManager = new ScoresManager();
        scoreListPanel = new ScoreListPanel();


        frame.setLayout(new BorderLayout());
        frame.add(scoreListPanel);
        JButton button = new JButton("BACK");
        button.setFont(new Font("Monospaced",Font.BOLD,20));
        button.setFocusable(false);
        button.setBackground(Color.BLACK);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Menu();
                frame.dispose();

            }
        });
        frame.add(button,BorderLayout.SOUTH);
        updateScores();

    }

    private void updateScores() {
        List<Score> scores = scoresManager.getBestScores();
        scoreListPanel.updateScores(scores);
    }
}
