package RacingSimulator;

/**
 *
 * @author d_troy, Christian Daher
 */
public class Segment{
    private int length;
    private int width;
    private int angle;

    public Segment(int l, int w, int a){
        length = l;
        width = w;
        angle = a;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getAngle() {
        return angle;
    }

    public String toString(){
        return ("Length: " + length + " Width: " + width + " Angle: " + angle);
    }

}