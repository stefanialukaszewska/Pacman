
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoresManager {
    private static final String SCORES_FILE = "scores.txt";
    private static List<Score> bestScores;


    static {
        bestScores = loadScores();
    }

    public ScoresManager() {
    }

    public static void addScore(Score score) {
        bestScores.add(score);
        bestScores.sort(Comparator.comparingInt(Score::getScore).reversed());
        saveScores();
    }

    public List<Score> getBestScores() {
        return new ArrayList<>(bestScores);
    }

    private static void saveScores() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SCORES_FILE))) {
            outputStream.writeObject(bestScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Score> loadScores() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SCORES_FILE))) {
            return (List<Score>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

