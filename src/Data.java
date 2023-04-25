import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Data {
    public static Image imageBottomHead;
    public static Image imageTopHead;
    public static Image imageLeftHead;
    public static Image imageRightHead;
    public static Image imageBody;

    public static Image imageTopTail;
    public static Image imageLeftTail;
    public static Image imageRightTail;
    public static Image imageBottomTail;

    public static Image imageApple1;
    public static Image imageApple2;
    public static Image imageApple3;
    public static Animation Apple;

    public static Image imageWall;
    public static Image imagePoop;

    public static Image imageOrange;
    public static Image imageCherry;
    public static Image imageBerry;
    public static Image imageCoconut;
    public static Image imagePeach;
    public static Image imageWaterMelon;
    public static Image imageTomato;
    public static Image imagePomegranate;

    // public static
    public static void loadImage() {
        try {
            imageBody = ImageIO.read(new File("image/SnakeBody5.png"));
            imageBottomHead = ImageIO.read(new File("image/Head_Down.png"));
            imageTopHead = ImageIO.read(new File("image/Head_Up.png"));
            imageLeftHead = ImageIO.read(new File("image/Head_Left.png"));
            imageRightHead = ImageIO.read(new File("image/Head_Right.png"));
            
            imageApple1 = ImageIO.read(new File("image/ic_apple6.png"));
            imageApple2 = ImageIO.read(new File("image/ic_apple5.png"));
            imageApple3 = ImageIO.read(new File("image/ic_apple4.png"));

            imageWall = ImageIO.read(new File("image/wall.jpg"));

            imageOrange = ImageIO.read(new File("image/ic_orange2.png"));
            imageCherry = ImageIO.read(new File("image/ic_cherry2.png"));
            imageBerry = ImageIO.read(new File("image/ic_berry2.png"));
            imageCoconut = ImageIO.read(new File("image/ic_coconut2.png"));
            imagePeach = ImageIO.read(new File("image/ic_peach2.png"));
            imageWaterMelon = ImageIO.read(new File("image/ic_watermelon2.png"));
            imageTomato = ImageIO.read(new File("image/ic_tomato2.png"));
            imagePomegranate = ImageIO.read(new File("image/ic_pomegranate2.png"));

        } catch (Exception e) {
        }
    }

    public static void loadAllAnimation() {
        Apple = new Animation();
        Apple.AddImage(imageApple1);
        Apple.AddImage(imageApple2);
        Apple.AddImage(imageApple3);
        Apple.AddImage(imageApple2);
    }

    public static String[] fruity = new String[] { "imageCherry", "imageCoconut", "imageBerry", "imageOrange",
            "imagePeach", "imagePomegranate", "imageTomato", "imageWaterMelon", "Apple" };

}
