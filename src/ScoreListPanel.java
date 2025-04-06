import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreListPanel extends JPanel {
    private JList<String> scoreList;

    public ScoreListPanel() {
        setLayout(new BorderLayout());

        scoreList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(scoreList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateScores(List<Score> scores) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Score score : scores) {
            model.addElement(score.getPlayerName() + ": " + score.getScore());
        }
        scoreList.setModel(model);
    }
}
