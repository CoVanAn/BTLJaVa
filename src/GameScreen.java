import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.File;
import java.awt.Color;

public class GameScreen extends JPanel implements Runnable {

    Thread thread;
    Thread thread2;
    static int[][] BG = new int[100][100];
    static int padding = 10;
    static int width = 700;
    static int height = 700;
    static boolean isPlaying = false;
    static boolean textGame = true;
    Snake snake;
    static boolean isGameOver = false;
    static int Level = 1;
    static int Score = 0;
    static SoundPlayer LevelUpSound, PointUpSound, OverSound;
    static int fruityImage;

    public GameScreen() {
        snake = new Snake();
        Data.loadImage();
        Data.loadAllAnimation();
        thread = new Thread(this);
        thread.start();
        BG[10][10] = 1;
        LevelUpSound = new SoundPlayer(new File("sound/levelUp.wav"));
        PointUpSound = new SoundPlayer(new File("sound/pointUp.wav"));
        OverSound = new SoundPlayer(new File("sound/over.wav"));
    }

    public int y = 0;

    @Override
    public void paint(Graphics g) {
        paintBG(g);
        snake.ArtSnake(g);
        Border(g);
        if (!isPlaying) {
            if (textGame) {
                g.setFont(getFont().deriveFont(18.0f));
                g.drawString("PRESS SPACE TO PLAY GAME", 220, 100);
            }
        }
        if (isGameOver) {
            g.setFont(getFont().deriveFont(28.0f));
            g.drawString("Game Over", 280, 250);

        }
        g.setFont(getFont().deriveFont(24.0f));
        g.drawString("Level: " + Level, 620, 35);
        g.setFont(getFont().deriveFont(18.0f));
        g.drawString("Score: " + Score, 620, 60);
        int max = GameScreen.Score;
        for (int i = 0; i < SnakeEats.scores.size(); i++)
            if (max < Integer.valueOf(SnakeEats.scores.get(i).toString()))
                max = Integer.valueOf(SnakeEats.scores.get(i).toString());
        g.setFont(getFont().deriveFont(24.0f));
        g.drawString("Highest score: " + max, 20, 35);
    }

    public void paintBG(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, width + padding * 2 , width + padding * 2 );
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {

                if (BG[i][j] == 1) {
                    if (Snake.fruityImage == 1)
                        g.drawImage(Data.imageCherry, i * 35 + padding, j * 35 + padding, null);
                    else if (Snake.fruityImage == 2)
                        g.drawImage(Data.imageCoconut, i * 35 + padding, j * 35 + padding, null);
                    else if (Snake.fruityImage == 3)
                        g.drawImage(Data.imageBerry, i * 35 + padding, j * 35 + padding, null);
                    else if (Snake.fruityImage == 4)
                        g.drawImage(Data.imageOrange, i * 35 + padding, j * 35 + padding, null);
                    else if (Snake.fruityImage == 5)
                        g.drawImage(Data.imagePeach, i * 35 + padding, j * 35 + padding, null);
                    else if (Snake.fruityImage == 6)
                        g.drawImage(Data.imagePomegranate, i * 35 + padding, j * 35 + padding, null);
                    else if (Snake.fruityImage == 7)
                        g.drawImage(Data.imageTomato, i * 35 + padding, j * 35 + padding, null);
                    else if (Snake.fruityImage == 8)
                        g.drawImage(Data.imageWaterMelon, i * 35 + padding, j * 35 + padding, null);
                    else
                        g.drawImage(Data.Apple.getCurrentImage(), i * 35 + padding, j * 35 + padding, null);
                }
                if (Snake.a[i][j] == 1)
                    g.drawImage(Data.imageWall, i * 35 + padding + 3, j * 35 + padding + 2, null);

            }
    }

    public void Border(Graphics g) {
        g.setColor(Color.magenta);
        g.drawRect(1, 1, width + padding * 2 - 2, height + padding * 2 - 2);
        g.drawRect(2, 2, width + padding * 2 - 4, height + padding * 2 - 4);
    }

    @Override
    public void run() {
        long t = 0;
        long t2 = 0;
        while (true) {
            if (System.currentTimeMillis() - t2 > 500) {
                textGame = !textGame;
                t2 = System.currentTimeMillis();
            }
            if (isPlaying) {
                if (System.currentTimeMillis() - t > 200) {
                    Data.Apple.update();
                    t = System.currentTimeMillis();
                }
                snake.update();
            }
            y++;
            repaint();
        }
    }
}
