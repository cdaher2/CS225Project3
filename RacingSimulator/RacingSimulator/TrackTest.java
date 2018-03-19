package RacingSimulator;

import java.io.File;

public class TrackTest {

    public static void main(String args[]) throws InterruptedException {
        Track t = new Track(new File("testtrack.txt"));
        Thread.sleep(100);
        System.out.println(t.toString());
        Segment s;
        for (int i = 0; i < 40; i++){
            s = t.getNextSegment();
            System.out.println(s.toString());

        }
    }
}