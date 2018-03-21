/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RacingSimulator;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Christian Daher
 */
public class Simulator {

    private Track track;
    private ArrayList<Track> trackList;
    private ArrayList<Car> cars;
    private int time;

    public Simulator(){
        cars = new ArrayList<>();
        trackList = new ArrayList<>();
        buildCarList();
        buildTrackList();
    }

    /**
     * Builds a list of Cars that will compete on the track
     */
    public void buildCarList(){
        File f = new File("cars");
        File[] matchingFiles = f.listFiles((dir, s) -> s.endsWith("car"));

        try {
            for (int i = 0; i < matchingFiles.length; i++){
                Car c = new Car(matchingFiles[i]);
                cars.add(c);
            }
        }
        catch (NullPointerException e){
            System.out.println("No car files found");
        }
    }


    /**
     * Builds a list of Tracks for the user to choose from in the initial dialogue
     */
    public void buildTrackList(){
        File f = new File("tracks");
        File[] matchingFiles = f.listFiles((dir, s) -> s.endsWith("track"));

        try {
            for (int i = 0; i < matchingFiles.length; i++) {
                Track t = new Track(matchingFiles[i]);
                trackList.add(t);
            }
        }
        catch (NullPointerException e){
            System.out.println("No track files found");
        }
    }

    public ArrayList<Car> getCarList(){
        return cars;
    }

    public ArrayList<Track> getTrackList(){
        return trackList;
    }

    /**
     * Sets the primary Track to the one specified
     * @param t the Track that the simulation will run on
     */
    public void setTrack(Track t){
        track = t;
    }

    public Track getTrack(){
        return track;
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < trackList.size(); i++){
            s = s + trackList.get(i).toString() + " \n";
        }
        for (int i = 0; i < cars.size(); i++){
            s = s + cars.get(i).toString() + " \n";
        }
        try {
            s = s + " Chosen track: " + track.toString() + " \n";
        }
        catch (NullPointerException e){
            s = s + "No track chosen";
        }
        return s;
    }

}
