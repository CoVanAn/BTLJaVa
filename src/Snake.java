import java.awt.Graphics;
import java.util.Random;

public class Snake {
    int SLength = 4, i, j, d = 0;
    int Walls = 0, m = 0, n = 0;
    long t1 = 0;
    int[] x = new int[100];
    int[] y = new int[100];
    static int[][] a = new int[200][200];
    static int GO_UP = 1;
    static int GO_DOWN = -1;
    static int GO_LEFT = 2;
    static int GO_RIGHT = -2;
    int vector = Snake.GO_RIGHT;
    int speed = 150, addPoint = 5;
    boolean changeVector = true;
    static int[] fruity = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
    static int fruityImage = new Random().nextInt(fruity.length);

    public Snake() {
        x[0] = 5;
        y[0] = 4;
        x[1] = 4;
        y[1] = 4;
        x[2] = 3;
        y[2] = 4;
        x[3] = 2;
        y[3] = 4;
    }

    // Nhận vector thay đổi
    public void setVector(int v) {
        if ( changeVector)
            vector = v;
        changeVector = false;
    }

    public boolean pointOverlap(int x1, int y1) {
        if (a[x1][y1] == 1)
            return true;
        for (i = 0; i < SLength; i++) {
            if (x[i] == x1 && y[i] == y1)
                return true;
        }
        return false;
    }

    public void newFruity() {
        int x, y;
        d += 1;
        do {
            x = new Random().nextInt(19);
            y = new Random().nextInt(19);
        } while (pointOverlap(x, y));
        GameScreen.BG[x][y] = 1;
    }

    public int getCurrentSpeed() {
        for (int i = 0; i < GameScreen.Level; i++)
            speed *= 0.8;
        return speed;
    }

    public void reWall() {
        for (i = 0; i < 20; i++)
            for (j = 0; j < 20; j++) {
                a[i][j] = 0;
            }
    }

    public void update() {
        if (SLength == addPoint + 3) {
            GameScreen.isPlaying = false;
            resetGame();
            GameScreen.Level++;
            addPoint += 5;
            speed = getCurrentSpeed();
            d = 0;
            GameScreen.LevelUpSound.play();
        }
        // Rắn cắn vào thân
        // for (int i = 1; i < SLength; i++) {
        //     if (x[0] == x[i] && y[0] == y[i]) {
        //         GameScreen.OverSound.play();
        //         GameScreen.isPlaying = false;
        //         GameScreen.isGameOver = true;
        //         SnakeEats.scores.add(new Score(String.valueOf(GameScreen.Score)));
        //     }
        // }
        // Rắn ăn gach
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                if (a[i][j] == 1 && x[0] == i && y[0] == j) {
                    GameScreen.OverSound.play();
                    GameScreen.isPlaying = false;
                    GameScreen.isGameOver = true;
                    SnakeEats.scores.add(new Score(String.valueOf(GameScreen.Score)));
                }
            }
        
        if (System.currentTimeMillis() - t1 > speed) {
            // Bỏ lỗi thay đổi vector đột ngột
            changeVector = true;
            if (GameScreen.BG[x[0]][y[0]] == 1) {
                GameScreen.PointUpSound.play();
                if (fruityImage == 0)
                    GameScreen.Score += 5;
                else
                    GameScreen.Score += 1;
                fruityImage = new Random().nextInt(fruity.length);
                a[x[0]][y[0]] = 1;
                SLength++;
                GameScreen.BG[x[0]][y[0]] = 0;
                newFruity();
            }

            for (i = SLength - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            if (vector == Snake.GO_UP)
                y[0]--;
            if (vector == Snake.GO_DOWN)
                y[0]++;
            if (vector == Snake.GO_LEFT)
                x[0]--;
            if (vector == Snake.GO_RIGHT)
                x[0]++;
            if (x[0] < 0)
                x[0] = 19;
            if (x[0] > 19)
                x[0] = 0;
            if (y[0] < 0)
                y[0] = 19;
            if (y[0] > 19)
                y[0] = 0;
            t1 = System.currentTimeMillis();
        }
    }

    public void resetGame() {
        x = new int[100];
        y = new int[100];
        x[0] = 5;
        y[0] = 4;
        x[1] = 4;
        y[1] = 4;
        x[2] = 3;
        y[2] = 4;
        SLength = 3;
        vector = Snake.GO_RIGHT;
        reWall();
    }

    public void newGame() {
        resetGame();
        GameScreen.Score = 0;
        GameScreen.Level = 1;
        speed = 150;
        addPoint = 5;
        // SnakeEats.ReadData();
    }

    public void ArtSnake(Graphics g) {

        for (i = 1; i < SLength; i++) {
            g.drawImage(Data.imageBody, x[i] * 35 + GameScreen.padding, y[i] * 35 + GameScreen.padding, null);
        }
        if (vector == Snake.GO_UP) {
            g.drawImage(Data.imageTopHead, x[0] * 35 - 12 + GameScreen.padding,
                    y[0] * 35 - 12 + GameScreen.padding, null);
        }

        if (vector == Snake.GO_DOWN) {
            g.drawImage(Data.imageBottomHead, x[0] * 35 - 12 + GameScreen.padding,
                    y[0] * 35 - 12 + GameScreen.padding, null);
        }

        if (vector == Snake.GO_LEFT) {
            g.drawImage(Data.imageLeftHead, x[0] * 35 - 12 + GameScreen.padding,
                    y[0] * 35 - 12 + GameScreen.padding, null);
        }
        if (vector == Snake.GO_RIGHT) {
            g.drawImage(Data.imageRightHead, x[0] * 35 - 12 + GameScreen.padding,
                    y[0] * 35 - 12 + GameScreen.padding, null);
        }
    }
}
