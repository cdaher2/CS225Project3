
package RacingSimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author d_troy, Christian Daher
 */
public class Track {

    private File file;
    private ArrayList<Sector> sectors;

    public Track(File f){
        file = f;
        sectors = new ArrayList<Sector>();
        readDataFromFile();
    }

    private void readDataFromFile(){
        try {
            Scanner s = new Scanner(file);
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
                    System.out.println(ts);
                }

                s.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Track file not found, aborting");
        }
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < sectors.size(); i++){
            s = s + sectors.get(i).toString() + " ";
        }
        return s;
    }

    protected class Sector{

        private ArrayList<Segment> segments;
        private int surfaceGrip;

        public Sector (int sg){
            segments = new ArrayList<Segment>();
            surfaceGrip = sg;
        }

        public ArrayList<Segment> getSegments() {
            return segments;
        }

        public int getSurfaceGrip() {
            return surfaceGrip;
        }

        public void addSegment(int l, int w, int a){
            segments.add(new Segment(l,w,a));
        }

        public String toString(){
            String s = "";
            for (int i = 0; i < segments.size(); i++){
                s = s + segments.get(i).toString() + " ";
            }
            return s;
        }

    }

}
