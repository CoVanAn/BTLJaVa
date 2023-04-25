import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class SnakeEats extends JFrame {
    GameScreen game;
    // Load du lieu vao list
    public static ArrayList<Score> scores;

    public SnakeEats() {
        setSize(735, 760);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scores = new ArrayList<Score>();
        ReadData();
        game = new GameScreen();
        add(game);
        this.addKeyListener(new handler());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UpdateData();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        SnakeEats f = new SnakeEats();
        System.out.println(f + "Start");
    }

    private class handler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_0) {
                GameScreen.isPlaying = !GameScreen.isPlaying;
                if (GameScreen.isGameOver) {
                    GameScreen.isGameOver = false;
                    game.snake.newGame();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                if (GameScreen.isGameOver == false)
                    GameScreen.isPlaying = true;
                game.snake.setVector(Snake.GO_UP);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                if (GameScreen.isGameOver == false)
                    GameScreen.isPlaying = true;
                game.snake.setVector(Snake.GO_DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                if (GameScreen.isGameOver == false)
                    GameScreen.isPlaying = true;
                game.snake.setVector(Snake.GO_LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                if (GameScreen.isGameOver == false)
                    GameScreen.isPlaying = true;
                game.snake.setVector(Snake.GO_RIGHT);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public static void UpdateData() {
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter("data/dat.txt");
            bw = new BufferedWriter(fw);
            for (Score u : scores) {
                bw.write(u.getScore());
                bw.newLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void ReadData() {
        try {
            FileReader fr = new FileReader("data/dat.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(" ");
                scores.add(new Score(str[0]));
            }
            br.close();
        } catch (IOException ex) {
        }
    }
}
