
package RacingSimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Christian Daher
 *
 * Contains data for the Track in the form of polar vectors
 * Vector should be read by GUI, localized, and used as a reference point to draw a curve
 */
public class Track {

    private File file;
    private ArrayList<Sector> sectors;
    private String trackName;
    private int secTracker;

    /**
     * Instantiates the Track with a File (should be raw text)
     * Data should be formatted as follows:
     * S00:5 -> "S00" refers to "Sector 0", and "5" denotes the surface grip for the sector
     * 05:10:0000 -> "05" denotes Segment length, "10" denotes Segment width, "0000" denotes the angle at which the Segment turns (zero degrees in this case, a straight)
     * 01:10:-005 -> in this case, the track is turning to the left by 5 degrees; the first digit in XXXX can be either a 0 (meaning a positive number, or a right turn) or a - (meaning a negative number, or a left turn)
     * S01:6 -> Sector 1, surface grip is 6
     * etc.
     * in all cases, the number of characters is important - do not deviate from the XXX:Y or XX:YY:ZZZZ format, or things will break
     *
     * @param f File object containing formatted Track data
     */
    public Track(File f){
        secTracker = 0;
        file = f;
        sectors = new ArrayList<Sector>();
        readDataFromFile();
    }

    /**
     * Returns the next Segment in the track
     * Iterates through the ArrayList of Sectors, and uses the method of the same name in Sector to retrieve the next piece of the Track
     * @return The next Segment in line
     */
    public Segment getNextSegment(){
        try{
            System.out.println(secTracker);
            return sectors.get(secTracker).getNextSegment();
        }
        catch (IndexOutOfBoundsException e){
            secTracker = 0;
            System.out.println("Passed starting line");
            return sectors.get(secTracker).getNextSegment();
        }
    }

    /**
     * Reads in Track data from the provided file
     */
    private void readDataFromFile(){
        try {
            Scanner s = new Scanner(file);
            setTrackName(s.nextLine());
            String t = s.nextLine();
            int ts = 0;
            int tsg = 0;
            while (s.hasNext()){

                if (t.substring(0,1).equals("S")) {
                    ts = Integer.valueOf(t.substring(1,3));
                    tsg = Integer.valueOf(t.substring(4,5));
                    sectors.add(new Sector(tsg));
                }
                else{
                    sectors.get(ts).addSegment(Integer.valueOf(t.substring(0,2)),
                            Integer.valueOf(t.substring(3,5)),
                            Integer.valueOf(t.substring(6,10)));
                }

                t = s.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Track file not found, aborting");
        }
    }

    /**
     * Returns the current sector that getNextSegment is on
     * @return sector
     */
    public int getSector(){
        return secTracker;
    }

    /**
     * An organizational class containing a small list of Segments
     * Each Sector has its own surfaceGrip
     * Used for keeping track of car times - this is not a critical function
     */
    protected class Sector{

        private ArrayList<Segment> segments;
        private int surfaceGrip;
        private int segTracker;

        public Sector (int sg){
            segTracker = -1;
            segments = new ArrayList<Segment>();
            surfaceGrip = sg;
        }

        /**
         * Iterates through the Sector's segments and returns the next one in line
         * @return the next Segment in the Sector
         */
        public Segment getNextSegment(){
            try {
                segTracker++;
                segments.get(segTracker + 1);
                return segments.get(segTracker);
            }
            catch (IndexOutOfBoundsException e){
                int l = segTracker;
                segTracker = -1;
                secTracker++;
                return segments.get(l);
            }
        }

        public ArrayList<Segment> getSegments() {
            return segments;
        }

        public int getSurfaceGrip() {
            return surfaceGrip;
        }

        /**
         * Adds a Segment to the Sector
         * @param l length of the Segment
         * @param w width of the Segment
         * @param a angle of the Segment
         */
        public void addSegment(int l, int w, int a){
            segments.add(new Segment(l,w,a, surfaceGrip));
        }

        @Override
        public String toString(){
            String s = "";
            for (int i = 0; i < segments.size(); i++){
                s = s + segments.get(i).toString() + " ";
            }
            return s;
        }

    }

    public void setTrackName(String tn){
        trackName = tn;
    }

    public String getTrackName(){
        return trackName;
    }

    @Override
    public String toString(){
        String s = getTrackName() + " ";
        for (int i = 0; i < sectors.size(); i++){
            s = s + sectors.get(i).toString() + " ";
        }
        return s;
    }

}
