package RacingSimulator;

public class SimTester {

    private static Simulator sim;

    public static void main (String args[]){
        sim = new Simulator();
        sim.setTrack(sim.getTrackList().get(0));
        System.out.println(sim.toString());

    }

}
