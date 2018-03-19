package RacingSimulator;

/**
 * @author Christian Daher
 *
 * A basic data class for Segments
 * Contains the length, width, corner angle, and surface grip of each piece
 */
public class Segment{
    private int length;
    private int width;
    private int angle;
    private int grip;

    /**
     * Instantiates the Segment with data
     * @param l Segment length
     * @param w Segment width
     * @param a Segment corner angle
     * @param g Segment surface grip
     */
    public Segment(int l, int w, int a, int g){
        length = l;
        width = w;
        angle = a;
        grip = g;
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

    public int getGrip() {
        return grip;
    }

    @Override
    public String toString(){
        return ("Length: " + length + " Width: " + width + " Angle: " + angle + " Surface grip: " + grip);
    }

}